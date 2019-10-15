package com.ximouzhao.myBatisGenerator;

import com.mysql.cj.util.StringUtils;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.InnerClass;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.internal.DefaultCommentGenerator;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;

/**
 * mybatis generator生成注释插件
 * <p>
 * Created by huhaichao on 2017/5/15.
 */
public class MyCommentGenerator extends DefaultCommentGenerator {
    private Properties properties;
    private Properties systemPro;
    private boolean suppressDate;
    private boolean suppressAllComments;
    private String currentDateStr;

    public MyCommentGenerator() {
        super();
        properties = new Properties();
        systemPro = System.getProperties();
        suppressDate = false;
        suppressAllComments = false;
        currentDateStr = (new SimpleDateFormat("yyyy-MM-dd")).format(new Date());
    }


    @Override
    public void addFieldComment(Field field, IntrospectedTable introspectedTable,
                                IntrospectedColumn introspectedColumn) {
        if (suppressAllComments) {
            return;
        }
        StringBuilder sb = new StringBuilder();
        field.addJavaDocLine("/**");
        sb.append(" * ");
        sb.append(removeLineBreaks(introspectedColumn.getRemarks()));
        sb.append("\n\t * @Column(name = \"" + removeLineBreaks(introspectedColumn.getActualColumnName()) + "\")");

        List<IntrospectedColumn> primaryKeyColumns = introspectedTable.getPrimaryKeyColumns();
        for (IntrospectedColumn col : primaryKeyColumns) {
            if (col.getActualColumnName().equals(introspectedColumn.getActualColumnName())) {
                sb.append("\n\t * @Id");
            }
        }
        field.addJavaDocLine(sb.toString());
        field.addJavaDocLine(" */");
        /*除开id的其他字段上生成@Column注解*/
        //field.addAnnotation("@Column(name = \"" + introspectedColumn.getActualColumnName() + "\")");
        if(!introspectedColumn.isNullable()){
            if(introspectedColumn.getJdbcType()==12){
                field.addAnnotation("@NotBlank(message = \"" + introspectedColumn.getRemarks() + "不能为空\")");
            }else{
                field.addAnnotation("@NotNull(message = \"" + introspectedColumn.getRemarks() + "不能为空\")");
            }

        }
    }
    public static String removeLineBreaks(String inStr){
        return inStr.replace("\n", " ");
    }
    @Override
    public void addModelClassComment(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
//        topLevelClass.addImportedType("javax.persistence.Column");
//        topLevelClass.addImportedType("javax.persistence.Id");
//        topLevelClass.addImportedType("javax.persistence.Table");
//        topLevelClass.addImportedType("org.apache.ibatis.type.Alias");

        if (suppressAllComments) {
            return;
        }

        StringBuilder sb = new StringBuilder();

        topLevelClass.addJavaDocLine("/**");
        sb.append(" * ");
        if(StringUtils.isNullOrEmpty(introspectedTable.getRemarks())){
            sb.append(introspectedTable.getFullyQualifiedTable());
        }else{
            sb.append(introspectedTable.getRemarks());
        }
        topLevelClass.addJavaDocLine(sb.toString());
        sb.setLength(0);
        sb.append(" * @author ");
        sb.append(systemPro.getProperty("user.name"));
        sb.append(" ");
        sb.append(currentDateStr);
        sb.append("\n * @Table(name = \"" + introspectedTable.getFullyQualifiedTable() + "\")");
        topLevelClass.addJavaDocLine(sb.toString());
        // addJavadocTag(innerClass, markAsDoNotDelete);

        topLevelClass.addJavaDocLine(" */");
        //topLevelClass.addAnnotation("@Table(name = \"" + introspectedTable.getFullyQualifiedTable() + "\")");
    }

    @Override
    public void addFieldComment(Field field, IntrospectedTable introspectedTable) {

    }

    @Override
    public void addGeneralMethodComment(Method method, IntrospectedTable introspectedTable) {

    }

    @Override
    public void addGetterComment(Method method, IntrospectedTable introspectedTable,
                                 IntrospectedColumn introspectedColumn) {

    }

    @Override
    public void addSetterComment(Method method, IntrospectedTable introspectedTable,
                                 IntrospectedColumn introspectedColumn) {

    }

    @Override
    public void addClassComment(InnerClass innerClass, IntrospectedTable introspectedTable, boolean markAsDoNotDelete) {

    }

    @Override
    public void addClassComment(InnerClass innerClass, IntrospectedTable introspectedTable) {
    }


}