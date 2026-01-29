package net.etfbl.ip.beans;

import net.etfbl.ip.*;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import net.etfbl.ip.dao.ConnectionPool;
import net.etfbl.ip.dao.DAOUtil;

public class IznajmljivanjeBiciklaBean {
    
    private static final ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
    private static final String SQL_SELECT_BY_USERNAME = "SELECT * FROM Automobil WHERE objectId=?";

    public String iznajmi(String koordinata1, String koordinata2, String idAutomobila, String vozackaDozvola) {
        //provjeriti da li je automobil iznajmljen
        System.out.println("koordinata1 " + koordinata1);
        System.out.println("koordinata2 " + koordinata2);
        System.out.println("idAutomobila " + idAutomobila);
        System.out.println("vozackaDozvola " + vozackaDozvola);
        
        Connection connection = null;
	ResultSet rs = null;
	Object values[] = { idAutomobila };
        
        try { 
            connection = connectionPool.checkOut();
            PreparedStatement pstmt = DAOUtil.prepareStatement(connection, "SELECT * FROM Automobil WHERE objectId=?", false, values);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                Long id = rs.getLong("id");
                boolean iznajmljeno = rs.getBoolean("iznajmljeno");
	        boolean pokvaren = rs.getBoolean("pokvaren");
	        String model = rs.getString("model");
                
                System.out.println("Id  je " + id);
                System.out.println("iznajmljeno  je " + iznajmljeno);
                System.out.println("pokvaren  je " + pokvaren);
                System.out.println("model  je " + model);
            }
            pstmt.close();
	    rs.close();
	} catch (SQLException exp) {
            exp.printStackTrace();
	} finally {
            connectionPool.checkIn(connection);
	}

        return "Automobil iznajmljen!";
    }
    
}
