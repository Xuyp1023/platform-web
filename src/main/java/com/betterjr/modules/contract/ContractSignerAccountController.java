// Copyright (c) 2014-2017 Bytter. All rights reserved.
// ============================================================================
// CURRENT VERSION
// ============================================================================
// CHANGE LOG
// V2.0 : 2017年4月26日, liuwl, creation
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
import com.betterjr.common.web.Servlets;

/**
 * @author liuwl
 *
 */
@Controller
@RequestMapping("/Platform/ContractSignerAccount")
public class ContractSignerAccountController {
    private static final Logger logger = LoggerFactory.getLogger(ContractSignerAccountController.class);

    @Reference(interfaceClass = IContractSignerAccountService.class)
    private IContractSignerAccountService contractSignerAccountService;

    @RequestMapping(value = "/findOperInfo", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String findOperInfo(final HttpServletRequest request, final Long id) {
        return exec(() -> contractSignerAccountService.webFindOperInfo(id), "获取操作员信息出错！", logger);
    }

    @RequestMapping(value = "/querySignerAccountInfo", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String querySignerAccountInfo(final HttpServletRequest request, final Long anCustNo, final int flag, final int pageNum,
            final int pageSize) {
        return exec(() -> contractSignerAccountService.webQuerySignerAccountInfo(anCustNo, flag, pageNum, pageSize), "获取操作员信息出错！", logger);
    }

    @RequestMapping(value = "/checkSignerAccount", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String checkSignerAccount(final HttpServletRequest request, final Long operId) {
        return exec(() -> contractSignerAccountService.webCheckSignerAccount(operId), "检查操作员帐号出错！", logger);
    }

    @RequestMapping(value = "/saveRegistSignerAccount", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String saveRegistSignerAccount(final HttpServletRequest request) {
        final Map<String, Object> param = Servlets.getParametersStartingWith(request, "");
        logger.debug("入参:param=" + param);
        return exec(() -> contractSignerAccountService.webSaveRegistSignerAccount(param), "注册操作员帐号出错！", logger);
    }
}
