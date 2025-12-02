package titanic;

import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.*;

import java.io.IOException;

public class reducer extends Reducer<Text, IntWritable, Text, DoubleWritable> {

    private DoubleWritable result = new DoubleWritable();
    
    public void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
        // Case 1: Calculating the average age of those who died (key = "male" or "female")
        if ("male".equals(key.toString()) || "female".equals(key.toString())) {
            int sum = 0;
            int count = 0;

            for (IntWritable val : values) {
                sum += val.get();
                count++;
            }
            if (count > 0) {
                result.set((double) sum / count);  // Compute average
                context.write(new Text("Average Age of Died " + key), result);
            }
        }
    
        // Case 2: Counting survivors in each class (key = "SurvivorClass <class>")
        else if (key.toString().startsWith("SurvivorClass")) {
            int sum = 0;
            for (IntWritable val : values) {
                sum += val.get();  // Count how many survivors in this class
            }
            result.set(sum);  // Total survivors in this class
            context.write(new Text(key), result);
        }
    }
}

