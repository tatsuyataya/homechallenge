<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">
        <h2>編集ページ</h2>

        <form method="POST" action="<c:url value='/posts/update' />">
            <c:import url="_formpost.jsp" />
        </form>
        
        <p>
        	<a  class=delete href="#" onclick="confirmDestroy();">この記事を削除する</a>
        </p>
        <form method="POST" action="${pageContext.request.contextPath}/posts/destroy?id=${sessionScope.post_id}">
            <input type="hidden" name="_token" value="${_token}" />
        </form>
        <script>
        function confirmDestroy() {
            if(confirm("本当に削除してよろしいですか？")) {
                document.forms[1].submit();
            }
        }

        </script>
        <p><a href="<c:url value='/posts/mypage?id=${sessionScope.login_user.id}' />">マイページに戻る</a></p>
    </c:param>
</c:import>