package com.flong.codegenerator;  
  
import java.sql.Connection;  
import java.sql.DatabaseMetaData;  
import java.sql.DriverManager;  
import java.sql.PreparedStatement;  
import java.sql.ResultSet;  
import java.sql.SQLException;  
import java.util.List;  
/*** 
 *@Author:liangjilong 
 *@Date:2015��12��5������12:25:12 
 *@Email:jilongliang@sina.com 
 *@Version:1.0 
 *@CopyRight(c)Flong Intergrity Ltd. 
 */  
@SuppressWarnings("all")  
public class DBHelperUtils {  
    private static final Connection conn;  
    private static final String driverClass = PropertiesHelper.getValueByKey("jdbc.driver");  
    private static final String connectionUrl = PropertiesHelper.getValueByKey("jdbc.url");  
    private static final String username = PropertiesHelper.getValueByKey("jdbc.username");  
    private static final String password = PropertiesHelper.getValueByKey("jdbc.password");  
      
    private static DBHelperUtils instance = null;  
    /** 
     * ��������. 
     */  
    static {  
        try {  
            Class.forName(driverClass);  
            conn = DriverManager.getConnection(connectionUrl, username, password);  
        } catch (Exception e) {  
            throw new RuntimeException(e);  
        }  
    }  
  
    /**��������ģʽ 
     * Single 
     * @return 
     */  
    public static DBHelperUtils getInstance() {  
        if (instance == null) {  
            synchronized (DBHelperUtils.class) {  
                instance = new DBHelperUtils();  
            }  
        }  
        return instance;  
    }  
      
      
    /** 
     * ��ѯ���� 
     * @param sql 
     * @param params 
     * @return 
     */  
    public static ResultSet query(String sql, List<Object> params) {  
        System.out.println("sql: " + sql);  
        //System.out.println("params: " + params);  
        try {  
            PreparedStatement psmt = conn.prepareStatement(sql);  
            if(params != null) {  
                for (int i = 0; i < params.size(); i++) {  
                    psmt.setObject(i+1, params.get(i));  
                }  
            }     
            return psmt.executeQuery();  
        } catch (SQLException e) {  
            throw new RuntimeException(e);  
        }  
    }  
  
    /*** 
     * ���� 
     * @param sql 
     * @param params 
     */  
    public static void update(String sql, List<Object> params) {  
        System.out.println("sql: " + sql);  
        //System.out.println("params: " + params);  
        try {  
            PreparedStatement psmt = conn.prepareStatement(sql);  
            if(params != null) {  
                for (int i = 0; i < params.size(); i++) {  
                    psmt.setObject(i+1, params.get(i));  
                }  
            }     
            psmt.executeUpdate();  
        } catch (SQLException e) {  
            throw new RuntimeException(e);  
        }  
    }  
      
    /** 
     * ��ȡ���� 
     * @return 
     */  
    public static Connection getConnection(){  
        try {  
            Class.forName(driverClass);  
            return DriverManager.getConnection(connectionUrl, username, password);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }    
        return null;  
    }  
      
      
      
    /** 
     * ��ȡ����DatabaseMetaData���� 
     * @return 
     */  
    public DatabaseMetaData  getDatabaseMetaData(){  
          
        try {  
            return getInstance().getConnection().getMetaData();  
        } catch (SQLException e) {  
            e.printStackTrace();  
        }  
        return null;  
    }  
      
      
     /**  
     * ������ݿ��һЩ�����Ϣ  
     */    
    public void getDataBaseInformations() {    
        try {               
            System.out.println("���ݿ���֪���û�: "+ getDatabaseMetaData().getUserName());      
            System.out.println("���ݿ��ϵͳ�����Ķ��ŷָ��б�: "+ getDatabaseMetaData().getSystemFunctions());      
            System.out.println("���ݿ��ʱ������ں����Ķ��ŷָ��б�: "+ getDatabaseMetaData().getTimeDateFunctions());      
            System.out.println("���ݿ���ַ��������Ķ��ŷָ��б�: "+ getDatabaseMetaData().getStringFunctions());      
            System.out.println("���ݿ⹩Ӧ������ ��schema�� ����ѡ����: "+ getDatabaseMetaData().getSchemaTerm());      
            System.out.println("���ݿ�URL: " + getDatabaseMetaData().getURL());      
            System.out.println("�Ƿ�����ֻ��:" + getDatabaseMetaData().isReadOnly());      
            System.out.println("���ݿ�Ĳ�Ʒ����:" + getDatabaseMetaData().getDatabaseProductName());      
            System.out.println("���ݿ�İ汾:" + getDatabaseMetaData().getDatabaseProductVersion());      
            System.out.println("�������������:" + getDatabaseMetaData().getDriverName());      
            System.out.println("��������İ汾:" + getDatabaseMetaData().getDriverVersion());     
    
            System.out.println();      
            System.out.println("���ݿ���ʹ�õı�����");      
            ResultSet rs = getDatabaseMetaData().getTableTypes();      
            while (rs.next()) {      
                System.out.println(rs.getString(1));      
            }      
            rs.close();                  
            System.out.println();    
        } catch (SQLException e) {    
            e.printStackTrace();    
        }    
    }    
      
