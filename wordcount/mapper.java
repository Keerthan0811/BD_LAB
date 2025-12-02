package wordcount;
import java.io.*;
import java.util.*;
import org.apache.hadoop.mapred.*;
import org.apache.hadoop.io.*;

public class mapper extends MapReduceBase implements Mapper<LongWritable, Text, Text, IntWritable> {
    
    // Static final variable for the count of 1
    private final static IntWritable one = new IntWritable(1);
    
    // Reusable Text object to hold each word
    private Text word = new Text();

    // The map function
    public void map(LongWritable key, Text value, OutputCollector<Text, IntWritable> output, Reporter reporter)
            throws IOException {
        
        // Convert the input value (line of text) to a string
        String line = value.toString();
        
        // Tokenize the line into words
        StringTokenizer tokenizer = new StringTokenizer(line);
        
        // Iterate through the tokens (words)
        while (tokenizer.hasMoreTokens()) {
            // Set the current word into the Text object
            word.set(tokenizer.nextToken());
            
            // Collect the word and emit (word, 1) as key-value pairs
            output.collect(word, one);
        }
    }
}
