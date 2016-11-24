package com.betterjr.modules.customer;

import static com.betterjr.common.web.ControllerExceptionHandler.exec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;
import com.betterjr.common.web.AjaxObject;
import com.betterjr.common.web.Servlets;

/**
 * 代录接口
 * 
 * @author wudy
 */
@Controller
@RequestMapping("/Platform/Instead2")
public class InsteadController2 {

    private static final Logger logger = LoggerFactory.getLogger(ChangeController.class);

    @Reference(interfaceClass = ICustInsteadService2.class)
    private ICustInsteadService2 insteadService;

    /**
     * PC代录申请-申请代录
     */
    @RequestMapping(value = "/addInsteadApply", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String addInsteadApply(final String custName, final Long operId, final String fileList) {
        return exec(() -> insteadService.webAddInsteadApply(custName, operId, fileList), "代录申请-添加代录出错", logger);
    }
    
    /**
     * 微信代录申请-申请代录
     */
    @RequestMapping(value = "/wechatAddInsteadApply", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String wechatAddInsteadApply(final String custName, final Long id, final String fileList) {
        final Object openIdObj = Servlets.getSession().getAttribute("wechat_openId");
        try {
            if (openIdObj != null) {
                final String openId = String.valueOf(openIdObj);
                insteadService.webWechatAddInsteadApply(custName, id, fileList);
            }
            return AjaxObject.newError("申请代录失败").toJson();
        }
        catch (final Exception e) {
            return AjaxObject.newError("申请代录失败").toJson();
        }
    }
    
    /**
     * 查询代录申请
     */
    @RequestMapping(value = "/findInsteadApplyByAccountTmpId", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String findInsteadApplyByAccountTmpId(final Long id) {
        return exec(() -> insteadService.webFindInsteadApplyByAccountTmpId(id), "查询代录申请出错", logger);
    }
}
