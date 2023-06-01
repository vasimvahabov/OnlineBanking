package com.onlinebanking.interceptors;

//import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import com.onlinebanking.models.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

//@Component
public class AppInterceptor implements HandlerInterceptor{
   
  @Override
  public boolean preHandle(HttpServletRequest request,
                                HttpServletResponse response,Object object) throws Exception{
    System.out.println("In Pre Handle Interceptor Method...");
    
    if(request.getRequestURI().startsWith("/app")){		
      HttpSession session=request.getSession();
      String token=(String)session.getAttribute("token");
      User user=(User)session.getAttribute("user");
      Boolean authenticated=(Boolean)session.getAttribute("authenticated");
      
      if(token==null || user==null || authenticated==null){
        response.sendRedirect("/login");
        return false;
      }
    }
	
    return true;
  }
  
  @Override
  public void postHandle(HttpServletRequest request,HttpServletResponse response,
                   		  Object object,ModelAndView modelAndView) throws Exception{
    System.out.println("In Post Handle Interceptor Method...");
  }
  
  @Override
  public void afterCompletion(HttpServletRequest request,
                 HttpServletResponse response,Object object,Exception error) throws Exception{
    System.out.println("In After Completion Interceptor Method...");
  }
}
