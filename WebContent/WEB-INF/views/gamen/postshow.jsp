<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">
    <div id="show">    
        <c:choose>
            <c:when test="${post != null}">
                <h2><c:out value= "${post.title}"/></h2>
                <p>投稿者:<c:out value="${post.writer.name }" />さん　　<c:out value="${post.writer.age }"/>0代　
                <c:choose>
                	<c:when test="${post.writer.gender == 1 }">
                		男性
                	</c:when>
                	<c:otherwise>
                		女性
                	</c:otherwise>
                	</c:choose>
                	</p>
                <p>更新日時:<fmt:formatDate value="${post.updated_at}" pattern="yyyy-MM-dd" /></p>
                <p>内容<br /><c:out value= "${post.content}"/></p>
               	<div id=show_option>
                <c:choose>
                	<c:when test="${sessionScope.login_user.id == null }">
                	</c:when>
              		<c:when test="${sessionScope.login_user.id == post.writer.id}">
                		<p><a href="<c:url value='/posts/edit?id=${post.id}' />">この記事を編集する</a></p>
        			</c:when>
                	<c:otherwise>
               		<span class=good>
               		<c:choose>
               		<c:when test= "${good_check == 0}">
               				<a class="heart"href="<c:url value='/goodon?good_post=${post.id}'/>">♡</a>
               			</c:when>
               			<c:otherwise>
               				<a class="heart" href="<c:url value='/goodoff?good_post=${post.id}'/>">♥</a>
               			</c:otherwise>
               		</c:choose>
               		</span>
               		<c:choose>
              			<c:when test= "${follow_check == 0}">
              				<a href="<c:url value='/follow/on?writer=${post.writer.id}&post=${post.id}'/>"><c:out value="${post.writer.name }"/>さんをフォローする</a>
              			</c:when>
              			<c:otherwise>
              				<c:out value="${post.writer.name }" />さんをフォロー中(<a href="<c:url value='/follow/off?writer=${post.writer.id}&post=${post.id}'/>">フォローを解除する</a>)
              			</c:otherwise>
              		</c:choose>
              	
              	</c:otherwise>
                </c:choose> 
                </div>
            </c:when>
            <c:otherwise>
                <h2>お探しのデータは見つかりませんでした。</h2>
            </c:otherwise>
        </c:choose>
      <p><a href="<c:url value= '/toppage'/>">一覧に戻る</a></p>
    </div>
    </c:param>
</c:import>