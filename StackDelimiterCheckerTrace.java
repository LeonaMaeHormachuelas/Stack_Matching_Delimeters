/* Description: Modified version of the delimiter checker. It checks whether (), [],
   and {} are properly matched and nested using a stack, and it prints a trace of every
   stack operation (PUSH, PEEK, POP), the delimiter involved, the current contents of the
   stack, and the reason when the sequence is invalid.
   
   Programmed by: [Leona Mae G. Hormachuelas] [BSIT] [48079] [CC104]
   Last Modified: October 11, 2026
   Version: 1.1
   Acknowledgements: Claude Sonnet 5.5 (Anthropic) - Fixing code, finalizing my grammar to my answers
   Abdul Bari and Neso Academy".
   Prompt: "can you make it cleaner" "correct my grammar" */
import java.util.Scanner;
import java.util.Stack;

public class StackDelimiterCheckerTrace {

    static boolean isOpening(char c) {
        return c == '(' || c == '[' || c == '{';
    }

    static boolean isClosing(char c) {
        return c == ')' || c == ']' || c == '}';
    }

    static boolean matches(char open, char close) {
        return (open == '(' && close == ')')
            || (open == '[' && close == ']')
            || (open == '{' && close == '}');
    }

    /** Formats the stack bottom-to-top, e.g. "{ [ (" ; "(empty)" when empty. */
    static String show(Stack<Character> stack) {
        if (stack.isEmpty()) return "(empty)";
        StringBuilder sb = new StringBuilder();
        for (char c : stack) sb.append(c).append(' ');
        return sb.toString().trim();
    }

    static String check(String input) {
        Stack<Character> stack = new Stack<>();
        System.out.println("Input: " + (input.isEmpty() ? "(empty)" : input));
        System.out.println("--------------------------------------------");

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);

            if (isOpening(c)) {
                System.out.println("Read: " + c);
                stack.push(c);
                System.out.println("Operation: PUSH " + c);
                System.out.println("Stack: " + show(stack));
            } else if (isClosing(c)) {
                System.out.println("Read: " + c);
                if (stack.isEmpty()) {
                    System.out.println("Operation: isEmpty -> true");
                    System.out.println("Stack: " + show(stack));
                    System.out.println("Reason: closing delimiter '" + c + "' has no matching opening delimiter");
                    return "Invalid";
                }
                char top = stack.peek();
                System.out.println("Operation: PEEK -> " + top);
                if (matches(top, c)) {
                    stack.pop();
                    System.out.println("Operation: POP " + top + "  ('" + top + "' matches '" + c + "')");
                    System.out.println("Stack: " + show(stack));
                } else {
                    System.out.println("Stack: " + show(stack));
                    System.out.println("Reason: mismatched delimiters - '" + c
                        + "' does not match the top of the stack '" + top + "'");
                    return "Invalid";
                }
            } else {
                System.out.println("Read: " + c + "  (not a delimiter, ignored)");
            }
        }

        System.out.println("End of input. isEmpty -> " + stack.isEmpty());
        System.out.println("Stack: " + show(stack));
        if (!stack.isEmpty()) {
            System.out.println("Reason: unmatched opening delimiter remains: '" + stack.peek() + "'");
            return "Invalid";
        }
        return "Valid";
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Matching Delimiters Checker with Stack Trace  (type 'exit' to quit)");
        while (true) {
            System.out.print("Enter sequence: ");
            if (!sc.hasNextLine()) break;
            String input = sc.nextLine();
            if (input.trim().equalsIgnoreCase("exit")) break;
            System.out.println();
            String result = check(input);
            System.out.println("--------------------------------------------");
            System.out.println("Result: " + result);
            System.out.println();
        }
        sc.close();
    }
}
