<%@page import="java.io.*" contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>Bate Papo</title>
</head>
<body>
  <h1>Messages</h1>

  <form name="message-form" method="post">
    <label id="message-label"><b>Mensagem</b></label>
    <input type="text" name="message-text" placeholder="Digite sua mensagem..." size="150" maxlength="140" autofocus required>

    <label id="emoji-label"><b>Emoji</b></label>
    <select name="emoji">
      <option value="">Selecione</option>
      <option value="images/emoji01.png">Feliz</option>
      <option value="images/emoji02.png">Rindo</option>
      <option value="images/emoji03.png">Confuso</option>
      <option value="images/emoji04.png">Bobinho</option>
      <option value="images/emoji05.png">Rico</option>
    </select>

    <button type="submit">Enviar</button>
  </form>
  <%
    if (request.getParameter("message-text") != null) {
      String messageText = request.getParameter("message-text");
      String emoji = request.getParameter("emoji");

      String finalMessage = "<img src='" + session.getAttribute("avatar") + "' alt='avatar' width='50' height='50'/> "
        + "<font color='" + session.getAttribute("color") + "'>"
        + session.getAttribute("nick") + ": " + messageText + "</font>";

      if (!emoji.toString().equals("")) {
        finalMessage += " <img src='" + emoji + "' alt='emoji' width='30' height='30'/>";
      }
      finalMessage += "<br/>";

      String dbPath = "/home/heitor/Code/cefet-2025/web-programming/bate_papo/src/main/webapp/db/messages.txt";

      FileWriter writer = new FileWriter(dbPath, true);

      writer.write(finalMessage);

      writer.close();
    }
  %>
</body>
</html>