import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.TreeMap;

public class Main {
    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException {

        // Задача №1
        // путь к xml файлу
        VeemTask1.copyWithConfig("/home/c3po/IdeaProjects/Veem/src/files/example.xml");




        // Задача №2

        // файл со списком и хеш суммами
        String pathToDir = "/home/c3po/IdeaProjects/Veem/src/files/";
        VeemTask2.main("/home/c3po/IdeaProjects/Veem/src/files/list.txt", pathToDir);



        // Задача №3
        Test1.execute();
        Test2.execute();
    }
}

