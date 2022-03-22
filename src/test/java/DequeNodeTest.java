import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DequeNodeTest {

    private DequeNode<Integer> dequeNode;

    @AfterEach
    void finish(){
        dequeNode=null;
    }

    // getItem() -> 1
    @Test
    void getItemReturnsTheItemAttributeValue() {
        int expectedValue = 1;
        dequeNode = new DequeNode<>(expectedValue,null,null);

        int obtainedValue = dequeNode.getItem();

        assertEquals(expectedValue,obtainedValue);
    }

    // getNext() -> DequeNode(2)
    @Test
    void getNextReturnsTheNextNode(){
        DequeNode<Integer> expectedValue = new DequeNode<>(2,null,null);
        dequeNode = new DequeNode<>(1,expectedValue,null);
        DequeNode<Integer> obtainedValue= dequeNode.getNext();

        assertEquals(expectedValue,obtainedValue);
    }

    // getNext() -> null
    @Test
    void getNextReturnsNullIfNodeHasNoNextNode(){
        dequeNode = new DequeNode<>(1,null,null);
        DequeNode<Integer> obtainedValue= dequeNode.getNext();

        assertNull(obtainedValue);
    }

    // getPrevious() -> DequeNode(1)
    @Test
    void getPreviousReturnsThePreviousNode(){
        DequeNode<Integer> expectedValue = new DequeNode<>(1,null,null);;
        dequeNode = new DequeNode<>(2,null,expectedValue);
        DequeNode<Integer> obtainedValue= dequeNode.getPrevious();

        assertEquals(expectedValue,obtainedValue);
    }

    // getPrevious() -> null
    @Test
    void getPreviousReturnsNullIfNodeHasNoPreviousNode(){
        dequeNode = new DequeNode<>(1,null,null);
        DequeNode<Integer> obtainedValue= dequeNode.getPrevious();

        assertNull(obtainedValue);
    }

    // setNext() valid
    @Test
    void setNextModifiesTheNextNode(){
        DequeNode<Integer> expectedValue=  new DequeNode<>(2,null,null);
        dequeNode = new DequeNode<>(1,null,null);
        dequeNode.setNext(expectedValue);

        DequeNode<Integer> obtainedValue = dequeNode.getNext();

        assertEquals(expectedValue,obtainedValue);
    }

    // setNext() throws IllegalArgumentException
    @Test
    void setNextOfItselfThrowsAnException(){
        dequeNode = new DequeNode<>(1,null,null);

        assertThrows(IllegalArgumentException.class,()->dequeNode.setNext(dequeNode));
    }

    // setPrevious() valid
    @Test
    void setPreviousModifiesThePreviousNode(){
        DequeNode<Integer> expectedValue=  new DequeNode<>(1,null,null);
        dequeNode = new DequeNode<>(2,null,null);
        dequeNode.setPrevious(expectedValue);

        DequeNode<Integer> obtainedValue = dequeNode.getPrevious();

        assertEquals(expectedValue,obtainedValue);
    }

    // setPrevious() throws IllegalArgumentException
    @Test
    void setPreviousOfItselfThrowsExceptions(){
        dequeNode = new DequeNode<>(1,null,null);

        assertThrows(IllegalArgumentException.class,()->dequeNode.setPrevious(dequeNode));
    }

    // isFirstNode() -> true
    @Test
    void isFirstNodeReturnsTrueIfPreviousAttributeIsNull(){
        dequeNode = new DequeNode<>(2,null,null);
        boolean expectedValue = true;
        boolean obtainedValue = dequeNode.isFirstNode();

        assertEquals(expectedValue,obtainedValue);
    }

    // isFirstNode() -> false
    @Test
    void isFirstNodeReturnsFalseIfPreviousAttributeIsNotNull(){
        DequeNode<Integer> previous = new DequeNode<>(1,null,null);
        dequeNode = new DequeNode<>(2,null,previous);
        boolean expectedValue = false;
        boolean obtainedValue = dequeNode.isFirstNode();

        assertEquals(expectedValue,obtainedValue);
    }

    // isLastNode() -> true
    @Test
    void isLastNodeReturnsTrueIfNextAttributeIsNull(){
        dequeNode = new DequeNode<>(2,null,null);
        boolean expectedValue = true;
        boolean obtainedValue = dequeNode.isLastNode();

        assertEquals(expectedValue,obtainedValue);
    }

    // isLastNode() -> false
    @Test
    void isLastNodeReturnsFalseIfNextAttributeIsNotNull(){
        DequeNode<Integer> next = new DequeNode<>(2,null,null);
        dequeNode = new DequeNode<>(1,next,null);
        boolean expectedValue = false;
        boolean obtainedValue = dequeNode.isLastNode();

        assertEquals(expectedValue,obtainedValue);
    }

    // isNotATerminalNode() -> false
    @Test
    void isNotATerminalNodeReturnsFalseIfNextAndPreviousAttributesAreNull(){
        dequeNode = new DequeNode<>(1,null,null);
        boolean expectedValue = false;
        boolean obtainedValue = dequeNode.isNotATerminalNode();

        assertEquals(expectedValue,obtainedValue);
    }


    // isNotATerminalNode() -> true
    @Test
    void isNotATerminalNodeReturnsTrueIfNextAttributeIsNotNull() {
        boolean expectedValue = false;
        DequeNode<Integer> next = new DequeNode<>(3, null, null);
        dequeNode = new DequeNode<>(2, next, null);
        boolean obtainedValue = dequeNode.isNotATerminalNode();

        assertEquals(expectedValue, obtainedValue);
    }

    // isNotATerminalNode() -> true
    @Test
    void isNotATerminalNodeReturnsTrueIfPreviousAttributeIsNotNull() {
        boolean expectedValue = false;
        DequeNode<Integer> previous = new DequeNode<>(1, null, null);
        dequeNode = new DequeNode<>(2, null, previous);
        boolean obtainedValue = dequeNode.isNotATerminalNode();

        assertEquals(expectedValue, obtainedValue);
    }

    // isNotATerminalNode() -> true
    @Test
    void isNotATerminalNodeReturnsTrueIfPreviousAndNextAttributesAreNotNull(){
        boolean expectedValue = true;
        DequeNode<Integer> next = new DequeNode<>(3, null, null);
        DequeNode<Integer> previous = new DequeNode<>(1, null, null);
        dequeNode = new DequeNode<>(2, next, previous);
        boolean obtainedValue = dequeNode.isNotATerminalNode();

        assertEquals(expectedValue, obtainedValue);
    }
}