<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix= "c" uri= "http://java.sun.com/jsp/jstl/core" %>
<c:import url="/WEB-INF/views/layout/app.jsp">
	<c:param name="content">
		<c:if test="${hasError}">
            <div id="flush_error">
                ユーザー名かパスワードが間違っています。
            </div>
        </c:if>
        <h2>ログイン</h2>
        <form method= "POST" action= "<c:url value= '/users/login'/>">
        	<label for= "name">ユーザー名</label>
        	<input type= "text" name= "name" value= "${name}" />
        	<br /><br />
        	
        	<label for= "password">パスワード</label>
        	<input type= "password" name= "password" value= "${password }" />
        	<br /><br />
        	
        	<input type= "hidden" name= "_token" value= "${_token }" />
        	<button type= "submit">ログイン</button>
        	<br /><br />
        	<p><a href="<c:url value='/toppage'/>">トップページに戻る</a></p>
        </form>
	</c:param>
</c:import>