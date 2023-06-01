package com.onlinebanking.config;

import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.ComponentScan; 
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.thymeleaf.spring6.templateresolver.SpringResourceTemplateResolver;
import com.onlinebanking.interceptors.AppInterceptor;

@Configuration
//@ComponentScan(basePackages = {"com.onlinebanking"})
public class AppConfig extends WebMvcConfigurationSupport{

  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry){
     registry.addResourceHandler("css/**","images/**","js/**")
     .addResourceLocations("classpath:/static/css/",
                                   "classpath:/static/images/","classpath:/static/js/");
  }
  
  @Bean
  public SpringResourceTemplateResolver resolver(){
    SpringResourceTemplateResolver htmlResolver=new SpringResourceTemplateResolver();
    htmlResolver.setPrefix("WEB-INF/html/");
    htmlResolver.setSuffix(".html");
    return htmlResolver;
  }
	
  @Override
  public void addInterceptors(InterceptorRegistry registry){
    registry.addInterceptor(new AppInterceptor()).addPathPatterns("/app/*");
  }	 
}
