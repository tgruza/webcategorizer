<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<%@include file="../dynamic/css.jsp" %>

<body class="d-flex h-100 text-center text-white bg-dark">
<div class="cover-container d-flex w-100 h-80 p-3 mx-auto flex-column">

    <%@include file="../dynamic/header.jsp" %>

    <main class="px-3">
        <p class="lead p-1">
            <br>
            <img src='<c:url value="../../resources/img/sadcat.png"/>' width="70">
            <br>
            <br>
            Catson, we have a problem... Your url is not right.
            <br>
            <br>
        </p>
        <p class="lead">
            <a href='<c:url value="/checkUrl"/>'
               class="btn btn-lg btn-secondary fw-bold border-white bg-white">
                Try again
            </a>
        </p>
    </main>

    <%@include file="../dynamic/footer.jsp" %>

</div>


</body>
</html>
