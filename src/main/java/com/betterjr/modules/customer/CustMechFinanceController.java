package com.betterjr.modules.customer;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.rpc.RpcException;
import com.betterjr.common.exception.BytterException;
import com.betterjr.common.web.AjaxObject;
import com.betterjr.common.web.Servlets;

@Controller
@RequestMapping("/Platform/CustMechFinance")
public class CustMechFinanceController {

    private static final Logger logger = LoggerFactory.getLogger(CustMechFinanceController.class);

    @Reference(interfaceClass = ICustMechFinanceService.class)
    private ICustMechFinanceService custMechFinanceService;

    @RequestMapping(value = "/addFinanceInfo", method = RequestMethod.POST)
    public @ResponseBody String addFinanceInfo(HttpServletRequest request, Long custNo, String fileList) {
        Map<String, Object> anMap = Servlets.getParametersStartingWith(request, "");
        try {
            return custMechFinanceService.webAddFinanceInfo(anMap, custNo, fileList);
        }
        catch (RpcException btEx) {
            logger.error(btEx.getMessage(), btEx);
            if (BytterException.isCauseBytterException(btEx)) {
                return AjaxObject.newError(btEx.getCause().getMessage()).toJson();
            }
            return AjaxObject.newError("财务信息添加失败").toJson();
        }
        catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            return AjaxObject.newError("财务信息添加失败").toJson();
        }
    }

    @RequestMapping(value = "/findFinanceInfo", method = RequestMethod.POST)
    public @ResponseBody String findFinanceInfo(HttpServletRequest request, Long custNo, Long id) {
        try {
            return custMechFinanceService.webFindFinanceInfo(custNo, id);
        }
        catch (RpcException btEx) {
            logger.error(btEx.getMessage(), btEx);
            if (BytterException.isCauseBytterException(btEx)) {
                return AjaxObject.newError(btEx.getCause().getMessage()).toJson();
            }
            return AjaxObject.newError("财务信息查询失败").toJson();
        }
        catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            return AjaxObject.newError("财务信息查询失败").toJson();
        }
    }

    @RequestMapping(value = "/queryFinanceList", method = RequestMethod.POST)
    public @ResponseBody String webQueryFinanceList(Long custNo, String flag, int pageNum, int pageSize) {
        try {
            return custMechFinanceService.webQueryFinanceList(custNo, flag, pageNum, pageSize);
        }
        catch (RpcException btEx) {
            logger.error(btEx.getMessage(), btEx);
            if (BytterException.isCauseBytterException(btEx)) {
                return AjaxObject.newError(btEx.getCause().getMessage()).toJson();
            }
            return AjaxObject.newError("财务上传记录查询失败").toJson();
        }
        catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            return AjaxObject.newError("财务上传记录查询失败").toJson();
        }
    }

    @RequestMapping(value = "/saveFinanceInfo", method = RequestMethod.POST)
    public @ResponseBody String webSaveFinanceInfo(HttpServletRequest request, Long custNo, Long id, String fileList) {
        Map<String, Object> anMap = Servlets.getParametersStartingWith(request, "");
        try {
            return custMechFinanceService.webSaveFinanceInfo(anMap, custNo, id, fileList);
        }
        catch (RpcException btEx) {
            if (BytterException.isCauseBytterException(btEx)) {
                return AjaxObject.newError(btEx.getCause().getMessage()).toJson();
            }
            return AjaxObject.newError("财务上传记录保存失败").toJson();
        }
        catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            return AjaxObject.newError("财务上传记录保存失败").toJson();
        }
    }

    @RequestMapping(value = "/saveDeleteFinanceInfo", method = RequestMethod.POST)
    public @ResponseBody String saveDeleteFinanceInfo(Long id) {
        try {
            return custMechFinanceService.webSaveDeleteFinanceInfo(id);
        }
        catch (RpcException btEx) {
            if (BytterException.isCauseBytterException(btEx)) {
                return AjaxObject.newError(btEx.getCause().getMessage()).toJson();
            }
            return AjaxObject.newError("财务上传记录删除失败").toJson();
        }
        catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            return AjaxObject.newError("财务上传记录删除失败").toJson();
        }
    }
}
