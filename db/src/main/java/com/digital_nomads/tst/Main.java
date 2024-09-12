package com.digital_nomads.tst;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.sql.Timestamp;

import com.digital_nomads.beans.ActorBean;
//import com.opencsv.CSVWriter;

public class Main {

    public static void writeDataLineByLine(String filePath, ActorBean actorBean) {
//        // Create file object for the specified file path
//        File file = new File(filePath);
//        try {
//            // Create FileWriter object with file as parameter
//            FileWriter outputFile = new FileWriter(file);
//
//            // Create CSVWriter object with FileWriter object as parameter
//            CSVWriter writer = new CSVWriter(outputFile);
//
//            // Adding header to csv
//            Field[] fields = ActorBean.class.getDeclaredFields();
//            String[] header = new String[fields.length];
//            for (int i = 0; i < fields.length; i++) {
//                header[i] = fields[i].getName();
//            }
//            writer.writeNext(header);
//
//            // Adding data to csv
//            String[] data = new String[fields.length];
//            for (int i = 0; i < fields.length; i++) {
//                fields[i].setAccessible(true); // Allow access to private fields
//                Object value = fields[i].get(actorBean);
//                data[i] = value != null ? value.toString() : "";
//            }
//            writer.writeNext(data);
//
//            // Closing writer connection
//            writer.close();
//        } catch (IOException | IllegalAccessException e) {
//            e.printStackTrace();
//        }
    }

    public static void main(String[] args) {
//        ActorBean actorBean = new ActorBean(1, "RandomName", "RandomLastName", Timestamp.valueOf("2013-05-26 14:47:57.62"));
//        String filePath = "/Users/ermekadiev/IdeaProjects/Spring2024_TAF/db/src/main/resources/actorBean.csv";
//        writeDataLineByLine(filePath, actorBean);
    }
}
