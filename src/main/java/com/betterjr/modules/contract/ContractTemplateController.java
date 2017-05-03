// Copyright (c) 2014-2017 Bytter. All rights reserved.
// ============================================================================
// CURRENT VERSION
// ============================================================================
// CHANGE LOG
// V2.0 : 2017年4月19日, liuwl, creation
// ============================================================================
package com.betterjr.modules.contract;

import static com.betterjr.common.web.ControllerExceptionHandler.exec;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;
import com.betterjr.common.mapper.JsonMapper;
import com.betterjr.common.web.Servlets;

/**
 * @author liuwl
 *
 */
@Controller
@RequestMapping("/Platform/ContractTemplate")
public class ContractTemplateController {
    private static final Logger logger = LoggerFactory.getLogger(ContractTemplateController.class);

    @Reference(interfaceClass = IContractTemplateService.class)
    private IContractTemplateService contractTemplateService;

    @RequestMapping(value = "/queryStandardType", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String queryStandardType(final HttpServletRequest request, final Long custNo, final String businStatus, final int flag, final int pageNum, final int pageSize) {
        return exec(() -> contractTemplateService.webQueryStandardType(custNo, businStatus, flag, pageNum, pageSize), "查询已启用标准合同类型列表出错！", logger);
    }

    @RequestMapping(value = "/queryUnusedStandardType", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String queryUnusedStandardType(final HttpServletRequest request, final Long custNo, final Long typeId) {
        return exec(() -> contractTemplateService.webQueryUnusedStandardType(custNo, typeId), "查询未启用标准合同类型列表出错！", logger);
    }

    @RequestMapping(value = "/saveEnableStandardType", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String saveEnableStandardType(final HttpServletRequest request, final Long custNo, final String standardTypeIds) {
        return exec(() -> contractTemplateService.webSaveEnableStandardType(custNo, standardTypeIds), "启用标准合同类型出错！", logger);
    }

    @RequestMapping(value = "/saveRemoveStandardType", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String saveRemoveStandardType(final HttpServletRequest request, final Long custNo, final Long standardTypeId) {
        return exec(() -> contractTemplateService.webSaveRemoveStandardType(custNo, standardTypeId), "启用标准合同类型出错！", logger);
    }

    @RequestMapping(value = "/queryUnusedContractTemplate", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String queryUnusedContractTemplate(final HttpServletRequest request, final Long custNo, final Long typeId, final int flag, final int pageNum, final int pageSize) {
        return exec(() -> contractTemplateService.webQueryUnusedConstractTemplate(custNo, typeId, flag, pageNum, pageSize), "查询已启用标准合同类型列表出错！", logger);
    }

    @RequestMapping(value = "/queryText", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String queryText(final HttpServletRequest request, final int flag, final int pageNum, final int pageSize) {
        final Map<String, Object> param = Servlets.getParametersStartingWith(request, "");
        logger.debug("入参:param=" + param);

        return exec(() -> contractTemplateService.webQueryText(param, flag, pageNum, pageSize), "查询合同文本列表出错！", logger);
    }

    @RequestMapping(value = "/queryAuditText", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String queryAuditText(final HttpServletRequest request, final int flag, final int pageNum, final int pageSize) {
        final Map<String, Object> param = Servlets.getParametersStartingWith(request, "");
        logger.debug("入参:param=" + param);

        return exec(() -> contractTemplateService.webQueryAuditText(param, flag, pageNum, pageSize), "查询审核合同文本列表出错！", logger);
    }

    @RequestMapping(value = "/queryUnusedText", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String queryUnusedText(final HttpServletRequest request, final Long custNo, final int flag, final int pageNum, final int pageSize) {
        final Map<String, Object> param = Servlets.getParametersStartingWith(request, "");
        logger.debug("入参:param=" + param);

        return exec(() -> contractTemplateService.webQueryUnusedText(custNo, flag, pageNum, pageSize), "查询审核合同文本列表出错！", logger);
    }

    @RequestMapping(value = "/saveAuditText", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String saveAuditText(final HttpServletRequest request, final Long templateId, final String auditStatus,
            final String auditRemark) {
        final Map<String, Object> param = Servlets.getParametersStartingWith(request, "");
        logger.debug("入参:param=" + param);

        return exec(() -> contractTemplateService.webSaveAuditText(templateId, auditStatus, auditRemark), "查询审核合同文本出错！", logger);
    }

    @RequestMapping(value = "/queryTemplate", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String queryTemplate(final HttpServletRequest request, final int flag, final int pageNum, final int pageSize) {
        final Map<String, Object> param = Servlets.getParametersStartingWith(request, "");
        logger.debug("入参:param=" + param);

        return exec(() -> contractTemplateService.webQueryTemplate(param, flag, pageNum, pageSize), "查询合同模板列表出错！", logger);
    }

    @RequestMapping(value = "/queryAuditTemplate", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String queryAuditTemplate(final HttpServletRequest request, final int flag, final int pageNum, final int pageSize) {
        final Map<String, Object> param = Servlets.getParametersStartingWith(request, "");
        logger.debug("入参:param=" + param);

        return exec(() -> contractTemplateService.webQueryAuditTemplate(param, flag, pageNum, pageSize), "查询审核合同模板列表出错！", logger);
    }

    @RequestMapping(value = "/saveUploadText", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String saveUploadText(final HttpServletRequest request) {
        final Map<String, Object> param = Servlets.getParametersStartingWith(request, "");
        logger.debug("入参:param=" + param);

        return exec(() -> contractTemplateService.webSaveUploadText(param), "上传标准合同文本出错！", logger);
    }

    @RequestMapping(value = "/saveUploadTemplate", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String saveUploadTemplate(final HttpServletRequest request, final Long templateId, final String templateFileId, final String common) {
        final Map<String, Object> param = Servlets.getParametersStartingWith(request, "");
        logger.debug("入参:param=" + param);

        return exec(() -> contractTemplateService.webSaveUploadTemplate(templateId, templateFileId, common), "上传标准合同模板出错！", logger);
    }

    @RequestMapping(value = "/findTemplateDetail", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String findTemplateDetail(final HttpServletRequest request, final Long id) {

        return exec(() -> contractTemplateService.webFindTemplateDetail(id), "查询标准合同详情出错！", logger);
    }

    @RequestMapping(value = "/saveStampPlace", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String saveStampPlace(final HttpServletRequest request, final Long templateId, final String stampPlaces) {
        final List<Map<String, Object>> stampPlaceList= (List<Map<String, Object>>) JsonMapper.fromJsonString(stampPlaces, List.class);

        return exec(() -> contractTemplateService.webSaveStampPlace(templateId, stampPlaceList), "标准合同签章位置设置出错！", logger);
    }

    @RequestMapping(value = "/saveAuditTemplate", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String saveAuditTemplate(final HttpServletRequest request, final Long custNo, final Long templateId, final String auditStatus,
            final String auditRemark) {
        final Map<String, Object> param = Servlets.getParametersStartingWith(request, "");
        logger.debug("入参:param=" + param);

        return exec(() -> contractTemplateService.webSaveAuditTemplate(custNo, templateId, auditStatus, auditRemark), "查询审核合同模板出错！", logger);
    }

    @RequestMapping(value = "/queryTemplateLog", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String queryTemplateLog(final HttpServletRequest request, final Long templateId) {
        final Map<String, Object> param = Servlets.getParametersStartingWith(request, "");
        logger.debug("入参:param=" + param);

        return exec(() -> contractTemplateService.webQueryTemplateLog(templateId), "标准合同操作记录查询出错！", logger);
    }
}
