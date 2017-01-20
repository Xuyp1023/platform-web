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
import com.alibaba.dubbo.rpc.RpcException;
import com.betterjr.common.exception.BytterException;
import com.betterjr.common.web.AjaxObject;
import com.betterjr.common.web.Servlets;

@Controller
@RequestMapping("/Platform/Account")
public class CustOpenAccountController {

    private static final Logger logger = LoggerFactory.getLogger(CustOpenAccountController.class);

    @Reference(interfaceClass = ICustOpenAccountService.class)
    private ICustOpenAccountService custOpenAccountService;

    @RequestMapping(value = "/findAccInfo", method = RequestMethod.POST)
    public @ResponseBody String findOpenAccountInfo() {
        logger.info("客户开户资料读取");
        try {

            return custOpenAccountService.webFindOpenAccountInfo();
        }
        catch (RpcException e) {
            logger.error(e.getMessage(), e);
            if (BytterException.isCauseBytterException(e)) {
                return AjaxObject.newError(e.getCause().getMessage()).toJson();
            }
            return AjaxObject.newError("客户开户资料读取失败").toJson();
        }
        catch (Exception e) {
            logger.error(e.getMessage(), e);
            return AjaxObject.newError("客户开户资料读取失败").toJson();
        }
    }

    @RequestMapping(value = "/findAccInfoById", method = RequestMethod.POST)
    public @ResponseBody String findOpenAccountInfoById(Long id) {
        logger.info("客户开户资料读取");
        try {

            return custOpenAccountService.webFindOpenAccountInfo(id);
        }
        catch (RpcException e) {
            logger.error(e.getMessage(), e);
            if (BytterException.isCauseBytterException(e)) {
                return AjaxObject.newError(e.getCause().getMessage()).toJson();
            }
            return AjaxObject.newError("客户开户资料读取失败").toJson();
        }
        catch (Exception e) {
            logger.error(e.getMessage(), e);
            return AjaxObject.newError("客户开户资料读取失败").toJson();
        }
    }

    @RequestMapping(value = "/saveAccInfo", method = RequestMethod.POST)
    public @ResponseBody String saveOpenAccountInfo(HttpServletRequest request, Long id, String fileList) {
        Map<String, Object> anMap = Servlets.getParametersStartingWith(request, "");
        logger.info("客户开户资料暂存,入参：" + anMap.toString());
        try {

            return custOpenAccountService.webSaveOpenAccountInfo(anMap, id, fileList);
        }
        catch (RpcException e) {
            logger.error(e.getMessage(), e);
            if (BytterException.isCauseBytterException(e)) {
                return AjaxObject.newError(e.getCause().getMessage()).toJson();
            }
            return AjaxObject.newError("客户开户资料暂存失败").toJson();
        }
        catch (Exception e) {
            logger.error(e.getMessage(), e);
            return AjaxObject.newError("客户开户资料暂存失败").toJson();
        }
    }

    @RequestMapping(value = "/saveAccApply", method = RequestMethod.POST)
    public @ResponseBody String saveOpenAccountApply(HttpServletRequest request, Long id, String fileList) {
        Map<String, Object> anMap = Servlets.getParametersStartingWith(request, "");
        logger.info("开户申请,入参：" + anMap.toString());
        try {

            return custOpenAccountService.webSaveOpenAccountApply(anMap, id, fileList);
        }
        catch (RpcException e) {
            logger.error(e.getMessage(), e);
            if (BytterException.isCauseBytterException(e)) {
                return AjaxObject.newError(e.getCause().getMessage()).toJson();
            }
            return AjaxObject.newError("开户申请提交失败").toJson();
        }
        catch (Exception e) {
            logger.error(e.getMessage(), e);
            return AjaxObject.newError("开户申请提交失败").toJson();
        }
    }

