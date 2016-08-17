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
import com.betterjr.common.web.Servlets;
import com.betterjr.common.web.ControllerExceptionHandler.ExceptionHandler;

@Controller
@RequestMapping("/Platform/CustMechCooperation")
public class CustMechCooperationController {

    private static final Logger logger = LoggerFactory.getLogger(CustMechCooperationController.class);

    @Reference(interfaceClass = ICustMechCooperationService.class)
    private ICustMechCooperationService custCooperationDuuboService;

    @RequestMapping(value = "/addCooperation", method = RequestMethod.POST)
    public @ResponseBody String addCooperation(HttpServletRequest request) {
        Map<String, Object> anMap = Servlets.getParametersStartingWith(request, "");
        logger.info("合作企业录入,入参:" + anMap.toString());
        return ControllerExceptionHandler.exec(new ExceptionHandler() {
            public String handle() {
                return custCooperationDuuboService.webAddCooperation(anMap);
            }
        }, "合作企业录入失败", logger);
    }

    @RequestMapping(value = "/queryCooperationList", method = RequestMethod.POST)
    public @ResponseBody String queryCooperationList(Long custNo, String flag, int pageNum, int pageSize) {
        logger.info("合作企业查询,入参:custNo=" + custNo);
        return ControllerExceptionHandler.exec(new ExceptionHandler() {
            public String handle() {
                return custCooperationDuuboService.webQueryCooperationList(custNo, flag, pageNum, pageSize);
            }
        }, "合作企业查询失败", logger);
    }

    @RequestMapping(value = "/saveCooperation", method = RequestMethod.POST)
    public @ResponseBody String saveCooperation(HttpServletRequest request, Long id) {
        Map<String, Object> anMap = Servlets.getParametersStartingWith(request, "");
        logger.info("合作企业保存,入参:id=" + id + anMap.toString());
        return ControllerExceptionHandler.exec(new ExceptionHandler() {
            public String handle() {
                return custCooperationDuuboService.webSaveCooperation(anMap, id);
            }
        }, "合作企业保存失败", logger);
    }
    
    @RequestMapping(value = "/saveDeleteCooperation", method = RequestMethod.POST)
    public @ResponseBody String saveDeleteCooperation(Long id) {
        logger.info("合作企业删除,入参:id=" + id);
        return ControllerExceptionHandler.exec(new ExceptionHandler() {
            public String handle() {
                return custCooperationDuuboService.webSaveDeleteCooperation(id);
            }
        }, "合作企业删除失败", logger);
    }
}
