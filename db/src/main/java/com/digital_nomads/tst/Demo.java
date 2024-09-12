package com.digital_nomads.tst;

import com.digital_nomads.beans.ActorBean;
import com.digital_nomads.utils.DBConnection;

import java.sql.SQLException;
import java.sql.Timestamp;

public class Demo {

    public static void main(String[] args) throws SQLException {

        DBConnection.openConnection("dvdrental");

        String[] columns = {"first_name", "last_name", "last_update"};
        Object[] values = {"John1", "Doe1", new Timestamp(System.currentTimeMillis())};

        boolean isInserted = DBConnection.insertInto("public.actor", columns, values);
        if (isInserted) {
            System.out.println("Actor inserted successfully!");
        } else {
            System.out.println("Actor insertion failed.");
        }

            DBConnection.close();
        }
    }


