// Copyright (c) 2014-2017 Bytter. All rights reserved.
// ============================================================================
// CURRENT VERSION
// ============================================================================
// CHANGE LOG
// V2.0 : 2017年4月19日, liuwl, creation
// ============================================================================
package com.betterjr.modules.base;

import static com.betterjr.common.web.ControllerExceptionHandler.exec;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;

/**
 * @author liuwl
 *
 */
@Controller
@RequestMapping("/Platform/BusinessType")
public class BusinessTypeController {
    private static final Logger logger = LoggerFactory.getLogger(BusinessTypeController.class);

    @Reference(interfaceClass = IBusinessTypeService.class)
    private IBusinessTypeService businessTypeService;

    @RequestMapping(value = "/queryList", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String queryList(final HttpServletRequest request) {
        return exec(() -> businessTypeService.webQueryTypeList(), "查询业务类型列表出错！", logger);
    }

    @RequestMapping(value = "/querySimpleList", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String querySimpleList(final HttpServletRequest request) {
        return exec(() -> businessTypeService.webQuerySimpleTypeList(), "查询业务类型列表出错！", logger);
    }
}
