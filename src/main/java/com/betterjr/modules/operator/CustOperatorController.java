package com.betterjr.modules.operator;

import static com.betterjr.common.web.ControllerExceptionHandler.exec;

import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.betterjr.common.data.SimpleDataEntity;
import com.betterjr.common.web.AjaxObject;
import com.betterjr.common.web.ControllerExceptionHandler;
import com.betterjr.common.web.ControllerExceptionHandler.ExceptionHandler;
import com.betterjr.common.web.Servlets;
import com.betterjr.modules.account.dubboclient.CustOperatorDubboClientService;
import com.betterjr.modules.operator.dubboclient.OperatorDubboClientService;

/***
 * 操作员管理
 * 
 * @author hubl
 *
 */
@Controller
@RequestMapping(value = "/Platform/CustOperator")
public class CustOperatorController {

    private static final Logger logger = LoggerFactory.getLogger(CustOperatorController.class);

    @Autowired
    private OperatorDubboClientService custOperatorService;

    @Autowired
    private CustOperatorDubboClientService custOperatorDubboClientService;

    /**
     * 新增操作员
     *
     * @param anMap
     * @return
     */
    @RequestMapping(value = "/addCustOperator", method = RequestMethod.POST)
    public @ResponseBody String addCustOperator(final HttpServletRequest request, final String custList,
            final String fileList) {
        final Map<String, Object> anMap = Servlets.getParametersStartingWith(request, "");
        logger.info("入参：" + anMap);
        return ControllerExceptionHandler.exec(new ExceptionHandler() {
            @Override
            public String handle() {
                return custOperatorService.webAddCustOperator(anMap, custList, fileList);
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
    public @ResponseBody String updateCustOperator(final HttpServletRequest request, final String custList,
            final String fileList) {
        final Map anMap = Servlets.getParametersStartingWith(request, "");
        logger.info("入参：" + anMap);
        return ControllerExceptionHandler.exec(new ExceptionHandler() {
            @Override
            public String handle() {
                return custOperatorService.webUpdateOperatorWithImageFile(anMap, custList, fileList);
            }
        }, "编辑操作员异常", logger);
    }

    /****
     * 操作员分页查询
     * 
     * @param request
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/queryCustOperator", method = RequestMethod.POST)
    public @ResponseBody String queryCustOperator(final HttpServletRequest request, final int pageNum,
            final int pageSize) {
        final Map anMap = Servlets.getParametersStartingWith(request, "");
        logger.info("入参：" + anMap);
        return ControllerExceptionHandler.exec(new ExceptionHandler() {
            @Override
            public String handle() {
                return custOperatorService.webQueryCustOperator(anMap, pageNum, pageSize);
            }
        }, "操作员分页查询异常", logger);
    }

    /***
     * 获取当前操作员的菜单信息-用于左侧菜单列表显示
     * 
     * @param menuId
     * @return
     */
    @RequestMapping(value = "/findSysMenuByMenuId", method = RequestMethod.POST)
    public @ResponseBody String findSysMenuByMenuId(final Integer menuId) {
        return ControllerExceptionHandler.exec(new ExceptionHandler() {
            @Override
            public String handle() {
                return custOperatorService.webFindSysMenuByMenuId(menuId);
            }
        }, "获取菜单信息异常", logger);
    }

    /***
     * 根据角色获取菜单信息
     * 
     * @param menuId
     * @return
     */
    @RequestMapping(value = "/findMenuByRole", method = RequestMethod.POST)
    public @ResponseBody String findAllSysMenu(final String roleId) {

        return ControllerExceptionHandler.exec(new ExceptionHandler() {
            @Override
            public String handle() {
                return custOperatorService.webFindSysMenuByMenuRole(roleId);
            }
        }, "获取菜单信息异常", logger);
    }

    /***
     * 角色菜单添加
     * 
     * @param menuId
     * @return
     */
    @RequestMapping(value = "/addMenuRole", method = RequestMethod.POST)
    public @ResponseBody String addMenuRole(final String roleId, final String roleName, final String menuIdArr) {
        return ControllerExceptionHandler.exec(new ExceptionHandler() {
            @Override
            public String handle() {
                return custOperatorService.webAddMenuRole(roleId, roleName, menuIdArr);
            }
        }, "绑定角色菜单异常", logger);
    }

    /***
     * 根据操作员id 查询操作员信息
     * 
     * @param menuId
     * @return
     */
    @RequestMapping(value = "/findOperatorById", method = RequestMethod.POST)
    public @ResponseBody String findOperatorById(final Long operatorId) {
        return ControllerExceptionHandler.exec(new ExceptionHandler() {
            @Override
            public String handle() {
                return custOperatorService.webFindOperatorById(operatorId);
            }
        }, "绑定角色菜单异常", logger);
    }

    /****
     * 查询当前机构下面的所有操作员
     * 
     * @return
     */
    @RequestMapping(value = "/findCustOperator", method = RequestMethod.POST)
    public @ResponseBody String findCustOperator() {
        return ControllerExceptionHandler.exec(new ExceptionHandler() {
            @Override
            public String handle() {
                return custOperatorService.webFindCustOperator();
            }
        }, "查询当前机构下面的所有操作员异常", logger);
    }

    /****
     * 操作员密码修改
     * 
     * @return
     */
    @RequestMapping(value = "/updatePassword", method = RequestMethod.POST)
    public @ResponseBody String updatePassword(final String newPasswd, final String okPasswd, final String passwd) {
        return ControllerExceptionHandler.exec(new ExceptionHandler() {
            @Override
            public String handle() {
                return custOperatorService.webUpdatePassword(newPasswd, okPasswd, passwd);
            }
        }, "修改密码异常", logger);
    }

    /****
     * 操作员密码重置 id 操作员编号 password 密码 okPasswd 确认密码
     * 
     * @return
     */
    @RequestMapping(value = "/changeUserPassword", method = RequestMethod.POST)
    public @ResponseBody String changeUserPassword(final Long id, final String password, final String okPasswd) {
        return ControllerExceptionHandler.exec(new ExceptionHandler() {
            @Override
            public String handle() {
                return custOperatorService.webChangeUserPassword(id, password, okPasswd);
            }
        }, "修改密码异常", logger);
    }

    /*****
     * 获取操作机构关联的客户信息
     * 
     * @return
     */
    @RequestMapping(value = "/findOperatorCustInfo", method = RequestMethod.POST)
    public @ResponseBody String findOperatorCustInfo() {
        return ControllerExceptionHandler.exec(new ExceptionHandler() {
            @Override
            public String handle() {
                return custOperatorService.webFindOperatorCustInfo();
            }
        }, "获取操作机构关联的客户信息", logger);
    }

    /*****
     * 获取操作机构关联的客户信息
     * 
     * @return
     */
    @RequestMapping(value = "/queryOperatorByCustNo", method = RequestMethod.POST)
    public @ResponseBody String queryOperatorByCustNo(final Long custNo) {
        return ControllerExceptionHandler.exec(new ExceptionHandler() {
            @Override
            public String handle() {
                return AjaxObject.newOk("获取公司操作员成功",
                        custOperatorDubboClientService.queryOperatorByCustNo(custNo).stream().map(operator -> {
                            return new SimpleDataEntity(operator.getName(), String.valueOf(operator.getId()));
                        }).collect(Collectors.toList())).toJson();
            }
        }, "获取操作机构关联的客户信息", logger);
    }

    /**
     * 根据入参查询对应操作机构操作员 0普通操作员，1对外经办人, 2:账户业务办理人员，3:交易业务办理人员
     */
    @RequestMapping(value = "/findCustOperatorByClerk", method = RequestMethod.POST)
    public @ResponseBody String findCustOperatorByClerk(final String clerk) {
        logger.info("查询当前机构下面的操作员,入参: " + clerk);
        return exec(() -> custOperatorService.webFindCustOperatorByClerk(clerk), "查询当前机构下面的操作员失败", logger);
    }

    @RequestMapping(value = "/queryCustOperatorByPage", method = RequestMethod.POST)
    public @ResponseBody String queryCustOperatorByPage(final HttpServletRequest request) {
        final Map anMap = Servlets.getParametersStartingWith(request, "");
        logger.info("入参:" + anMap);
        return exec(() -> custOperatorService.webQueryCustOperatorByPage(anMap), "查询复核审批员信息", logger);
    }

}
