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
import com.betterjr.common.web.AjaxObject;
import com.betterjr.common.web.Servlets;

@Controller
@RequestMapping("/Platform/Manager")
public class ManagerController {
private static final Logger logger = LoggerFactory.getLogger(ManagerController.class);
    
    @Reference(interfaceClass = ICustMechManagerService.class)
    private ICustMechManagerService custMechManagerService;
    
    @RequestMapping(value = "/queryManager", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String queryManager(HttpServletRequest request, Long custNo) {
        try {
            logger.debug("高管信息-高管列表 入参:custNo=" + custNo);
            return custMechManagerService.webQueryManager(custNo);
        }
        catch (final Exception e) {
            logger.error("高管信息-高管列表 查询错误", e);
            return AjaxObject.newError("高管信息-高管列表 查询错误").toJson();
        }
    }
    
    @RequestMapping(value = "/addInsteadManagerTmp", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String addInsteadManagerTmp(HttpServletRequest request) {
        try {
            final Map<String, Object> reqParam = Servlets.getParametersStartingWith(request, "");
            logger.debug("高管信息-临时流水 添加 入参:reqParam=" + reqParam);
            return custMechManagerService.webAddInsteadManagerTmp(reqParam);
        }
        catch (final Exception e) {
            logger.error("高管信息-临时流水 添加错误", e);
            return AjaxObject.newError("高管信息-临时流水 添加错误").toJson();
        }
    }
    
    @RequestMapping(value = "/saveInsteadManagerTmp", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String saveInsteadManagerTmp(HttpServletRequest request, Long id) {
        try {
            final Map<String, Object> reqParam = Servlets.getParametersStartingWith(request, "");
            logger.debug("高管信息-临时流水 修改 入参:reqParam=" + reqParam + " id=" + id);
            return custMechManagerService.webSaveInsteadManagerTmp(reqParam, id);
        }
        catch (final Exception e) {
            logger.error("高管信息-临时流水 修改 错误", e);
            return AjaxObject.newError("高管信息-临时流水 修改 错误").toJson();
        }
    }
    
    @RequestMapping(value = "/addChangeManagerTmp", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String addChangeManagerTmp(HttpServletRequest request) {
        try {
            final Map<String, Object> reqParam = Servlets.getParametersStartingWith(request, "");
            logger.debug("高管信息-临时流水 添加 入参:reqParam=" + reqParam);
            return custMechManagerService.webAddInsteadManagerTmp(reqParam);
        }
        catch (final Exception e) {
            logger.error("高管信息-临时流水 添加错误", e);
            return AjaxObject.newError("高管信息-临时流水 添加错误").toJson();
        }
    }
    
    @RequestMapping(value = "/saveChangeManagerTmp", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String saveChangeManagerTmp(HttpServletRequest request, Long id) {
        try {
            final Map<String, Object> reqParam = Servlets.getParametersStartingWith(request, "");
            logger.debug("高管信息-临时流水 修改 入参:reqParam=" + reqParam + " id=" + id);
            return custMechManagerService.webSaveInsteadManagerTmp(reqParam, id);
        }
        catch (final Exception e) {
            logger.error("高管信息-临时流水 修改 错误", e);
            return AjaxObject.newError("高管信息-临时流水 修改 错误").toJson();
        }
    }
    
    @RequestMapping(value = "/addChangeApply", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String addChangeApply(HttpServletRequest request, Long custNo) {
        try {
            final Map<String, Object> reqParam = Servlets.getParametersStartingWith(request, "");
            logger.debug("高管信息-变更申请  入参:reqParam=" + reqParam + " custNo=" + custNo);
            return custMechManagerService.webAddChangeApply(reqParam, custNo);
        }
        catch (final Exception e) {
            logger.error("高管信息-变更申请  错误", e);
            return AjaxObject.newError("高管信息-变更申请  错误").toJson();
        }
    }
}
