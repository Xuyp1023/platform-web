package com.betterjr.modules.customer;

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
            if (e.getCause() != null && e.getCause() instanceof BytterException) {
                return AjaxObject.newError(e.getCause().getMessage()).toJson();
            }
            logger.error("客户开户资料读取失败", e);
            return AjaxObject.newError("客户开户资料读取失败").toJson();
        }
        catch (Exception e) {
            logger.error("客户开户资料读取失败", e);
            return AjaxObject.newError("客户开户资料读取失败").toJson();
        }
    }

    @RequestMapping(value = "/saveAccInfo", method = RequestMethod.POST)
    public @ResponseBody String saveOpenAccountInfo(HttpServletRequest request) {
        Map<String, Object> anMap = Servlets.getParametersStartingWith(request, "");
        logger.info("客户开户资料暂存,入参：" + anMap.toString());
        try {

            return custOpenAccountService.webSaveOpenAccountInfo(anMap);
        }
        catch (RpcException e) {
            if (e.getCause() != null && e.getCause() instanceof BytterException) {
                return AjaxObject.newError(e.getCause().getMessage()).toJson();
            }
            logger.error("客户开户资料暂存失败", e);
            return AjaxObject.newError("客户开户资料暂存失败").toJson();
        }
        catch (Exception e) {
            logger.error("客户开户资料暂存失败", e);
            return AjaxObject.newError("客户开户资料暂存失败").toJson();
        }
    }

    @RequestMapping(value = "/saveAccApply", method = RequestMethod.POST)
    public @ResponseBody String saveOpenAccountApply(HttpServletRequest request, Long id) {
        Map<String, Object> anMap = Servlets.getParametersStartingWith(request, "");
        logger.info("开户申请,入参：" + anMap.toString());
        try {

            return custOpenAccountService.webSaveOpenAccountApply(anMap, id);
        }
        catch (RpcException e) {
            if (e.getCause() != null && e.getCause() instanceof BytterException) {
                return AjaxObject.newError(e.getCause().getMessage()).toJson();
            }
            logger.error("开户申请提交失败", e);
            return AjaxObject.newError("开户申请提交失败").toJson();
        }
        catch (Exception e) {
            logger.error("开户申请提交失败", e);
            return AjaxObject.newError("开户申请提交失败").toJson();
        }
    }

    @RequestMapping(value = "/queryAccApply", method = RequestMethod.POST)
    public @ResponseBody String queryOpenAccountApply(String flag, int pageNum, int pageSize) {
        try {

            return custOpenAccountService.webQueryOpenAccountApply(flag, pageNum, pageSize);
        }
        catch (RpcException e) {
            if (e.getCause() != null && e.getCause() instanceof BytterException) {
                return AjaxObject.newError(e.getCause().getMessage()).toJson();
            }
            logger.error("开户申请待审批列表查询失败", e);
            return AjaxObject.newError("开户申请待审批列表查询失败").toJson();
        }
        catch (Exception e) {
            logger.error("开户申请待审批列表查询失败", e);
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
            if (e.getCause() != null && e.getCause() instanceof BytterException) {
                return AjaxObject.newError(e.getCause().getMessage()).toJson();
            }
            logger.error("开户审核失败", e);
            return AjaxObject.newError("开户审核失败").toJson();
        }
        catch (Exception e) {
            logger.error("开户审核失败", e);
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
            if (e.getCause() != null && e.getCause() instanceof BytterException) {
                return AjaxObject.newError(e.getCause().getMessage()).toJson();
            }
            logger.error("开户申请驳回失败", e);
            return AjaxObject.newError("开户申请驳回失败").toJson();
        }
        catch (Exception e) {
            logger.error("开户申请驳回失败", e);
            return AjaxObject.newError("开户申请驳回失败").toJson();
        }
    }

    @RequestMapping(value = "/saveAccInfoInstead", method = RequestMethod.POST)
    public @ResponseBody String saveOpenAccountInfoByInstead(HttpServletRequest request, Long insteadId) {
        Map<String, Object> anMap = Servlets.getParametersStartingWith(request, "");
        logger.info("代录开户资料提交,入参：" + anMap.toString());
        try {

            return custOpenAccountService.webSaveOpenAccountInfoByInstead(anMap, insteadId);
        }
        catch (RpcException e) {
            if (e.getCause() != null && e.getCause() instanceof BytterException) {
                return AjaxObject.newError(e.getCause().getMessage()).toJson();
            }
            logger.error("代录开户资料提交失败", e);
            return AjaxObject.newError("代录开户资料提交失败").toJson();
        }
        catch (Exception e) {
            logger.error("代录开户资料提交失败", e);
            return AjaxObject.newError("代录开户资料提交失败").toJson();
        }
    }

    @RequestMapping(value = "/findAccInfoInstead", method = RequestMethod.POST)
    public @ResponseBody String findOpenAccountInfoByInsteadId(Long insteadId) {
        logger.info("代录开户资料读取,入参：" + insteadId);
        try {

            return custOpenAccountService.webFindOpenAccountInfoByInsteadId(insteadId);
        }
        catch (RpcException e) {
            if (e.getCause() != null && e.getCause() instanceof BytterException) {
                return AjaxObject.newError(e.getCause().getMessage()).toJson();
            }
            logger.error("代录开户资料读取失败", e);
            return AjaxObject.newError("代录开户资料读取失败").toJson();
        }
        catch (Exception e) {
            logger.error("代录开户资料读取失败", e);
            return AjaxObject.newError("代录开户资料读取失败").toJson();
        }
    }

}
