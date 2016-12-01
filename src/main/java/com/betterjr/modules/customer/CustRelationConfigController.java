package com.betterjr.modules.customer;

import static com.betterjr.common.web.ControllerExceptionHandler.exec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;
import com.betterjr.common.utils.BTAssert;

@Controller
@RequestMapping("/Platform/CustRelationConfig")
public class CustRelationConfigController {
    
    private static final Logger logger = LoggerFactory.getLogger(CustRelationConfigController.class);
    
    @Reference(interfaceClass=ICustRelationConfigService.class)
    private ICustRelationConfigService custRelationConfigService;
    
    @RequestMapping(value = "/findCustType", method = RequestMethod.POST)
    public @ResponseBody String findCustType() {
        return exec(() -> custRelationConfigService.webFindCustType(), "查询需要选择的客户类型", logger);
    }
    
    @RequestMapping(value = "/findCustInfo", method = RequestMethod.POST)
    public @ResponseBody String findCustInfo(String custType,Long custNo,String custName) {
        logger.info("添加客户关系，入参：custType="+custType+"，custNo="+custNo+"，custName："+custName);
        return exec(() -> custRelationConfigService.webFindCustInfo(custType,custNo,custName), "查询客户信息", logger);
    }
    
    @RequestMapping(value = "/addCustRelation", method = RequestMethod.POST)
    public @ResponseBody String addCustRelation(String custType,Long custNo,String relationCustStr) {
        logger.info("添加客户关系，入参：custType="+custType+"，custNo="+custNo+"，relationCustStr="+relationCustStr);
        return exec(() -> custRelationConfigService.webAddCustRelation(custType, custNo, relationCustStr), "添加客户关系", logger);
    }
    
    @RequestMapping(value = "/queryCustRelation", method = RequestMethod.POST)
    public @ResponseBody String queryCustRelation(Long custNo,String flag,int pageNum,int pageSize) {
        logger.info("添加客户关系，入参：custNo="+custNo);
        return exec(() -> custRelationConfigService.webQueryCustRelation(custNo,flag,pageNum,pageSize,""), "分页查询客户关系信息", logger);
    }
    
    // 查询当前客户的类型
    @RequestMapping(value = "/findCustTypeByLogin", method = RequestMethod.POST)
    public @ResponseBody String findCustTypeByLogin() {
        return exec(() -> custRelationConfigService.webFindCustTypeByCustNo(), "分页查询客户关系信息", logger);
    }
    
    
}