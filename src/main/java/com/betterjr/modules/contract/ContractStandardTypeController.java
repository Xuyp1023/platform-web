// Copyright (c) 2014-2017 Bytter. All rights reserved.
// ============================================================================
// CURRENT VERSION
// ============================================================================
// CHANGE LOG
// V2.0 : 2017年4月19日, liuwl, creation
// ============================================================================
package com.betterjr.modules.contract;

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
import com.betterjr.common.web.AjaxObject;
import com.betterjr.common.web.ControllerExceptionHandler;
import com.betterjr.common.web.Servlets;

/**
 * @author liuwl
 *
 */
@Controller
@RequestMapping("/Platform/ContractStandardType")
public class ContractStandardTypeController {
    private static final Logger logger = LoggerFactory.getLogger(ContractStandardTypeController.class);

    @Reference(interfaceClass = IContractStandardTypeService.class)
    private IContractStandardTypeService contractStandardTypeService;

    @RequestMapping(value = "/queryList", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String queryList(final HttpServletRequest request, final Long typeId) {
        return exec(() -> contractStandardTypeService.webQueryTypeList(typeId), "查询标准合同类型列表出错！", logger);
    }

    @RequestMapping(value = "/querySimpleList", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String querySimpleList(final HttpServletRequest request, final Long typeId) {
        return exec(() -> contractStandardTypeService.webQuerySimpleTypeList(typeId), "查询标准合同类型列表出错！", logger);
    }

    /**
     * 标准登记合同类型
     */
    @RequestMapping(value = "/addContractStandards", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String addContractStandards(HttpServletRequest request) {
    	  Map<String, Object> anMap = Servlets.getParametersStartingWith(request, "");
          logger.info("标准合同登记,入参:" + anMap.toString());
          return exec(() -> contractStandardTypeService.webContractStandards(anMap), "标准合同登记失败！", logger);
    }
    
    /**
     * 编辑标准合同类型
     */
    @RequestMapping(value = "/saveContractStandardType", method = RequestMethod.POST)
    public @ResponseBody String saveModifyAgreementType(HttpServletRequest request, Long id) {
        Map<String, Object> anMap = Servlets.getParametersStartingWith(request, "");
        logger.info("标准合同合同类型,入参:" + anMap);
        return  exec(() -> contractStandardTypeService.webSaveModifyContractType(anMap, id), "编辑标准合同类型错误", logger);
    }
    
    /**
     * 删除标准合同类型
     */
    @RequestMapping(value = "/saveDeleteContractStandardType", method = RequestMethod.POST)
    public @ResponseBody String saveDeleteAgreementType(Long id) {
        logger.info("删除标准合同类型,入参:id=" + id);
        return exec(() -> contractStandardTypeService.webSaveDeleteContractStandardType(id), "删除标准合同类型错误", logger);
    }
    
    /**
     * 已启动标准登记合同类型查询
     */
    @RequestMapping(value = "/queryContractStandardType", method = RequestMethod.POST)
    public @ResponseBody String queryContractStandardType(int pageNum, int pageSize, String flag) {
        logger.info("已启用标准合同类型登记查询");
        return ControllerExceptionHandler.exec(() -> contractStandardTypeService.webQueryContractStandardType(pageNum, pageSize, flag), "合同类型登记查询失败", logger);
    }
    
    /**
     * 未启用查询标准合同
     */
    @RequestMapping(value = "/queryUnEnableContractStandardType", method = RequestMethod.POST)
    public @ResponseBody String queryUnEnableAgreementType(int pageNum, int pageSize, String flag) {
        logger.info("查询待启用标准合同类型");
        return exec(() -> contractStandardTypeService.webQueryUnEnableContractStandardType(pageNum, pageSize, flag), "查询未启用标准合同类型失败", logger);
    }

    /**
     * 启用标准合同类型
     */
    @RequestMapping(value = "/saveEnableContractStandardType", method = RequestMethod.POST)
    public @ResponseBody String saveEnableAgreementType(Long id) {
        logger.info("启用标准合同类型,入参:id=" + id);
        return exec(() -> contractStandardTypeService.webSaveEnableContractStandardTyp(id), "启用标准合同类型错误", logger);
    }
    
    /**
     * 查询所有标准合同类型
     */
    @RequestMapping(value = "/queryAllContractStandardType", method = RequestMethod.POST)
    public @ResponseBody String queryAllAgreementType(final HttpServletRequest request, int pageNum, int pageSize, String flag) {
    	logger.info("查询所有的标准合同类型");
        final Map<String, Object> anMap = Servlets.getParametersStartingWith(request, "");
        logger.debug("入参:param=" + anMap);
        return exec(() -> contractStandardTypeService.webQueryAllContractStandardType(anMap, pageNum, pageSize, flag), "查询所有的标准合同类型出错", logger);
    }
    
    /**
     * 停用标准合同类型
     */
    @RequestMapping(value = "/saveStopContractStandardType", method = RequestMethod.POST)
    public @ResponseBody String saveStopAgreementType(Long id) {
        logger.info("停用标准合同类型,入参:id=" + id);
        return exec(() -> contractStandardTypeService.webSaveStopContractStandardTyp(id), "停用标准合同类型错误", logger);
    }
    
}