    @RequestMapping(value = "/queryAccApply", method = RequestMethod.POST)
    public @ResponseBody String queryOpenAccountApply(String flag, int pageNum, int pageSize) {
        try {

            return custOpenAccountService.webQueryOpenAccountApply(flag, pageNum, pageSize);
        }
        catch (RpcException e) {
            logger.error(e.getMessage(), e);
            if (BytterException.isCauseBytterException(e)) {
                return AjaxObject.newError(e.getCause().getMessage()).toJson();
            }
            return AjaxObject.newError("开户申请待审批列表查询失败").toJson();
        }
        catch (Exception e) {
            logger.error(e.getMessage(), e);
            return AjaxObject.newError("开户申请待审批列表查询失败").toJson();
        }
    }

    @RequestMapping(value = "/saveAuditccApply", method = RequestMethod.POST)
    public @ResponseBody String saveAuditOpenAccountApply(Long id, String auditOpinion) {
        logger.info("开户审核,入参：" + id + " and " + auditOpinion);
        try {

            return custOpenAccountService.webSaveAuditOpenAccountApply(id, auditOpinion);
        }
        catch (RpcException e) {
            logger.error(e.getMessage(), e);
            if (BytterException.isCauseBytterException(e)) {
                return AjaxObject.newError(e.getCause().getMessage()).toJson();
            }
            return AjaxObject.newError("开户审核失败").toJson();
        }
        catch (Exception e) {
            logger.error(e.getMessage(), e);
            return AjaxObject.newError("开户审核失败").toJson();
        }
    }

    @RequestMapping(value = "/saveRefuseAccApply", method = RequestMethod.POST)
    public @ResponseBody String saveRefuseOpenAccountApply(Long id, String auditOpinion) {
        logger.info("开户申请驳回,入参：" + id + " and " + auditOpinion);
        try {

            return custOpenAccountService.webSaveRefuseOpenAccountApply(id, auditOpinion);
        }
        catch (RpcException e) {
            logger.error(e.getMessage(), e);
            if (BytterException.isCauseBytterException(e)) {
                return AjaxObject.newError(e.getCause().getMessage()).toJson();
            }
            return AjaxObject.newError("开户申请驳回失败").toJson();
        }
        catch (Exception e) {
            logger.error(e.getMessage(), e);
            return AjaxObject.newError("开户申请驳回失败").toJson();
        }
    }

    @RequestMapping(value = "/saveAccInfoInstead", method = RequestMethod.POST)
    public @ResponseBody String saveOpenAccountInfoByInstead(HttpServletRequest request, Long insteadRecordId, String fileList) {
        Map<String, Object> anMap = Servlets.getParametersStartingWith(request, "");
        logger.info("代录开户资料提交,入参：" + anMap.toString());
        try {

            return custOpenAccountService.webSaveOpenAccountInfoByInstead(anMap, insteadRecordId, fileList);
        }
        catch (RpcException e) {
            logger.error(e.getMessage(), e);
            if (BytterException.isCauseBytterException(e)) {
                return AjaxObject.newError(e.getCause().getMessage()).toJson();
            }
            return AjaxObject.newError("代录开户资料提交失败").toJson();
        }
        catch (Exception e) {
            logger.error(e.getMessage(), e);
            return AjaxObject.newError("代录开户资料提交失败").toJson();
        }
    }

    @RequestMapping(value = "/findAccInfoInstead", method = RequestMethod.POST)
    public @ResponseBody String findOpenAccountInfoByInsteadId(Long id) {
        logger.info("代录开户资料读取,入参：" + id);
        try {

            return custOpenAccountService.webFindOpenAccountInfoByInsteadId(id);
        }
        catch (RpcException e) {
            logger.error(e.getMessage(), e);
            if (BytterException.isCauseBytterException(e)) {
                return AjaxObject.newError(e.getCause().getMessage()).toJson();
            }
            return AjaxObject.newError("代录开户资料读取失败").toJson();
        }
        catch (Exception e) {
            logger.error(e.getMessage(), e);
            return AjaxObject.newError("代录开户资料读取失败").toJson();
        }
    }

