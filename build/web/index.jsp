<%-- 
    Document   : index
    Created on : Oct 4, 2020, 11:05:07 PM
    Author     : グエン　トゥ
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <style>
            .input-group input {
                padding: 5px 10px;
                margin: 0;
            }

            .input-group button {
                padding: 7px 10px;
                background: #fff;
                border: 1px solid #d3d3d3;
                margin: 0;
            }

            .input-group * {
                display: inline-block;
                font-size: 14px;
            }

            .container {
                text-align: center;
                display: inline-block;
                border: 1px dashed;
                padding: 20px 40px;
            }

            body {
                text-align: center;
            }

            .input-group button:hover {
                background: aliceblue;
                cursor: pointer;
            }
        </style>
    </head>
    <body>  
        <div class="container">
            <div class="input-group">

            </h1>時間の範囲確認</h1><br/>
                <form action="ProcessServlet" method="POST">

                    確認したい時刻 <input type="number" name="txtSpecificTimeHour" value="" min="0" max="23"/>:
                    <input type="number" name="txtSpecificTimeMin" value="" min="0" max="59"/><br>
                    <% if (request.getAttribute("ERRORSPECTIME") != null) {
                    %>
                    <font color="red"><%= request.getAttribute("ERRORSPECTIME")%></font><br/>
                    <%
                        }
                    %> 

                    開始時刻 <input type="number" name="txtHourFrom" value="" min="0" max="23"/>:
                    <input type="number" name="txtMinFrom" value="" min="0" max="59"/><br>
                    <%
                        if (request.getAttribute("ERRORHOURFROM") != null) {
                    %>
                    <font color="red"><%= request.getAttribute("ERRORHOURFROM")%></font><br/>
                    <%
                        }
                    %> 

                    終了時刻 <input type="number" name="txtHourTo" value="" min="0" max="23"/>:
                    <input type="number" name="txtMinTo" value="" min="0" max="59"/>
                    当日<input type="radio" name="rdbnDay" value="today" required/>
                    翌日<input type="radio" name="rdbnDay" value="tomorrow" /><br>
                    <%
                        if (request.getAttribute("ERRORHOURTO") != null) {
                    %>
                    <font color="red"><%= request.getAttribute("ERRORHOURTO")%></font><br/>
                    <%
                        }
                    %> 

                    <input type="submit" value="OK" name="btAction" /><br>

                    <%
                        if (request.getAttribute("RESULTERROR") != null) {
                    %>
                    <font color="red"><%= request.getAttribute("RESULTERROR")%></font><br/>
                    <%
                        }
                    %> 

                    <%
                        if (request.getAttribute("RESULT") != null) {
                    %>
                    <font color="blue"><%= request.getAttribute("RESULT")%></font><br/>
                    <%
                        }
                    %> 
                </form>
            </div>
        </div>
    </body>
</html>
