package com.betterjr.modules.cert;

import static com.betterjr.common.web.ControllerExceptionHandler.exec;

import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.betterjr.common.web.Servlets;
import com.betterjr.modules.cert.dubboclient.X509CertDubboClientService;

/**
 *
 * @author liuwl
 *
 */
@Controller
@RequestMapping("/Platform/Certificate")
public class CertificateController {
    private static final Logger logger = LoggerFactory.getLogger(CertificateController.class);

    @Inject
    private X509CertDubboClientService x509CertDubboClientService;

    /**
     * 数字证书信息-查询
     *
     * @return
     */
    @RequestMapping(value = "/queryCertificateInfo", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String queryCertificateInfo(final HttpServletRequest request, final int flag,
            final int pageNum, final int pageSize) {
        final Map<String, Object> param = Servlets.getParametersStartingWith(request, "");
        logger.debug("数字证书信息-查询 入参:anParam=" + param);
        return exec(() -> x509CertDubboClientService.queryCertificateInfo(param, flag, pageNum, pageSize),
                "数字证书信息-查询 出错", logger);
    }

    /**
     * 数字证书信息-查询
     *
     * @return
     */
    @RequestMapping(value = "/queryUnusedCertificateInfo", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String queryUnusedCertificateInfo(final HttpServletRequest request) {
        return exec(() -> x509CertDubboClientService.queryUnusedCertificateInfo(), "数字证书信息-查询 出错", logger);
    }

    /**
     * 数字证书信息-新增
     *
     * @return
     */
    @RequestMapping(value = "/addCertificateInfo", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String addCertificateInfo(final HttpServletRequest request) {
        final Map<String, Object> param = Servlets.getParametersStartingWith(request, "");
        logger.debug("数字证书信息-新增 入参:anParam=" + param);
        return exec(() -> x509CertDubboClientService.addCertificateInfo(param), "数字证书信息-新增 出错", logger);
    }

    /**
     * 数字证书信息-修改
     *
     * @return
     */
    @RequestMapping(value = "/modifyCertificateInfo", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String modifyCertificateInfo(final HttpServletRequest request, final Long id) {
        final Map<String, Object> param = Servlets.getParametersStartingWith(request, "");
        logger.debug("数字证书信息-修改 入参: id=" + id + "anParam=" + param);
        return exec(() -> x509CertDubboClientService.modifyCertificateInfo(id, param), "数字证书信息-修改 出错", logger);
    }

    /**
     * 数字证书信息-详情
     *
     * @return
     */
    @RequestMapping(value = "/findCertificateInfo", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String findCertificateInfo(final HttpServletRequest request, final Long id,
            final String serialNo) {
        logger.debug("数字证书信息-详情 入参:id=" + id + " serialNo=" + serialNo);
        return exec(() -> x509CertDubboClientService.findCertificateInfo(id, serialNo), "数字证书信息-详情 出错", logger);
    }

    /**
     * 数字证书信息-详情
     *
     * @return
     */
    @RequestMapping(value = "/findCertificateInfoById", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String findCertificateInfoById(final HttpServletRequest request, final Long id) {
        logger.debug("数字证书信息-详情 入参:id=" + id);
        return exec(() -> x509CertDubboClientService.findCertificateInfo(id), "数字证书信息-详情 出错", logger);
    }

    /**
     * 数字证书信息-作废
     *
     * @return
     */
    @RequestMapping(value = "/cancelCertificateInfo", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String cancelCertificateInfo(final HttpServletRequest request, final Long id,
            final String serialNo) {
        logger.debug("数字证书信息-作废 入参:id=" + id + " serialNo=" + serialNo);
        return exec(() -> x509CertDubboClientService.cancelCertificateInfo(id, serialNo), "数字证书信息-作废 出错", logger);
    }

    /**
     * 查询签发者列表
     *
     * @return
     */
    @RequestMapping(value = "/querySignerList", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String querySignerList(final HttpServletRequest request) {
        return exec(() -> x509CertDubboClientService.querySignerList(), "查询签发者列表 出错", logger);
    }

}
