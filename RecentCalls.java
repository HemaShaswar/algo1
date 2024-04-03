public class RecentCalls { //stack
    Contact [] callStack;
    int top;
    int size;

    RecentCalls(){
        this.size = 15;
        callStack = new Contact [this.size];
        top = -1;
    }

    public void addCaller(Contact caller){ //push();
        if(isFull()){
            popBottom();
        }
        callStack[++top] = caller;
    }

    // Method to pop an element from the stack
    private void popBottom() { // pop(); remove outdated call. (older than 10 past calls)
        if (isEmpty()) {
            System.out.println("Error: Stack is empty.");
            return;
        }
        for (int i = 0; i < top; i++) {
            callStack[i] = callStack[i + 1];
        }
        top--;
    }


    public void displayRecent() {
        System.out.println("List of recent calls:");
        if (top == -1) {
            System.out.println("No calls were made");
            return;
        }
        for (int i = top; i >= 0; i--) {
            System.out.println(callStack[i].name + " was called at number: " + callStack[i].number);
        }
    }
    
    private boolean isFull(){
        return top >= size-1;
    }
    
    private boolean isEmpty(){
        return top < 0;
    }

}
