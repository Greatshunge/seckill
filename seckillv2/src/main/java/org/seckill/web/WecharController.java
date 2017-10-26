package org.seckill.web;

import org.seckill.utils.CheckWecharUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@Controller
@RequestMapping(value = "/wx.do")
public class WecharController extends HttpServlet{

    public void verificAtion(HttpServletRequest req, HttpServletResponse res){

        String signature = req.getParameter("signature");
        String timestamp = req.getParameter("timestamp");
        String nonce = req.getParameter("nonce");
        String echostr = req.getParameter("echostr");

        try {
            PrintWriter out = res.getWriter();
            if(CheckWecharUtil.checkSignature(signature,timestamp,nonce)){
                out.print(echostr);
            }
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @RequestMapping(method = RequestMethod.POST)
    public void getMessage(HttpServletRequest req,HttpServletResponse res){

        res.setCharacterEncoding("utf-8");
        try {
            req.setCharacterEncoding("utf-8");
            StringBuffer sb = new StringBuffer();
            InputStream in = req.getInputStream();
            InputStreamReader isr = new InputStreamReader(in,"UTF-8");
            BufferedReader br = new BufferedReader(isr);
            String message = "";
            while ((message=br.readLine())!=null){
                sb.append(message);
            }
            String xml = sb.toString();

            String result = "hehe";

            PrintWriter pw = res.getWriter();
            pw.print(result);

            pw.flush();
            pw.close();

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
