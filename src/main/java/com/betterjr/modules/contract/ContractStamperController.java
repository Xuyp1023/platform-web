// Copyright (c) 2014-2017 Bytter. All rights reserved.
// ============================================================================
// CURRENT VERSION
// ============================================================================
// CHANGE LOG
// V2.0 : 2017年4月25日, liuwl, creation
// ============================================================================
package com.betterjr.modules.contract;

import static com.betterjr.common.web.ControllerExceptionHandler.exec;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;
import com.betterjr.common.web.Servlets;

/**
 * @author liuwl
 *
 */
@Controller
@RequestMapping("/Platform/ContractStamper")
public class ContractStamperController {
    private static final Logger logger = LoggerFactory.getLogger(ContractStamperController.class);

    @Reference(interfaceClass = IContractStamperService.class)
    private IContractStamperService contractStamperService;

    @RequestMapping(value = "/queryOwnStamper", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String queryOwnStamper(final HttpServletRequest request, final Long custNo, final int flag,
            final int pageNum, final int pageSize) {
        return exec(() -> contractStamperService.webQueryOwnStamper(custNo, flag, pageNum, pageSize), "查询合同印章列表出错！",
                logger);
    }

    @RequestMapping(value = "/queryAllStamper", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String queryAllStamper(final HttpServletRequest request, final int flag, final int pageNum,
            final int pageSize) {
        final Map<String, Object> param = Servlets.getParametersStartingWith(request, "");
        logger.debug("入参:param=" + param);
        return exec(() -> contractStamperService.webQueryAllStamper(param, flag, pageNum, pageSize), "查询合同印章列表出错！",
                logger);
    }

    @RequestMapping(value = "/findCheckStamper", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String findCheckStamper(final HttpServletRequest request, final Long custNo) {
        final Map<String, Object> param = Servlets.getParametersStartingWith(request, "");
        logger.debug("入参:param=" + param);
        return exec(() -> contractStamperService.webFindCheckStamper(custNo), "检测合同是否创建合同印章出错！", logger);
    }

    @RequestMapping(value = "/findStamper", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String findStamper(final HttpServletRequest request, final Long id) {
        final Map<String, Object> param = Servlets.getParametersStartingWith(request, "");
        logger.debug("入参:param=" + param);
        return exec(() -> contractStamperService.webFindStamper(id), "查询合同印章出错！", logger);
    }

    @RequestMapping(value = "/saveAddOwnStamper", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String saveAddOwnStamper(final HttpServletRequest request, final String originFileId) {
        final Map<String, Object> param = Servlets.getParametersStartingWith(request, "");
        logger.debug("入参:param=" + param);
        return exec(() -> contractStamperService.webSaveAddOwnStamper(param, originFileId), "添加合同印章出错！", logger);
    }

    @RequestMapping(value = "/saveAddStamper", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String saveAddStamper(final HttpServletRequest request, final String originFileId,
            final String fileId) {
        final Map<String, Object> param = Servlets.getParametersStartingWith(request, "");
        logger.debug("入参:param=" + param);
        return exec(() -> contractStamperService.webSaveAddStamper(param, originFileId, fileId), "添加合同印章出错！", logger);
    }

    @RequestMapping(value = "/saveMakeStamper", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String saveMakeStamper(final HttpServletRequest request, final Long id, final String fileId) {
        final Map<String, Object> param = Servlets.getParametersStartingWith(request, "");
        logger.debug("入参:param=" + param);
        return exec(() -> contractStamperService.webSaveMakeStamper(id, fileId), "制作合同印章出错！", logger);
    }
}