    @RequestMapping(value = "/queryAccWorkflow", method = RequestMethod.POST)
    public @ResponseBody String queryOpenAccWorkflow(Long custNo) {
        logger.info("开户审批流程查询,入参：" + custNo);
        try {

            return custOpenAccountService.webQueryAuditWorkflow(custNo);
        }
        catch (RpcException e) {
            logger.error(e.getMessage(), e);
            if (BytterException.isCauseBytterException(e)) {
                return AjaxObject.newError(e.getCause().getMessage()).toJson();
            }
            return AjaxObject.newError("开户审批流程查询失败").toJson();
        }
        catch (Exception e) {
            logger.error(e.getMessage(), e);
            return AjaxObject.newError("开户审批流程查询失败").toJson();
        }
    }

    @RequestMapping(value = "/queryAccWorkflowById", method = RequestMethod.POST)
    public @ResponseBody String queryOpenAccWorkflowById(Long openAccId) {
        logger.info("开户审批流程查询,入参：" + openAccId);
        try {

            return custOpenAccountService.webQueryAuditWorkflowById(openAccId);
        }
        catch (RpcException e) {
            logger.error(e.getMessage(), e);
            if (BytterException.isCauseBytterException(e)) {
                return AjaxObject.newError(e.getCause().getMessage()).toJson();
            }
            return AjaxObject.newError("开户审批流程查询失败").toJson();
        }
        catch (Exception e) {
            logger.error(e.getMessage(), e);
            return AjaxObject.newError("开户审批流程查询失败").toJson();
        }
    }
    
    /**
     * 检查申请机构名称是否存在
     */
    @RequestMapping(value = "/checkCustExistsByCustName", method = RequestMethod.POST)
    public @ResponseBody String checkCustExistsByCustName(String custName) {
        logger.info("检查申请机构名称是否存在,入参: " + custName);
        return exec(() -> custOpenAccountService.webCheckCustExistsByCustName(custName), "检查申请机构名称是否存在失败", logger);
    }
    
    /**
     * 检查组织机构代码证是否存在
     */
    @RequestMapping(value = "/checkCustExistsByIdentNo", method = RequestMethod.POST)
    public @ResponseBody String checkCustExistsByIdentNo(String identNo){
        logger.info("检查组织机构代码证是否存在,入参: " + identNo);
        return exec(() -> custOpenAccountService.webCheckCustExistsByIdentNo(identNo), "检查组织机构代码证是否存在失败", logger);
    }
    
    /**
     * 检查营业执照号码是否存在
     */
    @RequestMapping(value = "/checkCustExistsByBusinLicence", method = RequestMethod.POST)
    public @ResponseBody String checkCustExistsByBusinLicence(String businLicence) {
        logger.info("检查营业执照号码是否存在,入参: " + businLicence);
        return exec(() -> custOpenAccountService.webCheckCustExistsByBusinLicence(businLicence), "检查营业执照号码是否存在失败", logger);
    }
    
    /**
     * 检查银行账号是否存在
     */
    @RequestMapping(value = "/checkCustExistsByBankAccount", method = RequestMethod.POST)
    public @ResponseBody String checkCustExistsByBankAccount(String bankAccount) {
        logger.info("检查银行账号是否存在,入参: " + bankAccount);
        return exec(() -> custOpenAccountService.webCheckCustExistsByBankAccount(bankAccount), "检查银行账号是否存在失败", logger);
    }
    
    /**
     * 检查电子邮箱是否存在
     */
    @RequestMapping(value = "/checkCustExistsByEmail", method = RequestMethod.POST)
    public @ResponseBody String checkCustExistsByEmail(String email) {
        logger.info("检查电子邮箱是否存在,入参: " + email);
        return exec(() -> custOpenAccountService.webCheckCustExistsByEmail(email), "检查电子邮箱是否存在失败", logger);
    }
    
