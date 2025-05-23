<%@ page contentType="text/html; charset=UTF-8" import="java.util.*" pageEncoding="UTF-8" %>
<%
  Random random = new Random();

  String[] photos = {
    "https://sdmntprcentralus.oaiusercontent.com/files/00000000-8c40-61f5-a551-bb16e8744bcc/raw?se=2025-05-23T14%3A14%3A53Z&sp=r&sv=2024-08-04&sr=b&scid=46687e0a-c1d3-5204-b936-70d35a0d2f79&skoid=b0fd38cc-3d33-418f-920e-4798de4acdd1&sktid=a48cca56-e6da-484e-a814-9c849652bcb3&skt=2025-05-23T11%3A17%3A15Z&ske=2025-05-24T11%3A17%3A15Z&sks=b&skv=2024-08-04&sig=uzQFw2WX7VaNCv3RpHDAxD3Aj7iiF/fNYrILx38WsZw%3D",
    "https://sdmntprcentralus.oaiusercontent.com/files/00000000-d7d8-61f5-8282-9a0b56d4a2e6/raw?se=2025-05-23T14%3A09%3A45Z&sp=r&sv=2024-08-04&sr=b&scid=47139959-36de-5185-a4cb-9fcd3c018092&skoid=b0fd38cc-3d33-418f-920e-4798de4acdd1&sktid=a48cca56-e6da-484e-a814-9c849652bcb3&skt=2025-05-23T10%3A46%3A12Z&ske=2025-05-24T10%3A46%3A12Z&sks=b&skv=2024-08-04&sig=drlwjC4u5BFJtrSNtAz5g1HQDCdZLF2AktBebepRpPA%3D",
    "https://sdmntpreastus.oaiusercontent.com/files/00000000-c3a4-61f9-a1bd-638b362809f4/raw?se=2025-05-23T14%3A06%3A17Z&sp=r&sv=2024-08-04&sr=b&scid=3263ca69-333a-5921-b59f-465647f650dc&skoid=b0fd38cc-3d33-418f-920e-4798de4acdd1&sktid=a48cca56-e6da-484e-a814-9c849652bcb3&skt=2025-05-22T19%3A32%3A37Z&ske=2025-05-23T19%3A32%3A37Z&sks=b&skv=2024-08-04&sig=bmrJrxqyAHagH2q%2BSqwVPAwvNm8vkoAU/tuXa75foVU%3D",
    "https://sdmntpreastus.oaiusercontent.com/files/00000000-8538-61f9-b8aa-15d3a6081719/raw?se=2025-05-23T13%3A52%3A20Z&sp=r&sv=2024-08-04&sr=b&scid=92600c87-bb66-594c-ae71-534f2d7d1bdb&skoid=b0fd38cc-3d33-418f-920e-4798de4acdd1&sktid=a48cca56-e6da-484e-a814-9c849652bcb3&skt=2025-05-23T10%3A38%3A02Z&ske=2025-05-24T10%3A38%3A02Z&sks=b&skv=2024-08-04&sig=%2BrlQ53Oho50rU1Kfj4L86XzJFu5ykOQ/wg28Nl3LgoA%3D",
    "https://sdmntpreastus.oaiusercontent.com/files/00000000-8904-61f9-a45c-ba252e953ee5/raw?se=2025-05-23T13%3A50%3A22Z&sp=r&sv=2024-08-04&sr=b&scid=78a55fcd-5466-5f76-a18b-fdb67fa24378&skoid=b0fd38cc-3d33-418f-920e-4798de4acdd1&sktid=a48cca56-e6da-484e-a814-9c849652bcb3&skt=2025-05-23T10%3A48%3A07Z&ske=2025-05-24T10%3A48%3A07Z&sks=b&skv=2024-08-04&sig=TCnI2N%2BeLo8R8WO0lvoNt/RjXbg0UvOU%2B2maXhc9QfU%3D"
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
    <img src="https://sdmntpreastus.oaiusercontent.com/files/00000000-fe8c-61f9-bd1d-9a47b9c4dec7/raw?se=2025-05-23T13%3A42%3A46Z&sp=r&sv=2024-08-04&sr=b&scid=61c91026-8293-5b35-ba5a-2143763baff2&skoid=b0fd38cc-3d33-418f-920e-4798de4acdd1&sktid=a48cca56-e6da-484e-a814-9c849652bcb3&skt=2025-05-23T12%3A20%3A22Z&ske=2025-05-24T12%3A20%3A22Z&sks=b&skv=2024-08-04&sig=C4UtnLogcyncDDtFHvxj0ytwTzpLXLYfrABrsO%2BZyiI%3D" alt="Mega Sena" width="100" height="100">
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