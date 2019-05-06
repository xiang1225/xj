package com.flong.codegenerator;  
  
import java.sql.Connection;  
import java.sql.DatabaseMetaData;  
import java.sql.PreparedStatement;  
import java.sql.ResultSet;  
import java.sql.ResultSetMetaData;  
import java.sql.SQLException;  
import java.sql.Timestamp;  
import java.text.SimpleDateFormat;  
import java.util.ArrayList;  
import java.util.Date;  
import java.util.HashMap;  
import java.util.List;  
import java.util.Map;  
import java.util.Set;  
  
import org.apache.commons.lang3.StringUtils;  
/*** 
 *@Author:liangjilong 
 *@Date:2015��12��5������12:25:12 
 *@Email:jilongliang@sina.com 
 *@Version:1.0 
 *@CopyRight(c)Flong Intergrity Ltd. 
 *@Desction:�������������������������ʵ��˼·������������������������������ 
 * 
 *   ����ڿ��ٿ����Ĺ����У�Ϊ�˽�ʡʱ��ͳɱ��ܶ��˶��Ὺ���Լ��Ĵ��������������ҳ�Ϊ����Ա���������бز����ٵ�����. 
 *   ���������������������������������������������������������� 
 *    ��һ�֣��������ݿ��Ȼ��ͨ��jdbc�������ݿ��ٶ�ȡ����ֶε����Գ�������Entity,Dao,Service,Controller��JSP�ȴ��� 
 *   ���ֱ����������ݿ�ͱ��˼��,ͨ������ȥ��ȡ���ݿ������Ե���Ϣ,Ȼ����֯����ͨ���ļ��������ļ�. 
 *    
 *   ���������������������������������������������������������� 
 *   �ڶ��֣��Ѿ���ƺ����ݿ���ĵ��������б���ֶ��������õ�EXCEL����CSV��ʽ���ļ�ͨ��JXL/POI����ȥ��ȡ�ļ����ֶ�ʵ�� 
 *   Entity,Dao,Service,Controller��JSP,�ڹ����л����Freemaker,Velocityȥʵ��.�����jsp��Ȼ��ͨ��һ��ORM(hibernate, 
 *   ibatis,myibatis��)���������������ݿ��.�����������ݿ���˼��. ������java�Ĵ���һ�㲻���齨ORMӳ�����ӱ��ϵ,ͨ�� 
 *   SQLȥ������ϵ,Ϊɶ����Ϊ��һЩ���͵Ĺ�˾�磺���У�����Ͱͣ����ŵȹ�˾���ܶ���Ŀ���������������ݿ����ٽ������ϵ�� 
 *   ��Ϊ��Щҵ���ӵ������ͨ��SQL�ͳ�����ƵĽ��������ORMӳ���ϵ����ռ����.�����Ż�����/ά��/����Ը��Ӻõ�. 
 *   ���������������������������������������������������������� 
 *    
 *   �˳�����MySQLΪ����δʵ���������ݿ�.�˳���������Ż��ģ�Ϊ����Щ��ѧ��,�Ͳ���̫��Ĵ�����Ż�.һЩ���ֻ��̸��õ��� 
 *   �������˳���ֻ�ṩ�ο���ѧϰ,����ʲô���⣬���Զ�ָ��.��ͬѧϰ�ͽ���.лл��~ 
 *   ���������������������������������������������������������� 
 */  
@SuppressWarnings("all")  
public class CodeGenerator {  
      
       
    /*************************����****Begin************************************/  
    private static final String myEmail="jilongliang@sina.com";//Email  
    private static final String Version="1.0";//�汾  
    private static final String Description=" ";//����  
      
    public static final String ENTER = "\n";//����  
    public static final String TAB = "    ";//tab�ո�.  
    public static final String NAME = "NAME";  
    public static final String TABLE_CAT = "TABLE_CAT";//�� catalog  
    public static final String TABLE_SCHEM = "TABLE_SCHEM";//�� schema  
    public static final String TABLE_NAME = "TABLE_NAME";//����  
    public static final String TABLE_TYPE = "TABLE_TYPE";//������  
    public static final String REMARKS = "REMARKS";//��ע��  
    public static final String TYPE = "TYPE";//�������  
    public static final String SIZE = "SIZE";//��С  
    public static final String CLASS = "CLASS";//���  
      
    /*************************����****End************************************/  
      
    public static final String NOW_DATE = new SimpleDateFormat("yyyy-MM-dd").format(new Date());  
    /***************��ȡ���ݿ����������************/  
    public static final String DB_NAME = PropertiesHelper.getValueByKey("jdbc.url").substring(  
                                         PropertiesHelper.getValueByKey("jdbc.url").lastIndexOf("/")+1,  
                                         PropertiesHelper.getValueByKey("jdbc.url").indexOf("?") == -1?  
                                         PropertiesHelper.getValueByKey("jdbc.url").length():  
                                         PropertiesHelper.getValueByKey("jdbc.url").indexOf("?"));  
    //�����û�ȡ���̵ı���·��  
    public static final String ROOT_PACKAGE = PropertiesHelper.getValueByKey("rootPackage");  
    //��ȡ����.  
    public static final String AUTHOR = PropertiesHelper.getValueByKey("author");  
    //���Ա�ĺ�׺.  
    public static final List<String> IGNORE_TABLE_PREFIX = new ArrayList<String>();  
  
    /*******��������*******/  
    static {  
        String ignoreTablePrefix = PropertiesHelper.getValueByKey("ignoreTablePrefix");  
        if(ignoreTablePrefix.length() > 0) {  
            String[] ignoreTablePrefixs = ignoreTablePrefix.split("\\s*\\,\\s*");  
            for (String elem : ignoreTablePrefixs) {  
                IGNORE_TABLE_PREFIX.add(elem);  
            }  
        }  
    }  
  
