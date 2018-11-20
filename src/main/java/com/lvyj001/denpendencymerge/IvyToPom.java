package com.lvyj001.denpendencymerge;

import com.lvyj001.denpendencymerge.metadata.MetaDependency;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 有一些项目使用ant进行依赖包下载，需要把ivy中的依赖填写到本工程pom文件中
 * 1.获取本工程pom文件中的依赖项集合 A
 * 2.获取ivy中的依赖项集合 B
 * 3.进行对比
 * 4.查漏补缺 A = A + {B - A}
 * 5.从重新生成本工程的pom文件
 */
public class IvyToPom {

    public List<MetaDependency> getIvyDependency(String projectIvy) throws ParserConfigurationException, IOException, SAXException {
        List<MetaDependency> metaList = new ArrayList<>();
        // 获取ivy文件
        String path = IvyToPom.class.getClassLoader().getResource("ivys/" + projectIvy).getPath();
        File ivy = new File(path);
        Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(ivy);
        Element root = doc.getDocumentElement();

        NodeList children = root.getChildNodes();
        NodeList denpendencyList = null;
        for (int i = 0; i < children.getLength(); i++) {
            Node node = children.item(i);
            if (node instanceof Element) {
                Element element = (Element) node;
                if ("dependencies".equals(element.getNodeName())) {
                    denpendencyList = element.getChildNodes();
                    break;
                }
            }
        }
        if(denpendencyList == null){
            return null;
        }
        for (int i = 0; i < denpendencyList.getLength(); i++) {
            Node node = denpendencyList.item(i);
            if(node instanceof Element){
                Element element = (Element)node;
                MetaDependency metaDependency = new MetaDependency();
                metaDependency.setValuesByIvy(element);
                metaList.add(metaDependency);
            }
        }
        return metaList;
    }


    public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException {
        IvyToPom itp = new IvyToPom();
        List<MetaDependency> metaList = itp.getIvyDependency("aml-ivy.xml");
        System.out.println(metaList.size());
    }
}
