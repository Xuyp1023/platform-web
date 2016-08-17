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
@RequestMapping("/Platform/CustFinancing")
public class CustFinancingController {
    
    private static final Logger logger = LoggerFactory.getLogger(CustFinancingController.class);
    
    @Reference(interfaceClass = ICustFinancingService.class)
    private ICustFinancingService custFinancingService;
    
    @RequestMapping(value = "/addFinancing", method = RequestMethod.POST)
    public @ResponseBody String addFinancing(HttpServletRequest request) {
        Map<String, Object> anMap = Servlets.getParametersStartingWith(request, "");
        logger.info("融资信息添加,入参：" + anMap.toString());
        return ControllerExceptionHandler.exec(new ExceptionHandler() {
            public String handle() {
                return custFinancingService.webAddFinancing(anMap);
            }
        }, "融资信息添加失败", logger);
    }
    
    @RequestMapping(value = "/queryFinancingList", method = RequestMethod.POST)
    public @ResponseBody String queryFinancingList(Long custNo, String flag, int pageNum, int pageSize) {
        logger.info("融资信息查询,入参：custNo=" + custNo);
        return ControllerExceptionHandler.exec(new ExceptionHandler() {
            public String handle() {
                return custFinancingService.webQueryFinancingList(custNo, flag, pageNum, pageSize);
            }
        }, "融资信息查询失败", logger);
    }
    
    @RequestMapping(value = "/saveFinancing", method = RequestMethod.POST)
    public @ResponseBody String saveFinancing(HttpServletRequest request, Long id) {
        Map<String, Object> anMap = Servlets.getParametersStartingWith(request, "");
        logger.info("融资信息保存,入参：" + anMap.toString());
        return ControllerExceptionHandler.exec(new ExceptionHandler() {
            public String handle() {
                return custFinancingService.webSaveFinancing(anMap, id);
            }
        }, "融资信息保存失败", logger);
    }
    
    @RequestMapping(value = "/saveDeleteFinancing", method = RequestMethod.POST)
    public @ResponseBody String saveDeleteFinancing(Long id) {
        logger.info("融资信息删除,入参:id=" + id);
        return ControllerExceptionHandler.exec(new ExceptionHandler() {
            public String handle() {
                return custFinancingService.webSaveDeleteFinancing(id);
            }
        }, "融资信息删除失败", logger);
    }
    
    

}
