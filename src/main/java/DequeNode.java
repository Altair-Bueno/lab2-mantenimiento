/**
 * Class representing a node of a double-ended queue (deque). Each node has pointers to
 * the next and previous nodes.
 * The previous and next of the first and last node of the deque is null.
 *
 * @param <T>
 */
public class DequeNode<T> {
    private T item ;
    private DequeNode<T> next ;
    private DequeNode<T> previous ;

    public DequeNode(T item, DequeNode<T> next, DequeNode<T> previous) {
        this.item = item;
        this.next = next;
        this.previous = previous;
    }

    public T getItem() {
        return item;
    }

    public DequeNode<T> getNext() {
        return next;
    }

    void setNext(DequeNode<T> next) {
        if (next == this)
            throw new IllegalArgumentException("This node and the next one must be different");
        this.next = next;
    }

    public boolean isFirstNode() {
        return previous == null ;
    }

    public boolean isLastNode() {
        return next == null ;
    }

    public boolean isNotATerminalNode() {
        return (!isFirstNode() && !isLastNode()) ;
    }

    public DequeNode<T> getPrevious() {
        return previous;
    }

    void setPrevious(DequeNode<T> previous) {
        if (previous == this)
            throw new IllegalArgumentException("This node and the previous one must be different");
        this.previous = previous;
    }
}