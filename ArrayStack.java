public class ArrayStack {
    private int[] S;
    private int top = -1;
    int UB;

    public ArrayStack(int capacity){
        S = new int[capacity];
        UB = capacity-1;
    }

    // Remove int from top value
    public int pop(){
        S[top] = 0;
        // Decrement top value
        top-=1;
        return top;
    }
    // Add int to top value
    public void push(int value){
        // Increment top value
        top+=1;
        S[top] = value;
    }

    // Return the top value as int
    public int top(){
        return S[top];
    }

    public int size(){
        return top+1;
    }


    // Check if stack is empty
    public boolean isEmpty(){
        if(top == -1){
            return true;
        }
        else return false;
    }
    // Check if stack is full
    public boolean isFull(){
        if (top == UB){
            return true;
        }
        else return false;
    }

    // Make stack empty
    public void makeEmpty(){
        while(top != -1){
            pop();
        }
    }



    // Display the stacked list
    public void display(){
        for(int i = 0;i<S.length;i++){
            System.out.print(S[i] + " ");
            System.out.println();
            }
        }

    }


class main{
    public static void main(String[] args){
        ArrayStack S1 = new ArrayStack(5);

        S1.push(10);
        S1.push(20);
        S1.display();
        System.out.println("Top value is: "+S1.top());
        System.out.println("Size of stack is: "+S1.size());
        System.out.println("Is the stack full: "+S1.isFull());


        S1.push(30);
        S1.push(40);
        S1.push(50);

        S1.display();
        System.out.println("Top value is: "+S1.top());
        System.out.println("Size of stack is: "+S1.size());
        System.out.println("Is the stack full: "+S1.isFull());


        S1.makeEmpty();
        S1.display();
        System.out.println("Is the stack Empty: "+S1.isEmpty());


    }
}