    /*** 
     * ����ʵ����Ĵ��� 
     * @param table 
     * @throws Exception 
     */  
    public void createEntityClass(String table) throws Exception {  
        String tableConstantName = getTableConstantName(table);  
          
        String className = getClassName(tableConstantName);  
        StringBuilder buffer = new StringBuilder();  
        buffer.append("package " + ROOT_PACKAGE + ".entity;").append(ENTER);  
        buffer.append("import java.util.Date;").append(ENTER);  
        buffer.append("import com.flong.commons.persistence.Entity;").append(ENTER);  
        buffer.append("import com.flong.commons.persistence.annotation.Column;").append(ENTER);  
        buffer.append("import com.flong.commons.persistence.annotation.Id;").append(ENTER);  
        buffer.append("import com.flong.commons.persistence.annotation.Relation;").append(ENTER);  
        buffer.append("import com.flong.commons.persistence.annotation.Table;").append(ENTER);  
        buffer.append(ENTER);  
        buffer.append(ENTER);  
        buffer.append("/**\n * @Created��" + NOW_DATE + "\n * @Author " + AUTHOR + "\n");  
        buffer.append(" * @Version:").append(Version).append(ENTER);  
        buffer.append(" * @Description:").append(className).append(ENTER);  
        buffer.append(" * @Email:").append(myEmail).append("\n*/");  
        buffer.append(ENTER);  
        buffer.append("@Relation(" + className + ".TABLE)");  
        buffer.append(ENTER);  
        buffer.append("public class " + className + " extends Entity {");  
        buffer.append(ENTER);  
        buffer.append(ENTER);  
        buffer.append(TAB);  
        buffer.append("/** �������� */");  
        buffer.append(ENTER);  
        buffer.append(TAB);  
        buffer.append("public static final String TABLE = Table." + tableConstantName + ";");  
        buffer.append(ENTER);  
        buffer.append(ENTER);  
        buffer.append(TAB);  
        buffer.append("/**");  
        buffer.append(ENTER);  
        buffer.append(TAB);  
        buffer.append(" * ��������");  
        buffer.append(ENTER);  
        buffer.append(TAB);  
        buffer.append(" */");  
        buffer.append(ENTER);  
        for (Map<String, Object> col : getCols(table)) {  
            String colName = col.get(NAME).toString().toUpperCase();  
            buffer.append(TAB);//�����ֶα���  
            buffer.append("public static final String COL_" + colName + " = \"" + colName + "\";//"+col.get(REMARKS));  
            buffer.append(ENTER);  
        }  
        buffer.append(ENTER);  
        buffer.append(TAB);  
        buffer.append("/**");  
        buffer.append(ENTER);  
        buffer.append(TAB);  
        buffer.append(" * ������");  
        buffer.append(ENTER);  
        buffer.append(TAB);  
        buffer.append(" */");  
          
        String tablePrimaryKeys = getTablePrimaryKeys(table);//���������  
          
        //if(col.get(NAME).toString().equalsIgnoreCase("ID")) {  
        if(tablePrimaryKeys!=null){   
            buffer.append(ENTER+TAB);  
              
            //���������Ϊ�յ�ʱ��͸�һ��@Idע��.  
            //�����hibernate�Ŀ��Ը�������ע�⣬��@GeneratedValue(strategy = GenerationType.IDENTITY)   @SequenceGenerator��  
            //��Ҫ�ڰ�������ͷ������  
            //import javax.persistence.Column;  
            //import javax.persistence.Entity;  
            //import javax.persistence.GeneratedValue;  
            //import javax.persistence.GenerationType;  
            //import javax.persistence.Id;  
            //import javax.persistence.Table;  
            buffer.append("@Id");  
            //���ﲻ��ֵ��,��Ϊ�������forѭ����һ��.  
            //sb.append("@Column(COL_" + tablePrimaryKeys + ")");  
        }  
        for (Map<String, Object> col : getCols(table)) {  
            buffer.append(TAB);  
            buffer.append(ENTER);  
            buffer.append(TAB);  
            buffer.append("@Column(COL_" + col.get(NAME).toString().toUpperCase() + ")");  
            buffer.append(ENTER);  
            buffer.append(TAB);  
            buffer.append("private ");  
            //���д������˼����˵������ҵ����ݿ����ֶ���ΪID��ʱ�򣬻��׺��_ID�ľ���Ϊ������,���Һ��Դ�Сд�͸�һ��Long  
            //��ʵ�ʹ�����Ӧ���ж��������ֶ��ǲ���Ϊ��PrimaryKey����ΪLong���ʺ�.  
            //if(col.get(NAME).toString().equalsIgnoreCase("ID") || col.get(NAME).toString().toUpperCase().endsWith("_ID")) {  
            if(Class.forName(col.get(CLASS).toString()).isAssignableFrom(Date.class) || Class.forName(col.get(CLASS).toString()) == Timestamp.class) {  
                buffer.append("Date");  
            } else if(getClassName(col.get(NAME).toString()).equals(Class.forName(col.get(CLASS).toString()).getSimpleName())) {  
                buffer.append(col.get(CLASS));  
            } else {  
                buffer.append(Class.forName(col.get(CLASS).toString()).getSimpleName());  
            }  
            //sb.append(" " + getFieldName(col.get(NAME).toString()) + ";");  
            buffer.append(" " + col.get(NAME).toString() + ";");  
            buffer.append(ENTER);  
        }  
        buffer.append(ENTER);  
        for (Map<String, Object> col : getCols(table)){  
            buffer.append(TAB);  
            buffer.append("public ");  
            if(Class.forName(col.get(CLASS).toString()).isAssignableFrom(Date.class) || Class.forName(col.get(CLASS).toString()) == Timestamp.class) {  
                buffer.append("Date");  
            } else if(getClassName(col.get(NAME).toString()).equals(Class.forName(col.get(CLASS).toString()).getSimpleName())) {  
                buffer.append(col.get(CLASS));  
            } else {  
                buffer.append(Class.forName(col.get(CLASS).toString()).getSimpleName());  
            }  
            buffer.append(" ").append("get").append(col.get(NAME).toString().replaceFirst("\\b(\\w)|\\s(\\w)", col.get(NAME).toString().substring(0,1).toUpperCase()));  
            buffer.append("() {");  
            buffer.append(ENTER);  
            buffer.append(TAB);  
            buffer.append(TAB);  
            buffer.append("return ").append(col.get(NAME).toString()).append(";");  
            buffer.append(ENTER);  
            buffer.append(TAB);  
            buffer.append("}");  
            buffer.append(ENTER);  
            buffer.append(TAB);  
            buffer.append("public void ").append("set").append(col.get(NAME).toString().replaceFirst("\\b(\\w)|\\s(\\w)", col.get(NAME).toString().substring(0,1).toUpperCase()));  
            buffer.append("(");  
            if(Class.forName(col.get(CLASS).toString()).isAssignableFrom(Date.class) || Class.forName(col.get(CLASS).toString()) == Timestamp.class) {  
                buffer.append("Date");  
            } else if(getClassName(col.get(NAME).toString()).equals(Class.forName(col.get(CLASS).toString()).getSimpleName())) {  
                buffer.append(col.get(CLASS));  
            } else {  
                buffer.append(Class.forName(col.get(CLASS).toString()).getSimpleName());  
            }  
            buffer.append(" ").append(col.get(NAME).toString());  
            buffer.append(") {");  
            buffer.append(ENTER);  
            buffer.append(TAB);  
            buffer.append(TAB);  
            buffer.append("this.").append(col.get(NAME).toString()).append(" = ").append(col.get(NAME).toString()).append(";");  
            buffer.append(ENTER);  
            buffer.append(TAB);  
            buffer.append("}");  
            buffer.append(ENTER);  
        }  
        buffer.append("}");  
        buffer.append(ENTER);  
        FileUtils.save("output-code/" + ROOT_PACKAGE.replaceAll("\\.", "/") + "/entity/" + className + ".java", buffer.toString());  
    }  
      
