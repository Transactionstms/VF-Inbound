<%-- 
    Document   : SubirExcelSP
    Created on : 28-abr-2023, 13:40:49
    Author     : grecendiz
--%>
<%@page import="com.tacts.ExcelToOracle"%>
<%@page import="com.tacts.ExcelToOracleSP"%>
<%
    
            response.setHeader("Access-Control-Allow-Origin", "*"); 
               String insertSql22=request.getParameter("ins");
                String ruta2=request.getParameter("rut");
                String folio=request.getParameter("folio");
                String insertSql2 = "";
                
                 String ruta   = "D:\\Servicios\\wspod\\INBOUND\\"+ruta2; 
                String mensaje =  "";
           
                if(insertSql22.equals("21")){
                    
                insertSql2 =""
                + " INSERT INTO TRA_INB_DNS(\n"
                + " BRAND,\n"
                + "STYLE_COLOR,\n"
                + "MATERIAL_REF,\n"
                + "SIZE_1,\n"
                + "UPC,\n"
                + "SOLID_PPK,\n"
                + "SBU_NAME,\n"
                + "SUB_SBU_NAME,\n"
                + "STYLE_COLOR_DESCR,\n"
                + "COLOR_DESCR,\n"
                + "INVENTORY_CLASS,\n"
                + "CUSTMR_NAME,\n"
                + "ORDER_NUMBER,\n"
                + "CUSTMR_NBR,\n"
                + "PO_CUSTOMER_NAME,\n"
                + "INT_DIR_FRO,\n"
                + "SALES_ORDER_CONTRACTS,\n"
                + "PLANNED_QTY,\n"
                + "LUM,\n"
                + "IN_DC_DATE,\n"
                + "REQUEST_DATE,\n"
                + "CONTRACT_DATE,\n"
                + "FISCAL_CONTRACT_MONTH,\n"
                + "STATUS,\n"
                + "STATUS_2,\n"
                + "COMMENTS,\n"
                + "DEMAND_ENTER_DATE,\n"
                + "DEMAND_CHNG_DATE,\n"
                + "DEMAND_CHNG_BY,\n"
                + "PO_PR_NUM,\n"
                + "SHIPMENT_NUM,\n"
                + "CONTAINER_NUM,\n"
                + "BUY_MONTH,\n"
                + "MANAGING_OFFICE,\n"
                + "PLNT_CODE,\n"
                + "PLNT_DES,\n"
                + "VNDR_CODE,\n"
                + "VNDR_DES,\n"
                + "TRANS_MODE,\n"
                + "EXIT_FACTORY_DATE,\n"
                + "STD_COST,"
                        + "CONSECUTIVO,"
                        + "FOLIO "
                        + " "
                + " )VALUES(\n"
                + " ?,\n"
                + "?,\n"
                + "?,\n"
                + "?,\n"
                + "?,\n"
                + "?,\n"
                + "?,\n"
                + "?,\n"
                + "?,\n"
                + "?,\n"
                        
                + "?,\n"
                + "?,\n"
                + "?,\n"
                + "?,\n"
                + "?,\n"
                + "?,\n"
                + "?,\n"
                + "?,\n"
                + "?,\n"
                + "?,\n"
                        
                + "?,\n"
                + "?,\n"
                + "?,\n"
                + "?,\n"
                + "?,\n"
                + "?,\n"
                + "?,\n"
                + "?,\n"
                + "?,\n"
                + "?,\n"
                        
                + "?,\n"
                + "?,\n"
                + "?,\n"
                + "?,\n"
                + "?,\n"
                + "?,\n"
                + "?,\n"
                + "?,\n"    
                + "?,\n"
                + "?,\n"
                        
                + "?, \n"
                + "?, \n"
                + "'"+folio+"' ) "
                + "";
                
                
            // ruta="D:\\"+ruta2; 
              //  ExcelToOracle excelToOracle=new ExcelToOracle();
              ExcelToOracleSP excelToOracleSP=new ExcelToOracleSP();
             mensaje=   excelToOracleSP.InsertSpDSN(insertSql2,ruta,folio); 
                  excelToOracleSP.actDivision();
                  
                }else if(insertSql22.equals("20")){
                    
                 ExcelToOracleSP excelToOracleSP=new ExcelToOracleSP();
                 mensaje=   excelToOracleSP.InsertSp(insertSql2,ruta,folio); 
                 
                  excelToOracleSP.InsertSpACT(folio);
                  excelToOracleSP.InsertSpACTExc(folio);
                
                
                }
                
                else if(insertSql22.equals("30")){
                    
                 ExcelToOracleSP excelToOracleSP=new ExcelToOracleSP();
                 mensaje=   excelToOracleSP.InsertSpActETA_ATC(insertSql2,ruta,folio); 
                 
                 excelToOracleSP.InsertSpACT(folio);
                
                }
                
                
                else if(insertSql22.equals("31")){
                    
                 ExcelToOracleSP excelToOracleSP=new ExcelToOracleSP();
                 mensaje=   excelToOracleSP.InsertSpRDI(ruta,folio); 
                 
               
                
                }
                 else if(insertSql22.equals("33")){
                    
                 ExcelToOracleSP excelToOracleSP=new ExcelToOracleSP();
                 mensaje=   excelToOracleSP.InsertSpSCI(ruta,folio); 
                excelToOracleSP.actDivisionSCI();
                
                
                //AGREGAR SBU GTN
                
                }
                 else if(insertSql22.equals("35")){
                    
                 ExcelToOracleSP excelToOracleSP=new ExcelToOracleSP();
                 mensaje=   excelToOracleSP.InsertSpRDI2(ruta,folio); 
                 
               
                
                }
                 
                else if(insertSql22.equals("36")){
                    
                 ExcelToOracleSP excelToOracleSP=new ExcelToOracleSP();
                 mensaje=   excelToOracleSP.InsertSpibr1(ruta,folio); 
                 
               
                
                }
                else if(insertSql22.equals("37")){
                    
                 ExcelToOracleSP excelToOracleSP=new ExcelToOracleSP();
                 mensaje=   excelToOracleSP.InsertSpibr2(ruta,folio); 
                 
               
                
                }
                else if(insertSql22.equals("40")){
                    
                 ExcelToOracleSP excelToOracleSP=new ExcelToOracleSP();
                 mensaje=   excelToOracleSP.InsertSpgtn2(insertSql2,ruta,folio); 
                 
                  excelToOracleSP.InsertSpACT(folio);
                  excelToOracleSP.InsertSpACTExc(folio);
                
                
                }
                 else if(insertSql22.equals("41")){
                    
                 ExcelToOracleSP excelToOracleSP=new ExcelToOracleSP();
                 mensaje=   excelToOracleSP.InsertSpbobj(insertSql2,ruta,folio); 
                 
               
                
                
                }
                 
                 else{
                
                
                }
               





%>
<%=mensaje%>