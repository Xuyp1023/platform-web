package com.betterjr.modules.notification;

import static com.betterjr.common.web.ControllerExceptionHandler.exec;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;
import com.betterjr.common.web.Servlets;

/**
 *
 * @author liuwl
 *
 */
@Controller
@RequestMapping(path = "/Platform/NotificationProfile")
public class NotificationProfileConstroller {
    private static final Logger logger = LoggerFactory.getLogger(NotificationProfileConstroller.class);

    @Reference(interfaceClass = INotificationProfileService.class)
    private INotificationProfileService profileService;

    /**
     * 消息模板列表-查询
     *
     * @return
     */
    @RequestMapping(value = "/queryNotificationProfile", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String queryUnreadNotification(final HttpServletRequest request, final Long custNo,
            final int flag, final int pageNum, final int pageSize) {
        logger.debug("消息模板列表-查询 入参:custNo=" + custNo);
        return exec(() -> profileService.webQueryNotificationProfile(custNo, flag, pageNum, pageSize), "消息模板列表-查询 出错",
                logger);
    }

    /**
     * 设置模板启用状态
     *
     * @return
     */
    @RequestMapping(value = "/setEnabledNotificationProfile", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String setEnabledNotificationProfile(final HttpServletRequest request, final Long id,
            final Long custNo) {
        logger.debug("设置模板启用状态 入参:id=" + id);
        return exec(() -> profileService.webSetEnabledNotificationProfile(id, custNo), "设置模板启用状态 出错", logger);
    }

    /**
     * 设置模板禁用状态
     *
     * @return
     */
    @RequestMapping(value = "/setDisabledNotificationProfile", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String setDisabledNotificationProfile(final HttpServletRequest request, final Long id,
            final Long custNo) {
        logger.debug("设置模板禁用状态 入参:id=" + id);
        return exec(() -> profileService.webSetDisabledNotificationProfile(id, custNo), "设置模板禁用状态 出错", logger);
    }

    /**
     * 通道模板列表-查询
     *
     * @return
     */
    @RequestMapping(value = "/queryChannelProfile", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String queryChannelProfile(final HttpServletRequest request, final Long profileId,
            final Long custNo) {
        logger.debug("通道模板列表-查询 入参:profileId=" + profileId);
        return exec(() -> profileService.webQueryNotificationChannelProfile(profileId, custNo), "通道模板列表-查询 出错", logger);
    }

    /**
     * 通道模板-保存
     *
     * @return
     */
    @RequestMapping(value = "/saveChannelProfile", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String saveChannelProfile(final HttpServletRequest request, final Long channelProfileId,
            final Long custNo) {
        final Map<String, Object> anParam = Servlets.getParametersStartingWith(request, "");
        logger.debug("通道模板-保存 入参:anParam=" + anParam + " channelProfileId=" + channelProfileId);
        return exec(() -> profileService.webSaveNotificationChannelProfile(anParam, channelProfileId, custNo),
                "通道模板-保存 出错", logger);
    }

    /**
     * 通道模板变量规则-查询
     *
     * @return
     */
    @RequestMapping(value = "/queryProfileVariable", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String queryProfileVariable(final HttpServletRequest request, final Long channelProfileId,
            final Long custNo) {
        logger.debug("通道模板变量规则-查询 入参:channelProfileId=" + channelProfileId);

        return exec(() -> profileService.webQueryNotificationProfileVariable(channelProfileId, custNo),
                "通道模板变量规则-查询 出错", logger);
    }
}
