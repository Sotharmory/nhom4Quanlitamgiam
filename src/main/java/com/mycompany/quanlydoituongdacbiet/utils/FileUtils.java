package com.mycompany.quanlydoituongdacbiet.utils;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class FileUtils {

    /**
     * Chuyển đổi đối tượng object về định dạng XML
     * Sau đo lưu vào fileName
     * 
     * @param fileName
     * @param object
     */
    public static void writeXMLtoFile(String fileName, Object object) {
        try {
            // tạo đối tượng JAXB Context
            JAXBContext jaxbContext = JAXBContext.newInstance(object.getClass());
            // Create đối tượng Marshaller
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            // formating 
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            // lưu nội dung XML vào file
            File xmlFile = new File(fileName);
            jaxbMarshaller.marshal(object, xmlFile);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    /**
     * Đọc nội dung fileName, sau đó chuyển đổi nội dung của file 
     * thành đối tượng có kiểu là clazz.
     * 
     * @param fileName
     * @param clazz
     * @return
     */
    public static Object readXMLFile(String fileName, Class<?> clazz) {
        try {
            File xmlFile = new File(fileName);

            if (!xmlFile.exists()) {
                System.out.println("File " + fileName + " không tồn tại. Tạo mới với dữ liệu rỗng.");

                // Tạo một instance rỗng của lớp wrapper
                Object emptyWrapper = clazz.getDeclaredConstructor().newInstance();

                // Ghi ra file để lần sau đọc được
                writeXMLtoFile(fileName, emptyWrapper);

                return emptyWrapper;
            }

            JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            return jaxbUnmarshaller.unmarshal(xmlFile);
        } catch (JAXBException e) {
            System.err.println("Lỗi JAXB khi đọc file: " + fileName);
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Lỗi khác khi đọc file: " + fileName);
            e.printStackTrace();
        }

        return null;
    }
}
