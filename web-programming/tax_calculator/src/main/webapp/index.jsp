<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Calculadora</title
  </head>
  <body>
  <h1>Impostos</h1>
  <form name="calc-form" method="get" action="result.jsp">
    <label for="name">Nome:</label>
    <input type="text" name="name" id="name" required>
    <br>
    <label for="dependencies">Quantidade de dependentes:</label>
    <input type="number" name="dependencies" id="dependencies" required>
    <br>
    <label for="health">Gastos com saúde:</label>
    <input type="number" name="health" id="health" required>
    <br>
    <label for="salary">Salário:</label>
    <input type="number" name="salary" id="salary" required>
    <br>
    <button type="submit" name="submit-button">Calculate</button>
  </form>
  </body>
</html>
