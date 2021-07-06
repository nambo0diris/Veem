import java.io.*;
import java.nio.file.Files;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class VeemTask1 {
    public static void copyWithConfig(String configPath) throws IOException, SAXException, ParserConfigurationException {
        File xmlFile = new File(configPath);
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        Document doc = null;

        try {
            doc = dbf.newDocumentBuilder().parse(xmlFile);
        } catch (ParserConfigurationException ex) {
            ex.printStackTrace(System.out);
        } catch (IOException | SAXException e) {
            e.printStackTrace();
        }
        Node node = doc.getFirstChild();
        NodeList nodeList = node.getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i ++) {
            if (nodeList.item(i).getNodeType() != Node.TEXT_NODE) {
                NamedNodeMap s = nodeList.item(i).getAttributes();
                String pathFrom = s.item(2).getTextContent();
                String pathTo = s.item(0).getTextContent();
                String fileName = s.item(1).getTextContent();
                File file = new File(pathFrom, fileName);
                File newFile = new File(pathTo, fileName);

                if (newFile.exists()) {
                    fileName = fileName + "(copy)";
                    newFile = new File(pathTo, fileName);
                }
                Files.copy(file.toPath(),newFile.toPath());
            }
        }
    }
}
