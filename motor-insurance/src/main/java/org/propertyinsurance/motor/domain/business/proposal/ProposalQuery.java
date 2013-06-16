package org.propertyinsurance.motor.domain.business.proposal;

import com.esotericsoftware.reflectasm.MethodAccess;
import org.propertyinsurance.motor.BusinessException;
import org.propertyinsurance.motor.domain.area.Area;
import org.propertyinsurance.motor.domain.CommonInfo;
import org.propertyinsurance.motor.domain.ProposalQueryType;
import org.propertyinsurance.motor.infrastructure.ProposalQueryTranslator;
import com.sinosoft.ebusiness.thirdparty.prpall.client.facade.PrpallClientService;
import com.sinosoft.ebusiness.thirdparty.prpall.client.model.tbquery.TBQueryRequest;
import com.sinosoft.ebusiness.thirdparty.prpall.client.model.tbquery.TBQueryResponse;

/**
 * 投保查询，分为商业险和交强险，并且暂时再细分为苏州和上海
 * <p/>
 * User: zhenguang.zhu
 * Date: 13-1-6
 * Time: 下午1:47
 */
public abstract class ProposalQuery {
    private static final String QUERY_SUFFIX = "TBQuery";

    private PrpallClientService clientService;
    private ProposalQueryTranslator translator;

    /**
     * 根据投保查询类型创建投保查询类别
     *
     * @param type
     * @return
     */
    public static ProposalQuery create(ProposalQueryType type) {
        switch (type) {
            case COMMERIAL:
                return new CommercialQuery();
            case TRAFFIC:
                return new TrafficQuery();
            default:
                throw new BusinessException("投保查询必须是商业险或者交强险");
        }
    }

    /*
     * 获取投保查询的方法名称
     *
     * SHTBQuery
     * SZTBQuery
     */
    protected String getQueryMethodString(Area area) {
        return area.getPrefix() + QUERY_SUFFIX;
    }

    protected PrpallClientService getClientService() {
        return clientService;
    }

    protected ProposalQueryTranslator getTranslator() {
        return translator;
    }

    /**
     * 投保查询
     *
     * @param specification
     * @return 车辆信息
     */
    public abstract CommonInfo query(ProposalQuerySpecification specification);
}

/**
 * 商业险投保查询
 */
class CommercialQuery extends ProposalQuery {
    @Override
    public CommonInfo query(ProposalQuerySpecification specification) {
        TBQueryRequest request = getTranslator().translateFromSpecificationToRequest(specification);
        MethodAccess access = MethodAccess.get(PrpallClientService.class);
        TBQueryResponse tbQueryResponse = (TBQueryResponse) access.invoke(getClientService(),
                getQueryMethodString(specification.getArea()), request);
        return getTranslator().translateFromResponseToObject(tbQueryResponse, specification);
    }
}

/**
 * 交强险投保查询
 */
class TrafficQuery extends ProposalQuery {
    @Override
    public CommonInfo query(ProposalQuerySpecification specification) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
