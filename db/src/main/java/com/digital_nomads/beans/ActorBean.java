package com.digital_nomads.beans;

import com.digital_nomads.utils.DBConnection;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.apache.commons.dbutils.BeanProcessor;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class ActorBean {
    int actor_id;
    String first_name;
    String last_name;
    String last_update;



    public static List<ActorBean> getAllActors() throws SQLException {
        String query = "select * from public.actor;";
        try(ResultSet resultSet = DBConnection.query(query)){
            return new BeanProcessor().toBeanList(resultSet, ActorBean.class);
        }
    }

    public static ActorBean getBy(String column,String value) throws SQLException {
        String query= "select * from public.actor where " + column + " = ?;";
        ResultSet resultSet = DBConnection.query(query,value);
        if (!resultSet.next()){
           return null;
        }else {
            return new BeanProcessor().toBean(resultSet,ActorBean.class);
        }
    }

    public static boolean createActor(String firstName, String lastName) throws SQLException {
        String query = "INSERT INTO public.actor (first_name, last_name, last_update) VALUES (?, ?, CURRENT_TIMESTAMP);";
        try (PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(query)) {
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        }
    }


}
