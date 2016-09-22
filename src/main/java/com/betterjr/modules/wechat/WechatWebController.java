package com.betterjr.modules.wechat;

import java.io.IOException;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.betterjr.common.web.Servlets;
import com.betterjr.modules.wechat.data.api.AccessToken;
import com.betterjr.modules.wechat.dubboclient.CustWeChatDubboClientService;
import com.betterjr.modules.wechat.util.WechatDefHandler;
import com.betterjr.modules.wechat.util.WechatKernel;

/**
 * WeChat WEB容器环境接入
 *
 * @author zhoucy
 */

@Controller
@RequestMapping(value = "/Wechat/wxRequest")
public class WechatWebController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Resource
    private CustWeChatDubboClientService wechatDubboService;

    protected WechatKernel initKernel(final Map<String, String> anMap) {
        final WechatKernel wk = new WechatKernel(wechatDubboService.getMpAccount(), new WechatDefHandler(wechatDubboService), anMap);

        return wk;
    }

    /**
     * 与微信服务器互动
     *
     * @param req
     *            微信服务器请求
     * @param resp
     *            响应微信服务器
     * @throws IOException
     */
    @RequestMapping(value = "/dispatcher", method = { RequestMethod.POST, RequestMethod.GET })
    public void wxDispatcher(final HttpServletRequest req, final HttpServletResponse resp) throws IOException {
        final Map<String, String> map = Servlets.getParameters(req);
        final WechatKernel wk = initKernel(map);
        String respmsg = "success";
        if ("GET".equals(req.getMethod())) {
            respmsg = wk.check();
        }
        else {
            respmsg = wk.handle(req.getInputStream());
        }
        // 输出回复消息
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html");
        resp.getWriter().print(respmsg);
    }

    /**
     * 与微信服务器互动
     *
     * @param req
     *            微信服务器请求
     * @param resp
     *            响应微信服务器
     * @throws IOException
     */
    @RequestMapping(value = "/oauth2", method = { RequestMethod.POST, RequestMethod.GET })
    public void wxOauth2(final HttpServletRequest req, final HttpServletResponse resp) throws IOException {
        final Map<String, String> map = Servlets.getParameters(req);
        final WechatKernel wk = initKernel(map);
        for (final Map.Entry<String, String> ent : map.entrySet()) {
            logger.info("this is oauth2 values " + ent.getKey() + " = " + ent.getValue());
        }
        final AccessToken at = wk.findUserAuth2(map.get("code"));
        logger.info("wxOauth2 AccessToken"+at);
        resp.sendRedirect("http://atest.qiejf.com/better/p/pages/login.html");
    }


}
