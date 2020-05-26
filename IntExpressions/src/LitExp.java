// LitExp - an integer literal expression
// Eric McCreath 2018


public class LitExp extends Exp {

	int val;
	
	public LitExp(int v) {
		val = v;
	}
	
	@Override
	int evaluate() {
		return val;
	}

	@Override
	String show() {
		return val + "";
	}

}
