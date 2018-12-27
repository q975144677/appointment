package springboot.advice;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandle {

	@ExceptionHandler(value = Exception.class)
	public  ModelAndView error(Exception e , HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("error");
	mav.addObject("e",e);
	mav.addObject("url",request.getRequestURL());
		
		return mav;
	}
	
	
}
