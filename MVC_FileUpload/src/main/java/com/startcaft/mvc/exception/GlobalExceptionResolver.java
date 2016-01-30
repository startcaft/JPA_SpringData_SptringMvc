package com.startcaft.mvc.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

/**
 * 全局异常处理器，需要为SpringMVC配置这个全局异常处理器。
 * 处理思路：系统里包括两种异常，预期异常和RuntimeException。前者是通过捕获异常而获取异常信息，后者主要通过规范代码开发，测试来减少发生。
 * 
 * 系统的dao，service，controller出现异常都向上抛出，知道SpringMVC的前端控制器。
 * 
 * 前端控制器交由异常处理器进行异常处理。【实现HandlerExceptionResolver接口的类为异常处理器】
 */
public class GlobalExceptionResolver implements HandlerExceptionResolver {
	
	/**
	 * 参数handler 就是处理器适配器要执行的Handler对象
	 * 参数ex 就是被前端控制器截获到的Exception对象
	 */
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {
		
		/**
		 * 如果截获的异常是 自定义异常，则直接去除异常信息。
		 * 如果不是自定义异常，则构建一个 自定义异常 对象，异常信息为"未知信息"
		 */
		
		CustomException customException = null;
		
		if (ex instanceof CustomException) {
			customException = (CustomException) ex;
		}
		else{
			customException = new CustomException("未知异常");
		}
		
		/**
		 * 将构建好的 自定义异常 传递到页面中
		 */
		ModelAndView mv = new ModelAndView();
		mv.addObject("exception", customException);
		mv.setViewName("exception");
		
		return mv;
	}

}
