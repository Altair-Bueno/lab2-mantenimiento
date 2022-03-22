import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Comparator;
import java.util.NoSuchElementException;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class DoubleEndedQueueTest {
    DoubleEndedQueue<Integer> queue;
    private final Comparator<DequeNode<Integer>> comparator = Comparator.comparingInt(DequeNode::getItem);

    static Stream<DequeNode<Integer>> nodeOnListProvider() {
        return Stream.of(
                new DequeNode<>(1,new DequeNode<>(2,null,null),null),
                new DequeNode<>(3, null ,new DequeNode<>(4,null,null)),
                new DequeNode<>(5,new DequeNode<>(6,null,null), new DequeNode<>(7,null,null))
        );
    }

    @BeforeEach
    void setup() {
         queue = new DoubleLinkedListQueue<>();
    }

    @AfterEach
    void finish() {
        queue = null;
    }

    // DoubleEndedQueue(DequeNode(1), DequeNode(2), DequeNode(3))
    // append(node) -> peekLast() = node
    @Test
    void AppendShouldAddNodeAtTheEnd() {
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
    void AppendLeftShouldAddNodeAtTheStart() {
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
    void DeleteFirstShouldDeleteNodeAtTheStart() {
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
    void DeleteLastShouldDeleteNodeAtTheEnd() {
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
    void peekFirstShouldReturnNodeAtTheStart() {
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
    void peekLastShouldReturnNodeAtTheEnd() {
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
    void sizeOfQueueWithThreeNodesShouldReturnThree() {
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

    // DoubleEndedQueue(DequeNode(1))
    // peekLast() -> DequeNode(1)
    @Test
    void appendLeftOnEmptyQueueMakesItemTheLast() {
        var expectedValue = new DequeNode<>(1,null,null);
        queue.appendLeft(expectedValue);
        var obtainedValue = queue.peekLast();

        assertEquals(expectedValue, obtainedValue);
    }

    // append(null) -> IllegalArgumentException
    @Test
    void appendNullNodeThrowsException() {
        Executable lambda = ()->queue.append(null);
        assertThrows(IllegalArgumentException.class,lambda);
    }

    // appendLeft(null) -> IllegalArgumentException
    @Test
    void appendLeftNullNodeThrowsException() {
        Executable lambda = ()->queue.appendLeft(null);
        assertThrows(IllegalArgumentException.class,lambda);
    }

    // DoubleEndedQueue(DequeNode(1))
    // append(node) -> IllegalArgumentException
    // appendLeft(node) -> IllegalArgumentException
    @Test
    void appendAnElementTwiceThrowsException() {
        var node = new DequeNode<>(1,null,null);
        var exceptionClass = IllegalArgumentException.class;
        queue.append(node);

        assertAll(
                ()->assertThrows(exceptionClass,()->queue.append(node)),
                ()->assertThrows(exceptionClass,()->queue.appendLeft(node))
        );
    }

    // DoubleEndedQueue()
    // deleteFirst() -> IllegalStateException
    @Test
    void deleteFirstOnEmptyQueueThrowsException() {
        Executable lambda = ()-> queue.deleteFirst();
        assertThrows(IllegalStateException.class,lambda);
    }

    // DoubleEndedQueue()
    // deleteLast() -> IllegalStateException
    @Test
    void deleteLastOnEmptyQueueThrowsException() {
        Executable lambda = ()-> queue.deleteLast();
        assertThrows(IllegalStateException.class,lambda);
    }

    // DoubleEndedQueue()
    // size() -> 0
   @Test
   void sizeOfEmptyQueueShouldReturnZero() {
        assertEquals(0,queue.size());
   }

    // DoubleEndedQueue(DequeNode(1),DequeNode(2),DequeNode(3))
    // deleteLast() -> deleteLast() -> deleteLast() -> size() = 0
    @Test
    void sizeOfQueueThatHadAllItsElementRemovedShouldReturnZero() {
        var node1 = new DequeNode<>(1,null,null);
        var node2 = new DequeNode<>(2,null,null);
        var node3 = new DequeNode<>(3,null,null);

        queue.append(node1);
        queue.append(node2);
        queue.append(node3);
        queue.deleteLast();
        queue.deleteLast();
        queue.deleteLast();

        assertEquals(0,queue.size());
    }

    // DoubleEndedQueue(DequeNode(1),DequeNode(2),DequeNode(3))
    // peekFirst() -> deleteFirst() -> first.getNext() = null
    // peekFirst() -> deleteFirst() -> first.getPrevious() = null
    @Test
    void removedFirstElementDoesNotHavePreviousNorNextNode() {
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

    // DoubleEndedQueue(node) and DoubleEndedQueue(queue)
    // queue.append(note) -> IllegalStateException
    @ParameterizedTest
    @MethodSource("nodeOnListProvider")
    void appendNodeFromAnotherQueueThrowsException(DequeNode<Integer> node) {
        Executable lambda = ()->queue.append(node);
        assertThrows(IllegalStateException.class,lambda);
    }

    // DoubleEndedQueue(node) and DoubleEndedQueue(queue)
    // queue.appendLeft(note) -> IllegalStateException
    @ParameterizedTest
    @MethodSource("nodeOnListProvider")
    void appendLeftNodeFromAnotherQueueThrowsException(DequeNode<Integer> node) {
        Executable lambda = ()->queue.appendLeft(node);
        assertThrows(IllegalStateException.class,lambda);
    }
    // DoubleEndedQueue()
    // queue.getAt(-1) -> null
    @Test
    void getAtNegativeIndex() {
        var index = -1;

        assertThrows(IllegalArgumentException.class, () -> queue.getAt(index));
    }

    // DoubleEndedQueue()
    // queue.getAt(index) -> null
    @ParameterizedTest
    @ValueSource(ints = { 0, 1, 2, 3, 4, 5, 50 })
    void getAtWithEmptyList(int index) {
        var obtainedValue = queue.getAt(index);

        assertNull(obtainedValue);
    }

    // DoubleEndedQueue(DequeNode(1),DequeNode(2),DequeNode(3))
    // queue.peekFirst() == queue.getAt(0)
    @Test
    void getAtFirstEqualsPeekFirst() {
        queue.append(new DequeNode<>(1,null,null));
        queue.append(new DequeNode<>(2,null,null));
        queue.append(new DequeNode<>(3,null,null));

        var expected = queue.peekFirst();
        var obtained = queue.getAt(0);

        assertEquals(expected,obtained);
    }

    // DoubleEndedQueue(DequeNode(1),DequeNode(2),DequeNode(3))
    // queue.peekFirst() == queue.getAt(2)
    @Test
    void getAtLastEqualsPeekLast() {
        queue.append(new DequeNode<>(1,null,null));
        queue.append(new DequeNode<>(2,null,null));
        queue.append(new DequeNode<>(3,null,null));

        var expected = queue.peekLast();
        var obtained = queue.getAt(2);

        assertEquals(expected,obtained);
    }

    // DoubleEndedQueue(DequeNode(1),DequeNode(2),DequeNode(3))
    // queue.getAt(0) == DequeNode(1)
    // queue.getAt(1) == DequeNode(2)
    // queue.getAt(2) == DequeNode(3)
    @Test
    void getAtInOrder() {
        var first = new DequeNode<>(1,null,null);
        var second = new DequeNode<>(2,null,null);
        var third = new DequeNode<>(3,null,null);

        queue.append(first);
        queue.append(second);
        queue.append(third);

        assertAll(
                ()-> {
                    var obtained = queue.getAt(0);
                    assertEquals(first,obtained);
                },
                ()-> {
                    var obtained = queue.getAt(1);
                    assertEquals(second,obtained);
                },
                ()-> {
                    var obtained = queue.getAt(2);
                    assertEquals(third,obtained);
                }
        );
    }

    // DoubleEndedQueue()
    // queue.delete(null) -> IllegalArgumentException
    @Test
    void deleteWithNullNode() {
        var expectedException = IllegalArgumentException.class;
        Executable lambda = () -> queue.delete(null);

        assertThrows(expectedException,lambda);
    }

    // DoubleEndedQueue(DequeNode(1),DequeNode(2)), DequeNode(3)
    // queue.delete(DequeNode(3)) -> NoSuchElementException
    @ParameterizedTest
    @MethodSource("nodeOnListProvider")
    void deleteNodeFromOtherList (DequeNode<Integer> node) {
        queue.append(new DequeNode<>(1,null,null));
        queue.append(new DequeNode<>(2,null,null));

        var expectedException = NoSuchElementException.class;
        Executable lambda = ()-> queue.delete(node);

        assertThrows(expectedException,lambda);
    }

    // DoubleEndedQueue(DequeNode(1))
    // queue.delete(DequeNode(1)) -> queue.size == 0
    @Test
    void deleteRemovesElement() {
        var one = 1;
        var first = new DequeNode<>(one,null,null);
        queue.append(first);

        queue.delete(first);

        assertAll(
                ()-> {
                    var obtained = queue.find(one);

                    assertNull(obtained);
                },
                ()->{
                    var obtained = queue.size();
                    var expected = 0;

                    assertEquals(expected,obtained);
                }
        );
    }

    // DoubleEndedQueue(DequeNode(1),DequeNode(2),DequeNode(3))
    // queue.delete(DequeNode(2)) -> queue.size == 2
    @Test
    void deleteMiddleElement() {
        var first = new DequeNode<>(1,null,null);
        var second = new DequeNode<>(2,null,null);
        var third = new DequeNode<>(3,null,null);
        queue.append(first);
        queue.append(second);
        queue.append(third);

        queue.delete(second);
        var expected = 2;
        var obtained = queue.size();

        assertEquals(expected,obtained);
    }

    // DoubleEndedQueue(DequeNode(1),DequeNode(2),DequeNode(3))
    // queue.delete(DequeNode(2)) -> node2.next == null and node2.previous == null
    @Test
    void deleteShouldSetPreviousAndNextToNull() {
        var first = new DequeNode<>(1,null,null);
        var second = new DequeNode<>(2,null,null);
        var third = new DequeNode<>(3,null,null);
        queue.append(first);
        queue.append(second);
        queue.append(third);

        queue.delete(second);

        assertAll(
                ()->{
                    var obtained = second.getPrevious();
                    assertNull(obtained);
                },
                ()->{
                    var obtained = second.getNext();
                    assertNull(obtained);
                }
        );
    }

    // DoubleEndedQueue(DequeNode(1))
    // find(1) -> DequeNode(1)
    @Test
    void findShouldReturnTheNodeWithTheGivenItem(){
        DequeNode<Integer> expectedValue = new DequeNode<>(1, null, null);
        queue.append(expectedValue);
        DequeNode<Integer> obtainedValue=queue.find(1);
        assertEquals(expectedValue,obtainedValue);
    }

    // DoubleEndedQueue()
    // find(1) -> null
    @Test
    void findOfAnItemThatIsNotInTheQueueShouldReturnNull(){
        assertNull(queue.find(1));
    }

    // DoubleEndedQueue()
    // find(null) -> IllegalArgumentException
    @Test
    void findANullShouldRaiseAnException(){
        Executable lambda = ()-> queue.find(null);
        assertThrows(IllegalArgumentException.class,lambda);
    }

    // DoubleEndedQueue(DequeNode(2),DequeNode(3),DequeNode(1))
    // sort(comparator) -> DoubleEndedQueue(DequeNode(1),DequeNode(2),DequeNode(3))
    @Test
    void sortOfADisorderedQueueShouldOrderIt(){
        DequeNode<Integer> node1 = new DequeNode<>(2, null, null);
        DequeNode<Integer> node2 = new DequeNode<>(3, null, null);
        DequeNode<Integer> node3 = new DequeNode<>(1, null, null);
        queue.append(node1);
        queue.append(node2);
        queue.append(node3);

        queue.sort(comparator);
        assertEquals(node2,queue.peekFirst());
        assertEquals(node3,queue.peekLast());
    }

    // DoubleEndedQueue(DequeNode(1),DequeNode(2),DequeNode(3))
    // sort(comparator) -> DoubleEndedQueue(DequeNode(1),DequeNode(2),DequeNode(3))
    @Test
    void sortOfAnOrderedQueueShouldDoNothing(){
        DequeNode<Integer> node1 = new DequeNode<>(1, null, null);
        DequeNode<Integer> node2 = new DequeNode<>(2, null, null);
        DequeNode<Integer> node3 = new DequeNode<>(3, null, null);
        queue.append(node1);
        queue.append(node2);
        queue.append(node3);

        queue.sort(comparator);
        assertEquals(node3,queue.peekFirst());
        assertEquals(node1,queue.peekLast());
    }

    // DoubleEndedQueue()
    // sort(comparator) -> size() = 0
    @Test
    void sortOfAnEmptyQueueShouldDoNothing(){
        queue.sort(comparator);
        assertEquals(0,queue.size());
    }

    // DoubleEndedQueue()
    // sort(null) -> IllegalArgumentException
    @Test
    void sortShouldRaiseAnExceptionIfTheParameterIsNull(){
        Executable lambda = ()->queue.sort(null);
        assertThrows(IllegalArgumentException.class,lambda);
    }
}