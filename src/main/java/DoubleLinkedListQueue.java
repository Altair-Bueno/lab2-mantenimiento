import java.util.Comparator;

public class DoubleLinkedListQueue<T> implements DoubleEndedQueue<T> {

    private DequeNode<T> first = null;
    private DequeNode<T> last = null;
    private int size = 0;

    public DoubleLinkedListQueue() {}

    @Override
    public void append(DequeNode<T> node) {
        if (node == null) throw new IllegalArgumentException("Argument `node` cannot be null");
        if (node.getPrevious() != null || node.getNext() != null)
            throw new IllegalStateException("The node is already inserted on a list");

        if (size == 0) {
            first = node;
        } else {
            node.setPrevious(last);
            last.setNext(node);
        }

        last = node;
        size++;
    }

    @Override
    public void appendLeft(DequeNode<T> node) {
        if (node == null) throw new IllegalArgumentException("Argument `node` cannot be null");
        if (node.getPrevious() != null || node.getNext() != null)
            throw new IllegalStateException("The node is already inserted on a list");

        if (size == 0) {
            last = node;
        } else {
            node.setNext(first);
            first.setPrevious(node);
        }
        first = node;
        size++;
    }

    @Override
    public void deleteFirst() {
        if (size == 0)
            throw new IllegalStateException("Cannot delete last element on empty queue");

        var deletedNode = first;

        if (size == 1) {
            first = null;
            last = null;
        } else {
            var second = first.getNext();
            second.setPrevious(null);
            first = second;
        }

        deletedNode.setNext(null);
        deletedNode.setPrevious(null);
        size--;
    }

    @Override
    public void deleteLast() {
        if (size == 0)
            throw new IllegalStateException("Cannot delete last element on empty queue");

        var deletedNode = last;

        if (size == 1) {
            first = null;
            last = null;
        } else {
            var nextToLast = last.getPrevious();
            nextToLast.setNext(null);
            last = nextToLast;
        }

        deletedNode.setNext(null);
        deletedNode.setPrevious(null);
        size--;
    }

    @Override
    public DequeNode<T> peekFirst() {
        return first;
    }

    @Override
    public DequeNode<T> peekLast() {
        return last;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public DequeNode<T> getAt(int position) {
        return null;
    }

    @Override
    public DequeNode<T> find(T item) {
        return null;
    }

    @Override
    public void delete(DequeNode<T> node) {

    }

    @Override
    public void sort(Comparator<?> comparator) {

    }
}
