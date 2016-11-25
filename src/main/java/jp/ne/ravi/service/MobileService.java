package jp.ne.ravi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Scope("prototype")
@Service
public class MobileService {

	@Value("${url.news}")
	private String URL_NEWS;

	@Autowired
	private IndexService indexService;

	/**
	 * メイン処理
	 * @param model
	 */
	public void main(Model model, String url) {
		// NEWSページ
		if (url.equals(URL_NEWS)) {
			// NEWS取得
			indexService.init(model);
		}
		model.addAttribute("kind", url);
	}

}
