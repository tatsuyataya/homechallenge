<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri= "http://java.sun.com/jsp/jstl/fmt" %>
<c:import url="../layout/app.jsp">
    <c:param name="content">
		<h2>お気に入り</h2>
			<div class="tabs">
  				<input id="followerlist" type="radio" name="tab_item" checked>
  				<label class="tab_item" for="followerlist">フォロワー(${follower_count }人)</label>
  				<input id="followedlist" type="radio" name="tab_item">
 				<label class="tab_item" for="followedlist">フォロー中(${followed_count }人)</label>
  				<div class="tab_content" id="followerlist_content">
    				<div class="tab_content_description">
   			  			<ul class="c-txtsp">
   			  				<c:forEach var="a_follower" items="${follower}" varStatus="status">
								<li>${a_follower.follower.name}さん</li>
   			  				</c:forEach>
   			  			</ul>
   				 	</div>
  				</div>
  				<div class="tab_content" id="followedlist_content">
				 	<div class="tab_content_description">
  				    	<ul class="c-txtsp">
  				    		<c:forEach var="a_followed" items="${followed}" varStatus="status">
  				    			<li>${a_followed.followed.name}さん</li>
  				    		</c:forEach>
  				    	</ul>
			    	</div>
	  			</div>
			</div>
		<h3>いいね</h3>
		<table class="post_list">
            <tbody>
                <tr class="koumoku">
                    <th class="post_title">タイトル</th>
                    <th class="post_name">投稿者</th>
                    <th class="post_age_gender">年代、性別</th>
                    <th class="post_update_at">更新日時</th>
                    <th class="report_action"></th>
                </tr>
                <c:forEach var="a_fp" items="${fp}" varStatus="status">
                    <tr class="row${status.count % 2}">
                    	<td class="post_title">${a_fp.good_post.title}</td>
                        <td class="post_name"><c:out value="${a_fp.good_post.writer.name}" />さん</td>
                        <td class="post_age_gender"><c:out value="${a_fp.good_post.writer.age}"/>0代　
                        <c:choose>
                        	<c:when test="${a_fp.good_post.writer.gender == 1 }">
                        		男性
                        	</c:when>
                        	<c:otherwise>
                        		女性
                        	</c:otherwise>
                        </c:choose>
                        </td>
                        <td class="post_date"><fmt:formatDate value='${a_fp.good_post.updated_at}' pattern="yyyy-MM-dd" /></td>
                        <td class="post_action"><a href="<c:url value='/posts/show?id=${a_fp.good_post.id}' />">内容を見る</a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <div id="pagination">
            （全 ${fp_count} 件）<br />
            <c:forEach var="i" begin="1" end="${((fp_count - 1) / 15) + 1}" step="1">
                <c:choose>
                    <c:when test="${i == page}">
                        <c:out value="${i}" />&nbsp;
                    </c:when>
                    <c:otherwise>
                        <a href="<c:url value='/favorite?page=${i}' />"><c:out value="${i}" /></a>&nbsp;
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </div>
    </c:param>
</c:import>