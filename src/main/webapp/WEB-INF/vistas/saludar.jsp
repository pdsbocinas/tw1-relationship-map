<%--
  Created by IntelliJ IDEA.
  User: pds.gomez
  Date: 2019-05-09
  Time: 20:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <p>Hola!!</p>
    <c:forEach items="${lista}" var="s">
        <tr>
            <td>${s}</td>
        </tr>
    </c:forEach>
</body>
</html>
