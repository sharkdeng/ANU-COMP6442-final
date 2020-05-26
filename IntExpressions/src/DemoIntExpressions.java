// DemoIntExpressions - run a few basic test of the parser
// Eric McCreath 2018


public class DemoIntExpressions {

	public static void test(String input, String expected, int value) {
		Exp expression;
		try {
			expression = Exp.parse(new Tokenizer(input));
			String expressionAsString = expression.show();
			int val = expression.evaluate();
			System.out.println("String " + input + " parses to " + expressionAsString + " and evaluates to " + val + " " + 
			(expected.equals(expressionAsString) && value == val ? "" : "PROBLEM! expected " + expected + " = " + value ));
		} catch (ParseException e) {
			System.out.println("ParseException : " + e.message);
		}
	}
	
	public static void main(String[] args) {
//		test(" 1  + 1 ","(1+1)",2);
//		test(" 2-1+3 ","((2-1)+3)",4);
//		test(" 1  - 1 ","(1-1)",0);
		test(" (2+1) - (5-2) ","((2+1)-(5-2))",0);
//		test(" (2+1) - 5 -2 ","(((2+1)-5)-2)",-4);
	}
}
