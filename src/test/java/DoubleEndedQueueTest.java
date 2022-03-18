import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class DoubleEndedQueueTest {
    public DoubleEndedQueue<Integer> queue;

    @BeforeEach
    public void setup() {
         queue = new DoubleLinkedListQueue<>();
    }

    @AfterEach
    public void finish() {
        queue = null;
    }

    // DoubleEndedQueue(DequeNode(1), DequeNode(2), DequeNode(3))
    // append(node) -> peekLast() = node
    @Test
    public void AppendShouldAddNodeAtTheEnd() {
        DequeNode<Integer> node1 = new DequeNode<>(1, null, null);
        DequeNode<Integer> node2 = new DequeNode<>(2, null, null);
        DequeNode<Integer> expectedValue = new DequeNode<>(3, null, null);

        queue.append(node1);
        queue.append(node2);
        queue.append(expectedValue);
        DequeNode<Integer> obtainedValue = queue.peekLast();

        assertEquals(obtainedValue, expectedValue);
    }

    // DoubleEndedQueue(DequeNode(1), DequeNode(2), DequeNode(3))
    // appendLeft(node) -> peekFirst() = node
    @Test
    public void AppendLeftShouldAddNodeAtTheStart() {
        DequeNode<Integer> node1 = new DequeNode<>(1, null, null);
        DequeNode<Integer> node2 = new DequeNode<>(2, null, null);
        DequeNode<Integer> expectedValue = new DequeNode<>(3, null, null);

        queue.append(node1);
        queue.append(node2);
        queue.appendLeft(expectedValue);
        DequeNode<Integer> obtainedValue = queue.peekFirst();

        assertEquals(obtainedValue, expectedValue);
    }

    // DoubleEndedQueue(DequeNode(1), DequeNode(2), DequeNode(3))
    // deleteFirst() -> peekFirst() = DequeNode(2)
    @Test
    public void DeleteFirstShouldDeleteNodeAtTheStart() {
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
    public void DeleteLastShouldDeleteNodeAtTheEnd() {
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
    public void peekFirstShouldReturnNodeAtTheStart() {
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
    public void peekLastShouldReturnNodeAtTheEnd() {
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
    public void sizeOfQueueWithThreeNodesShouldReturnThree() {
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

    @Test
    public void appendNullNodeThrowsException() {
        Executable lambda = ()->queue.append(null);
        assertThrows(IllegalArgumentException.class,lambda);
    }

    @Test
    public void appendLeftNullNodeThrowsException() {
        Executable lambda = ()->queue.appendLeft(null);
        assertThrows(IllegalArgumentException.class,lambda);
    }

    @Test
    public void appendAnElementTwiceThrowsException() {
        var node = new DequeNode<>(1,null,null);
        var exceptionClass = IllegalArgumentException.class;
        queue.append(node);

        assertAll(
                ()->assertThrows(exceptionClass,()->queue.append(node)),
                ()->assertThrows(exceptionClass,()->queue.appendLeft(node))
        );
    }

    @Test
    public void deleteFirstOnEmptyQueueThrowsException() {
        Executable lambda = ()-> queue.deleteFirst();
        assertThrows(IllegalStateException.class,lambda);
    }

    @Test
    public void deleteLastOnEmptyQueueThrowsException() {
        Executable lambda = ()-> queue.deleteLast();
        assertThrows(IllegalStateException.class,lambda);
    }

   @Test
   public void sizeOfEmptyQueueShouldReturnZero() {
        assertEquals(queue.size(),0);
   }

    @Test
    public void sizeOfQueueThatHadAllItsElementRemovedShouldReturnZero() {
        var node1 = new DequeNode<>(1,null,null);
        var node2 = new DequeNode<>(2,null,null);
        var node3 = new DequeNode<>(3,null,null);

        queue.append(node1);
        queue.append(node2);
        queue.append(node3);
        queue.deleteLast();
        queue.deleteLast();
        queue.deleteLast();

        assertEquals(queue.size(),0);
    }

    @Test
    public void removedFirstElementDoesNotHavePreviousNorNextNode() {
        var node1 = new DequeNode<>(1,null,null);
        var node2 = new DequeNode<>(2,null,null);
        var node3 = new DequeNode<>(3,null,null);
        queue.append(node1);
        queue.append(node2);
        queue.append(node3);

        while (queue.size() != 0) {
            var first = queue.peekFirst();
            queue.deleteFirst();

            assertNull(first.getNext());
            assertNull(first.getPrevious());
        }
    }

    public static Stream<DequeNode<Integer>> nodeOnListProvider() {
        return Stream.of(
                new DequeNode<>(1,new DequeNode<>(2,null,null),null),
                new DequeNode<>(3, null ,new DequeNode<>(4,null,null)),
                new DequeNode<>(5,new DequeNode<>(6,null,null), new DequeNode<>(7,null,null))
        );
    }

    @ParameterizedTest
    @MethodSource("nodeOnListProvider")
    public void appendNodeFromAnotherQueueThrowsException(DequeNode<Integer> node) {
        Executable lambda = ()->queue.append(node);
        assertThrows(IllegalStateException.class,lambda);
    }

    @ParameterizedTest
    @MethodSource("nodeOnListProvider")
    public void appendLeftNodeFromAnotherQueueThrowsException(DequeNode<Integer> node) {
        Executable lambda = ()->queue.appendLeft(node);
        assertThrows(IllegalStateException.class,lambda);
    }
}