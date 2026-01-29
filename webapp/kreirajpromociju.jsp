<%@page import="net.etfbl.ip.beans.PromocijaBean"%>
<%@page import="net.etfbl.ip.Promocija"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<jsp:useBean id="promocijaBean" class="net.etfbl.ip.beans.PromocijaBean"
	scope="application"></jsp:useBean>
<jsp:useBean id="promocija"
	class="net.etfbl.ip.Promocija" scope="session"></jsp:useBean>
<jsp:setProperty property="naslov" name="promocija" param="naslov" />
<jsp:setProperty property="opis" name="promocija" param="opis" />
<jsp:setProperty property="datumTrajanja" name="promocija" param="datumTrajanja" />
<%
	if (request.getParameter("submit") != null) {
		boolean isExist = PromocijaBean.dodajPromociju(promocija.getNaslov(), promocija.getOpis(), promocija.getDatumTrajanja());
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
	<h2>KREIRANJE PROMOCIJE</h2>
	<form method="POST" action="kreirajpromociju.jsp">
                <br /> Opis <br /> <input type="text" name="opis" id="opis" /><br />
                <br /> Naslov <br /> <input type="text" name="naslov" id="naslov" /><br />           
                <br /> Datum trajanja <br /> <input type="text" name="datumTrajanja" id="datumTrajanja" /><br />

                <br /><input type="submit" value="Kreiraj promociju" name="submit" /><br />
		<h3><%=session.getAttribute("notification").toString()%></h3>
	</form>

</body>
</html>