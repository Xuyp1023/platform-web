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
 * 变更接口
 * @author liuwl
 *
 */
@Controller
@RequestMapping("/Platform/Change")
public class ChangeController {
    private static final Logger logger = LoggerFactory.getLogger(ChangeController.class);
    
    @Reference(interfaceClass = ICustChangeService.class)
    private ICustChangeService custChangeService;
    
    /**
     * 变更申请列表-查询
     * 
     * @return
     */
    @RequestMapping(value = "/queryChangeApplyList", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String queryChangeApplyList(HttpServletRequest request, int flag, int pageNum, int pageSize) {
        try {
            Map<String, Object> anParam = Servlets.getParametersStartingWith(request, "");
            return custChangeService.webQueryChangeApplyList(anParam, flag, pageNum, pageSize);
        }
        catch (final Exception e) {
            logger.error("变更申请列表-查询出错", e);
            return AjaxObject.newError("变更申请列表-查询出错").toJson();
        }
    }

    /**
     * 变更申请 - 审核通过
     * 
     * @return
     */
    @RequestMapping(value = "/auditPassChangeApply", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String auditPassChangeApply(HttpServletRequest request, Long id, String reason) {
        try {
            return custChangeService.webAuditPassChangeApply(id, reason);
        }
        catch (final Exception e) {
            logger.error("变更申请-审核通过出错", e);
            return AjaxObject.newError("变更申请-审核通过出错").toJson();
        }
    }
    
    /**
     * 变更申请 - 审核驳回
     * 
     * @return
     */
    @RequestMapping(value = "/auditRejectChangeApply", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String auditRejectChangeApply(HttpServletRequest request, Long id, String reason) {
        try {
            return custChangeService.webAuditRejectChangeApply(id, reason);
        }
        catch (final Exception e) {
            logger.error("变更申请-审核驳回出错", e);
            return AjaxObject.newError("变更申请-审核驳回出错").toJson();
        }
    }
}
