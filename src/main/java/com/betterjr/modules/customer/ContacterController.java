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
@RequestMapping("/Platform/Contacter")
public class ContacterController {
    private static final Logger logger = LoggerFactory.getLogger(ContacterController.class);

    @Reference(interfaceClass = ICustMechContacterService.class)
    private ICustMechContacterService contacterService;

    @RequestMapping(value = "/queryContacter", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String queryContacter(HttpServletRequest request, Long custNo) {
        logger.debug("联系人信息-联系人列表 入参:custNo=" + custNo);
        return exec(() -> contacterService.webQueryContacter(custNo), "联系人信息-联系人列表 查询错误", logger);
    }

    @RequestMapping(value = "/findContacter", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String findContacter(HttpServletRequest request, Long id) {
        logger.debug("联系人信息-联系人详情 入参:id=" + id);
        return exec(() -> contacterService.webFindContacter(id), "联系人信息-联系人详情 查询错误", logger);
    }

    @RequestMapping(value = "/findContacterTmp", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String findContacterTmp(HttpServletRequest request, Long id) {
        logger.debug("联系人信息-联系人流水详情 入参:id=" + id);
        return exec(() -> contacterService.webFindContacterTmp(id), "联系人信息-联系人流水详情 查询错误", logger);
    }

    @RequestMapping(value = "/saveContacterTmp", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String saveContacterTmp(HttpServletRequest request, Long id, String fileList) {
        final Map<String, Object> reqParam = Servlets.getParametersStartingWith(request, "");
        logger.debug("联系人信息-联系人流水修改 入参:reqParam=" + reqParam + " id=" + id);
        return exec(() -> contacterService.webSaveContacterTmp(reqParam, id, fileList), "联系人信息-联系人流水修改 错误", logger);
    }

    @RequestMapping(value = "/addChangeContacterTmp", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String addChangeContacterTmp(HttpServletRequest request, String fileList) {
        final Map<String, Object> reqParam = Servlets.getParametersStartingWith(request, "");
        logger.debug("联系人信息-临时流水 添加 入参:reqParam=" + reqParam);
        return exec(() -> contacterService.webAddChangeContacterTmp(reqParam, fileList), "联系人信息-临时流水 添加错误", logger);
    }

    @RequestMapping(value = "/saveChangeContacterTmp", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String saveChangeContacterTmp(HttpServletRequest request, String fileList) {
        final Map<String, Object> reqParam = Servlets.getParametersStartingWith(request, "");
        logger.debug("联系人信息-临时流水 修改 入参:reqParam=" + reqParam);
        return exec(() -> contacterService.webSaveChangeContacterTmp(reqParam, fileList), "联系人信息-临时流水 修改 错误", logger);
    }

    @RequestMapping(value = "/deleteChangeContacterTmp", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String deleteChangeContacterTmp(HttpServletRequest request, Long refId) {
        logger.debug("联系人信息-临时流水 删除 入参:refId=" + refId);
        return exec(() -> contacterService.webDeleteChangeContacterTmp(refId), "联系人信息-临时流水 删除 错误", logger);
    }

    @RequestMapping(value = "/cancelChangeContacterTmp", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String cancelChangeContacterTmp(HttpServletRequest request, Long id) {
        logger.debug("联系人信息-临时流水 作废 入参:id=" + id);
        return exec(() -> contacterService.webCancelChangeContacterTmp(id), "联系人信息-临时流水 作废 错误", logger);
    }

    @RequestMapping(value = "/queryNewChangeContacterTmp", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String queryNewChangeContacterTmp(HttpServletRequest request, Long custNo) {
        logger.debug("联系人信息-临时流水 未使用流水列表 入参:custNo=" + custNo);
        return exec(() -> contacterService.webQueryNewChangeContacterTmp(custNo), "联系人信息-临时流水 未使用流水列表 查询错误", logger);
    }

    @RequestMapping(value = "/queryChangeContacterTmp", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String queryChangeContacterTmp(HttpServletRequest request, Long applyId) {
        logger.debug("联系人信息-临时流水 变更流水列表 入参:applyId=" + applyId);
        return exec(() -> contacterService.webQueryChangeContacterTmp(applyId), "联系人信息-临时流水 变更流水列表 查询错误", logger);
    }

    @RequestMapping(value = "/addChangeApply", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String addChangeApply(HttpServletRequest request, Long custNo) {
        final Map<String, Object> reqParam = Servlets.getParametersStartingWith(request, "");
        logger.debug("联系人信息-变更申请  入参:reqParam=" + reqParam + " custNo=" + custNo);
        return exec(() -> contacterService.webAddChangeApply(reqParam, custNo), "联系人信息-变更申请  错误", logger);
    }

    @RequestMapping(value = "/saveChangeApply", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String saveChangeApply(HttpServletRequest request, Long custNo) {
        final Map<String, Object> reqParam = Servlets.getParametersStartingWith(request, "");
        logger.debug("联系人信息-变更申请修改  入参:reqParam=" + reqParam + " custNo=" + custNo);
        return exec(() -> contacterService.webAddChangeApply(reqParam, custNo), "联系人信息-变更申请修改  错误", logger);
    }

    @RequestMapping(value = "/queryChangeApply", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String queryChangeApply(HttpServletRequest request, Long custNo, int flag, int pageNum, int pageSize) {
        logger.debug("联系人信息-变更列表  入参:custNo=" + custNo);
        return exec(() -> contacterService.webQueryChangeApply(custNo, flag, pageNum, pageSize), "联系人信息-变更列表查询  错误", logger);
    }

    @RequestMapping(value = "/findChangeApply", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String findChangeApply(HttpServletRequest request, Long applyId, Long tmpId) {
        logger.debug("联系人信息-变更申请  入参: applyId=" + applyId + " tmpId=" + tmpId);
        return exec(() -> contacterService.webFindChangeApply(applyId, tmpId), "联系人信息-变更申请  错误", logger);
    }

    @RequestMapping(value = "/addInsteadContacterTmp", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String addInsteadContacterTmp(HttpServletRequest request, Long insteadRecordId, String fileList) {
        final Map<String, Object> reqParam = Servlets.getParametersStartingWith(request, "");
        logger.debug("联系人信息-添加代录新增流水  入参:reqParam=" + reqParam + " insteadRecordId=" + insteadRecordId);
        return exec(() -> contacterService.webAddInsteadContacterTmp(reqParam, insteadRecordId, fileList), "联系人信息-添加代录新增流水  错误", logger);
    }

    @RequestMapping(value = "/saveInsteadContacterTmp", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String saveInsteadContacterTmp(HttpServletRequest request, Long insteadRecordId, String fileList) {
        final Map<String, Object> reqParam = Servlets.getParametersStartingWith(request, "");
        logger.debug("联系人信息-添加代录修改流水  入参:reqParam=" + reqParam + " insteadRecordId=" + insteadRecordId);
        return exec(() -> contacterService.webSaveInsteadContacterTmp(reqParam, insteadRecordId, fileList), "联系人信息-添加代录新增流水  错误", logger);
    }

    @RequestMapping(value = "/deleteInsteadContacterTmp", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String deleteInsteadContacterTmp(HttpServletRequest request, Long refId, Long insteadRecordId) {
        logger.debug("联系人信息-添加代录删除流水  入参:refId=" + refId + " insteadRecordId=" + insteadRecordId);
        return exec(() -> contacterService.webDeleteInsteadContacterTmp(refId, insteadRecordId), "联系人信息-添加代录删除流水  错误", logger);
    }

    @RequestMapping(value = "/cancelInsteadContacterTmp", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String cancelInsteadContacterTmp(HttpServletRequest request, Long insteadRecordId, Long id) {
        logger.debug("联系人信息-删除代录流水  入参: id=" + id);
        return exec(() -> contacterService.webCancelInsteadContacterTmp(id, insteadRecordId), "联系人信息-删除代录流水  错误", logger);
    }

    @RequestMapping(value = "/queryInsteadContacterTmp", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String queryInsteadContacterTmp(HttpServletRequest request, Long insteadRecordId) {
        logger.debug("联系人信息-查询代录流水列表  入参:insteadRecordId=" + insteadRecordId);
        return exec(() -> contacterService.webQueryInsteadContacterTmp(insteadRecordId), "联系人信息-查询代录流水列表  错误", logger);
    }

    @RequestMapping(value = "/addInsteadRecord", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String addInsteadRecord(HttpServletRequest request, Long insteadRecordId) {
        final Map<String, Object> reqParam = Servlets.getParametersStartingWith(request, "");
        logger.debug("联系人信息-添加代录记录  入参:reqParam=" + reqParam + " insteadRecordId=" + insteadRecordId);
        return exec(() -> contacterService.webAddInsteadRecord(reqParam, insteadRecordId), "联系人信息-添加代录记录  错误", logger);
    }

    @RequestMapping(value = "/saveInsteadRecord", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String saveInsteadRecord(HttpServletRequest request, Long insteadRecordId) {
        final Map<String, Object> reqParam = Servlets.getParametersStartingWith(request, "");
        logger.debug("联系人信息-修改代录记录  入参:reqParam=" + reqParam + " insteadRecordId=" + insteadRecordId);
        return exec(() -> contacterService.webSaveInsteadRecord(reqParam, insteadRecordId), "联系人信息-修改代录记录  错误", logger);
    }

    @RequestMapping(value = "/findInsteadRecord", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String findInsteadRecord(HttpServletRequest request, Long insteadRecordId) {
        logger.debug("联系人信息-代录记录详情  入参: insteadRecordId=" + insteadRecordId);
        return exec(() -> contacterService.webFindInsteadRecord(insteadRecordId), "联系人信息-代录记录详情  错误", logger);
    }
}
