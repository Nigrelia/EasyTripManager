package com.EasyTripManager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class ViewMembers {
    public static void main(String[] args) {
        System.out.println("\n=== Members List ===\n");
        
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM members")) {
            
            // Print table header
            System.out.printf("%-12s %-20s %-25s %-15s %-15s%n", 
                            "Account ID", "Full Name", "Email", "Account Type", "Company Name");
            System.out.println("-".repeat(90));
            
            // Print each member
            while (rs.next()) {
                System.out.printf("%-12s %-20s %-25s %-15s %-15s%n",
                    rs.getString("account_id"),
                    rs.getString("full_name"),
                    rs.getString("email"),
                    rs.getString("account_type"),
                    rs.getString("company_name") != null ? rs.getString("company_name") : "-"
                );
            }
            
        } catch (Exception e) {
            System.out.println("Error viewing members: " + e.getMessage());
            e.printStackTrace();
        }
    }
}