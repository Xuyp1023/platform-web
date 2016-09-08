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
 * 
 * @author liuwl
 *
 */
@Controller
@RequestMapping("/Platform/BaseInfo")
public class BaseInfoController {
    private static final Logger logger = LoggerFactory.getLogger(BaseInfoController.class);

    @Reference(interfaceClass = ICustMechBaseService.class)
    private ICustMechBaseService baseInfoService;

    /**
     * 检查当前用户机构类型
     * 
     * @return
     */
    @RequestMapping(value = "/checkOrgType", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String checkOrgType(String role) {
        return exec(() -> baseInfoService.webCheckOrgType(role), "判断机构类型-查询出错", logger);
    }
    
    /**
     * 当前客户公司列表-查询
     * 
     * @return
     */
    @RequestMapping(value = "/queryCustList", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String queryCustList() {
        return exec(() -> baseInfoService.webQueryCustInfo(), "公司列表-查询出错", logger);
    }

    /**
     * 当前客户公司列表-查询  供select使用
     * 
     * @return
     */
    @RequestMapping(value = "/queryCustSelect", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String queryCustSelect() {
        return exec(() -> baseInfoService.webQueryCustInfoSelect(), "公司列表-查询出错", logger);
    }

    /**
     * 公司基本信息-查询
     * 
     * @param custNo
     * @return
     */
    @RequestMapping(value = "/findBaseInfo", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String findBaseInfo(Long custNo) {
        logger.debug("入参:custNo=" + String.valueOf(custNo));
        return exec(() -> baseInfoService.webFindBaseInfo(custNo), "公司基本信息-查询详情 错误", logger);
    }

    /**
     * 公司基本信息-变更申请
     * 
     * @param custNo
     * @return
     */
    @RequestMapping(value = "/addChangeApply", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String addChangeApply(HttpServletRequest request, String fileList) {
        final Map<String, Object> reqParam = Servlets.getParametersStartingWith(request, "");
        logger.debug("公司基本信息-变更申请 入参:reqParam=" + reqParam.toString() + " fileList=" + fileList);

        return exec(() -> baseInfoService.webAddChangeApply(reqParam, fileList), "公司基本信息-变更申请 错误", logger);
    }

    /**
     * 公司基本信息-变更修改
     * 
     * @param custNo
     * @return
     */
    @RequestMapping(value = "/saveChangeApply", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String saveChangeApply(HttpServletRequest request, Long applyId, String fileList) {
        final Map<String, Object> reqParam = Servlets.getParametersStartingWith(request, "");
        logger.debug("公司基本信息-变更修改 入参:reqParam=" + reqParam.toString() + " applyId=" + String.valueOf(applyId) + " fileList=" + fileList);

        return exec(() -> baseInfoService.webSaveChangeApply(reqParam, applyId, fileList), "公司基本信息-变更修改 错误", logger);
    }
    
    /**
     * 公司基本信息-变更详情
     * 
     * @param custNo
     * @return
     */
    @RequestMapping(value = "/findChangeApply", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String findChangeApply(Long id) {
        logger.debug("公司基本信息-变更详情 入参: id=" + String.valueOf(id));
        return exec(() -> baseInfoService.webFindChangeApply(id), "公司基本信息-变更详情 错误", logger);
    }

    /**
     * 公司基本信息-变更列表
     * 
     * @param custNo
     * @return
     */
    @RequestMapping(value = "/queryChangeApply", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String queryChangeApply(Long custNo, int flag, int pageNum, int pageSize) {
        logger.debug("公司基本信息-变更列表 入参:custNo=" + String.valueOf(custNo) + " flag=" + String.valueOf(flag) + " pageNum=" + String.valueOf(pageNum)
                + " pageSize=" + String.valueOf(pageSize));
        return exec(() -> baseInfoService.webQueryChangeApply(custNo, flag, pageNum, pageSize), "公司基本信息-变更列表 错误", logger);
    }

    /**
     * 公司基本信息-添加代录
     * 
     * @param custNo
     * @return
     */
    @RequestMapping(value = "/addInsteadRecord", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String addInsteadRecord(HttpServletRequest request, Long insteadRecordId, String fileList) {
        final Map<String, Object> reqParam = Servlets.getParametersStartingWith(request, "");
        logger.debug(
                "公司基本信息-添加代录 入参:reqParam=" + reqParam.toString() + " insteadRecordId=" + String.valueOf(insteadRecordId) + " fileList=" + fileList);

        return exec(() -> baseInfoService.webAddInsteadRecord(reqParam, insteadRecordId, fileList), "公司基本信息-添加代录 错误", logger);
    }

    /**
     * 公司基本信息-代录修改
     * 
     * @param custNo
     * @return
     */
    @RequestMapping(value = "/saveInsteadRecord", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String saveInsteadRecord(HttpServletRequest request, Long insteadRecordId, String fileList) {
        final Map<String, Object> reqParam = Servlets.getParametersStartingWith(request, "");
        logger.debug(
                "公司基本信息-代录修改 入参:reqParam=" + reqParam.toString() + " insteadRecordId=" + String.valueOf(insteadRecordId) + " fileList=" + fileList);

        return exec(() -> baseInfoService.webSaveInsteadRecord(reqParam, insteadRecordId, fileList), "公司基本信息-代录修改 错误", logger);
    }
    
    /**
     * 公司基本信息-代录详情
     * 
     * @param custNo
     * @return
     */
    @RequestMapping(value = "/findInsteadRecord", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String findInsteadRecord(Long id) {
        logger.debug("公司基本信息-代录详情 入参:id=" + String.valueOf(id));

        return exec(() -> baseInfoService.webFindInsteadRecord(id), "公司基本信息-代录详情  错误", logger);
    }
}
