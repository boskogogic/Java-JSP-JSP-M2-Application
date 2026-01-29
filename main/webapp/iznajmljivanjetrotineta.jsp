<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <jsp:useBean id="appUserBean" class="net.etfbl.ip.beans.AppUserBean"
	scope="application"></jsp:useBean>
<jsp:useBean id="appUser"
	class="net.etfbl.ip.AppUser" scope="session"></jsp:useBean>
	<jsp:useBean id="messageBean" class="net.etfbl.ip.beans.MessageBean"
	scope="application"></jsp:useBean>
<jsp:useBean id="message"
	class="net.etfbl.ip.Message" scope="session"></jsp:useBean>
<jsp:setProperty property="username" name="appUser" param="username" />
<jsp:setProperty property="password" name="appUser" param="password" />
<%@ page import="net.etfbl.ip.AppUser" %>
<%@ page import="net.etfbl.ip.beans.AppUserBean" %>
<!DOCTYPE html>
<%
	if (request.getParameter("submit1") != null) {
		
	} else if (request.getParameter("submit2") != null ) {
			session.setAttribute("notification", "Unijeli ste neispravno korisnicko ime ili lozinku.");
			//u.setLoggedIn(false);
	} else if (request.getParameter("submit2") != null ) {
			session.setAttribute("notification", "Unijeli ste neispravno korisnicko ime ili lozinku.");
			//u.setLoggedIn(false);
	} else {
                
        }
	
%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="styles/style.css"></link>
</head>
<body>
<h1>ETFBL_IP User Support</h1>
<hr/>
	<h2>ETFBL IP App</h2>
	
		<input type="submit"
			 href="mainpage.jsp" value="Iznajmi trotinet" name="submit1" /><br />
                <input type="submit"
			value="Iznajmi bicikl" name="submit2" /><br />
                <input type="submit"
			value="Iznajmi automobil" name="submit3" /><br />
                <input type="submit"
			value="Prikazi profil" name="submit4" /><br />
		

</body>
</html>