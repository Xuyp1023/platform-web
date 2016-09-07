package com.betterjr.modules.operator;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.dubbo.config.annotation.Reference;
import com.betterjr.common.web.ControllerExceptionHandler;
import com.betterjr.common.web.ControllerExceptionHandler.ExceptionHandler;
import com.betterjr.common.web.Servlets;

/***
 * 操作员管理
 * @author hubl
 *
 */
@Controller
@RequestMapping(value = "/Platform/CustOperator")
public class CustOperatorController {

    private static final Logger logger = LoggerFactory.getLogger(CustOperatorController.class);
    
    @Reference(interfaceClass=IOperatorService.class)
    private IOperatorService custOperatorService;
    
    /**
     * 新增操作员
     * 
     * @param anMap
     * @return
     */
    @RequestMapping(value = "/addCustOperator", method = RequestMethod.POST)
    public @ResponseBody String addCustOperator(HttpServletRequest request) {
        Map anMap = Servlets.getParametersStartingWith(request, "");
        logger.info("入参：" + anMap);
        return ControllerExceptionHandler.exec(new ExceptionHandler() {
            public String handle() {
                return custOperatorService.webAddCustOperator(anMap);
            }
        }, "新增操作员异常", logger);
    }
    
    /**
     * 编辑操作员
     * 
     * @param anMap
     * @return
     */
    @RequestMapping(value = "/updateCustOperator", method = RequestMethod.POST)
    public @ResponseBody String updateCustOperator(HttpServletRequest request) {
        Map anMap = Servlets.getParametersStartingWith(request, "");
        logger.info("入参：" + anMap);        
        return ControllerExceptionHandler.exec(new ExceptionHandler() {
            public String handle() {
                return custOperatorService.webUpdateCustOperator(anMap);
            }
        }, "编辑操作员异常", logger);
    }
    
    /****
     * 操作员分页查询
     * @param request
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/queryCustOperator", method = RequestMethod.POST)
    public @ResponseBody String queryCustOperator(HttpServletRequest request,int pageNum,int pageSize) {
        Map anMap = Servlets.getParametersStartingWith(request, "");
        logger.info("入参：" + anMap);
        return ControllerExceptionHandler.exec(new ExceptionHandler() {
            public String handle() {
                return custOperatorService.webQueryCustOperator(anMap, pageNum, pageSize);
            }
        }, "操作员分页查询异常", logger);
    }
    
    /***
     * 获取当前操作员的菜单信息-用于左侧菜单列表显示
     * @param menuId
     * @return
     */
    @RequestMapping(value = "/findSysMenuByMenuId", method = RequestMethod.POST)
    public @ResponseBody String findSysMenuByMenuId(Integer menuId) {
        return ControllerExceptionHandler.exec(new ExceptionHandler() {
            public String handle() {
                return custOperatorService.webFindSysMenuByMenuId(menuId);
            }
        }, "获取菜单信息异常", logger);
    } 
    

    /***
     * 根据角色获取菜单信息
     * @param menuId
     * @return
     */
    @RequestMapping(value = "/findMenuByRole", method = RequestMethod.POST)
    public @ResponseBody String findAllSysMenu(String roleId) {
        
        return ControllerExceptionHandler.exec(new ExceptionHandler() {
            public String handle() {
                return custOperatorService.webFindSysMenuByMenuRole(roleId);
            }
        }, "获取菜单信息异常", logger);
    } 
    
    /***
     * 角色菜单添加
     * @param menuId
     * @return
     */
    @RequestMapping(value = "/addMenuRole", method = RequestMethod.POST)
    public @ResponseBody String addMenuRole(String roleId,String roleName,String menuIdArr) {
        return ControllerExceptionHandler.exec(new ExceptionHandler() {
            public String handle() {
                return custOperatorService.webAddMenuRole(roleId,roleName,menuIdArr);
            }
        }, "绑定角色菜单异常", logger);
    }
    
    /***
     * 根据操作员id 查询操作员信息
     * @param menuId
     * @return
     */
    @RequestMapping(value = "/findOperatorById", method = RequestMethod.POST)
    public @ResponseBody String findOperatorById(Long operatorId) {
        return ControllerExceptionHandler.exec(new ExceptionHandler() {
            public String handle() {
                return custOperatorService.webFindOperatorById(operatorId);
            }
        }, "绑定角色菜单异常", logger);
    }
    
    /****
     * 查询当前机构下面的所有操作员
     * @return
     */
    @RequestMapping(value = "/findCustOperator", method = RequestMethod.POST)
    public @ResponseBody String findCustOperator() {
        return ControllerExceptionHandler.exec(new ExceptionHandler() {
            public String handle() {
                return custOperatorService.webFindCustOperator();
            }
        }, "查询当前机构下面的所有操作员异常", logger);
    }
    
}
