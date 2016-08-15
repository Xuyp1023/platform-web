package com.betterjr.modules.workflow;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;
import com.betterjr.common.mapper.JsonMapper;
import com.betterjr.common.web.ControllerExceptionHandler;
import com.betterjr.common.web.ControllerExceptionHandler.ExceptionHandler;
import com.betterjr.common.web.Servlets;

@Controller
@RequestMapping("/Platform/workflow")
public class FlowController {

    private static final Logger logger = LoggerFactory.getLogger(FlowController.class);

    @Reference(interfaceClass = IFlowService.class)
    private IFlowService flowService;

    /**
     * 保存流程配置
     * 
     * @param base
     */
    @RequestMapping(value = "/webSaveProcess")
    public @ResponseBody String webSaveProcess(String data) {
        Map anMap=JsonMapper.parserJson(data);
        logger.info("保存流程配置入参" + anMap);
        return ControllerExceptionHandler.exec(new ExceptionHandler() {
            public String handle() {
                return flowService.webSaveProcess(anMap);
            }
        }, "保存流程配置入参失败，请检查", logger);
    }

    /**
     * 所有需要审批的任务
     * 
     * @param page
     * @return
     */
    @RequestMapping(value = "/webQueryWorkTask")
    public @ResponseBody String webQueryWorkTask(HttpServletRequest request, int flag, int pageNum, int pageSize) {
        Map anMap = Servlets.getParametersStartingWith(request, "");
        logger.info("所有需要审批的任务入参" + anMap);
        return ControllerExceptionHandler.exec(new ExceptionHandler() {
            public String handle() {
                return flowService.webQueryWorkTask(anMap, flag, pageNum, pageSize);
            }
        }, "所有需要审批的任务 失败，请检查", logger);
    }

    /**
     * 所有审批历史数据
     * 
     * @param page
     * @return
     */
    @RequestMapping(value = "/webQueryHistoryWorkTask")
    public @ResponseBody String webQueryHistoryWorkTask(HttpServletRequest request, int flag, int pageNum, int pageSize) {
        Map anMap = Servlets.getParametersStartingWith(request, "");
        logger.info("所有审批历史数据入参" + anMap);
        return ControllerExceptionHandler.exec(new ExceptionHandler() {
            public String handle() {
                return flowService.webQueryWorkTask(anMap, flag, pageNum, pageSize);
            }
        }, "所有审批历史数据 失败，请检查", logger);
    }

    /**
     * 查询监控人所属流程
     */
    @RequestMapping(value = "/webQueryWorkTaskByMonitor")
    public @ResponseBody String webQueryWorkTaskByMonitor(HttpServletRequest request, int flag, int pageNum, int pageSize) {
        Map anMap = Servlets.getParametersStartingWith(request, "");
        logger.info("查询监控人所属流程入参" + anMap);
        return ControllerExceptionHandler.exec(new ExceptionHandler() {
            public String handle() {
                return flowService.webQueryWorkTaskByMonitor(anMap, flag, pageNum, pageSize);
            }
        }, "查询监控人所属流程 失败，请检查", logger);
    }

    /*
     * 流程监控-修改流程审批人
     */
    @RequestMapping(value = "/webSaveProcessAudit")
    public @ResponseBody String webSaveProcessAudit(String[] operators) {
        logger.info("流程监控-修改流程审批人入参" + operators);
        return ControllerExceptionHandler.exec(new ExceptionHandler() {
            public String handle() {
                return flowService.webSaveProcessAudit(operators);
            }
        }, "流程监控-修改流程审批人 失败，请检查", logger);
    }

    /**
     * 新增流程节点
     */
    @RequestMapping(value = "/webAddFlowNode")
    public @ResponseBody String webAddFlowNode(HttpServletRequest request) {
        Map anMap = Servlets.getParametersStartingWith(request, "");
        logger.info("新增流程节点入参" + anMap);
        return ControllerExceptionHandler.exec(new ExceptionHandler() {
            public String handle() {
                return flowService.webAddFlowNode(anMap);
            }
        }, "新增流程节点 失败，请检查", logger);
    }

    /**
     * 编辑流程节点
     */
    @RequestMapping(value = "/webSaveFlowNode")
    public @ResponseBody String webSaveFlowNode(HttpServletRequest request) {
        Map anMap = Servlets.getParametersStartingWith(request, "");
        logger.info("编辑流程节点入参" + anMap);
        return ControllerExceptionHandler.exec(new ExceptionHandler() {
            public String handle() {
                return flowService.webSaveFlowNode(anMap);
            }
        }, "编辑流程节点 失败，请检查", logger);
    }
    
    /**
     * 删除流程节点
     */
    @RequestMapping(value = "/webDeleteFlowNode")
    public @ResponseBody String webDeleteFlowNode(HttpServletRequest request) {
        Map anMap = Servlets.getParametersStartingWith(request, "");
        logger.info("删除流程节点入参" + anMap);
        return ControllerExceptionHandler.exec(new ExceptionHandler() {
            public String handle() {
                return flowService.webDeleteFlowNode(anMap);
            }
        }, "删除流程节点 失败，请检查", logger);
    }

    /**
     * 当前流程已经执行的历史详情
     * 
     * @param businessId
     * @return
     */
    @RequestMapping(value = "/webFindExecutedHistory")
    public @ResponseBody String webFindExecutedHistory(Long businessId) {
        logger.info("当前流程已经执行的历史详情入参" + businessId);
        return ControllerExceptionHandler.exec(new ExceptionHandler() {
            public String handle() {
                return flowService.webFindExecutedHistory(businessId);
            }
        }, "当前流程已经执行的历史详情 失败，请检查", logger);
    }

    /**
     * 当前流程当前节点之前的流程节点详情
     * 
     * @param businessId
     * @return
     */
    @RequestMapping(value = "/webFindExecutedNodes")
    public @ResponseBody String webFindExecutedNodes(Long businessId) {
        logger.info("当前流程当前节点之前的流程节点详情入参" + businessId);
        return ControllerExceptionHandler.exec(new ExceptionHandler() {
            public String handle() {
                return flowService.webFindExecutedNodes(businessId);
            }
        }, "当前流程当前节点之前的流程节点详情失败，请检查", logger);
    }

    /**
     * 查询金额分段
     * 
     * @param businessId
     * @return
     */
    @RequestMapping(value = "/webFindMoneyClass")
    public @ResponseBody String webFindMoneyClass() {
        return ControllerExceptionHandler.exec(new ExceptionHandler() {
            public String handle() {
                return flowService.webFindMoneyClass();
            }
        }, "查询金额分段失败，请检查", logger);
    }

    /**
     * 根据流程类型查询系统节点
     * 
     * @param businessId
     * @return
     */
    @RequestMapping(value = "/webFindSysNode")
    public @ResponseBody String webFindSysNode(String flowType) {
        logger.info("根据流程类型查询系统节点入参" + flowType);
        return ControllerExceptionHandler.exec(new ExceptionHandler() {
            public String handle() {
                return flowService.webFindSysNode(flowType);
            }
        }, "根据流程类型查询系统节点失败，请检查", logger);
    }

    /**
     * 根据流程类型得到所有节点
     */
    @RequestMapping(value = "/webFindFlowNodesByType")
    public @ResponseBody String webFindFlowNodesByType(String flowType) {
        logger.info("根据流程类型得到所有节点入参" + flowType);
        return ControllerExceptionHandler.exec(new ExceptionHandler() {
            public String handle() {
                return flowService.webFindFlowNodesByType(flowType);
            }
        }, "根据流程类型得到所有节点失败，请检查", logger);
    }

}
