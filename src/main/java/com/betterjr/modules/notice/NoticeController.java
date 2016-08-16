package com.betterjr.modules.notice;

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
    public @ResponseBody String queryUnreadNotice(HttpServletRequest request, int flag, int pageNum, int pageSize) {
        try {
            Map<String, Object> anParam = Servlets.getParametersStartingWith(request, "");
            logger.debug("未读公告列表-查询 入参:anParam=" + anParam);
            return noticeService.webQueryUnreadNotice(anParam, flag, pageNum, pageSize);
        }
        catch (final Exception e) {
            logger.error("未读公告列表-查询 出错", e);
            return AjaxObject.newError("未读公告列表-查询 出错").toJson();
        }
    }

    /**
     * 已读公告列表-查询
     * 
     * @return
     */
    @RequestMapping(value = "/queryReadNotice", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String queryReadNotice(HttpServletRequest request, int flag, int pageNum, int pageSize) {
        try {
            Map<String, Object> anParam = Servlets.getParametersStartingWith(request, "");
            logger.debug("已读公告列表-查询 入参:anParam=" + anParam);
            return noticeService.webQueryReadNotice(anParam, flag, pageNum, pageSize);
        }
        catch (final Exception e) {
            logger.error("已读公告列表-查询 出错", e);
            return AjaxObject.newError("已读公告列表-查询 出错").toJson();
        }
    }

    /**
     * 未读公告数量-查询
     * 
     * @return
     */
    @RequestMapping(value = "/countUnreadNotice", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String countUnreadNotice(HttpServletRequest request) {
        try {
            return noticeService.webCountUnreadNotice();
        }
        catch (final Exception e) {
            logger.error("未读公告数量-查询 出错", e);
            return AjaxObject.newError("未读公告数量-查询 出错").toJson();
        }
    }

    /**
     * 公告详情-查询
     * 
     * @return
     */
    @RequestMapping(value = "/findNotice", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String findNotice(HttpServletRequest request, Long id) {
        try {
            return noticeService.webFindNotice(id);
        }
        catch (final Exception e) {
            logger.error("公告详情-查询 出错", e);
            return AjaxObject.newError("公告详情-查询  出错").toJson();
        }
    }

    /**
     * 设置公告删除
     * 
     * @return
     */
    @RequestMapping(value = "/setDeletedNotice", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String setDeletedNotice(HttpServletRequest request, Long id, Long custNo) {
        try {
            logger.debug("设置公告删除 入参:id=" + id + " custNo=" + custNo);
            return noticeService.webSetDeletedNotice(id, custNo);
        }
        catch (final Exception e) {
            logger.error("设置公告删除 出错", e);
            return AjaxObject.newError("设置公告删除  出错").toJson();
        }
    }
    
    /**
     * 设置公告已读
     * 
     * @return
     */
    @RequestMapping(value = "/setReadNotice", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String setReadNotice(HttpServletRequest request, Long id, Long custNo) {
        try {
            logger.debug("设置公告已读 入参:id=" + id + " custNo=" + custNo);
            return noticeService.webSetReadNotice(id, custNo);
        }
        catch (final Exception e) {
            logger.error("设置公告已读 出错", e);
            return AjaxObject.newError("设置公告已读  出错").toJson();
        }
    }
    
    /**
     * 已读公告列表-查询
     * 
     * @return
     */
    @RequestMapping(value = "/queryNotice", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String queryNotice(HttpServletRequest request, int flag, int pageNum, int pageSize) {
        try {
            Map<String, Object> anParam = Servlets.getParametersStartingWith(request, "");
            logger.debug("已读公告列表-查询 入参:anParam=" + anParam);
            return noticeService.webQueryNotice(anParam, flag, pageNum, pageSize);
        }
        catch (final Exception e) {
            logger.error("已读公告列表-查询 出错", e);
            return AjaxObject.newError("已读公告列表-查询 出错").toJson();
        }
    }


    /**
     * 添加并发布公告
     * 
     * @return
     */
    @RequestMapping(value = "/addPublishNotice", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String addPublishNotice(HttpServletRequest request, String targetCust, String fileList) {
        try {
            Map<String, Object> anParam = Servlets.getParametersStartingWith(request, "");
            logger.debug("添加并发布公告 入参:anParam=" + anParam + " targetCust=" + targetCust + " fileList=" + fileList);
            return noticeService.webAddPublishNotice(anParam, targetCust, fileList);
        }
        catch (final Exception e) {
            logger.error("添加并发布公告 出错", e);
            return AjaxObject.newError("添加并发布公告  出错").toJson();
        }
    }
    
    /**
     * 添加并暂存公告
     * 
     * @return
     */
    @RequestMapping(value = "/addStoreNotice", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String addStoreNotice(HttpServletRequest request, String targetCust, String fileList) {
        try {
            Map<String, Object> anParam = Servlets.getParametersStartingWith(request, "");
            logger.debug("添加并暂存公告 入参:anParam=" + anParam + " targetCust=" + targetCust + " fileList=" + fileList);
            return noticeService.webAddStoreNotice(anParam, targetCust, fileList);
        }
        catch (final Exception e) {
            logger.error("添加并暂存公告 出错", e);
            return AjaxObject.newError("添加并暂存公告  出错").toJson();
        }
    }

    /**
     * 修改并发布公告
     * 
     * @return
     */
    @RequestMapping(value = "/savePublishNotice", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String savePublishNotice(HttpServletRequest request, Long id, String targetCust, String fileList) {
        try {
            Map<String, Object> anParam = Servlets.getParametersStartingWith(request, "");
            logger.debug("修改并发布公告 入参:anParam=" + anParam + " targetCust=" + targetCust + " fileList=" + fileList);
            return noticeService.webSavePublishNotice(anParam, id, targetCust, fileList);
        }
        catch (final Exception e) {
            logger.error("修改并发布公告  出错", e);
            return AjaxObject.newError("修改并发布公告  出错").toJson();
        }
    }
    
    /**
     * 修改并暂存公告
     * 
     * @return
     */
    @RequestMapping(value = "/saveStoreNotice", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String saveStoreNotice(HttpServletRequest request, Long id, String targetCust, String fileList) {
        try {
            Map<String, Object> anParam = Servlets.getParametersStartingWith(request, "");
            logger.debug("修改并暂存公告 入参:anParam=" + anParam + " targetCust=" + targetCust + " fileList=" + fileList);
            return noticeService.webSaveStoreNotice(anParam, id, targetCust, fileList);
        }
        catch (final Exception e) {
            logger.error("修改并暂存公告  出错", e);
            return AjaxObject.newError("修改并暂存公告  出错").toJson();
        }
    }

    /**
     * 发布公告
     * 
     * @return
     */
    @RequestMapping(value = "/publishNotice", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String publishNotice(HttpServletRequest request, Long id) {
        try {
            return noticeService.webPublishNotice(id);
        }
        catch (final Exception e) {
            logger.error("发布公告 出错", e);
            return AjaxObject.newError("发布公告  出错").toJson();
        }
    }

    /**
     * 撤销公告
     * 
     * @return
     */
    @RequestMapping(value = "/cancelNotice", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String cancelNotice(HttpServletRequest request, Long id) {
        try {
            return noticeService.webCancelNotice(id);
        }
        catch (final Exception e) {
            logger.error("撤销公告 出错", e);
            return AjaxObject.newError("撤销公告  出错").toJson();
        }
    }

    /**
     * 删除公告
     * 
     * @return
     */
    @RequestMapping(value = "/deleteNotice", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String deleteNotice(HttpServletRequest request, Long id) {
        try {
            return noticeService.webDeleteNotice(id);
        }
        catch (final Exception e) {
            logger.error("删除公告 出错", e);
            return AjaxObject.newError("删除公告  出错").toJson();
        }
    }
}
