import java.util.Comparator;
import java.util.NoSuchElementException;

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
        if (position < 0)
            throw new IllegalArgumentException("Index " + position + " does not exist");

        DequeNode<T> result = null;
        if (position < this.size()) {
            DequeNode<T> aux = this.peekFirst();
            int counter = 0;
            while(aux != null && result == null) {
                if (counter == position) {
                    result = aux;
                } else {
                    aux = aux.getNext();
                    counter++;
                }
            }
        }
        return result;
    }

    @Override
    public DequeNode<T> find(T item) {
        if (item == null)
            throw new IllegalArgumentException("Cannot find null nodes on DoubleLinkedListQueue");

        DequeNode<T> aux = this.peekFirst();
        while (aux != null && !item.equals(aux.getItem()))
            aux = aux.getNext();
        return aux;
    }

    @Override
    public void delete(DequeNode<T> node) {
        if (node == null)
            throw new IllegalArgumentException("Cannot delete a null node on DoubleLinkedListQueue");

        DequeNode<T> actual = this.peekFirst();
        DequeNode<T> previous = null;
        while (actual != null && node != actual) {
            previous = actual;
            actual = actual.getNext();
        }
        if (actual != null && previous != null) {
            previous.setNext(actual.getNext());
            actual.setNext(null);
            actual.setPrevious(null);
            this.size--;
        } else if (actual != null) {
            first = null;
            last = null;
            this.size = 0;
        } else {
            throw new NoSuchElementException("Node " + node + " does not exists on the DoubleLinkedListQueue");
        }
    }

    @Override
    public void sort(Comparator<DequeNode<T>> comparator) {
        if (comparator == null)
            throw new IllegalArgumentException("Comparator cannot be null");

        if (this.size() > 0) {
            DoubleLinkedListQueue<T> result = new DoubleLinkedListQueue<>();
            while (this.size() != 0) {
                DequeNode<T> actual = this.peekFirst();
                this.deleteFirst();
                if (result.size() != 0) {
                    if (comparator.compare(actual, result.peekFirst()) > 0) {
                        result.appendLeft(actual);
                    } else {
                        result.append(actual);
                    }
                } else {
                    result.append(actual);
                }
            }
            this.first = result.peekFirst();
            this.last = result.peekLast();
        }
    }
}
