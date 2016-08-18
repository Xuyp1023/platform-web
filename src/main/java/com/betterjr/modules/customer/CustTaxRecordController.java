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
@RequestMapping("/Platform/CustTaxRecord")
public class CustTaxRecordController {

    private static final Logger logger = LoggerFactory.getLogger(CustTaxRecordController.class);
    
    @Reference(interfaceClass = ICustTaxService.class)
    private ICustTaxService custTaxDubboService;

    @RequestMapping(value = "/queryCustTaxRecord", method = RequestMethod.POST)
    public @ResponseBody String queryCustTaxRecord(Long custNo, String flag, int pageNum, int pageSize) {
        logger.info(",入参:custNo=" + custNo);
        try {
            return custTaxDubboService.webQueryTaxRecordList(custNo, flag, pageSize, pageNum);
        }
        catch (RpcException btEx) {
            logger.error(btEx.getMessage(), btEx);
            if (BytterException.isCauseBytterException(btEx)) {
                return AjaxObject.newError(btEx.getCause().getMessage()).toJson();
            }
            return AjaxObject.newError("纳税信息查询失败").toJson();
        }
        catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            return AjaxObject.newError("纳税信息查询失败").toJson();
        }
    }
    
    @RequestMapping(value = "/addCustTaxRecord", method = RequestMethod.POST)
    public @ResponseBody String addCustTaxRecord(HttpServletRequest request, String fileList) {
        Map<String, Object> anMap = Servlets.getParametersStartingWith(request, "");
        logger.info("纳税信息录入,入参:" + anMap.toString());
        try {
            return custTaxDubboService.webAddTaxRecord(anMap, fileList);
        }
        catch (RpcException btEx) {
            logger.error(btEx.getMessage(), btEx);
            if (BytterException.isCauseBytterException(btEx)) {
                return AjaxObject.newError(btEx.getCause().getMessage()).toJson();
            }
            return AjaxObject.newError("纳税信息录入失败").toJson();
        }
        catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            return AjaxObject.newError("纳税信息录入失败").toJson();
        }
    }
    
    @RequestMapping(value = "/saveCustTaxRecord", method = RequestMethod.POST)
    public @ResponseBody String saveCustTaxRecord(HttpServletRequest request, Long id, String fileList) {
        Map<String, Object> anMap = Servlets.getParametersStartingWith(request, "");
        logger.info("纳税信息录入,入参:" + anMap.toString());
        try {
            return custTaxDubboService.webSaveTaxRecord(anMap, id, fileList);
        }
        catch (RpcException btEx) {
            logger.error(btEx.getMessage(), btEx);
            if (BytterException.isCauseBytterException(btEx)) {
                return AjaxObject.newError(btEx.getCause().getMessage()).toJson();
            }
            return AjaxObject.newError("纳税信息保存失败").toJson();
        }
        catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            return AjaxObject.newError("纳税信息保存失败").toJson();
        }
    }
    
    @RequestMapping(value = "/saveDeleteCustTaxRecorde", method = RequestMethod.POST)
    public @ResponseBody String saveDeleteCustTaxRecorde(Long id) {
        logger.info("纳税信息删除,入参:id" + id);
        try {
            return custTaxDubboService.webSaveDeleteCustTaxRecord(id);
        }
        catch (RpcException btEx) {
            logger.error(btEx.getMessage(), btEx);
            if (BytterException.isCauseBytterException(btEx)) {
                return AjaxObject.newError(btEx.getCause().getMessage()).toJson();
            }
            return AjaxObject.newError("纳税信息删除失败").toJson();
        }
        catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            return AjaxObject.newError("纳税信息删除失败").toJson();
        }
    }
}
