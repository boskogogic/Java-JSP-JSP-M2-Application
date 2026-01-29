package net.etfbl.ip.beans;

import net.etfbl.ip.*;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import net.etfbl.ip.dao.ConnectionPool;
import net.etfbl.ip.dao.DAOUtil;

public class PromocijaBean implements Serializable {
    
    private static final ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
    private static final String SQL_GET_PROMOCIJE = "SELECT * FROM app_promocija";
    private static final String SQL_INSERT_INTO_PROMOCIJE = "INSERT INTO app_promocija(naslov,opis,datumTrajanja) values (?,?,?)";
    
    public PromocijaBean() {
        
    }

    public static List<Promocija> getAll() {
        List<Promocija> promocije = new ArrayList<>();
        Connection connection = null;
	ResultSet rs = null;
	Object values[] = { };
        
        try { 
            connection = connectionPool.checkOut();
            PreparedStatement pstmt = DAOUtil.prepareStatement(connection, SQL_GET_PROMOCIJE, false, values);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                String opis = rs.getString("opis");
                String naslov = rs.getString("naslov");
                String datumTrajanja = rs.getString("datumTrajanja");
                System.out.println("opis  je " + opis);
                System.out.println("naslov  je " + naslov);
                System.out.println("datumTrajanja  je " + datumTrajanja);
                promocije.add(new Promocija(opis,naslov,datumTrajanja));
            }
        } catch (SQLException exp) {
            exp.printStackTrace();
	} finally {
            connectionPool.checkIn(connection);
	}
        
        return promocije;
    }
    
    public static boolean dodajPromociju(String naslov, String opis, String datumTrajanja) {
         Connection connection = null;
	ResultSet rs = null;
	Object values[] = {naslov, opis, datumTrajanja  };
        
        try { 
            connection = connectionPool.checkOut();
            PreparedStatement pstmt = DAOUtil.prepareStatement(connection, SQL_INSERT_INTO_PROMOCIJE, true, values);
            pstmt.executeUpdate();
        } catch (SQLException exp) {
            exp.printStackTrace();
            return false;
	} finally {
            connectionPool.checkIn(connection);
                
	}
        return true;
    }
}
