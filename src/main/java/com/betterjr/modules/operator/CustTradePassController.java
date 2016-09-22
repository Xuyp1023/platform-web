// Copyright (c) 2014-2016 Betty. All rights reserved.
// ============================================================================
// CURRENT VERSION
// ============================================================================
// CHANGE LOG
// V2.0 : 2016年9月18日, liuwl, creation
// ============================================================================
package com.betterjr.modules.operator;

import static com.betterjr.common.web.ControllerExceptionHandler.exec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;
import com.betterjr.modules.account.dubbo.interfaces.ICustTradePassService;

/**
 * @author liuwl
 *
 */
@Controller
@RequestMapping(value = "/Platform/TradePass")
public class CustTradePassController {

    private static final Logger logger = LoggerFactory.getLogger(CustTradePassController.class);

    @Reference(interfaceClass = ICustTradePassService.class)
    private ICustTradePassService tradePassService;

    /**
     * 发送验证码
     */
    @RequestMapping(value = "/sendVerifyCode", method = RequestMethod.POST)
    public @ResponseBody String sendVerifyCode() {
        return exec(() -> tradePassService.webSendVerifyCode(), "发送验证码失败！", logger);
    }

    /**
     * 判断验证码
     */
    @RequestMapping(value = "/checkVerifyCode", method = RequestMethod.POST)
    public @ResponseBody String checkVerifyCode(final String verifyCode) {
        return exec(() -> tradePassService.webCheckVerifyCode(verifyCode), "校验验证码失败！", logger);
    }

    /**
     * 修改交易密码
     */
    @RequestMapping(value = "/saveModifyTradePass", method = RequestMethod.POST)
    public @ResponseBody String saveModifyTradePass(final String newPassword, final String okPassword, final String oldPassword) {
        return exec(() -> tradePassService.webSaveModifyTradePass(newPassword, okPassword, oldPassword), "修改交易密码失败！", logger);
    }

    /**
     * 保存移动端交易密码
     */
    @RequestMapping(value = "/saveMobileTradePass", method = RequestMethod.POST)
    public @ResponseBody String saveMobileTradePass(final String newPassword, final String okPassword, final String loginPassword) {
        return exec(() -> tradePassService.webSaveMobileTradePass(newPassword, okPassword, loginPassword), "保存密码失败！", logger);
    }
}
