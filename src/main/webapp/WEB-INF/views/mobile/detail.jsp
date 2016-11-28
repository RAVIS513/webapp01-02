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
		<link rel="stylesheet" type="text/css" href='<c:url value="/css/mobile/detail.css"></c:url>'>
	</c:param>
	<c:param name="addHeadScriptFile">
		<script type="text/javascript" src='<c:url value="/js/mobile/detail.js"></c:url>'></script>
	</c:param>
	<c:param name="addBody">
		<div id="sp_detail_container">
			<c:if test="${kind == 'namba'}">
				<c:import url="/WEB-INF/views/mobile/archives/namba.jsp"></c:import>
			</c:if>
			<c:if test="${kind == 'tsune'}">
				<c:import url="/WEB-INF/views/mobile/archives/tsune.jsp"></c:import>
			</c:if>
			<c:if test="${kind == 'ken'}">
				<c:import url="/WEB-INF/views/mobile/archives/ken.jsp"></c:import>
			</c:if>
			<c:if test="${kind == 'news'}">
				<c:import url="/WEB-INF/views/mobile/archives/news.jsp"></c:import>
			</c:if>
			<c:if test="${kind == 'airjam'}">
				<c:import url="/WEB-INF/views/mobile/archives/airjam.jsp"></c:import>
			</c:if>
			<c:if test="${kind == 'pizza'}">
				<c:import url="/WEB-INF/views/mobile/archives/pizza.jsp"></c:import>
			</c:if>
			<c:if test="${kind == 'about'}">
				<c:import url="/WEB-INF/views/mobile/archives/about.jsp"></c:import>
			</c:if>
		</div>
		<script async src="//platform.twitter.com/widgets.js" charset="utf-8"></script>
		<script type="text/javascript">
			var histSpScript = new HistSpDetail();

			// LOAD後処理
			$(window).load(function() {
				histSpScript.main();
			});
		</script>
	</c:param>
</c:import>