package template;

import freemarker.cache.TemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.RandomAccessFile;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Method;
import java.net.URL;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Generator
{
    private static final Logger log = LoggerFactory.getLogger(Generator.class);
    private static String appPath;
    private static String rootPath;
    private static String webRootPath;
    private static String packageName;
    private static String subSystem;
    private static String entityPath;
    private static String[][] tempArray;
    private static String[][] nameArray;
    private static String[][] validateArray;
    private static Map<String, String> validateMap;

    static
    {
        String temp = Generator.class.getResource("/").getFile();
        System.out.println("Class Path: " + temp);
        appPath = "F:\\backup\\Project\\ColorMeFun\\idea\\colormefun";//temp.substring(0, temp.indexOf("/web/WEB-INF/classes"));

        rootPath = appPath + "/src/com/colormefun";
        webRootPath = appPath + "/web";
        packageName = "com.colormefun";
        subSystem = "/admin";

        entityPath = rootPath + "/entity";

        tempArray = new String[][] {
                { "Conversion.ftl",
                        "${rootPath}/entity/${pojo.ClassName}-conversion.properties" },
                { "DAO.ftl", "${rootPath}/dao/${pojo.ClassName}DAO.java" },
                { "DAOImpl.ftl", "${rootPath}/dao/${pojo.ClassName}DAOImpl.java" },
                { "Service.ftl",
                        "${rootPath}/service/${pojo.ClassName}Service.java" },
                { "ServiceImpl.ftl",
                        "${rootPath}/service/${pojo.ClassName}ServiceImpl.java" },
                { "Action.ftl", "${rootPath}/action/${pojo.ClassName}Action.java" } };

        nameArray = new String[][] { { "email", "Email" },
                { "image", "Image" }, { "phone", "Phone" }, { "item", "Items" },
                { "items", "Items" }, { "comment", "Comment" },
                { "text", "Comment" }, { "html", "Comment" }, { "url", "URL" },
                { "password", "Password" }, { "pwd", "Password" }, { "qq", "QQ" },
                { "cardid", "CardId" }, { "name", "Name" }, { "birth", "Birthday" } };

        validateArray = new String[][] { { "Email", "'email'" },
                { "Image", "'image'" }, { "Phone", "'phone'" },
                { "Comment", "'length[0,-1]'" }, { "URL", "'url'" },
                { "Password", "'length[6-12]'" },
                { "QQ", "'number','length[7-10]'" },
                { "CardId", "'number','length[15-18]'" },
                { "Name", "'length[2-100]'" }, { "Birthday", "'date'" },
                { "Date", "'date'" }, { "Required", "'required'" },
                { "ToOne", "" }, { "ToMany", "" }, { "Items", "" } };

        validateMap = new LinkedHashMap();

        for (String[] v : validateArray)
            validateMap.put(v[0], v[1]);
    }

    public static void main(String[] args)
    {
        try {
            generate();
            log.info("代码自动生成完成。");
        } catch (Throwable e) {
            log.debug("代码自动生成出现问题，因为：" + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void generate() throws Exception
    {
        Map contextMap = createContextMap();

        Configuration cfg = new Configuration();
        cfg.setDefaultEncoding("UTF-8");
        cfg.setDirectoryForTemplateLoading(
                new File(Generator.class
                        .getResource("/template").getFile()));
        cfg.setObjectWrapper(new DefaultObjectWrapper());

        File entityFiles = new File(entityPath);

        String[] fileNames = entityFiles.list();

        Map<String, Map> pojos = new HashMap<String, Map>();
        contextMap.put("pojos", pojos);
        String pojoClassName;
        for (String n : fileNames)
        {
            if (!n.endsWith(".java")) {
                continue;
            }
            pojoClassName = n
                    .substring(0, n.length() - ".java".length());

            analyzePojoClass(pojoClassName, contextMap);
        }

        for (Map pojo : pojos.values()) {
            contextMap.put("pojo", pojo);
            if (pojo.get("ClassName") == null) {
                continue;
            }
            System.out.println(pojo.get("ClassName"));
            String[][] arrayOfString = tempArray;
            //pojoClassName = arrayOfString.length;

            for (int i = 0; i < arrayOfString.length; i++) {
                String[] temp = arrayOfString[i];
                processTemplate(cfg, contextMap, temp);
            }
            System.out.println("--------------------------------");
        }
    }

    private static void processTemplate(Configuration cfg, Map contextMap, String[] temp)
            throws IOException, TemplateException
    {
        Template template = cfg.getTemplate(temp[0]);
        String outFileName = parseEl(temp[1], contextMap);

        if (outFileName.startsWith("to:")) {
            outFileName = outFileName.substring("to:".length());
            File outFile = new File(outFileName);

            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            Writer out = new BufferedWriter(
                    new OutputStreamWriter(bos, "UTF-8"));
            template.process(contextMap, out);
            out.close();

            StringWriter sw = new StringWriter();
            BufferedWriter bw = new BufferedWriter(sw);
            BufferedReader fr = new BufferedReader(new FileReader(outFile));
            char[] buffer = new char[(int)outFile.length()];
            int i = fr.read(buffer);
            bw.write(buffer, 0, i);
            bw.close();
            sw.close();

            RandomAccessFile raf = new RandomAccessFile(outFile, "rw");

            raf.skipBytes((int)outFile.length());

            if (sw.toString().contains(
                    parseEl("[${pojo.ClassName}]", contextMap))) {
                return;
            }

            raf.write(bos.toByteArray());

            raf.close();
        }
        else {
            File outFile = new File(outFileName);
            if (!outFile.getParentFile().exists()) {
                outFile.getParentFile().mkdirs();
            }

            if (outFile.exists()) {
                return;
            }

            Writer out = new BufferedWriter(
                    new OutputStreamWriter(new FileOutputStream(outFile), "UTF-8"));

            template.process(contextMap, out);
            out.close();
        }
    }

    private static String parseEl(String el, Map contextMap)
            throws IOException, TemplateException
    {
        Configuration cfg = new Configuration();
        cfg.setTemplateLoader(new StringTemplateLoader(el));
        cfg.setDefaultEncoding("UTF-8");

        Template template = cfg.getTemplate("");

        StringWriter writer = new StringWriter();
        template.process(contextMap, writer);
        return writer.toString();
    }

    private static void analyzePojoClass(String pojoClassName, Map contextMap) throws ClassNotFoundException
    {
        String pojoVarName = pojoClassName.substring(0, 1).toLowerCase() +
                pojoClassName.substring(1);

        Map pojos = (Map)contextMap.get("pojos");

        Map pojo = getPojo(contextMap, pojoClassName);

        pojo.put("ClassName", pojoClassName);
        pojo.put("VarName", pojoVarName);

        Class pojoClass = Class.forName(contextMap.get("entityPackage") + "." +
                pojoClassName);

        Method[] methods = pojoClass.getMethods();

        for (Method m : methods)
        {
            String methodName = m.getName();

            if (!methodName.startsWith("get"))
            {
                continue;
            }
            String fieldName = methodName.substring("get".length());
            fieldName = fieldName.substring(0, 1).toLowerCase() +
                    fieldName.substring(1);
            Column column = (Column)m.getAnnotation(Column.class);

            if (column != null)
            {
                Map fieldMap = new HashMap();
                pojo.put(fieldName, fieldMap);

                fieldMap.put("Name", fieldName);
                fieldMap.put("Length", Integer.valueOf(column.length()));
                fieldMap.put("Nullable", Boolean.valueOf(column.nullable()));
                fieldMap.put("Unique", Boolean.valueOf(column.unique()));
                String type = parseType(m.getReturnType(), column);
                fieldMap.put("Type", type);
                String nameType = parseType(type, fieldName);
                fieldMap.put("NameType", nameType);

                String fieldNameLower = fieldName.toLowerCase();
                if (((fieldNameLower.endsWith("name")) ||
                        (fieldNameLower.endsWith("title")) ||
                        (fieldNameLower
                                .endsWith("key"))) && (!pojo.containsKey("Name"))) {
                    pojo.put("Name", fieldName);
                }

                String validateType = (String)validateMap.get(nameType);
                if (!column.nullable()) {
                    validateType = validateType + "," + (String)validateMap.get("Required");
                }
                fieldMap.put("ValidateType", parseValidate(nameType));
            }

            if (m.getAnnotation(Id.class) != null) {
                pojo.put("Id", fieldName);
                pojo.put("IdClassName", m.getReturnType().getSimpleName());
            }

            if (m.getAnnotation(ManyToMany.class) != null)
            {
                ManyToMany mtm = (ManyToMany)m.getAnnotation(ManyToMany.class);

                Map fieldMap = new HashMap();
                pojo.put("#" + fieldName, fieldMap);

                fieldMap.put("Name", fieldName);
                fieldMap.put("Type", "ToMany");
                String nameType = parseType("ToMany", fieldName);
                fieldMap.put("NameType", nameType);

                String referenceClass = fieldName.substring(0, 1).toUpperCase() +
                        fieldName.substring(1, fieldName.length() - 1);

                if ((mtm.targetEntity() != null) && (!"void".equals(mtm.targetEntity().getSimpleName()))) {
                    referenceClass = mtm.targetEntity().getSimpleName();
                }

                fieldMap.put("ReferenceClass",
                        getPojo(contextMap, referenceClass));
                fieldMap.put("ValidateType", parseValidate(nameType));
            }

            if (m.getAnnotation(OneToMany.class) != null)
            {
                OneToMany otm = (OneToMany)m.getAnnotation(OneToMany.class);

                Map fieldMap = new HashMap();
                pojo.put("#" + fieldName, fieldMap);

                fieldMap.put("Name", fieldName);
                fieldMap.put("Type", "ToMany");
                String nameType = parseType("ToMany", fieldName);
                fieldMap.put("NameType", nameType);

                String referenceClass = fieldName.substring(0, 1).toUpperCase() +
                        fieldName.substring(1, fieldName.length() - 1);
                if ((otm.targetEntity() != null) && (!"void".equals(otm.targetEntity().getSimpleName())))
                {
                    referenceClass = otm.targetEntity().getSimpleName();
                }

                fieldMap.put("ReferenceClass",
                        getPojo(contextMap, referenceClass));
                fieldMap.put("ValidateType", parseValidate(nameType));
            }

            if ((m.getAnnotation(ManyToOne.class) == null) &&
                    (m.getAnnotation(OneToOne.class) == null))
                continue;
            Map fieldMap = new HashMap();
            pojo.put("#" + fieldName, fieldMap);

            fieldMap.put("Name", fieldName);
            fieldMap.put("Type", "ToOne");
            String nameType = parseType("ToOne", fieldName);
            fieldMap.put("NameType", nameType);
            fieldMap.put("ReferenceClass",
                    getPojo(contextMap, m.getReturnType().getSimpleName()));

            fieldMap.put("ValidateType", parseValidate(nameType));
        }

        if (pojo.get("Name") == null)
            pojo.put("Name", pojo.get("Id"));
    }

    private static Map getPojo(Map contextMap, String key)
    {
        Map pojo = (Map)((Map)contextMap.get("pojos")).get(key);
        if (pojo == null) {
            pojo = new HashMap();
            ((Map)contextMap.get("pojos")).put(key, pojo);
        }
        return pojo;
    }

    private static String parseValidate(String nameType) {
        String v = (String)validateMap.get(nameType);
        return v == null ? "" : v;
    }

    private static Map createContextMap()
    {
        String entityPackageName = packageName + ".entity";
        String daoPackageName = packageName + ".dao";
        String servicePackageName = packageName + ".service";
        String actionPackageName = packageName + ".action";

        ResourceBundle bundle = ResourceBundle.getBundle("config/Message");

        Map contextMap = new HashMap();

        contextMap.put("i18n", new I18nMethod(bundle));

        contextMap.put("appPath", appPath);
        contextMap.put("rootPath", rootPath);
        contextMap.put("webRootPath", webRootPath);

        contextMap.put("subSystem", subSystem);
        contextMap.put("entityPackage", entityPackageName);
        contextMap.put("daoPackage", daoPackageName);
        contextMap.put("servicePackage", servicePackageName);
        contextMap.put("actionPackage", actionPackageName);

        return contextMap;
    }

    private static String parseType(Class type, Column column)
    {
        if ((type == Integer.TYPE) || (type == Integer.class) || (type == Long.TYPE) ||
                (type == Long.class) || (type == Byte.TYPE) ||
                (type == Byte.class) || (type == Short.TYPE) ||
                (type == Short.class)) {
            return "Integer";
        }

        if ((type == Float.TYPE) || (type == Float.class) || (type == Double.TYPE) ||
                (type == Double.class)) {
            return "Float";
        }

        if ((type == Boolean.TYPE) || (type == Boolean.class)) {
            return "Boolean";
        }

        if (type == String.class) {
            if (column.length() > 100) {
                return "Comment";
            }
            return "String";
        }

        if ((type == java.util.Date.class) || (type == java.sql.Date.class)) {
            return "Date";
        }

        if (type == Timestamp.class) {
            return "Time";
        }

        return "Unknown";
    }

    private static String parseType(String type, String fieldName)
    {
        String name = fieldName.toLowerCase();

        for (String[] temp : nameArray) {
            if (name.endsWith(temp[0])) {
                return temp[1];
            }
        }

        return type;
    }

    private static class StringTemplateLoader
            implements TemplateLoader
    {
        private String template;

        public StringTemplateLoader(String template)
        {
            this.template = template;
            if (template == null)
                this.template = "";
        }

        public void closeTemplateSource(Object templateSource)
                throws IOException
        {
            ((StringReader)templateSource).close();
        }

        public Object findTemplateSource(String name) throws IOException {
            return new StringReader(this.template);
        }

        public long getLastModified(Object templateSource) {
            return 0L;
        }

        public Reader getReader(Object templateSource, String encoding) throws IOException
        {
            return (Reader)templateSource;
        }
    }
}