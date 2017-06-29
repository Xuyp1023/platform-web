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
import com.betterjr.mapper.pagehelper.Page;

/**
 * 
 * @author liuwl
 *
 */
@Controller
@RequestMapping("/Platform/BankAccount")
public class BankAccountController {
    private static final Logger logger = LoggerFactory.getLogger(BankAccountController.class);

    @Reference(interfaceClass = ICustMechBankAccountService.class)
    private ICustMechBankAccountService bankAccountService;

    @RequestMapping(value = "/queryBankAccount", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String queryBankAccount(HttpServletRequest request, Long custNo) {
        logger.debug("银行帐户信息-银行帐户列表 入参:custNo=" + custNo);
        return exec(() -> bankAccountService.webQueryBankAccount(custNo), "银行帐户信息-银行帐户列表 查询错误", logger);
    }

    @RequestMapping(value = "/findBankAccount", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String findBankAccount(HttpServletRequest request, Long id) {
        logger.debug("银行帐户信息-银行帐户详情 入参:id=" + id);
        return exec(() -> bankAccountService.webFindBankAccount(id), "银行帐户信息-银行帐户详情 查询错误", logger);
    }

    @RequestMapping(value = "/findBankAccountTmp", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String findBankAccountTmp(HttpServletRequest request, Long id) {
        logger.debug("银行帐户信息-银行帐户流水详情 入参:id=" + id);
        return exec(() -> bankAccountService.webFindBankAccountTmp(id), "银行帐户信息-银行帐户流水详情 查询错误", logger);
    }

    @RequestMapping(value = "/saveBankAccountTmp", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String saveBankAccountTmp(HttpServletRequest request, Long id, String fileList) {
        final Map<String, Object> reqParam = Servlets.getParametersStartingWith(request, "");
        logger.debug("银行帐户信息-银行帐户流水修改 入参:reqParam=" + reqParam + " id=" + id);
        return exec(() -> bankAccountService.webSaveBankAccountTmp(reqParam, id, fileList), "银行帐户信息-银行帐户流水修改 错误", logger);
    }
    
    @RequestMapping(value = "/queryBankAccountKeyAndValue", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String queryBankAccountKeyAndValue(HttpServletRequest request, Long custNo) {
    	logger.debug("银行帐户信息-银行帐户列表 入参:custNo=" + custNo);
    	return exec(() -> bankAccountService.webQueryBankAccountKeyAndValue(custNo), "银行帐户信息-银行帐户列表 查询错误", logger);
    }
    
    @RequestMapping(value = "/addChangeBankAccountTmp", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String addChangeBankAccountTmp(HttpServletRequest request, String fileList) {
        final Map<String, Object> reqParam = Servlets.getParametersStartingWith(request, "");
        logger.debug("银行帐户信息-临时流水 添加 入参:reqParam=" + reqParam);
        return exec(() -> bankAccountService.webAddChangeBankAccountTmp(reqParam, fileList), "银行帐户信息-临时流水 添加错误", logger);
    }

    @RequestMapping(value = "/saveChangeBankAccountTmp", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String saveChangeBankAccountTmp(HttpServletRequest request, String fileList) {
        final Map<String, Object> reqParam = Servlets.getParametersStartingWith(request, "");
        logger.debug("银行帐户信息-临时流水 修改 入参:reqParam=" + reqParam);
        return exec(() -> bankAccountService.webSaveChangeBankAccountTmp(reqParam, fileList), "银行帐户信息-临时流水 修改 错误", logger);
    }

    @RequestMapping(value = "/deleteChangeBankAccountTmp", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String deleteChangeBankAccountTmp(HttpServletRequest request, Long refId) {
        logger.debug("银行帐户信息-临时流水 删除 入参:refId=" + refId);
        return exec(() -> bankAccountService.webDeleteChangeBankAccountTmp(refId), "银行帐户信息-临时流水 删除 错误", logger);
    }

    @RequestMapping(value = "/cancelChangeBankAccountTmp", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String cancelChangeBankAccountTmp(HttpServletRequest request, Long id) {
        logger.debug("银行帐户信息-临时流水 作废 入参:id=" + id);
        return exec(() -> bankAccountService.webCancelChangeBankAccountTmp(id), "银行帐户信息-临时流水 作废 错误", logger);
    }

    @RequestMapping(value = "/queryNewChangeBankAccountTmp", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String queryNewChangeBankAccountTmp(HttpServletRequest request, Long custNo) {
        logger.debug("银行帐户信息-临时流水 未使用流水列表 入参:custNo=" + custNo);
        return exec(() -> bankAccountService.webQueryNewChangeBankAccountTmp(custNo), "银行帐户信息-临时流水 未使用流水列表 查询错误", logger);
    }

    @RequestMapping(value = "/queryChangeBankAccountTmp", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String queryChangeBankAccountTmp(HttpServletRequest request, Long applyId) {
        logger.debug("银行帐户信息-临时流水 变更流水列表 入参:applyId=" + applyId);
        return exec(() -> bankAccountService.webQueryChangeBankAccountTmp(applyId), "银行帐户信息-临时流水 变更流水列表 查询错误", logger);
    }

    @RequestMapping(value = "/addChangeApply", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String addChangeApply(HttpServletRequest request, Long custNo) {
        final Map<String, Object> reqParam = Servlets.getParametersStartingWith(request, "");
        logger.debug("银行帐户信息-变更申请  入参:reqParam=" + reqParam + " custNo=" + custNo);
        return exec(() -> bankAccountService.webAddChangeApply(reqParam, custNo), "银行帐户信息-变更申请  错误", logger);
    }

    @RequestMapping(value = "/saveChangeApply", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String saveChangeApply(HttpServletRequest request, Long custNo) {
        final Map<String, Object> reqParam = Servlets.getParametersStartingWith(request, "");
        logger.debug("银行帐户信息-变更申请修改  入参:reqParam=" + reqParam + " custNo=" + custNo);
        return exec(() -> bankAccountService.webAddChangeApply(reqParam, custNo), "银行帐户信息-变更申请修改  错误", logger);
    }

    @RequestMapping(value = "/queryChangeApply", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String queryChangeApply(HttpServletRequest request, Long custNo, int flag, int pageNum, int pageSize) {
        logger.debug("银行帐户信息-变更列表  入参:custNo=" + custNo);
        return exec(() -> bankAccountService.webQueryChangeApply(custNo, flag, pageNum, pageSize), "银行帐户信息-变更列表查询  错误", logger);
    }

    @RequestMapping(value = "/findChangeApply", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String findChangeApply(HttpServletRequest request, Long applyId, Long tmpId) {
        logger.debug("银行帐户信息-变更申请  入参: applyId=" + applyId + " tmpId=" + tmpId);
        return exec(() -> bankAccountService.webFindChangeApply(applyId, tmpId), "银行帐户信息-变更申请  错误", logger);
    }

    @RequestMapping(value = "/addInsteadBankAccountTmp", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String addInsteadBankAccountTmp(HttpServletRequest request, Long insteadRecordId, String fileList) {
        final Map<String, Object> reqParam = Servlets.getParametersStartingWith(request, "");
        logger.debug("银行帐户信息-添加代录新增流水  入参:reqParam=" + reqParam + " insteadRecordId=" + insteadRecordId);
        return exec(() -> bankAccountService.webAddInsteadBankAccountTmp(reqParam, insteadRecordId, fileList), "银行帐户信息-添加代录新增流水  错误", logger);
    }

    @RequestMapping(value = "/saveInsteadBankAccountTmp", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String saveInsteadBankAccountTmp(HttpServletRequest request, Long insteadRecordId, String fileList) {
        final Map<String, Object> reqParam = Servlets.getParametersStartingWith(request, "");
        logger.debug("银行帐户信息-添加代录修改流水  入参:reqParam=" + reqParam + " insteadRecordId=" + insteadRecordId);
        return exec(() -> bankAccountService.webSaveInsteadBankAccountTmp(reqParam, insteadRecordId, fileList), "银行帐户信息-添加代录新增流水  错误", logger);
    }

    @RequestMapping(value = "/deleteInsteadBankAccountTmp", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String deleteInsteadBankAccountTmp(HttpServletRequest request, Long refId, Long insteadRecordId) {
        logger.debug("银行帐户信息-添加代录删除流水  入参:refId=" + refId + " insteadRecordId=" + insteadRecordId);
        return exec(() -> bankAccountService.webDeleteInsteadBankAccountTmp(refId, insteadRecordId), "银行帐户信息-添加代录删除流水  错误", logger);
    }

    @RequestMapping(value = "/cancelInsteadBankAccountTmp", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String cancelInsteadBankAccountTmp(HttpServletRequest request, Long insteadRecordId, Long id) {
        logger.debug("银行帐户信息-删除代录流水  入参: id=" + id);
        return exec(() -> bankAccountService.webCancelInsteadBankAccountTmp(id, insteadRecordId), "银行帐户信息-删除代录流水  错误", logger);
    }

    @RequestMapping(value = "/queryInsteadBankAccountTmp", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String queryInsteadBankAccountTmp(HttpServletRequest request, Long insteadRecordId) {
        logger.debug("银行帐户信息-查询代录流水列表  入参:insteadRecordId=" + insteadRecordId);
        return exec(() -> bankAccountService.webQueryInsteadBankAccountTmp(insteadRecordId), "银行帐户信息-查询代录流水列表  错误", logger);
    }

    @RequestMapping(value = "/addInsteadRecord", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String addInsteadRecord(HttpServletRequest request, Long insteadRecordId) {
        final Map<String, Object> reqParam = Servlets.getParametersStartingWith(request, "");
        logger.debug("银行帐户信息-添加代录记录  入参:reqParam=" + reqParam + " insteadRecordId=" + insteadRecordId);
        return exec(() -> bankAccountService.webAddInsteadRecord(reqParam, insteadRecordId), "银行帐户信息-添加代录记录  错误", logger);
    }

    @RequestMapping(value = "/saveInsteadRecord", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String saveInsteadRecord(HttpServletRequest request, Long insteadRecordId) {
        final Map<String, Object> reqParam = Servlets.getParametersStartingWith(request, "");
        logger.debug("银行帐户信息-修改代录记录  入参:reqParam=" + reqParam + " insteadRecordId=" + insteadRecordId);
        return exec(() -> bankAccountService.webSaveInsteadRecord(reqParam, insteadRecordId), "银行帐户信息-修改代录记录  错误", logger);
    }

    @RequestMapping(value = "/findInsteadRecord", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String findInsteadRecord(HttpServletRequest request, Long insteadRecordId) {
    	logger.debug("银行帐户信息-代录记录详情  入参: insteadRecordId=" + insteadRecordId);
    	return exec(() -> bankAccountService.webFindInsteadRecord(insteadRecordId), "银行帐户信息-代录记录详情  错误", logger);
    }
    
    @RequestMapping(value = "/findCustMechBankAccount", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String findCustMechBankAccount(HttpServletRequest request, String bankAcco) {
        logger.debug("银行帐户信息-代录记录详情  入参: bankAcco=" + bankAcco);
        return exec(() -> bankAccountService.webFindCustMechBankAccount(bankAcco), "银行帐户信息-代录记录详情  错误", logger);
    }
    
    @RequestMapping(value = "/findBankCode", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String findBankCode(HttpServletRequest request) {
        Map anMap = Servlets.getParametersStartingWith(request, "");
        return exec(() -> bankAccountService.webFindSysBankCodeList(anMap), "查询联行号", logger);
    }
}
