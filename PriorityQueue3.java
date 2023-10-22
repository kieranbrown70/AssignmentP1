import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

class PriorityQueue3<E> implements PriorityQueueIF<E>{

    private int k;
    private PointSet pointSet;
    private LabelledPoint queryPoint;
    private int size;
    private PriorityQueue<LabelledPoint> pointsPQ;

    public PriorityQueue3(int k, PointSet dataSet, LabelledPoint queryPoint) {
        this.k = k;
        this.pointSet = dataSet;
        this.queryPoint = queryPoint;
        this.size = 0;
    }

    @Override
    public boolean offer(LabelledPoint addPoint) {
        pointsPQ.add(addPoint);
        this.size++;
        return true;
    }

    @Override
    public LabelledPoint poll() {
        this.size--;
        return pointsPQ.poll();
    }

    @Override
    public LabelledPoint peek() {
        return pointsPQ.peek();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return pointsPQ.isEmpty();
    }

    @Override
    public ArrayList<LabelledPoint> findKNN() {
        
        ArrayList<LabelledPoint> pointSetList = this.pointSet.getPointsList();
        int arraySize = pointSetList.size();
        this.pointsPQ = new PriorityQueue<LabelledPoint>(arraySize, new PointComparator());

        for (int i = 0; i < pointSetList.size(); i++){
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

class PointComparator implements Comparator<LabelledPoint>{

    @Override
    public int compare(LabelledPoint o1, LabelledPoint o2) {
        if (o1.getKey() > o2.getKey()){
            return 1;
        } else if (o1.getKey() < o2.getKey()) {
            return -1;
        }
        return 0;
    }
    
}
