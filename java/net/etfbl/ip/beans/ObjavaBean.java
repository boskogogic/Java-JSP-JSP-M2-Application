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

public class ObjavaBean implements Serializable {
    
    private static final ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
    private static final String SQL_GET_PROMOCIJE = "SELECT * FROM app_objava";
    private static final String SQL_INSERT_INTO_PROMOCIJE = "INSERT INTO app_objava(naslov,sadrzaj) values (?,?)";
    
    public ObjavaBean() {
        
    }

    public static List<Objava> getAll() {
        List<Objava> objave = new ArrayList<>();
        Connection connection = null;
	ResultSet rs = null;
	Object values[] = { };
        
        try { 
            connection = connectionPool.checkOut();
            PreparedStatement pstmt = DAOUtil.prepareStatement(connection, SQL_GET_PROMOCIJE, false, values);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                String sadrzaj = rs.getString("sadrzaj");
                String naslov = rs.getString("naslov");
                System.out.println("sadrzaj  je " + sadrzaj);
                System.out.println("naslov  je " + naslov);
                objave.add(new Objava(naslov, sadrzaj));
            }
        } catch (SQLException exp) {
            exp.printStackTrace();
	} finally {
            connectionPool.checkIn(connection);
	}
        
        return objave;
    }
    
    public static boolean dodajObjavu(String naslov, String sadrzaj) {
        Connection connection = null;
	ResultSet rs = null;
	Object values[] = {naslov, sadrzaj  };
        
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
