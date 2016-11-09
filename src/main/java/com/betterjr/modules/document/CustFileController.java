package com.betterjr.modules.document;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.alibaba.dubbo.config.annotation.Reference;
import com.betterjr.common.config.ParamNames;
import com.betterjr.common.utils.BetterStringUtils;
import com.betterjr.common.utils.FileUtils;
import com.betterjr.common.web.AjaxObject;
import com.betterjr.modules.document.entity.CustFileItem;
import com.betterjr.modules.document.service.DataStoreService;
import com.betterjr.modules.document.utils.FileWebClientUtils;

@Controller
@RequestMapping(value = "/Platform/CustFile")
public class CustFileController {
    private static final Logger logger = LoggerFactory.getLogger(CustFileController.class);

    @Reference(interfaceClass = ICustFileService.class)
    private ICustFileService fileItemService;

    @Autowired
    private DataStoreService storeService;

    /**
     * 文件资料下载
     * 
     * @param id
     *            ；文件编号
     * @param response
     * @return
     */
    @RequestMapping(value = "/fileDownload")
    public @ResponseBody void fileDownload(Long id, HttpServletResponse response) {
        try {
            CustFileItem fileItem = fileItemService.findOne(id);
            FileWebClientUtils.fileDownload(storeService, response, fileItem);
        }
        catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        }
    }

    /**
     * 从batch中将文件删除
     * 
     * @param id
     *            ；文件编号
     * @param response
     * @return
     */
    @RequestMapping(value = "/fileDelete")
    public @ResponseBody String fileDelete(Long id, Long batchNo) {
        try {
            return fileItemService.webDeleteFileItem(id, batchNo);
        }
        catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            return AjaxObject.newError("文件删除失败!").toJson();
        }
    }

    /**
     * 文件列表
     * 
     * @param id
     *            ；文件编号
     * @param response
     * @return
     */
    @RequestMapping(value = "/fileListByBatchNo")
    public @ResponseBody String fileListByBatchNo(Long batchNo) {
        try {
            List<CustFileItem> fileItems = fileItemService.findCustFiles(batchNo);
            return AjaxObject.newOk("文件列表查询成功!", fileItems).toJson();
        }
        catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            return AjaxObject.newError("文件列表查询失败!").toJson();
        }
    }

    /**
     * 文件列表
     * 
     * @param id
     *            ；文件编号
     * @param response
     * @return
     */
    @RequestMapping(value = "/fileListByBatchNoList")
    public @ResponseBody String fileListByBatchNo(List<Long> batchNo) {
        try {
            List<CustFileItem> fileItemList = new ArrayList<CustFileItem>();
            for (Long anBathNo : batchNo) {
                List<CustFileItem> fileItems = fileItemService.findCustFiles(anBathNo);
                fileItemList.addAll(fileItems);
            }
            return AjaxObject.newOk("文件列表查询成功!", fileItemList).toJson();
        }
        catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            return AjaxObject.newError("文件列表查询失败!").toJson();
        }
    }

    /**
     * 文件资料下载
     * 
     * @param batchNo
     *            文件上传的批次号，方便于只批次中只有一个文件的情况
     * @param response
     * @return
     */
    @RequestMapping(value = "/fileDownloadByBatchNo")
    public @ResponseBody void fileDownloadByBatchNo(Long batchNo, HttpServletResponse response) {
        try {
            CustFileItem fileItem = fileItemService.findOneByBatchNo(batchNo);
            FileWebClientUtils.fileDownload(storeService, response, fileItem);
        }
        catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        }
    }

    /**
     * 
     * 文件资料上传
     * 
     * @param request
     * @param fileTypeName
     *            文件分类
     * @return
     */
    @RequestMapping(value = "/fileUpload", method = RequestMethod.POST)
    public @ResponseBody String fileUpload(HttpServletRequest request, String fileTypeName) {
        logger.info("用户文件上传.");
        try {
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
            MultipartFile mFile = multipartRequest.getFile("Filedata");
            String tmpFileName = mFile.getOriginalFilename();
            String tmpFileType = FileUtils.extractFileExt(tmpFileName);
            if (!FileUtils.isSupportedUploadFileType(tmpFileType)) {
                return AjaxObject.newError("不支持该文件类型，支持列表：jpg,jpeg,png,gif,doc,docx,pdf,xls,xlsx,zip,rar").toJson();
            }

            return storeService.webSaveStreamToStore(mFile.getInputStream(), fileTypeName, tmpFileName);
        }
        catch (Exception e) {
            logger.error("文件上传失败，失败原因：" + e.getMessage(), e);
            return AjaxObject.newError("上传文件失败，请检查").toJson();
        }
    }
}