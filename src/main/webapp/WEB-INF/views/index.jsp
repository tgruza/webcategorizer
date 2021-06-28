<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<%@include file="dynamic/css.jsp" %>

    <body class="d-flex h-100 text-center text-white bg-dark">
        <div class="cover-container d-flex w-100 h-80 p-3 mx-auto flex-column">

            <%@include file="dynamic/header.jsp" %>

            <div class="mb-1"><h1>Welcome to WebCATegorizer</h1></div>
            <main class="px-3">

                <p class="lead p-1">
                    If you want to check website category, you've come to the perfect place.
                    We are here to help you.
                    <br>
                    <img src='<c:url value="../../resources/img/smile1.png"/>' width="25">
                    Enjoy!
                    <img src='<c:url value="../../resources/img/smile1.png"/>' width="25">
                    <br> </p>
                <p class="lead">
                    <a href='<c:url value="/checkUrl"/>'
                       class="btn btn-lg btn-secondary fw-bold border-white bg-white">
                        Check website
                    </a>
                </p>
            </main>

            <%@include file="dynamic/footer.jsp" %>

        </div>



    </body>
</html>
