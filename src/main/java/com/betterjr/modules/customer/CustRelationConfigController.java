package com.betterjr.modules.customer;

import static com.betterjr.common.web.ControllerExceptionHandler.exec;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;
import com.betterjr.common.web.Servlets;

@Controller
@RequestMapping("/Platform/CustRelationConfig")
public class CustRelationConfigController {
    
    private static final Logger logger = LoggerFactory.getLogger(CustRelationConfigController.class);
    
    @Reference(interfaceClass=ICustRelationConfigService.class)
    private ICustRelationConfigService custRelationConfigService;
    
    @RequestMapping(value = "/findCustByPlatform", method = RequestMethod.POST)
    public @ResponseBody String findCustByPlatform(String custType) {
        return exec(() -> custRelationConfigService.webFindCustByPlatform(custType), "查询需要选择的客户类型", logger);
    }
    
    @RequestMapping(value = "/findCustType", method = RequestMethod.POST)
    public @ResponseBody String findCustType() {
        return exec(() -> custRelationConfigService.webFindCustType(), "查询需要选择的客户类型", logger);
    }
    
    @RequestMapping(value = "/findCustInfo", method = RequestMethod.POST)
    public @ResponseBody String findCustInfo(String custType,Long custNo,String custName) {
        logger.info("添加客户关系，入参：custType="+custType+"，custNo="+custNo+"，custName："+custName);
        return exec(() -> custRelationConfigService.webFindCustInfo(custType,custNo,custName), "查询客户信息", logger);
    }
    
    @RequestMapping(value = "/addCustRelation", method = RequestMethod.POST)
    public @ResponseBody String addCustRelation(String custType,Long custNo,String relationCustStr) {
        logger.info("添加客户关系，入参：custType="+custType+"，custNo="+custNo+"，relationCustStr="+relationCustStr);
        return exec(() -> custRelationConfigService.webAddCustRelation(custType, custNo, relationCustStr), "添加客户关系", logger);
    }
    
    @RequestMapping(value = "/queryCustRelation", method = RequestMethod.POST)
    public @ResponseBody String queryCustRelation(Long custNo,String flag,int pageNum,int pageSize) {
        logger.info("添加客户关系，入参：custNo="+custNo);
        return exec(() -> custRelationConfigService.webQueryCustRelation(custNo,flag,pageNum,pageSize,""), "分页查询客户关系信息", logger);
    }
    
    
    @RequestMapping(value = "/queryCustRelationByCore", method = RequestMethod.POST)
    public @ResponseBody String queryCustRelationByCore(Long custNo,String relationType,String flag,int pageNum,int pageSize) {
        logger.info("添加客户关系，入参：custNo="+custNo);
        return exec(() -> custRelationConfigService.webQueryCustRelation(custNo,flag,pageNum,pageSize,relationType), "分页查询客户关系信息", logger);
    }
    // 查询当前客户的类型
    @RequestMapping(value = "/findCustTypeByLogin", method = RequestMethod.POST)
    public @ResponseBody String findCustTypeByLogin() {
        return exec(() -> custRelationConfigService.webFindCustTypeByCustNo(), "查询当前客户的类型", logger);
    }
    
    // 查询要上传的文件列表
    @RequestMapping(value = "/findCustAduitTemp", method = RequestMethod.POST)
    public @ResponseBody String findCustAduitTemp(Long relateCustNo,Long selectCustNo) {
        return exec(() -> custRelationConfigService.webFindCustAduitTempFile(relateCustNo,selectCustNo), "查询临时文件", logger);
    }
    
    // 查询保理业务申请数据
    @RequestMapping(value = "/findFactorBusinessRequest", method = RequestMethod.POST)
    public @ResponseBody String findFactorBusinessRequest(Long custNo) {
        return exec(() -> custRelationConfigService.webFindFactorBusinessRequestData(custNo), "查询保理业务申请数据", logger);
    }
    
    /***
     * 查询电子合同服务商客户
     * @return
     */
    @RequestMapping(value = "/findElecAgreementServiceCust", method = RequestMethod.POST)
    public @ResponseBody String findElecAgreementServiceCust() {
        return exec(() -> custRelationConfigService.webFindElecAgreementServiceCust(), "查询电子合同服务商客户", logger);
    }
    
    /***
     * 添加保理方客户关系
     * @param custType
     * @param relationCustNo
     * @return
     */
    @RequestMapping(value = "/addFactorCustRelation", method = RequestMethod.POST)
    public @ResponseBody String addFactorCustRelation(String factorCustType,String wosCustType,String factorCustNoList,String wosCustNoList,Long custNo) {
        logger.info("添加保理方客户关系，入参：factorCustType="+factorCustType+"，wosCustType="+wosCustType+"，factorCustNoList="+factorCustNoList+"，wosCustStr="+wosCustNoList+"，custNo="+custNo);
        return exec(() -> custRelationConfigService.webAddFactorCustRelation(factorCustType, wosCustType, factorCustNoList, wosCustNoList,custNo), "添加保理方客户关系", logger);
    }
    
    /***
     * 添加客户文件关系
     * @param relateCustNo 关联的客户号
     * @param fileIds 上传的文件列表(以,分隔)
     * @param custType 客户类型
     */
    @RequestMapping(value = "/saveCustAduitTempFile", method = RequestMethod.POST)
    public @ResponseBody String saveCustAduitTempFile(Long relateCustNo,String fileIds,String custType,Long custNo){
        logger.info("添加客户文件关系，入参：relateCustNo="+relateCustNo+"，fileIds="+fileIds+"，custType="+custType+"，custNo="+custNo);
        return exec(() -> custRelationConfigService.webSaveCustAduitTempFile(relateCustNo, fileIds, custType,custNo), "添加客户文件关系", logger);
    }
    
    /***
     * 查询关联的临时文件
     * @param custNo 关联查询附件的客户号
     * @return
     */
    @RequestMapping(value = "/findRelateAduitTempFile", method = RequestMethod.POST)
    public @ResponseBody String findRelateAduitTempFile(Long custNo){
        logger.info("查询关联的临时文件，入参：custNo="+custNo);
        return exec(() -> custRelationConfigService.webFindRelateAduitTempFile(custNo), "查询关联的临时文件", logger);
    }
    
    /***
     * 受理审批
     * @param request
     * @return
     */
    @RequestMapping(value = "/saveAcceptAduit", method = RequestMethod.POST)
    public @ResponseBody String saveAcceptAduit(final HttpServletRequest request){
        final Map anMap = Servlets.getParametersStartingWith(request, "");
        logger.info("入参：" + anMap);
        return exec(() -> custRelationConfigService.webSaveAcceptAduit(anMap), "查询关联的临时文件", logger);
    }
    
    /***
     * 查询审批记录
     * @param request
     * @return
     */
    @RequestMapping(value = "/findCustRelateAduitRecord", method = RequestMethod.POST)
    public @ResponseBody String findCustRelateAduitRecord(Long custNo,Long selectCustNo,String relateType){
        logger.info("入参：custNo:" + custNo+"，selectCustNo:"+selectCustNo);
        return exec(() -> custRelationConfigService.webFindCustRelateAduitRecord(custNo,selectCustNo,relateType), "查询审批记录", logger);
    }
    
    
}
