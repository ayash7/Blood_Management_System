package Entity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedHashMap;
import java.util.Map;

public class Donor {
    
    public Donor(String donor_id, String donor_name, String donor_type, Double amount_donated, Double amount_remaining) {
        
        this.donor_id = donor_id;
        this.donor_name = donor_name;
        this.donor_type = donor_type;
        this.amount_donated = amount_donated;
        this.amount_remaining = amount_remaining;
        
    }
    
    public void addDonor() {
        
        try {
            
            Class.forName(classname);
            
            Connection connection = DriverManager.getConnection(database_url, database_username, database_password);
            
            String query = "SELECT * FROM Donor WHERE donor_id = ?";
            
            PreparedStatement prepared_statement = connection.prepareStatement(query);
            
            prepared_statement.setString(1, donor_id);
            
            ResultSet result_set = prepared_statement.executeQuery();
            
            if(result_set.next()) {
                
                System.out.println("Error: Donor already present in database.");
                
                connection.close(); return;
                
            }
            
            query = "INSERT INTO Donor VALUES(?, ?, ?, ?, ?)";
            
            prepared_statement = connection.prepareStatement(query);
            
            prepared_statement.setString(1, donor_id);
            prepared_statement.setString(2, donor_name);
            prepared_statement.setString(3, donor_type);
            prepared_statement.setDouble(4, amount_donated);
            prepared_statement.setDouble(5, amount_remaining);
            
            prepared_statement.execute();
            
            connection.close();
            
            System.out.println("Status: Donor successfully added to database.");
            
        }
        
        catch(Exception exp) {
            
            System.out.println(exp);
            
        }
        
    }
    
    public static void generateReport() {
        
        try {
            
            Class.forName(classname);
            
            Connection connection = DriverManager.getConnection(database_url, database_username, database_password);
            
            String query = "SELECT * FROM Donor";
            
            PreparedStatement prepared_statement = connection.prepareStatement(query);
            
            ResultSet result_set = prepared_statement.executeQuery();
            
            System.out.println("--------------------DONOR DETAILS--------------------");
            
            if(!result_set.next()) {
                
                System.out.println("Status: Zero donors present in database.");
                
                connection.close(); return;
                
            }
            
            do {
                
                System.out.println("donor_id: " + result_set.getString(1) + "    donor_name: " + result_set.getString(2) + "    donor_type: " + result_set.getString(3) + "    amount_donated: " + result_set.getDouble(4) + "    amount_remaining: " + result_set.getDouble(5));
                
            } while(result_set.next());
            
            connection.close();
            
        }
        
        catch(Exception exp) {
            
            System.out.println(exp);
            
        }
        
    }
    
    public static void generateReportByType() {
        
        try {
            
            Class.forName(classname);
            
            Connection connection = DriverManager.getConnection(database_url, database_username, database_password);
            
            String query = "SELECT donor_type, amount_remaining FROM Donor";
            
            PreparedStatement prepared_statement = connection.prepareStatement(query);
            
            ResultSet result_set = prepared_statement.executeQuery();
            
            System.out.println("--------------------BLOOD TYPE DETAILS--------------------");
            
            if(!result_set.next()) {
                
                System.out.println("Status: Zero donors present in database.");
                
                connection.close(); return;
                
            }
            
            LinkedHashMap<String, Double> blood_type = new LinkedHashMap<String, Double>();
            
            do {
                
                if(blood_type.get(result_set.getString(1)) == null) {
                    
                    blood_type.put(result_set.getString(1), 0.d);
                    
                }
                
                blood_type.put(result_set.getString(1), blood_type.get(result_set.getString(1)) + result_set.getDouble(2));
                
            } while(result_set.next());
            
            for(Map.Entry<String, Double> entry: blood_type.entrySet()) {
                
                System.out.println("blood_type: " + entry.getKey() + "    amount_remaining: " + entry.getValue());
                
            }
            
            connection.close();
            
        }
        
        catch(Exception exp) {
            
            System.out.println(exp);
            
        }
        
    }
    
