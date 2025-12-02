package employee;
import java.io.*;
import java.util.*;
import org.apache.hadoop.mapred.*;
import org.apache.hadoop.io.*;
class mapper extends MapReduceBase implements Mapper<LongWritable , Text , Text , DoubleWritable> 
{
   public void map(LongWritable key, Text value, OutputCollector<Text,DoubleWritable> output ,Reporter r) throws IOException
    {
   	 String[] line = value.toString().split("\\t");
           	// String[] fields = line.trim().split("\\s+"); Split by one or more spaces or tabs
        	if (line.length < 9) { // Ensure there are enough fields
            	return; // Skip this record if not enough fields
        	}
        	String gender = line[3]; // Assuming gender is in the 4th column (index 3)
        	Double salary = Double.parseDouble(line[8]); // Assuming salary is in the 9th column (index 8)
        	output.collect(new Text(gender), new DoubleWritable(salary));

    }
}
