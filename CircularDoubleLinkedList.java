public class CircularDoubleLinkedList {
    // Node class representing each element
    private class Node {
        String courseID;
        String courseName;

        // Pointers to sides of Nodes
        Node next;
        Node prev;

        Node(String courseID, String courseName) {
            this.courseID = courseID;
            this.courseName = courseName;
            this.next = null;
            this.prev = null;
        }
    }
    private Node head;
    private Node tail;


    // Constructor to initialize an empty list
    public CircularDoubleLinkedList() {
        head = null;
        tail = null;
    }

    // Method to check if the list is empty
    private boolean isEmpty() {
        return head == null;
    }

    // Display nodes forward
    public void forwardDisplay() {
        if (isEmpty()) {
            System.out.println("List is empty.");
            return;
        }
        // Start at head and traverse through list printing courseID and courseNames
        Node current = head;
        do {
            System.out.println("Course ID: " + current.courseID + ", Course Name: " + current.courseName);
            current = current.next;
        } while (current != head);
    }

    // Display nodes backward
    public void backwardDisplay() {
        if (isEmpty()) {
            System.out.println("List is empty.");
            return;
        }
        // Start at tail and traverse through list backwards printing courseID and courseNames
        Node current = tail;
        do {
            System.out.println("Course ID: " + current.courseID + ", Course Name: " + current.courseName);
            current = current.prev;
        } while (current != tail);
    }

    // Add node at the start of the list
    public void addFirst(String courseID, String courseName) {
        Node newNode = new Node(courseID, courseName);
        if (isEmpty()) {
            // Node will point to itself in both directions
            head = tail = newNode;
            head.next = head;
            head.prev = head;
        } else {
            newNode.next = head;
            newNode.prev = tail;
            head.prev = newNode;
            tail.next = newNode;
            head = newNode;
        }
    }

    // Add node at the end of the list
    public void addLast(String courseID, String courseName) {
        if (isEmpty()) {
            addFirst(courseID, courseName);
        } else {
            Node newNode = new Node(courseID, courseName);
            newNode.next = head;
            newNode.prev = tail;
            tail.next = newNode;
            head.prev = newNode;
            tail = newNode;
        }
    }

    // Add node at a specific position
    public void addAtPos(int pos, String courseID, String courseName) {
        if (pos <= 0) {
            System.out.println("Invalid position.");
            return;
        }
        // Same as addFirst method
        if (pos == 1) {
            addFirst(courseID, courseName);
        } else {
            Node current = head;
            int count = 1;
            while (count < pos - 1 && current.next != head) {
                current = current.next;
                count++;
            }
            // When we reach desired position
            if (count == pos - 1) {
                Node newNode = new Node(courseID, courseName);
                newNode.next = current.next;
                newNode.prev = current;
                current.next.prev = newNode;
                current.next = newNode;
            } else {
                System.out.println("Position out of bounds.");
            }
        }
    }

    // Delete the first node in the list
    public void deleteFirst() {
        if (isEmpty()) {
            System.out.println("List is empty.");
        }
        else {
            head = head.next;
            head.prev = tail;
            tail.next = head;
        }
    }

    // Delete the last node in the list
    public void deleteLast() {
        if (isEmpty()) {
            System.out.println("List is empty.");
        } else {
            tail = tail.prev;
            tail.next = head;
            head.prev = tail;
        }
    }

    // Delete a node with a specific course ID
    public void deleteNode(String courseID) {
        if (isEmpty()) {
            System.out.println("List is empty.");
            return;
        }
        Node current = head;
        do {
            if (current.courseID.equals(courseID)) {
                // Same as delete first
                if (current == head) {
                    deleteFirst();
                    // Same as delete last
                } else if (current == tail) {
                    deleteLast();
                    // Delete found Node
                } else {
                    current.prev.next = current.next;
                    current.next.prev = current.prev;
                }
                System.out.println("Node with Course ID: " + courseID + " deleted.");
                return;
            }
            // Go to next Node
            current = current.next;
            // If we come back to the head Node
        } while (current != head);
        System.out.println("Course ID: " + courseID + " not found.");
    }

    // Find a node with a specific course ID
    public Node findNode(String courseID) {
        if (isEmpty()) {
            System.out.println("List is empty.");
            return null;
        }
        Node current = head;
        do {
            if (current.courseID.equals(courseID)) {
                return current;
            }
            current = current.next;
        } while (current != head);
        System.out.println("Course ID: " + courseID + " not found.");
        return null;
    }

    // Method to find and display courses with duplicate course IDs
    public void findDuplicate() {
        if (isEmpty()) {
            System.out.println("List is empty.");
            return;
        }

        Node current = head;
        boolean foundDuplicate = false;

        System.out.println("Duplicate Course's:");
        do {
            Node checker = current.next;
            boolean isDuplicate = false;

            // Check for duplicates by comparing with all Nodes
            do {

                // If Checker Node is equal to Current Node
                if (current.courseID.equals(checker.courseID) || current.courseName.equals((checker.courseName))) {
                    isDuplicate = true;
                    foundDuplicate = true;
                    // We found a duplicate
                    break;
                }

                // If Checker and Current are not the same
                // Checker Node moves through list, while Current Node stays the same
                checker = checker.next;
            }

            // While Checker is not equal to Head Node
            // AKA we have not gone through the entire list yet
            while (checker != head);
                // If out isDuplicate boolean was turned TRUE
                if (isDuplicate) {

                    // Print Duplicate
                    System.out.println("Course ID: " + current.courseID + ", Course Name: " + current.courseName);
                }
                // Move the Current Node through list
                current = current.next;

            // While Current is not equal to Head Node
        }   while (current != head);
                if (!foundDuplicate) {
                    System.out.println("No duplicate course IDs found.");
        }
    }
}



