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
import com.betterjr.common.web.AjaxObject;
import com.betterjr.common.web.Servlets;
import com.betterjr.modules.account.dubbo.interfaces.ICustInfoService;

/**
 * 
 * @author liuwl
 *
 */
@Controller
@RequestMapping("/Platform/CustMechBase")
public class CustMechBaseController {
    private static final Logger logger = LoggerFactory.getLogger(CustMechBaseController.class);

    @Reference(interfaceClass = ICustInfoService.class)
    private ICustInfoService custInfoService;

    @Reference(interfaceClass = ICustMechBaseService.class)
    private ICustMechBaseService custMechBaseService;

    /**
     * 当前客户公司列表-查询
     * 
     * @return
     */
    @RequestMapping(value = "/queryCustList", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String queryCustList() {
        try {
            return custInfoService.webQueryCustInfo();
        }
        catch (final Exception e) {
            logger.error("公司列表-查询出错", e);
            return AjaxObject.newError("公司列表-查询出错").toJson();
        }
    }

    /**
     * 公司基本信息-查询
     * 
     * @param custNo
     * @return
     */
    @RequestMapping(value = "/findBaseInfo", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String findBaseInfo(Long custNo) {
        try {
            logger.debug("入参:custNo=" + String.valueOf(custNo));
            return custMechBaseService.webFindBaseInfo(custNo);
        }
        catch (final Exception e) {
            logger.error("公司基本信息-查询 错误", e);
            return AjaxObject.newError("公司基本信息-查询 错误").toJson();
        }
    }

    /**
     * 公司基本信息-代录-添加
     * 
     * @param custNo
     * @return
     */
    @RequestMapping(value = "/addInsteadRecord", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String addInsteadRecord(HttpServletRequest request, Long insteadRecordId) {
        try {
            final Map<String, Object> reqParam = Servlets.getParametersStartingWith(request, "");
            logger.debug("公司基本信息-代录-添加 入参:reqParam=" + reqParam.toString() + " insteadRecordId=" + String.valueOf(insteadRecordId));

            return custMechBaseService.webAddInsteadRecord(reqParam, insteadRecordId);
        }
        catch (final Exception e) {
            logger.error("公司基本信息-代录-添加 错误", e);
            return AjaxObject.newError("公司基本信息-代录-添加 错误").toJson();
        }
    }

    /**
     * 公司基本信息-代录-查询
     * 
     * @param custNo
     * @return
     */
    @RequestMapping(value = "/findInsteadRecord", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String findInsteadRecord(Long id) {
        try {
            logger.debug("公司基本信息-代录-查询 入参:id=" + String.valueOf(id));

            return custMechBaseService.webFindInsteadRecord(id);
        }
        catch (final Exception e) {
            logger.error("公司基本信息-代录-查询  错误", e);
            return AjaxObject.newError("公司基本信息-代录-查询  错误").toJson();
        }
    }

    /**
     * 公司基本信息-变更-添加
     * 
     * @param custNo
     * @return
     */
    @RequestMapping(value = "/addChangeApply", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String addChangeApply(HttpServletRequest request, Long custNo) {
        try {
            final Map<String, Object> reqParam = Servlets.getParametersStartingWith(request, "");
            logger.debug("公司基本信息-变更-添加 入参:reqParam=" + reqParam.toString() + " custNo=" + String.valueOf(custNo));

            return custMechBaseService.webAddChangeApply(reqParam, custNo);
        }
        catch (final Exception e) {
            logger.error("公司基本信息-变更-添加 错误", e);
            return AjaxObject.newError("公司基本信息-变更-添加 错误").toJson();
        }
    }

    /**
     * 公司基本信息-变更-查询
     * 
     * @param custNo
     * @return
     */
    @RequestMapping(value = "/findChangeApply", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String findChangeApply(Long id) {
        try {
            logger.debug("公司基本信息-变更-查询 入参: id=" + String.valueOf(id));
            return custMechBaseService.webFindChangeApply(id);
        }
        catch (final Exception e) {
            logger.error("公司基本信息-变更-查询 错误", e);
            return AjaxObject.newError("公司基本信息-变更-查询 错误").toJson();
        }
    }

    /**
     * 公司基本信息-变更列表-查询
     * 
     * @param custNo
     * @return
     */
    @RequestMapping(value = "/queryChangeApply", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String queryChangeApply(Long custNo, int flag, int pageNum, int pageSize) {
        try {
            logger.debug("公司基本信息-变更列表-查询 入参:custNo=" + String.valueOf(custNo) + " flag=" + String.valueOf(flag) + " pageNum=" + String.valueOf(pageNum)
                    + " pageSize=" + String.valueOf(pageSize));
            return custMechBaseService.webQueryChangeApply(custNo, flag, pageNum, pageSize);
        }
        catch (final Exception e) {
            logger.error("公司基本信息-变更列表-查询 错误", e);
            return AjaxObject.newError("公司基本信息-变更列表-查询 错误").toJson();
        }
    }
}
