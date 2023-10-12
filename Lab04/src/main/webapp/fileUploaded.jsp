<%@ page import="java.io.File" %><%--
  Created by IntelliJ IDEA.
  User: kienl
  Date: 10/11/2023
  Time: 9:22 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>File Table</title>
    <style>
        /* Style to remove borders from the table */
        table {
            border-collapse: collapse;
        }

        /* Style to add some padding to the table cells */
        td {
            padding: 8px;

        }
        a{
            text-decoration: none;
        }
    </style>
</head>
<body>

<h1>File uploaded</h1>

<table>
    <thead>
    <tr>
        <th>File Name</th>
        <th>Download Link</th>
    </tr>
    </thead>
    <tbody>
    <%
        File[] files = (File[]) request.getAttribute("files");
        if (files != null) {
            for (File file : files) {
    %>
    <tr>
        <td><%= file.getName() %></td>
        <td>Click <a href="/download?file=<%= file.getName() %>">here</a> to download</td>
    </tr>
    <%
        }
    } %>
    </tbody>
</table>

</body>
</html>

