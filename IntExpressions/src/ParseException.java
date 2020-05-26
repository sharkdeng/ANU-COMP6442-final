// ParseException - problem parsing
// Eric McCreath 2018

public class ParseException extends Exception {
    String message;
	public ParseException(String m) {
		message = m;
	}
	@Override
	public String toString() {
		return "Parse Exception : " + message;
	}

}
