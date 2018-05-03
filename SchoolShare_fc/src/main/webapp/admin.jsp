<%--
  Created by IntelliJ IDEA.
  User: 胡家明
  Date: 2018/5/4
  Time: 1:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>admin</title>
</head>
<body>
    <form method="post" action="/login" id="form">
        <input name="name">
        <input name="password">
    </form>
    <button id="btn">submit</button>
</body>
<script type="text/javascript">
    document.getElementById("btn").onclick = function () {
        document.getElementById("form").submit();
    }
</script>
</html>