    public static void deleteDonor(String donor_id) {
        
        try {
            
            Class.forName(classname);
            
            Connection connection = DriverManager.getConnection(database_url, database_username, database_password);
            
            String query = "SELECT * FROM Donor WHERE donor_id = ?";
            
            PreparedStatement prepared_statement = connection.prepareStatement(query);
            
            prepared_statement.setString(1, donor_id);
            
            ResultSet result_set = prepared_statement.executeQuery();
            
            if(!result_set.next()) {
                
                System.out.println("Error: Donor not present in database.");
                
                connection.close(); return;
                
            }
            
            query = "DELETE FROM Donor WHERE donor_id = ?";
            
            prepared_statement = connection.prepareStatement(query);
            
            prepared_statement.setString(1, donor_id);
            
            prepared_statement.execute();
            
            connection.close();
            
            System.out.println("Status: Donor successfully deleted from database.");
            
        }
        
        catch(Exception exp) {
            
            System.out.println(exp);
            
        }
        
    }
    
    public static void updateDonor(String donor_id, Double amount_added) {
        
        try {
            
            Class.forName(classname);
            
            Connection connection = DriverManager.getConnection(database_url, database_username, database_password);
            
            String query = "SELECT amount_donated, amount_remaining FROM Donor WHERE donor_id = ?";
            
            PreparedStatement prepared_statement = connection.prepareStatement(query);
            
            prepared_statement.setString(1, donor_id);
            
            ResultSet result_set = prepared_statement.executeQuery();
            
            if(!result_set.next()) {
                
                System.out.println("Error: Donor not present in database.");
                
                connection.close(); return;
                
            }
            
            Double amount_donated = result_set.getDouble(1) + amount_added;
            Double amount_remaining = result_set.getDouble(2) + amount_added;
            
            query = "UPDATE Donor SET amount_donated = ?, amount_remaining = ? WHERE donor_id = ?";
            
            prepared_statement = connection.prepareStatement(query);
            
            prepared_statement.setDouble(1, amount_donated);
            prepared_statement.setDouble(2, amount_remaining);
            prepared_statement.setString(3, donor_id);
            
            prepared_statement.execute();
            
            connection.close();
            
            System.out.println("Status: Donor successfully updated in database.");
            
        }
        
        catch(Exception exp) {
            
            System.out.println(exp);
            
        }
        
    }
    
    public static Double updateDonation(String donor_id, String receiver_type, Double amount_required) {
        
        try {
            
            Class.forName(classname);
            
            Connection connection = DriverManager.getConnection(database_url, database_username, database_password);
            
            String query = "SELECT donor_type, amount_remaining FROM Donor WHERE donor_id = ?";
            
            PreparedStatement prepared_statement = connection.prepareStatement(query);
            
            prepared_statement.setString(1, donor_id);
            
            ResultSet result_set = prepared_statement.executeQuery();
            
            if(!result_set.next()) {
                
                System.out.println("Error: Donor not present in database.");
                
                connection.close(); return 0.d;
                
            }
            
            if(!result_set.getString(1).equals(receiver_type)) {
                
                System.out.println("Error: Blood type mismatch.");
                
                connection.close(); return 0.d;
                
            }
            
            Double amount_remaining = result_set.getDouble(2) - amount_required;
            
            if(amount_remaining <= 0.d) {
                
                deleteDonor(donor_id); amount_required = result_set.getDouble(2);
                
                connection.close(); return amount_required;
                
            }
            
            query = "UPDATE Donor SET amount_remaining = ? WHERE donor_id = ?";
            
            prepared_statement = connection.prepareStatement(query);
            
            prepared_statement.setDouble(1, amount_remaining);
            prepared_statement.setString(2, donor_id);
            
            prepared_statement.execute();
            
            connection.close();
            
            System.out.println("Status: Donor successfully updated in database.");
            
        }
        
        catch(Exception exp) {
            
            System.out.println(exp);
            
        }
        
        return amount_required;
        
    }
    
    private
        
        String donor_id, donor_name, donor_type;
        Double amount_donated, amount_remaining;
        
        static String classname = "com.mysql.cj.jdbc.Driver";
        static String database_url = "jdbc:mysql://localhost:3306/Entity";
        static String database_username = "root";
        static String database_password = "password";
        
}