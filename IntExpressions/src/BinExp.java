// BinExp - a binary expression,  in this case either + or -
// Eric McCreath 2018


public class BinExp extends Exp {
    char operator;
    Exp e1, e2;
	
    public BinExp(Exp e1, char operator, Exp e2) {
    	this.e1 = e1;
    	this.e2 = e2;
    	this.operator = operator;
    }
	
	@Override
	int evaluate() {
	    if (operator=='-') {
		return e1.evaluate() - e2.evaluate();
	    } else  if (operator=='+') {
		return e1.evaluate() + e2.evaluate();
	    } else {
	    	throw new Error();
	    }
	}

	@Override
	String show() {
		return "(" + e1.show() + operator + e2.show() + ")";
	}

}
