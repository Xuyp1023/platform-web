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
 * 代录接口
 * @author liuwl
 *
 */
@Controller
@RequestMapping("/Platform/Instead")
public class InsteadController {
    private static final Logger logger = LoggerFactory.getLogger(ChangeController.class);

    @Reference(interfaceClass = ICustInsteadService.class)
    private ICustInsteadService insteadService;

    /**
     * 代录申请-申请代录
     *
     * @return
     */
    @RequestMapping(value = "/addInsteadApply", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String addInsteadApply(final HttpServletRequest request, final String fileList) {
        final Map<String, Object> anParam = Servlets.getParametersStartingWith(request, "");
        return exec(() -> insteadService.webAddInsteadApply(anParam, fileList), "代录申请-添加代录出错", logger);
    }

    /**
     * PC代录申请-申请代录
     */
    @RequestMapping(value = "/addOpenAccountInsteadApply", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String addOpenAccountInsteadApply(final String custName, final Long operId,
            final String fileList) {
        return exec(() -> insteadService.webAddOpenAccountInsteadApply(custName, operId, fileList), "PC申请代录开户出错",
                logger);
    }

    /**
     * 代录申请-申请修改
     *
     * @return
     */
    @RequestMapping(value = "/saveInsteadApply", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String saveInsteadApply(final HttpServletRequest request, final Long id,
            final String fileList) {
        final Map<String, Object> anParam = Servlets.getParametersStartingWith(request, "");
        return exec(() -> insteadService.webSaveInsteadApply(anParam, id, fileList), "代录申请-修改代录出错", logger);
    }

    /**
     * 代录申请-详情
     *
     * @return
     */
    @RequestMapping(value = "/findInsteadApply", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String findInsteadApply(final HttpServletRequest request, final Long id) {
        logger.debug("代录申请-详情 入参:id=" + id);
        return exec(() -> insteadService.webFindInsteadApply(id), "代录申请-详情 出错", logger);
    }

    /**
     * 代录申请-查询代录申请列表[所有] （待审，已审，已代录未复核，已复核未确认，驳回)
     *
     * @return
     */
    @RequestMapping(value = "/queryInsteadApplyList", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String queryInsteadApplyList(final HttpServletRequest request, final int flag,
            final int pageNum, final int pageSize) {
        final Map<String, Object> anParam = Servlets.getParametersStartingWith(request, "");
        logger.debug("代录申请-查询代录申请列表[所有] 入参:anParam=" + anParam);
        return exec(() -> insteadService.webQueryInsteadApplyList(anParam, flag, pageNum, pageSize),
                "代录申请-查询代录申请列表[所有] 出错", logger);
    }

    /**
     * 代录申请-查询代录申请列表[自己机构拥有的]
     *
     * @return
     */
    @RequestMapping(value = "/queryInsteadApplyOwnList", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String queryInsteadApplyOwnList(final HttpServletRequest request, final int flag,
            final int pageNum, final int pageSize) {
        final Map<String, Object> anParam = Servlets.getParametersStartingWith(request, "");
        logger.debug("代录申请-查询代录申请列表[自己发起的申请] 入参:anParam=" + anParam);
        return exec(() -> insteadService.webQueryInsteadApplyOwnList(anParam, flag, pageNum, pageSize),
                "代录申请-查询代录申请列表[自己发起的申请] 出错", logger);
    }

    /**
     * 代录申请-查询代录申请列表[待审核]
     *
     * @return
     */
    @RequestMapping(value = "/queryInsteadApplyAuditList", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String queryInsteadApplyAuditList(final HttpServletRequest request, final int flag,
            final int pageNum, final int pageSize) {
        final Map<String, Object> anParam = Servlets.getParametersStartingWith(request, "");
        logger.debug("代录申请-查询代录申请列表[待审核] 入参:anParam=" + anParam);
        return exec(() -> insteadService.webQueryInsteadApplyAuditList(anParam, flag, pageNum, pageSize),
                "代录申请-查询代录申请列表[待审核] 出错", logger);
    }

    /**
     * 代录申请-查询代录申请列表[待复核]
     *
     * @return
     */
    @RequestMapping(value = "/queryInsteadApplyReviewList", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String queryInsteadApplyReviewList(final HttpServletRequest request, final int flag,
            final int pageNum, final int pageSize) {
        final Map<String, Object> anParam = Servlets.getParametersStartingWith(request, "");
        logger.debug("代录申请-查询代录申请列表[待复核] 入参:anParam=" + anParam);
        return exec(() -> insteadService.webQueryInsteadApplyReviewList(anParam, flag, pageNum, pageSize),
                "代录申请-查询代录申请列表[待复核] 出错", logger);
    }

    /**
     * 代录申请-查询代录申请列表[待确认]
     *
     * @return
     */
    @RequestMapping(value = "/queryInsteadApplyConfirmList", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String queryInsteadApplyConfirmList(final HttpServletRequest request, final int flag,
            final int pageNum, final int pageSize) {
        final Map<String, Object> anParam = Servlets.getParametersStartingWith(request, "");
        logger.debug("代录申请-查询代录申请列表[待确认] 入参:anParam=" + anParam);
        return exec(() -> insteadService.webQueryInsteadApplyConfirmList(anParam, flag, pageNum, pageSize),
                "代录申请-查询代录申请列表[待确认] 出错", logger);
    }

    /**
     * 代录记录-通过代录申请 查询代录记录列表
     *
     * @return
     */
    @RequestMapping(value = "/queryInsteadRecordByApply", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String queryInsteadRecordByApply(final Long id) {
        logger.debug("代录申请-查询代录记录列表 入参:id=" + id);
        return exec(() -> insteadService.webQueryInsteadRecordByApply(id), "代录申请-查询代录记录列表 出错", logger);
    }

    /**
     * 代录申请 审核通过
     * @return
     */
    @RequestMapping(value = "/auditPassInsteadApply", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String auditPassInsteadApply(final Long id, final String reason) {
        logger.debug("代录申请 审核通过 入参:id=" + id + " reason=" + reason);
        return exec(() -> insteadService.webAuditPassInsteadApply(id, reason), "代录申请 审核通过 出错", logger);
    }

    /**
     * 代录申请 审核驳回
     * @return
     */
    @RequestMapping(value = "/auditRejectInsteadApply", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String auditRejectInsteadApply(final Long id, final String reason) {
        logger.debug("代录申请 审核驳回 入参:id=" + id + " reason=" + reason);
        return exec(() -> insteadService.webAuditRejectInsteadApply(id, reason), "代录申请 审核驳回 出错", logger);
    }

    /**
     * 代录申请 复核通过
     * @return
     */
    @RequestMapping(value = "/reviewPassInsteadApply", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String reviewPassInsteadApply(final Long id, final String reason) {
        logger.debug("代录申请 复核通过 入参:id=" + id + " reason=" + reason);
        return exec(() -> insteadService.webReviewPassInsteadApply(id, reason), "代录申请 复核通过 出错", logger);
    }

    /**
     * 代录申请 复核驳回
     * @return
     */
    @RequestMapping(value = "/reviewRejectInsteadApply", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String reviewRejectInsteadApply(final Long id, final String reason) {
        logger.debug("代录申请 复核驳回 入参:id=" + id + " reason=" + reason);
        return exec(() -> insteadService.webReviewRejectInsteadApply(id, reason), "代录申请 复核驳回 出错", logger);
    }

    /**
     * 代录申请 确认通过
     * @return
     */
    @RequestMapping(value = "/confirmPassInsteadApply", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String confirmPassInsteadApply(final Long id, final String reason) {
        logger.debug("代录申请 确认通过 入参:id=" + id + " reason=" + reason);
        return exec(() -> insteadService.webConfirmPassInsteadApply(id, reason), "代录申请 确认通过 出错", logger);
    }

    /**
     * 代录申请 确认驳回
     * @return
     */
    @RequestMapping(value = "/confirmRejectInsteadApply", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String confirmRejectInsteadApply(final Long id, final String reason) {
        logger.debug("代录申请 确认驳回 入参:id=" + id + " reason=" + reason);
        return exec(() -> insteadService.webConfirmRejectInsteadApply(id, reason), "代录申请 确认驳回 出错", logger);
    }

    /**
     * 代录申请 复核提交
     * @return
     */
    @RequestMapping(value = "/submitReviewInsteadApply", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String submitReviewInsteadApply(final Long id) {
        logger.debug("代录申请 复核提交 入参:id=" + id);
        return exec(() -> insteadService.webSubmitReviewInsteadApply(id), "代录申请 代录复核提交 出错", logger);
    }

    /**
     * 代录申请 确认提交
     * @return
     */
    @RequestMapping(value = "/submitConfirmInsteadApply", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String submitConfirmInsteadApply(final Long id) {
        logger.debug("代录申请 确认提交 入参:id=" + id);
        return exec(() -> insteadService.webSubmitConfirmInsteadApply(id), "代录申请 代录确认提交 出错", logger);
    }

    /**
     * 代录申请 录入提交
     * @return
     */
    @RequestMapping(value = "/submitTypeInInsteadApply", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String submitTypeInInsteadApply(final Long id) {
        logger.debug("代录申请 录入提交 入参:id=" + id);
        return exec(() -> insteadService.webSubmitTypeInInsteadApply(id), "代录申请 录入提交 出错", logger);
    }

    /**
     * 代录申请 作废
     * @return
     */
    @RequestMapping(value = "/cancelInsteadApply", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String cancelInsteadApply(final Long id, final String reason) {
        logger.debug("代录申请 作废 入参:id=" + id + " reason=" + reason);
        return exec(() -> insteadService.webCancelInsteadApply(id, reason), "代录申请 作废 出错", logger);
    }

    /**
     * 代录项目 复核通过
     */
    @RequestMapping(value = "/reviewPassInsteadRecord", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String reviewPassInsteadRecord(final Long id, final String reason) {
        logger.debug("代录项目 复核通过 入参:id=" + id + " reason=" + reason);
        return exec(() -> insteadService.webReviewPassInsteadRecord(id, reason), "代录项目 复核通过 出错", logger);
    }

    /**
     * 代录项目 复核驳回
     * @return
     */
    @RequestMapping(value = "/reviewRejectInsteadRecord", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String reviewRejectInsteadRecord(final Long id, final String reason) {
        logger.debug("代录项目 复核驳回 入参:id=" + id + " reason=" + reason);
        return exec(() -> insteadService.webReviewRejectInsteadRecord(id, reason), "代录项目 复核驳回 出错", logger);
    }

    /**
     * 代录项目 确认通过
     * @return
     */
    @RequestMapping(value = "/confirmPassInsteadRecord", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String confirmPassInsteadRecord(final Long id, final String reason) {
        logger.debug("代录项目 确认通过 入参:id=" + id + " reason=" + reason);
        return exec(() -> insteadService.webConfirmPassInsteadRecord(id, reason), "代录项目 确认通过 出错", logger);
    }

    /**
     * 代录项目 确认驳回
     * @return
     */
    @RequestMapping(value = "/confirmRejectInsteadRecord", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String confirmRejectInsteadRecord(final Long id, final String reason) {
        logger.debug("代录项目 确认驳回 入参:id=" + id + " reason=" + reason);
        return exec(() -> insteadService.webConfirmRejectInsteadRecord(id, reason), "代录项目 确认驳回 出错", logger);
    }

    /**
     * 代录项目 作废
     * @return
     */
    @RequestMapping(value = "/cancelInsteadRecord", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String cancelInsteadRecord(final Long id, final String reason) {
        logger.debug("代录项目 作废 入参:id=" + id);
        return exec(() -> insteadService.webCancelInsteadRecord(id, reason), "代录项目 作废 出错", logger);
    }

    /**
     * 查询代录申请
     */
    @RequestMapping(value = "/findInsteadApplyByAccountTmpId", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String findInsteadApplyByAccountTmpId(final Long id) {
        return exec(() -> insteadService.webFindInsteadApplyByAccountTmpId(id), "查询代录申请出错", logger);
    }

    /**
     * 代录激活操作
     */
    @RequestMapping(value = "/saveActiveOpenAccount", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String saveActiveOpenAccount(final Long id) {
        return exec(() -> insteadService.webSaveActiveOpenAccount(id), "激活出错", logger);
    }

}
