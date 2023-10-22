import java.util.ArrayList;

class PriorityQueue2<E> implements PriorityQueueIF<E>{

    private int k;
    private PointSet pointSet;
    private LabelledPoint queryPoint;
    private int tail;
    private ArrayList<LabelledPoint> pointsHeap;

    public PriorityQueue2(int k, PointSet dataSet, LabelledPoint queryPoint) {
        this.k = k;
        this.pointSet = dataSet;
        this.queryPoint = queryPoint;
        this.pointsHeap = new ArrayList<LabelledPoint>();
        this.tail = -1;
    }

    @Override
    public boolean offer(LabelledPoint addPoint) {
        tail++;
        pointsHeap.add(addPoint);
        upHeap(tail);
        return true;
    }

    @Override
    public LabelledPoint poll() {
        if(isEmpty()) return null;
        LabelledPoint ret = pointsHeap.get(0);
        if(tail == 0){
            tail = -1;
            pointsHeap.set(0, null);
            return ret;
        }
        pointsHeap.set(0, pointsHeap.remove(tail));
        tail--;
        downHeap(0);
        return ret;
    }

    @Override
    public LabelledPoint peek() {
        if(isEmpty()) return null;
        return pointsHeap.get(0);
    }

    @Override
    public int size() {
        return this.tail + 1;
    }

    @Override
    public boolean isEmpty() {
        return this.tail < 0;
    }

    @Override
    public ArrayList<LabelledPoint> findKNN() {
        
        ArrayList<LabelledPoint> pointSetList = this.pointSet.getPointsList();
        
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

    private void upHeap(int location){
        if(location == 0) return;
        int parent = parent(location);
        if(Double.compare(pointsHeap.get(parent).getKey(), pointsHeap.get(location).getKey()) > 0){
            swap(location,parent);
            upHeap(parent);
        }             
    }

    /**
    * Algorithm to place element after removal of root and tail element placed at root.
    * O(log(n))
    */
    private void downHeap(int location){
        int left = (location*2) +1;
        int right = (location*2) +2;
        
        //Both children null or out of bound
        if(left > tail) return;
        //left in right out;
        if(left == tail){
             if(Double.compare(pointsHeap.get(location).getKey(), pointsHeap.get(left).getKey()) > 0){
                 swap(location,left);                  
             }
             return;
        }
        
        int toSwap= (Double.compare(pointsHeap.get(left).getKey(), pointsHeap.get(right).getKey()) < 0)? left:right;         
        if(Double.compare(pointsHeap.get(location).getKey(), pointsHeap.get(toSwap).getKey()) > 0){
            swap(location,toSwap);
            downHeap(toSwap);
        }             
   }


    private int parent(int location) {
        return (location-1)/2;
    }

    /**
    * Inplace swap of 2 elements, assumes locations are in array
    * O(1)
    */
    private void swap(int location1, int location2){
        LabelledPoint temp = pointsHeap.get(location1);
        pointsHeap.set(location1, pointsHeap.get(location2));
        pointsHeap.set(location2, temp);
    }

}
