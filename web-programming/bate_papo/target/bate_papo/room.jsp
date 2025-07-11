<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>Bate Papo</title>
</head>
<body>
  <%
    session.setAttribute("nick", request.getParameter("nickname"));
    session.setAttribute("cor", request.getParameter("color"));
    session.setAttribute("avatar", request.getParameter("avatar"));
  %>
  <iframe src="header.jsp" width="100%" height="120"></iframe>
  <iframe src="chat.jsp" width="100%" height="500"></iframe>
  <iframe src="messages.jsp" width="100%" height="160"></iframe>
</body>
</html>