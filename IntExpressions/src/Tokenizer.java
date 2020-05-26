import java.util.ArrayList;

// Tokenizer - A simple tokenizer for integer expressions.  Noting this tokenizer can traverse over the tokens in either direction.
// So it includes "next" and "previous" methods.  This is a little unusual. 
// Eric McCreath 2018


public class Tokenizer {
	static final char whitespace[] = { ' ', '\n', '\t' };
	static final char symbol[] = { '(', ')', '+', '-' };

	private ArrayList<Token> tokens;  // used for storing the tokens
	private int position; // the current position with the token stream


	public Tokenizer(String input) { // this processes all the input tokens and stores them in "tokens"
		tokens = new ArrayList<Token>();
		position = 0;

		int inputpos = 0;

		while (inputpos < input.length()) {
			inputpos = consumewhite(input, inputpos);
			if (inputpos < input.length()) {
				if (isin(input.charAt(inputpos),symbol)) {
					tokens.add(new SymbolToken(input.charAt(inputpos)));
					inputpos++;
				} else if (Character.isDigit(input.charAt(inputpos))) {
					int dstart = inputpos;
					inputpos++;
					while (inputpos < input.length() && Character.isDigit(input.charAt(inputpos))) inputpos++;
					tokens.add(new NumberToken(Integer.parseInt(input.substring(dstart, inputpos))));
					
				} else {
					throw new Error();
				}
			}
		}
	}
	
	public boolean done() {
		return tokens.size() == position || position == -1;
	}
	
	public Token current() {
		return (position < tokens.size() ? tokens.get(position) : null);
	}
	
	public void next() {
		if (position < tokens.size()) position++;
	}
	
	public void previous() {
		if (position >= 0) position--;
	}
	
	public void gotoEnd() {
		position = tokens.size()-1;
	}
	
	private int consumewhite(String input, int inputpos) {
		int pos = inputpos;
		while (pos < input.length() && isin(input.charAt(pos), whitespace))
			pos++;
		return pos;
	}

	private boolean isin(char c, char[] chars) {
		for (char a : chars) if (c == a) return true;
		return false;
	}
}
