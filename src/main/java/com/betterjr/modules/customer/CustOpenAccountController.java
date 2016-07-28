package com.betterjr.modules.customer;

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
import com.betterjr.common.web.Servlets;

@Controller
@RequestMapping("/Platform/CustOpenAccount")
public class CustOpenAccountController {

    private static final Logger logger = LoggerFactory.getLogger(CustOpenAccountController.class);

    @Reference(interfaceClass = ICustOpenAccountService.class)
    private ICustOpenAccountService custOpenAccountService;

    @RequestMapping(value = "/openAccount", method = RequestMethod.POST)
    public @ResponseBody String openAccount(HttpServletRequest request, Long id, String fileList) {
        Map<String, Object> anMap = Servlets.getParametersStartingWith(request, "");
        logger.info("开户资料,入参：" + anMap.toString());
        try {

            return custOpenAccountService.webSaveOpenAccount(anMap, id, fileList);
        }
        catch (Exception e) {
            logger.error("开户失败", e);
            return AjaxObject.newError("开户失败").toJson();
        }
    }

    @RequestMapping(value = "/findOpenAccountTemp", method = RequestMethod.POST)
    public @ResponseBody String findOpenAccountTemp() {
        logger.info("开户资料读取");
        try {

            return custOpenAccountService.webFindOpenAccountTemp();
        }
        catch (Exception e) {
            logger.error("开户资料读取失败", e);
            return AjaxObject.newError("开户资料读取失败").toJson();
        }
    }

    @RequestMapping(value = "/findOpenAccountTempByInsteadId", method = RequestMethod.POST)
    public @ResponseBody String findOpenAccountTempByInsteadId(Long id) {
        logger.info("开户资料读取,入参：" + id);
        try {

            return custOpenAccountService.webFindOpenAccountTempByInsteadId(id);
        }
        catch (Exception e) {
            logger.error("开户资料读取失败", e);
            return AjaxObject.newError("开户资料读取失败").toJson();
        }
    }

    @RequestMapping(value = "/saveOpenAccountTemp", method = RequestMethod.POST)
    public @ResponseBody String saveOpenAccountTemp(HttpServletRequest request, String fileList) {
        Map<String, Object> anMap = Servlets.getParametersStartingWith(request, "");
        logger.info("开户资料暂存,入参：" + anMap.toString());
        try {

            return custOpenAccountService.webSaveOpenAccountTemp(anMap, fileList);
        }
        catch (Exception e) {
            logger.error("开户资料暂存失败", e);
            return AjaxObject.newError("开户资料暂存失败").toJson();
        }
    }

    @RequestMapping(value = "/saveOpenAccountInsteadTemp", method = RequestMethod.POST)
    public @ResponseBody String saveOpenAccountInsteadTemp(HttpServletRequest request, Long id, String fileList) {
        Map<String, Object> anMap = Servlets.getParametersStartingWith(request, "");
        logger.info("开户资料暂存,入参：" + anMap.toString());
        try {

            return custOpenAccountService.webSaveOpenAccountInsteadTemp(anMap, id, fileList);
        }
        catch (Exception e) {
            logger.error("开户资料暂存失败", e);
            return AjaxObject.newError("开户资料暂存失败").toJson();
        }
    }

}
