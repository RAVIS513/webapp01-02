package jp.ne.ravi.interceptor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import jp.ne.ravi.constant.Const;

public class Interceptor implements HandlerInterceptor{

	private Logger logger = LogManager.getLogger(Interceptor.class);

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
	 * コントローラメソッド前実行処理
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		Boolean result = true;
		String contextPath = request.getContextPath();

		// PC・モバイル判定
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

		// 接続先情報出力
		if (request.getRequestURI().equals(contextPath)
				|| request.getRequestURI().equals(contextPath + "/")
				|| request.getRequestURI().equals(contextPath + "/sp")
				|| request.getRequestURI().equals(contextPath + "/sp/")) {
			outRequestInfo(request);
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

	/**
	 * 接続先情報ログ出力
	 * @param request
	 */
	private void outRequestInfo(HttpServletRequest request) {
		// UserAgent取得
		String ua = request.getHeader("User-Agent");
		// 接続先IPアドレス取得
		String ip = request.getRemoteAddr();
		// 接続先ブラウザ取得
		String bs = getBrowser(ua);
		// ログ出力
		logger.info("[AccessLog][ip:" + ip + "][browser:" + bs + "]");
	}

	/**
	 * ブラウザ種別判定
	 * @param ua
	 * @return
	 */
	private String getBrowser(String ua) {
		if (isEdge(ua)) {
			return Const.BROWSER_EDGE;
		}
		if (isIE(ua)) {
			return Const.BROWSER_IE;
		}
		if (isFirefox(ua)) {
			return Const.BROWSER_FIREFOX;
		}
		if (isChrome(ua)) {
			return Const.BROWSER_CHROME;
		}
		if (isSafari(ua)) {
			return Const.BROWSER_SAFARI;
		}
		if (isOpera(ua)) {
			return Const.BROWSER_OPERA;
		}
		return Const.BROWSER_UNKNOWN;
	}

	/**
	 * Is Browser Edge
	 * @param ua
	 * @return
	 */
	private boolean isEdge(String ua) {
		Pattern pt = Pattern.compile(".*(Edge).*");
		Matcher mc = pt.matcher(ua);
		return mc.matches();
	}

	/**
	 * Is Browser IE
	 * @param ua
	 * @return
	 */
	private boolean isIE(String ua) {
		Pattern pt1 = Pattern.compile(".*((MSIE)+[0-9]\\.[0-9]).*");
		Matcher mc1 = pt1.matcher(ua);

		Pattern pt2 = Pattern.compile(".*(Windows NT).*");
		Matcher mc2 = pt2.matcher(ua);

		if (mc1.matches() || mc2.matches()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Is Browser Firefox
	 * @param ua
	 * @return
	 */
	private boolean isFirefox(String ua) {
		Pattern pt = Pattern.compile(".*((Firefox/)+[0-9]\\.[0-9]\\.?[0-9]?).*");
		Matcher mc = pt.matcher(ua);
		return mc.matches();
	}

	/**
	 * Is Browser Chrome
	 * @param ua
	 * @return
	 */
	private boolean isChrome(String ua) {
		Pattern pt = Pattern.compile(".*((Chrome)+/?[0-9]\\.?[0-9]?).*");
		Matcher mc = pt.matcher(ua);
		return mc.matches();
	}

	/**
	 * Is Browser Safari
	 * @param ua
	 * @return
	 */
	private boolean isSafari(String ua) {
		Pattern pt = Pattern.compile(".*((Version/)+[0-9]\\.?[0-9]?\\.?[0-9]? Safari).*");
		Matcher mc = pt.matcher(ua);
		return mc.matches();
	}

	/**
	 * Is Browser Opera
	 * @param ua
	 * @return
	 */
	private boolean isOpera(String ua) {
		Pattern pt = Pattern.compile(".*((Opera)+/? ?[0-9]\\.[0-9][0-9]?).*");
		Matcher mc = pt.matcher(ua);
		return mc.matches();
	}
}
