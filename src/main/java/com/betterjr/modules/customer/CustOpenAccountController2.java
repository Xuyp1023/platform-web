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
@RequestMapping("/Platform/Account2")
public class CustOpenAccountController2 {
    
    @Reference(interfaceClass = ICustOpenAccountService2.class)
    private ICustOpenAccountService2 custOpenAccountService;
    @Reference(interfaceClass = ICustInsteadService2.class)
    private ICustInsteadService2 insteadService;

    
    private static final Logger logger = LoggerFactory.getLogger(CustOpenAccountController2.class);
    
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
    @RequestMapping(value = "/saveOpenAccountApply", method = RequestMethod.POST)
    public @ResponseBody String saveOpenAccountApply(HttpServletRequest request, Long operId, String fileList) {
        Map<String, Object> anMap = Servlets.getParametersStartingWith(request, "");
        logger.info("开户申请提交,入参: " + anMap.toString());
        return exec(() -> custOpenAccountService.webSaveOpenAccountApply(anMap, operId, fileList), "开户申请提交失败", logger);
    }
    
    /**
     * 开户信息修改
     */
    @RequestMapping(value = "/saveModifyOpenAccount", method = RequestMethod.POST)
    public @ResponseBody String saveModifyOpenAccount(HttpServletRequest request, Long id, String fileList) {
        Map<String, Object> anMap = Servlets.getParametersStartingWith(request, "");
        logger.info("开户信息修改,入参: " + anMap.toString());
        return exec(() -> custOpenAccountService.webSaveModifyOpenAccount(anMap, id, fileList), "开户信息修改失败", logger);
    }
    
    /**
     * 代录开户资料申请提交
     */
    @RequestMapping(value = "/saveAccInfoInstead", method = RequestMethod.POST)
    public @ResponseBody String saveOpenAccountInfoByInstead(HttpServletRequest request, Long insteadRecordId, String fileList) {
        Map<String, Object> anMap = Servlets.getParametersStartingWith(request, "");
        logger.info("代录开户资料提交,入参：" + anMap.toString());
        return exec(() -> custOpenAccountService.webSaveOpenAccountInfoByInstead(anMap, insteadRecordId, fileList), "代录开户资料提交失败", logger);
    }
    
    /**
     * 客户开户资料暂存
     */
    @RequestMapping(value = "/saveAccInfo", method = RequestMethod.POST)
    public @ResponseBody String saveOpenAccountInfo(HttpServletRequest request, Long id, String fileList) {
        Map<String, Object> anMap = Servlets.getParametersStartingWith(request, "");
        final Object openIdObj = Servlets.getSession().getAttribute("wechat_openId");
        if (openIdObj != null) {
            final String openId = String.valueOf(openIdObj);
            anMap.put("wechatOpenId", openId);
        }
        logger.info("客户开户资料暂存,入参：" + anMap.toString());
        return exec(() -> custOpenAccountService.webSaveOpenAccountInfo(anMap, id, fileList), "暂存失败", logger);
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
}