    /*** 
     * ����dao�ӿ�interface��Ĵ��� 
     * @param table 
     * @throws Exception 
     */  
    public void createDaoClass(String table) throws Exception {  
        String className = getClassName(getTableConstantName(table));  
          
        String objectName = StringUtils.uncapitalize(className);  
          
        StringBuilder buffer = new StringBuilder();  
        buffer.append("package " + ROOT_PACKAGE + ".dao;").append(ENTER);  
        buffer.append("import java.io.Serializable;").append(ENTER);  
        buffer.append("import java.util.List;").append(ENTER);  
        buffer.append("import com.flong.commons.persistence.bean.SimplePage;").append(ENTER);  
        buffer.append("import com.flong.commons.persistence.dao.EntityDao;").append(ENTER);  
        buffer.append("import com.flong.modules.pojo."+className+";").append(ENTER);  
          
        buffer.append(ENTER);  
        buffer.append(ENTER);  
        buffer.append("/**\n * @Created��" + NOW_DATE + "\n * @Author " + AUTHOR + "\n");  
        buffer.append(" * @Version:").append(Version).append(ENTER);  
        buffer.append(" * @Description:").append(className).append(ENTER);  
        buffer.append(" * @Email:").append(myEmail).append("\n*/");  
        buffer.append(ENTER);  
          
        buffer.append("public interface " + className + "Dao extends EntityDao<" + className + "> {").append(ENTER);  
          
          
        buffer.append("/**��ѯ*/").append(ENTER);  
        buffer.append(" public List<"+className+"> list(SimplePage simplePage,"+className+" "+objectName+");").append(ENTER);  
          
        buffer.append("/**��������*/").append(ENTER);  
        buffer.append(" public void saveData("+className+" "+objectName+");").append(ENTER);  
          
        buffer.append("/**��������*/").append(ENTER);  
          
        buffer.append(" public void updateData("+className+" "+objectName+");").append(ENTER);  
          
        buffer.append("/**ɾ������*/").append(ENTER);  
          
        buffer.append(" public void deleteData(Long pk);").append(ENTER);  
          
        buffer.append(ENTER);  
        buffer.append(ENTER);  
        buffer.append("}");  
        buffer.append(ENTER);  
        FileUtils.save("output-code/" + ROOT_PACKAGE.replaceAll("\\.", "/") + "/dao/" + className + "Dao.java", buffer.toString());  
    }  
      
    /*** 
     * ����dao��ʵ����Ĵ��� 
     * @param table 
     * @throws Exception 
     */  
    public void createDaoImplClass(String table) throws Exception {  
        String className = getClassName(getTableConstantName(table));  
        String objectName =  StringUtils.uncapitalize(className);  
        String tableName = StringUtils.lowerCase(getTableConstantName(table));//��ȡ����  
          
        StringBuilder buffer = new StringBuilder();  
        buffer.append("package " + ROOT_PACKAGE + ".dao.impl;").append(ENTER);  
        buffer.append("import java.io.Serializable;").append(ENTER);  
        buffer.append("import org.apache.commons.lang3.StringUtils;").append(ENTER);  
        buffer.append("import org.springframework.stereotype.Repository;").append(ENTER);  
        buffer.append("import com.flong.commons.persistence.bean.SimplePage;").append(ENTER);  
        buffer.append("import com.flong.commons.persistence.dao.impl.EntityDaoSupport;").append(ENTER);  
        buffer.append("import com.flong.modules.dao."+className+"Dao;").append(ENTER);  
        buffer.append("import com.flong.modules.pojo."+className+";").append(ENTER);  
   
          
        buffer.append(ENTER);  
        buffer.append(ENTER);  
        buffer.append("/**\n * @Created��" + NOW_DATE + "\n * @Author " + AUTHOR + "\n");  
        buffer.append(" * @Version:").append(Version).append(ENTER);  
        buffer.append(" * @Description:").append(className).append(ENTER);  
        buffer.append(" * @Email:").append(myEmail).append("\n*/");  
        buffer.append(ENTER);  
        buffer.append("@Repository");//�����spring��ע��  
        buffer.append(ENTER);  
        buffer.append("public class " + className + "DaoImpl extends EntityDaoSupport<" + className + "> implements " + className + "Dao {");  
          
          
        buffer.append("/**��ѯ*/").append(ENTER);  
        buffer.append(" public List<"+className+"> list(SimplePage simplePage,"+className+" "+objectName+"){").append(ENTER);  
          
        buffer.append(ENTER);  
          
        String mergeField= "";//�ϲ��ֶ�.  
          
        //--������ȡ��,��ƴ���ַ���,SQL�Ĳ�ѯ��,��ѯ��������*ȥ��ѯ���������.  
        for (Map<String, Object> col : getCols(table)){  
              
            //  
            if(col.get(NAME).toString()!=null){  
                mergeField +=col.get(NAME).toString()+",";//�ϲ��ֶβ���,�����ֶ���  
            }  
        }  
        //ȥ�����һ��,��Ȼ��ƴ�ӳ�һ����ɵ�select��ѯ�ֶ�  
        if(mergeField!=null){  
            mergeField = mergeField.substring(0, mergeField.length()-1);  
        }  
          
        buffer.append("    String sql = ").append("\" select "+mergeField+" from ").append(tableName).append(" where 1=1 \" ").append(ENTER);//���TABLE��ʵ����ı���  
        //daoQuery����ǵײ��װ��һ���ӿ�,�Ը����Ը����Լ������װ.  
        buffer.append("    List<"+className+"> list= daoQuery.query(sql,"+className+".class,simplePage);").append(ENTER);  
        buffer.append(" return list;").append(ENTER);  
          
        buffer.append("}").append(ENTER);//��ѯ�Ľ���{  
          
          
        buffer.append("/**��������*/").append(ENTER);  
        buffer.append(" public void saveData("+className+" "+objectName+"){").append(ENTER);  
        buffer.append("   try {").append(ENTER);  
        buffer.append("      saveOrUpdate("+className+");").append(ENTER);  
        buffer.append("   } catch (DaoAccessException e) {").append(ENTER);  
        buffer.append("      e.printStackTrace();").append(ENTER);  
        buffer.append("  }").append(ENTER);  
          
        buffer.append("}");  
          
          
        buffer.append("/**��������*/").append(ENTER);  
          
        buffer.append(" public void updateData("+className+" "+objectName+"){").append(ENTER);  
          
        buffer.append("   try {").append(ENTER);  
        buffer.append("      saveOrUpdate("+className+");").append(ENTER);  
        buffer.append("   } catch (DaoAccessException e) {").append(ENTER);  
        buffer.append("      e.printStackTrace();").append(ENTER);  
        buffer.append("  }").append(ENTER);  
          
        buffer.append("}");  
          
          
        buffer.append("/**ɾ������*/").append(ENTER);  
        buffer.append(" public void deleteData(Long pk){").append(ENTER);  
          
        buffer.append("   try {").append(ENTER);  
        buffer.append("      delete(pk);").append(ENTER);  
        buffer.append("   } catch (DaoAccessException e) {").append(ENTER);  
        buffer.append("      e.printStackTrace();").append(ENTER);  
        buffer.append("  }").append(ENTER);  
          
          
        buffer.append("}");  
          
        buffer.append(ENTER);  
        buffer.append(ENTER);  
        buffer.append("}");  
        buffer.append(ENTER);  
        FileUtils.save("output-code/" + ROOT_PACKAGE.replaceAll("\\.", "/") + "/dao/impl/" + className + "DaoImpl.java", buffer.toString());  
    }  
  
      
    /*** 
     * ����Service�Ľӿ� 
     * createServiceClass 
     * @param table 
     */  
    public void createServiceClass(String table) {  
  
        String className = getClassName(getTableConstantName(table));  
        String objectName = StringUtils.uncapitalize(className);  
          
        StringBuilder buffer = new StringBuilder();  
          
        buffer.append("package " + ROOT_PACKAGE + ".service;");  
  
        buffer.append("import java.io.Serializable;").append(ENTER);  
        buffer.append("import java.util.List;").append(ENTER);  
        buffer.append("import com.flong.commons.persistence.bean.SimplePage;").append(ENTER);  
        buffer.append("import com.flong.commons.persistence.dao.EntityDao;").append(ENTER);  
        buffer.append("import com.flong.modules.pojo."+className+";").append(ENTER);  
          
          
        buffer.append(ENTER);  
        buffer.append(ENTER);  
        buffer.append("/**\n * @Created��" + NOW_DATE + "\n * @Author " + AUTHOR + "\n");  
        buffer.append(" * @Version:").append(Version).append(ENTER);  
        buffer.append(" * @Description:").append(className).append(ENTER);  
        buffer.append(" * @Email:").append(myEmail).append("\n*/");  
        buffer.append(ENTER);  
        buffer.append("public interface " + className + "Service {");  
          
        buffer.append("/**��ѯ*/").append(ENTER);  
        buffer.append(" public List<"+className+"> list(SimplePage simplePage,"+className+" "+objectName+");").append(ENTER);  
          
        buffer.append("/**��������*/").append(ENTER);  
        buffer.append(" public void saveData("+className+" "+objectName+");").append(ENTER);  
          
        buffer.append("/**��������*/").append(ENTER);  
          
        buffer.append(" public void updateData("+className+" "+objectName+");").append(ENTER);  
          
        buffer.append("/**ɾ������*/").append(ENTER);  
          
        buffer.append(" public void deleteData(Long pk);").append(ENTER);  
          
        buffer.append(ENTER);  
        buffer.append(ENTER);  
        buffer.append("}");  
        buffer.append(ENTER);  
        FileUtils.save("output-code/" + ROOT_PACKAGE.replaceAll("\\.", "/") + "/service/" + className + "Service.java", buffer.toString());  
      
    }  
  
