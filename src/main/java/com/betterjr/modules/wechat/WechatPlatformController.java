// Copyright (c) 2014-2016 Betty. All rights reserved.
// ============================================================================
// CURRENT VERSION
// ============================================================================
// CHANGE LOG
// V2.0 : 2016年9月18日, liuwl, creation
// ============================================================================
package com.betterjr.modules.wechat;

import static com.betterjr.common.web.ControllerExceptionHandler.exec;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.betterjr.common.web.AjaxObject;
import com.betterjr.modules.wechat.dubboclient.CustWeChatDubboClientService;

/**
 * @author liuwl
 *
 */
@Controller
@RequestMapping(value = "/Platform/Wechat")
public class WechatPlatformController {

    private static final Logger logger = LoggerFactory.getLogger(WechatPlatformController.class);

    @Resource
    private CustWeChatDubboClientService wechatDubboService;

    /**
     * 检查是否已经绑定微信账户
     */
    @RequestMapping(value = "/checkBindStatus", method = { RequestMethod.POST, RequestMethod.GET })
    public @ResponseBody String checkBindStatus(final HttpServletRequest request) throws IOException {
        return AjaxObject.newOk("检查微信账户扫描码结果", wechatDubboService.checkBindStatus()).toJson();
    }

    /**
     * 检查扫描状态，成功扫描返回TRUE
     */
    @RequestMapping(value = "/checkScanStatus", method = { RequestMethod.POST, RequestMethod.GET })
    public @ResponseBody String checkScanStatus(final int workType) throws IOException {
        return AjaxObject.newOk("检查微信账户扫描码结果", wechatDubboService.checkScanStatus()).toJson();
    }

    /**
     * 创建扫描码
     */
    @RequestMapping(value = "/createQcode", method = { RequestMethod.POST })
    public @ResponseBody String createQcode(final int workType) throws IOException {
        return exec(() -> AjaxObject.newOk("创建微信账户绑定扫描码", wechatDubboService.createQcode(workType)).toJson(), "创建微信账户绑定扫描码失败，请检查", logger);
    }

    /**
     * 保存移动端交易密码
     */
    @RequestMapping(value = "/saveMobileTradePass", method = RequestMethod.POST)
    public @ResponseBody String saveMobileTradePass(final String newPassword, final String okPassword, final String loginPassword) {
        return exec(() -> wechatDubboService.saveMobileTradePass(newPassword, okPassword, loginPassword), "保存密码失败！", logger);
    }

}
