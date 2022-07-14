package com.makrusali.belajar;

import java.sql.*;

/**
 * Hello world!
 * Makrus Ali
 */


public class App {


    public static void main( String[] args ) {

        String dbUsername = "root";
        String dbPassword = "";


        try{
            // instance object dari driver connector mysql
            // secara otomatis
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch (ClassNotFoundException e){
        }

        try {
            // Membuat koneksi ke DATABASE MySQL
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/latihan",dbUsername,dbPassword);
            // Membuat statement untuk execute query ke database
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);

            // Membuat result set
            // result set adalah interface yang menyediakan method untuk getter dan setter
            // di sini di gunakan untuk menampung hasi dari query ke database
            ResultSet resultSet = statement.executeQuery("SELECT * FROM t_person");

            // geser cursor .next akan mengembalikan true jika row masih ada
            while (resultSet.next()){
                // print hasil result dengan .get deangan argument adalah key dari column
                System.out.println(resultSet.getInt("id"));
                System.out.println(resultSet.getString("name"));
                System.out.println(resultSet.getString("password"));
            }

            // execute delete
            int result  = statement.executeUpdate("DELETE from t_person where name='MAHAIRUL'");
            System.out.println(result);

            resultSet = statement.executeQuery("SELECT * FROM t_person");
            // geser cursor .next akan mengembalikan true jika row masih ada
            while (resultSet.next()){
                // print hasil result dengan .get deangan argument adalah key dari column
                System.out.println(resultSet.getInt("id"));
                System.out.println(resultSet.getString("name"));
                System.out.println(resultSet.getString("password"));
            }

            // close semua resources
            connection.close();
            statement.close();
            resultSet.close();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}