    /*** 
     * ����Service���ʵ���� 
     * �����Dao��ʵ�ֵĶ��̳���EntityDaoSupport����Ҫ��Ϊ����������service�ֳɵ����鱣��. 
     * createServiceImplClass 
     * @param table 
     */  
    public void createServiceImplClass(String table) {  
  
        String className = getClassName(getTableConstantName(table));  
      
        String objectName = StringUtils.uncapitalize(className);  
          
        StringBuilder buffer = new StringBuilder();  
          
        buffer.append("package " + ROOT_PACKAGE + ".service.impl;");  
        buffer.append("import java.io.Serializable;").append(ENTER);  
        buffer.append("import java.util.List;").append(ENTER);  
        buffer.append("import org.springframework.beans.factory.annotation.Autowired;").append(ENTER);  
        buffer.append("import org.springframework.stereotype.Service;").append(ENTER);  
        buffer.append("import com.flong.commons.persistence.bean.SimplePage;").append(ENTER);  
        buffer.append("import com.flong.commons.persistence.dao.impl.EntityDaoSupport;").append(ENTER);  
        buffer.append("import com.flong.modules.dao."+className+"Dao;").append(ENTER);  
        buffer.append("import com.flong.modules.pojo."+className+";").append(ENTER);  
        buffer.append("import com.flong.modules.service."+className+"Service;").append(ENTER);  
          
           
        buffer.append(ENTER);  
        buffer.append(ENTER);  
        buffer.append("/**\n * @Created��" + NOW_DATE + "\n * @Author " + AUTHOR + "\n");  
        buffer.append(" * @Version:").append(Version).append(ENTER);  
        buffer.append(" * @Description:").append(className).append(ENTER);  
        buffer.append(" * @Email:").append(myEmail).append("\n*/");  
        buffer.append(ENTER);  
        buffer.append("@Service");  
        buffer.append(ENTER);  
        buffer.append("public class " + className + "ServiceImpl extends EntityDaoSupport  implements " + className + "Service {").append(ENTER);  
        buffer.append("@Autowired "+className+"Dao "+objectName+"Dao;");  
          
        buffer.append("/**��ѯ*/").append(ENTER);  
        buffer.append(" public List<"+className+"> list(SimplePage simplePage,"+className+" "+objectName+"){").append(ENTER);  
        buffer.append("     return "+objectName+"Dao.list(simplePage,"+objectName+");").append(ENTER);  
        buffer.append("}").append(ENTER);//��ѯ�Ľ���{  
          
          
          
        buffer.append("/**��������*/").append(ENTER);  
        buffer.append(" public void saveData("+className+" "+objectName+"){").append(ENTER);  
              
        buffer.append(    objectName+"Dao.saveData("+objectName+");").append(ENTER);  
        buffer.append("}");  
          
        buffer.append("/**��������*/").append(ENTER);  
          
        buffer.append(" public void updateData("+className+" "+objectName+"){").append(ENTER);  
        buffer.append(    objectName+"Dao.updateData("+objectName+");").append(ENTER);  
          
        buffer.append("}");  
        buffer.append("/**ɾ������*/").append(ENTER);  
          
        buffer.append(" public void deleteData(Long pk){").append(ENTER);  
        buffer.append(    objectName+"Dao.deleteData(pk);").append(ENTER);  
        buffer.append("}");  
          
          
        buffer.append(ENTER);  
        buffer.append(ENTER);  
        buffer.append("}");  
        buffer.append(ENTER);  
        FileUtils.save("output-code/" + ROOT_PACKAGE.replaceAll("\\.", "/") + "/service/impl/" + className + "ServiceImpl.java", buffer.toString());  
      
    }  
      
      
    /*** 
     * �������Ʋ���Controller 
     * @param table 
     */  
    public void createControllerClass(String table){  
        //����  
        String className = getClassName(getTableConstantName(table));  
        //ͨ�� org.apache.commons.lang3.StringUtils��uncapitalize������������һ����ĸת����Сд  
        String objectName = StringUtils.uncapitalize(className);  
          
        //ͨ�� org.apache.commons.lang3.StringUtils��lowerCase������������������ת����СдȻ��Ϊspringmvc��·������jsp����.  
        String BASE_PATH="modules/"+StringUtils.lowerCase(className)+"/";//modules+ģ����  
          
        StringBuilder buffer = new StringBuilder();  
        /*******�������������Ҫ����*********/  
        buffer.append("import java.util.List;").append(ENTER);  
        buffer.append("import javax.servlet.http.HttpServletRequest;").append(ENTER);  
        buffer.append("import javax.servlet.http.HttpServletResponse;").append(ENTER);  
        buffer.append("import org.springframework.beans.factory.annotation.Autowired;").append(ENTER);  
        buffer.append("import org.springframework.stereotype.Controller;").append(ENTER);  
        buffer.append("import org.springframework.web.bind.annotation.RequestMapping;").append(ENTER);  
        buffer.append("import com.flong.commons.persistence.bean.SimplePage;").append(ENTER);  
        buffer.append("import com.flong.commons.web.BaseController;").append(ENTER);  
        buffer.append("import com.flong.modules.pojo."+className+";").append(ENTER);  
        buffer.append("import com.flong.modules.service."+className+"Service;").append(ENTER);  
           
        buffer.append(ENTER);  
        buffer.append(ENTER);  
        buffer.append("/**\n * @Created��" + NOW_DATE + "\n * @Author " + AUTHOR + "\n");  
        buffer.append(" * @Version:").append(Version).append(ENTER);  
        buffer.append(" * @Description:").append(className).append(ENTER);  
        buffer.append(" * @Email:").append(myEmail).append("\n*/");  
        buffer.append(ENTER);  
        buffer.append(ENTER);  
        buffer.append("@Controller").append(ENTER);  
        buffer.append("@RequestMapping(\""+StringUtils.lowerCase(className)+"\")");  
        buffer.append(ENTER);  
        buffer.append("public class " + className + "Controller extends BaseController {");  
        buffer.append(ENTER);  
        buffer.append(ENTER);  
        buffer.append(" @Autowired "+className+"Service " +className+"Service");//ע��Service��Ľӿ�Name  
        buffer.append(ENTER);  
          
        //����һ��Ĭ�ϵĲ�ѯ..  
        buffer.append(ENTER);  
        buffer.append("   @RequestMapping(value=\"list\")").append(ENTER);  
        buffer.append("   public String list("+className+" "+objectName+",SimplePage simplePage ,HttpServletRequest request ,HttpServletResponse response){");  
        buffer.append(ENTER);  
        buffer.append("         List<"+className+"> list = "+className+"Service.list(simplePage, "+objectName+");");  
        buffer.append(ENTER);  
        buffer.append("      request.setAttribute(\""+objectName+"\", object);");  
        buffer.append(ENTER);  
        buffer.append("      request.setAttribute(\"page\", simplePage);");  
        buffer.append(ENTER);  
        buffer.append("      request.setAttribute(\"list\", list);");  
        buffer.append(ENTER);  
        buffer.append("      return \""+BASE_PATH+"list\";");  
        buffer.append(ENTER);  
        buffer.append("   }");  
          
        buffer.append(ENTER);  
        buffer.append("}");  
        buffer.append(ENTER);  
        FileUtils.save("output-code/" + ROOT_PACKAGE.replaceAll("\\.", "/") + "/controller/" + className + "Controller.java", buffer.toString());  
      
          
    }  
      
      
    /*** 
     * ����JSPҳ��. 
     * ��bootstrap3.xΪ��. 
     * @param table 
     */  
    public void createJspView(String table)throws Exception{  
  
        String tableConstantName = getTableConstantName(table);  
          
        String className = getClassName(tableConstantName);//��ȡ����  
        //ͨ�� org.apache.commons.lang3.StringUtils��uncapitalize������������һ����ĸת����Сд  
        String objectName = StringUtils.uncapitalize(className);  
          
        StringBuilder buffer = new StringBuilder();  
           
        buffer.append(" <%@ page language=\"java\" contentType=\"text/html; charset=UTF-8\"  pageEncoding=\"UTF-8\"%>").append(ENTER);  
        //����ͱ�עһ�£����taglib.jsp�ļ���JSTL��EL���ʽ��Spring ��ǩ���Զ����ǩ���ȵ��ļ���  
        buffer.append("  <%@ include file=\"/WEB-INF/views/include/taglib.jsp\"%>").append(ENTER);  
        buffer.append(" <!DOCTYPE htm>").append(ENTER);  
        buffer.append(" <html>").append(ENTER);  
        buffer.append(" <head>").append(ENTER);  
        //���һ������������ļ�������ҾͲ�һһ��ע  
        buffer.append("  <%@ include file=\"/WEB-INF/views/include/meta.jsp\"%>").append(ENTER);  
        buffer.append("  <%@ include file=\"/WEB-INF/views/include/include.jsp\"%>").append(ENTER);  
        buffer.append(" <title></title>").append(ENTER);  
        /**=======================���style===Begin====================**/  
        buffer.append(" <style>").append(ENTER);  
        buffer.append("     .breadcrumb{").append(ENTER);  
        buffer.append("         background-color: #fff;").append(ENTER);  
        buffer.append("     }").append(ENTER);  
        buffer.append("     .form-search{").append(ENTER);  
        buffer.append("         background-color: #fff;").append(ENTER);  
        buffer.append(" }").append(ENTER);  
        buffer.append(" .form-search1{").append(ENTER);  
        buffer.append("         padding: 8px 15px;").append(ENTER);  
        buffer.append("         background-color: #f5f5f5;").append(ENTER);  
        buffer.append("     }").append(ENTER);  
        buffer.append(" </style>").append(ENTER);  
        buffer.append(" </head>").append(ENTER);  
        /**=======================���style===End====================**/  
          
        buffer.append("<body>").append(ENTER);  
        buffer.append("<ul class=\"nav nav-tabs\">").append(ENTER);  
        buffer.append( "<li class=\"active\"><a href=\"${basePath}"+StringUtils.lowerCase(className)+"/list\">"+className+"�б�</a></li>").append(ENTER);  
        buffer.append("</ul>").append(ENTER);  
        buffer.append( " <form:form id=\"searchForm\" modelAttribute=\""+className+"\" action=\"${basePath}"+StringUtils.lowerCase(className)+"/list\" method=\"post\" class=\"breadcrumb form-search form-inline\">").append(ENTER);  
        buffer.append("  <div style=\"margin-bottom: 20px;\" class=\"form-search1\">").append(ENTER);  
          
        //��������ж����ݿ���ֶε�����������Ū����������ѯ.  
           
        for (Map<String, Object> col : getCols(table)) {  
               
            //�ж���������ݿ����ֶ���DateTime���͵ľ���ֵMy97DatePicker�����,������ʹ��.  
            if(Class.forName(col.get(CLASS).toString()).isAssignableFrom(Date.class) || Class.forName(col.get(CLASS).toString()) == Timestamp.class) {  
                buffer.append("<input id=\""+col.get(NAME).toString()+"\" name=\""+col.get(NAME).toString()+"\" type=\"text\" readonly=\"readonly\" maxlength=\"20\" class=\"Wdate\"").append(ENTER);  
                //����������$��Ϊ�˲�ѯ��ʱ����ֵ.  
                buffer.append(" value=\"<fmt:formatDate value=\"${"+className+"."+col.get(NAME).toString()+"}\" pattern=\"yyyy-MM-dd HH:mm:ss\"/>\"").append(ENTER);  
                buffer.append(" onclick=\"WdatePicker({dateFmt:��yyyy-MM-dd HH:mm:ss��,isShowClear:false});\"/>").append(ENTER);  
            } else if(getClassName(col.get(NAME).toString()).equals(Class.forName(col.get(CLASS).toString()).getSimpleName())) {  
                //form:input��spring�ܹ���input��ǩpath����Ҫ����ʵ����Ҫ�е�����.����ᱨ��.placeholder��html5�е�ռλ��������,  
                //htmlEscapeҲ��spring��������.�����jar����,��Ϊ�������������maven��ģ����п�����ʱ��,������ʱ��������·��.�����һ���jar,�����������spring����3.x  
                //C:\Users\liangjilong\.m2\repository\org\springframework\org.springframework.web.servlet\3.1.1.RELEASE\org.springframework.web.servlet-3.1.1.RELEASE.jar  
                //org.springframework.web.servlet-3.1.1.RELEASE.jar����ļ�������һ��spring-from.tld�ļ��������ҵ�path,htmlEscape������.  
                buffer.append("   <label>"+col.get(NAME).toString()+" ��</label><form:input path=\""+col.get(NAME).toString()+"\" htmlEscape=\"false\" maxlength=\"50\" class=\"input-medium form-control\" placeholder=\""+col.get(NAME).toString()+"\"/>").append(ENTER);  
            }else{  
                buffer.append("   <label>"+col.get(NAME).toString()+" ��</label><form:input path=\""+col.get(NAME).toString()+"\" htmlEscape=\"false\" maxlength=\"50\" class=\"input-medium form-control\" placeholder=\""+col.get(NAME).toString()+"\"/>").append(ENTER);  
            }  
            buffer.append(ENTER);  
        }  
        //btn btn-info�����ʽ�ù�bootstrap���˶�֪�������.  
        buffer.append("    &nbsp;<input id=\"btnSubmit\" class=\"btn btn-info\" type=\"submit\" value=\"��ѯ\"/>").append(ENTER);  
        buffer.append("  </div>").append(ENTER);  
          
        buffer.append("<table id=\"contentTable\" class=\"table table-striped table-bordered table-hover\">").append(ENTER);  
        buffer.append("<thead>").append(ENTER);//thead��ǩEnd  
        buffer.append("<tr>").append(ENTER);//tr��ǩEnd  
        /*******�����б��th����*****/  
        for (Map<String, Object> col : getTableRemarks(table)) {  
             for (String k : col.keySet()){    
                String colName = col.get(k).toString();  
                buffer.append("<th>").append(colName).append("</th>");  
                buffer.append(ENTER);  
              }    
        }  
        buffer.append("<th>����</th> ");  
        buffer.append(ENTER);  
          
        buffer.append("</tr>").append(ENTER);  
        buffer.append("</thead>").append(ENTER);  
          
        buffer.append("<tbody>").append(ENTER);  
          
        /*******�����б��td����*****/  
        buffer.append("   <c:forEach items=\"${list}\" var=\""+objectName+"\" varStatus=\"row\">").append(ENTER);  
        buffer.append("     <tr>").append(ENTER);  
        buffer.append("     <td>${row.index+1 }</td>").append(ENTER);  
          
        for (Map<String, Object> col : getCols(table)) {  
            buffer.append("         <td>");  
            if(Class.forName(col.get(CLASS).toString()).isAssignableFrom(Date.class) || Class.forName(col.get(CLASS).toString()) == Timestamp.class) {  
                //�����Date���;�ת����EL���ʽ��ʽ��fmt:formatDate  
                buffer.append("<fmt:formatDate value=\"${"+objectName+"."+col.get(NAME).toString()+"}\"  type=\"date\" dateStyle=\"long\"/>");  
            } else if(getClassName(col.get(NAME).toString()).equals(Class.forName(col.get(CLASS).toString()).getSimpleName())) {  
                buffer.append(" ${"+objectName+"."+col.get(NAME).toString()+"}" );  
            }else{  
                buffer.append(" ${"+objectName+"."+col.get(NAME).toString()+"}" );  
            }  
            buffer.append("</td>");  
            buffer.append(ENTER);  
        }  
          
        buffer.append("    </tr>").append(ENTER);  
        buffer.append("   </c:forEach>").append(ENTER);  
        buffer.append("</tbody>").append(ENTER);//tbody��ǩ����.  
          
        buffer.append("</table>").append(ENTER);  
        //�����pagination.jsp�Ƿ�ҳ�ļ�.  
        buffer.append("<%@ include file=\"/WEB-INF/views/include/pagination.jsp\"%>").append(ENTER);  
        buffer.append("</form:form>").append(ENTER);//form:form��ǩ����.  
        buffer.append("</body>").append(ENTER);//body��ǩ����.  
        buffer.append("</html>").append(ENTER);//html��ǩ����.  
        buffer.append(ENTER);  
        buffer.append(ENTER);  
        FileUtils.save("output-code/" + ROOT_PACKAGE.replaceAll("\\.", "/") + "/jsp/" + className + ".jsp", buffer.toString());  
          
    }  
      
      
    /*** 
     * ��������ඨ�峣�� 
     * @param tables 
     */  
    public void createTableClass(List<String> tables) {  
        StringBuilder buffer = new StringBuilder();  
        buffer.append("package " + ROOT_PACKAGE + ".domain;");  
        buffer.append(ENTER);  
        buffer.append(ENTER);  
        buffer.append("/**\n * @Created��" + NOW_DATE + "\n * @Author " + AUTHOR + "\n");  
        buffer.append(" * @Version:").append(Version).append(ENTER);  
        buffer.append(" * @Email:").append(myEmail).append("\n*/");  
        buffer.append(ENTER);  
        buffer.append("public interface Table {");  
        buffer.append(ENTER);  
        for (String table : tables) {  
            buffer.append(TAB);  
            buffer.append("String " + getTableConstantName(table) + " = \"" + table.toUpperCase() + "\";");  
            buffer.append(ENTER);  
        }  
        buffer.append(ENTER);  
        buffer.append("}");  
        buffer.append(ENTER);  
        FileUtils.save("output-code/" + ROOT_PACKAGE.replaceAll("\\.", "/") + "/domain/Table.java", buffer.toString());  
    }  
  
