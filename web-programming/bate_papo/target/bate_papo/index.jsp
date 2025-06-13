<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>Bate Papo</title>
</head>
<body>
  <form name="chat-form" method="post" action="room.jsp">
    <center>
      <table width="60%" border="1">
        <tr>
          <td colspan="2" bgcolor="crimson" align="center">
            <font color="white" size="+3">Bate Papo</font>
          </td>  
        </tr>
        <tr>
          <td align="right">
            <b>Nickname:</b>
          </td>
          <td>
            <input type="text" name="nickname" required>
          </td>
        </tr>
        <tr>
          <td align="right">
            <b>Avatar:</b>
          </td>
          <td>
            <label>
              <input type="radio" name="avatar" value="images/avatar01.png" checked>
              <img src="images/avatar01.png" width="50" alt="avatar01"/>
            </label>
            
            <label>
              <input type="radio" name="avatar" value="images/avatar02.png">
              <img src="images/avatar02.png" width="50" alt="avatar02"/>    
            </label>
            
            <label>
              <input type="radio" name="avatar" value="images/avatar03.png">
              <img src="images/avatar03.png" width="50" alt="avatar03"/>    
            </label>
            
            <label>
              <input type="radio" name="avatar" value="images/avatar04.png">
              <img src="images/avatar04.png" width="50" alt="avatar04"/>    
            </label>
            
            <label>
              <input type="radio" name="avatar" value="images/avatar05.png">
              <img src="images/avatar05.png" width="50" alt="avatar05"/>     
            </label>
          </td>                 
        </tr>
        <tr>
          <td align="right">
            <b>Cor do Nome:</b> 
          </td>
          <td>
            <label>
              <input type="radio" name="color" value="blue" checked>
              <font color="blue">Azul</font>
              &nbsp;&nbsp;&nbsp;    
            </label>
            
            <label>
              <input type="radio" name="color" value="purple">
              <font color="purple">Roxo</font>
              &nbsp;&nbsp;&nbsp;    
            </label>
            
            <label>
              <input type="radio" name="color" value="green">
              <font color="green">Verde</font>
            </label>
            
            <label>
              <input type="radio" name="color" value="red">
              <font color="red">Vermelho</font>
            </label>
            
            <label>
              <input type="radio" name="color" value="orange">
              <font color="orange">Laranja</font>
            </label>
          </td>
        </tr>
        <tr>
          <td colspan="2" align="center">
            <button type="submit" name="chat-submit" value="Entrar" style="margin: 10px; padding: 10px 90px; background-color: crimson; color: white">Entrar</button>
            <button type="reset" name="chat-clear" value="Limpar" style="margin: 10px; padding: 10px 90px; background-color: white">Limpar</button>
          </td>
        </tr>
      </table>   
    </center>
  </form>
</body>
</html>