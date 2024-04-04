// Hema Shaswar Rasool hs21123 section 3
// Yad Hawar Hiwa yh21145 section 2

import java.util.Scanner;

public class ContactList { //Singley Linked List
    public Scanner input = new Scanner(System.in);
    public Contact head;
    public Contact tail;
    public int size;

    public ContactList(){
        this.head = null;
        this.tail = null;
        this.size = 0;
    };

    //adds new contact to the end of the list if name is not duplicated

    // Time Complexity = O(n) Because it uses findContact
    public void addContact(String name, String number){
        Contact newContact = new Contact(name, number);
        if (this.size == 0) {
            this.head = this.tail = newContact;
            System.out.println("Contact Added Successfully");
            this.size++;
            return;
        } 
        if (findContact(newContact.name) == null) {
            this.tail.next = newContact;
            this.tail = newContact;
            System.out.println("Contact Added Successfully");
            this.size++;
            return;
        }else{
        System.out.println("Contact Name Already exists. Please choose a different name");
    }
    }

    // Time Complexity = O(n) Because it uses findContact
    public void deleteContact(String deleteName) {
        Contact deleteContact = findContact(deleteName);
        if (deleteContact != null) {
            if (deleteContact == head) {
                head = head.next;
                if (head != null)
                    head.prev = null;
            } else if (deleteContact == tail) {
                tail = tail.prev;
                tail.next = null;
            } else {
                deleteContact.prev.next = deleteContact.next;
                deleteContact.next.prev = deleteContact.prev;
            }
            System.out.println("Contact deleted.");
            return;
        }
        System.out.println("Provided Name Does Not Exist in the Contact List");
    }

    // Time Complexity = O(n) Because it uses findContact
    public Contact callContact(String callName){
        Contact callContact = findContact(callName);
        if (callContact != null) {
            System.out.printf("Calling '%s: %s'...%n",callContact.name,callContact.number);
            System.out.printf("You called '%s' successfully.%n", callContact.name);
            return callContact;
        }
        System.out.println("Call failed...\nContact not found.");
        return null;
    };

    // Time Complexity = O(n) Because it uses findContact
    public void modifyContactName(String oldContactName){
        String newName;
        Contact oldContact = findContact(oldContactName);
        if (oldContact != null) {
            System.out.print("Please enter the new name: ");
            newName = input.nextLine();
            if (findContact(newName) == null){
                oldContact.name = newName;
            } else {
                System.out.println("Contact Name Already exists. Please choose a different name");
        }
        } else {
            System.out.println("Please enter a valid name. (Null space not accepted)");
        }
    }

    // Time Complexity = O(n log n) 
    public void displayContactList() {
        this.sort();
        Contact current = this.head;
        char currentGroup = ' '; // Initialize to an arbitrary value
        while (current != null) {
            char firstChar = current.name.charAt(0);
            if (Character.isLetter(firstChar)) {
                firstChar = Character.toUpperCase(firstChar);
                if (firstChar != currentGroup) {
                    // Start of a new group
                    currentGroup = firstChar;
                    System.err.println();
                    System.out.println(currentGroup);
                    System.out.println("_________________");
                    System.out.println();
                }
    
                // Print contacts in the current group
                while (current != null && Character.toUpperCase(current.name.charAt(0)) == currentGroup) {
                    System.out.println(current.name);
                    current = current.next;
                }
                System.out.println("\n");
            } else {
                // Skip non-letter characters
                current = current.next;
            }
        }
    }

    //searches for contact by name
    //returns searched contact or null if not found

    // Time Complexity = O(n) 
    public Contact findContact(String name) {
        Contact currentContact = head;
        while (currentContact != null) {
            if (currentContact.name.equals(name) || currentContact.number.equals(name)) {
                return currentContact;
            }
            currentContact = currentContact.next;
        }
        return null; // Contact not found
    }

    // Time Complexity = O(n log n) 
    private void sort() {
        head = mergeSort(head);
        Contact temp = head;
        while (temp != null && temp.next != null) {
            temp.next.prev = temp;
            temp = temp.next;
        }
        tail = temp;
    }

    // Time Complexity = O(n log n) 
    private Contact mergeSort(Contact head) {

        if (head == null || head.next == null) {
            return head;
        }

        Contact middle = getMiddle(head);
        Contact nextOfMiddle = middle.next;
        middle.next = null;

        Contact left = mergeSort(head);
        Contact right = mergeSort(nextOfMiddle);

        return merge(left, right);
    }

    // Time Complexity = O(log n) 
    private Contact merge(Contact left, Contact right) {

        if (left == null) return right;
        if (right == null) return left;

        Contact result;

        if (left.name.compareToIgnoreCase(right.name) <= 0) {
            result = left;
            result.next = merge(left.next, right);
            if (result.next != null) {
                result.next.prev = result;
            }
        } else {
            result = right;
            result.next = merge(left, right.next);
            if (result.next != null) {
                result.next.prev = result;
            }
        }
        return result;
    }

    // Find the middle Contact of the list

    // Time Complexity = O(n) 
    private Contact getMiddle(Contact head) {
        if (head == null) return head;

        Contact slow = head, fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    
    
}
