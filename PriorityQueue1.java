import java.util.ArrayList;

class PriorityQueue1<E> implements PriorityQueueIF<E>{

    private int numOfNeighbors = 0;
    private PointSet pointSet;
    private int arraySize;
    private ArrayList<LabelledPoint> pointsArray;

    public PriorityQueue1(ArrayList<LabelledPoint> newPointsArray, int size) {
        this.pointsArray = new ArrayList<>(newPointsArray);
        this.arraySize = size;
    }

    @Override
    public boolean offer(E e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'offer'");
    }

    @Override
    public E poll() {
        if (pointsArray.isEmpty()){
            return null;
        }
        
        throw new UnsupportedOperationException("Unimplemented method 'poll'");
    }

    @Override
    public E peek() {
        if (pointsArray.isEmpty()){
            return null;
        }

        throw new UnsupportedOperationException("Unimplemented method 'peek'");
    }

    @Override
    public int size() {
        return arraySize;
    }

    @Override
    public boolean isEmpty() {
        return pointsArray.isEmpty();
    }
    
}
