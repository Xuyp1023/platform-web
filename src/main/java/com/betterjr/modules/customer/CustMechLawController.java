package com.betterjr.modules.customer;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;
import com.betterjr.common.web.AjaxObject;

/**
 * 法人接口
 * 
 * @author liuwl
 *
 */
@Controller
@RequestMapping("/Platform/CustMechLaw")
public class CustMechLawController {
    private static final Logger logger = LoggerFactory.getLogger(CustMechLawController.class);
    
    @Reference(interfaceClass = ICustMechLawService.class)
    private ICustMechLawService custMechLawService;

    @RequestMapping(value = "/findLawInfo", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String findLawInfo(Long custNo) {
        return "";
    }

    @RequestMapping(value = "/addInstead", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String addInstead(HttpServletRequest request, Long custNo) {
        return "";
    }

    @RequestMapping(value = "/findInstead", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String findInstead(Long custNo, Long tempId) {
        return "";
    }

    @RequestMapping(value = "/addChangeApply", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String addChangeApply(HttpServletRequest request, Long custNo) {
        return "";
    }

    @RequestMapping(value = "/findChangeApply", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String findChangeApply(Long custNo, Long id) {
        return "";
    }

    @RequestMapping(value = "/queryChangeApply", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String queryChangeApply(Long custNo, int flag, int pageNum, int pageSize) {
        try {
            logger.debug("公司基本信息-变更列表-查询 入参:custNo=" + String.valueOf(custNo) + " flag=" + String.valueOf(flag) + " pageNum=" + String.valueOf(pageNum)
                    + " pageSize=" + String.valueOf(pageSize));
            return custMechLawService.webQueryChangeApply(custNo, flag, pageNum, pageSize);
        }
        catch (final Exception e) {
            logger.error("公司基本信息-变更列表-查询 错误", e);
            return AjaxObject.newError("公司基本信息-变更列表-查询 错误").toJson();
        }
    }
}
