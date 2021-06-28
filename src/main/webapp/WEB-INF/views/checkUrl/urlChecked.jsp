<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<%@include file="../dynamic/css.jsp" %>

<body class="d-flex h-100 text-center text-white bg-dark">
<div class="cover-container d-flex w-100 h-80 p-3 mx-auto flex-column">

    <%@include file="../dynamic/header.jsp" %>

    <main class="px-3">

        <h3>Website URL:</h3>
        <h5><p class="lead">${website.url}</p></h5>
        <br>
        <h3>Website Category:</h3>
        <c:forEach items="${website.categories}" var="category">
            <h5><p class="lead">${category.name}</p></h5>
        </c:forEach>
        <br>
        <p class="lead">
            <a href='<c:url value="/checkUrl"/>' class="btn btn-lg btn-secondary fw-bold border-white bg-white">
                Back
            </a>
        </p>
    </main>

    <%@include file="../dynamic/footer.jsp" %>
</div>
</body>
</html>
