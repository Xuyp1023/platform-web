package com.betterjr.modules.notification;

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
@RequestMapping(path = "/Platform/NotificationProfile")
public class NotificationProfileConstroller {
    private static final Logger logger = LoggerFactory.getLogger(NotificationProfileConstroller.class);

    @Reference(interfaceClass = INotificationProfileService.class)
    private INotificationProfileService notificationProfileService;

    /**
     * 消息模板列表-查询
     * 
     * @return
     */
    @RequestMapping(value = "/queryNotificationProfile", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String queryUnreadNotification(HttpServletRequest request, Long custNo, int flag, int pageNum, int pageSize) {
        try {
            logger.debug("消息模板列表-查询 入参:custNo=" + custNo);
            return notificationProfileService.webQueryNotificationProfile(custNo, flag, pageNum, pageSize);
        }
        catch (final Exception e) {
            logger.error("消息模板列表-查询 出错", e);
            return AjaxObject.newError("消息模板列表-查询 出错").toJson();
        }
    }

    /**
     * 设置模板启用状态
     * 
     * @return
     */
    @RequestMapping(value = "/setEnabledNotificationProfile", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String setEnabledNotificationProfile(HttpServletRequest request, Long id) {
        try {
            logger.debug("设置模板启用状态 入参:id=" + id);
            return notificationProfileService.webSetEnabledNotificationProfile(id);
        }
        catch (final Exception e) {
            logger.error("设置模板启用状态 出错", e);
            return AjaxObject.newError("设置模板启用状态 出错").toJson();
        }
    }

    /**
     * 设置模板禁用状态
     * 
     * @return
     */
    @RequestMapping(value = "/setDisabledNotificationProfile", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String setDisabledNotificationProfile(HttpServletRequest request, Long id) {
        try {
            logger.debug("设置模板禁用状态 入参:id=" + id);
            return notificationProfileService.webSetDisabledNotificationProfile(id);
        }
        catch (final Exception e) {
            logger.error("设置模板禁用状态 出错", e);
            return AjaxObject.newError("设置模板禁用状态 出错").toJson();
        }
    }

    /**
     * 通道模板列表-查询
     * 
     * @return
     */
    @RequestMapping(value = "/queryChannelProfile", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String queryChannelProfile(HttpServletRequest request, Long profileId) {
        try {
            logger.debug("通道模板列表-查询 入参:profileId=" + profileId);
            return notificationProfileService.webQueryNotificationChannelProfile(profileId);
        }
        catch (final Exception e) {
            logger.error("通道模板列表-查询 出错", e);
            return AjaxObject.newError("通道模板列表-查询 出错").toJson();
        }
    }

    /**
     * 通道模板-保存
     * 
     * @return
     */
    @RequestMapping(value = "/saveChannelProfile", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String saveChannelProfile(HttpServletRequest request, Long channelProfileId) {
        try {
            Map<String, Object> anParam = Servlets.getParametersStartingWith(request, "");
            logger.debug("通道模板-保存 入参:anParam=" + anParam + " channelProfileId=" + channelProfileId);
            return notificationProfileService.webSaveNotificationChannelProfile(anParam, channelProfileId);
        }
        catch (final Exception e) {
            logger.error("通道模板-保存 出错", e);
            return AjaxObject.newError("通道模板-保存 出错").toJson();
        }
    }

    /**
     * 通道模板变量规则-查询
     * 
     * @return
     */
    @RequestMapping(value = "/queryProfileVariable", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String queryProfileVariable(HttpServletRequest request, Long channelProfileId) {
        try {
            logger.debug("通道模板变量规则-查询 入参:channelProfileId=" + channelProfileId);
            return notificationProfileService.webQueryNotificationProfileVariable(channelProfileId);
        }
        catch (final Exception e) {
            logger.error("通道模板变量规则-查询 出错", e);
            return AjaxObject.newError("通道模板变量规则-查询 出错").toJson();
        }
    }
}
