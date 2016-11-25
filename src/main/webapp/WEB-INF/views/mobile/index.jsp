<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="/WEB-INF/views/common/abstract.jsp">
	<c:param name="addHeadTitle">
		<%-- ページタイトル --%>
		EndlessTrip
	</c:param>
	<c:param name="addHeadKeyword">
		<meta name="keywords" content="Hi-STANDARD,ハイスタ,まとめ">
	</c:param>
	<c:param name="addHeadMeta">
		<%-- ページ説明 --%>
		<meta name="description" content="Hi-STANDARDのニュース・ツイートまとめ">
	</c:param>
	<c:param name="addHeadStyleSheet">
		<link rel="stylesheet" type="text/css" href='<c:url value="/css/mobile/index.css"></c:url>'>
	</c:param>
	<c:param name="addHeadScriptFile">
		<script type="text/javascript" src='<c:url value="/js/mobile/index.js"></c:url>'></script>
	</c:param>
	<c:param name="addBody">
		<div id="sp_index_container">
			<div id="sp_index_logo">
				<img alt="Endless Trip" src='<c:url value="/img/mobile/Logo.png"></c:url>'>
			</div>
			<div id="sp_index_header">
				<p>Hi-STANDARDファンサイトです</p>
			</div>
			<div id="sp_index_main">
				<div class="sp_index_tile tile_style_1">
					<a href="/endlesstrip/sp/namba"></a>
					<p>NAMBA</p>
				</div>
				<div class="sp_index_tile tile_style_2">
					<a href="/endlesstrip/sp/tsune"></a>
					<p>TSUNE</p>
				</div>
				<div class="sp_index_tile tile_style_3">
					<a href="/endlesstrip/sp/ken"></a>
					<p>KEN</p>
				</div>
				<div class="sp_index_tile tile_style_4">
					<a href="/endlesstrip/sp/news"></a>
					<p>NEWS</p>
				</div>
				<div class="sp_index_tile tile_style_5">
					<a href="/endlesstrip/sp/airjam"></a>
					<p>AIRJAM</p>
				</div>
				<div class="sp_index_tile tile_style_6">
					<a href="/endlesstrip/sp/pizza"></a>
					<p>PIZZA</p>
				</div>
				<div class="sp_index_tile tile_style_6">
					<a href="http://hi-standard.jp/index_pc.php"></a>
					<p>OFFICIAL</p>
				</div>
				<div class="sp_index_tile tile_style_4">
					<a href="http://hi-standard-store.jp/"></a>
					<p>WEB STORE</p>
				</div>
				<div class="sp_index_tile tile_style_5">
					<a href="https://ja-jp.facebook.com/histandardofficial/"></a>
					<p>FACEBOOK</p>
				</div>
			</div>
			<div id="sp_index_footer">
				<a href="/endlesstrip/sp/about"></a>
				<p>本サイトについて</p>
			</div>
		</div>
		<script type="text/javascript">
			var histSpScript = new HistSp();

			// LOAD後処理
			$(window).load(function() {
				histSpScript.main();
			});
		</script>
	</c:param>
</c:import>