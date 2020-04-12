<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="../layout/app.jsp">
    <c:param name="content">
        <h2>新規会員登録ページ</h2>
			<c:if test="${errors != null}">
    			<div id="flush_error">
       				 入力内容にエラーがあります。<br />
        		<c:forEach var="error" items="${errors}">
            		・<c:out value="${error}" /><br />
        		</c:forEach>
				</div>
			</c:if>
			<form method="POST" action="<c:url value='/users/create' />">
				<label for="name">ユーザー名</label><br />
				<input type="text" name="name" value="${user.name }" />
				<br /><br />

<label for="password">パスワード</label><br />
<input type="password" name="password" />
<br /><br />

	<label for= "gender">性別</label><br />
	<input type="radio" name="gender" value="1">男性
	<input type="radio" name="gender" value="2">女性
	<br /><br />

<label for="age">年齢</label><br />
<select name="age">
	<option value="0"<c:if test="${age == 0}"> selected</c:if>>選択してください</option>
    <option value="1"<c:if test="${age == 1}"> selected</c:if>>10代</option>
    <option value="2"<c:if test="${age == 2}"> selected</c:if>>20代</option>
        <option value="3"<c:if test="${age == 3}"> selected</c:if>>30代</option>
    <option value="4"<c:if test="${age == 4}"> selected</c:if>>40代</option>
        <option value="5"<c:if test="${age == 5}"> selected</c:if>>50代</option>
    <option value="6"<c:if test="${age == 6}"> selected</c:if>>60代</option>
        <option value="7"<c:if test="${age == 7}"> selected</c:if>>70代</option>
    <option value="8"<c:if test="${age == 8}"> selected</c:if>>80代</option>
        <option value="9"<c:if test="${age == 9}"> selected</c:if>>90代</option>
</select>
<br /><br />

<input type="hidden" name="_token" value="${_token}" />
<button type="submit">登録する</button>
        </form>

        <p><a href="<c:url value='/toppage' />">トップページに戻る</a></p>
    </c:param>
</c:import>