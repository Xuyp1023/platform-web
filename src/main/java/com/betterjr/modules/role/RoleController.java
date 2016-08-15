package com.betterjr.modules.role;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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
import com.betterjr.common.web.Servlets;

/****
 * 角色管理
 * @author hubl
 *
 */
@Controller
@RequestMapping(value = "/Role")
public class RoleController {

    private static final Logger logger = LoggerFactory.getLogger(RoleController.class);
    
    @Reference(interfaceClass=IRoleService.class)
    private IRoleService roleService;
    
    @RequestMapping(value = "/addRole", method = RequestMethod.POST)
    public @ResponseBody String addRole(String roleName,String roleType,String businStatus) {
        try {
            return roleService.webAddRole(roleName,roleType,businStatus);
        }
        catch (RpcException btEx) {
            logger.error(btEx.getMessage(),btEx);
            if(BytterException.isCauseBytterException(btEx)){
                return AjaxObject.newError(btEx.getCause().getMessage()).toJson();
            }
            return AjaxObject.newError("角色添加异常").toJson();
        }catch (Exception ex){
            logger.error("角色添加异常："+ex.getMessage());
            return AjaxObject.newError("角色添加异常").toJson();
        }
    }
    
    @RequestMapping(value = "/updateRole", method = RequestMethod.POST)
    public @ResponseBody String updateRole(String roleId,String roleName,String roleType,String businStatus) {
        try {
            return roleService.webUploadRole(roleId,roleName,roleType,businStatus);
        }
        catch (RpcException btEx) {
            logger.error(btEx.getMessage(),btEx);
            if(BytterException.isCauseBytterException(btEx)){
                return AjaxObject.newError(btEx.getCause().getMessage()).toJson();
            }
            return AjaxObject.newError("角色编辑异常").toJson();
        }catch (Exception ex){
            logger.error("角色编辑异常："+ex.getMessage());
            return AjaxObject.newError("角色编辑异常").toJson();
        }
    }
    
    @RequestMapping(value = "/queryRole", method = RequestMethod.POST)
    public @ResponseBody String queryRole(HttpServletRequest request,int pageNum,int pageSize) {
        Map anMap = Servlets.getParametersStartingWith(request, "");
        logger.info("入参：" + anMap);
        try {
            return roleService.webQueryRole(anMap, pageNum, pageSize);
        }
        catch (RpcException btEx) {
            logger.error(btEx.getMessage(),btEx);
            if(BytterException.isCauseBytterException(btEx)){
                return AjaxObject.newError(btEx.getCause().getMessage()).toJson();
            }
            return AjaxObject.newError("角色查询异常").toJson();
        }catch (Exception ex){
            logger.error("角色查询异常："+ex.getMessage());
            return AjaxObject.newError("角色查询异常").toJson();
        }
    }
    
    @RequestMapping(value = "/findAllRole", method = RequestMethod.POST)
    public @ResponseBody String findAllRole() {
        try {
            return roleService.findRole();
        }
        catch (RpcException btEx) {
            logger.error(btEx.getMessage(),btEx);
            if(BytterException.isCauseBytterException(btEx)){
                return AjaxObject.newError(btEx.getCause().getMessage()).toJson();
            }
            return AjaxObject.newError("findAllRole异常").toJson();
        }catch (Exception ex){
            logger.error("findAllRole异常："+ex.getMessage());
            return AjaxObject.newError("findAllRole异常").toJson();
        }
    }
}
