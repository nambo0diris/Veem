import java.io.*;
import org.apache.commons.codec.digest.DigestUtils;

public class VeemTask2 {
    public static void main(String filePath, String pathToDir) throws IOException {
        File listFile = new File(filePath);
        FileReader fileReader = new FileReader(listFile);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        InputStream is = new BufferedInputStream(new FileInputStream(listFile));
        byte[] c = new byte[1024];
        int count = 0;
        int readChars = 0;
        // узнаем количество строк в файле
        try {
            boolean endsWithoutNewLine = false;
            while ((readChars = is.read(c)) != -1) {
                for (int i = 0; i < readChars; ++i) {
                    if (c[i] == '\n')
                        ++count;
                }
                endsWithoutNewLine = (c[readChars - 1] != '\n');
            }
            if(endsWithoutNewLine) {
                ++count;
            }
        } finally {
            is.close();
        }

        // проходимся по строкам
        try {
            for (int j = 0; j < count; j ++){
                String result = bufferedReader.readLine();

                // каждую строку сплитим по пробелу
                String [] lineArr = result.split(" ");
                String fileName = lineArr[0];
                String crypto = lineArr[1];
                String hash = lineArr[2];

                // получаем нужную хеш сумму исходя из имени файла и указанного алгоритма
                String my = "";
                if (crypto.equals("sha1")){
                    my = DigestUtils.sha1Hex(fileName);
                } else if (crypto.equals("sha256")) {
                    my = DigestUtils.sha256Hex(fileName);
                } else if (crypto.equals("md5")) {
                    my = DigestUtils.md5Hex(fileName);
                }

                // проверяем наличия файла в указанной директории
                File f = new File(pathToDir+fileName);

                // если нет, сообщаем об этом
                if(!f.exists()) {
                    System.out.println(fileName + " NOT FOUND");
                } else {
                    // если есть, то сравниваем хеш-суммы
                    if(my.equals(hash)){
                        System.out.println(fileName + " OK");
                    } else {
                        System.out.println(fileName + " FAIL");
                    }
                }
            }
        } catch (NumberFormatException | IOException ignored) {
        }
    }

}
