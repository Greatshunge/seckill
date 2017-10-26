/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.seckill.utils;

import org.apache.commons.exec.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

/**
 *
 * @author shawn
 * @copyright Halo
 * @datetime 2017-4-28 15:15:27
 * @version 1.0
 */
public class CommandExecutor {
    
    private static final Logger logger = LoggerFactory.getLogger(CommandExecutor.class);
    
    public static final int executeShell(final String shell) throws IllegalArgumentException, IOException, InterruptedException {

        CommandLine cmdLine = CommandLine.parse(shell);
        
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ByteArrayOutputStream errorStream = new ByteArrayOutputStream();
        PumpStreamHandler streamHandler = new PumpStreamHandler(outputStream, errorStream);
        DefaultExecuteResultHandler resultHandler = new DefaultExecuteResultHandler();

        ExecuteWatchdog watchdog = new ExecuteWatchdog(60 * 1000);
        Executor executor = new DefaultExecutor();
        executor.setExitValue(1);
        executor.setWatchdog(watchdog);
        executor.setStreamHandler(streamHandler);
        executor.execute(cmdLine, resultHandler);
        
        int osize = outputStream.size();
        int esize = errorStream.size();
        while (!resultHandler.hasResult()) {
            if (osize < outputStream.size()) {
                BufferedReader br = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(outputStream.toByteArray(), osize, outputStream.size() - osize)));
                String line = null;
                while ((line = br.readLine()) != null) {                       
                    logger.info(line);
                }
                
                osize = outputStream.size();
                try {
                    br.close();
                } catch (Exception e) {
                    // do nothing
                }
            }
            if (esize < errorStream.size()) {
                BufferedReader br = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(errorStream.toByteArray(), esize, errorStream.size() - esize)));
                String line = null;
                while ((line = br.readLine()) != null) {                    
                    logger.info(line);
                }
                
                esize = errorStream.size();
                try {
                    br.close();
                } catch (Exception e) {
                    // do nothing
                }
            }
        }
        
        // some time later the result handler callback was invoked so we
        // can safely request the exit value
        resultHandler.waitFor();

        System.out.println(outputStream.toString("gbk"));
        
        try {
            outputStream.close();
            errorStream.close();
        } catch (Exception e) {
            // do nothing
        }
        
        return resultHandler.getExitValue();
    }
    
    public static void main(String[] args) throws Exception {
        System.out.println("=== " + CommandExecutor.executeShell("ping 192.168.3.150"));
    }
}