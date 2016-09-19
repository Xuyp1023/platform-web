package com.betterjr.modules.customer;

import static com.betterjr.common.web.ControllerExceptionHandler.exec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.rpc.RpcException;
import com.betterjr.common.exception.BytterException;
import com.betterjr.common.web.AjaxObject;

@Controller
@RequestMapping("/Platform/CustRelation")
public class CustRelationController {

    private static final Logger logger = LoggerFactory.getLogger(CustRelationController.class);

    @Reference(interfaceClass = ICustRelationService.class)
    private ICustRelationService custRelationService;

    @RequestMapping(value = "/findStatus", method = RequestMethod.POST)
    public @ResponseBody String findStatus(Long custNo) {
        logger.info("开通保理融资业务状态查询,入参: " + custNo);
        try {

            return custRelationService.webFindFactorStatus(custNo);
        }
        catch (RpcException e) {
            logger.error(e.getMessage(), e);
            if (BytterException.isCauseBytterException(e)) {
                return AjaxObject.newError(e.getCause().getMessage()).toJson();
            }
            return AjaxObject.newError("开通保理融资业务状态查询失败").toJson();
        }
        catch (Exception e) {
            logger.error(e.getMessage(), e);
            return AjaxObject.newError("开通保理融资业务状态查询失败").toJson();
        }
    }

    @RequestMapping(value = "/saveRelation", method = RequestMethod.POST)
    public @ResponseBody String saveRelation(Long custNo, String providerList, String factorList, String postscript) {
        logger.info("开通保理融资业务申请,入参: " + custNo + " and " + providerList + " and " + factorList + " and " + postscript);
        try {

            return custRelationService.webSaveCustRelation(custNo, providerList, factorList, postscript);
        }
        catch (RpcException e) {
            logger.error(e.getMessage(), e);
            if (BytterException.isCauseBytterException(e)) {
                return AjaxObject.newError(e.getCause().getMessage()).toJson();
            }
            return AjaxObject.newError("开通保理融资业务申请提交失败").toJson();
        }
        catch (Exception e) {
            logger.error(e.getMessage(), e);
            return AjaxObject.newError("开通保理融资业务申请提交失败").toJson();
        }
    }

    @RequestMapping(value = "/queryAuditWorkflow", method = RequestMethod.POST)
    public @ResponseBody String queryAuditWorkflow(Long custNo) {
        logger.info("开通保理融资业务审批流程查询,入参: " + custNo);
        try {

            return custRelationService.webQueryAuditWorkflow(custNo);
        }
        catch (RpcException e) {
            logger.error(e.getMessage(), e);
            if (BytterException.isCauseBytterException(e)) {
                return AjaxObject.newError(e.getCause().getMessage()).toJson();
            }
            return AjaxObject.newError("开通保理融资业务审批流程查询失败").toJson();
        }
        catch (Exception e) {
            logger.error(e.getMessage(), e);
            return AjaxObject.newError("开通保理融资业务审批流程查询失败").toJson();
        }
    }

    @RequestMapping(value = "/queryAccept", method = RequestMethod.POST)
    public @ResponseBody String queryRelationAccept(String businStatus, String flag, int pageNum, int pageSize) {
        logger.info("客户白名单受理列表查询,入参: " + businStatus);
        try {

            return custRelationService.webQueryRelationAccept(businStatus, flag, pageNum, pageSize);
        }
        catch (RpcException e) {
            logger.error(e.getMessage(), e);
            if (BytterException.isCauseBytterException(e)) {
                return AjaxObject.newError(e.getCause().getMessage()).toJson();
            }
            return AjaxObject.newError("客户白名单受理列表查询失败").toJson();
        }
        catch (Exception e) {
            logger.error(e.getMessage(), e);
            return AjaxObject.newError("客户白名单受理列表查询失败").toJson();
        }
    }

    @RequestMapping(value = "/queryAudit", method = RequestMethod.POST)
    public @ResponseBody String queryRelationAudit(String businStatus, String flag, int pageNum, int pageSize) {
        logger.info("客户白名单审批列表查询,入参: " + businStatus);
        try {

            return custRelationService.webQueryRelationAudit(businStatus, flag, pageNum, pageSize);
        }
        catch (RpcException e) {
            logger.error(e.getMessage(), e);
            if (BytterException.isCauseBytterException(e)) {
                return AjaxObject.newError(e.getCause().getMessage()).toJson();
            }
            return AjaxObject.newError("客户白名单审批列表查询失败").toJson();
        }
        catch (Exception e) {
            logger.error(e.getMessage(), e);
            return AjaxObject.newError("客户白名单审批列表查询失败").toJson();
        }
    }

