package com.matt;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Database {

    private Connection connect() {
        // SQLite connection string
        // where the database lives on my system
        String url = "jdbc:sqlite:/Users/matt/Documents/development/EchoClient/troop.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    // this is method that sets the table headers setting int and doubles for the table
    public void insert(int id, int ammo, double rations, double water, int location) {
        String sql = "INSERT INTO troop(id, ammo, rations, water, location) VALUES(?,?,?,?,?)";
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.setInt(2, ammo);
            pstmt.setDouble(3, rations);
            pstmt.setDouble(4, water);
            pstmt.setInt(5, location);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}


