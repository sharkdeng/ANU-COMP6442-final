// DemoIntExpressions - run a few basic test of the parser
// Eric McCreath 2018


public class Fulltests {

	public static int test(String input, String expected, int value, StringBuffer sb) {
		Exp expression;
		try {
			expression = Exp.parse(new Tokenizer(input));
			String expressionAsString = expression.show();
			int val = expression.evaluate();
			sb.append("String " + input + " parses to " + expressionAsString + " and evaluates to " + val + " " + 
			(expected.equals(expressionAsString) && value == val ? "\n" : "PROBLEM! expected " + expected + " = " + value + "\n"));
			return (expected.equals(expressionAsString) && value == val ? 1 : 0);
		} catch (ParseException e) {
			sb.append("ParseException : " + e.message + "\n");
			return 0;
		} catch (Error e) {
			sb.append("Error : " + e.toString() + "\n");
			return 0;
		}
	}
	
	public static void main(String[] args) {
		int mark = 0;
		StringBuffer sb = new StringBuffer();
		
		mark += test(" 2  + 3 ","(2+3)",5,sb);
		mark += test(" 3  - 2 ","(3-2)",1,sb);
		mark += test(" 2-1+3 ","((2-1)+3)",4,sb);
		mark += test(" 1  - 1 ","(1-1)",0,sb);
		mark += test(" (2+1) - (5-2) ","((2+1)-(5-2))",0,sb);
		mark += test(" (2+1) - 5 -2 ","(((2+1)-5)-2)",-4,sb);
		mark += test("1-1-1-1-1", "((((1-1)-1)-1)-1)",-3,sb);
		mark += test("(11-1) - (4+6) - (4-1)", "(((11-1)-(4+6))-(4-1))",-3,sb);
		mark += test("9+1-1+1", "(((9+1)-1)+1)",10,sb);
		mark += test("(13-2-1) - (13-2-1) - (4-5)", "((((13-2)-1)-((13-2)-1))-(4-5))",1,sb);
		
		
		System.out.println(mark);
		System.out.println("-------------------");
		System.out.println(sb.toString());
		
		
		
	}
}
