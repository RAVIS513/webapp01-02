package jp.ne.ravi.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class Interceptor implements HandlerInterceptor{

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO 自動生成されたメソッド・スタブ

	}

	/**
	 * PC・モバイル判定
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		Boolean result = true;
		String contextPath = request.getContextPath();

		// Mobile to PC
		if (!findMobile(request)) {
			if (request.getRequestURI().startsWith(contextPath + "/sp")) {
				response.sendRedirect(contextPath);
				result = false;
			}
		}

		// PC to Mobile
		if (findMobile(request)) {
			if (!request.getRequestURI().startsWith(contextPath + "/sp")) {
				response.sendRedirect(contextPath + "/sp/");
				result = false;
			}
		}
		return result;
	}

	/**
	 * UserAgent判定
	 * @param request
	 * @return
	 */
	private boolean findMobile(HttpServletRequest request) {
		Boolean result = false;

		// UserAgent取得
		String ua = request.getHeader("User-Agent");

		// UserAgent判定
		if (ua.indexOf("Windows Phone") != -1
				|| ua.indexOf("iPhone") != -1
				|| ua.indexOf("iPod") != -1
				|| (ua.indexOf("Android") != -1 && ua.indexOf("Mobile") != -1)
				|| (ua.indexOf("Firefox") != -1 && ua.indexOf("Mobile") != -1)
				|| ua.indexOf("BlackBerry") != -1) {
			result = true;
		}

		return result;
	}

}
