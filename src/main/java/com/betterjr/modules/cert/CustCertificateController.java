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
import com.betterjr.modules.cert.dubboclient.CustCertDubboClientService;

@Controller
@RequestMapping("/Platform/CustCertificate")
public class CustCertificateController {
    private static final Logger logger = LoggerFactory.getLogger(CustCertificateController.class);

    @Inject
    private CustCertDubboClientService certDubboClientService;

    /**
     * 数字证书信息-查询
     *
     * @return
     */
    @RequestMapping(value = "/queryCertificateInfo", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String queryCertificateInfo(final HttpServletRequest request, final int flag, final int pageNum, final int pageSize) {
        final Map<String, Object> param = Servlets.getParametersStartingWith(request, "");
        logger.debug("数字证书信息-查询 入参:anParam=" + param);
        return exec(() -> certDubboClientService.queryCustCertificate(param, flag, pageNum, pageSize), "数字证书信息-查询 出错", logger);
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
        return exec(() -> certDubboClientService.addCustCertificate(param), "数字证书信息-新增 出错", logger);
    }

    /**
     * 数字证书信息-修改
     *
     * @return
     */
    @RequestMapping(value = "/modifyCertificateInfo", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String modifyCertificateInfo(final HttpServletRequest request, final String serialNo) {
        final Map<String, Object> param = Servlets.getParametersStartingWith(request, "");
        logger.debug("数字证书信息-修改 入参: id=" + serialNo + "anParam=" + param);
        return exec(() -> certDubboClientService.modifyCustCertificate(serialNo, param), "数字证书信息-修改 出错", logger);
    }

    /**
     * 数字证书信息-修改
     *
     * @return
     */
    @RequestMapping(value = "/modifyWechatCertificateInfo", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String modifyWechatCertificateInfo(final HttpServletRequest request, final String serialNo, final String orginSerialNo) {
        final Map<String, Object> param = Servlets.getParametersStartingWith(request, "");
        logger.debug("数字证书信息-修改 入参: id=" + serialNo + "anParam=" + param);
        return exec(() -> certDubboClientService.modifyWechatCustCertificate(serialNo, orginSerialNo, param), "数字证书信息-修改 出错", logger);
    }


    /**
     * 数字证书信息-详情
     *
     * @return
     */
    @RequestMapping(value = "/findCertificateInfo", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String findCertificateInfo(final HttpServletRequest request, final String serialNo) {
        logger.debug("数字证书信息-详情 入参: serialNo=" + serialNo);
        return exec(() -> certDubboClientService.findCustCertificate(serialNo), "数字证书信息-详情 出错", logger);
    }

    /**
     * 数字证书信息-作废
     *
     * @return
     */
    @RequestMapping(value = "/cancelCertificateInfo", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String cancelCertificateInfo(final HttpServletRequest request, final String serialNo) {
        logger.debug("数字证书信息-作废 入参: serialNo=" + serialNo);
        return exec(() -> certDubboClientService.cancelCustCertificate(serialNo), "数字证书信息-作废 出错", logger);
    }

    /**
     * 数字证书信息-发布
     *
     * @return
     */
    @RequestMapping(value = "/publishCertificateInfo", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String publishCertificateInfo(final HttpServletRequest request, final String serialNo, final String publishMode) {
        logger.debug("数字证书信息-发布 入参: serialNo=" + serialNo);
        return exec(() -> certDubboClientService.publishCustCertificate(serialNo, publishMode), "数字证书信息-发布 出错", logger);
    }

    /**
     * 数字证书信息-回收
     *
     * @return
     */
    @RequestMapping(value = "/revokeCertificateInfo", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String revokeCertificateInfo(final HttpServletRequest request, final String serialNo, final String reason) {
        logger.debug("数字证书信息-回收 入参: serialNo=" + serialNo + " reason=" + reason);
        return exec(() -> certDubboClientService.revokeCustCertificate(serialNo, reason), "数字证书信息-回收 出错", logger);
    }

}
