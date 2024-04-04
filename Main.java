// Hema Shaswar Rasool hs21123 section 3
// Yad Hawar Hiwa yh21145 section 2

import java.util.Scanner;

public class Main {
    public static void main(String... args) {
        System.out.println("Welcome to the Phone Book App\n");
        ContactList contacts = new ContactList();
        RecentCalls recentCalls = new RecentCalls();
        Scanner input = new Scanner(System.in);
        int choice = -1;
        while (choice != 0) {
            System.out.println("Menu:\nOperations:\n1. Save Contact\n2. Display Contact\n3. Call Contact\n4. Recent Contacts\n5. Delete Contact\n6. Modify Contact\n0. Exit");
            System.out.print("Enter the number corresponding to the operation you want to perform: ");
            choice = input.nextInt();
            switch (choice) {
                case 1:
                    //Time Complexity = O(n)
                    contacts.addContact(getContactName(),getContactNumber());
                    break;
                case 2:
                    //Time Complexity = O(n log n)
                    contacts.displayContactList();
                    break;
                case 3:
                    //Time Complexity = O(n)
                    Contact recent = contacts.callContact(getContactName());
                    recentCalls.addCaller(recent); // adding caller to recent contacts
                    break;
                case 4:
                    //Time Complexity = O(n)
                    recentCalls.displayRecent();
                    break;
                case 5:
                    //Time Complexity = O(n)
                    contacts.deleteContact(getContactName());
                    break;
                case 6:
                    //Time Complexity = O(n)
                    contacts.modifyContactName(getContactName());
                    break;
                case 0:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please select a valid option.");
                    break;
            }
        }
    }

    public static String getContactName(){
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the contact's first name: ");
        String name = input.nextLine();
        return name;
    }

    public static String getContactNumber(){
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the contact's phone number: ");
        String number = input.nextLine();
        return number;
    }
    
    
}