    /**
     * 检查银行账号是否存在
     */
    @RequestMapping(value = "/checkCustExistsByMobileNo", method = RequestMethod.POST)
    public @ResponseBody String checkCustExistsByMobileNo(String mobileNo) {
        logger.info("检查手机号码是否存在,入参: " + mobileNo);
        return exec(() -> custOpenAccountService.webCheckCustExistsByMobileNo(mobileNo), "检查手机号码是否存在失败", logger);
    }
    
    /**
     * 开户申请提交
     */
    @RequestMapping(value = "/saveOpenAccountApplySubmit", method = RequestMethod.POST)
    public @ResponseBody String saveOpenAccountApplySubmit(HttpServletRequest request, Long operId, String fileList) {
        Map<String, Object> anMap = Servlets.getParametersStartingWith(request, "");
        logger.info("开户申请提交,入参: " + anMap.toString());
        return exec(() -> custOpenAccountService.webSaveOpenAccountApplySubmit(anMap, operId, fileList), "开户申请提交失败", logger);
    }

    /**
     * PC端使用，根据operOrg查询insteadApply的业务状态
     */
    @RequestMapping(value = "/findInsteadApplyStatus", method = RequestMethod.POST)
    public @ResponseBody String findInsteadApplyStatus() {
        return exec(() -> custOpenAccountService.webFindInsteadApplyStatus(), "状态查询失败", logger);
    }
    
    /**
     * PC端使用，根据operOrg查询开户信息
     */
    @RequestMapping(value = "/findOpenAccoutnTmp", method = RequestMethod.POST)
    public @ResponseBody String findOpenAccoutnTmp() {
        return exec(() -> custOpenAccountService.webFindOpenAccoutnTmp(), "信息查询失败", logger);
    }
    
    
    /**
     * 微信查询开户资料
     */
    @RequestMapping(value = "/findAccountTmpInfo", method = RequestMethod.POST)
    public @ResponseBody String findAccountTmpInfo() {
        try {
            final Object openIdObj = Servlets.getSession().getAttribute("wechat_openId");
            if (openIdObj != null) {
                final String openId = String.valueOf(openIdObj);
                return custOpenAccountService.webFindAccountTmpInfo(openId);
            }
            return AjaxObject.newError("获取开户信息失败").toJson();
        }
        catch (final Exception e) {
            return AjaxObject.newError("获取开户信息失败").toJson();
        }
    }
    
    /**
     * 开户资料附件保存
     */
    @RequestMapping(value = "/saveSingleFileLink", method = RequestMethod.POST)
    public @ResponseBody String saveSingleFileLink(Long id, final String fileTypeName, final String fileMediaId) {
        return exec(() -> custOpenAccountService.webSaveSingleFileLink(id, fileTypeName, fileMediaId), "开户资料附件保存", logger);
    }
    
    /**
     * 根据batchNo生成对应文件类型Map Json对象(微信使用)
     */
    @RequestMapping(value = "/findAccountFileByBatChNo", method = RequestMethod.POST)
    public @ResponseBody String findAccountFileByBatChNo(Long batchNo) {
        logger.info("附件查询,入参：" + batchNo );
        try {

            return custOpenAccountService.webFindAccountFileByBatChNo(batchNo);
        }
        catch (RpcException e) {
            logger.error(e.getMessage(), e);
            if (BytterException.isCauseBytterException(e)) {
                return AjaxObject.newError(e.getCause().getMessage()).toJson();
            }
            return AjaxObject.newError("附件查询失败").toJson();
        }
        catch (Exception e) {
            logger.error(e.getMessage(), e);
            return AjaxObject.newError("附件查询失败").toJson();
        }
    }
    
    @RequestMapping(value = "/queryCustInfoByPlatform", method = RequestMethod.POST)
    public @ResponseBody String queryCustInfoByPlatform(String flag, int pageNum, int pageSize) {
        logger.info("平台查询客户信息");
        
        return exec(() -> custOpenAccountService.webQueryCustInfoByPlatform(flag, pageNum, pageSize), "平台查询客户信息失败", logger);
    }
}
