<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form:form method="POST"
           action="/" modelAttribute="pais">
    <table>
        <tr>
            <td><form:label path="id">Id</form:label></td>
            <td><form:input path="id"/></td>
        </tr>
        <tr>
            <td><form:label path="nombre">nombre</form:label></td>
            <td><form:input path="nombre"/></td>
        </tr>
        <tr>
            <td><form:label path="habitantes">habitantes</form:label></td>
            <td><form:input path="habitantes"/></td>
        </tr>
        <tr>
            <td><form:label path="idioma">idioma</form:label></td>
            <td><form:input path="idioma"/></td>
        </tr>
        <tr>
            <td><form:label path="capital">capital</form:label></td>
            <td><form:input path="capital"/></td>
        </tr>
        <tr>
            <td><form:label path="continente">continente</form:label></td>
            <td><form:input path="continente"/></td>
        </tr>
        <tr>
            <td><input type="submit" value="Submit"/></td>
        </tr>
    </table>
</form:form>
</body>
</html>