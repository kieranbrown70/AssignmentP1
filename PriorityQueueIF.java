import java.util.ArrayList;

public interface PriorityQueueIF<E> {

    // Inserts the specified element into this queue if it is possible to do so immediately 
	// without violating capacity restrictions.
    boolean offer(LabelledPoint addPoint);

    // Retrieves and removes the head of this queue, or returns null if this queue is empty.
    LabelledPoint poll();

    // Retrieves, but does not remove, the head of this queue, or returns null if this queue is empty.
    LabelledPoint peek();

    // Returns the number of elements in this queue.
    int size();

    // Returns true if this queue contains no elements.
    boolean isEmpty();

    ArrayList<LabelledPoint> findKNN();

}
