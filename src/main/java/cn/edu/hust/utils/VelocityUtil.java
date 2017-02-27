package cn.edu.hust.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
/**
 * 用于解析vm模版（world文档保存为xml格式，然后把里面的值改为velocity变量）
 */
public class VelocityUtil {

    private static VelocityEngine ve = new VelocityEngine();

    /**
     * 创建 word 文档
     * @throws IOException
     */
    public static void createDoc(String templateName, VelocityContext vc, String docFileName) throws FileNotFoundException {
        //解析模版
        Template template = ve.getTemplate(templateName, "UTF-8");
        PrintWriter writer = new PrintWriter(new File(docFileName));
        template.merge(vc, writer);

        writer.flush();
        writer.close();
    }


}
