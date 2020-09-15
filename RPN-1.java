import java.util.*;

/**
 * This function takes a string in reverse polish notation and calculates the
 * product
 * 
 * @author BrennanAyers
 *
 */
public class RPN {

	/**
	 * main method - contains call to the testing method
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		testRPN();
	}

	/**
	 * Tests the RPN evaluator - Do not modify this in any way
	 */
	public static void testRPN() {
		String[] tests = { "2 2 +", "2 3 -", "4 5 *", "6 5 /",
				"1 2 3 4 5 6 7 8 9 + + + + + + + +", "5 2 2 * - 1 2 + /" };

		double[] results = { 4.0, -1, 20, 1.2, 45, 0.3333333333333333 };

		for (int i = 0; i < tests.length; i++) {
			System.out.println(String.format("Evaluating => %s", tests[i]));
			double result = evaluateRPN(tests[i]);
			System.out.println(String.format("Result => %s", result));
			if (result != results[i]) {
				System.out.println(String.format(
						"Error on test %s expected %s, received %s", tests[i],
						results[i], evaluateRPN(tests[i])));
				return;
			}
		}
		System.out.println("Congratulations - you passed the tests");

	}

	/**
	 * This method parses and evaluates a string of values in RPN notation
	 * 
	 * @param input - a string of values in RPN notation
	 * @return last double value in the stack to be printed after calculation
	 */
	public static double evaluateRPN(String input) {
		// Create queue, transfer input into it "2 3 +" -> [2 3 +]
		Queue<String> holdQueue = new LinkedList<String>();

		// Create scanner
		Scanner qScan = new Scanner(input);

		// Scan through
		while (qScan.hasNext()) {
			holdQueue.add(qScan.next());
		}

		// Print holdQueue(input)
		System.out.println(holdQueue);

		// Create new empty stack
		Stack<String> holdStack = new Stack<String>();

		// Pop off each item in the queue and evaluate it
		while (!holdQueue.isEmpty()) {

			String nextOne = holdQueue.remove();

			if (isOperator(nextOne)) {
				// pop two operands, evaluate, push result: Queue [+] Stack [2
				// 3] => [][5]
				Double secondOperand = Double.parseDouble(holdStack.pop());
				Double firstOperand = Double.parseDouble(holdStack.pop());

				// call evaluator
				holdStack.push(String.valueOf(evaluateBinaryOperator(
						firstOperand, nextOne, secondOperand)));

				// print queue and stack
				System.out.print(holdQueue);
				System.out.println(holdStack);
			}
			// else operands such as "5" just need to be pushed [3 +][2]=>[+][2
			// 3]
			else {
				holdStack.push((nextOne));
			}

		}
		// return last item in stack [][5]
		return Double.parseDouble(holdStack.pop());
	}

	/**
	 * Performs testing to determine if passed in string is an operator or not
	 * 
	 * @param input which is to be determined whether is an operator
	 * @return boolean whether input is operator or not
	 */
	private static boolean isOperator(String input) {
		// if input == any of the 4 operators, return true
		return "+-*/".indexOf(input) >= 0;

	}

	/**
	 * Evaluates the expression passed in via 3 parameters, 2 operands and an
	 * operator
	 * 
	 * evaluateBinaryOperator(2, "+", 3)=> "5"
	 * 
	 * @param op1,      first operand
	 * @param op2,      second operand
	 * @param operator, operator to process operands
	 * @return double computation of the two operands with the operator
	 */
	private static double evaluateBinaryOperator(Double op1, String operator,
			Double op2) {
		// Individual if/else statements for possible operators, return op1
		// <operator> op2
		if (operator.contains("+")) {
			return (op1 + op2);
		}

		else if (operator.contains("-")) {
			return (op1 - op2);
		}

		else if (operator.contains("*")) {
			return (op1 * op2);
		}

		else {
			return (op1 / op2);
		}

	}

}
