<%@page import="net.etfbl.ip.beans.ObjavaBean"%>
<%@page import="net.etfbl.ip.Objava"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<jsp:useBean id="objavaBean" class="net.etfbl.ip.beans.ObjavaBean"
	scope="application"></jsp:useBean>
<jsp:useBean id="objava"
	class="net.etfbl.ip.Objava" scope="session"></jsp:useBean>
<jsp:setProperty property="naslov" name="objava" param="naslov" />
<jsp:setProperty property="sadrzaj" name="objava" param="sadrzaj" />
<%
	if (request.getParameter("submit") != null) {
		boolean isExist = ObjavaBean.dodajObjavu(objava.getNaslov(), objava.getSadrzaj());
		if (isExist) {
                    response.sendRedirect("mainpage.jsp");
		} else {
			session.setAttribute("notification", "Unijeli ste neispravno korisnicko ime ili lozinku.");
			//u.setLoggedIn(false);
		}
	} else {
		session.setAttribute("notification", "");
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="styles/style.css"></link>
</head>
<body>
<h1>ETFBL_IP PROJEKTNI ZADATAK</h1>
<hr/>
	<h2>KREIRANJE OBJAVE</h2>
	<form method="POST" action="kreirajobjavu.jsp">
                <br /> Sadrzaj <br /> <input type="text" name="sadrzaj" id="sadrzaj" /><br />
                <br /> Naslov <br /> <input type="text" name="naslov" id="naslov" /><br />           
              
                <br /><input type="submit" value="Kreiraj objavu" name="submit" /><br />
		<h3><%=session.getAttribute("notification").toString()%></h3>
	</form>

</body>
</html>