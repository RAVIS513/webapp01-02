package jp.ne.ravi.mapper;

import java.util.List;

import jp.ne.ravi.dto.NewsDto;

public interface NewsMapper {

	/**
	 * 全検索
	 * @return
	 */
	List<NewsDto> selectAll();

	/**
	 * 新規作成
	 * @param dto
	 * @return
	 */
	int insert(NewsDto dto);

	/**
	 * 全削除
	 * @return
	 */
	int deleteAll();

}
