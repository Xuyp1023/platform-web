package com.betterjr.modules.document.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringReader;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//import com.betterjr.common.config.ParamNames;
import com.betterjr.common.data.KeyAndValueObject;
import com.betterjr.common.exception.BettjerIOException;
import com.betterjr.common.exception.BytterTradeException;
import com.betterjr.common.service.FreemarkerService;
import com.betterjr.common.service.SpringContextHolder;
import com.betterjr.common.utils.BetterStringUtils;
import com.betterjr.common.utils.FileUtils;
import com.betterjr.common.utils.MimeTypesHelper;
import com.betterjr.modules.document.entity.CustFileItem;
//import com.betterjr.modules.sys.service.SysConfigService;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.Pipeline;
import com.itextpdf.tool.xml.XMLWorker;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import com.itextpdf.tool.xml.html.Tags;
import com.itextpdf.tool.xml.parser.XMLParser;
import com.itextpdf.tool.xml.pipeline.css.CSSResolver;
import com.itextpdf.tool.xml.pipeline.css.CssResolverPipeline;
import com.itextpdf.tool.xml.pipeline.end.PdfWriterPipeline;
import com.itextpdf.tool.xml.pipeline.html.HtmlPipeline;
import com.itextpdf.tool.xml.pipeline.html.HtmlPipelineContext;

import java.util.*;

public abstract class CustFileClientUtils {
    private static final Logger logger = LoggerFactory.getLogger(CustFileClientUtils.class);

    /**
     * 将上传的文件持续化。
     * 
     * @param anFileInfo
     * @param anInput
     * @return
     */
    public static boolean saveFileStream(KeyAndValueObject anFileInfo, InputStream anInput) {
        File tmpFile = (File) anFileInfo.getValue();
        FileOutputStream outStream = null;
        try {
            outStream = new FileOutputStream(tmpFile);
            IOUtils.copy(anInput, outStream);
            return true;
        }
        catch (IOException ex) {
            tmpFile.delete();
            return false;
        }
        finally {
            IOUtils.closeQuietly(outStream);
        }
    }

    /**
     * 下载文件
     * 
     * @param response
     * @param anFileItem
     */
    public static void fileDownload(HttpServletResponse response, CustFileItem anFileItem,String basePath) {

        fileDownloadWithOpenType(response, anFileItem, null,basePath);
    }

    public static void fileDownloadWithOpenType(HttpServletResponse response, CustFileItem anFileItem, String anOpenType,String basePath) {
        OutputStream os = null;
        String msg = null;
        try {
//            String basePath = SysConfigService.getString(ParamNames.OPENACCO_FILE_DOWNLOAD_PATH);
            if (anFileItem != null) {
                File file = FileUtils.getRealFile(basePath + anFileItem.getFilePath());
                if (file != null) {
                    String openType = anOpenType;
                    if (BetterStringUtils.isBlank(openType)) {
                        if (anFileItem.isInner(MimeTypesHelper.getMimeType(anFileItem.getFileType()))) {
                            openType = "inline";
                        }
                        else {
                            openType = "attachment";
                        }
                    }
                    String fileName = anFileItem.getFileName();
                    StringBuilder sb = new StringBuilder(100);
                    sb.append(openType).append("; ").append("filename=").append(java.net.URLEncoder.encode(fileName, "UTF-8"));
                    os = response.getOutputStream();
                    response.setHeader("Content-Disposition", sb.toString());
                    response.setContentType(anFileItem.getFileType());
                    FileUtils.copyFile(file, os);
                    return;
                }
                else {
                    msg = "下载文件不存在！";
                }
            }
            else {
                msg = "没有获得下载文件的任何信息";
            }
        }
        catch (IOException e) {
            logger.error("下载文件失败，请检查；" + anFileItem, e);
            msg = "出现IO异常，请稍后再试!";
        }
        finally {
            if (msg != null) {
                response.reset();
                response.setContentType("text/html;UTF-8");
                try {
                    response.getWriter().append(msg);
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
            IOUtils.closeQuietly(os);
        }
    }

    public static void directExportPDF(String anOutFileName, Map<String, Object> anParam, HttpServletResponse response, String anModeFile,
            String anModeName) {
        FreemarkerService markerService = SpringContextHolder.getBean(FreemarkerService.class);
        StringBuffer data = markerService.processTemplateByFileNameUnderModule(anModeFile, anParam, anModeName);
        String msg = null;
        try {
            response.reset();
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "attachment; filename=" + java.net.URLEncoder.encode(anOutFileName, "UTF-8"));
            exportPDF(data, response.getOutputStream());
        }
        catch (IOException e) {
            msg = "出现文件异常，请稍后再试!";
        }
        catch (BettjerIOException e) {
            msg = e.getMessage();
        }
        finally {
            if (msg != null) {
                try {
                    response.reset();
                    response.setContentType("text/html;UTF-8");
                    response.getWriter().append(msg);
                }
                catch (IOException e) {
                    logger.error("directExportPDF can't output exception message", e);
                }
            }
        }
    }

    /**
     * 根据保存的文件信息，产生PDF文件。
     * 
     * @param anSb
     * @param anFileInfo
     * @return
     */
    public static boolean exportPDF(StringBuffer anSb, KeyAndValueObject anFileInfo) {
        File tmpFile = (File) anFileInfo.getValue();
        OutputStream out = null;
        try {
            out = new FileOutputStream(tmpFile);
            exportPDF(anSb, out);
            return true;
        }
        catch (BytterTradeException ex) {

            throw ex;
        }
        catch (Exception ex) {
            logger.error("exportPDF has error", ex);
            return false;
        }
        finally {
            IOUtils.closeQuietly(out);
        }
    }

    /**
     * 输出PDF文件
     * @param anSb
     * @param anOut
     */
    public static void exportPDF(StringBuffer anSb, OutputStream anOut) {
        Document document = new Document(PageSize.A4, 0, 0, 0, 0);
        document.setMargins(0, 0, 0, 0);
        System.out.println(anSb.toString());
        PdfWriter pdfwriter = null;
        try {
            pdfwriter = PdfWriter.getInstance(document, anOut);
            pdfwriter.setViewerPreferences(PdfWriter.HideToolbar);
            document.open();
            document.newPage();
            HtmlPipelineContext htmlContext = new HtmlPipelineContext(null);

            htmlContext.setTagFactory(Tags.getHtmlTagProcessorFactory());
            CSSResolver cssResolver = XMLWorkerHelper.getInstance().getDefaultCssResolver(true);
            Pipeline pipeline = new CssResolverPipeline(cssResolver, new HtmlPipeline(htmlContext, new PdfWriterPipeline(document, pdfwriter)));

            XMLWorker worker = new XMLWorker(pipeline, true);
            XMLParser p = new XMLParser(worker);
            StringReader reader = new StringReader(anSb.toString());
            p.parse(reader);
            p.flush();
        }
        catch (IOException | DocumentException ex) {
            throw new BytterTradeException(30002, "产生PDF报告文件出现异常，请稍后再试", ex);
        }
        finally {
            document.close();
            pdfwriter.close();
        }
    }
}