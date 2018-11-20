package com.lvyj001.denpendencymerge;

import com.lvyj001.denpendencymerge.metadata.MetaDependency;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 如果两个项目都是用pom，则查漏补缺
 */
public class PomToPom {

    // denpendencyList 用于保存依赖的列表
    // propertyList    用于依赖中版本的占位符的替换
    public List<MetaDependency> getPomDependency(String projectPom) throws ParserConfigurationException, IOException, SAXException {
        List<MetaDependency> metaList = new ArrayList<>();
        // 获取pom文件
        String path = IvyToPom.class.getClassLoader().getResource("poms/" + projectPom).getPath();
        File pom = new File(path);
        Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(pom);
        Element root = doc.getDocumentElement();

        NodeList children = root.getChildNodes();
        NodeList propertyList = getNodes(children, "properties");
        NodeList denpendencyList = getNodes(children, "dependencies");
        if (denpendencyList == null) {
            return null;
        }

        for (int i = 0; i < denpendencyList.getLength(); i++) {
            Node node = denpendencyList.item(i);
            if (node instanceof Element) {
                Element element = (Element) node;
                MetaDependency metaDependency = new MetaDependency();
                metaDependency.setValuesByPom(element);
                metaList.add(metaDependency);
            }
        }
        return metaList;
    }

    private NodeList getNodes(NodeList children, String nodeType) {
        for (int i = 0; i < children.getLength(); i++) {
            Node node = children.item(i);
            if (node instanceof Element) {
                Element element = (Element) node;
                if (nodeType.equals(element.getNodeName())) {
                    return element.getChildNodes();
                }
            }
        }
        return null;
    }

}
