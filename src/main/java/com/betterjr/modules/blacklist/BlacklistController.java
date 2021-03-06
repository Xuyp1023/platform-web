package com.betterjr.modules.blacklist;

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
@RequestMapping(value = "/Platform/Blacklist")
public class BlacklistController {

    private static final Logger logger = LoggerFactory.getLogger(BlacklistController.class);

    @Reference(interfaceClass = IBlacklistService.class)
    private IBlacklistService scfBlacklistService;

    @RequestMapping(value = "/queryBlacklist", method = RequestMethod.POST)
    public @ResponseBody String queryBlacklist(HttpServletRequest request, String flag, int pageNum, int pageSize) {
        Map<String, Object> anMap = Servlets.getParametersStartingWith(request, "");
        logger.info("黑名单信息查询,入参：" + anMap.toString());
        try {

            return scfBlacklistService.webQueryBlacklist(anMap, flag, pageNum, pageSize);
        }
        catch (RpcException e) {
            logger.error(e.getMessage(), e);
            if (BytterException.isCauseBytterException(e)) {
                return AjaxObject.newError(e.getCause().getMessage()).toJson();
            }
            return AjaxObject.newError("黑名单信息查询失败").toJson();
        }
        catch (Exception e) {
            logger.error(e.getMessage(), e);
            return AjaxObject.newError("黑名单信息查询失败").toJson();
        }
    }

    @RequestMapping(value = "/addBlacklist", method = RequestMethod.POST)
    public @ResponseBody String addBlacklist(HttpServletRequest request) {
        Map<String, Object> anMap = Servlets.getParametersStartingWith(request, "");
        logger.info("黑名单录入,入参: " + anMap.toString());
        try {

            return scfBlacklistService.webAddBlacklist(anMap);
        }
        catch (RpcException e) {
            logger.error(e.getMessage(), e);
            if (BytterException.isCauseBytterException(e)) {
                return AjaxObject.newError(e.getCause().getMessage()).toJson();
            }
            return AjaxObject.newError("黑名单录入失败").toJson();
        }
        catch (Exception e) {
            logger.error(e.getMessage(), e);
            return AjaxObject.newError("黑名单录入失败").toJson();
        }
    }

    @RequestMapping(value = "/modifyBlacklist", method = RequestMethod.POST)
    public @ResponseBody String modifyBlacklist(HttpServletRequest request, Long id) {
        Map<String, Object> anMap = Servlets.getParametersStartingWith(request, "");
        logger.info("黑名单修改,入参：" + anMap.toString());
        try {

            return scfBlacklistService.webSaveModifyBlacklist(anMap, id);
        }
        catch (RpcException e) {
            logger.error(e.getMessage(), e);
            if (BytterException.isCauseBytterException(e)) {
                return AjaxObject.newError(e.getCause().getMessage()).toJson();
            }
            return AjaxObject.newError("黑名单修改失败").toJson();
        }
        catch (Exception e) {
            logger.error(e.getMessage(), e);
            return AjaxObject.newError("黑名单修改失败").toJson();
        }
    }

    @RequestMapping(value = "/activateBlacklist", method = RequestMethod.POST)
    public @ResponseBody String activateBlacklist(Long id) {
        logger.info("黑名单激活,入参: " + id);

        try {

            return scfBlacklistService.webSaveActivateBlacklist(id);
        }
        catch (RpcException e) {
            logger.error(e.getMessage(), e);
            if (BytterException.isCauseBytterException(e)) {
                return AjaxObject.newError(e.getCause().getMessage()).toJson();
            }
            return AjaxObject.newError("黑名单激活失败").toJson();
        }
        catch (Exception e) {
            logger.error(e.getMessage(), e);
            return AjaxObject.newError("黑名单激活失败").toJson();
        }
    }

    @RequestMapping(value = "/cancelBlacklist", method = RequestMethod.POST)
    public @ResponseBody String cancelBlacklist(Long id) {
        logger.info("黑名单注销,入参: " + id);

        try {

            return scfBlacklistService.webSaveCancelBlacklist(id);
        }
        catch (RpcException e) {
            logger.error(e.getMessage(), e);
            if (BytterException.isCauseBytterException(e)) {
                return AjaxObject.newError(e.getCause().getMessage()).toJson();
            }
            return AjaxObject.newError("黑名单注销失败").toJson();
        }
        catch (Exception e) {
            logger.error(e.getMessage(), e);
            return AjaxObject.newError("黑名单注销失败").toJson();
        }
    }

    @RequestMapping(value = "/deleteBlacklist", method = RequestMethod.POST)
    public @ResponseBody String deleteBlacklist(Long id) {
        logger.info("黑名单删除,入参: " + id);

        try {

            scfBlacklistService.webSaveDeleteBlacklist(id);

            return AjaxObject.newOk("黑名单删除成功").toJson();
        }
        catch (RpcException e) {
            logger.error(e.getMessage(), e);
            if (BytterException.isCauseBytterException(e)) {
                return AjaxObject.newError(e.getCause().getMessage()).toJson();
            }
            return AjaxObject.newError("黑名单删除失败").toJson();
        }
        catch (Exception e) {
            logger.error(e.getMessage(), e);
            return AjaxObject.newError("黑名单删除失败").toJson();
        }
    }

    @RequestMapping(value = "/checkBlacklist", method = RequestMethod.POST)
    public @ResponseBody String checkBlacklistExists(String name, String identNo, String lawName) {
        logger.info("检查是否存在黑名单,入参: " + name + " and " + identNo + " and " + lawName);

        try {

            return scfBlacklistService.webCheckBlacklistExists(name, identNo, lawName);
        }
        catch (RpcException e) {
            logger.error(e.getMessage(), e);
            if (BytterException.isCauseBytterException(e)) {
                return AjaxObject.newError(e.getCause().getMessage()).toJson();
            }
            return AjaxObject.newError("检查是否存在黑名单出错").toJson();
        }
        catch (Exception e) {
            logger.error(e.getMessage(), e);
            return AjaxObject.newError("检查是否存在黑名单出错").toJson();
        }
    }

}
