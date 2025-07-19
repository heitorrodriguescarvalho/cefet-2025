<%@page import="java.io.*" contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <meta http-equiv="refresh" content="5">
  <title>Bate Papo</title>
</head>
<body>
  <%
    String dbPath = "/home/heitor/Code/cefet-2025/web-programming/bate_papo/src/main/webapp/db/messages.txt";
    
    FileReader reader = new FileReader(dbPath);
    BufferedReader buffer = new BufferedReader(reader);

    String line;
    while ((line = buffer.readLine()) != null) {
      out.println(line);
    }
    
    buffer.close();
    reader.close();
  %>
</body>
</html