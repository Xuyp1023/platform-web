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

/**
 * 
 * @author liuwl
 *
 */
@Controller
@RequestMapping("/Platform/BusinLicence")
public class BusinLicenceController {
    private static final Logger logger = LoggerFactory.getLogger(LawInfoController.class);
    
    @Reference(interfaceClass = ICustMechBusinLicenceService.class)
    private ICustMechBusinLicenceService custMechBusinLicenceService;
    
    /**
     * 营业执照信息-查询
     * 
     * @param custNo
     * @return
     */
    @RequestMapping(value = "/findBusinLicence", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String findBusinLicence(Long custNo) {
        try {
            logger.debug("入参:custNo=" + String.valueOf(custNo));
            return custMechBusinLicenceService.webFindBusinLicence(custNo);
        }
        catch (final Exception e) {
            logger.error("营业执照信息-查询详情 错误", e);
            return AjaxObject.newError("营业执照信息-查询详情 错误").toJson();
        }
    }
    
    /**
     * 营业执照信息-变更申请
     * 
     * @param custNo
     * @return
     */
    @RequestMapping(value = "/addChangeApply", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String addChangeApply(HttpServletRequest request, String fileList) {
        try {
            final Map<String, Object> reqParam = Servlets.getParametersStartingWith(request, "");
            logger.debug("营业执照信息-变更申请 入参:reqParam=" + reqParam.toString() + " fileList=" + fileList);

            return custMechBusinLicenceService.webAddChangeApply(reqParam, fileList);
        }
        catch (final Exception e) {
            logger.error("营业执照信息-变更申请 错误", e);
            return AjaxObject.newError("营业执照信息-变更申请 错误").toJson();
        }
    }

    /**
     * 营业执照信息-变更修改
     * 
     * @param custNo
     * @return
     */
    @RequestMapping(value = "/saveChangeApply", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String saveChangeApply(HttpServletRequest request, Long applyId, String fileList) {
        try {
            final Map<String, Object> reqParam = Servlets.getParametersStartingWith(request, "");
            logger.debug("营业执照信息-变更修改 入参:reqParam=" + reqParam.toString() + " applyId=" + String.valueOf(applyId) + " fileList=" + fileList);

            return custMechBusinLicenceService.webSaveChangeApply(reqParam, applyId, fileList);
        }
        catch (final Exception e) {
            logger.error("营业执照信息-变更修改 错误", e);
            return AjaxObject.newError("营业执照信息-变更修改 错误").toJson();
        }
    }
    
    /**
     * 营业执照信息-变更详情
     * 
     * @param custNo
     * @return
     */
    @RequestMapping(value = "/findChangeApply", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String findChangeApply(Long id) {
        try {
            logger.debug("营业执照信息-变更详情 入参: id=" + String.valueOf(id));
            return custMechBusinLicenceService.webFindChangeApply(id);
        }
        catch (final Exception e) {
            logger.error("营业执照信息-变更详情 错误", e);
            return AjaxObject.newError("营业执照信息-变更详情 错误").toJson();
        }
    }

    /**
     * 营业执照信息-变更列表
     * 
     * @param custNo
     * @return
     */
    @RequestMapping(value = "/queryChangeApply", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String queryChangeApply(Long custNo, int flag, int pageNum, int pageSize) {
        try {
            logger.debug("营业执照信息-变更列表-查询 入参:custNo=" + String.valueOf(custNo) + " flag=" + String.valueOf(flag) + " pageNum=" + String.valueOf(pageNum)
                    + " pageSize=" + String.valueOf(pageSize));
            return custMechBusinLicenceService.webQueryChangeApply(custNo, flag, pageNum, pageSize);
        }
        catch (final Exception e) {
            logger.error("营业执照信息-变更列表-查询 错误", e);
            return AjaxObject.newError("营业执照信息-变更列表-查询 错误").toJson();
        }
    }

    /**
     * 营业执照信息-添加代录
     * 
     * @param custNo
     * @return
     */
    @RequestMapping(value = "/addInsteadRecord", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String addInsteadRecord(HttpServletRequest request, Long insteadRecordId, String fileList) {
        try {
            final Map<String, Object> reqParam = Servlets.getParametersStartingWith(request, "");
            logger.debug("营业执照信息-添加代录 入参:reqParam=" + reqParam.toString() + " insteadRecordId=" + String.valueOf(insteadRecordId) + " fileList=" + fileList);

            return custMechBusinLicenceService.webAddInsteadRecord(reqParam, insteadRecordId, fileList);
        }
        catch (final Exception e) {
            logger.error("营业执照信息-添加代录 错误", e);
            return AjaxObject.newError("营业执照信息-添加代录 错误").toJson();
        }
    }

    /**
     * 营业执照信息-代录修改
     * 
     * @param custNo
     * @return
     */
    @RequestMapping(value = "/saveInsteadRecord", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String saveInsteadRecord(HttpServletRequest request, Long insteadRecordId, String fileList) {
        try {
            final Map<String, Object> reqParam = Servlets.getParametersStartingWith(request, "");
            logger.debug("营业执照信息-代录修改 入参:reqParam=" + reqParam.toString() + " insteadRecordId=" + String.valueOf(insteadRecordId) + " fileList=" + fileList);

            return custMechBusinLicenceService.webSaveInsteadRecord(reqParam, insteadRecordId, fileList);
        }
        catch (final Exception e) {
            logger.error("营业执照信息-代录修改 错误", e);
            return AjaxObject.newError("营业执照信息-代录修改 错误").toJson();
        }
    }

    /**
     * 营业执照信息-代录详情
     * 
     * @param custNo
     * @return
     */
    @RequestMapping(value = "/findInsteadRecord", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String findInsteadRecord(Long id) {
        try {
            logger.debug("营业执照信息-代录详情 入参:id=" + String.valueOf(id));

            return custMechBusinLicenceService.webFindInsteadRecord(id);
        }
        catch (final Exception e) {
            logger.error("营业执照信息-代录详情  错误", e);
            return AjaxObject.newError("营业执照信息-代录详情  错误").toJson();
        }
    }
}
