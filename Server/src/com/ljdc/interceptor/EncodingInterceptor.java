package com.ljdc.interceptor;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import org.apache.struts2.StrutsStatics;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;

/**
 * Created with IntelliJ IDEA
 * User:邹旭
 * Date:2017/3/3 0003
 * Time:下午 2:21
 * Desc:略
 */
public class EncodingInterceptor extends AbstractInterceptor {

    /**
     * Struts2编码拦截器
     */
    @Override
    public String intercept(ActionInvocation invocation) throws Exception {
        // TODO Auto-generated method stub

        ActionContext actionContext = invocation.getInvocationContext();
        HttpServletRequest request = (HttpServletRequest) actionContext.get(StrutsStatics.HTTP_REQUEST);
        System.out.println("Encoding Intercept...");
        /**
         * 此方法体对GET 和 POST方法均可
         */
        if (request.getMethod().compareToIgnoreCase("post") >= 0) {
            try {
                request.setCharacterEncoding("UTF-8");
            } catch (UnsupportedEncodingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } else {

            Iterator iter = request.getParameterMap().values().iterator();
            while (iter.hasNext()) {
                String[] parames = (String[]) iter.next();
                for (int i = 0; i < parames.length; i++) {
                    try {
                        parames[i] = new String(parames[i].getBytes("ISO8859-1"), "UTF-8");//此处GBK与页面编码一样
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return invocation.invoke();
    }
}

