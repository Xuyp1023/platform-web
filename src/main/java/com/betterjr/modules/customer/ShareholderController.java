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
@RequestMapping("/Platform/Shareholder")
public class ShareholderController {
    private static final Logger logger = LoggerFactory.getLogger(ShareholderController.class);

    @Reference(interfaceClass = ICustMechShareholderService.class)
    private ICustMechShareholderService shareholderService;

    @RequestMapping(value = "/queryShareholder", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String queryShareholder(HttpServletRequest request, Long custNo) {
        logger.debug("股东信息-股东列表 入参:custNo=" + custNo);
        return exec(() -> shareholderService.webQueryShareholder(custNo), "股东信息-股东列表 查询错误", logger);
    }

    @RequestMapping(value = "/findShareholder", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String findShareholder(HttpServletRequest request, Long id) {
        logger.debug("股东信息-股东详情 入参:id=" + id);
        return exec(() -> shareholderService.webFindShareholder(id), "股东信息-股东详情 查询错误", logger);
    }

    @RequestMapping(value = "/findShareholderTmp", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String findShareholderTmp(HttpServletRequest request, Long id) {
        logger.debug("股东信息-股东流水详情 入参:id=" + id);
        return exec(() -> shareholderService.webFindShareholderTmp(id), "股东信息-股东流水详情 查询错误", logger);
    }

    @RequestMapping(value = "/saveShareholderTmp", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String saveShareholderTmp(HttpServletRequest request, Long id, String fileList) {
        final Map<String, Object> reqParam = Servlets.getParametersStartingWith(request, "");
        logger.debug("股东信息-股东流水修改 入参:reqParam=" + reqParam + " id=" + id);
        return exec(() -> shareholderService.webSaveShareholderTmp(reqParam, id, fileList), "股东信息-股东流水修改 错误", logger);
    }

    @RequestMapping(value = "/addChangeShareholderTmp", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String addChangeShareholderTmp(HttpServletRequest request, String fileList) {
        final Map<String, Object> reqParam = Servlets.getParametersStartingWith(request, "");
        logger.debug("股东信息-临时流水 添加 入参:reqParam=" + reqParam);
        return exec(() -> shareholderService.webAddChangeShareholderTmp(reqParam, fileList), "股东信息-临时流水 添加错误", logger);
    }

    @RequestMapping(value = "/saveChangeShareholderTmp", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String saveChangeShareholderTmp(HttpServletRequest request, String fileList) {
        final Map<String, Object> reqParam = Servlets.getParametersStartingWith(request, "");
        logger.debug("股东信息-临时流水 修改 入参:reqParam=" + reqParam);
        return exec(() -> shareholderService.webSaveChangeShareholderTmp(reqParam, fileList), "股东信息-临时流水 修改 错误",
                logger);
    }

    @RequestMapping(value = "/deleteChangeShareholderTmp", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String deleteChangeShareholderTmp(HttpServletRequest request, Long refId) {
        logger.debug("股东信息-临时流水 删除 入参:refId=" + refId);
        return exec(() -> shareholderService.webDeleteChangeShareholderTmp(refId), "股东信息-临时流水 删除 错误", logger);
    }

    @RequestMapping(value = "/cancelChangeShareholderTmp", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String cancelChangeShareholderTmp(HttpServletRequest request, Long id) {
        logger.debug("股东信息-临时流水 作废 入参:id=" + id);
        return exec(() -> shareholderService.webCancelChangeShareholderTmp(id), "股东信息-临时流水 作废 错误", logger);
    }

    @RequestMapping(value = "/queryNewChangeShareholderTmp", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String queryNewChangeShareholderTmp(HttpServletRequest request, Long custNo) {
        logger.debug("股东信息-临时流水 未使用流水列表 入参:custNo=" + custNo);
        return exec(() -> shareholderService.webQueryNewChangeShareholderTmp(custNo), "股东信息-临时流水 未使用流水列表 查询错误", logger);
    }

    @RequestMapping(value = "/queryChangeShareholderTmp", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String queryChangeShareholderTmp(HttpServletRequest request, Long applyId) {
        logger.debug("股东信息-临时流水 变更流水列表 入参:applyId=" + applyId);
        return exec(() -> shareholderService.webQueryChangeShareholderTmp(applyId), "股东信息-临时流水 变更流水列表 查询错误", logger);
    }

    @RequestMapping(value = "/addChangeApply", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String addChangeApply(HttpServletRequest request, Long custNo) {
        final Map<String, Object> reqParam = Servlets.getParametersStartingWith(request, "");
        logger.debug("股东信息-变更申请  入参:reqParam=" + reqParam + " custNo=" + custNo);
        return exec(() -> shareholderService.webAddChangeApply(reqParam, custNo), "股东信息-变更申请  错误", logger);
    }

    @RequestMapping(value = "/saveChangeApply", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String saveChangeApply(HttpServletRequest request, Long custNo) {
        final Map<String, Object> reqParam = Servlets.getParametersStartingWith(request, "");
        logger.debug("股东信息-变更申请修改  入参:reqParam=" + reqParam + " custNo=" + custNo);
        return exec(() -> shareholderService.webAddChangeApply(reqParam, custNo), "股东信息-变更申请修改  错误", logger);
    }

    @RequestMapping(value = "/queryChangeApply", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String queryChangeApply(HttpServletRequest request, Long custNo, int flag, int pageNum,
            int pageSize) {
        logger.debug("股东信息-变更列表  入参:custNo=" + custNo);
        return exec(() -> shareholderService.webQueryChangeApply(custNo, flag, pageNum, pageSize), "股东信息-变更列表查询  错误",
                logger);
    }

    @RequestMapping(value = "/findChangeApply", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String findChangeApply(HttpServletRequest request, Long applyId, Long tmpId) {
        logger.debug("股东信息-变更申请  入参: applyId=" + applyId + " tmpId=" + tmpId);
        return exec(() -> shareholderService.webFindChangeApply(applyId, tmpId), "股东信息-变更申请  错误", logger);
    }

    @RequestMapping(value = "/addInsteadShareholderTmp", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String addInsteadShareholderTmp(HttpServletRequest request, Long insteadRecordId,
            String fileList) {
        final Map<String, Object> reqParam = Servlets.getParametersStartingWith(request, "");
        logger.debug("股东信息-添加代录新增流水  入参:reqParam=" + reqParam + " insteadRecordId=" + insteadRecordId);
        return exec(() -> shareholderService.webAddInsteadShareholderTmp(reqParam, insteadRecordId, fileList),
                "股东信息-添加代录新增流水  错误", logger);
    }

    @RequestMapping(value = "/saveInsteadShareholderTmp", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String saveInsteadShareholderTmp(HttpServletRequest request, Long insteadRecordId,
            String fileList) {
        final Map<String, Object> reqParam = Servlets.getParametersStartingWith(request, "");
        logger.debug("股东信息-添加代录修改流水  入参:reqParam=" + reqParam + " insteadRecordId=" + insteadRecordId);
        return exec(() -> shareholderService.webSaveInsteadShareholderTmp(reqParam, insteadRecordId, fileList),
                "股东信息-添加代录新增流水  错误", logger);
    }

    @RequestMapping(value = "/deleteInsteadShareholderTmp", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String deleteInsteadShareholderTmp(HttpServletRequest request, Long refId,
            Long insteadRecordId) {
        logger.debug("股东信息-添加代录删除流水  入参:refId=" + refId + " insteadRecordId=" + insteadRecordId);
        return exec(() -> shareholderService.webDeleteInsteadShareholderTmp(refId, insteadRecordId),
                "股东信息-添加代录删除流水  错误", logger);
    }

    @RequestMapping(value = "/cancelInsteadShareholderTmp", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String cancelInsteadShareholderTmp(HttpServletRequest request, Long insteadRecordId, Long id) {
        logger.debug("股东信息-删除代录流水  入参: id=" + id);
        return exec(() -> shareholderService.webCancelInsteadShareholderTmp(id, insteadRecordId), "股东信息-删除代录流水  错误",
                logger);
    }

    @RequestMapping(value = "/queryInsteadShareholderTmp", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String queryInsteadShareholderTmp(HttpServletRequest request, Long insteadRecordId) {
        logger.debug("股东信息-查询代录流水列表  入参:insteadRecordId=" + insteadRecordId);
        return exec(() -> shareholderService.webQueryInsteadShareholderTmp(insteadRecordId), "股东信息-查询代录流水列表  错误",
                logger);
    }

    @RequestMapping(value = "/addInsteadRecord", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String addInsteadRecord(HttpServletRequest request, Long insteadRecordId) {
        final Map<String, Object> reqParam = Servlets.getParametersStartingWith(request, "");
        logger.debug("股东信息-添加代录记录  入参:reqParam=" + reqParam + " insteadRecordId=" + insteadRecordId);
        return exec(() -> shareholderService.webAddInsteadRecord(reqParam, insteadRecordId), "股东信息-添加代录记录  错误", logger);
    }

    @RequestMapping(value = "/saveInsteadRecord", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String saveInsteadRecord(HttpServletRequest request, Long insteadRecordId) {
        final Map<String, Object> reqParam = Servlets.getParametersStartingWith(request, "");
        logger.debug("股东信息-修改代录记录  入参:reqParam=" + reqParam + " insteadRecordId=" + insteadRecordId);
        return exec(() -> shareholderService.webSaveInsteadRecord(reqParam, insteadRecordId), "股东信息-修改代录记录  错误",
                logger);
    }

    @RequestMapping(value = "/findInsteadRecord", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String findInsteadRecord(HttpServletRequest request, Long insteadRecordId) {
        logger.debug("股东信息-代录记录详情  入参: insteadRecordId=" + insteadRecordId);
        return exec(() -> shareholderService.webFindInsteadRecord(insteadRecordId), "股东信息-代录记录详情  错误", logger);
    }
}
