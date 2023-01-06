package com.transactions.mailsender.Dao;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.transactions.mailsender.jdbc.GenericJdbc;
import oracle.jdbc.OracleTypes;

public class BaseDao extends GenericJdbc{
   
    
    public List<ModelDataBase> getSTS() throws SQLException{
        ResultSet rs = null;
        List<ModelDataBase> modelList = new ArrayList<>();
        try{
            this.openConection();
            CallableStatement cStmt = connection.prepareCall("{call SP_CN_STS_ENTREGADO(?,?)}");
            cStmt.setString(1, "3");
            cStmt.registerOutParameter(2, OracleTypes.CURSOR);
            cStmt.executeUpdate();
            rs = (ResultSet)cStmt.getObject(2); 
            while(rs.next()){
                ModelDataBase model = new ModelDataBase();
                model.setDoctoSalId(rs.getObject(1).toString());
                model.setReferenceNumber(rs.getObject(2).toString());
                model.setDoctoEstadoId(rs.getObject(3).toString());
                model.setCbDivId(rs.getObject(4).toString());
                model.setDoctoFecha((java.util.Date) rs.getObject(5));
                model.setCorreo(rs.getObject(7).toString());
                model.setNombreCliente(rs.getObject(6).toString());
                modelList.add(model);
            }
        }catch(SQLException ex){
            System.out.println("Errores");
            System.out.println(ex);
        }catch(Exception ex ){
            System.out.println("Exception");
            System.out.println(ex);
        }
        this.closeConnection();
        return modelList;
    }
    
    
    public List<ModelDataBase> getSTSEmbarcado() throws SQLException{
        ResultSet rs = null;
        List<ModelDataBase> modelList = new ArrayList<>();
        try{
            this.openConection();
            CallableStatement cStmt = connection.prepareCall("{call SP_CN_STS_EMBARCADO(?,?)}");
            cStmt.setString(1, "3");
            cStmt.registerOutParameter(2, OracleTypes.CURSOR);
            cStmt.executeUpdate();
            rs = (ResultSet)cStmt.getObject(2); 
            while(rs.next()){
                ModelDataBase model = new ModelDataBase();
                model.setDoctoSalId(rs.getObject(1).toString());
                model.setReferenceNumber(rs.getObject(2).toString());
                model.setDoctoEstadoId(rs.getObject(3).toString());
                model.setCbDivId(rs.getObject(4).toString());
                model.setDoctoFecha((java.util.Date) rs.getObject(5));
                model.setCorreo(rs.getObject(7).toString());
                model.setNombreCliente(rs.getObject(6).toString());
                modelList.add(model);
            }
        }catch(SQLException ex){
            System.out.println("Errores");
            System.out.println(ex);
        }catch(Exception ex ){
            System.out.println("Exception");
            System.out.println(ex);
        }
        this.closeConnection();
        return modelList;
    }
    
    public void InsertarAlerta(ModelActAlerta modelActAlerta){
        ResultSet rs = null;
        try {
            this.openConection();
            CallableStatement cStmt = connection.prepareCall("{call SP_REG_ALERTA_DOCUMENTO(?,?,?,?)}");
            cStmt.setString(1, modelActAlerta.getDOCTOSALID());
            cStmt.setInt(2, modelActAlerta.getDOCTOESTADOID());
            cStmt.setString(3, modelActAlerta.getFECHA());
            cStmt.registerOutParameter(4, OracleTypes.CURSOR);
            
            rs = (ResultSet)cStmt.executeQuery();
            
        } catch(SQLException ex){
            System.out.println("Error al insertar alerta");
            System.out.println(ex);
        }catch(Exception ex ){
            System.out.println("Exception");
            System.out.println(ex);
        }
        this.closeConnection();
        
    }
    
}