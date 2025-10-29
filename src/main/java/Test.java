import Exception.InvalidExpressionException;

import java.util.HashMap;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.print("Enter an expression (q to exit): ");
            String exp = sc.nextLine();
            if (exp.equals("q")) {
                break;
            }
            try {
                Expression expression = new Expression(exp);
                LogicalExpressionSolver.isValid(expression);
                System.out.println("Postfix: " + expression.getRepresentation());

                HashMap<Character, Boolean> map = getMap(expression.getRepresentation().toCharArray());
                LogicalExpressionSolver solver = new LogicalExpressionSolver(map);

                Boolean result = solver.evaluateExpression(expression);
                System.out.println("Result: " + result.toString());
            } catch (InvalidExpressionException error) {
                System.out.println("Wrong Expression!");
            }
        }
    }

    public static HashMap<Character, Boolean> getMap(char[] expression) {
        HashMap<Character, Boolean> map = new HashMap<>();
        Scanner scan = new Scanner(System.in);

        for (int i = 0; i < expression.length; i++) {
            char curr = expression[i];

            if (Character.isAlphabetic(curr) && !LogicalExpressionSolver.isOperator(curr) && !map.containsKey(curr)) {
                while (true) {
                    System.out.print("Input Value of '" + curr + "' (true/false): ");
                    String value = scan.nextLine();
                    if (value.equals("true")) {
                        map.put(curr, true);
                        break;
                    }
                    else if (value.equals("false")) {
                        map.put(curr, false);
                        break;
                    }
                    System.out.println("Incorrect Value. Please Enter (true/false).");
                }
            }
        }
        return map;
    }
}
