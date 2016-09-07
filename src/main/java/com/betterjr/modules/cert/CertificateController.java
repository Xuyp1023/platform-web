package com.betterjr.modules.cert;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.betterjr.modules.customer.AuditLogController;

/**
 * 
 * @author liuwl
 *
 */
@Controller
@RequestMapping("/Platform/Certificate")
public class CertificateController {
    private static final Logger logger = LoggerFactory.getLogger(CertificateController.class);
}