    /*** 
     * ��ȡ���ݿ���� 
     * @return 
     * @throws Exception 
     */  
    public List<String> getTables() throws Exception {  
        List<Object> params = new ArrayList<Object>();  
        //System.out.println("==========="+DB_NAME);  
        //params.add(DB_NAME);  
        String dbname=DB_NAME;  
        params.add(dbname);  
          
        ResultSet rs = DBHelperUtils.query("select table_name from information_schema.tables where table_schema = ? order by table_name", params);  
        List<String> tables = new ArrayList<String>();  
        while (rs.next()) {  
            tables.add(rs.getString(1));          
        }  
        return tables;  
    }  
      
    /*** 
     * ���� ���� => ˵�� 
     * TABLE_CAT String => �� catalog 
     * TABLE_SCHEM String => �� schema 
     * TABLE_NAME String => ���� 
     * TABLE_TYPE String => ������ 
     * REMARKS String => ��ע�� 
     * ��ȡ����� 
     * @param table 
     * @return 
     * @throws Exception 
     */  
    private List<Map<String, Object>> getCols(String table) throws Exception {  
         List<Map<String, Object>> cols = new ArrayList<Map<String,Object>>();  
         ResultSetMetaData md = DBHelperUtils.query("select * from " + table + " where 1 = 2", null).getMetaData();  
           
         for (int i = 1; i <= md.getColumnCount(); i++) {  
             Map<String, Object> col = new HashMap<String, Object>();  
             cols.add(col);  
             col.put(NAME, md.getColumnName(i));  
             col.put(CLASS, md.getColumnClassName(i));  
             col.put(SIZE, md.getColumnDisplaySize(i));  
             col.put(REMARKS, md.getColumnName(i));  
        /*  System.out.println("1"+ md.getCatalogName(i)); 
            System.out.println("2"+ md.getColumnClassName(i)); 
            System.out.println("3"+ md.getColumnDisplaySize(i)); 
            System.out.println("4"+ md.getColumnType(i)); 
            System.out.println("5"+ md.getSchemaName(i)); 
            System.out.println("6"+ md.getPrecision(i)); 
            System.out.println("7"+ md.getScale(i));*/  
               
             String _type = null;  
             String type = md.getColumnTypeName(i);  
             if(type.equals("INT")) {  
                 _type = "INTEGER";  
             } else if(type.equals("DATETIME")) {  
                 _type = "TIMESTAMP";  
             } else {  
                 _type = type;  
             }  
             col.put(TYPE, _type);  
        }  
        return cols;  
    }  
   
      
    /** 
     * ��ȡ���б� 
     * @param conn 
     * @throws SQLException 
     */  
    public static   List<Map<String, Object>> getAllTable() throws SQLException {  
        /** 
         * ����һ��Lis 
         */  
        List<Map<String, Object>> cols = new ArrayList<Map<String,Object>>();  
        DatabaseMetaData metaData = DBHelperUtils.getInstance().getDatabaseMetaData();  
  
        //����ǻ�ȡ���б�.  
        ResultSet rs = metaData.getTables(null, "%", "%", new String[] {"TABLE"});  
  
        while (rs.next()) {  
            String tableName = rs.getString("TABLE_NAME");////����ǻ�ȡ����  
  
            if(tableName!=null){  
                Map<String, Object> col = new HashMap<String, Object>();  
                // rs =getConnection.getMetaData().getColumns(null, getXMLConfig.getSchema(),tableName.toUpperCase(), "%");  
                //�������ݿⲻ��Ҫ��������ģ�ֱ�Ӵ�null�������oracle��db2��ô��  
                  
                ResultSet rs1 = metaData.getColumns(null, "%", tableName, "%");  
                  
                while(rs1.next()){  
                    String COLUMN_NAME = rs1.getString("COLUMN_NAME");  
                    String REMARKS = rs1.getString("REMARKS");  
                    //���жϱ�ע�Ƿ�Ϊ��,��Ϊ�վ�ȡ����ֶε�ע��˵��������Ļ���ȥ�ֶ�����  
                    if(REMARKS==null||REMARKS==""){  
                        col.put(COLUMN_NAME, COLUMN_NAME);  
                    }else{  
                        col.put(REMARKS, REMARKS);  
                    }  
                    cols.add(col);  
                }  
            }  
        }  
        return cols;  
    }  
      
