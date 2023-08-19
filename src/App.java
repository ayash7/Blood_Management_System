import java.util.Scanner;

import Entity.Admin;
import Entity.Donor;
import Entity.Receiver;

public class App {
    
    public static void main(String[] args) throws Exception {
        
        String database_username, database_password, current_username, username, password, donor_id, receiver_id; Double amount_donated; Integer choice;
        
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("--------------------BLOOD MANAGEMENT SYSTEM--------------------");
        
        while(true) {
            
            System.out.println("1. Register Admin");
            System.out.println("2. Delete Admin");
            System.out.println("3. Admin Login");
            System.out.println("4. Exit");
            System.out.print("Enter Choice: ");
            
            choice = Integer.parseInt(scanner.nextLine());
            
            if(choice == 1) {
                
                while(true) {
                    
                    System.out.print("Username: "); username = scanner.nextLine();
                    
                    if(username.length() > 20) {
                        
                        System.out.println("Error: Username format mismatch."); continue;
                        
                    }
                    
                    System.out.print("Password: "); password = scanner.nextLine();
                    
                    if(password.length() > 40) {
                        
                        System.out.println("Error: Password format mismatch."); continue;
                        
                    }
                    
                    break;
                    
                }
                
                System.out.print("Database Username: "); database_username = scanner.nextLine();
                
                System.out.print("Database Password: "); database_password = scanner.nextLine();
                
                if(username.equals("-1") || password.equals("-1") || database_username.equals("-1") || database_username.equals("-1")) continue;
                
                Admin admin = new Admin(username, password);
                
                admin.registerAdmin(database_username, database_password);
                
            }
            
            else if(choice == 2) {
                
                while(true) {
                    
                    System.out.print("Username: "); username = scanner.nextLine();
                    
                    if(username.length() > 20) {
                        
                        System.out.println("Error: Username format mismatch."); continue;
                        
                    }
                    
                    break;
                    
                }
                
                System.out.print("Database Username: "); database_username = scanner.nextLine();
                
                System.out.print("Database Password: "); database_password = scanner.nextLine();
                
                if(username.equals("-1") || database_username.equals("-1") || database_username.equals("-1")) continue;
                
                Admin.deleteAdmin(username, database_username, database_password);
                
            }
            
            else if(choice == 3) {
                
                while(true) {
                    
                    System.out.print("Username: "); username = scanner.nextLine();
                    
                    if(username.length() > 20) {
                        
                        System.out.println("Error: Username format mismatch."); continue;
                        
                    }
                    
                    System.out.print("Password: "); password = scanner.nextLine();
                    
                    if(password.length() > 40) {
                        
                        System.out.println("Error: Password format mismatch."); continue;
                        
                    }
                    
                    break;
                    
                }
                
                if(username.equals("-1") || password.equals("-1")) continue;
                
                if(Admin.adminLogin(username, password)) {
                    
                    current_username = username;
                    
                    while(true) {
                        
                        System.out.println("1. Donor Portal");
                        System.out.println("2. Receiver Portal");
                        System.out.println("3. Generate Report");
                        System.out.println("4. Change Password");
                        System.out.println("5. Logout");
                        System.out.print("Enter Choice: ");
                        
                        choice = Integer.parseInt(scanner.nextLine());
                        
                        if(choice == 1) {
                            
                            while(true) {
                                
                                System.out.println("1. Add Donor");
                                System.out.println("2. Delete Donor");
                                System.out.println("3. Update Donor");
                                System.out.println("4. Back");
                                System.out.print("Enter Choice: ");
                                
                                choice = Integer.parseInt(scanner.nextLine());
                                
                                if(choice == 1) {
                                    
                                    String donor_name, donor_type;
                                    
                                    while(true) {
                                        
                                        System.out.print("Donor ID: "); donor_id = scanner.nextLine();
                                        
                                        if(donor_id.length() > 10) {
                                            
                                            System.out.println("Error: ID format mismatch."); continue;
                                            
                                        }
                                        
                                        System.out.print("Donor Name: "); donor_name = scanner.nextLine();
                                        
                                        if(donor_name.length() > 50) {
                                            
                                            System.out.println("Error: Name format mismatch."); continue;
                                            
                                        }
                                        
                                        System.out.print("Donor Type: "); donor_type = scanner.nextLine();
                                        
                                        if(donor_type.length() > 3) {
                                            
                                            System.out.println("Error: Type format mismatch."); continue;
                                            
                                        }
                                        
                                        break;
                                        
                                    }
                                    
                                    System.out.print("Amount Donated: "); amount_donated = Double.parseDouble(scanner.nextLine());
                                    
                                    if(donor_id.equals("-1") || donor_name.equals("-1") || donor_type.equals("-1") || amount_donated == -1) continue;
                                    
                                    Donor donor = new Donor(donor_id, donor_name, donor_type, amount_donated, amount_donated);
                                    
                                    donor.addDonor();
                                    
                                }
                                
                                else if(choice == 2) {
                                    
                                    while(true) {
                                        
                                        System.out.print("Donor ID: "); donor_id = scanner.nextLine();
                                        
                                        if(donor_id.length() > 10) {
                                            
                                            System.out.println("Error: ID format mismatch."); continue;
                                            
                                        }
                                        
                                        break;
                                        
                                    }
                                    
                                    if(donor_id.equals("-1")) continue;
                                    
                                    Donor.deleteDonor(donor_id);
                                    
                                }
                                
                                else if(choice == 3) {
                                    
                                    while(true) {
                                        
                                        System.out.print("Donor ID: "); donor_id = scanner.nextLine();
                                        
                                        if(donor_id.length() > 10) {
                                            
                                            System.out.println("Error: ID format mismatch."); continue;
                                            
                                        }
                                        
                                        break;
                                        
                                    }
                                    
                                    System.out.print("Amount Donated: "); amount_donated = Double.parseDouble(scanner.nextLine());
                                    
                                    if(donor_id.equals("-1") || amount_donated == -1) continue;
                                    
                                    Donor.updateDonor(donor_id, amount_donated);
                                    
                                }
                                
                                else if(choice == 4) break;
                                
                                else System.out.println("Error: Invalid choice.");
                                
                            }
                            
                        }
                        
                        else if(choice == 2) {
                            
                            while(true) {
                                
                                System.out.println("1. Add Receiver");
                                System.out.println("2. Delete Receiver");
                                System.out.println("3. Update Receiver");
                                System.out.println("4. Back");
                                System.out.print("Enter Choice: ");
                                
                                choice = Integer.parseInt(scanner.nextLine());
                                
                                if(choice == 1) {
                                    
                                    String receiver_name, receiver_type;
                                    
                                    while(true) {
                                        
                                        System.out.print("Receiver ID: "); receiver_id = scanner.nextLine();
                                        
                                        if(receiver_id.length() > 10) {
                                            
                                            System.out.println("Error: ID format mismatch."); continue;
                                            
                                        }
                                        
                                        System.out.print("Receiver Name: "); receiver_name = scanner.nextLine();
                                        
                                        if(receiver_name.length() > 50) {
                                            
                                            System.out.println("Error: Name format mismatch."); continue;
                                            
                                        }
                                        
                                        System.out.print("Receiver Type: "); receiver_type = scanner.nextLine();
                                        
                                        if(receiver_type.length() > 3) {
                                            
                                            System.out.println("Error: Type format mismatch."); continue;
                                            
                                        }
                                        
                                        break;
                                        
                                    }
                                    
                                    System.out.print("Amount Required: "); Double amount_required = Double.parseDouble(scanner.nextLine());
                                    
                                    if(receiver_id.equals("-1") || receiver_name.equals("-1") || receiver_type.equals("-1") || amount_required == -1) continue;
                                    
                                    Receiver receiver = new Receiver(receiver_id, receiver_name, receiver_type, amount_required);
                                    
                                    receiver.addReceiver();;
                                    
                                }
                                
                                else if(choice == 2) {
                                    
                                    while(true) {
                                        
                                        System.out.print("Receiver ID: "); receiver_id = scanner.nextLine();
                                        
                                        if(receiver_id.length() > 10) {
                                            
                                            System.out.println("Error: ID format mismatch."); continue;
                                            
                                        }
                                        
                                        break;
                                        
                                    }
                                    
                                    if(receiver_id.equals("-1")) continue;
                                    
                                    Receiver.deleteReceiver(receiver_id);
                                    
                                }
                                
                                else if(choice == 3) {
                                    
                                    while(true) {
                                        
                                        System.out.print("Receiver ID: "); receiver_id = scanner.nextLine();
                                        
                                        if(receiver_id.length() > 10) {
                                            
                                            System.out.println("Error: ID format mismatch."); continue;
                                            
                                        }
                                        
                                        System.out.print("Donor ID: "); donor_id = scanner.nextLine();
                                        
                                        if(donor_id.length() > 10) {
                                            
                                            System.out.println("Error: ID format mismatch."); continue;
                                            
                                        }
                                        
                                        break;
                                        
                                    }
                                    
                                    if(receiver_id.equals("-1") || donor_id.equals("-1")) continue;
                                    
                                    Receiver.updateReceiver(receiver_id, donor_id);
                                    
                                }
                                
                                else if(choice == 4) break;
                                
                                else System.out.println("Error: Invalid choice.");
                                
                            }
                            
                        }
                        
                        else if(choice == 3) {
                            
                            Donor.generateReport(); Receiver.generateReport(); Donor.generateReportByType();
                            
                        }
                        
                        else if(choice == 4) {
                            
                            String current_password, new_password;
                            
                            while(true) {
                                
                                System.out.print("Current Password: "); current_password = scanner.nextLine();
                                
                                if(current_password.length() > 40) {
                                    
                                    System.out.println("Error: Password format mismatch."); continue;
                                    
                                }
                                
                                System.out.print("New Password: "); new_password = scanner.nextLine();
                                
                                if(new_password.length() > 40) {
                                    
                                    System.out.println("Error: Password format mismatch."); continue;
                                    
                                }
                                
                                break;
                                
                            }
                            
                            Admin.updatePassword(current_username, current_password, new_password);
                            
                        }
                        
                        else if(choice == 5) break;
                        
                        else System.out.println("Error: Invalid choice.");
                        
                    }
                    
                }
                
            }
            
            else if(choice == 4) break;
            
            else System.out.println("Error: Invalid choice.");
            
        }
        
        System.out.println("Goodbye."); scanner.close();
        
    }
    
}