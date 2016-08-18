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
import com.betterjr.common.web.ControllerExceptionHandler;
import com.betterjr.common.web.ControllerExceptionHandler.ExceptionHandler;
import com.betterjr.common.web.Servlets;

@Controller
@RequestMapping(value = "/Platform/CustBankFlowRecord")
public class CustBankFlowRecordController {

private static final Logger logger = LoggerFactory.getLogger(CustBankFlowRecordController.class);
    
    @Reference(interfaceClass = ICustBankFlowService.class)
    private ICustBankFlowService custBankFlowService;
    
    @RequestMapping(value = "/addBankFlowRecord", method = RequestMethod.POST)
    public @ResponseBody String  addBankFlowRecord(HttpServletRequest request, String fileList) {
        Map<String, Object> anMap = Servlets.getParametersStartingWith(request, "");
        logger.info("银行流水增加,入参：" + anMap.toString());
        return ControllerExceptionHandler.exec(new ExceptionHandler() {
            public String handle() {
                return custBankFlowService.webAddBankFlowRecord(anMap, fileList);
            }
        }, "银行流水增加失败", logger);
    }
    
    @RequestMapping(value = "/queryBankFlowRecord", method = RequestMethod.POST)
    public @ResponseBody String queryBankFlowRecord(Long custNo, String flag, int pageNum, int pageSize) {
        logger.info("银行流水查询,入参：custNo=" + custNo);
        return ControllerExceptionHandler.exec(new ExceptionHandler() {
            public String handle() {
                return custBankFlowService.webQueryBankFlowRecordList(custNo, flag, pageNum, pageSize);
            }
        }, "订银行流水查询失败", logger);
    }
    
    @RequestMapping(value = "/saveDeleteBankFlowRecord", method = RequestMethod.POST)
    public @ResponseBody String saveDeleteBankFlowRecord(Long id) {
        logger.info("银行流水删除,入参：id=" + id);
        return ControllerExceptionHandler.exec(new ExceptionHandler() {
            public String handle() {
                return custBankFlowService.webSaveDeleteBankFlowRecord(id);
            }
        }, "银行流水删除失败", logger);
    }
}
