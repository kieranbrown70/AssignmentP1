import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class KNN {
    
    private int k;
    private PointSet dataSet;
    private PointSet querySet;

    public KNN (String[] args){
        setK(Integer.parseInt(args[1]));
        dataSet = new PointSet(PointSet.read_ANN_SIFT(args[2]));
        querySet = new PointSet(PointSet.read_ANN_SIFT(args[3]));
    }

    public void setK(int kValue){
        this.k = kValue;
    }

    public int getK(){
        return this.k;
    }

    public PointSet getDataSet(){
        return this.dataSet;
    }
    
    public PointSet getQuerySet(){
        return this.querySet;
    }


    public static void main(String[] args) throws IOException {
        
        int versionNum = Integer.parseInt(args[0]);
        
        // This is done to allow the variables to be set with a setter method (as instructed for setK)
        KNN KNNTest = new KNN(args);

        ArrayList<LabelledPoint> queryPoints = new ArrayList<>(KNNTest.getQuerySet().getPointsList());

        ArrayList<ArrayList<LabelledPoint>> results = new ArrayList<ArrayList<LabelledPoint>>();

        long startTime = System.currentTimeMillis();

        switch(versionNum){
            case 1:
                for (int i = 0; i < queryPoints.size(); i++){
                    PriorityQueueIF<LabelledPoint> PQ = new PriorityQueue1<>(((int)KNNTest.getK()), 
                    ((PointSet)KNNTest.getDataSet()), queryPoints.get(i));
                    ArrayList<LabelledPoint> currentResult = new ArrayList<LabelledPoint>(PQ.findKNN());
                    results.add(currentResult);
                }
                break;
            case 2:
                for (int i = 0; i < queryPoints.size(); i++){
                    PriorityQueueIF<LabelledPoint> PQ = new PriorityQueue2<>(((int)KNNTest.getK()), 
                    ((PointSet)KNNTest.getDataSet()), queryPoints.get(i));
                    ArrayList<LabelledPoint> currentResult = new ArrayList<LabelledPoint>(PQ.findKNN());
                    results.add(currentResult);
                }
                break;
            case 3:
                for (int i = 0; i < queryPoints.size(); i++){
                    PriorityQueueIF<LabelledPoint> PQ = new PriorityQueue3<>(((int)KNNTest.getK()), 
                    ((PointSet)KNNTest.getDataSet()), queryPoints.get(i));
                    ArrayList<LabelledPoint> currentResult = new ArrayList<LabelledPoint>(PQ.findKNN());
                    results.add(currentResult);
                }
                break;
        }

        long endTime = System.currentTimeMillis(); 
        System.out.println("Execution time: " + (endTime - startTime) + " milliseconds");

        String fileName = Integer.toString(versionNum) + "_" + Integer.toString(KNNTest.getK()) + "_1000_1000000";

        try {
            File newFile = new File(fileName);
            newFile.createNewFile();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        try{
            FileWriter fileWriter = new FileWriter(fileName);
            for (int i = 0; i < results.size(); i++){
                ArrayList<LabelledPoint> currentResult = new ArrayList<LabelledPoint>(results.get(i));
                String outString = i + ": ";
                for (int j = 0; j < currentResult.size(); j++){
                    if (j != 0){
                        outString += ", ";
                    }
                    outString += currentResult.get(j).getLabel();
                }
                System.out.println(outString);
                fileWriter.write(outString);
            }
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    
    }   
}
