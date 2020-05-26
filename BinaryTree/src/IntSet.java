import java.util.List;

// IntSet - an integer set abstract class
// Eric McCreath 2018

public abstract class IntSet {
    abstract Integer max();
    abstract boolean isEmpty();
    abstract boolean isIn(int value);
    abstract List<Integer> elements();
    abstract IntSet insert(int value);
    abstract IntSet delete(int value);
}
