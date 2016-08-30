package com.betterjr.modules.document;

import java.util.Enumeration;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.rpc.RpcException;
import com.betterjr.common.exception.BytterException;
import com.betterjr.common.web.AjaxObject;

@Controller
@RequestMapping(value = "/Platform/CustAduit")
public class CustFileAuditController {
    private static final Logger logger = LoggerFactory.getLogger(CustFileAuditController.class);

    @Reference(interfaceClass=ICustFileService.class)
    private ICustFileService custFileAuditService;

    @RequestMapping(value = "/addAduitFile", method = RequestMethod.POST)
    public @ResponseBody String addCustAduitFile(HttpServletRequest request, Long custNo) {
        logger.info("新增用户认证文件审核信息");
        try {
            Map<String, String[]> paramMap = request.getParameterMap();
            Enumeration<String> paramNames = request.getParameterNames();
            return custFileAuditService.webUpdateCustFileAuditInfo(paramMap, paramNames, custNo);
        }
        catch (RpcException btEx) {
            logger.error(btEx.getMessage(), btEx);
            if (BytterException.isCauseBytterException(btEx)) {
                return AjaxObject.newError(btEx.getCause().getMessage()).toJson();
            }
            return AjaxObject.newError("新增用户认证文件审核信息失败，请检查").toJson();
        }
        catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            return AjaxObject.newError("新增用户认证文件审核信息失败，请检查").toJson();
        }
    }

    @RequestMapping(value = "/queryAduitFile", method = RequestMethod.POST)
    public @ResponseBody String queryCustAduitFile(Long custNo) {
        logger.info("查询用户认证文件审核信息");

        try {
            return custFileAuditService.webFindCustFileAuditInfo(custNo);
        }
        catch (RpcException btEx) {
            logger.error(btEx.getMessage(), btEx);
            if (BytterException.isCauseBytterException(btEx)) {
                return AjaxObject.newError(btEx.getCause().getMessage()).toJson();
            }
            return AjaxObject.newError("查询用户认证文件审核信息失败，请检查").toJson();
        }
        catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            return AjaxObject.newError("查询用户认证文件审核信息失败，请检查").toJson();
        }
    }
    /*
     * 查找客户认证材料缺少的文件业务类型
     */

    @RequestMapping(value = "/checkCustFileByBusinFlag", method = RequestMethod.POST)
    public @ResponseBody String checkCustFileByBusinFlag(Long custNo, String agencyNo, String businFlag) {
        try {
            return custFileAuditService.webFindDeficiencyFileInfoList(custNo, agencyNo);
        }
        catch (RpcException btEx) {
            logger.error(btEx.getMessage(), btEx);
            if (BytterException.isCauseBytterException(btEx)) {
                return AjaxObject.newError(btEx.getCause().getMessage()).toJson();
            }
            return AjaxObject.newError("查找客户认证材料缺少的文件业务类型失败，请检查").toJson();
        }
        catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            return AjaxObject.newError("查找客户认证材料缺少的文件业务类型失败，请检查").toJson();
        }
    }
}