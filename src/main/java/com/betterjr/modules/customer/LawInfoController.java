package com.betterjr.modules.customer;

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
 * 法人接口
 * 
 * @author liuwl
 *
 */
@Controller
@RequestMapping("/Platform/LawInfo")
public class LawInfoController {
    private static final Logger logger = LoggerFactory.getLogger(LawInfoController.class);

    @Reference(interfaceClass = ICustMechLawService.class)
    private ICustMechLawService lawService;

    @RequestMapping(value = "/findLawInfo", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String findLawInfo(Long custNo) {
        logger.debug("入参:custNo=" + String.valueOf(custNo));
        return exec(() -> lawService.webFindLawInfo(custNo), "法人信息-查询详情 错误", logger);
    }

    @RequestMapping(value = "/addChangeApply", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String addChangeApply(HttpServletRequest request, String fileList) {
        final Map<String, Object> reqParam = Servlets.getParametersStartingWith(request, "");
        logger.debug("法人信息-变更申请 入参:reqParam=" + reqParam.toString() + " fileList=" + fileList);

        return exec(() -> lawService.webAddChangeApply(reqParam, fileList), "法人信息-变更申请 错误", logger);
    }

    /**
     * 法人信息-变更修改
     * 
     * @param custNo
     * @return
     */
    @RequestMapping(value = "/saveChangeApply", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String saveChangeApply(HttpServletRequest request, Long applyId, String fileList) {
        final Map<String, Object> reqParam = Servlets.getParametersStartingWith(request, "");
        logger.debug("法人信息-变更修改 入参:reqParam=" + reqParam.toString() + " applyId=" + String.valueOf(applyId)
                + " fileList=" + fileList);

        return exec(() -> lawService.webSaveChangeApply(reqParam, applyId, fileList), "法人信息-变更修改 错误", logger);
    }

    /**
     * 法人信息-变更详情
     * 
     * @param custNo
     * @return
     */
    @RequestMapping(value = "/findChangeApply", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String findChangeApply(Long id) {
        logger.debug("法人信息-变更详情 入参: id=" + String.valueOf(id));
        return exec(() -> lawService.webFindChangeApply(id), "法人信息-变更详情 错误", logger);
    }

    @RequestMapping(value = "/queryChangeApply", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String queryChangeApply(Long custNo, int flag, int pageNum, int pageSize) {
        logger.debug("法人信息-变更列表-查询 入参:custNo=" + String.valueOf(custNo) + " flag=" + String.valueOf(flag) + " pageNum="
                + String.valueOf(pageNum) + " pageSize=" + String.valueOf(pageSize));
        return exec(() -> lawService.webQueryChangeApply(custNo, flag, pageNum, pageSize), "法人信息-变更列表-查询 错误", logger);
    }

    /**
     * 法人信息-添加代录
     * 
     * @param custNo
     * @return
     */
    @RequestMapping(value = "/addInsteadRecord", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String addInsteadRecord(HttpServletRequest request, Long insteadRecordId, String fileList) {
        final Map<String, Object> reqParam = Servlets.getParametersStartingWith(request, "");
        logger.debug("法人信息-添加代录 入参:reqParam=" + reqParam.toString() + " insteadRecordId="
                + String.valueOf(insteadRecordId) + " fileList=" + fileList);

        return exec(() -> lawService.webAddInsteadRecord(reqParam, insteadRecordId, fileList), "法人信息-添加代录 错误", logger);
    }

    /**
     * 法人信息-代录修改
     * 
     * @param custNo
     * @return
     */
    @RequestMapping(value = "/saveInsteadRecord", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String saveInsteadRecord(HttpServletRequest request, Long insteadRecordId, String fileList) {
        final Map<String, Object> reqParam = Servlets.getParametersStartingWith(request, "");
        logger.debug("法人信息-代录修改 入参:reqParam=" + reqParam.toString() + " insteadRecordId="
                + String.valueOf(insteadRecordId) + " fileList=" + fileList);

        return exec(() -> lawService.webSaveInsteadRecord(reqParam, insteadRecordId, fileList), "法人信息-代录修改 错误", logger);
    }

    /**
     * 法人信息-代录详情
     * 
     * @param custNo
     * @return
     */
    @RequestMapping(value = "/findInsteadRecord", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String findInsteadRecord(Long id) {
        logger.debug("法人信息-代录详情 入参:id=" + String.valueOf(id));

        return exec(() -> lawService.webFindInsteadRecord(id), "法人信息-代录详情  错误", logger);
    }
}
