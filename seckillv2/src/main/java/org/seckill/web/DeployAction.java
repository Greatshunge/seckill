/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.seckill.web;

import org.seckill.utils.CommandExecutor;
import org.seckill.utils.FileFetcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.io.IOException;

/**
 *
 * @author shawn
 * @copyright Halo
 * @datetime 2017-4-28 11:31:17
 * @version 1.0
 */
@ConfigurationProperties(prefix="env")
@Controller
@RequestMapping("/service")
public class DeployAction {

    private static final Logger logger = LoggerFactory.getLogger(DeployAction.class);
    private static final String LOGGER_PREFIX = "[我的服务][服务发布服务]：";
    
    private String pkg_remote_host;
    private String pkg_remote_port;
    private String pkg_remote_path;
    private String deploy_local_dir;
    private String deploy_shell;

    public String getPkg_remote_host() {
        return pkg_remote_host;
    }

    public void setPkg_remote_host(String pkg_remote_host) {
        this.pkg_remote_host = pkg_remote_host;
    }

    public String getPkg_remote_port() {
        return pkg_remote_port;
    }

    public void setPkg_remote_port(String pkg_remote_port) {
        this.pkg_remote_port = pkg_remote_port;
    }

    public String getPkg_remote_path() {
        return pkg_remote_path;
    }

    public void setPkg_remote_path(String pkg_remote_path) {
        this.pkg_remote_path = pkg_remote_path;
    }

    public String getDeploy_local_dir() {
        return deploy_local_dir;
    }

    public void setDeploy_local_dir(String deploy_local_dir) {
        this.deploy_local_dir = deploy_local_dir;
    }

    public String getDeploy_shell() {
        return deploy_shell;
    }

    public void setDeploy_shell(String deploy_shell) {
        this.deploy_shell = deploy_shell;
    }
    
    @GetMapping(value = "/sayHello/{name}.do")
    @ResponseBody
    public String sayHello(@PathVariable("name") String name) {
        logger.info(LOGGER_PREFIX + "Hello, [" + name + "].");
        
        logger.info(LOGGER_PREFIX + "Hello, [" + name + "].");
        return "Hello, [" + name + "].";
    }
    
    @GetMapping(value = "/download/{pkg}.do")
    @ResponseBody
    public String downloadPackage(@PathVariable("pkg") String pkg) {
        logger.info(LOGGER_PREFIX + "开始下载服务发布包[" + pkg + "].");
        
        String file = "http://" + pkg_remote_host + ":" + pkg_remote_port + pkg_remote_path + pkg + ".zip";
        String localFile = deploy_local_dir + pkg + ".zip";
        
        int result = FileFetcher.fetche(file, localFile);
        if (result != 1) {
            logger.info(LOGGER_PREFIX + "服务发布包下载失败[" + pkg + "].");
            return "服务包[" + pkg + "]下载失败，请登录服务器查询详细信息。";
        }
        
        logger.info(LOGGER_PREFIX + "服务发布包下载完成[" + pkg + "].");
        return "服务包[" + pkg + "]下载完成";
    }
    
    @GetMapping(value = "/deploy/{pkg}.do")
    @ResponseBody
    public String deploy(@PathVariable("pkg") String pkg) {
        logger.info(LOGGER_PREFIX + "部署发布包[" + pkg + "]开始.");
        
        if (StringUtils.isEmpty(pkg)) {
            return "服务发布包名不能为空";
        }
        
        // 解压服务发布包
        logger.info(LOGGER_PREFIX + "解压发布包[" + pkg + "]开始.");
        String source = deploy_local_dir + pkg;
        if (StringUtils.isEmpty(source) || !(new File(source + ".zip").exists())) {
            logger.error(LOGGER_PREFIX + "解压发布包[" + pkg + "]失败！文件不存在。");
            return "解压发布包[" + pkg + "]异常!请登录服务器查看详细信息！文件不存在。";
        }
        
        String shell = "unzip " + deploy_local_dir + pkg + ".zip -d " + deploy_local_dir + pkg;
        int executeResult = 1;
        try {
            executeResult = CommandExecutor.executeShell(shell);
        } catch (IOException | IllegalArgumentException | InterruptedException e) {
            logger.error(LOGGER_PREFIX + "解压发布包[" + pkg + "]失败！", e);
            return "解压发布包[" + pkg + "]异常!异常信息[" + e.getMessage() + "]，请登录服务器查看详细信息！";
        }
        
        if (executeResult != 0) {
            logger.error(LOGGER_PREFIX + "解压发布包[" + pkg + "]失败！");
            return "解压发布包[" + pkg + "]失败！执行结果不为0，请登录服务器查看详细信息！";
        }
        
        logger.info(LOGGER_PREFIX + "解压发布包[" + pkg + "]完成.");
        
        // 执行发布脚本
        shell = "sh " + deploy_local_dir + pkg + deploy_shell;
        if (!(new File(deploy_local_dir + pkg + deploy_shell).exists())) {
            logger.error(LOGGER_PREFIX + "发布脚本不存在！");
            return "发布脚本不存在！请确认发布包[" + pkg + "]中包含发布脚本[" + deploy_shell + "].";
        }
        
        executeResult = 1;
        try {
            executeResult = CommandExecutor.executeShell(shell);
        } catch (IOException | IllegalArgumentException | InterruptedException e) {
            logger.error(LOGGER_PREFIX + "部署发布包[" + pkg + "]失败！", e);
            return "部署发布包[" + pkg + "]异常!异常信息[" + e.getMessage() + "]，请登录服务器查看详细信息！";
        }
        
        if (executeResult != 0) {
            logger.error(LOGGER_PREFIX + "部署发布包[" + pkg + "]失败！");
            return "部署发布包[" + pkg + "]失败！执行结果不为0，请登录服务器查看详细信息！";
        }
        
        logger.info(LOGGER_PREFIX + "部署发布包[" + pkg + "]完成.");
        return "服务包[" + pkg + "]部署完成";
    }
    
