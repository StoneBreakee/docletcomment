package com.lvyj001.doclet;

import com.sun.javadoc.*;

public class MyDoclet {

    public static boolean start(RootDoc root) {
        ClassDoc[] classes = root.classes();
        for (int i = 0; i < classes.length; ++i) {
            System.out.println(classes[i]);
            ClassDoc doc = classes[i];
            // 类注解
//            AnnotationDesc[] anDescs = doc.annotations();
//            for (AnnotationDesc anDesc : anDescs) {
//                AnnotationTypeDoc atd = anDesc.annotationType();
//                System.out.println(atd);
//                AnnotationDesc.ElementValuePair[] pairs = anDesc.elementValues();
//                for (AnnotationDesc.ElementValuePair pair : pairs) {
//                    System.out.println(pair.element() + " -> " + pair.value());
//                }
//            }
            // 类注释
            // 成员变量注解
            // 成员变量注释
            // 成员方法注解
            // 成员方法注释
            MethodDoc[] mDocs = doc.methods();
            for (MethodDoc mDoc : mDocs) {
                System.out.println(mDoc.name());
                System.out.println(mDoc.getRawCommentText());
                System.out.println(mDoc.returnType());
                Parameter[] parameters = mDoc.parameters();
                for (Parameter parameter:parameters){
                    System.out.println(parameter.name());
                }
                AnnotationDesc[] anDescs = mDoc.annotations();
                for (AnnotationDesc anDesc : anDescs) {
                    AnnotationTypeDoc atd = anDesc.annotationType();
                    System.out.println(atd);
                    AnnotationDesc.ElementValuePair[] pairs = anDesc.elementValues();
                    for (AnnotationDesc.ElementValuePair pair : pairs) {
                        System.out.println(pair.element() + " -> " + pair.value());
                    }
                }
                System.out.println("--=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
            }
            // 构造方法注释
            // 构造方法注解
            // 递归
        }
        return true;
    }

}
