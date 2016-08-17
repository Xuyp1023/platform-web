package com.betterjr.modules.customer;

import static com.betterjr.common.web.ControllerExceptionHandler.exec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;

/**
 * 
 * @author liuwl
 *
 */
@Controller
@RequestMapping("/Platform/AuditLog")
public class AuditLogController {
    private static final Logger logger = LoggerFactory.getLogger(AuditLogController.class);

    @Reference(interfaceClass = ICustAuditLogService.class)
    private ICustAuditLogService auditLogService;

    /**
     * 审核日志列表 开户审核
     * 
     * @param businId
     * @param auditType
     * @param flag
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "queryAuditLogOpenAccountList", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String queryAuditLogOpenAccountList(Long businId) {
        logger.debug("入参:businId=" + String.valueOf(businId));
        return exec(() -> auditLogService.webQueryAuditLogOpenAccountList(businId), "审核日志-列表查询 错误", logger);
    }

    /**
     * 审核日志列表 代录申请审核
     * 
     * @param businId
     * @param auditType
     * @param flag
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "queryAuditLogInsteadApplyList", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String queryAuditLogInsteadApplyList(Long businId) {
        logger.debug("入参:businId=" + String.valueOf(businId));
        return exec(() -> auditLogService.webQueryAuditLogInsteadApplyList(businId), "审核日志-列表查询 错误", logger);
    }

    /**
     * 审核日志列表 代录记录审核
     * 
     * @param businId
     * @param auditType
     * @param flag
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "queryAuditLogInsteadRecordList", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String queryAuditLogInsteadRecordList(Long businId) {
        logger.debug("入参:businId=" + String.valueOf(businId));
        return exec(() -> auditLogService.webQueryAuditLogInsteadRecordList(businId), "审核日志-列表查询 错误", logger);
    }

    /**
     * 审核日志列表 变更申请审核
     * 
     * @param businId
     * @param auditType
     * @param flag
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "queryAuditLogChangeApplyList", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String queryAuditLogChangeApplyList(Long businId, int flag, int pageNum, int pageSize) {
        logger.debug("入参:businId=" + String.valueOf(businId));
        return exec(() -> auditLogService.webQueryAuditLogChangeApplyList(businId), "审核日志-列表查询 错误", logger);
    }

    /**
     * 审核日志详情
     * 
     * @param id
     * @return
     */
    @RequestMapping(value = "findAuditLog", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String findAuditLog(Long id) {
        logger.debug("入参:id=" + String.valueOf(id));
        return exec(() -> auditLogService.webFindAuditLog(id), "审核日志-详情查询 错误", logger);
    }
}
