<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Mega Sena</title
  </head>
  <body>
  <img src="./images/home.png" alt="Mega Sena" width="100" height="100">
  <form name="form" method="get" action="result.jsp">
    <p>Selecione 6 números entre 0 e 60:</p>
    <label for="label_nums">Selecionados:</label>
    <% for (int i = 1; i <= 6; i++) { %>
      <input type="number" id="input-num-<%= i %>" name="input-num-<%= i %>" min="0" max="60" required>
    <% } %>
    <br>
    <button type="submit">Sortear</button>
  </form>
  </body>
</html>
