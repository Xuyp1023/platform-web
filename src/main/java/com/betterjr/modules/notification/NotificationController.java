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
@RequestMapping(path = "/Platform/Notification")
public class NotificationController {
    private static final Logger logger = LoggerFactory.getLogger(NotificationController.class);

    @Reference(interfaceClass = INotificationService.class)
    private INotificationService notificationService;

    /**
     * 未读消息列表-查询
     * 
     * @return
     */
    @RequestMapping(value = "/queryUnreadNotification", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String queryUnreadNotification(HttpServletRequest request, int flag, int pageNum, int pageSize) {
        try {
            Map<String, Object> anParam = Servlets.getParametersStartingWith(request, "");
            logger.debug("未读消息列表-查询 入参:anParam=" + anParam);
            return notificationService.webQueryUnreadNotification(anParam, flag, pageNum, pageSize);
        }
        catch (final Exception e) {
            logger.error("未读消息列表-查询 出错", e);
            return AjaxObject.newError("未读消息列表-查询 出错").toJson();
        }
    }

    /**
     * 已读消息列表-查询
     * 
     * @return
     */
    @RequestMapping(value = "/queryReadNotification", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String queryReadNotification(HttpServletRequest request, int flag, int pageNum, int pageSize) {
        try {
            Map<String, Object> anParam = Servlets.getParametersStartingWith(request, "");
            logger.debug("已读消息列表-查询 入参:anParam=" + anParam);
            return notificationService.webQueryReadNotification(anParam, flag, pageNum, pageSize);
        }
        catch (final Exception e) {
            logger.error("已读消息列表-查询 出错", e);
            return AjaxObject.newError("已读消息列表-查询 出错").toJson();
        }
    }

    /**
     * 未读消息数量-查询
     * 
     * @return
     */
    @RequestMapping(value = "/countUnreadNotification", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String countUnreadNotification(HttpServletRequest request) {
        try {
            return notificationService.webCountUnreadNotification();
        }
        catch (final Exception e) {
            logger.error("未读消息数量-查询 出错", e);
            return AjaxObject.newError("未读消息数量-查询 出错").toJson();
        }
    }

    /**
     * 消息详情
     * 
     * @return
     */
    @RequestMapping(value = "/findNotification", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String countUnreadNotification(HttpServletRequest request, Long id) {
        try {
            logger.debug("消息详情 入参:id=" + id);
            return notificationService.webFindNotification(id);
        }
        catch (final Exception e) {
            logger.error("消息详情 出错", e);
            return AjaxObject.newError("消息详情 出错").toJson();
        }
    }

    /**
     * 设置消息已读状态
     * 
     * @return
     */
    @RequestMapping(value = "/setReadNotificationStatus", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String setReadNotificationStatus(HttpServletRequest request, Long id) {
        try {
            logger.debug("设置消息已读状态 入参:id=" + id);
            return notificationService.webSetReadNotificationStatus(id);
        }
        catch (final Exception e) {
            logger.error("设置消息已读状态 出错", e);
            return AjaxObject.newError("设置消息已读状态 出错").toJson();
        }
    }
}
