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
@RequestMapping("/Platform/CustMechTradeRecord")
public class CustMechTradeRecordController {
    private static final Logger logger = LoggerFactory.getLogger(CustMechTradeRecordController.class);

    @Reference(interfaceClass = ICustMechTradeService.class)
    private ICustMechTradeService custMechTradeService;

    @RequestMapping(value = "/addTradeRecord", method = RequestMethod.POST)
    public @ResponseBody String addTradeRecord(HttpServletRequest request, String fileList) {
        Map<String, Object> anMap = Servlets.getParametersStartingWith(request, "");
        logger.info("贸易信息添加,入参：" + anMap.toString());
        return ControllerExceptionHandler.exec(new ExceptionHandler() {
            @Override
            public String handle() {
                return custMechTradeService.webAddTradeRecord(anMap, fileList);
            }
        }, "贸易信息添加失败", logger);
    }

    @RequestMapping(value = "/queryTradeRecord", method = RequestMethod.POST)
    public @ResponseBody String queryTradeRecord(Long custNo, String flag, int pageNum, int pageSize) {
        logger.info("贸易信息查询,入参：custNo" + custNo);
        return ControllerExceptionHandler.exec(new ExceptionHandler() {
            @Override
            public String handle() {
                return custMechTradeService.webQueryTradeRecordList(custNo, flag, pageNum, pageSize);
            }
        }, "贸易信息查询失败", logger);
    }

    @RequestMapping(value = "/saveDeleteTradeRecord", method = RequestMethod.POST)
    public @ResponseBody String saveDeleteTradeRecord(Long id) {
        logger.info("贸易信息添加,入参：id=" + id);
        return ControllerExceptionHandler.exec(new ExceptionHandler() {
            @Override
            public String handle() {
                return custMechTradeService.webSaveDeleteTradeRecord(id);
            }
        }, "贸易信息添加失败", logger);
    }
}
