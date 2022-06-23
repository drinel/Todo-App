
package util;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class ConnectionFactory {
    
    public static final String DRIVER = "com.mysql.jdbc.Driver";
    public static final String URL = "jdbc:mysql://localhost:3306/todoApp";
    public static final String USER = "root";
    public static final String PASS = "";
    
    
    public static Connection getConnection(){
        try{
            Class.forName(DRIVER);
            return DriverManager.getConnection(URL, USER, PASS);
        }catch(Exception e){
            throw new RuntimeException("Erro na conexão com banco de dados", e);
        }
    }
    
    
    public static void closeConnection(Connection conn){
        try{
            if(conn != null){
                conn.close();
            }
        } catch(Exception e){
            throw new RuntimeException("Erro ao fechar conexão com banco de dados", e);
        }
    }
    
    
     public static void closeConnection(Connection conn, PreparedStatement stm){
        try{
            if(conn != null){
                conn.close();
            }
            
            if(stm != null){
                stm.close();
            }
        } catch(Exception e){
            throw new RuntimeException("Erro ao fechar conexão com banco de dados", e);
        }
    }
     
     
       public static void closeConnection(Connection conn, PreparedStatement stm, ResultSet rs){
        try{
            if(conn != null){
                conn.close();
            }
            
            if(stm != null){
                stm.close();
            }
            
            if(rs != null){
                rs.close();
            }
        } catch(Exception e){
            throw new RuntimeException("Erro ao fechar conexão com banco de dados", e);
        }
    }
}
