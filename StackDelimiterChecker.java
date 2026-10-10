/* Description: Checks whether the delimiters (), [], and {} in a user-entered
   sequence are properly matched and nested. It uses a stack (LIFO): opening
   delimiters are pushed, and each closing delimiter is checked against the top
   of the stack using isEmpty, peek, and pop. Other characters are ignored.
   
   Programmed by: [Leona Mae G. Hormachuelas] [BSIT] [48079] [CC104]
   Last Modified: October 11, 2026
   Version: 1.0
   Acknowledgements: Claude Sonnet 5.5 (Anthropic) - Fixing code, finalizing my grammar to my answers.
   Prompt: "Fix the code and make me understand it more on where is my problem here" "correct my grammar" */

import java.util.Scanner;
import java.util.Stack;

public class StackDelimiterChecker {

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

    /** Returns "Valid" or "Invalid - <reason>". */
    static String check(String input) {
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);

            if (isOpening(c)) {
                stack.push(c);
            } else if (isClosing(c)) {
                if (stack.isEmpty()) {
                    return "Invalid - closing delimiter '" + c + "' at position " + (i + 1)
                         + " has no matching opening delimiter";
                }
                char top = stack.peek();
                if (matches(top, c)) {
                    stack.pop();
                } else {
                    return "Invalid - mismatched delimiters: '" + c + "' at position " + (i + 1)
                         + " does not match the top of the stack '" + top + "'";
                }
            }
        }

        if (!stack.isEmpty()) {
            return "Invalid - unmatched opening delimiter remains: '" + stack.peek() + "'";
        }
        return "Valid";
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Matching Delimiters Checker  (type 'exit' to quit; empty line = empty input)");
        while (true) {
            System.out.print("Enter sequence: ");
            if (!sc.hasNextLine()) break;
            String input = sc.nextLine();
            if (input.trim().equalsIgnoreCase("exit")) break;
            System.out.println("Result: " + check(input));
            System.out.println();
        }
        sc.close();
    }
}
