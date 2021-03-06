package com.betterjr.modules.document;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
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
import com.betterjr.common.exception.BytterTradeException;
import com.betterjr.common.utils.FileUtils;
import com.betterjr.common.web.AjaxObject;
import com.betterjr.common.web.ControllerExceptionHandler;
import com.betterjr.common.web.ControllerExceptionHandler.ExceptionHandler;
import com.betterjr.modules.document.entity.CustFileItem;
import com.betterjr.modules.document.entity.CustResolveFile;
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

    @Reference(interfaceClass = IAgencyAuthFileGroupService.class)
    private IAgencyAuthFileGroupService authFileGroupService;

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
     * 创建解析文件的日志，并且通过消息解析文件
     * @param request
     * @param custNo
     * @param coreCustNo
     * @param id
     * @param infoType 要解析的文件的类型   解析的文件类型1订单 2票据  3应收账款 4发票 5 合同
     * @return
     */
    @RequestMapping(value = "/saveResolveFile", method = RequestMethod.POST)
    public @ResponseBody String saveResolveFile(HttpServletRequest request, Long custNo, Long coreCustNo, Long id,
            String infoType) {

        try {
            logger.info("文件解析添加日志记录,入参：" + custNo.toString() + " " + coreCustNo.toString() + "   " + id.toString());

            CustFileItem fileItem = fileItemService.findOneAndButchId(id);
            if (fileItem == null || StringUtils.isBlank(fileItem.getFileType())) {
                return AjaxObject.newError("文件解析日志插入失败,日志").toJson();
            }
            if (!(fileItem.getFileType().equals("xls") || fileItem.getFileType().equals("xlsx"))) {
                return AjaxObject.newError("文件解析失败，解析的文件应该是excel").toJson();
            }
            CustResolveFile resolveFile = new CustResolveFile();
            resolveFile.setBusinStatus("0");
            resolveFile.setCoreCustNo(coreCustNo);
            resolveFile.setCustNo(custNo);
            resolveFile.setFileItemId(id);
            resolveFile.setBatchNo(fileItem.getBatchNo());
            resolveFile.setFileLength(fileItem.getFileLength());
            resolveFile.setFileType(fileItem.getFileType());
            resolveFile.setInfoType(infoType);
            resolveFile = fileItemService.webSaveAddResolveFile(resolveFile);
            // 发送MQ消息
            Long timetemp = System.currentTimeMillis();
            logger.info("文件解析添加日志记录,发送消息开始时间" + timetemp);
            boolean flag = sendResolveMessage(resolveFile);
            logger.info("文件解析添加日志记录,发送消息结束时间时间" + System.currentTimeMillis() + "   一共耗时："
                    + (System.currentTimeMillis() - timetemp) + "");
            if (flag) {
                return AjaxObject.newOk("文件解析日志插入成功", resolveFile).toJson();
            } else {
                return AjaxObject.newError("文件解析日志插入成功,但消息发送失败,日志ID：" + resolveFile.getId()).toJson();
            }

        }
        catch (Exception ex) {
            logger.error(ex.getMessage(), "文件解析添加日志记录" + ex);
            return AjaxObject.newError("文件解析失败!!!").toJson();
        }

    }

    @RequestMapping(value = "/findResolveFileById", method = RequestMethod.POST)
    public @ResponseBody String findResolveFileById(Long id) {

        logger.info("融资资料信息查询,入参：id=" + id);
        return ControllerExceptionHandler.exec(new ExceptionHandler() {
            @Override
            public String handle() {
                return fileItemService.webfindResolveFile(id);
            }
        }, "融资资料信息查询失败", logger);

    }

    /**
     * 发送解析文件的类型
     * @param anResolveFile
     */
    private boolean sendResolveMessage(CustResolveFile anResolveFile) {

        return fileItemService.sendResolveMessage(anResolveFile);

    }

    /**
     * 文件批量下载
     */
    @RequestMapping(value = "/fileMultipleDownload")
    public @ResponseBody void fileMultipleDownload(String fileIdList, HttpServletResponse response, String fileName) {
        try {
            List<CustFileItem> fileItemList = fileItemService.findFileListByIds(fileIdList.split(","));
            FileWebClientUtils.fileMultipleDownload(storeService, response, fileItemList, fileName);
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
     * @throws IOException 
     */
    @RequestMapping(value = "/fileUpload", method = RequestMethod.POST)
    public void fileUpload(HttpServletRequest request, HttpServletResponse anResponse, String fileTypeName)
            throws IOException {
        logger.info("用户文件上传.");
        String tmpResult;
        try {
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
            MultipartFile mFile = multipartRequest.getFile("Filedata");
            String tmpFileName = mFile.getOriginalFilename();
            String tmpFileType = FileUtils.extractFileExt(tmpFileName);
            if (!FileUtils.isSupportedUploadFileType(tmpFileType)) {
                tmpResult = AjaxObject.newError("不支持该文件类型，支持列表：jpg,jpeg,png,gif,doc,docx,pdf,xls,xlsx,zip,rar")
                        .toJson();
            } else {
                tmpResult = storeService.webSaveStreamToStore(mFile.getInputStream(), fileTypeName, tmpFileName);
            }
        }
        catch (BytterTradeException e) {
            logger.error("文件上传失败，失败原因：" + e.getMessage(), e);
            tmpResult = AjaxObject.newError(e.getMessage()).toJson();
        }
        catch (Exception e) {
            logger.error("文件上传失败，失败原因：" + e.getMessage(), e);
            tmpResult = AjaxObject.newError("上传文件失败，请检查").toJson();
        }
        anResponse.setContentType("text/html;charset=UTF-8");
        anResponse.getWriter().write(tmpResult);
        anResponse.flushBuffer();
    }

    /**
     * 文件资料下载
     * 
     * @param batchNo
     *            文件上传的批次号，方便于只批次中只有一个文件的情况
     * @param response
     * @return
     */
    @RequestMapping(value = "/findFileTypePermitInfo")
    public @ResponseBody String findFileTypePermitInfo(String fileTypeName) {
        return ControllerExceptionHandler.exec(new ExceptionHandler() {
            @Override
            public String handle() {
                return authFileGroupService.webFindFileTypePermitInfo(fileTypeName);
            }
        }, "查询允许的文件类型信息失败！", logger);
    }
}