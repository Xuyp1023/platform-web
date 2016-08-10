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
    
    @RequestMapping(value = "/findManagerTmp", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String findManagerTmp(HttpServletRequest request, Long id) {
        try {
            logger.debug("高管信息-高管流水详情 入参:id=" + id);
            return custMechManagerService.webFindManagerTmp(id);
        }
        catch (final Exception e) {
            logger.error("高管信息-高管流水详情 查询错误", e);
            return AjaxObject.newError("高管信息-高管流水详情 查询错误").toJson();
        }
    }
    
    @RequestMapping(value = "/saveManagerTmp", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String saveManagerTmp(HttpServletRequest request, Long id) {
        try {
            final Map<String, Object> reqParam = Servlets.getParametersStartingWith(request, "");
            logger.debug("高管信息-高管流水修改 入参:reqParam=" + reqParam + " id=" + id);
            return custMechManagerService.webSaveManagerTmp(reqParam, id);
        }
        catch (final Exception e) {
            logger.error("高管信息-高管流水修改 错误", e);
            return AjaxObject.newError("高管信息-高管流水修改 错误").toJson();
        }
    }
    
    @RequestMapping(value = "/addChangeManagerTmp", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String addChangeManagerTmp(HttpServletRequest request) {
        try {
            final Map<String, Object> reqParam = Servlets.getParametersStartingWith(request, "");
            logger.debug("高管信息-临时流水 添加 入参:reqParam=" + reqParam);
            return custMechManagerService.webAddChangeManagerTmp(reqParam);
        }
        catch (final Exception e) {
            logger.error("高管信息-临时流水 添加错误", e);
            return AjaxObject.newError("高管信息-临时流水 添加错误").toJson();
        }
    }
    
    @RequestMapping(value = "/saveChangeManagerTmp", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String saveChangeManagerTmp(HttpServletRequest request) {
        try {
            final Map<String, Object> reqParam = Servlets.getParametersStartingWith(request, "");
            logger.debug("高管信息-临时流水 修改 入参:reqParam=" + reqParam);
            return custMechManagerService.webSaveChangeManagerTmp(reqParam);
        }
        catch (final Exception e) {
            logger.error("高管信息-临时流水 修改 错误", e);
            return AjaxObject.newError("高管信息-临时流水 修改 错误").toJson();
        }
    }
    
    @RequestMapping(value = "/deleteChangeManagerTmp", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String deleteChangeManagerTmp(HttpServletRequest request, Long refId) {
        try {
            logger.debug("高管信息-临时流水 修改 入参:refId=" + refId);
            return custMechManagerService.webDelChangeManagerTmp(refId);
        }
        catch (final Exception e) {
            logger.error("高管信息-临时流水 修改 错误", e);
            return AjaxObject.newError("高管信息-临时流水 修改 错误").toJson();
        }
    }
    
    @RequestMapping(value = "/cancelChangeManagerTmp", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String cancelChangeManagerTmp(HttpServletRequest request, Long id) {
        try {
            logger.debug("高管信息-临时流水 作废 入参:id=" + id);
            return custMechManagerService.webCancelChangeManagerTmp(id);
        }
        catch (final Exception e) {
            logger.error("高管信息-临时流水 作废 错误", e);
            return AjaxObject.newError("高管信息-临时流水 作废 错误").toJson();
        }
    }
    
    @RequestMapping(value = "/queryNewChangeManagerTmp", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String queryNewChangeManagerTmp(HttpServletRequest request, Long custNo) {
        try {
            logger.debug("高管信息-临时流水 未使用流水列表 入参:custNo=" + custNo);
            return custMechManagerService.webQueryNewChangeManagerTmp(custNo);
        }
        catch (final Exception e) {
            logger.error("高管信息-临时流水 未使用流水列表 错误", e);
            return AjaxObject.newError("高管信息-临时流水 未使用流水列表 错误").toJson();
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
    
    @RequestMapping(value = "/saveChangeApply", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String saveChangeApply(HttpServletRequest request, Long custNo) {
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
    
    @RequestMapping(value = "/queryChangeApply", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String queryChangeApply(HttpServletRequest request, Long custNo, int flag, int pageNum, int pageSize) {
        try {
            logger.debug("高管信息-变更申请  入参:custNo=" + custNo);
            return custMechManagerService.webQueryChangeApply(custNo, flag, pageNum, pageSize);
        }
        catch (final Exception e) {
            logger.error("高管信息-变更申请  错误", e);
            return AjaxObject.newError("高管信息-变更申请  错误").toJson();
        }
    }
    
    @RequestMapping(value = "/findChangeApply", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String findChangeApply(HttpServletRequest request, Long custNo) {
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
    
    @RequestMapping(value = "/addInsteadManagerTmp", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String addInsteadManagerTmp(HttpServletRequest request, Long custNo) {
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
    
    @RequestMapping(value = "/saveInsteadManagerTmp", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String saveInsteadManagerTmp(HttpServletRequest request, Long custNo) {
        try {
            final Map<String, Object> reqParam = Servlets.getParametersStartingWith(request, "");
            logger.debug("高管信息-变更申请  入参:reqParam=" + reqParam + " custNo=" + custNo);
            return custMechManagerService.webSaveInsteadManagerTmp(reqParam, custNo);
        }
        catch (final Exception e) {
            logger.error("高管信息-变更申请  错误", e);
            return AjaxObject.newError("高管信息-变更申请  错误").toJson();
        }
    }
    
    @RequestMapping(value = "/delInsteadManagerTmp", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String delInsteadManagerTmp(HttpServletRequest request, Long custNo) {
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
    
    @RequestMapping(value = "/cancelInsteadManagerTmp", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String cancelInsteadManagerTmp(HttpServletRequest request, Long custNo) {
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
    
    @RequestMapping(value = "/queryNewInsteadManagerTmp", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String queryNewInsteadManagerTmp(HttpServletRequest request, Long custNo) {
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
    
    @RequestMapping(value = "/addInsteadRecord", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String addInsteadRecord(HttpServletRequest request, Long custNo) {
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
    
    @RequestMapping(value = "/saveInsteadRecord", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String saveInsteadRecord(HttpServletRequest request, Long custNo) {
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
    
    @RequestMapping(value = "/findInsteadRecord", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String findInsteadRecord(HttpServletRequest request, Long custNo) {
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
