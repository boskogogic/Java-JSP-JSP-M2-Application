<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <jsp:useBean id="voznjaAutomobilaBean" class="net.etfbl.ip.beans.VoznjaAutomobilaBean"
	scope="application"></jsp:useBean>
    <jsp:useBean id="voznjaAutomobila"
            class="net.etfbl.ip.VoznjaAutomobila" scope="session"></jsp:useBean>
    <jsp:setProperty property="polaznoVrijeme" name="voznjaAutomobila" param="polaznoVrijeme" />
    <jsp:setProperty property="zavrsnoVrijeme" name="voznjaAutomobila" param="zavrsnoVrijeme" />
    <jsp:setProperty property="idAutomobila" name="voznjaAutomobila" param="idAutomobila" />
<%@ page import="net.etfbl.ip.IznajmljivanjeAutomobila" %>
<%@ page import="net.etfbl.ip.beans.IznajmljivanjeAutomobilaBean" %>
<!DOCTYPE html>
<%
	if (request.getParameter("submit") != null) {
               voznjaAutomobilaBean.generisiIzvjestaj(voznjaAutomobila.getPolaznoVrijeme(), voznjaAutomobila.getZavrsnoVrijeme(), session.getAttribute("IdAutomobila").toString());
        } else {
                
        }
	
%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="styles/style.css"></link>
</head>
<body onload="startTime();whenYouStart()">
    <h1>ETFBL_IP PROJEKTNI ZADATAK</h1>
    <hr/>
            <h2>Voznja automobila</h2>
            <form method="POST" action="voznjaautomobila.jsp">
                <h3><%=session.getAttribute("IdAutomobila").toString()%></h3>
                Polazno vrijeme <input type="text" id="polaznoVrijeme" name="polaznoVrijeme" />
                Trenutno vrijeme <input type="text" id="zavrsnoVrijeme" name="zavrsnoVrijeme" />
                <input type="submit"
			value="Zavrsi voznju" name="submit" /><br />
            </form>
            

     <script>
        function startTime() {
          const today = new Date();
          let h = today.getHours();
          let m = today.getMinutes();
          let s = today.getSeconds();
          m = checkTime(m);
          s = checkTime(s);
          document.getElementById('zavrsnoVrijeme').innerHTML =  h + ":" + m + ":" + s;
          document.getElementById('zavrsnoVrijeme').value =  h + ":" + m + ":" + s;
          setTimeout(startTime, 1000);
        }
        
        function whenYouStart() {
          const today = new Date();
          let hours  = today.getHours();
          let minutes = today.getMinutes();
          let seconds = today.getSeconds();
          minutes = checkTime(minutes);
          seconds = checkTime(seconds);
          document.getElementById('polaznoVrijeme').innerHTML =  hours + ":" + minutes + ":" + seconds;
          document.getElementById('polaznoVrijeme').value =  hours + ":" + minutes + ":" + seconds;
        }

        function checkTime(i) {
          if (i < 10) {i = "0" + i};  // add zero in front of numbers < 10
          return i;
        }
    </script>
</body>
</html>