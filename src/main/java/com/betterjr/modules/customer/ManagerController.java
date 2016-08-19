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

/**
 * 
 * @author liuwl
 *
 */
@Controller
@RequestMapping("/Platform/Manager")
public class ManagerController {
    private static final Logger logger = LoggerFactory.getLogger(ManagerController.class);

    @Reference(interfaceClass = ICustMechManagerService.class)
    private ICustMechManagerService managerService;

    @RequestMapping(value = "/queryManager", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String queryManager(HttpServletRequest request, Long custNo) {
        logger.debug("高管信息-高管列表 入参:custNo=" + custNo);
        return exec(() -> managerService.webQueryManager(custNo), "高管信息-高管列表 查询错误", logger);
    }

    @RequestMapping(value = "/findManager", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String findManager(HttpServletRequest request, Long id) {
        logger.debug("高管信息-高管详情 入参:id=" + id);
        return exec(() -> managerService.webFindManager(id), "高管信息-高管详情 查询错误", logger);
    }

    @RequestMapping(value = "/findManagerTmp", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String findManagerTmp(HttpServletRequest request, Long id) {
        logger.debug("高管信息-高管流水详情 入参:id=" + id);
        return exec(() -> managerService.webFindManagerTmp(id), "高管信息-高管流水详情 查询错误", logger);
    }

    @RequestMapping(value = "/saveManagerTmp", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String saveManagerTmp(HttpServletRequest request, Long id, String fileList) {
        final Map<String, Object> reqParam = Servlets.getParametersStartingWith(request, "");
        logger.debug("高管信息-高管流水修改 入参:reqParam=" + reqParam + " id=" + id);
        return exec(() -> managerService.webSaveManagerTmp(reqParam, id, fileList), "高管信息-高管流水修改 错误", logger);
    }

    @RequestMapping(value = "/addChangeManagerTmp", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String addChangeManagerTmp(HttpServletRequest request, String fileList) {
        final Map<String, Object> reqParam = Servlets.getParametersStartingWith(request, "");
        logger.debug("高管信息-临时流水 添加 入参:reqParam=" + reqParam);
        return exec(() -> managerService.webAddChangeManagerTmp(reqParam, fileList), "高管信息-临时流水 添加错误", logger);
    }

    @RequestMapping(value = "/saveChangeManagerTmp", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String saveChangeManagerTmp(HttpServletRequest request, String fileList) {
        final Map<String, Object> reqParam = Servlets.getParametersStartingWith(request, "");
        logger.debug("高管信息-临时流水 修改 入参:reqParam=" + reqParam);
        return exec(() -> managerService.webSaveChangeManagerTmp(reqParam, fileList), "高管信息-临时流水 修改 错误", logger);
    }

    @RequestMapping(value = "/deleteChangeManagerTmp", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String deleteChangeManagerTmp(HttpServletRequest request, Long refId) {
        logger.debug("高管信息-临时流水 删除 入参:refId=" + refId);
        return exec(() -> managerService.webDeleteChangeManagerTmp(refId), "高管信息-临时流水 删除 错误", logger);
    }

    @RequestMapping(value = "/cancelChangeManagerTmp", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String cancelChangeManagerTmp(HttpServletRequest request, Long id) {
        logger.debug("高管信息-临时流水 作废 入参:id=" + id);
        return exec(() -> managerService.webCancelChangeManagerTmp(id), "高管信息-临时流水 作废 错误", logger);
    }

    @RequestMapping(value = "/queryNewChangeManagerTmp", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String queryNewChangeManagerTmp(HttpServletRequest request, Long custNo) {
        logger.debug("高管信息-临时流水 未使用流水列表 入参:custNo=" + custNo);
        return exec(() -> managerService.webQueryNewChangeManagerTmp(custNo), "高管信息-临时流水 未使用流水列表 查询错误", logger);
    }

    @RequestMapping(value = "/queryChangeManagerTmp", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String queryChangeManagerTmp(HttpServletRequest request, Long applyId) {
        logger.debug("高管信息-临时流水 变更流水列表 入参:applyId=" + applyId);
        return exec(() -> managerService.webQueryChangeManagerTmp(applyId), "高管信息-临时流水 变更流水列表 查询错误", logger);
    }

    @RequestMapping(value = "/addChangeApply", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String addChangeApply(HttpServletRequest request, Long custNo) {
        final Map<String, Object> reqParam = Servlets.getParametersStartingWith(request, "");
        logger.debug("高管信息-变更申请  入参:reqParam=" + reqParam + " custNo=" + custNo);
        return exec(() -> managerService.webAddChangeApply(reqParam, custNo), "高管信息-变更申请  错误", logger);
    }

    @RequestMapping(value = "/saveChangeApply", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String saveChangeApply(HttpServletRequest request, Long custNo) {
        final Map<String, Object> reqParam = Servlets.getParametersStartingWith(request, "");
        logger.debug("高管信息-变更申请修改  入参:reqParam=" + reqParam + " custNo=" + custNo);
        return exec(() -> managerService.webAddChangeApply(reqParam, custNo), "高管信息-变更申请修改  错误", logger);
    }

    @RequestMapping(value = "/queryChangeApply", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String queryChangeApply(HttpServletRequest request, Long custNo, int flag, int pageNum, int pageSize) {
        logger.debug("高管信息-变更列表  入参:custNo=" + custNo);
        return exec(() -> managerService.webQueryChangeApply(custNo, flag, pageNum, pageSize), "高管信息-变更列表查询  错误", logger);
    }

    @RequestMapping(value = "/findChangeApply", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String findChangeApply(HttpServletRequest request, Long applyId, Long tmpId) {
        logger.debug("高管信息-变更申请  入参: applyId=" + applyId + " tmpId=" + tmpId);
        return exec(() -> managerService.webFindChangeApply(applyId, tmpId), "高管信息-变更申请  错误", logger);
    }

    @RequestMapping(value = "/addInsteadManagerTmp", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String addInsteadManagerTmp(HttpServletRequest request, Long insteadRecordId, String fileList) {
        final Map<String, Object> reqParam = Servlets.getParametersStartingWith(request, "");
        logger.debug("高管信息-添加代录新增流水  入参:reqParam=" + reqParam + " insteadRecordId=" + insteadRecordId);
        return exec(() -> managerService.webAddInsteadManagerTmp(reqParam, insteadRecordId, fileList), "高管信息-添加代录新增流水  错误", logger);
    }

    @RequestMapping(value = "/saveInsteadManagerTmp", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String saveInsteadManagerTmp(HttpServletRequest request, Long insteadRecordId, String fileList) {
        final Map<String, Object> reqParam = Servlets.getParametersStartingWith(request, "");
        logger.debug("高管信息-添加代录修改流水  入参:reqParam=" + reqParam + " insteadRecordId=" + insteadRecordId);
        return exec(() -> managerService.webSaveInsteadManagerTmp(reqParam, insteadRecordId, fileList), "高管信息-添加代录新增流水  错误", logger);
    }

    @RequestMapping(value = "/deleteInsteadManagerTmp", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String deleteInsteadManagerTmp(HttpServletRequest request, Long refId, Long insteadRecordId) {
        logger.debug("高管信息-添加代录删除流水  入参:refId=" + refId + " insteadRecordId=" + insteadRecordId);
        return exec(() -> managerService.webDeleteInsteadManagerTmp(refId, insteadRecordId), "高管信息-添加代录删除流水  错误", logger);
    }

    @RequestMapping(value = "/cancelInsteadManagerTmp", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String cancelInsteadManagerTmp(HttpServletRequest request, Long insteadRecordId, Long id) {
        logger.debug("高管信息-删除代录流水  入参: id=" + id);
        return exec(() -> managerService.webCancelInsteadManagerTmp(id, insteadRecordId), "高管信息-删除代录流水  错误", logger);
    }

    @RequestMapping(value = "/queryInsteadManagerTmp", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String queryInsteadManagerTmp(HttpServletRequest request, Long insteadRecordId) {
        logger.debug("高管信息-查询代录流水列表  入参:insteadRecordId=" + insteadRecordId);
        return exec(() -> managerService.webQueryInsteadManagerTmp(insteadRecordId), "高管信息-查询代录流水列表  错误", logger);
    }

    @RequestMapping(value = "/addInsteadRecord", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String addInsteadRecord(HttpServletRequest request, Long insteadRecordId) {
        final Map<String, Object> reqParam = Servlets.getParametersStartingWith(request, "");
        logger.debug("高管信息-添加代录记录  入参:reqParam=" + reqParam + " insteadRecordId=" + insteadRecordId);
        return exec(() -> managerService.webAddInsteadRecord(reqParam, insteadRecordId), "高管信息-添加代录记录  错误", logger);
    }

    @RequestMapping(value = "/saveInsteadRecord", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String saveInsteadRecord(HttpServletRequest request, Long insteadRecordId) {
        final Map<String, Object> reqParam = Servlets.getParametersStartingWith(request, "");
        logger.debug("高管信息-修改代录记录  入参:reqParam=" + reqParam + " insteadRecordId=" + insteadRecordId);
        return exec(() -> managerService.webSaveInsteadRecord(reqParam, insteadRecordId), "高管信息-修改代录记录  错误", logger);
    }

    @RequestMapping(value = "/findInsteadRecord", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String findInsteadRecord(HttpServletRequest request, Long insteadRecordId) {
        logger.debug("高管信息-代录记录详情  入参: insteadRecordId=" + insteadRecordId);
        return exec(() -> managerService.webFindInsteadRecord(insteadRecordId), "高管信息-代录记录详情  错误", logger);
    }
}
