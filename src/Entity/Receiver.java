package Entity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Receiver {
    
    public Receiver(String receiver_id, String receiver_name, String receiver_type, Double amount_required) {
        
        this.receiver_id = receiver_id;
        this.receiver_name = receiver_name;
        this.receiver_type = receiver_type;
        this.amount_required = amount_required;
        
    }
    
    public void addReceiver() {
        
        try {
            
            Class.forName(classname);
            
            Connection connection = DriverManager.getConnection(database_url, database_username, database_password);
            
            String query = "SELECT * FROM Receiver WHERE receiver_id = ?";
            
            PreparedStatement prepared_statement = connection.prepareStatement(query);
            
            prepared_statement.setString(1, receiver_id);
            
            ResultSet result_set = prepared_statement.executeQuery();
            
            if(result_set.next()) {
                
                System.out.println("Error: Receiver already present in database.");
                
                connection.close(); return;
                
            }
            
            query = "INSERT INTO Receiver VALUES(?, ?, ?, ?)";
            
            prepared_statement = connection.prepareStatement(query);
            
            prepared_statement.setString(1, receiver_id);
            prepared_statement.setString(2, receiver_name);
            prepared_statement.setString(3, receiver_type);
            prepared_statement.setDouble(4, amount_required);
            
            prepared_statement.execute();
            
            connection.close();
            
            System.out.println("Status: Receiver successfully added to database.");
            
        }
        
        catch(Exception exp) {
            
            System.out.println(exp);
            
        }
        
    }
    
    public static void generateReport() {
        
        try {
            
            Class.forName(classname);
            
            Connection connection = DriverManager.getConnection(database_url, database_username, database_password);
            
            String query = "SELECT * FROM Receiver";
            
            PreparedStatement prepared_statement = connection.prepareStatement(query);
            
            ResultSet result_set = prepared_statement.executeQuery();
            
            System.out.println("--------------------RECEIVER DETAILS--------------------");
            
            if(!result_set.next()) {
                
                System.out.println("Status: Zero receivers present in database.");
                
                connection.close(); return;
                
            }
            
            do {
                
                System.out.println("receiver_id: " + result_set.getString(1) + "    receiver_name: " + result_set.getString(2) + "    receiver_type: " + result_set.getString(3) + "    amount_required: " + result_set.getDouble(4));
                
            } while(result_set.next());
            
            connection.close();
            
        }
        
        catch(Exception exp) {
            
            System.out.println(exp);
            
        }
        
    }
    
    public static void deleteReceiver(String receiver_id) {
        
        try {
            
            Class.forName(classname);
            
            Connection connection = DriverManager.getConnection(database_url, database_username, database_password);
            
            String query = "SELECT * FROM Receiver WHERE receiver_id = ?";
            
            PreparedStatement prepared_statement = connection.prepareStatement(query);
            
            prepared_statement.setString(1, receiver_id);
            
            ResultSet result_set = prepared_statement.executeQuery();
            
            if(!result_set.next()) {
                
                System.out.println("Error: Receiver not present in database.");
                
                connection.close(); return;
                
            }
            
            query = "DELETE FROM Receiver WHERE receiver_id = ?";
            
            prepared_statement = connection.prepareStatement(query);
            
            prepared_statement.setString(1, receiver_id);
            
            prepared_statement.execute();
            
            connection.close();
            
            System.out.println("Status: Receiver successfully deleted from database.");
            
        }
        
        catch(Exception exp) {
            
            System.out.println(exp);
            
        }
        
    }
    
    public static void updateReceiver(String receiver_id, String donor_id) {
        
        try {
            
            Class.forName(classname);
            
            Connection connection = DriverManager.getConnection(database_url, database_username, database_password);
            
            String query = "SELECT receiver_type, amount_required FROM Receiver WHERE receiver_id = ?";
            
            PreparedStatement prepared_statement = connection.prepareStatement(query);
            
            prepared_statement.setString(1, receiver_id);
            
            ResultSet result_set = prepared_statement.executeQuery();
            
            if(!result_set.next()) {
                
                System.out.println("Error: Receiver not present in database.");
                
                connection.close(); return;
                
            }
            
            Double amount_used = Donor.updateDonation(donor_id, result_set.getString(1), result_set.getDouble(2));
            
            if(amount_used == 0.d) {
                
                System.out.println("Status: Receiver not updated in database.");
                
                connection.close(); return;
                
            }
            
            Double amount_required = result_set.getDouble(2) - amount_used;
            
            if(amount_required == 0.d) {
                
                deleteReceiver(receiver_id); connection.close(); return;
                
            }
            
            query = "UPDATE Receiver SET amount_required = ? WHERE receiver_id = ?";
            
            prepared_statement = connection.prepareStatement(query);
            
            prepared_statement.setDouble(1, amount_required);
            prepared_statement.setString(2, receiver_id);
            
            prepared_statement.execute();
            
            connection.close();
            
            System.out.println("Status: Receiver successfully updated in database.");
            
        }
        
        catch(Exception exp) {
            
            System.out.println(exp);
            
        }
        
    }
    
    private
        
        String receiver_id, receiver_name, receiver_type;
        Double amount_required;
        
        static String classname = "com.mysql.cj.jdbc.Driver";
        static String database_url = "jdbc:mysql://localhost:3306/Entity";
        static String database_username = "root";
        static String database_password = "password";
        
}