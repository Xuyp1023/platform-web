// Copyright (c) 2014-2016 Betty. All rights reserved.
// ============================================================================
// CURRENT VERSION
// ============================================================================
// CHANGE LOG
// V2.0 : 2016年9月22日, liuwl, creation
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
@RequestMapping(value = "/Wechat/Platform/TradePass")
public class WechatTradePassController {

    private static final Logger logger = LoggerFactory.getLogger(WechatTradePassController.class);

    @Reference(interfaceClass = ICustTradePassService.class)
    private ICustTradePassService tradePassService;

    /**
     * 首次登陆验证交易密码
     */
    @RequestMapping(value = "/checkFristTradePass", method = RequestMethod.POST)
    public @ResponseBody String checkFristTradePass(final String tradePassword) {
        return exec(() -> tradePassService.webSaveFristLoginTradePassword(tradePassword), "验证交易密码失败！", logger);
    }

}
