// Node class representing each element in the singly linked list
class Node {
    int data;
    Node next;

    // Constructor to initialize node with data
    public Node(int data) {
        this.data = data;
        this.next = null;
    }
}

public class StackLinkedList {
    private Node top; // Reference to the top of the stack
    private int size; // To keep track of the stack size

    // Constructor to initialize an empty stack
    public StackLinkedList() {
        this.top = null;
        this.size = 0;
    }

    // Push an element onto the stack
    public void push(int data) {
        Node newNode = new Node(data);
        newNode.next = top; // New node points to the current top
        top = newNode; // Update the top to the new node
        size++;
    }

    // Pop the top element off the stack and return its value
    public int pop() {
        if (isEmpty()) {
            System.out.println("Stack is empty. Cannot pop.");
            return -1;
        }
        int poppedData = top.data;
        top = top.next; // Update the top to the next node
        size--;
        return poppedData;
    }

    // Return the value of the top element without removing it
    public int top() {
        if (isEmpty()) {
            System.out.println("Stack is empty.");
            return -1;
        }
        return top.data;
    }

    // Return the current size of the stack
    public int size() {
        return size;
    }

    // Check if the stack is empty
    public boolean isEmpty() {
        return top == null;
    }

    // Display the elements of the stack
    public void display() {
        if (isEmpty()) {
            System.out.println("Stack is empty.");
            return;
        }
        Node current = top;
        System.out.print("Stack: ");
        while (current != null) {
            System.out.print(current.data + " ");
            current = current.next;
        }
        System.out.println();
    }

    // Make the stack empty by clearing all the elements
    public void makeEmpty() {
        top = null;
        size = 0;
        System.out.println("Stack is now empty.");
    }

    // Main method to test the StackLinkedList class
    public static void main(String[] args) {
        StackLinkedList stack = new StackLinkedList();

        // Test pushing elements onto the stack
        stack.push(10);
        stack.push(20);
        stack.push(30);
        stack.display(); // Output: Stack: 30 20 10

        // Test popping elements from the stack
        System.out.println("Popped: " + stack.pop()); // Output: Popped: 30
        stack.display(); // Output: Stack: 20 10

        // Test the top element
        System.out.println("Top element: " + stack.top()); // Output: Top element: 20

        // Test the size of the stack
        System.out.println("Stack size: " + stack.size()); // Output: Stack size: 2

        // Test isEmpty method
        System.out.println("Is stack empty? " + stack.isEmpty()); // Output: Is stack empty? false

        // Test makeEmpty method
        stack.makeEmpty(); // Output: Stack is now empty.
        stack.display(); // Output: Stack is empty.
    }
}
