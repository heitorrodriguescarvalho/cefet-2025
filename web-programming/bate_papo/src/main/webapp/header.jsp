<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <table>
            <td>
                <img src="<%= session.getAttribute("avatar")%>" alt="avatar" width="75"/>    
            </td>
            
            <td>
                    &nbsp;
                   <font color="<%= session.getAttribute("cor")%>" size="12">
                    <%= session.getAttribute("nick")%>
                </font>
            </td>
        </table>
    </body>
</html>