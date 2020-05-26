
// Token - a single token
// Eric McCreath 2018

public abstract class Token {
	public boolean isTheSymbol(char sym) {
		return this instanceof SymbolToken && ((SymbolToken) this).symbol == sym;
	}
	public boolean isSymbol() {
		return this instanceof SymbolToken;
	}
	public boolean isNumber() {
		return this instanceof NumberToken;
	}
	public int number() {
		return ((NumberToken) this).value;
	}
}
