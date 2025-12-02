package wordcount;
import java.io.*;
import java.util.*;
import org.apache.hadoop.mapred.*;
import org.apache.hadoop.io.*;

public class reducer extends MapReduceBase implements Reducer<Text, IntWritable, Text, IntWritable> {

    public void reduce(Text key, Iterator<IntWritable> values, OutputCollector<Text, IntWritable> output,
            Reporter reporter) throws IOException {
        
        int sum = 0;
        
        // Sum up the counts for each word
        while (values.hasNext()) {
            sum += values.next().get();
        }
        
        // Emit the word with the total count
        output.collect(key, new IntWritable(sum));
    }
}
