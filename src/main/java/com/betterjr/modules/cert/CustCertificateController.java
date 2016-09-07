package com.betterjr.modules.cert;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/Platform/CustCertificate")
public class CustCertificateController {
    private static final Logger logger = LoggerFactory.getLogger(CustCertificateController.class);
}
