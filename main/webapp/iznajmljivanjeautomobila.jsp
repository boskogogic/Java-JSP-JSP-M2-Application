<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <jsp:useBean id="iznajmljivanjeAutomobilaBean" class="net.etfbl.ip.beans.IznajmljivanjeAutomobilaBean"
	scope="application"></jsp:useBean>
    <jsp:useBean id="iznajmljivanjeAutomobila"
            class="net.etfbl.ip.IznajmljivanjeAutomobila" scope="session"></jsp:useBean>
    <jsp:setProperty property="koordinata1" name="iznajmljivanjeAutomobila" param="koordinata1" />
    <jsp:setProperty property="koordinata2" name="iznajmljivanjeAutomobila" param="koordinata2" />
    <jsp:setProperty property="idAutomobila" name="iznajmljivanjeAutomobila" param="idAutomobila" />
    <jsp:setProperty property="vozackaDozvola" name="iznajmljivanjeAutomobila" param="vozackaDozvola" />
<%@ page import="net.etfbl.ip.IznajmljivanjeAutomobila" %>
<%@ page import="net.etfbl.ip.beans.IznajmljivanjeAutomobilaBean" %>
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
	<h2>Iznajmljivanje automobila</h2>
	<form method="POST" action="iznajmljivanjeautomobila.jsp">
                <br /> Vrsta vozila : AUTOMOBIL <br /> 
		Koordinate <br /> 
                <input type="text" name="koordinata1" id="koordinata1" /><br />
                <input type="text" name="koordinata2" id="koordinata2" /><br />
                <br /> Unesti ID automobila <br /> <input type="text" name="idAutomobila" id="idAutomobila" /><br />
                <br /> Unesti vozacku dozvolu <br /> <input type="text" name="vozackaDozvola" id="vozackaDozvola" /><br />
                
                <br /> Unesti karticu <br /> 
                <br /> Broj kartice <input type="text" name="brojKartice" id="brojKartice" /><br />
                <br /> Istice  <input type="text" name="istice" id="istice" /><br />
                <br /> Tri broja  <input type="text" name="triBroja" id="triBroja" /><br />
                <br /><input type="submit" value="Iznajmi" name="submit" /><br />
		<h3><%=session.getAttribute("notification").toString()%></h3>
	</form>

</body>
</html>