    @RequestMapping(value = "/saveAccept", method = RequestMethod.POST)
    public @ResponseBody String saveRelationAccept(Long id, String auditOpinion) {
        logger.info("客户白名单受理,入参: " + id);
        try {

            return custRelationService.webSaveRelationAccept(id, auditOpinion);
        }
        catch (RpcException e) {
            logger.error(e.getMessage(), e);
            if (BytterException.isCauseBytterException(e)) {
                return AjaxObject.newError(e.getCause().getMessage()).toJson();
            }
            return AjaxObject.newError("客户白名单受理失败").toJson();
        }
        catch (Exception e) {
            logger.error(e.getMessage(), e);
            return AjaxObject.newError("客户白名单受理失败").toJson();
        }
    }

    @RequestMapping(value = "/saveAudit", method = RequestMethod.POST)
    public @ResponseBody String saveRelationAudit(Long id, String auditOpinion) {
        logger.info("客户白名单审批,入参: " + id);
        try {

            return custRelationService.webSaveRelationAudit(id, auditOpinion);
        }
        catch (RpcException e) {
            logger.error(e.getMessage(), e);
            if (BytterException.isCauseBytterException(e)) {
                return AjaxObject.newError(e.getCause().getMessage()).toJson();
            }
            return AjaxObject.newError("客户白名单审批失败").toJson();
        }
        catch (Exception e) {
            logger.error(e.getMessage(), e);
            return AjaxObject.newError("客户白名单审批失败").toJson();
        }
    }

    @RequestMapping(value = "/saveRefuseAccept", method = RequestMethod.POST)
    public @ResponseBody String saveRefuseAcceptRelation(Long id, String auditOpinion) {
        logger.info("客户白名单受理驳回,入参: " + id);
        try {

            return custRelationService.webSaveRefuseAcceptRelation(id, auditOpinion);
        }
        catch (RpcException e) {
            logger.error(e.getMessage(), e);
            if (BytterException.isCauseBytterException(e)) {
                return AjaxObject.newError(e.getCause().getMessage()).toJson();
            }
            return AjaxObject.newError("客户白名单受理驳回失败").toJson();
        }
        catch (Exception e) {
            logger.error(e.getMessage(), e);
            return AjaxObject.newError("客户白名单受理驳回失败").toJson();
        }
    }

    @RequestMapping(value = "/saveRefuseAudit", method = RequestMethod.POST)
    public @ResponseBody String saveRefuseAuditRelation(Long id, String auditOpinion) {
        logger.info("客户白名单审批驳回,入参: " + id);
        try {

            return custRelationService.webSaveRefuseAuditRelation(id, auditOpinion);
        }
        catch (RpcException e) {
            logger.error(e.getMessage(), e);
            if (BytterException.isCauseBytterException(e)) {
                return AjaxObject.newError(e.getCause().getMessage()).toJson();
            }
            return AjaxObject.newError("客户白名单审批驳回失败").toJson();
        }
        catch (Exception e) {
            logger.error(e.getMessage(), e);
            return AjaxObject.newError("客户白名单审批驳回失败").toJson();
        }
    }

    @RequestMapping(value = "/queryCoreKeyAndValue", method = RequestMethod.POST)
    public @ResponseBody String queryCoreKeyAndValue(Long custNo) {
        logger.info("核心企业下拉列表查询,入参: " + custNo);
        try {

            return custRelationService.webQueryCoreKeyAndValue(custNo);
        }
        catch (RpcException e) {
            logger.error(e.getMessage(), e);
            if (BytterException.isCauseBytterException(e)) {
                return AjaxObject.newError(e.getCause().getMessage()).toJson();
            }
            return AjaxObject.newError("核心企业下拉列表查询失败").toJson();
        }
        catch (Exception e) {
            logger.error(e.getMessage(), e);
            return AjaxObject.newError("核心企业下拉列表查询失败").toJson();
        }
    }

    @RequestMapping(value = "/querySupplierByCore", method = RequestMethod.POST)
    public @ResponseBody String querySupplierByCore(Long coreCustNo) {
        logger.info("供应商下拉列表查询,入参: " + coreCustNo);
        try {

            return custRelationService.webQuerySupplierByCore(coreCustNo);
        }
        catch (RpcException e) {
            logger.error(e.getMessage(), e);
            if (BytterException.isCauseBytterException(e)) {
                return AjaxObject.newError(e.getCause().getMessage()).toJson();
            }
            return AjaxObject.newError("供应商下拉列表查询失败").toJson();
        }
        catch (Exception e) {
            logger.error(e.getMessage(), e);
            return AjaxObject.newError("供应商下拉列表查询询失败").toJson();
        }
    }