    /*** 
     * ��ȡ�еı�ע 
     * @param table 
     * @return 
     * @throws SQLException 
     */  
    public static List<Map<String, Object>> getTableRemarks(String table) throws SQLException {  
          
        List<Map<String, Object>> cols = new ArrayList<Map<String,Object>>();  
           
        Connection conn=DBHelperUtils.getInstance().getConnection();  
        DatabaseMetaData metaData = conn.getMetaData();  
  
        ResultSet rs = metaData.getTables(null, "%", "%", new String[] {"TABLE"});  
  
        while (rs.next()) {  
              
            String tableName = rs.getString("TABLE_NAME");  
            //�������ı����Ͳ�ѯ�����ı������Աȣ������Ǻ��Դ�Сд  
            if(tableName!=null){  
                if(table.equalsIgnoreCase(tableName)){  
                    Map<String, Object> col = new HashMap<String, Object>();  
                    //Map<String, Object> col = new HashTable<String, Object>();  
                    ResultSet rs1 = metaData.getColumns(null, "%", tableName, "%");  
                   while(rs1.next()){  
                        String COLUMN_NAME = rs1.getString("COLUMN_NAME");  
                        String REMARKS = rs1.getString("REMARKS");  
                          
                        //���жϱ�ע�Ƿ�Ϊ��,��Ϊ�վ�ȡ����ֶε�ע��˵��������Ļ���ȥ�ֶ�����  
                         
                        if(REMARKS==null||REMARKS==""){  
                            col.put(COLUMN_NAME, COLUMN_NAME);  
                        }else{  
                            col.put(REMARKS, REMARKS);  
                        }  
                        //ȥ���ظ�������  
                        col = removeRepeatData();  
                        cols.add(col);  
                   }  
                   break;  
                }  
            }  
        }  
        return cols;  
    }  
       
