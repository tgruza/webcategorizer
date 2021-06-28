<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<%@include file="../dynamic/css.jsp" %>

<body class="d-flex h-100 text-center text-white bg-dark">
<div class="cover-container d-flex w-100 h-80 p-3 mx-auto flex-column">

    <header class="mb-auto" style="width: 900px; align-self: center">
        <div>
            <h3 class="float-md-start mb-0 "><a href='<c:url value="/"/>' class="text-white"
                                                style="text-decoration: none">
                <img src=
                     <c:url value="../../resources/img/images1.png"/> height="50"> WebCATegorizer
            </a>
            </h3>
            <nav class="nav nav-masthead justify-content-center float-md-end">
                <a class="nav-link" aria-current="page" href='<c:url value="/"/>'>Home, sweet Home</a>
                <a class="nav-link active" href='<c:url value="/checkUrl"/>'>Check URL</a>
                <a class="nav-link" href='<c:url value="/contact"/>'>Contact</a>
            </nav>
        </div>
    </header>


    <div class="mb-2">
        <h2>Paste URL to check</h2>
    </div>
    <main class="px-3">
        <p class="lead">

        <form name="send" method="post" action='<c:url value="/checkWebsite"/>'>
            <div class="form-group">
                <label for="url"></label><input type="text" class="form-control" id="url" name="url"
                                                placeholder="https://github.com/tgruza">
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
