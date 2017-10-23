package com.betterjr.modules.document;

import static com.betterjr.common.web.ControllerExceptionHandler.exec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;

/***
 * 审批临时文件处理类
 * @ClassName: CustFileAduitTempController 
 * @Description: TODO(审批临时文件处理类) 
 * @author hubl
 * @date 2017年10月17日 下午5:26:00 
 *
 */
@Controller
@RequestMapping(value = "/Platform/CustAduitTemp")
public class CustFileAduitTempController {

    private static Logger logger = LoggerFactory.getLogger(CustFileAduitTempController.class);

    @Reference(interfaceClass = ICustFileAduitTempService.class)
    private ICustFileAduitTempService custFileAduitTempService;

    @RequestMapping(value = "/addAduitTempFile", method = RequestMethod.POST)
    public @ResponseBody String addAduitTempFile(final Long custNo, final String fileIds, final String agencyNo) {
        logger.info("新增用户认证文件审核信息,custNo:" + custNo + "，fileIds:" + fileIds + ",agencyNo:" + agencyNo);

        return exec(() -> custFileAduitTempService.saveCustFileAuditTempInfo(custNo, fileIds), "新增用户认证文件审核信息", logger);
    }

    @RequestMapping(value = "/findCustAduitFileChangeApply", method = RequestMethod.POST)
    public @ResponseBody String findCustAduitFileChangeApply(final Long id) {
        logger.info("查询资料认证附件入参,id:" + id);
        return exec(() -> custFileAduitTempService.webFindChangeApply(id), "查询资料认证附件", logger);
    }

}
