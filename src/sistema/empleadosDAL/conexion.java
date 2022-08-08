/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistema.empleadosDAL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import javax.naming.spi.DirStateFactory;
import java.sql.ResultSet;
/**
 *
 * @author OSKAR
 */
//paquete para la interaccion con la base de datos (comunicarnos con la base de datos)
public class conexion {
    
    //string de conexion
    String strConexionDB="jdbc:sqlite:C:/Users/OSKAR/Documents/db/sistema.s3db";
    //clase connection
    Connection conn=null;
    
    //constructor (metodo con el mismo nombre de la clase)
    public conexion (){
        
        try {
            //concectar y controlar la información
            Class.forName("org.sqlite.JDBC");
            //crear conexion entre sql lite y aplicación
            conn=DriverManager.getConnection(strConexionDB);
            
            System.out.println("conexion establecida");
        } catch (Exception e) {
            
            System.out.println("Error de conexion"+e );
        }
        
    }
    
    //metodo que nos permitirá insertar
    //cuando se presione el boton se va a ejecutar una inserccion lo cual mpodificará la basa de datos
    //metodo para ejecutar cualquier instruccion que no requiera retorno de datos
    public int ejecutarSentenciaSQL(String strSentenciaSQL){
        
        //try para atrapar lo que el error, si lo hubiera
        try {
            PreparedStatement pstm= conn.prepareStatement(strSentenciaSQL);
            pstm.execute();
            return 1;
            
        } catch (SQLException e) {
            System.out.println(e);
            return 0;
        }
        
        
    }
    //metodo para devolver informacion y para saber si tal informacion existe o no y de lo contrario enviar algo nulo  
    public ResultSet consultarRegistros (String strSentenciaSQL){
        
        try {
            
            PreparedStatement pstm= conn.prepareStatement(strSentenciaSQL);
            ResultSet respuesta= pstm.executeQuery();
            return respuesta;
        } catch (Exception e) {
            System.err.println(e);
            return null;
        }
        
    }
    
}
