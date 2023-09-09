package org.example;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.testng.annotations.Test;
import org.testng.Assert;

public class Main {
    @Test
    public void promotion() throws SQLException, ClassNotFoundException, ArrayIndexOutOfBoundsException {
        Map<Integer, Person> personMap = new HashMap<>();
        String select_sql = "SELECT * FROM persons3";
        Class.forName("org.postgresql.Driver");
        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "docker");
        Statement statement = connection.createStatement();

        ResultSet rs = statement.executeQuery(select_sql);
        while (rs.next()) {
            int personId = rs.getInt("PersonID");

            String lastName = rs.getString("LastName");
            String firstName = rs.getString("FirstName");
            String role = rs.getString("Role");
            int promotion = rs.getInt("Promotion");

            Person person = new Person(personId, lastName, firstName, role, promotion);
            personMap.put(personId, person);
        }

        connection.close();

        for (Person person : personMap.values()) {
            String role = person.getRole();
            int promotion = person.getPromotion();

            if ("manager".equals(role)) {
                Assert.assertEquals(promotion, 12, "Manager should have a promotion of 12");
            } else if ("staff".equals(role)) {
                Assert.assertEquals(promotion, 10, "Staff should have a promotion of 10");
            }
        }
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException,ArrayIndexOutOfBoundsException{
        List<Integer> idList = new ArrayList<>();
        int[] array = new int[5];
        int i = 0;
        String select_sql = "SELECT * FROM persons3";
        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "docker");
        Statement statement = connection.createStatement();
        ResultSet rs= statement.executeQuery(select_sql);
        while (rs.next()) {
          //  int personId = rs.getInt("PersonId");
         //   idList.add(personId);
            System.out.println(rs.getString("PersonId") + " " + rs.getString(2) + " " + rs.getString(3)
                    + " " + rs.getString(4) + " " + rs.getString(5) + " " + rs.getString(6) + " " + rs.getString(7) + " " + rs.getString(8));
            int a = rs.getInt("PersonId");
            array[i++] = a;
    }
}}

class Person {
    private int personId;
    private String lastName;
    private String firstName;
    private String role;
    private int promotion;

    public Person(int personId, String lastName, String firstName, String role, int promotion) {

        this.personId = personId;
        this.lastName = lastName;
        this.firstName = firstName;
        this.role = role;
        this.promotion = promotion;
    }

    public int getPersonId() {
        return personId;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getRole() {
        return role;
    }

    public int getPromotion() {
        return promotion;
    }
}

