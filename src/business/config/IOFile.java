package business.config;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class IOFile {

    public static <T extends Serializable> void writeObjectToFile(List<T> list, File file) {
        FileOutputStream fos = null; // output là đầu ra nên cái FileOutputStream nó là ghi file (đại diện cho file nào dựa vào path)
        ObjectOutputStream oos = null; // đối tượng ObjectOutputStream dành việc ghi object vào file
        try {
            fos = new FileOutputStream(file);
            oos = new ObjectOutputStream(fos);

            oos.writeObject(list);

        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (fos != null) {
                    fos.close();
                }
                if (oos != null) {
                    oos.close();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
    }

    public static <T extends Serializable> List<T> readObjectFromFile(File file) {
        FileInputStream fis = null; // Input là đầu vào nên nó sẽ là lấy dữ liệu vào chương trình (đại diện cho file thông đường dẫn path)
        ObjectInputStream ois = null; // ObjectInputStream nó là đối tượng dùng để làm việc đọc file
        List<T> list = new ArrayList<>();
        try {
            fis = new FileInputStream(file);
            ois = new ObjectInputStream(fis);

            list = (List<T>) ois.readObject();
            return list;
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (fis != null) {
                    fis.close();
                }
                if (ois != null) {
                    ois.close();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
