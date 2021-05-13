package pl.sdacademy.jdbc.hello.workshop1;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class Workshop1 {
    public static void main(String[] args) {
        List<String> countries = getCountries();
        final String countriesString = String.join("\n", countries);
        System.out.println(countriesString);
    }

    private static List<String> getCountries() {
        List<String> result = new LinkedList<>();
        try (Connection connection
                 = DriverManager.getConnection("jdbc:mysql://localhost:6306/world", "root", "example")) {
            try (PreparedStatement preparedStatement
                     = connection.prepareStatement("SELECT * FROM country WHERE continent = 'EUROPE' ORDER BY name;")) {
                ResultSet resultSet = preparedStatement.executeQuery();
                while(resultSet.next()){
                    result.add(resultSet.getString("Name"));
                }
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return result;
    }
}
