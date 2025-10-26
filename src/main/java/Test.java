import Exception.InvalidExpressionException;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        while (true) {
            Expression expression = new Expression();
            LogicalExpressionSolver solver = new LogicalExpressionSolver();

            Scanner sc = new Scanner(System.in);

            System.out.print("Enter an expression (q to exit): ");
            String exp = sc.nextLine();
            if (exp.equals("q")) {
                break;
            }
            try {
                expression.setRepresentation(exp);
                System.out.println(expression.getRepresentation());
                Boolean result = solver.evaluateExpression(expression);
                System.out.println("Result: " + result.toString());
            } catch (InvalidExpressionException error) {
                System.out.println("Wrong Expression!");
            }
        }
    }
}
