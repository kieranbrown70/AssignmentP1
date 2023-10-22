import java.util.ArrayList;

class PriorityQueue1<E> implements PriorityQueueIF<E>{

    private int k;
    private PointSet pointSet;
    private LabelledPoint queryPoint;
    private int arraySize;
    private ArrayList<LabelledPoint> pointsArray;

    public PriorityQueue1(int k, PointSet dataSet, LabelledPoint queryPoint) {
        this.k = k;
        this.pointSet = dataSet;
        this.queryPoint = queryPoint;
        this.pointsArray = new ArrayList<LabelledPoint>();
        this.arraySize = 0;
    }

    @Override
    public boolean offer(LabelledPoint addPoint) {
        for (int i = 0; i <= this.arraySize; i++){
            if (i == this.arraySize){
                pointsArray.add(addPoint);
                arraySize++;
                return true;
            }
            if (addPoint.getKey() < pointsArray.get(i).getKey()){
                pointsArray.add(i, addPoint);
                arraySize++;
                return true;
            }
        }
        return false;
    }

    @Override
    public LabelledPoint poll() {
        if (pointsArray.isEmpty()){
            return null;
        }
        this.arraySize--;
        return pointsArray.remove(0);
    }

    @Override
    public LabelledPoint peek() {
        if (pointsArray.isEmpty()){
            return null;
        }

        return pointsArray.get(0);
    }

    @Override
    public int size() {
        return arraySize;
    }

    @Override
    public boolean isEmpty() {
        return pointsArray.isEmpty();
    }

    @Override
    public ArrayList<LabelledPoint> findKNN() {
        
        ArrayList<LabelledPoint> pointSetList = this.pointSet.getPointsList();
        
        for (int i = 0; i < pointSetList.size(); i++) {
            double distance = pointSetList.get(i).distanceTo(this.queryPoint);
            pointSetList.get(i).setKey(distance);
            offer(pointSetList.get(i));
        }

        ArrayList<LabelledPoint> KNNList = new ArrayList<LabelledPoint>();

        for (int i = 0; i < this.k; i++){
            KNNList.add((LabelledPoint) poll());
        }

        return KNNList;
    }
    
}