    @RequestMapping(value = "/querySellerByCore", method = RequestMethod.POST)
    public @ResponseBody String querySellerByCore(Long coreCustNo) {
        logger.info("经销商下拉列表查询,入参: " + coreCustNo);
        try {

            return custRelationService.webQuerySellerByCore(coreCustNo);
        }
        catch (RpcException e) {
            logger.error(e.getMessage(), e);
            if (BytterException.isCauseBytterException(e)) {
                return AjaxObject.newError(e.getCause().getMessage()).toJson();
            }
            return AjaxObject.newError("经销商下拉列表查询失败").toJson();
        }
        catch (Exception e) {
            logger.error(e.getMessage(), e);
            return AjaxObject.newError("经销商下拉列表查询失败").toJson();
        }
    }

    @RequestMapping(value = "/queryFactorKeyAndValue", method = RequestMethod.POST)
    public @ResponseBody String queryFactorKeyAndValue(Long custNo) {
        logger.info("保理机构下拉列表查询,入参: " + custNo);
        try {

            return custRelationService.webQueryFactorKeyAndValue(custNo);
        }
        catch (RpcException e) {
            logger.error(e.getMessage(), e);
            if (BytterException.isCauseBytterException(e)) {
                return AjaxObject.newError(e.getCause().getMessage()).toJson();
            }
            return AjaxObject.newError("保理机构下拉列表查询失败").toJson();
        }
        catch (Exception e) {
            logger.error(e.getMessage(), e);
            return AjaxObject.newError("保理机构下拉列表查询失败").toJson();
        }
    }

    @RequestMapping(value = "/queryProviderRelation", method = RequestMethod.POST)
    public @ResponseBody String queryProviderRelation(Long custNo) {
        logger.info("客户与电子合同服务商关系查询,入参: " + custNo);
        try {

            return custRelationService.webQueryProviderRelation(custNo);
        }
        catch (RpcException e) {
            logger.error(e.getMessage(), e);
            if (BytterException.isCauseBytterException(e)) {
                return AjaxObject.newError(e.getCause().getMessage()).toJson();
            }
            return AjaxObject.newError("客户与电子合同服务商关系查询失败").toJson();
        }
        catch (Exception e) {
            logger.error(e.getMessage(), e);
            return AjaxObject.newError("客户与电子合同服务商关系查询失败").toJson();
        }
    }

    @RequestMapping(value = "/queryCustRelation", method = RequestMethod.POST)
    public @ResponseBody String queryCustRelation(Long custNo) {
        logger.info("客户关系查询,入参: " + custNo);
        try {

            return custRelationService.webQueryCustRelation(custNo);
        }
        catch (RpcException e) {
            logger.error(e.getMessage(), e);
            if (BytterException.isCauseBytterException(e)) {
                return AjaxObject.newError(e.getCause().getMessage()).toJson();
            }
            return AjaxObject.newError("客户关系查询失败").toJson();
        }
        catch (Exception e) {
            logger.error(e.getMessage(), e);
            return AjaxObject.newError("客户关系查询失败").toJson();
        }
    }

    @RequestMapping(value = "/queryFacotrCoreRelation", method = RequestMethod.POST)
    public @ResponseBody String queryFacotrCoreRelation(Long factorNo) {
        logger.info("保理机构与核心企业关系查询,入参: " + factorNo);
        try {

            return custRelationService.webQueryFacotrCoreRelation(factorNo);
        }
        catch (RpcException e) {
            logger.error(e.getMessage(), e);
            if (BytterException.isCauseBytterException(e)) {
                return AjaxObject.newError(e.getCause().getMessage()).toJson();
            }
            return AjaxObject.newError("保理机构与核心企业关系查询失败").toJson();
        }
        catch (Exception e) {
            logger.error(e.getMessage(), e);
            return AjaxObject.newError("保理机构与核心企业关系查询失败").toJson();
        }
    }

    @RequestMapping(value = "/queryFactorCustRelation", method = RequestMethod.POST)
    public @ResponseBody String queryFactorCustRelation(Long factorNo, String creditType) {
        logger.info("保理机构关系客户查询,入参: " + factorNo + " and " + creditType);
        try {

            return custRelationService.webQueryFactorCustRelation(factorNo, creditType);
        }
        catch (RpcException e) {
            logger.error(e.getMessage(), e);
            if (BytterException.isCauseBytterException(e)) {
                return AjaxObject.newError(e.getCause().getMessage()).toJson();
            }
            return AjaxObject.newError("保理机构关系客户查询失败").toJson();
        }
        catch (Exception e) {
            logger.error(e.getMessage(), e);
            return AjaxObject.newError("保理机构关系客户查询失败").toJson();
        }
    }

    @RequestMapping(value = "/queryFactorRelation", method = RequestMethod.POST)
    public @ResponseBody String queryFactorList(Long custNo) {
        logger.info("客户与保理机构关系查询");

        return exec(() -> custRelationService.webQueryFactorRelation(custNo), "客户与保理机构关系查询失败", logger);
    }
}