    /**  
     * ��ø��û���������б�  
     */    
    public void getAllTableList(String schemaName) {    
        try {    
            // table type. Typical types are "TABLE", "VIEW", "SYSTEM TABLE", "GLOBAL TEMPORARY", "LOCAL TEMPORARY", "ALIAS", "SYNONYM".    
            String[] types = { "TABLE" };    
            ResultSet rs = getDatabaseMetaData().getTables(null, schemaName, "%", types);    
            while (rs.next()) {    
                String tableName = rs.getString("TABLE_NAME");  //����    
                String tableType = rs.getString("TABLE_TYPE");  //������    
                String remarks = rs.getString("REMARKS");       //��ע    
                System.out.println(tableName + "-" + tableType + "-" + remarks);    
            }    
        } catch (SQLException e) {    
            e.printStackTrace();    
        }    
    }    
      
    /**  
     * ��ø��û������������ͼ  
     */    
    public void getAllViewList(String schemaName) {    
         try{      
             String[] types = { "VIEW" };                   
             ResultSet rs = getDatabaseMetaData().getTables(null, schemaName, "%", types);    
             while (rs.next()){    
                 String viewName = rs.getString("TABLE_NAME"); //��ͼ��    
                 String viewType = rs.getString("TABLE_TYPE"); //��ͼ����    
                 String remarks = rs.getString("REMARKS");      //��ͼ��ע    
                 System.out.println(viewName + "-" + viewType + "-" + remarks);    
             }    
         } catch (SQLException e) {    
             e.printStackTrace();    
         }    
    }    
        
