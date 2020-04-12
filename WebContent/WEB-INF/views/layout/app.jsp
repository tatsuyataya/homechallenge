<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ja">
    <head>
        <meta charset="UTF-8">
        <title>ホームチャレンジャー</title>
  		<link rel="stylesheet" href="<c:url value='/css/reset.css' />">
        <link rel="stylesheet" href="<c:url value='/css/style.css' />">
    </head>
    <body>
		<div id="app">
			<h1><a class="h1" href= "<c:url value='/toppage'/>">ホームチャレンジャー</a></h1>
		<div class="log">
		<c:choose>
			<c:when test= "${sessionScope.login_user == null}">
					
					<a href= "<c:url value='/users/login'/>">ログイン</a>　<a href= "<c:url value='/users/new'/>">会員登録</a>
 					
 			</c:when>
			<c:otherwise>
			
				<span class="username">
					<c:out value="${sessionScope.login_user.name}"/>さん　
				</span>
				<a href="<c:url value='/posts/mypage?id=${sessionScope.login_user.id}' />">マイページ</a>　
				<a href="<c:url value='/favorite?id=${sessionScope.login_user.id}'/>">お気に入り</a>　
				<a class=logout href="<c:url value='/users/logout'/>">ログアウト</a>
				
			</c:otherwise>
		</c:choose>
		</div>
		</div>
		
		<div id= "content">
			${param.content }
		</div>
		<div id= "footer">
			by T.T
		</div>
    </body>
</html>