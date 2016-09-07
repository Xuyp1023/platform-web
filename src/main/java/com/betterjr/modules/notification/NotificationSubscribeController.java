package com.betterjr.modules.notification;

import static com.betterjr.common.web.ControllerExceptionHandler.exec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
    public String querySubscribeByCustNo(Long custNo, int flag, int pageNum, int pageSize) {
        logger.debug("消息订阅列表-查询 入参:custNo=" + custNo);
        return exec(() -> subscribeService.webQuerySubscribeByCustNo(custNo, flag, pageNum, pageSize), "消息订阅列表-查询 出错", logger);
    }

    /**
     * 取消订阅
     */
    @RequestMapping(value = "/cancelSubscribe", method = RequestMethod.POST, produces = "application/json")
    public String cancelSubscribe(Long custNo, Long channelProfileId) {
        logger.debug("取消订阅 入参:custNo=" + custNo + " channelProfileId=" + channelProfileId);
        return exec(() -> subscribeService.webCancelSubscribe(custNo, channelProfileId), "取消订阅  出错", logger);
    }

    /**
     * 订阅
     */
    @RequestMapping(value = "/confirmSubscribe", method = RequestMethod.POST, produces = "application/json")
    public String confirmSubscribe(Long custNo, Long channelProfileId) {
        logger.debug("消息订阅列表-查询 入参:custNo=" + custNo + " channelProfileId=" + channelProfileId);
        return exec(() -> subscribeService.webConfirmSubscribe(custNo, channelProfileId), "订阅  出错", logger);
    }
}