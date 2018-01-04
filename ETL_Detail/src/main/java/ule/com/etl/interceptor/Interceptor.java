package ule.com.etl.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import ule.com.etl.model.User;


public class Interceptor implements HandlerInterceptor {
	
	private List<String> allowedPath;

	public List<String> getAllowedPath() {
		return allowedPath;
	}

	public void setAllowedPath(List<String> allowedPath) {
		this.allowedPath = allowedPath;
	}

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		User user=(User) request.getSession().getAttribute("user");
		if(user!=null){
			return true;
		}
		String path = request.getRequestURI();
		for(String uri:allowedPath){
			if(path.endsWith(uri)){
				return true;
			}
		}
//		System.out.println(request.getRequestURI());
//		System.out.println("拦截器");
		response.sendRedirect("login.jsp");
		return false;
	}
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
	}

	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
	}

}
