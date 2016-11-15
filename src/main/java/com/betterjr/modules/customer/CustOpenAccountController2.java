package com.betterjr.modules.customer;

import static com.betterjr.common.web.ControllerExceptionHandler.exec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.dubbo.config.annotation.Reference;

@Controller
@RequestMapping("/Platform/Account2")
public class CustOpenAccountController2 {
    
    @Reference(interfaceClass = ICustOpenAccountService2.class)
    private ICustOpenAccountService2 custOpenAccountService;

    
    private static final Logger logger = LoggerFactory.getLogger(CustOpenAccountController2.class);
    
    /**
     * 检查申请机构名称是否存在
     */
    @RequestMapping(value = "/checkCustExistsByCustName", method = RequestMethod.POST)
    public String checkCustExistsByCustName(String custName) {
        logger.info("检查申请机构名称是否存在,入参: " + custName);
        return exec(() -> custOpenAccountService.webCheckCustExistsByCustName(custName), "检查申请机构名称是否存在失败", logger);
    }
    
    /**
     * 检查组织机构代码证是否存在
     */
    @RequestMapping(value = "/checkCustExistsByIdentNo", method = RequestMethod.POST)
    public String checkCustExistsByIdentNo(String identNo){
        logger.info("检查组织机构代码证是否存在,入参: " + identNo);
        return exec(() -> custOpenAccountService.webCheckCustExistsByIdentNo(identNo), "检查组织机构代码证是否存在失败", logger);
    }
    
    /**
     * 检查营业执照号码是否存在
     */
    @RequestMapping(value = "/checkCustExistsByBusinLicence", method = RequestMethod.POST)
    public String checkCustExistsByBusinLicence(String businLicence) {
        logger.info("检查营业执照号码是否存在,入参: " + businLicence);
        return exec(() -> custOpenAccountService.webCheckCustExistsByBusinLicence(businLicence), "检查营业执照号码是否存在失败", logger);
    }
    
    /**
     * 检查银行账号是否存在
     */
    @RequestMapping(value = "/checkCustExistsByBankAccount", method = RequestMethod.POST)
    public String checkCustExistsByBankAccount(String bankAccount) {
        logger.info("检查银行账号是否存在,入参: " + bankAccount);
        return exec(() -> custOpenAccountService.webCheckCustExistsByBankAccount(bankAccount), "检查银行账号是否存在失败", logger);
    }
    
    /**
     * 检查电子邮箱是否存在
     */
    @RequestMapping(value = "/checkCustExistsByBankAccount", method = RequestMethod.POST)
    public String checkCustExistsByEmail(String email) {
        logger.info("检查电子邮箱是否存在,入参: " + email);
        return exec(() -> custOpenAccountService.webCheckCustExistsByEmail(email), "检查电子邮箱是否存在失败", logger);
    }
    
    /**
     * 检查银行账号是否存在
     */
    @RequestMapping(value = "/checkCustExistsByBankAccount", method = RequestMethod.POST)
    public String checkCustExistsByMobileNo(String mobileNo) {
        logger.info("检查手机号码是否存在,入参: " + mobileNo);
        return exec(() -> custOpenAccountService.webCheckCustExistsByMobileNo(mobileNo), "检查手机号码是否存在失败", logger);
    }
}
