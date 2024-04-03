public class Contact {
    public String name;
    public String number;
    public Contact next;
    public Contact prev; 

    public Contact(String name, String number){
        this.name = name;
        this.number = number;
    }
    
}