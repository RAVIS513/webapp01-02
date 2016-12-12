package jp.ne.ravi.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import jp.ne.ravi.dao.NewsDao;
import jp.ne.ravi.dto.NewsDto;
import jp.ne.ravi.exception.WebException;
import jp.ne.ravi.utils.DbSessionUtil;
import jp.ne.ravi.utils.StringUtil;
import jp.ne.ravi.web.dto.WebNewDto;

@Scope("prototype")
@Service
public class IndexService {

	@Autowired
	private StringUtil stringUtil;

	public void init(Model model) {
		DbSessionUtil sessionUtil = new DbSessionUtil();
		SqlSession session = null;
		try {
			// DB接続設定
			session = sessionUtil.open();

			// ニュースコンテンツ取得
			NewsDao dao = new NewsDao(session);
			model.addAttribute("newsList", getNews(dao.selectAll()));

			// DB正常終了
			sessionUtil.close(session);

		} catch (Exception e) {
			e.printStackTrace();
			if (session != null) {
				// DB異常終了
				sessionUtil.rollback(session);
			}
			throw new WebException("Hist Init Error", e);
		}
	}

	/**
	 * ニュースコンテンツ取得
	 * @param dto
	 * @return
	 */
	private List<WebNewDto> getNews(List<NewsDto> dto) {
		List<WebNewDto> list = new ArrayList<WebNewDto>();
		if (dto != null && !dto.isEmpty()) {
			for (NewsDto d : dto) {
				WebNewDto wd = new WebNewDto();
//				wd.setTitle(d.getTitle());
				wd.setTitle(stringUtil.repositCharacter(d.getTitle()));
				wd.setUrl(d.getUrl());
				wd.setNewFlag(d.getNewFlag());
				list.add(wd);
			}
		}
		return list;
	}
}
