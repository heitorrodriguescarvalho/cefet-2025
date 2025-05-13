<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
      <title>Resultado</title>
  </head>
  <body>
    <h1>O resultado é: </h1>
    <%
      int n1 = Integer.parseInt(request.getParameter("num-1"));
      int n2 = Integer.parseInt(request.getParameter("num-2"));
      String operation = request.getParameter("submit-button");

      switch (operation) {
        case "+":
          out.print(n1 + n2);
          break;
        case "-":
          out.print(n1 - n2);
          break;
        case "*":
          out.print(n1 * n2);
          break;
        case "/":
          out.print(n1 / n2);
          break;
        default:
          out.print("Operação inválida");
          break;
      }
    %>
  </body>
</html>