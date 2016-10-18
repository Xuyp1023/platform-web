// Copyright (c) 2014-2016 Bytter. All rights reserved.
// ============================================================================
// CURRENT VERSION
// ============================================================================
// CHANGE LOG
// V2.0 : 2016年10月18日, liuwl, creation
// ============================================================================
package com.betterjr.modules.cert;

import java.io.UnsupportedEncodingException;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.betterjr.modules.cert.dubboclient.CustCertDubboClientService;

/**
 * @author liuwl
 *
 */
@Controller
@RequestMapping(value = "/forCustomer")
public class CustCertificateDownloadController {
    @Inject
    private CustCertDubboClientService certDubboClientService;

    @RequestMapping(value = "/downloadCertificate")
    public void fileDownload(final String token, final HttpServletResponse response) throws UnsupportedEncodingException {
        certDubboClientService.downloadCertificate(token, response);
    }
}
