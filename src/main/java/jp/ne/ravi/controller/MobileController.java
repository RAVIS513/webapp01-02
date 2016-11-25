package jp.ne.ravi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jp.ne.ravi.service.MobileService;
import jp.ne.ravi.utils.DebugUtil;

@Scope("prototype")
@Controller
public class MobileController {

	@Autowired
	private MobileService mobileService;

	@Autowired
	private DebugUtil debugUtil;

	/**
	 * 初期表示
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/sp", method = RequestMethod.GET)
	public String init(Model model) {
		debugUtil.outConsole("sp/init");
		return "mobile/index.jsp";
	}

	/**
	 * 詳細表示
	 * @param model
	 * @param target
	 * @return
	 */
	@RequestMapping(value = "/sp/{url}", method = RequestMethod.GET)
	public String detail(Model model, @PathVariable String url) {
		debugUtil.outConsole("sp/init/" + url);
		mobileService.main(model, url);
		return "mobile/detail.jsp";
	}

}
