package jp.ne.ravi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.ne.ravi.service.IndexService;
import jp.ne.ravi.utils.DebugUtil;

@Scope("prototype")
@Controller
public class IndexController {

	@Autowired
	private IndexService indexService;

	@Autowired
	private DebugUtil debugUtil;

	/**
	 * 初期表示
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/")
	public String init(Model model) {
		debugUtil.outConsole("init");
		indexService.init(model);
		return "index.jsp";
	}

}
