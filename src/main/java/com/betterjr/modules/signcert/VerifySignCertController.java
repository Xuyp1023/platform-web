// Copyright (c) 2014-2017 Bytter. All rights reserved.
// ============================================================================
// CURRENT VERSION
// ============================================================================
// CHANGE LOG
// V2.0 : 2017年5月10日, liuwl, creation
// ============================================================================
package com.betterjr.modules.signcert;

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
import com.betterjr.modules.cert.dubbo.interfaces.IVerifySignCertService;

/**
 * @author liuwl
 *
 */
@Controller
@RequestMapping("/Platform/VerifySignCert")
public class VerifySignCertController {
    private static final Logger logger = LoggerFactory.getLogger(VerifySignCertController.class);

    @Reference(interfaceClass = IVerifySignCertService.class)
    private IVerifySignCertService verifySignCertService;

    @RequestMapping(value = "/queryCertList", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String queryCertList(final HttpServletRequest request, final int flag, final int pageNum,
            final int pageSize) {
        final Map<String, Object> param = Servlets.getParametersStartingWith(request, "");
        logger.debug("签名证书信息-查询 入参:anParam=" + param);
        return exec(() -> verifySignCertService.webQueryCertList(param, flag, pageNum, pageSize), "签名证书信息 查询错误",
                logger);
    }

    @RequestMapping(value = "/addCert", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String addCert(final HttpServletRequest request) {
        final Map<String, Object> param = Servlets.getParametersStartingWith(request, "");
        logger.debug("签名证书信息-添加 入参:anParam=" + param);
        return exec(() -> verifySignCertService.webSaveAddCert(param), "签名证书信息 添加错误", logger);
    }

    @RequestMapping(value = "/editCert", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String editCert(final HttpServletRequest request, final Long id) {
        final Map<String, Object> param = Servlets.getParametersStartingWith(request, "");
        logger.debug("签名证书信息-编辑 入参:anParam=" + param);
        return exec(() -> verifySignCertService.webSaveEditCert(param, id), "签名证书信息 编辑错误", logger);
    }

    @RequestMapping(value = "/enableCert", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String enableCert(final HttpServletRequest request, final Long id) {
        final Map<String, Object> param = Servlets.getParametersStartingWith(request, "");
        logger.debug("签名证书信息-启用 入参:anParam=" + param);
        return exec(() -> verifySignCertService.webSaveEnableCert(id), "签名证书信息 启用错误", logger);
    }

    @RequestMapping(value = "/disableCert", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String disableCert(final HttpServletRequest request, final Long id) {
        final Map<String, Object> param = Servlets.getParametersStartingWith(request, "");
        logger.debug("签名证书信息-禁用 入参:anParam=" + param);
        return exec(() -> verifySignCertService.webSaveDisableCert(id), "签名证书信息 禁用错误", logger);
    }

    @RequestMapping(value = "/findCert", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String findCert(final HttpServletRequest request, final Long id) {
        final Map<String, Object> param = Servlets.getParametersStartingWith(request, "");
        logger.debug("签名证书信息-详情查询 入参:anParam=" + param);
        return exec(() -> verifySignCertService.webFindCert(id), "签名证书信息 详情查询错误", logger);
    }
}
