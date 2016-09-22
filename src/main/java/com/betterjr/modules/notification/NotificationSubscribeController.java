package com.betterjr.modules.notification;

import static com.betterjr.common.web.ControllerExceptionHandler.exec;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;

/**
 *
 * @author liuwl
 *
 */
@Controller
@RequestMapping(path = "/Platform/NotificationSubscribe")
public class NotificationSubscribeController {
    private static final Logger logger = LoggerFactory.getLogger(NotificationSubscribeController.class);

    @Reference(interfaceClass = INotificationSubscribeService.class)
    private INotificationSubscribeService subscribeService;

    /**
     * 查询本公司的订阅情况
     */
    @RequestMapping(value = "/querySubscribeByCustNo", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String querySubscribeByCustNo(final HttpServletRequest request, final Long custNo, final int flag, final int pageNum, final int pageSize) {
        logger.debug("消息订阅列表-查询 入参:custNo=" + custNo);
        return exec(() -> subscribeService.webQuerySubscribeByCustNo(custNo, flag, pageNum, pageSize), "消息订阅列表-查询 出错", logger);
    }

    /**
     * 取消订阅
     */
    @RequestMapping(value = "/cancelSubscribe", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String cancelSubscribe(final Long custNo, final Long sourceCustNo, final String profileName, final String channel) {
        logger.debug("消息订阅列表-查询 入参:custNo=" + custNo + " sourceCustNo=" + sourceCustNo + " profileName=" + profileName + " channel=" + channel);
        return exec(() -> subscribeService.webCancelSubscribe(custNo, sourceCustNo, profileName, channel), "取消订阅  出错", logger);
    }

    /**
     * 订阅
     */
    @RequestMapping(value = "/confirmSubscribe", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String confirmSubscribe(final Long custNo, final Long sourceCustNo, final String profileName, final String channel) {
        logger.debug("消息订阅列表-查询 入参:custNo=" + custNo + " sourceCustNo=" + sourceCustNo + " profileName=" + profileName + " channel=" + channel);
        return exec(() -> subscribeService.webConfirmSubscribe(custNo, sourceCustNo, profileName, channel), "订阅  出错", logger);
    }
}
