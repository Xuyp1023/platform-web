package com.betterjr.modules.notice;

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

@Controller
@RequestMapping(path = "/Platform/Notice")
public class NoticeController {
    private static final Logger logger = LoggerFactory.getLogger(NoticeController.class);

    @Reference(interfaceClass = INoticeService.class)
    private INoticeService noticeService;

    /**
     * 未读公告列表-查询
     *
     * @return
     */
    @RequestMapping(value = "/queryUnreadNotice", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String queryUnreadNotice(final HttpServletRequest request, final int flag, final int pageNum,
            final int pageSize) {
        final Map<String, Object> anParam = Servlets.getParametersStartingWith(request, "");
        logger.debug("未读公告列表-查询 入参:anParam=" + anParam);
        return exec(() -> noticeService.webQueryUnreadNotice(anParam, flag, pageNum, pageSize), "未读公告列表-查询 出错", logger);
    }

    /**
     * 已读公告列表-查询
     *
     * @return
     */
    @RequestMapping(value = "/queryReadNotice", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String queryReadNotice(final HttpServletRequest request, final int flag, final int pageNum,
            final int pageSize) {
        final Map<String, Object> anParam = Servlets.getParametersStartingWith(request, "");
        logger.debug("已读公告列表-查询 入参:anParam=" + anParam);
        return exec(() -> noticeService.webQueryReadNotice(anParam, flag, pageNum, pageSize), "已读公告列表-查询 出错", logger);
    }

    /**
     * 未读公告数量-查询
     *
     * @return
     */
    @RequestMapping(value = "/countUnreadNotice", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String countUnreadNotice(final HttpServletRequest request) {
        return exec(() -> noticeService.webCountUnreadNotice(), "未读公告数量-查询 出错", logger);
    }

    /**
     * 公告详情-查询
     *
     * @return
     */
    @RequestMapping(value = "/findNotice", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String findNotice(final HttpServletRequest request, final Long id) {
        return exec(() -> noticeService.webFindNotice(id), "公告详情-查询  出错", logger);
    }

    /**
     * 设置公告删除
     *
     * @return
     */
    @RequestMapping(value = "/setDeletedNotice", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String setDeletedNotice(final HttpServletRequest request, final Long id, final Long custNo) {
        logger.debug("设置公告删除 入参:id=" + id + " custNo=" + custNo);
        return exec(() -> noticeService.webSetDeletedNotice(id, custNo), "设置公告删除 出错", logger);
    }

    /**
     * 设置公告已读
     *
     * @return
     */
    @RequestMapping(value = "/setReadNotice", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String setReadNotice(final HttpServletRequest request, final Long id, final Long custNo) {
        logger.debug("设置公告已读 入参:id=" + id + " custNo=" + custNo);
        return exec(() -> noticeService.webSetReadNotice(id, custNo), "设置公告已读  出错", logger);
    }

    /**
     * 已读公告列表-查询
     *
     * @return
     */
    @RequestMapping(value = "/queryNotice", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String queryNotice(final HttpServletRequest request, final int flag, final int pageNum,
            final int pageSize) {
        final Map<String, Object> anParam = Servlets.getParametersStartingWith(request, "");
        logger.debug("已读公告列表-查询 入参:anParam=" + anParam);
        return exec(() -> noticeService.webQueryNotice(anParam, flag, pageNum, pageSize), "已读公告列表-查询 出错", logger);
    }

    /**
     * 添加并发布公告
     *
     * @return
     */
    @RequestMapping(value = "/addPublishNotice", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String addPublishNotice(final HttpServletRequest request, final String targetCust,
            final String fileList) {
        final Map<String, Object> anParam = Servlets.getParametersStartingWith(request, "");
        logger.debug("添加并发布公告 入参:anParam=" + anParam + " targetCust=" + targetCust + " fileList=" + fileList);
        return exec(() -> noticeService.webAddPublishNotice(anParam, targetCust, fileList), "添加并发布公告 出错", logger);
    }

    /**
     * 添加并暂存公告
     *
     * @return
     */
    @RequestMapping(value = "/addStoreNotice", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String addStoreNotice(final HttpServletRequest request, final String targetCust,
            final String fileList) {
        final Map<String, Object> anParam = Servlets.getParametersStartingWith(request, "");
        logger.debug("添加并暂存公告 入参:anParam=" + anParam + " targetCust=" + targetCust + " fileList=" + fileList);
        return exec(() -> noticeService.webAddStoreNotice(anParam, targetCust, fileList), "添加并暂存公告  出错", logger);
    }

    /**
     * 修改并发布公告
     *
     * @return
     */
    @RequestMapping(value = "/savePublishNotice", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String savePublishNotice(final HttpServletRequest request, final Long id,
            final String targetCust, final String fileList) {
        final Map<String, Object> anParam = Servlets.getParametersStartingWith(request, "");
        logger.debug("修改并发布公告 入参:anParam=" + anParam + " targetCust=" + targetCust + " fileList=" + fileList);
        return exec(() -> noticeService.webSavePublishNotice(anParam, id, targetCust, fileList), "修改并发布公告  出错", logger);
    }

    /**
     * 修改并暂存公告
     *
     * @return
     */
    @RequestMapping(value = "/saveStoreNotice", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String saveStoreNotice(final HttpServletRequest request, final Long id,
            final String targetCust, final String fileList) {
        final Map<String, Object> anParam = Servlets.getParametersStartingWith(request, "");
        logger.debug("修改并暂存公告 入参:anParam=" + anParam + " targetCust=" + targetCust + " fileList=" + fileList);
        return exec(() -> noticeService.webSaveStoreNotice(anParam, id, targetCust, fileList), "修改并暂存公告  出错", logger);
    }

    /**
     * 发布公告
     *
     * @return
     */
    @RequestMapping(value = "/publishNotice", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String publishNotice(final HttpServletRequest request, final Long id) {
        return exec(() -> noticeService.webPublishNotice(id), "发布公告  出错", logger);
    }

    /**
     * 撤销公告
     *
     * @return
     */
    @RequestMapping(value = "/cancelNotice", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String cancelNotice(final HttpServletRequest request, final Long id) {
        return exec(() -> noticeService.webCancelNotice(id), "撤销公告  出错", logger);
    }

    /**
     * 删除公告
     *
     * @return
     */
    @RequestMapping(value = "/deleteNotice", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String deleteNotice(final HttpServletRequest request, final Long id) {
        return exec(() -> noticeService.webDeleteNotice(id), "删除公告 出错", logger);
    }
}
