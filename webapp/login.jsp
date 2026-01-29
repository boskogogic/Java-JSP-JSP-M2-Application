<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <jsp:useBean id="appUserBean" class="net.etfbl.ip.beans.AppUserBean"
	scope="application"></jsp:useBean>
<jsp:useBean id="appUser"
	class="net.etfbl.ip.AppUser" scope="session"></jsp:useBean>
<jsp:setProperty property="username" name="appUser" param="username" />
<jsp:setProperty property="password" name="appUser" param="password" />
<%@ page import="net.etfbl.ip.AppUser" %>
<%@ page import="net.etfbl.ip.beans.AppUserBean" %>
<!DOCTYPE html>
<%
	if (request.getParameter("submit") != null) {
		boolean isExist = appUserBean.loginUserJSPM2(appUser.getUsername(), appUser.getPassword());
		if (isExist && appUser.getUsername().equals("menadzer")) {
                   System.out.println("User exist"); 
                   response.sendRedirect("mainpage.jsp");
		} else {
                    System.out.println("User DON'T exist");
			session.setAttribute("notification", "Unijeli ste neispravno korisnicko ime ili lozinku.");
			//u.setLoggedIn(false);
		}
	} else {
		session.setAttribute("notification", "");
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
	<h2>Prijava na sistem</h2>
	<form method="POST" action="login.jsp">
		Korisnicko ime<br /> <input type="text" name="username"
			id="username" /><br /><br /> Lozinka <br /> <input type="password"
			name="password" id="password" /><br /> <br /><input type="submit"
			value="Prijavi me" name="submit" /><br />
		<h3><%=session.getAttribute("notification").toString()%></h3>
                <a href="mainpage.jsp">Main page &gt;&gt;&gt;</a>
	</form>
</body>
</html>