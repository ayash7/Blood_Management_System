package Entity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Admin {
    
    public Admin(String username, String password) {
        
        this.username = username;
        this.password = password;
        
    }
    
    public void registerAdmin(String database_username, String database_password) {
        
        try {
            
            if(!database_username.equals(Admin.database_username)) {
                
                System.out.println("Error: Invalid database username."); return;
                
            }
            
            if(!database_password.equals(Admin.database_password)) {
                
                System.out.println("Error: Invalid database password."); return;
                
            }
            
            Class.forName(classname);
            
            Connection connection = DriverManager.getConnection(database_url, Admin.database_username, Admin.database_password);
            
            String query = "SELECT * FROM Admin WHERE username = ?";
            
            PreparedStatement prepared_statement = connection.prepareStatement(query);
            
            prepared_statement.setString(1, username);
            
            ResultSet result_set = prepared_statement.executeQuery();
            
            if(result_set.next()) {
                
                System.out.println("Error: Admin already registered.");
                
                connection.close(); return;
                
            }
            
            query = "INSERT INTO Admin VALUES(?, ?)";
            
            prepared_statement = connection.prepareStatement(query);
            
            prepared_statement.setString(1, username);
            prepared_statement.setString(2, password);
            
            prepared_statement.execute();
            
            connection.close();
            
            System.out.println("Status: Admin registered successfully.");
            
        }
        
        catch(Exception exp) {
            
            System.out.println(exp);
            
        }
        
    }
    
    public static void deleteAdmin(String username, String database_username, String database_password) {
        
        try {
            
            if(!database_username.equals(Admin.database_username)) {
                
                System.out.println("Error: Invalid database username."); return;
                
            }
            
            if(!database_password.equals(Admin.database_password)) {
                
                System.out.println("Error: Invalid database password."); return;
                
            }
            
            Class.forName(classname);
            
            Connection connection = DriverManager.getConnection(database_url, Admin.database_username, Admin.database_password);
            
            String query = "SELECT * FROM Admin WHERE username = ?";
            
            PreparedStatement prepared_statement = connection.prepareStatement(query);
            
            prepared_statement.setString(1, username);
            
            ResultSet result_set = prepared_statement.executeQuery();
            
            if(!result_set.next()) {
                
                System.out.println("Error: Admin not registered.");
                
                connection.close(); return;
                
            }
            
            query = "DELETE FROM Admin WHERE username = ?";
            
            prepared_statement = connection.prepareStatement(query);
            
            prepared_statement.setString(1, username);
            
            prepared_statement.execute();
            
            connection.close();
            
            System.out.println("Status: Admin deleted successfully.");
            
        }
        
        catch(Exception exp) {
            
            System.out.println(exp);
            
        }
        
    }
    
    public static void updatePassword(String username, String current_password, String new_password) {
        
        try {
            
            Class.forName(classname);
            
            Connection connection = DriverManager.getConnection(database_url, database_username, database_password);
            
            String query = "SELECT password FROM Admin WHERE username = ?";
            
            PreparedStatement prepared_statement = connection.prepareStatement(query);
            
            prepared_statement.setString(1, username);
            
            ResultSet result_set = prepared_statement.executeQuery();
            
            result_set.next();
            
            if(!result_set.getString(1).equals(current_password)) {
                
                System.out.println("Error: Current password invalid.");
                
                connection.close(); return;
                
            }
            
            if(current_password.equals(new_password)) {
                
                System.out.println("Error: New password not different.");
                
                connection.close(); return;
                
            }
            
            query = "UPDATE Admin SET password = ? WHERE username = ?";
            
            prepared_statement = connection.prepareStatement(query);
            
            prepared_statement.setString(1, new_password);
            prepared_statement.setString(2, username);
            
            prepared_statement.execute();
            
            connection.close();
            
            System.out.println("Status: Password updated successfully.");
            
        }
        
        catch(Exception exp) {
            
            System.out.println(exp);
            
        }
        
    }
    
    public static boolean adminLogin(String username, String password) {
        
        try {
            
            Class.forName(classname);
            
            Connection connection = DriverManager.getConnection(database_url, database_username, database_password);
            
            String query = "SELECT password FROM Admin WHERE username = ?";
            
            PreparedStatement prepared_statement = connection.prepareStatement(query);
            
            prepared_statement.setString(1, username);
            
            ResultSet result_set = prepared_statement.executeQuery();
            
            if(!result_set.next()) {
                
                System.out.println("Error: Admin not registered.");
                
                connection.close(); return false;
                
            }
            
            if(!result_set.getString(1).equals(password)) {
                
                System.out.println("Error: Invalid password entered.");
                
                connection.close(); return false;
                
            }
            
            connection.close();
            
            System.out.println("Status: Admin login successful.");
            
        }
        
        catch(Exception exp) {
            
            System.out.println(exp);
            
        }
        
        return true;
        
    }
    
    private
        
        String username, password;
        
        static String classname = "com.mysql.cj.jdbc.Driver";
        static String database_url = "jdbc:mysql://localhost:3306/Entity";
        static String database_username = "root";
        static String database_password = "password";
        
}