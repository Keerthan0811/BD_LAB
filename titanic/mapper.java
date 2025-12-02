package titanic;

import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.*;

import java.io.IOException;

public class mapper extends Mapper<LongWritable, Text, Text, IntWritable> {

    private final static IntWritable ageWritable = new IntWritable();
    private final static IntWritable countWritable = new IntWritable(1);
    private Text outputKey = new Text();
    
    public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        // Split the CSV row into fields
        String[] fields = value.toString().split(",");
    
        if (fields.length >= 9) {
            try {
                String survived = fields[1].trim();   // 1: Survived (0 or 1)
                String sex = fields[4].trim();        // 4: Sex (male or female)
                String ageStr = fields[5].trim();     // 5: Age
                int pclass = Integer.parseInt(fields[2].trim()); // 2: Pclass (1, 2, or 3)

                // Task 1: If the person died (survived == 0), emit their age based on gender
                if ("0".equals(survived) && !ageStr.isEmpty()) {
                    int age = (int) Double.parseDouble(ageStr);
                    outputKey.set(sex);  // Use "male" or "female" as the key
                    ageWritable.set(age);
                    context.write(outputKey, ageWritable);
                }
            
                // Task 2: If the person survived (survived == 1), emit the class number with count
                if ("1".equals(survived)) {
                    outputKey.set("SurvivorClass " + pclass); // Key is "SurvivorClass <pclass>"
                    context.write(outputKey, countWritable);
                }
            } catch (NumberFormatException e) {
                // Handle invalid data or missing age
            }
        }
    }
}

























