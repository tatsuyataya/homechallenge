<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix= "c" uri= "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix= "fmt" uri= "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<c:import url= "../layout/app.jsp">
	<c:param name="content">
	        <c:if test="${flush != null}">
            <div id="flush_success">
                <c:out value="${flush}"></c:out>
            </div>
        </c:if>
		<h2>ホームチャレンジャーへようこそ</h2>
		<p>ここではユーザーさんが考えた自宅でできる遊びや趣味に関する記事を閲覧でき、面白いと思ったことを自分でもやってみたりすることができます。<br />
		会員登録してアカウントを作成すれば、自分が考えた遊びや趣味を書くこともでき、他のユーザーの記事にいいねしたり、お気に入りのユーザーのフォローもできます。
		<table>
            <tbody>
                <tr class="koumoku">
                    <th class="postfirst">タイトル</th>
                    <th class="postfirst">投稿者</th>
                    <th class="postfirst">年代、性別</th>
                    <th class="postfirst">更新日時</th>
                    <th class="postfirst"></th>
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
                        <a href="<c:url value='/toppage?page=${i}' />"><c:out value="${i}" /></a>&nbsp;
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </div>
	</c:param>
</c:import>
