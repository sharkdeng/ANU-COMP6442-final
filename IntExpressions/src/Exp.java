
// Exp - a simple integer mathematical expression
// Eric McCreath 2018

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public abstract class Exp {
	abstract int evaluate(); // calculate the value of the expression
	abstract String show(); // show an expression
	
	
	// In your solution you may only modify the static methods parse, parseExp, and parseTerm.
	// You may not modify the signatures of these methods.
	// You may not modify any other classes or methods. 
	// You may not add any fields or methods to this abstract class. 
	
	public static Exp parse(Tokenizer t) throws ParseException {
		// add code in this method for your solution
		t.gotoEnd();
		Exp exp = parseExp(t);
		return exp;
	}

	private static Exp parseExp(Tokenizer t) throws ParseException {
		// add code in this method for your solution
		Exp term = parseTerm(t); // number
		t.previous();

		if (!t.done() && t.current().isTheSymbol('+')) {
			t.previous();
			BinExp bin = new BinExp(parseExp(t), '+', term);
			return bin;

		} else if (!t.done() && t.current().isTheSymbol('-')) {
			t.previous();
			BinExp bin = new BinExp(parseExp(t), '-', term);
			return bin;
		}

		return term;

	}

	private static Exp parseTerm(Tokenizer t) throws ParseException {
		// add code in this method for your solution

		if (t.current().isNumber()){
			return new LitExp(t.current().number());

		} else if (t.current().isTheSymbol(')')) {
			// skip this;
			t.previous();
			return parseExp(t);

		} else {
			throw new ParseException("Not supported!");
		}

	}

}