    /** 
     * ��ȡ�������. 
     * @param tableName 
     */  
    public static String getTablePrimaryKeys(String tableName)throws Exception{  
        DatabaseMetaData metaData = DBHelperUtils.getInstance().getDatabaseMetaData();  
        ResultSet pkRSet = metaData.getPrimaryKeys(null, null, tableName);   
          
        String primaryKey = "";  
        if(pkRSet.next() ) {   
            //������е����ƻ�ȡ����  
              primaryKey = pkRSet.getString("PK_NAME");//PK_NAME/COLUMN_NAME  
              primaryKey=(primaryKey==null?"":primaryKey);   
                
              System.out.println(primaryKey);  
        }   
        return primaryKey;  
    }  
      
    /** 
     * ��ȡ������������������������ 
     * @param tableName 
     */  
    public static String[] getTablePrimaryKeyForeignKey(String tableName)throws Exception{  
        DatabaseMetaData metaData = DBHelperUtils.getInstance().getDatabaseMetaData();  
        ResultSet fkSet = metaData.getPrimaryKeys(null, null, tableName);   
        String pkColumnName="",fkColumnName="",pkTablenName="";  
        String [] paramsKey= new String[3];  
          
        while(fkSet.next()){  
             pkColumnName = fkSet.getString("PK_NAME");//���������鵽���п�����PKCOLUMN_NAME  
             fkColumnName = fkSet.getString("FK_NAME");//������鵽���п�����PKCOLUMN_NAME  
             pkTablenName = fkSet.getString("PKTABLE_NAME");//��������  
             //System.out.println(pkColumnName+fkColumnName+pkTablenName);  
             pkColumnName=(pkColumnName==null?"":pkColumnName);  
             fkColumnName=(fkColumnName==null?"":fkColumnName);  
             pkTablenName=(pkTablenName==null?"":pkTablenName);  
             paramsKey[0]=fkColumnName;  
             paramsKey[1]=fkColumnName;  
             paramsKey[2]=pkTablenName;  
        }    
           
        return paramsKey;  
    }  
  
