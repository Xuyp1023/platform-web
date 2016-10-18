// Copyright (c) 2014-2016 Bytter. All rights reserved.
// ============================================================================
// CURRENT VERSION
// ============================================================================
// CHANGE LOG
// V2.0 : 2016年10月18日, liuwl, creation
// ============================================================================
package com.betterjr.modules.operator.dubboclient;

import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.betterjr.modules.account.dubbo.interfaces.ICustTradePassService;

/**
 * @author liuwl
 *
 */
@Service
public class CustTradePassDubboClientService {

    @Reference(interfaceClass = ICustTradePassService.class)
    private ICustTradePassService tradePassService;

    public String webSendVerifyCode() {
        return tradePassService.webSendVerifyCode();
    }

    public String webCheckVerifyCode(final String anVerifyCode) {
        return tradePassService.webCheckVerifyCode(anVerifyCode);
    }

    public String webSaveModifyTradePass(final String anNewPassword, final String anOkPassword, final String anOldPassword) {
        return tradePassService.webSaveModifyTradePass(anNewPassword, anOkPassword, anOldPassword);
    }
}
