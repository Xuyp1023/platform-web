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
    
    @RequestMapping(value = "/findManager", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String findManager(HttpServletRequest request, Long id) {
        try {
            logger.debug("高管信息-高管列表 入参:id=" + id);
            return custMechManagerService.webFindManager(id);
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
    
    @RequestMapping(value = "/queryChangeManagerTmp", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String queryChangeManagerTmp(HttpServletRequest request, Long applyId) {
        try {
            logger.debug("高管信息-临时流水 未使用流水列表 入参:applyId=" + applyId);
            return custMechManagerService.webQueryChangeManagerTmp(applyId);
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
            logger.debug("高管信息-变更申请修改  入参:reqParam=" + reqParam + " custNo=" + custNo);
            return custMechManagerService.webAddChangeApply(reqParam, custNo);
        }
        catch (final Exception e) {
            logger.error("高管信息-变更申请修改  错误", e);
            return AjaxObject.newError("高管信息-变更申请修改  错误").toJson();
        }
    }
    
    @RequestMapping(value = "/queryChangeApply", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String queryChangeApply(HttpServletRequest request, Long custNo, int flag, int pageNum, int pageSize) {
        try {
            logger.debug("高管信息-变更列表  入参:custNo=" + custNo);
            return custMechManagerService.webQueryChangeApply(custNo, flag, pageNum, pageSize);
        }
        catch (final Exception e) {
            logger.error("高管信息-变更列表查询  错误", e);
            return AjaxObject.newError("高管信息-变更列表查询  错误").toJson();
        }
    }
    
    @RequestMapping(value = "/findChangeApply", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String findChangeApply(HttpServletRequest request, Long applyId) {
        try {
            logger.debug("高管信息-变更申请  入参: applyId=" + applyId);
            return custMechManagerService.webFindChangeApply(applyId);
        }
        catch (final Exception e) {
            logger.error("高管信息-变更申请  错误", e);
            return AjaxObject.newError("高管信息-变更申请  错误").toJson();
        }
    }
    
    @RequestMapping(value = "/addInsteadManagerTmp", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String addInsteadManagerTmp(HttpServletRequest request, Long insteadRecordId) {
        try {
            final Map<String, Object> reqParam = Servlets.getParametersStartingWith(request, "");
            logger.debug("高管信息-添加代录新增流水  入参:reqParam=" + reqParam + " insteadRecordId=" + insteadRecordId);
            return custMechManagerService.webAddInsteadManagerTmp(reqParam, insteadRecordId);
        }
        catch (final Exception e) {
            logger.error("高管信息-添加代录新增流水  错误", e);
            return AjaxObject.newError("高管信息-添加代录新增流水  错误").toJson();
        }
    }
    
    @RequestMapping(value = "/saveInsteadManagerTmp", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String saveInsteadManagerTmp(HttpServletRequest request, Long insteadRecordId) {
        try {
            final Map<String, Object> reqParam = Servlets.getParametersStartingWith(request, "");
            logger.debug("高管信息-添加代录修改流水  入参:reqParam=" + reqParam + " insteadRecordId=" + insteadRecordId);
            return custMechManagerService.webSaveInsteadManagerTmp(reqParam, insteadRecordId);
        }
        catch (final Exception e) {
            logger.error("高管信息-添加代录修改流水  错误", e);
            return AjaxObject.newError("高管信息-添加代录修改流水  错误").toJson();
        }
    }
    
    @RequestMapping(value = "/delInsteadManagerTmp", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String delInsteadManagerTmp(HttpServletRequest request, Long refId, Long insteadRecordId) {
        try {
            logger.debug("高管信息-添加代录删除流水  入参:refId=" + refId + " insteadRecordId=" + insteadRecordId);
            return custMechManagerService.webDelInsteadManagerTmp(refId, insteadRecordId);
        }
        catch (final Exception e) {
            logger.error("高管信息-添加代录删除流水  错误", e);
            return AjaxObject.newError("高管信息-添加代录删除流水  错误").toJson();
        }
    }
    
    @RequestMapping(value = "/cancelInsteadManagerTmp", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String cancelInsteadManagerTmp(HttpServletRequest request, Long insteadRecordId, Long id) {
        try {
            logger.debug("高管信息-删除代录流水  入参: id=" + id);
            return custMechManagerService.webCancelInsteadManagerTmp(id, insteadRecordId);
        }
        catch (final Exception e) {
            logger.error("高管信息-删除代录流水  错误", e);
            return AjaxObject.newError("高管信息-删除代录流水  错误").toJson();
        }
    }
    
    @RequestMapping(value = "/queryInsteadManagerTmp", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String queryInsteadManagerTmp(HttpServletRequest request, Long insteadRecordId) {
        try {
            logger.debug("高管信息-查询代录流水列表  入参:insteadRecordId=" + insteadRecordId);
            return custMechManagerService.webQueryInsteadManagerTmp(insteadRecordId);
        }
        catch (final Exception e) {
            logger.error("高管信息-查询代录流水列表  错误", e);
            return AjaxObject.newError("高管信息-查询代录流水列表  错误").toJson();
        }
    }
    
    @RequestMapping(value = "/addInsteadRecord", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String addInsteadRecord(HttpServletRequest request, Long insteadRecordId) {
        try {
            final Map<String, Object> reqParam = Servlets.getParametersStartingWith(request, "");
            logger.debug("高管信息-添加代录记录  入参:reqParam=" + reqParam + " insteadRecordId=" + insteadRecordId);
            return custMechManagerService.webAddInsteadRecord(reqParam, insteadRecordId);
        }
        catch (final Exception e) {
            logger.error("高管信息-添加代录记录  错误", e);
            return AjaxObject.newError("高管信息-添加代录记录  错误").toJson();
        }
    }
    
    @RequestMapping(value = "/saveInsteadRecord", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String saveInsteadRecord(HttpServletRequest request, Long insteadRecordId) {
        try {
            final Map<String, Object> reqParam = Servlets.getParametersStartingWith(request, "");
            logger.debug("高管信息-修改代录记录  入参:reqParam=" + reqParam + " insteadRecordId=" + insteadRecordId);
            return custMechManagerService.webSaveInsteadRecord(reqParam, insteadRecordId);
        }
        catch (final Exception e) {
            logger.error("高管信息-修改代录记录  错误", e);
            return AjaxObject.newError("高管信息-修改代录记录  错误").toJson();
        }
    }
    
    @RequestMapping(value = "/findInsteadRecord", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String findInsteadRecord(HttpServletRequest request, Long insteadRecordId) {
        try {
            logger.debug("高管信息-代录记录详情  入参: insteadRecordId=" + insteadRecordId);
            return custMechManagerService.webFindInsteadRecord(insteadRecordId);
        }
        catch (final Exception e) {
            logger.error("高管信息-代录记录详情  错误", e);
            return AjaxObject.newError("高管信息-代录记录详情  错误").toJson();
        }
    }
}
