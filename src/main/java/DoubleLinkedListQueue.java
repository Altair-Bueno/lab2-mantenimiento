public class DoubleLinkedListQueue<T> implements DoubleEndedQueue<T> {

    private DequeNode<T> first = null;
    private DequeNode<T> last = null;
    private int size = 0;

    public DoubleLinkedListQueue() {}

    @Override
    public void append(DequeNode<T> node) {
        appendNode(last,node);
    }

    @Override
    public void appendLeft(DequeNode<T> node) {
        appendNode(first,node);
    }

    @Override
    public void deleteFirst() {
        deleteNode(first);
    }

    @Override
    public void deleteLast() {
        deleteNode(last);
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

    private void deleteNode(DequeNode<T> node) {
        if (size == 0)
            throw new IllegalStateException("Cannot delete last element on empty queue");
        if (node == null)
            throw new IllegalArgumentException("Argument `node` cannot be null");
        if (!this.contains(node))
            throw new IllegalArgumentException("The node is not contained on this list");

        if (size == 1) {
            first = null;
            last = null;
        } else {
            // size > 1
            var previous = node.getPrevious();
            var next = node.getNext();

            if (previous != null) previous.setNext(next);
            if (next != null) next.setPrevious(previous);

            node.setPrevious(null);
            node.setNext(null);
        }
        size--;
    }

    private boolean contains(DequeNode<T> node) {
        if (first == null) return false;

        var iterable = first;
        while (!first.isLastNode()) {
            if(iterable.equals(node)) return true;
            iterable = iterable.getNext();
        }

        return false;
    }

    private void appendNode(DequeNode<T> list, DequeNode<T> node) {
        if (node == null) throw new IllegalArgumentException("Argument `node` cannot be null");
        if (list == null) throw new IllegalArgumentException("Argument `list` cannot be null");
        if (node.getPrevious() != null || node.getNext() != null)
            throw new IllegalStateException("The node is already inserted on a list");

        var next = list.getNext();

        list.setNext(node);
        node.setPrevious(list);
        node.setNext(next);
        if (next != null) next.setPrevious(node);

        size++;
    }
}
