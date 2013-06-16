package org.propertyinsurance.motor.infrastructure;

import com.sinosoft.ebusiness.thirdparty.prpall.client.model.tbquery.TBQueryCarInputDto;
import com.sinosoft.ebusiness.thirdparty.prpall.client.model.tbquery.TBQueryKindInputDto;
import com.sinosoft.ebusiness.thirdparty.prpall.client.model.tbquery.TBQueryRequest;
import com.sinosoft.ebusiness.thirdparty.prpall.client.model.tbquery.TBQueryRequestBody;
import org.propertyinsurance.motor.BusinessException;
import org.propertyinsurance.motor.DateUtil;
import org.propertyinsurance.motor.DozerUtil;
import org.propertyinsurance.motor.domain.business.proposal.ProposalQuerySpecification;

/**
 * 商业险投保查询请求翻译
 * <p/>
 * User: zhu
 * Date: 13-2-14
 * Time: 下午6:18
 */
public class ProposalQueryRequestTranslator implements Translator {
    private static final String COM_COVERAGE_CODE = "B";

    private ProposalQuerySpecification specification;
    private TBQueryRequest request;

    @Override
    public void translate() throws BusinessException {
        TBQueryRequestBody body = new TBQueryRequestBody();
        TBQueryCarInputDto car = DozerUtil.map(specification, TBQueryCarInputDto.class);
        body.setCar(car);
        body.setKind(getKinds());

        this.request = new TBQueryRequest(body, RequestHead.createDto(RequestType.proposalQuery,
                specification.getArea()));
    }

    /*
     * 获得险别信息
     */
    private TBQueryKindInputDto[] getKinds() {
        TBQueryKindInputDto kind = new TBQueryKindInputDto();
        kind.setComCoverageCode(COM_COVERAGE_CODE);
        kind.setStartDate(DateUtil.getTodayPlus2DateString());
        kind.setEndDate(DateUtil.getTodayPlus1YearString());
        return new TBQueryKindInputDto[]{kind};
    }

    public TBQueryRequest getRequest() {
        return request;
    }
}
