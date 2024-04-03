
public class ContactList { //Singley Linked List
    public Contact head;
    public Contact tail;
    public RecentCalls recentCalls;
    public int size;

    public ContactList(){
        this.head = null;
        this.tail = null;
        this.size = 0;
        this.recentCalls = new RecentCalls();
    };

    //adds new contact to the end of the list if name is not duplicated
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
        }
        System.out.println("Contact Name Already exists. Please choose a different name");
    };

    public void deleteContact(String deleteName){
        Contact deleteContact = findContact(deleteName);
        if (deleteContact != null){
            if (deleteContact == this.tail) {
                deleteContact.prev.next = null;
                this.tail = deleteContact.prev;
                deleteContact.prev = null;
                this.size--;
                return;
            }
            if (deleteContact == this.head) {
                deleteContact.next.prev = null;
                this.head = deleteContact.next;
                deleteContact.next = null;
                this.size--;
                return;
            }
            deleteContact.prev.next = deleteContact.next;
            deleteContact.next.prev = deleteContact.prev;
            deleteContact.next = deleteContact.prev = null;
            this.size--;
            return;
        };

    };

    public void callContact(String callName){
        Contact callContact = findContact(callName);

        if (callContact != null) {
            System.out.println("Calling " + callContact.name + " on the number: " + callContact.number + "...");
            this.recentCalls.addCaller(callContact);
            return;
        };
        System.out.println("Contact Name Not Found");
    };

    public void recentCalls(){
        this.recentCalls.display();
    }

    public void modifyContactName(String oldContactName,String newName){
        Contact oldContact = findContact(newName);
        if (oldContact != null) {
            oldContact.number = newName;
        };
    }

    // public void modifyContactNumber(String oldContactName, String newNumber){
    //     Contact oldContact = findContact(oldContactName);
    //     if (oldContact != null) {
    //         oldContact.number = newNumber;
    //     };
    // }

    public void displayContactList(){
        this.sort();
        Contact current = this.head;
        char currentGroup = ' '; // Initialize to an arbitrary value
        while (current != null) {
        char firstChar = Character.toUpperCase(current.name.charAt(0));
        if (firstChar != currentGroup) {
            // Start of a new group
            currentGroup = firstChar;
            System.out.println(currentGroup);
            System.out.println("_________________");
            System.out.println();
        }

        // Print contacts in the current group
        while (current != null && Character.toUpperCase(current.name.charAt(0)) == currentGroup) {
            System.out.println(current.name);
            current = current.next;
        }
        System.out.println("-----------");
    }
    }

    //searches for contact by name
    //returns searched contact or null if not found 
    private Contact findContact(String findName){
        Contact currentContact = this.head;

        for (int i = 0; i < this.size; i++) {

            if (currentContact.name == findName) {
                return currentContact;
            };

            currentContact = currentContact.next;

        };

        return null;
    };

    private void sort() {
        head = mergeSort(head);
        Contact temp = head;
        while (temp != null && temp.next != null) {
            temp.next.prev = temp;
            temp = temp.next;
        }
        tail = temp;
    }

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
