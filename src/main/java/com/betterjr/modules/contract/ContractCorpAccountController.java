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
@RequestMapping("/Platform/ContractCorpAccount")
public class ContractCorpAccountController {
    private static final Logger logger = LoggerFactory.getLogger(ContractCorpAccountController.class);

    @Reference(interfaceClass = IContractCorpAccountService.class)
    private IContractCorpAccountService contractCorpAccountService;

    @RequestMapping(value = "/checkCorpAccount", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String checkCorpAccount(final HttpServletRequest request, final Long custNo) {
        return exec(() -> contractCorpAccountService.webCheckCorpAccount(custNo), "检查企业账户出错！", logger);
    }

    @RequestMapping(value = "/findCorpInfo", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String findCorpInfo(final HttpServletRequest request, final Long id) {
        return exec(() -> contractCorpAccountService.webFindCorpInfo(id), "获取公司信息出错！", logger);
    }

    @RequestMapping(value = "/queryCorpAccountInfo", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String queryCorpAccountInfo(final HttpServletRequest request, final Long anCustNo,
            final int flag, final int pageNum, final int pageSize) {
        return exec(() -> contractCorpAccountService.webQueryCorpAccountInfo(anCustNo, flag, pageNum, pageSize),
                "获取公司帐户信息出错！", logger);
    }

    @RequestMapping(value = "/saveCorpSigner", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String saveCorpSigner(final HttpServletRequest request, final Long corpAccountId,
            final Long signerAccountId) {
        return exec(() -> contractCorpAccountService.webSaveCorpSigner(corpAccountId, signerAccountId), "设置企业签署人出错！",
                logger);
    }

    @RequestMapping(value = "/saveRegistCorpAccount", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String saveRegistCorpAccount(final HttpServletRequest request) {
        final Map<String, Object> param = Servlets.getParametersStartingWith(request, "");
        logger.debug("入参:param=" + param);
        return exec(() -> contractCorpAccountService.webSaveRegistCorpAccount(param), "注册企业账户出错！", logger);
    }

}
