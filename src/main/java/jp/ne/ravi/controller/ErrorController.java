package jp.ne.ravi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.ne.ravi.utils.DebugUtil;

@Scope("prototype")
@Controller
public class ErrorController {

	@Autowired
	DebugUtil debugUtil;

	/**
	 * エラーページ遷移（共通）.
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/error")
	public String init(Model model) {
		debugUtil.outConsole("To Error Page");
		model.addAttribute("message", "内部サーバーエラー ページを表示できません");
		return "common/error.jsp";
	}

	/**
	 * エラーページ遷移（Http Status 404）.
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/error/404")
	public String status404(Model model) {
		debugUtil.outConsole("To Error Page By 404");
		model.addAttribute("message", "存在しないURLです。");
		return "common/error.jsp";
	}
}
