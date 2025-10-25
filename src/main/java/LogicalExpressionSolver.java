import Interface.ExpressionInterface;
import Interface.LogicalExpressionSolverInterface;
import Exception.InvalidExpressionException;

import java.util.*;


public class LogicalExpressionSolver implements LogicalExpressionSolverInterface {
    private Character[] operators = {'>', 'v', '^', '~'};
    private Map<Character, Boolean> map;

    private void getMap(char[] expression) {
        map = new HashMap<>();

        for (int i = 0; i < expression.length; i++) {
            char curr = expression[i];

            if (!isOperator(curr) && !map.containsKey(curr)) {
                while (true) {
                    Scanner scan = new Scanner(System.in);
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
    }

    public boolean evaluateExpression(ExpressionInterface expression) throws InvalidExpressionException {
        char[] postfix = expression.getRepresentation().toCharArray();
        Stack<Boolean> stack = new Stack<>();
        getMap(postfix);

        try {
            for (int i = 0; i < postfix.length; i++) {
                char curr = postfix[i];

                if (isOperator(curr)) {
                    if (curr == '~') {
                        Boolean operand = stack.pop();
                        stack.push(!operand);
                    } else {
                        Boolean right = stack.pop(), left = stack.pop();
                        switch (curr) {
                            case '^':
                                stack.push(left && right);
                                break;
                            case 'v':
                                stack.push(left || right);
                                break;
                            case '>':
                                stack.push(!left || right);
                                break;
                        }
                    }
                } else {
                    stack.push(map.get(curr));
                }
            }
            Boolean result = stack.pop();
            return result;
        } catch (EmptyStackException error) {
            throw new InvalidExpressionException("Invalid Expression");
        }
    };

    private boolean isOperator(Character c) {
        return Arrays.asList(operators).contains(c);
    }
}
