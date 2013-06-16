package org.propertyinsurance.motor.domain.business;

import com.esotericsoftware.reflectasm.MethodAccess;
import org.propertyinsurance.motor.BusinessException;
import org.propertyinsurance.motor.Errors;
import org.propertyinsurance.motor.domain.CommonInfo;
import org.propertyinsurance.motor.domain.ProposalQueryType;
import org.propertyinsurance.motor.domain.VehicleInfo;
import org.propertyinsurance.motor.domain.area.Area;
import org.propertyinsurance.motor.domain.business.proposal.ProposalQuery;
import org.propertyinsurance.motor.domain.business.proposal.ProposalQuerySpecification;
import org.propertyinsurance.motor.domain.business.vehicle.VehicleQuerySpecification;
import org.propertyinsurance.motor.infrastructure.*;
import com.sinosoft.ebusiness.thirdparty.prpall.client.facade.PrpallClientService;
import com.sinosoft.ebusiness.thirdparty.prpall.client.model.caculate.actualvalue.ActualValueRequest;
import com.sinosoft.ebusiness.thirdparty.prpall.client.model.caculate.actualvalue.ActualValueResponse;
import com.sinosoft.ebusiness.thirdparty.prpall.client.model.carquery.CarQueryRequest;
import com.sinosoft.ebusiness.thirdparty.prpall.client.model.carquery.CarQueryResponse;
import com.sinosoft.ebusiness.thirdparty.prpall.client.model.poliquery.RequestDto;
import com.sinosoft.ebusiness.thirdparty.prpall.client.model.poliquery.ResponseDto;
import com.sinosoft.ebusiness.thirdparty.prpall.client.model.userquery.UserQueryRequest;
import com.sinosoft.ebusiness.thirdparty.prpall.client.model.userquery.UserQueryResponse;
import com.sinosoft.ebusiness.thirdparty.prpall.client.spring.PrpallClientServiceImpl;

import java.rmi.RemoteException;

/**
 * 核心业务实现
 * <p/>
 * User: zhenguang.zhu
 * Date: 12-12-9
 * Time: 下午9:35
 */
public class BusinessServiceImpl implements BusinessService {
    private PrpallClientService clientService;
    private VehicleQueryTranslator vehicleQueryTranslator;
    private ActualPriceTranslator actualPriceTranslator;
    private SamsungUserTranslator samsungUserTranslator;
    private RenewalTranslator renewalTranslator;

    public BusinessServiceImpl() {
        this.clientService = new PrpallClientServiceImpl();
        this.samsungUserTranslator = new SamsungUserTranslator();
        this.vehicleQueryTranslator = new VehicleQueryTranslator();
        this.actualPriceTranslator = new ActualPriceTranslator();
        this.renewalTranslator = new RenewalTranslator();
    }

    @Override
    public VehicleInfo queryVehicleInfo(VehicleQuerySpecification specification) throws BusinessException {
        VehicleQueryRequestTranslator requestTranslator = new VehicleQueryRequestTranslator(specification);
        requestTranslator.translate();
        CarQueryRequest carQueryRequest = requestTranslator.getRequest();

        try {
            MethodAccess access = MethodAccess.get(PrpallClientService.class);
            CarQueryResponse response = (CarQueryResponse) access.invoke(getClientService(),
                    getVehicleQueryMethodString(specification.getArea()), carQueryRequest);
            VehicleQueryResponseTranslator responseTranslator = new VehicleQueryResponseTranslator
                    (response, specification);
            responseTranslator.translate();

            VehicleInfo vehicleInfo = responseTranslator.getVehicleInfo();// 车型查询结果
            checkCarownerAndLicenseNo(vehicleInfo, specification);
            return vehicleInfo;
        } catch (Exception e) {
            throw BusinessException.create(Errors.NET_ERROR);
        }
    }

    /*
     * 获得车型查询的方法名称
     */
    private String getVehicleQueryMethodString(Area area) {
        return area.getPrefix() + VehicleQuerySpecification.CAR_QUERY_SUFFIX;
    }

    /*
     * 检查车主与车牌号是否匹配
     */
    private void checkCarownerAndLicenseNo(VehicleInfo vehicleInfo, VehicleQuerySpecification specification)
            throws BusinessException{
        if (!vehicleInfo.getCarOwner().equals(specification.getCarOwner())) {// 车主与车牌号不匹配
            throw BusinessException.create(Errors.OWNER_LICENSENO_ERROR);
        }
    }

    @Override
    public double queryActualPrice(ActualPriceSpecification specification) throws BusinessException {
        ActualValueRequest actualValueRequest = actualPriceTranslator.translateFromSpecificationToRequest(
                specification);
        ActualValueResponse response = null;
        try {
            response = getClientService().actualValueCalculate(actualValueRequest);
        } catch (RemoteException e) {
            throw BusinessException.create(Errors.NET_ERROR);
        }
        return actualPriceTranslator.translateFromResponseToObject(response, specification);
    }

    @Override
    public CommonInfo queryProposalInfo(ProposalQuerySpecification specification) throws BusinessException {
        try {
            ProposalQuery proposalQuery = ProposalQuery.create(ProposalQueryType.COMMERIAL);
            return proposalQuery.query(specification);
        } catch (Exception e) {
            throw BusinessException.create(Errors.NET_ERROR);
        }
    }

    @Override
    public String queryUserInfo(SamsungUserSpecification specification) throws BusinessException {
        UserQueryRequest request = samsungUserTranslator.translateFromSpecificationToRequest(
                specification);
        UserQueryResponse response = null;
        try {
            response = getClientService().isSamsungUser(request);
        } catch (RemoteException e) {
            throw BusinessException.create(Errors.NET_ERROR);
        }
        return samsungUserTranslator.translateFromResponseToObject(response, specification);
    }

    @Override
    public CommonInfo queryLastPolicyInfo(LastPolicySpecification specification)
            throws BusinessException {
        LastPolicyTranslator lastPolicyTranslator = LastPolicyTranslator.create(specification.getTranslator());
        RequestDto requestDto = lastPolicyTranslator.translateFromSpecificationToRequest(specification);
        ResponseDto responseDto = null;
        try {
            responseDto = getClientService().queryLastPolicy(requestDto);
        } catch (RemoteException e) {
            throw BusinessException.create(Errors.NET_ERROR);
        }
        return lastPolicyTranslator.translateFromResponseToObject(responseDto, specification);
    }

    @Override
    public Boolean checkRenewal(RenewalSpecification specification) throws BusinessException {
        com.sinosoft.ebusiness.thirdparty.prpall.client.model.renewquery.RequestDto requestDto =
                renewalTranslator.translateFromSpecificationToRequest(specification);
        com.sinosoft.ebusiness.thirdparty.prpall.client.model.renewquery.ResponseDto responseDto = null;
        try {
            responseDto = getClientService().renewQuery(requestDto);
        } catch (RemoteException e) {
            throw BusinessException.create(Errors.NET_ERROR);
        }
        return renewalTranslator.translateFromResponseToObject(responseDto, specification);
    }

    private PrpallClientService getClientService() {
        return clientService;
    }
}
