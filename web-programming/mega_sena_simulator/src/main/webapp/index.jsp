<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Mega Sena</title
  </head>
  <body>
  <img src="https://sdmntpreastus.oaiusercontent.com/files/00000000-fe8c-61f9-bd1d-9a47b9c4dec7/raw?se=2025-05-23T13%3A42%3A46Z&sp=r&sv=2024-08-04&sr=b&scid=61c91026-8293-5b35-ba5a-2143763baff2&skoid=b0fd38cc-3d33-418f-920e-4798de4acdd1&sktid=a48cca56-e6da-484e-a814-9c849652bcb3&skt=2025-05-23T12%3A20%3A22Z&ske=2025-05-24T12%3A20%3A22Z&sks=b&skv=2024-08-04&sig=C4UtnLogcyncDDtFHvxj0ytwTzpLXLYfrABrsO%2BZyiI%3D" alt="Mega Sena" width="100" height="100">
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
