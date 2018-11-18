package com.lvyj001.doclet;

import com.sun.javadoc.AnnotationDesc;
import com.sun.javadoc.AnnotationTypeDoc;
import com.sun.javadoc.ClassDoc;
import com.sun.javadoc.RootDoc;

import java.io.*;
import java.net.URL;

public class MyDoclet {

    public static boolean start(RootDoc root) {
        ClassDoc[] classes = root.classes();
        for (int i = 0; i < classes.length; ++i) {
            System.out.println(classes[i]);
            ClassDoc doc = classes[i];
            // 类注解
            AnnotationDesc[] anDescs = doc.annotations();
            for (AnnotationDesc anDesc : anDescs) {
                AnnotationTypeDoc atd = anDesc.annotationType();
                System.out.println(atd);
                AnnotationDesc.ElementValuePair[] pairs = anDesc.elementValues();
                for (AnnotationDesc.ElementValuePair pair : pairs){
                    System.out.println(pair.element() + " -> " + pair.value());
                }
            }
            // 类注释
            // 成员变量注解
            // 成员变量注释
            // 成员方法注解
            // 成员方法注释
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        File fileList = initFileList();
        // MyDoclet.class.getName()  -->  com.lvyj001.doclet.MyDoclet
        com.sun.tools.javadoc.Main.execute(new String[]{"-doclet", MyDoclet.class.getName(), "-encoding", "utf-8", "@" + fileList.getPath()});
    }

    public static File initFileList() throws IOException {
        // /F:/intelij_hadoop/docletcomment/target/classes/
        String path = MyDoclet.class.getClassLoader().getResource(".").getPath();
        File dir = new File(path + "files/");
        File fileList = new File(path, "list");
        if (fileList.exists()) {
            return fileList;
        }

        OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(fileList));
        File[] files = dir.listFiles();
        for (File file : files) {
            osw.write(file.getAbsolutePath() + "\n");
        }
        osw.flush();
        osw.close();
        return fileList;
    }
}
