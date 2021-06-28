<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<%@include file="../dynamic/css.jsp" %>

<body class="d-flex h-100 text-center text-white bg-dark">
<div class="cover-container d-flex w-100 h-80 p-3 mx-auto flex-column">

    <%@include file="../dynamic/header.jsp" %>


    <div class="mb-2">
        <h2>Paste URL to check</h2>
    </div>
    <main class="px-3">
        <p class="lead">

        <form name="send" method="post" action='<c:url value="/checkWebsite"/>'>
        <div class="form-group">
            <label for="url"></label><input type="text" class="form-control" id="url" name="url" placeholder="https://github.com/tgruza">
                <br>
            </div>

            <button type="submit" class="btn btn-lg btn-secondary fw-bold border-white bg-white" id="sendButton">
                Submit
            </button>
        </form>

    </main>

    <%@include file="../dynamic/footer.jsp" %>
</div>
</body>
</html>
