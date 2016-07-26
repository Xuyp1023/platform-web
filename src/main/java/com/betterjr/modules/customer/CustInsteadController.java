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
@RequestMapping("/Platform/CustInstead")
public class CustInsteadController {
    private static final Logger logger = LoggerFactory.getLogger(CustChangeController.class);
    
    @Reference(interfaceClass = ICustInsteadService.class)
    private ICustInsteadService custInsteadService;
    
    /**
     * 变更申请-申请变更
     * 
     * @return
     */
    @RequestMapping(value = "/addInsteadApply", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String addInsteadApply(HttpServletRequest request) {
        try {
            Map<String, Object> anParam = Servlets.getParametersStartingWith(request, "");
            return custInsteadService.webAddInsteadApply(anParam);
        }
        catch (final Exception e) {
            logger.error("变更申请-添加变更出错", e);
            return AjaxObject.newError("变更申请-添加变更出错").toJson();
        }
    }

    /**
     * 变更申请-查询变更申请列表[所有] （待审，已审，已代录未复核，已复核未确认，驳回)
     * 
     * @return
     */
    @RequestMapping(value = "/queryInsteadApplyList", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String queryInsteadApplyList(HttpServletRequest request) {
        try {
            Map<String, Object> anParam = Servlets.getParametersStartingWith(request, "");
            return custInsteadService.webAddInsteadApply(anParam);
        }
        catch (final Exception e) {
            logger.error("变更申请列表-查询出错", e);
            return AjaxObject.newError("变更申请列表-查询出错").toJson();
        }
    }
    
    /**
     * 变更申请-查询变更申请列表[待审核]
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
            logger.error("变更申请列表-查询出错", e);
            return AjaxObject.newError("变更申请列表-查询出错").toJson();
        }
    }
    
    /**
     * 变更申请-查询变更申请列表[待复核]
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
            logger.error("变更申请列表-查询出错", e);
            return AjaxObject.newError("变更申请列表-查询出错").toJson();
        }
    }
    
    /**
     * 变更申请-查询变更申请列表[待复核]
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
            logger.error("变更申请列表-查询出错", e);
            return AjaxObject.newError("变更申请列表-查询出错").toJson();
        }
    }
}
