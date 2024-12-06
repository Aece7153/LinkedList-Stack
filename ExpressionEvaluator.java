import java.util.Scanner;

public class ExpressionEvaluator {

    // Function to check if parentheses are balanced
    public static boolean areParenthesesBalanced(String expression) {
        ArrayStack stack = new ArrayStack(10);
        for (char ch : expression.toCharArray()) {
            if (ch == '(') {
                stack.push(ch);
            } else if (ch == ')') {
                if (stack.isEmpty() || stack.pop() != '(') {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    // Order of operations
    public static int precedence(char operator) {
        switch (operator) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            case '^':
                return 3;
            default:
                return -1;
        }
    }

    // Function to convert infix to postfix
    public static String infixToPostfix(String expression) {
        StringBuilder result = new StringBuilder();
        ArrayStack stack = new ArrayStack(10);

        for (int i = 0; i < expression.length(); i++) {
            char C = expression.charAt(i);

            // If the character is an operand, add it to output
            if (Character.isLetterOrDigit(C)) {
                result.append(C);
            }
            // If the character is '(', push it to stack
            else if (C == '(') {
                stack.push(C);
            }
            // If the character is ')', pop and output until '(' is found
            else if (C == ')') {
                while (!stack.isEmpty() && stack.top() != '(') {
                    result.append(stack.pop());
                }
                if (!stack.isEmpty() && stack.top() != '(')
                    return "Invalid Expression"; // invalid expression
                else
                    stack.pop();
            } else { // an operator is encountered
                while (!stack.isEmpty() && precedence(C) <= precedence((char)stack.top())) {
                    if (stack.top() == '(')
                        return "Invalid Expression";
                    result.append(stack.pop());
                }
                stack.push(C);
            }
        }

        // pop all the operators from the stack
        while (!stack.isEmpty()) {
            if (stack.top() == '(')
                return "Invalid Expression";
            result.append(stack.pop());
        }

        return result.toString();
    }

    // Function to evaluate postfix expression
    public static double evaluatePostfix(String expression, double[] values) {
        ArrayStack stack = new ArrayStack(10);

        for (int i = 0; i < expression.length(); i++) {
            char ch = expression.charAt(i);

            // If the character is an operand, push its corresponding value to the stack
            if (Character.isLetter(ch)) {
                stack.push((int) values[ch - 'A']); // map A->0, B->1, C->2, etc.
            }
            // If the character is an operator, pop two elements and apply the operator
            else {
                double val2 = stack.pop();
                double val1 = stack.pop();
                switch (ch) {
                    case '+':
                        stack.push((int) (val1 + val2));
                        break;
                    case '-':
                        stack.push((int)( val1 - val2));
                        break;
                    case '*':
                        stack.push((int)( val1 - val2));
                        break;
                    case '/':
                        stack.push((int)( val1 - val2));
                        break;
                    case '^':
                        stack.push((int)Math.pow(val1, val2));
                        break;
                }
            }
        }
        return stack.pop();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input the expression
        System.out.println("Enter an infix expression (e.g., (A+B)*C-D^E): ");
        String expression = sc.nextLine();

        // Check if parentheses are balanced
        if (!areParenthesesBalanced(expression)) {
            System.out.println("The parentheses are not balanced.");
            return;
        }

        // Convert infix to postfix
        String postfix = infixToPostfix(expression);
        System.out.println("Postfix Expression: " + postfix);

        // Input values for variables (A, B, C, D, E, etc.)
        double[] values = new double[26]; // to store values for A-Z
        for (char ch = 'A'; ch <= 'Z'; ch++) {
            if (expression.indexOf(ch) != -1) {
                System.out.println("Enter value for " + ch + ": ");
                values[ch - 'A'] = sc.nextDouble();
            }
        }

        // Evaluate postfix expression
        double result = evaluatePostfix(postfix, values);
        System.out.println("The result of the expression is: " + result);
    }
}
