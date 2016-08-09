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
    private ICustInsteadService custInsteadService;
    
    /**
     * 代录申请-申请代录
     * 
     * @return
     */
    @RequestMapping(value = "/addInsteadApply", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String addInsteadApply(HttpServletRequest request, String fileList) {
        try {
            Map<String, Object> anParam = Servlets.getParametersStartingWith(request, "");
            return custInsteadService.webAddInsteadApply(anParam, fileList);
        }
        catch (final Exception e) {
            logger.error("代录申请-添加代录出错", e);
            return AjaxObject.newError("代录申请-添加代录出错").toJson();
        }
    }
    
    /**
     * 代录申请-申请修改
     * 
     * @return
     */
    @RequestMapping(value = "/saveInsteadApply", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String saveInsteadApply(HttpServletRequest request, Long applyId, String fileList) {
        try {
            Map<String, Object> anParam = Servlets.getParametersStartingWith(request, "");
            return custInsteadService.webSaveInsteadApply(anParam, applyId, fileList);
        }
        catch (final Exception e) {
            logger.error("代录申请-添加代录出错", e);
            return AjaxObject.newError("代录申请-添加代录出错").toJson();
        }
    }
    
    /**
     * 代录申请-查询代录申请列表[所有] （待审，已审，已代录未复核，已复核未确认，驳回)
     * 
     * @return
     */
    @RequestMapping(value = "/queryInsteadApplyList", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String queryInsteadApplyList(HttpServletRequest request, int flag, int pageNum, int pageSize) {
        try {
            Map<String, Object> anParam = Servlets.getParametersStartingWith(request, "");
            return custInsteadService.webQueryInsteadApplyList(anParam, flag, pageNum, pageSize);
        }
        catch (final Exception e) {
            logger.error("代录申请-查询代录申请列表[所有] 出错", e);
            return AjaxObject.newError("代录申请-查询代录申请列表[所有] 出错").toJson();
        }
    }
    
    /**
     * 代录申请-查询代录申请列表[自己机构拥有的]
     * 
     * @return
     */
    @RequestMapping(value = "/queryInsteadApplyOwnList", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String queryInsteadApplyOwnList(HttpServletRequest request, int flag, int pageNum, int pageSize) {
        try {
            Map<String, Object> anParam = Servlets.getParametersStartingWith(request, "");
            return custInsteadService.webQueryInsteadApplyOwnList(anParam, flag, pageNum, pageSize);
        }
        catch (final Exception e) {
            logger.error("代录申请-查询代录申请列表[待审核] 出错", e);
            return AjaxObject.newError("代录申请-查询代录申请列表[待审核] 出错").toJson();
        }
    }
    
    /**
     * 代录申请-查询代录申请列表[待审核]
     * 
     * @return
     */
    @RequestMapping(value = "/queryInsteadApplyAuditList", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String queryInsteadApplyAuditList(HttpServletRequest request, int flag, int pageNum, int pageSize) {
        try {
            Map<String, Object> anParam = Servlets.getParametersStartingWith(request, "");
            return custInsteadService.webQueryInsteadApplyAuditList(anParam, flag, pageNum, pageSize);
        }
        catch (final Exception e) {
            logger.error("代录申请-查询代录申请列表[待审核] 出错", e);
            return AjaxObject.newError("代录申请-查询代录申请列表[待审核] 出错").toJson();
        }
    }
    
    /**
     * 代录申请-查询代录申请列表[待复核]
     * 
     * @return
     */
    @RequestMapping(value = "/queryInsteadApplyReviewList", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String queryInsteadApplyReviewList(HttpServletRequest request, int flag, int pageNum, int pageSize) {
        try {
            Map<String, Object> anParam = Servlets.getParametersStartingWith(request, "");
            return custInsteadService.webQueryInsteadApplyReviewList(anParam, flag, pageNum, pageSize);
        }
        catch (final Exception e) {
            logger.error("代录申请-查询代录申请列表[待复核] 出错", e);
            return AjaxObject.newError("代录申请-查询代录申请列表[待复核] 出错").toJson();
        }
    }
    
    /**
     * 代录申请-查询代录申请列表[待复核]
     * 
     * @return
     */
    @RequestMapping(value = "/queryInsteadApplyConfirmList", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String queryInsteadApplyConfirmList(HttpServletRequest request, int flag, int pageNum, int pageSize) {
        try {
            Map<String, Object> anParam = Servlets.getParametersStartingWith(request, "");
            return custInsteadService.webQueryInsteadApplyConfirmList(anParam, flag, pageNum, pageSize);
        }
        catch (final Exception e) {
            logger.error("代录申请-查询代录申请列表[待复核] 出错", e);
            return AjaxObject.newError("代录申请-查询代录申请列表[待复核] 出错").toJson();
        }
    }
    
    /**
     * 代录记录-通过代录申请 查询代录记录列表 
     * 
     * @return
     */
    @RequestMapping(value = "/queryInsteadRecordByApply", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String queryInsteadRecordByApply(Long id) {
        try {
            return custInsteadService.webQueryInsteadRecordByApply(id);
        }
        catch (final Exception e) {
            logger.error("代录申请-查询代录记录列表 出错", e);
            return AjaxObject.newError("代录申请-查询代录记录列表 出错").toJson();
        }
    }
    
    
    
    /**
     * 代录申请 审核通过
     * @return
     */
    @RequestMapping(value = "/auditPassInsteadApply", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String auditPassInsteadApply(Long id, String reason) {
        try {
            return custInsteadService.webAuditPassInsteadApply(id, reason);
        }
        catch (final Exception e) {
            logger.error("代录申请 审核通过 出错", e);
            return AjaxObject.newError("代录申请 审核通过 出错").toJson();
        }
    }
    
    /**
     * 代录申请 审核驳回
     * @return
     */
    @RequestMapping(value = "/auditRejectInsteadApply", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String auditRejectInsteadApply(Long id, String reason) {
        try {
            return custInsteadService.webAuditRejectInsteadApply(id, reason);
        }
        catch (final Exception e) {
            logger.error("代录申请 审核驳回 出错", e);
            return AjaxObject.newError("代录申请 审核驳回 出错").toJson();
        }
    }
    

    /**
     * 代录申请 复核通过
     * @return
     */
    @RequestMapping(value = "/reviewPassInsteadApply", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String reviewPassInsteadApply(Long id, String reason) {
        try {
            return custInsteadService.webReviewPassInsteadApply(id, reason);
        }
        catch (final Exception e) {
            logger.error("代录申请 复核通过 出错", e);
            return AjaxObject.newError("代录申请 复核通过 出错").toJson();
        }
    }
    
    /**
     * 代录申请 复核驳回
     * @return
     */
    @RequestMapping(value = "/reviewRejectInsteadApply", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String reviewRejectInsteadApply(Long id, String reason) {
        try {
            return custInsteadService.webReviewRejectInsteadApply(id, reason);
        }
        catch (final Exception e) {
            logger.error("代录申请 复核驳回 出错", e);
            return AjaxObject.newError("代录申请 复核驳回 出错").toJson();
        }
    }
    
    /**
     * 代录申请 确认通过
     * @return
     */
    @RequestMapping(value = "/confirmPassInsteadApply", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String confirmPassInsteadApply(Long id, String reason) {
        try {
            return custInsteadService.webConfirmPassInsteadApply(id, reason);
        }
        catch (final Exception e) {
            logger.error("代录申请 确认通过 出错", e);
            return AjaxObject.newError("代录申请 确认通过 出错").toJson();
        }
    }
    
    /**
     * 代录申请 确认驳回
     * @return
     */
    @RequestMapping(value = "/confirmRejectInsteadApply", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String confirmRejectInsteadApply(Long id, String reason) {
        try {
            return custInsteadService.webConfirmRejectInsteadApply(id, reason);
        }
        catch (final Exception e) {
            logger.error("代录申请 确认驳回 出错", e);
            return AjaxObject.newError("代录申请 确认驳回 出错").toJson();
        }
    }
    
    /**
     * 代录申请 复核提交
     * @return
     */
    @RequestMapping(value = "/submitReviewInsteadApply", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String submitReviewInsteadApply(Long id) {
        try {
            return custInsteadService.webSubmitReviewInsteadApply(id);
        }
        catch (final Exception e) {
            logger.error("代录申请 代录复核提交 出错", e);
            return AjaxObject.newError("代录申请 代录复核提交 出错").toJson();
        }
    }
    
    /**
     * 代录申请 确认提交
     * @return
     */
    @RequestMapping(value = "/submitConfirmInsteadApply", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String submitConfirmInsteadApply(Long id) {
        try {
            return custInsteadService.webSubmitConfirmInsteadApply(id);
        }
        catch (final Exception e) {
            logger.error("代录申请 代录确认提交 出错", e);
            return AjaxObject.newError("代录申请 代录确认提交 出错").toJson();
        }
    }
    
    /**
     * 代录申请 录入提交
     * @return
     */
    @RequestMapping(value = "/submitTypeInInsteadApply", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String submitTypeInInsteadApply(Long id) {
        try {
            return custInsteadService.webSubmitTypeInInsteadApply(id);
        }
        catch (final Exception e) {
            logger.error("代录申请 录入提交 出错", e);
            return AjaxObject.newError("代录申请 录入提交 出错").toJson();
        }
    }
    
    /**
     * 代录申请 作废
     * @return
     */
    @RequestMapping(value = "/cancelInsteadApply", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String cancelInsteadApply(Long id, String reason) {
        try {
            return custInsteadService.webCancelInsteadApply(id, reason);
        }
        catch (final Exception e) {
            logger.error("代录申请 作废 出错", e);
            return AjaxObject.newError("代录申请 作废 出错").toJson();
        }
    }
    
    /**
     * 代录项目 复核通过
     */
    @RequestMapping(value = "/reviewPassInsteadRecord", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String reviewPassInsteadRecord(Long id, String reason) {
        try {
            return custInsteadService.webReviewPassInsteadRecord(id, reason);
        }
        catch (final Exception e) {
            logger.error("代录项目 复核通过 出错", e);
            return AjaxObject.newError("代录项目 复核通过 出错").toJson();
        }
    }
    
    /**
     * 代录项目 复核驳回
     * @return
     */
    @RequestMapping(value = "/reviewRejectInsteadRecord", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String reviewRejectInsteadRecord(Long id, String reason) {
        try {
            return custInsteadService.webReviewRejectInsteadRecord(id, reason);
        }
        catch (final Exception e) {
            logger.error("代录项目 复核驳回 出错", e);
            return AjaxObject.newError("代录项目 复核驳回 出错").toJson();
        }
    }
    
    /**
     * 代录项目 确认通过
     * @return
     */
    @RequestMapping(value = "/confirmPassInsteadRecord", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String confirmPassInsteadRecord(Long id, String reason) {
        try {
            return custInsteadService.webConfirmPassInsteadRecord(id, reason);
        }
        catch (final Exception e) {
            logger.error("代录项目 确认通过 出错", e);
            return AjaxObject.newError("代录项目 确认通过 出错").toJson();
        }
    }
    
    /**
     * 代录项目 确认驳回
     * @return
     */
    @RequestMapping(value = "/confirmRejectInsteadRecord", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String confirmRejectInsteadRecord(Long id, String reason) {
        try {
            return custInsteadService.webConfirmRejectInsteadRecord(id, reason);
        }
        catch (final Exception e) {
            logger.error("代录项目 确认驳回 出错", e);
            return AjaxObject.newError("代录项目 确认驳回 出错").toJson();
        }
    }
    
    /**
     * 代录项目 作废
     * @return
     */
    @RequestMapping(value = "/cancelInsteadRecord", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String cancelInsteadRecord(Long id, String reason) {
        try {
            return custInsteadService.webCancelInsteadRecord(id, reason);
        }
        catch (final Exception e) {
            logger.error("代录项目 作废 出错", e);
            return AjaxObject.newError("代录项目 作废 出错").toJson();
        }
    }
    
}
