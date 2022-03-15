import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class DoubleEndedQueueTest {
    private DoubleEndedQueue<Integer> queue;

    @BeforeEach
    private void setup() {
         queue = new DoubleLinkedListQueue<>();
    }

    @AfterEach
    private void finish() {
        queue = null;
    }

    // DoubleEndedQueue(DequeNode(1), DequeNode(2), DequeNode(3))
    // append(node) -> peekLast() = node
    @Test
    private void AppendShouldAddNodeAtTheEnd() {
        DequeNode<Integer> node1 = new DequeNode<>(1, null, null);
        queue.append(node1);
        DequeNode<Integer> node2 = new DequeNode<>(2, null, null);
        queue.append(node2);

        DequeNode<Integer> expectedValue = new DequeNode<>(3, null, null);
        DequeNode<Integer> obtainedValue = queue.peekLast();

        queue.append(expectedValue);
        assertEquals(obtainedValue, expectedValue);
    }

    // DoubleEndedQueue(DequeNode(1), DequeNode(2), DequeNode(3))
    // appendLeft(node) -> peekFirst() = node
    @Test
    private void AppendLeftShouldAddNodeAtTheStart() {
        DequeNode<Integer> node1 = new DequeNode<>(1, null, null);
        queue.append(node1);
        DequeNode<Integer> node2 = new DequeNode<>(2, null, null);
        queue.append(node2);

        DequeNode<Integer> expectedValue = new DequeNode<>(3, null, null);
        DequeNode<Integer> obtainedValue = queue.peekFirst();

        queue.appendLeft(expectedValue);
        assertEquals(obtainedValue, expectedValue);
    }

    // DoubleEndedQueue(DequeNode(1), DequeNode(2), DequeNode(3))
    // deleteFirst() -> peekFirst() = DequeNode(2)
    @Test
    private void DeleteFirstShouldDeleteNodeAtTheStart() {
        DequeNode<Integer> node1 = new DequeNode<>(1, null, null);
        queue.append(node1);
        DequeNode<Integer> expectedValue = new DequeNode<>(2, null, null);
        queue.append(expectedValue);
        DequeNode<Integer> node3 = new DequeNode<>(3, null, null);
        queue.append(node3);

        queue.deleteFirst();
        DequeNode<Integer> obtainedValue = queue.peekFirst();

        assertEquals(obtainedValue, expectedValue);
    }

    // DoubleEndedQueue(DequeNode(1), DequeNode(2), DequeNode(3))
    // deleteLast() -> peekLast() = DequeNode(2)
    @Test
    private void DeleteLastShouldDeleteNodeAtTheEnd() {
        DequeNode<Integer> node1 = new DequeNode<>(1, null, null);
        queue.append(node1);
        DequeNode<Integer> expectedValue = new DequeNode<>(2, null, null);
        queue.append(expectedValue);
        DequeNode<Integer> node3 = new DequeNode<>(3, null, null);
        queue.append(node3);

        queue.deleteLast();
        DequeNode<Integer> obtainedValue = queue.peekLast();

        assertEquals(obtainedValue, expectedValue);
    }

    // DoubleEndedQueue(DequeNode(1), DequeNode(2))
    // peekFirst() -> DequeNode(1)
    @Test
    private void peekFirstShouldReturnNodeAtTheStart() {
        DequeNode<Integer> expectedValue = new DequeNode<>(1, null, null);
        queue.append(expectedValue);
        DequeNode<Integer> node2 = new DequeNode<>(2, null, null);
        queue.append(node2);

        DequeNode<Integer> obtainedValue = queue.peekFirst();
        assertEquals(obtainedValue, expectedValue);
    }

    // DoubleEndedQueue(DequeNode(1), DequeNode(2))
    // peekLast() -> DequeNode(2)
    @Test
    private void peekLastShouldReturnNodeAtTheEnd() {
        DequeNode<Integer> node1 = new DequeNode<>(1, null, null);
        queue.append(node1);
        DequeNode<Integer> expectedValue = new DequeNode<>(2, null, null);
        queue.append(expectedValue);

        DequeNode<Integer> obtainedValue = queue.peekLast();
        assertEquals(obtainedValue, expectedValue);
    }

    // DoubleEndedQueue(DequeNode(1), DequeNode(2))
    // peekLast() -> DequeNode(2)
    @Test
    private void sizeOfQueueWithThreeNodesShouldReturnThree() {
        DequeNode<Integer> node1 = new DequeNode<>(1, null, null);
        queue.append(node1);
        DequeNode<Integer> node2 = new DequeNode<>(2, null, null);
        queue.append(node2);
        DequeNode<Integer> node3 = new DequeNode<>(3, null, null);
        queue.append(node3);

        int expectedValue = 3;
        int obtainedValue = queue.size();
        assertEquals(obtainedValue, expectedValue);
    }
}