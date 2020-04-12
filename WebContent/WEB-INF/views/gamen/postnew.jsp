<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">
        <h2>新たな楽しみ</h2>

        <form method="POST" action="<c:url value='/posts/create' />">
            <c:import url="_formpost.jsp" />
        </form>

        <p><a href="<c:url value='/posts/mypage?id=${sessionScope.login_user.id}' />">マイページに戻る</a></p>
    </c:param>
</c:import>