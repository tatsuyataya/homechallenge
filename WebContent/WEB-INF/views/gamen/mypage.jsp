<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri= "http://java.sun.com/jsp/jstl/fmt" %>
<c:import url="../layout/app.jsp">
    <c:param name="content">
		<c:if test="${flush_create != null}">
            <div id="flush_create">
                <c:out value="${flush_create}"></c:out>
            </div>
        </c:if>
        <h2>マイページ</h2>
        <p><c:out value="${sessionScope.login_user.name }"/>さん　<c:out value="${sessionScope.login_user.age }"/>0代　
        <c:choose>
        	<c:when test= "${sessionScope.login_user.gender == 1}">
        		男性
			</c:when>
			<c:otherwise>
				女性
			</c:otherwise>
		</c:choose>
		</p>
		<table id="post_list">
            <tbody>
                <tr>
                    <th class="post_title">タイトル</th>
                    <th class="post_name">投稿者</th>
                    <th class="post_age_gender">年代、性別</th>
                    <th class="post_update_at">更新日時</th>
                    <th class="report_action"></th>
                </tr>
                <c:forEach var="post" items="${posts}" varStatus="status">
                    <tr class="row${status.count % 2}">
                    	<td class="post_title">${post.title}</td>
                        <td class="post_name"><c:out value="${post.writer.name}" />さん</td>
                        <td class="post_age_gender"><c:out value="${post.writer.age }"/>0代　
                        <c:choose>
                        	<c:when test="${post.writer.gender == 1 }">
                        		男性
                        	</c:when>
                        	<c:otherwise>
                        		女性
                        	</c:otherwise>
                        </c:choose>
                        </td>
                        <td class="post_date"><fmt:formatDate value='${post.updated_at}' pattern="yyyy-MM-dd" /></td>
                        <td class="post_action"><a href="<c:url value='/posts/show?id=${post.id}' />">内容を見る</a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        
         <div id="pagination">
            （全 ${posts_count} 件）<br />
            <c:forEach var="i" begin="1" end="${((posts_count - 1) / 15) + 1}" step="1">
                <c:choose>
                    <c:when test="${i == page}">
                        <c:out value="${i}" />&nbsp;
                    </c:when>
                    <c:otherwise>
                        <a href="<c:url value='/posts/mypage?page=${i}' />"><c:out value="${i}" /></a>&nbsp;
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </div>
        <p><a href="<c:url value='/posts/new' />">投稿する</a></p>
        <p><a href="<c:url value='/favorite?id=${sessionScope.login_user.id }' />">お気に入りリスト</a></p>
        <p><a href="<c:url value='/users/edit?id=${sessionScope.login_user.id}' />">プロフィールを更新する</a></p>
       
    </c:param>
</c:import>