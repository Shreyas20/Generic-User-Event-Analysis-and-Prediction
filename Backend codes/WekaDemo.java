import java.io.BufferedReader;
import java.io.FileReader;

import weka.classifiers.Classifier;
import weka.classifiers.trees.J48;
import weka.core.Instances;

public class WekaDemo {

    public static void main(String[] args) throws Exception {

            Classifier j48tree = new J48();
            Instances train = new Instances(new BufferedReader(new FileReader("demo.arff")));
            int lastIndex = train.numAttributes() - 1;
            
            train.setClassIndex(lastIndex);
            
            Instances test = new Instances(new BufferedReader(new FileReader("demo-test.arff")));
            test.setClassIndex(lastIndex);
            
            j48tree.buildClassifier(train);
            
            for(int i=0; i<test.numInstances(); i++) {
            
                    double index = j48tree.classifyInstance(test.instance(i));
                    String className = train.attribute(lastIndex).value((int)index);
                    System.out.println(className);
            }
    }
}


