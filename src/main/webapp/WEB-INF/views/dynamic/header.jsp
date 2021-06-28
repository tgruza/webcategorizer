<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<header class="mb-auto" style="width: 900px; align-self: center">
    <div>
        <h3 class="float-md-start mb-0 "><a href='<c:url value="/"/>' class="text-white" style="text-decoration: none">
            <img src=<c:url value="../../resources/img/images1.png"/> height="50"> WebCATegorizer
        </a>
        </h3>
        <nav class="nav nav-masthead justify-content-center float-md-end">
            <a class="nav-link active" aria-current="page" href='<c:url value="/"/>'>Home, sweet Home</a>
            <a class="nav-link" href='<c:url value="/checkUrl"/>'>Check URL</a>
            <a class="nav-link" href='<c:url value="/contact"/>'>Contact</a>
        </nav>
    </div>
</header>