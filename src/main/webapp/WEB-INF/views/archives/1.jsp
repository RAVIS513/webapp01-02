<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%-- index/page 1 --%>
<div class="p1_split">
	<div id="p1_top">
		<img alt="Endless Trip" src='<c:url value="/img/Logo.png"></c:url>'>
	</div>
	<div id="p1_overview">
		<p>
			Hi-STANDARDのニュースをいち早く知りたい！！<br>
			そんな思いから作成したサイトです！<br>
		</p>
	</div>
</div>
<div class="p1_split">
	<div id="p1_newsTop"  class="text-v-aling">
		<p>NEWS!!</p>
	</div>
	<div id="p1_newsMain">

		<c:choose>
			<c:when test="${empty newsList}">
				ニュース取得に失敗しました
			</c:when>
			<c:otherwise>
				<c:forEach var="list" items="${newsList}">
					<div class="p1_newsBox">
						<a href="${list.url}"></a>
						<div class="p1_newsTitle">
							<table>
								<tr>
									<td class="p1_newsTitle_L">${list.title}</td>
									<td class="p1_newsTitle_R">
										<c:if test="${list.newFlag == '1'}">
											<img alt="New" src='<c:url value="/img/newFlag.png"></c:url>'>
										</c:if>
									</td>
								</tr>
							</table>
						</div>
					</div>
				</c:forEach>
			</c:otherwise>
		</c:choose>

	</div>
</div>