    /*** 
     * ȥ���ظ������� 
     * @return 
     */  
    private static Map<String, Object> removeRepeatData() {  
        Map<String, Object> col = new HashMap<String, Object>();  
        Set<String> keySet = col.keySet();  
        for (String str : keySet) {  
            col.put(str, str);  
        }  
        return col;  
    }  
      
      
    /*** 
     * ��ȡ��ĳ�������һ���������ݿ⽨���ʱ��д��ע��.. 
     * @param table 
     * @return 
     */  
    private String getTableConstantName(String table) {  
        String tableConstantName = table.toUpperCase();  
        for (String item : IGNORE_TABLE_PREFIX) {  
            tableConstantName = tableConstantName.replaceAll("^" + item.toUpperCase(), "");  
        }  
        return tableConstantName;  
    }  
  
    /*** 
     * ��ȡ����� 
     * @param name 
     * @return 
     */  
    private String getClassName(String name) {  
        String[] names = name.split("_");  
        StringBuilder sb = new StringBuilder();  
        for (String n : names) {  
            if(n.length() == 0) {  
                sb.append("_");  
            } else {  
                sb.append(n.substring(0, 1).toUpperCase());  
                if(n.length() > 1) {  
                    sb.append(n.substring(1).toLowerCase());  
                }  
            }  
        }  
        return sb.toString();  
    }  
  
    /** 
     * ��ȡ�ֶ��� 
     * @param name 
     * @return 
     */  
    private String getFieldName(String name) {  
        String _name = getClassName(name);  
        return _name.substring(0, 1).toLowerCase() + _name.substring(1);  
    }  
  
  
    /** 
     * ת���ɷ���Map 
     * @param limit 
     * @param rs 
     * @return 
     * @throws SQLException 
     */  
    public static List<Map> toListMap(int limit, ResultSet rs)throws SQLException {  
        ResultSetMetaData rsmd = rs.getMetaData();  
        int count = 0;  
        List list = new ArrayList();  
        while (rs.next()) {  
            Map row = new HashMap();  
            for (int i = 1; i <= rsmd.getColumnCount(); i++) {  
                row.put(rsmd.getColumnName(i), rs.getObject(i));  
            }  
            list.add(row);  
            count++;  
            if (count >= limit) {  
                break;  
            }  
        }  
        return list;  
     }  
      
    /*** 
     * ��ȡ��ѯlist 
     * @param conn 
     * @param sql 
     * @param limit 
     * @return 
     * @throws SQLException 
     */  
    public static List<Map> queryForList(Connection conn, String sql, int limit)throws SQLException {  
        PreparedStatement ps = conn.prepareStatement(sql.trim());  
        ps.setMaxRows(limit);  
        ps.setFetchDirection(1000);  
        ResultSet rs = ps.executeQuery();  
        return toListMap(limit, rs);  
    }  
    /*** 
     * ��ȡ��ѯlist 
     * @param conn 
     * @param sql 
     * @param limit 
     * @return 
     * @throws SQLException 
     */  
    public static List<Map> queryForList(String sql, int limit) throws SQLException {  
        Connection conn = DBHelperUtils.getConnection();  
        return queryForList(conn, sql, limit);  
    }  
      
      
    /*** 
     * ��������Entity,Dao,Service,Controller,JSP ���� 
     * @throws Exception 
     */  
    public void createAllCodeGenerator()throws Exception{  
        List<String> tables = getTables();  
        for (String table : tables) {  
            createEntityClass(table);//this is method create Entity  
            createDaoClass(table);//this is method create  Dao Interface  
            createDaoImplClass(table);//this is method create Dao implement  
            createServiceClass(table);//this is method create Service Interface  
            createServiceImplClass(table);//this is method create Service implement  
            createControllerClass(table);//this is method create Controller  
            createJspView(table);//this is method JspView  
        }  
         createTableClass(tables);  
          
    }  
      
      
    public static void main(String[] args)throws Exception {  
        String sql="select * from SYS_MENU ";  
        //List<Map> queryForList = queryForList(sql, 1000);  
        /*for(Map m:queryForList){ 
            System.out.println("======"+m); 
        }*/  
          
        String tableName = "SYS_MENU";//����  
       
      /*    List<Map<String, Object>> tableRemarks = getTableRemarks(tableName); 
        int i=0; 
        for (Map<String, Object> col : getTableRemarks(tableName)) { 
            Set<String> keySet = col.keySet(); 
            for (Object str : keySet) { 
                //System.out.println(str); 
            } 
        } 
        */  
        //getTablePrimaryKeys("test");  
          
        //new CodeGenerator().createJspView("sup_email");  
        new CodeGenerator().createEntityClass("test");  
        //new CodeGenerator().getTablePrimaryKeyForeignKey("test");  
        //String myId="My_id";  
         //boolean endsWith = myId.toString().toUpperCase().endsWith("_ID");  
        //if(col.get(NAME).toString().equalsIgnoreCase("ID") || col.get(NAME).toString().toUpperCase().endsWith("_ID"))  
        //if(endsWith){  
            //System.out.println(11111);  
        //}  
          
        //createAllCodeGenerator();  
          
    }  
  
}  