package pl.sdacademy.jdbc.hello.workshop2;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Workshop2 {
    public static void main(String[] args) {
        System.out.println("Podaj nazwę kontynentu:");
        final String continent = new Scanner(System.in).nextLine();

        List<String> countries = getCountries(continent);
        final String countriesString = String.join("\n", countries);
        System.out.println(countriesString);
    }

    private static List<String> getCountries(String continent) {
        List<String> result = new LinkedList<>();
        try (Connection connection
                     = DriverManager.getConnection("jdbc:mysql://localhost:3306/world", "root", "password")) {
            try (PreparedStatement preparedStatement
                         = connection.prepareStatement("SELECT * FROM country WHERE continent = ? ORDER BY name;")) {
                preparedStatement.setString(1,continent);
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
