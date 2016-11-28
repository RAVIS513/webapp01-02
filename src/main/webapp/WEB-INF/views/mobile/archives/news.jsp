<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div id="sp_detail_header">
	<p class="v-adjust-parent">NEWS!!</p>
</div>
<div id="sp_detail_main">
	<div class="p1_newsBox" style="height:1px;"></div>
	<c:choose>
		<c:when test="${empty newsList}">
			ニュース取得に失敗しました
		</c:when>
		<c:otherwise>
			<c:forEach var="list" items="${newsList}">
				<div class="p1_newsBox">
					<a href="${list.url}"></a>
					<div class="p1_newsTitle v-adjust-parent">
						<table>
							<tr>
								<td class="p1_newsTitle_L">${list.title}</td>
								<c:if test="${list.newFlag == '1'}">
									<td class="p1_newsTitle_R">
										<img alt="New" src='<c:url value="/img/newFlag.png"></c:url>'>
									</td>
								</c:if>
							</tr>
						</table>
					</div>
				</div>
			</c:forEach>
		</c:otherwise>
	</c:choose>
</div>
<div id="sp_detail_footer">
	<div class="sp_detail_footer_rbox line-height-1">
		<a href="/endlesstrip/sp"></a>
		戻る
	</div>
</div>