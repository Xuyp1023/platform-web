package com.betterjr.modules.operator;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;
import com.betterjr.common.exception.BytterTradeException;
import com.betterjr.common.web.AjaxObject;
import com.betterjr.common.web.Servlets;

/***
 * 操作员管理
 * @author hubl
 *
 */
@Controller
@RequestMapping(value = "/CustOperator")
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
        try {
            return custOperatorService.webAddCustOperator(anMap);
        }
        catch (BytterTradeException btEx) {
            return AjaxObject.newBusin(btEx.getMessage()).toJson();
        }
        catch (Exception ex) {
            logger.error("新增操作员异常："+ex.getMessage());
            return AjaxObject.newBusin("新增操作员失败，请检查").toJson();
        }
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
        try {
            return custOperatorService.webUpdateCustOperator(anMap);
        }
        catch (BytterTradeException btEx) {
            return AjaxObject.newBusin(btEx.getMessage()).toJson();
        }
        catch (Exception ex) {
            logger.error("编辑操作员异常："+ex.getMessage());
            return AjaxObject.newBusin("编辑作员失败，请检查").toJson();
        }
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
        try {
            return custOperatorService.webQueryCustOperator(anMap, pageNum, pageSize);
        }
        catch (BytterTradeException btEx) {
            return AjaxObject.newBusin(btEx.getMessage()).toJson();
        }
        catch (Exception ex) {
            logger.error("分页查询操作员异常："+ex.getMessage());
            return AjaxObject.newBusin("分页查询操作员失败，请检查").toJson();
        }
    }
    
    /***
     * 获取菜单信息
     * @param menuId
     * @return
     */
    @RequestMapping(value = "/findSysMenuByMenuId", method = RequestMethod.POST)
    public @ResponseBody String findSysMenuByMenuId(Integer menuId,String roleName) {
        try {
            return custOperatorService.webFindSysMenuByMenuId(menuId,roleName);
        }
        catch (BytterTradeException btEx) {
            return AjaxObject.newBusin(btEx.getMessage()).toJson();
        }
        catch (Exception ex) {
            logger.error("获取菜单信息异常："+ex.getMessage());
            return AjaxObject.newBusin("获取菜单失败，请检查").toJson();
        }
    } 
    

    /***
     * 获取所有有效菜单列表
     * @param menuId
     * @return
     */
    @RequestMapping(value = "/findAllSysMenu", method = RequestMethod.POST)
    public @ResponseBody String findAllSysMenu() {
        try {
            return custOperatorService.webFindAllSysMenu();
        }
        catch (BytterTradeException btEx) {
            return AjaxObject.newBusin(btEx.getMessage()).toJson();
        }
        catch (Exception ex) {
            logger.error("获取菜单信息异常："+ex.getMessage());
            return AjaxObject.newBusin("获取菜单失败，请检查").toJson();
        }
    } 
    
    /***
     * 角色菜单添加
     * @param menuId
     * @return
     */
    @RequestMapping(value = "/addMenuRole", method = RequestMethod.POST)
    public @ResponseBody String addMenuRole(String roleId,String roleName,String menuIdArr) {
        try {
            return custOperatorService.webAddMenuRole(roleId,roleName,menuIdArr);
        }
        catch (BytterTradeException btEx) {
            return AjaxObject.newBusin(btEx.getMessage()).toJson();
        }
        catch (Exception ex) {
            logger.error("获取菜单信息异常："+ex.getMessage());
            return AjaxObject.newBusin("获取菜单失败，请检查").toJson();
        }
    }
    
}
