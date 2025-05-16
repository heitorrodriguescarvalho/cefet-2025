<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
      <title>Resultado</title>
  </head>
  <body>
    <h1>Impostos</h1>
    <%
      String name = request.getParameter("name");
      int dependecies = Integer.parseInt(request.getParameter("dependencies"));
      float health = Float.parseFloat(request.getParameter("health"));
      float salary = Float.parseFloat(request.getParameter("salary"));

      float total_tax;

      if (salary <= 5000) total_tax = 0.00f;
      else if (salary <= 8000) total_tax = salary * 0.05f;
      else if (salary <= 12000) total_tax = salary * 0.08f;
      else total_tax = salary * 0.1f;

      float depencies_discount = dependecies * 100f;
      float health_discount = health * 0.1f;

      float final_tax = total_tax - depencies_discount - health_discount;

      out.println("<p>Nome: " + name + "</p>");
      out.println("<p>Imposto Total: R$" + total_tax + "</p>");
      out.println("<p>Desconto por dependentes: R$" + depencies_discount + "</p>");
      out.println("<p>Desconto por saúde: R$" + health_discount + "</p>");
      out.println("<p>Imposto Final: R$" + final_tax + "</p>");
    %>
  </body>
</html>