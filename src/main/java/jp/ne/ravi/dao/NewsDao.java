package jp.ne.ravi.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import jp.ne.ravi.dto.NewsDto;
import jp.ne.ravi.mapper.NewsMapper;

public class NewsDao {

	private Logger logger = LogManager.getLogger(NewsDao.class);

	private NewsMapper mapper = null;

	public NewsDao(SqlSession session) {
		this.mapper = session.getMapper(NewsMapper.class);
	}

	/**
	 * 全検索
	 * @return
	 */
	public List<NewsDto> selectAll() {
		logger.debug("selectAll Run");
		return mapper.selectAll();
	}

	/**
	 * 新規作成
	 * @param dto
	 * @return
	 */
	public int insert(NewsDto dto) {
		logger.debug("insert Run");
		return mapper.insert(dto);
	}

	/**
	 * 全削除
	 * @return
	 */
	public int deleteAll() {
		logger.debug("deleteAll Run");
		return mapper.deleteAll();
	}

}
