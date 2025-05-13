<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Calculadora</title
  </head>
  <body>
  <h2>Calculadora</h2>
  <form name="calc-form" method="get" action="result.jsp">
    <label for="num-1">Número 1</label>
    <input type="number" name="num-1" id="num-1" required>
    <br>
    <label for="num-2">Número 2</label>
    <input type="number" name="num-2" required>
    <br>
    <button type="submit" name="submit-button" value="+">+</button>
    <button type="submit" name="submit-button" value="-">-</button>
    <button type="submit" name="submit-button" value="*">*</button>
    <button type="submit" name="submit-button" value="/">/</button>
  </form>
  </body>
</html>
