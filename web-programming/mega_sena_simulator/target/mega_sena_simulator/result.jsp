<%@ page contentType="text/html; charset=UTF-8" import="java.util.*" pageEncoding="UTF-8" %>
<%
  Random random = new Random();

  String[] photos = {
    "./images/perdeu.png",
    "./images/terno.png",
    "./images/quadra.png",
    "./images/quina.png",
    "./images/sena.png"
  };

  boolean error = false;

  int[] sorteados = new int[6];
  for (int i = 0; i < sorteados.length; i++) {
      sorteados[i] = random.nextInt(61);
  }

  int[] selecionados = new int[6];
  for (int i = 1; i <= selecionados.length; i++) {
      selecionados[i - 1] = Integer.parseInt(request.getParameter("input-num-" + i));

      for (int j = 0; j < i - 1; j++) {
        if (selecionados[j] == selecionados[i - 1]) {
          error = true;
          break;
        }
      }

      if (selecionados[i - 1] > 60 || selecionados[i - 1] < 0) {
        error = true;
      }
  }

  int acertos = 0;
  for (int selecionado : selecionados) {
      for (int sorteado : sorteados) {
          if (selecionado == sorteado) {
              acertos++;
          }
      }
  }

  String photo = "";

  if (acertos < 3) {
      photo = photos[0];
  } else if (acertos == 3) {
      photo = photos[1];
  } else if (acertos == 4) {
      photo = photos[2];
  } else if (acertos == 5) {
      photo = photos[3];
  } else if (acertos == 6) {
      photo = photos[4];
  }
%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
      <title>Resultado</title>
  </head>
  <body>
    <img src="./images/home.png" alt="Mega Sena" width="100" height="100">
    <p>Você escolheu os números:</p>
    <p>
      <%
        for (int i = 0; i < selecionados.length; i++) {
            out.print(selecionados[i]);
            if (i < selecionados.length - 1) {
                out.print(" - ");
            }
        }
      %>
    </p>

    <% if (error) { %>
      <p>Você digitou números inválidos!</p>
    <% } else { %>
    <p>Foram sorteados os números:</p>
    <p>
      <%
        for (int i = 0; i < sorteados.length; i++) {
            out.print(sorteados[i]);
            if (i < selecionados.length - 1) {
                out.print(" - ");
            }
        }
      %>
    </p>

    <p>Você acertou <%= acertos %> número(s)!</p>

    <img src="<%= photo%>" alt="Resultado" width="200" height="200">
    <% } %>
  </body>
</html>