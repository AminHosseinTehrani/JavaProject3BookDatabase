package com.example.aminhosseintehrani_comp228_sec401_testfall2022;
import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetProvider;
import java.sql.*;
public class DBUtil {
    private static Connection connection = null;
    private static Statement statement = null;

    public static void dbConnect() throws SQLException {
        // connect to database
        String dbURL = "jdbc:oracle:thin:@199.212.26.208:1521:SQLD";
        String username = "COMP228_F22_sh_18";
        String password = "password";
        try {
            connection = DriverManager.getConnection(dbURL, username, password);
            System.out.println("Database is connected!");
            statement = connection.createStatement();
        } catch (SQLException e) {
            System.out.println("Database is not connected!");
            System.out.println(e.getMessage());
        }
    }

    //close database
    public static void dbDisconnect() throws SQLException {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Database is disconnected!");
            }
        } catch (Exception e) {
            throw e;
        }
    }

    //insert data in to Game table
    public static void insertBookData(int bookID, String title,String author, String category, int p_year) throws SQLException {
        dbConnect();

        try {
            //            String sql = "INSERT INTO GAME(game_id,game_title) VALUES(" + "'" + bookID + "'" + "," + "'" + title + "'" + ")";
            //"INSERT INTO Player(player_id,first_name,last_name,address,POSTAL_CODE,province,PHONE_NUMBER) VALUES(
            String sql = "INSERT INTO BOOK(bookID,title,author,category,p_year) VALUES(" + "'" + bookID + "'" + "," + "'" + title + "'" + "," + "'" + author + "'" + "," + "'" + category + "'" + "," + "'" + p_year + "'" + ")";
            statement.execute(sql);
            System.out.println("row inserted!");
        } catch (Exception e) {
            System.out.println("Error. Row was not inserted");
            System.out.println(e.getMessage());
        }
        if (statement != null) {
            //Close Statement
            statement.close();
        }
        //Close connection
        dbDisconnect();
    }

    //update Game table
    public static void updateGameData(String game_id, String game_title) throws SQLException {
        dbConnect();
        try {
            String sql = "Update Game set Game_title ='" + game_title + "'" + "where game_id =" + "'" + game_id + "'";
            statement.executeUpdate(sql);
            System.out.println("Row updated!");
        } catch (Exception e) {
            System.out.println("Error Row not updated");
            System.out.println(e.getMessage());
        }
        if (statement != null) {
            //close statement
            statement.close();
        }
        dbDisconnect();
    }

    // insert data in to Player table
    public static void insertPlayerData(String player_id, String first_name, String last_name, String address, String postal_code, String province, long phone_number) throws SQLException {
        dbConnect();
        try {
            String sql = "INSERT INTO Player(player_id,first_name,last_name,address,POSTAL_CODE,province,PHONE_NUMBER) VALUES(" + player_id + "," + "'" + first_name + "'" + "," + "'" + last_name + "'" + "," + "'" + address + "'" + "," + "'" + postal_code + "'" + "," + "'" + province + "'" + "," + phone_number + ")";
            statement.execute(sql);
            System.out.println("row inserted!");
        } catch (Exception e) {
            System.out.println("Error. Row was not inserted");
            System.out.println(e.getMessage());
        }
        if (statement != null) {
            //Close Statement
            statement.close();
        }
        //Close connection
        dbDisconnect();
    }

    //update player data
    public static void updatePlayerData(String player_id, String first_name, String last_name, String address, String postal_code, String province, long phone_number) throws SQLException {
        dbConnect();
        try {
            String sql = "Update Player set first_name =" + "'" + first_name + "'" + ",last_name=" + "'" + last_name + "'" + ",address=" + "'" + address + "'" + ",postal_code =" + "'" + postal_code + "'" + ",province=" + "'" + province + "'" + ",phone_number=" + phone_number + "Where player_id =" + player_id;
            statement.execute(sql);
            System.out.println("Row updated!");
        } catch (Exception e) {
            System.out.println("Error Row not updated");
            System.out.println(e.getMessage());
        }
        if (statement != null) {
            //close statement
            statement.close();
        }
        dbDisconnect();
    }

    //insert player and game data
    public static void insertPlayerAndGameData(int player_game_id, String game_id, int player_id, String playing_Date, int score) throws SQLException {
        dbConnect();
        try {
            String sql = "insert into player_and_game (player_game_id,game_id,player_id,playing_date,score)VALUES(" + player_game_id + "," + "'" + game_id + "'" + "," + "'" + player_id + "'" + "," + "'" + playing_Date + "'" + "," + score + ")";
            statement.execute(sql);
            System.out.println("row inserted!");
        } catch (Exception e) {
            System.out.println("Error. Row was not inserted");
            System.out.println(e.getMessage());
        }
        if (statement != null) {
            //Close Statement
            statement.close();
        }
        //Close connection
        dbDisconnect();
    }

    //update player and game Data
    public static void updatePlayerAndGameData(int player_game_id, String game_id, int player_id, String playing_Date, int score) throws SQLException {
        dbConnect();
        try {
            String sql = "Update player_and_game set player_game_id where = " + player_game_id + "," + "'" + game_id + "'" + "," + "'" + player_id + "'" + "," + "'" + playing_Date + "'" + "," + score + "where player_game_id =" + player_game_id;
            statement.execute(sql);
            System.out.println("Row updated!");
        } catch (Exception e) {
            System.out.println("Error Row not updated");
            System.out.println(e.getMessage());
        }
        if (statement != null) {
            //close statement
            statement.close();
        }
        dbDisconnect();
    }

    public static ResultSet query(String sql) throws SQLException {
        CachedRowSet crs = RowSetProvider.newFactory().createCachedRowSet();
        //open db connection
        dbConnect();
        ResultSet resultSet = null;
        try {
            resultSet = statement.executeQuery(sql);
            crs.populate(resultSet);
        } catch (SQLException e) {
            System.out.println("Query did not run!");
            System.out.println(e.getMessage());
        }
        if (statement != null) {
            //Close Statement
            statement.close();
        }
        //Close connection
        dbDisconnect();
        return crs;
    }
}
