<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<%@include file="../dynamic/css.jsp" %>


<body class="d-flex h-100 text-center text-white bg-dark">
<div class="cover-container d-flex w-100 h-80 p-3 mx-auto flex-column">

    <%@include file="../dynamic/header.jsp" %>

    <div class="mb-2"><h1>Contact</h1></div>
    <main class="px-3">
        <p class="lead" style="font-size: 18px;">
            If you want to find me just visit my LinkedIn profile
            <br>
            <a href='<c:url value="https://www.linkedin.com/in/tomaszgruza/"/>' class="text-white" style="text-decoration: none">
                <img src=<c:url value="../../../resources/img/linkedin.png"/> width="30">
                Click Right Here
                <img src=<c:url value="../../../resources/img/linkedin.png"/> width="30">
            </a>
            <br>
            <br>I heard it's recommend to check my GitHub profile as well
            <br>
            <a href='<c:url value="https://github.com/tgruza"/>' class="text-white" style="text-decoration: none">
                <img src=<c:url value="../../../resources/img/githubicon.png"/> height="30">
                So click here too!
                <img src=<c:url value="../../../resources/img/githubicon.png"/> height="30">
            </a>
            <br>
            <br>You will find very interesting stuff there... if not, call me!
            <br>
            <img src=<c:url value="../../../resources/img/call.jpg"/> width="30">+48 730 525 787
            <br>
            <br>or just write me an e-mail
            <br>
            <a href='<c:url value="mailto:tgruza@wp.pl"/>' class="text-white" style="text-decoration: none">
                <img src=<c:url value="../../../resources/img/mail.png"/> height="25">tgruza@wp.pl
            </a>
            <br>
            <br>
            <img src=<c:url value="../../../resources/img/smile1.png"/> width="25">
        </p>
    </main>

    <%@include file="../dynamic/footer.jsp" %>

</div>


</body>
</html>
