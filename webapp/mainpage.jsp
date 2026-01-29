<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ page import="net.etfbl.ip.Promocija" %>
<%@ page import="net.etfbl.ip.beans.PromocijaBean" %>
<%@ page import="net.etfbl.ip.Objava" %>
<%@ page import="net.etfbl.ip.beans.ObjavaBean" %>

<jsp:useBean id="promocijaBean" class="net.etfbl.ip.beans.PromocijaBean"
	scope="application"></jsp:useBean>
<jsp:useBean id="promocija"
	class="net.etfbl.ip.Promocija" scope="session"></jsp:useBean>

<jsp:useBean id="objavaBean" class="net.etfbl.ip.beans.ObjavaBean"
	scope="application"></jsp:useBean>
<jsp:useBean id="objava"
	class="net.etfbl.ip.Objava" scope="session"></jsp:useBean>

<jsp:setProperty property="username" name="appUser" param="username" />
<jsp:setProperty property="password" name="appUser" param="password" />


<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}\styles\style.css"></link>
</head>
<body>
<h1>ETFBL_IP PROJEKTNI ZADATAK JSP</h1>
<hr/>
	<h2>ETFBL IP App</h2>
	
            <a href="kreirajpromociju.jsp">Kreiraj promociju &gt;&gt;&gt; </a><br><br>
            <a href="kreirajobjavu.jsp">Kreiraj objavu  &gt;&gt;&gt;</a><br><br>
            
            <!DOCTYPE html>
<table border="1">
  <tr>
    <th>Naslov</th>
    <th>Opis</th>
    <th>Datum trajanja</th>
  </tr>
    <%
        List<Promocija> promocije = PromocijaBean.getAll();
        if (promocije != null) {
           for (int i = 0; i < promocije.size(); i++) {;
    %>
    <tr>
      <td> <%= promocije.get(i).getNaslov() %> </td>
      <td><%= promocije.get(i).getOpis() %></td>
      <td><%= promocije.get(i).getDatumTrajanja() %></td>
    </tr>
    <%   
        }
      }
    %>
</table><br><br>

<table border="1">
  <tr>
    <th>Objava naslov</th>
    <th>Objava sadrzaj</th>
  </tr>
    <%
        List<Objava> objave = ObjavaBean.getAll();
        if (objave != null) {
           for (int i = 0; i < objave.size(); i++) {;
    %>
    <tr>
      <td> <%= objave.get(i).getNaslov() %> </td>
      <td><%= objave.get(i).getSadrzaj() %></td>
    </tr>
    <%   
        }
      }
    %>
</table>

</body>
</html>