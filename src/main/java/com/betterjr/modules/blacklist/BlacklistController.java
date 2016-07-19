package com.betterjr.modules.blacklist;

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
@RequestMapping(value = "/Platform/Blacklist")
public class BlacklistController {

    private static final Logger logger = LoggerFactory.getLogger(BlacklistController.class);

    @Reference(interfaceClass = IBlacklistService.class)
    private IBlacklistService iScfBlacklistService;

    @RequestMapping(value = "/queryBlacklist", method = RequestMethod.POST)
    public @ResponseBody String queryBlacklist(HttpServletRequest request, String flag, int pageNum, int pageSize) {
        Map<String, Object> anMap = Servlets.getParametersStartingWith(request, "");
        logger.info("黑名单信息查询,入参：" + anMap.toString());
        try {

            return iScfBlacklistService.webQueryBlacklist(anMap, flag, pageNum, pageSize);
        }
        catch (Exception e) {
            logger.error("黑名单信息查询失败", e);
            return AjaxObject.newError("黑名单信息查询失败").toJson();
        }
    }

}
