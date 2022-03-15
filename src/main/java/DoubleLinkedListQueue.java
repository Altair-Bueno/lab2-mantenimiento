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

   