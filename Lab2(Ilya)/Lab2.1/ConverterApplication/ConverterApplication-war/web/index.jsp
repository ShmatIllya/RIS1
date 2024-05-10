<%@page import="web.NewServlet"%>
<%@page import="java.util.ArrayList"%>
<%@ page import="java.io.*" language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!--
Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Html.html to edit this template
-->

<html>
    <head>
        <title>TODO supply a title</title>
        <meta charset="windows-1251">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>


    <body>

        <form action="Form2Servlet" method ="post" name="form2">
            <%
                String arg[] = {"Yunost", "12", "120"};

                if (NewServlet.values2.size() == 0) {
                    NewServlet.values2.add(arg);
                    arg = new String[]{"3DMax", "15", "110"};
                    NewServlet.values2.add(arg);
                    arg = new String[]{"Chto-to tam", "10", "80"};
                    NewServlet.values2.add(arg);
                }
            %>
            
            <input name="But1" type="button" value="Add new" ONCLICK="button2()"/><br>
            <input name="go2" type="submit" value="Add" disabled = 'disabled'><br>
           Name: <input name="name" type="text" disabled = 'disabled'><br>
           Halls: <input name="halls" type="text" disabled = 'disabled'><br>
           Sits: <input name="sits" type="text" disabled = 'disabled'><br>


        </form>

        <form action="Form3Servlet" method="post" name="form3"/>
        <br><input name="But2" type="button" value="Change new" ONCLICK="button1()"/><br>
        <input name="go3" type="submit" value="Change" disabled = 'disabled'><br>
        Old name: <input name="old_name" type="text" disabled = 'disabled'><br>
        New name: <input name="name2" type="text" disabled = 'disabled'><br>
        New halls: <input name="halls2" type="text" disabled = 'disabled'><br>
        New sits: <input name="sits2" type="text" disabled = 'disabled'><br>
    </form>
    <%
        // check = (String) request.getParameter("check");
        if (NewServlet.add_check == true) {

            arg = new String[]{NewServlet.name, NewServlet.halls, NewServlet.sits};
            NewServlet.values2.add(arg);
            NewServlet.add_check = false;
    %>
    <% }
    %>   
    <form action=" NewServlet " method="get" name = "form1"> 
        <% for (int i = 0; i < NewServlet.values2.size(); i++) {%>
        <p> Number <%= i%>; 
            Name = <%= NewServlet.values2.get(i)[0]%>;
            Number of halls = <%= NewServlet.values2.get(i)[1]%>;
            Number of seats = <%= NewServlet.values2.get(i)[2]%>;
        </p>
        <%}%>
        <%request.setAttribute("array", NewServlet.values2);%>
        <input type="hidden" name="buttonName" value="no"/>
        Action:  <input name="amount" type="text">
        <input name="go" type="submit" value="Count">
    </form>
    <script language="JavaScript">

        function button1()
        {
            document.form3.old_name.disabled = false;
            document.form3.go3.disabled = false;
            document.form3.name2.disabled = false;
            document.form3.halls2.disabled = false;
            document.form3.sits2.disabled = false;

        }

        function button2()
        {

            document.form2.go2.disabled = false;
            document.form2.name.disabled = false;
            document.form2.halls.disabled = false;
            document.form2.sits.disabled = false;
        }
    </script>
</body>
</html>