    @GetMapping(value = "/fuck/{pkg}.do")
    @ResponseBody
    public String downloadAndDeploy(@PathVariable("pkg") String pkg) {
        
        if (StringUtils.isEmpty(pkg)) {
            return "服务发布包名不能为空";
        }
        
        logger.info(LOGGER_PREFIX + "开始下载服务发布包[" + pkg + "].");
        
        String file = "http://" + pkg_remote_host + ":" + pkg_remote_port + pkg_remote_path + pkg + ".zip";
        String localFile = deploy_local_dir + pkg + ".zip";
        
        int result = FileFetcher.fetche(file, localFile);
        if (result != 1) {
            logger.info(LOGGER_PREFIX + "服务发布包下载失败[" + pkg + "].");
            return "服务包[" + pkg + "]下载失败，请登录服务器查询详细信息。";
        }
        
        logger.info(LOGGER_PREFIX + "服务发布包下载完成[" + pkg + "].");

        logger.info(LOGGER_PREFIX + "部署发布包[" + pkg + "]开始.");
        
        // 解压服务发布包
        logger.info(LOGGER_PREFIX + "解压发布包[" + pkg + "]开始.");
        String source = deploy_local_dir + pkg;
        if (StringUtils.isEmpty(source) || !(new File(source + ".zip").exists())) {
            logger.error(LOGGER_PREFIX + "解压发布包[" + pkg + "]失败！文件不存在。");
            return "解压发布包[" + pkg + "]异常!请登录服务器查看详细信息！文件不存在。";
        }
        
        String shell = "unzip " + deploy_local_dir + pkg + ".zip -d " + deploy_local_dir + pkg;
        int executeResult = 1;
        try {
            executeResult = CommandExecutor.executeShell(shell);
        } catch (IOException | IllegalArgumentException | InterruptedException e) {
            logger.error(LOGGER_PREFIX + "解压发布包[" + pkg + "]失败！", e);
            return "解压发布包[" + pkg + "]异常!异常信息[" + e.getMessage() + "]，请登录服务器查看详细信息！";
        }
        
        if (executeResult != 0) {
            logger.error(LOGGER_PREFIX + "解压发布包[" + pkg + "]失败！");
            return "解压发布包[" + pkg + "]失败！执行结果不为0，请登录服务器查看详细信息！";
        }
        
        logger.info(LOGGER_PREFIX + "解压发布包[" + pkg + "]完成.");
        
        // 执行发布脚本
        shell = "sh " + deploy_local_dir + pkg + deploy_shell;
        if (!(new File(deploy_local_dir + pkg + deploy_shell).exists())) {
            logger.error(LOGGER_PREFIX + "发布脚本不存在！");
            return "发布脚本不存在！请确认发布包[" + pkg + "]中包含发布脚本[" + deploy_shell + "].";
        }
        
        executeResult = 1;
        try {
            executeResult = CommandExecutor.executeShell(shell);
        } catch (IOException | IllegalArgumentException | InterruptedException e) {
            logger.error(LOGGER_PREFIX + "部署发布包[" + pkg + "]失败！", e);
            return "部署发布包[" + pkg + "]异常!异常信息[" + e.getMessage() + "]，请登录服务器查看详细信息！";
        }
        
        if (executeResult != 0) {
            logger.error(LOGGER_PREFIX + "部署发布包[" + pkg + "]失败！");
            return "部署发布包[" + pkg + "]失败！执行结果不为0，请登录服务器查看详细信息！";
        }
        
        logger.info(LOGGER_PREFIX + "部署发布包[" + pkg + "]完成.");
        return "服务包[" + pkg + "]部署完成";
    }
}