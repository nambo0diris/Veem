import java.io.*;
import java.lang.management.ManagementFactory;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Date;
import java.util.Random;
import java.util.logging.*;

public class VeemTask3 {
    public static int tc_id;
    public static String name;
    public static Logger logger = Logger.getLogger("MyLogFile");

}

class Test1 extends VeemTask3 {
    public static void prep() {
        Date date = new Date();
        if (date.getTime() % 2 != 0) {
            System.exit(0);
        }
    }
    public static void run() {
        File file = new File("./");
        for (File x: file.listFiles()) {
            System.out.println(x);
        }
    }
    public static void clean_up() {

    }
    public static void execute() throws IOException {
        FileHandler fh = new FileHandler("./test");
        logger.addHandler(fh);
        SimpleFormatter formatter = new SimpleFormatter();
        fh.setFormatter(formatter);
        try {
            logger.log(Level.INFO, "Этап подготовки к тесту");
            prep();
            logger.log(Level.INFO, "Этап подготовки к тесту завершен");
            logger.log(Level.INFO, "Запуск теста");
            run();
            logger.log(Level.INFO, "Тест запущен");
            logger.log(Level.INFO, "Этап завершения теста");
            clean_up();
            logger.log(Level.INFO, "Тест завершен");
        } catch (NullPointerException e) {
            System.out.println(e);
            logger.log(Level.WARNING,"Ошибка выполнения " + e);
        }


    }
}
class Test2 extends VeemTask3 {
    public static void prep() {
        long memorySize = ((com.sun.management.OperatingSystemMXBean) ManagementFactory
                .getOperatingSystemMXBean()).getTotalMemorySize();
        int neededMemory = 1073741824;
        if (memorySize < neededMemory) {
            System.exit(0);
        }
    }
    public static void run() throws IOException {
        File out = new File("test.txt");
        FileWriter fw = null;
        int size = 1024;
        fw = new FileWriter(out);
        BufferedWriter bw = new BufferedWriter(fw);
        Random random = new Random();
        while (size > 0) {

            bw.write(String.valueOf(random.nextInt(2)));
            size--;
        }
        bw.close();
    }
    public static void clean_up() throws IOException {
        File file = new File("test.txt");
        Path path = Path.of(file.getAbsolutePath());
        Files.delete(path);
    }

    public static void execute() throws IOException {
        FileHandler fh = new FileHandler("./test");
        logger.addHandler(fh);
        SimpleFormatter formatter = new SimpleFormatter();
        fh.setFormatter(formatter);
        try {
            logger.log(Level.INFO, "Этап подготовки к тесту");
            prep();
            logger.log(Level.INFO, "Этап подготовки к тесту завершен");
            logger.log(Level.INFO, "Запуск теста");
            run();
            logger.log(Level.INFO, "Тест запущен");
            logger.log(Level.INFO, "Этап завершения теста");
            clean_up();
            logger.log(Level.INFO, "Тест завершен");
        } catch (NullPointerException e) {
            System.out.println(e);
            logger.log(Level.WARNING,"Ошибка выполнения " + e);
        }


    }

}