     /**    
     * ������ݿ������з�������    
     */      
    public void getAllSchemas(){    
        try{    
            ResultSet rs = getDatabaseMetaData().getSchemas();     
            while (rs.next()){       
                String tableSchem = rs.getString("TABLE_SCHEM");       
                System.out.println(tableSchem);       
            }       
        } catch (SQLException e){    
            e.printStackTrace();       
        }       
    }       
    
    
    /**  
     * ��ñ����ͼ�е���������Ϣ  
     */    
    public void getTableColumns(String schemaName, String tableName) {    
             
        try{       
                    
            ResultSet rs = getDatabaseMetaData().getColumns(null, schemaName, tableName, "%");                
            while (rs.next()){    
                    String tableCat = rs.getString("TABLE_CAT");//��Ŀ¼������Ϊ�գ�                    
                    String tableSchemaName = rs.getString("TABLE_SCHEM");//��ļܹ�������Ϊ�գ�       
                    String tableName_ = rs.getString("TABLE_NAME");//����    
                    String columnName = rs.getString("COLUMN_NAME");//����    
                    int dataType = rs.getInt("DATA_TYPE"); //��Ӧ��java.sql.Types����       
                    String dataTypeName = rs.getString("TYPE_NAME");//java.sql.Types����   ����    
                    int columnSize = rs.getInt("COLUMN_SIZE");//�д�С    
                    int decimalDigits = rs.getInt("DECIMAL_DIGITS");//С��λ��    
                    int numPrecRadix = rs.getInt("NUM_PREC_RADIX");//������ͨ����10��2��    
                    int nullAble = rs.getInt("NULLABLE");//�Ƿ�����Ϊnull    
                    String remarks = rs.getString("REMARKS");//������    
                    String columnDef = rs.getString("COLUMN_DEF");//Ĭ��ֵ    
                    int sqlDataType = rs.getInt("SQL_DATA_TYPE");//sql��������    
                    int sqlDatetimeSub = rs.getInt("SQL_DATETIME_SUB");   //SQL����ʱ���?    
                    int charOctetLength = rs.getInt("CHAR_OCTET_LENGTH");   //char���͵����е�����ֽ���    
                    int ordinalPosition = rs.getInt("ORDINAL_POSITION");  //�����е���������1��ʼ��    
                        
                    /**  
                     * ISO��������ȷ��ĳһ�е�Ϊ���ԡ�  
                     * ��---����ò������԰�����ֵ;  
                     * ��---����������ܰ�����ֵ  
                     * ���ַ���---�������Ϊ������δ֪��  
                     */    
                    String isNullAble = rs.getString("IS_NULLABLE");    
                        
                    /**  
                     * ָʾ�����Ƿ����Զ�����  
                     * ��---����������Զ�����  
                     * ��---��������Զ�������  
                     * ���ִ�---�������ȷ�����Ƿ�  
                     * �����Զ������Ĳ�����δ֪  
                     */    
                    String isAutoincrement = rs.getString("IS_AUTOINCREMENT");       
                        
                    System.out.println(tableCat + "-" + tableSchemaName + "-" + tableName_ + "-" + columnName + "-"      
                            + dataType + "-" + dataTypeName + "-" + columnSize + "-" + decimalDigits + "-" + numPrecRadix       
                            + "-" + nullAble + "-" + remarks + "-" + columnDef + "-" + sqlDataType + "-" + sqlDatetimeSub       
                            + charOctetLength + "-" + ordinalPosition + "-" + isNullAble + "-" + isAutoincrement + "-");       
                }       
            } catch (SQLException e){    
                e.printStackTrace();       
            }    
    }    
    
    
    /**  
     * ���һ�����������Ϣ  
     */    
    public void getIndexInfo(String schemaName, String tableName) {    
        try{    
            ResultSet rs = getDatabaseMetaData().getIndexInfo(null, schemaName, tableName, true, true);    
            while (rs.next()){    
                boolean nonUnique = rs.getBoolean("NON_UNIQUE");//��Ψһ����(Can index values be non-unique. false when TYPE is  tableIndexStatistic   )    
                String indexQualifier = rs.getString("INDEX_QUALIFIER");//����Ŀ¼������Ϊ�գ�    
                String indexName = rs.getString("INDEX_NAME");//����������    
                short type = rs.getShort("TYPE");//��������    
                short ordinalPosition = rs.getShort("ORDINAL_POSITION");//��������˳���    
                String columnName = rs.getString("COLUMN_NAME");//����    
                String ascOrDesc = rs.getString("ASC_OR_DESC");//������˳��:�����ǽ���    
                int cardinality = rs.getInt("CARDINALITY");   //����    
                System.out.println(nonUnique + "-" + indexQualifier + "-" + indexName + "-" + type + "-" + ordinalPosition + "-" + columnName + "-" + ascOrDesc + "-" + cardinality);       
            }       
        } catch (SQLException e){    
            e.printStackTrace();       
        }     
    }    
    
    
    /**  
     * ���һ�����������Ϣ  
     */    
    public void getAllPrimaryKeys(String schemaName, String tableName) {    
        try{    
            ResultSet rs = getDatabaseMetaData().getPrimaryKeys(null, schemaName, tableName);    
            while (rs.next()){    
                String columnName = rs.getString("COLUMN_NAME");//����    
                short keySeq = rs.getShort("KEY_SEQ");//���к�(������ֵ1��ʾ��һ�е�������ֵ2���������ڵĵڶ���)    
                String pkName = rs.getString("PK_NAME"); //��������      
                System.out.println(columnName + "-" + keySeq + "-" + pkName);       
            }    
        }catch (SQLException e){    
            e.printStackTrace();    
        }    
    }    
    
    
    /**  
     * ���һ����������Ϣ  
     */    
    public void getAllExportedKeys(String schemaName, String tableName) {    
            
        try{    
            ResultSet rs = getDatabaseMetaData().getExportedKeys(null, schemaName, tableName);    
            while (rs.next()){    
                String pkTableCat = rs.getString("PKTABLE_CAT");//�������Ŀ¼������Ϊ�գ�    
                String pkTableSchem = rs.getString("PKTABLE_SCHEM");//������ļܹ�������Ϊ�գ�    
                String pkTableName = rs.getString("PKTABLE_NAME");//��������     
                String pkColumnName = rs.getString("PKCOLUMN_NAME");//��������      
                String fkTableCat = rs.getString("FKTABLE_CAT");//����ı��Ŀ¼������Ϊ�գ����ڣ�����Ϊnull��    
                String fkTableSchem = rs.getString("FKTABLE_SCHEM");//�����ļܹ�������Ϊ�գ����ڣ�����Ϊ�գ�    
                String fkTableName = rs.getString("FKTABLE_NAME");//�������    
                String fkColumnName = rs.getString("FKCOLUMN_NAME"); //�������                    
                short keySeq = rs.getShort("KEY_SEQ");//���кţ������ֵ1��ʾ��һ�е������ֵ2�����ڵڶ��е��������    
                    
                /**  
                 * hat happens to foreign key when primary is updated:   
                 * importedNoAction - do not allow update of primary key if it has been imported  
                 * importedKeyCascade - change imported key to agree with primary key update   
                 * importedKeySetNull - change imported key to NULL if its primary key has been updated  
                 * importedKeySetDefault - change imported key to default values if its primary key has been updated  
                 * importedKeyRestrict - same as importedKeyNoAction (for ODBC 2.x compatibility)     
                 */    
                short updateRule = rs.getShort("UPDATE_RULE");    
                    
                /**  
                 * What happens to the foreign key when primary is deleted.  
                 * importedKeyNoAction - do not allow delete of primary key if it has been imported  
                 * importedKeyCascade - delete rows that import a deleted key   
                 * importedKeySetNull - change imported key to NULL if its primary key has been deleted   
                 * importedKeyRestrict - same as importedKeyNoAction (for ODBC 2.x compatibility)  
                 * importedKeySetDefault - change imported key to default if its primary key has been deleted     
                 */    
                short delRule = rs.getShort("DELETE_RULE");    
                String fkName = rs.getString("FK_NAME");//��������ƣ�����Ϊ�գ�    
                String pkName = rs.getString("PK_NAME");//���������ƣ�����Ϊ�գ�    
                    
                /**  
                 * can the evaluation of foreign key constraints be deferred until commit  
                 * importedKeyInitiallyDeferred - see SQL92 for definition  
                 * importedKeyInitiallyImmediate - see SQL92 for definition   
                 * importedKeyNotDeferrable - see SQL92 for definition     
                 */    
                short deferRability = rs.getShort("DEFERRABILITY");    
                    
                System.out.println(pkTableCat + "-" + pkTableSchem + "-" + pkTableName + "-" + pkColumnName + "-"      
                        + fkTableCat + "-" + fkTableSchem + "-" + fkTableName + "-" + fkColumnName + "-" + keySeq + "-"      
                        + updateRule + "-" + delRule + "-" + fkName + "-" + pkName + "-" + deferRability);       
            }    
        } catch (SQLException e){    
            e.printStackTrace();       
        }    
    }    
    
    
    public void closeResource() {    
        try {    
            if (conn != null) {    
                conn.close();    
            }    
        } catch (SQLException e) {    
            e.printStackTrace();    
        }    
    }    
    
      
    public static void main(String[] args) throws Exception {  
        DBHelperUtils metaData = new DBHelperUtils();    
        metaData.getDataBaseInformations();    
        metaData.getAllTableList(null);    
        metaData.getAllViewList(null);    
        metaData.getAllSchemas();    
        metaData.getTableColumns(null, "test");    
        metaData.getIndexInfo(null, "test");    
        metaData.getAllPrimaryKeys(null, "test");    
        metaData.getAllExportedKeys(null, "test");    
    }  
      
  
}  