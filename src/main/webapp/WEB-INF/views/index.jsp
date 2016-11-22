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
		<link rel="stylesheet" type="text/css" href='<c:url value="/css/index.css"></c:url>'>
	</c:param>
	<c:param name="addHeadScriptFile">
		<script type="text/javascript" src='<c:url value="/js/index.js"></c:url>'></script>
	</c:param>
	<c:param name="addBody">
		<div id="container">
			<div class="section">
				<c:import url="/WEB-INF/views/archives/1.jsp"></c:import>
			</div>
			<div class="section">
				<c:import url="/WEB-INF/views/archives/2.jsp"></c:import>
			</div>
			<div class="section">
				<c:import url="/WEB-INF/views/archives/3.jsp"></c:import>
			</div>
			<div class="section">
				<c:import url="/WEB-INF/views/archives/4.jsp"></c:import>
			</div>
		</div>
		<script>!function(d,s,id){var js,fjs=d.getElementsByTagName(s)[0],p=/^http:/.test(d.location)?'http':'https';if(!d.getElementById(id)){js=d.createElement(s);js.id=id;js.src=p+"://platform.twitter.com/widgets.js";fjs.parentNode.insertBefore(js,fjs);}}(document,"script","twitter-wjs");</script>
		<script type="text/javascript">
			// 処理呼び出し
			var sectionNumber = 4;
// 			var twitterNumber = 5;
			var twitterNumber = 1;
			var histScript = new Hist("#container",sectionNumber,twitterNumber);

			// DOM準備後処理
			$(document).ready(function() {
				histScript.init();
			});

			// LOAD後処理
			$(window).load(function() {
				histScript.main();
			});
		</script>
	</c:param>
</c:import>