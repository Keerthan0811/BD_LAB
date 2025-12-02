package titanic;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.*;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class driver {

    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf, "Titanic Analysis");
        job.setJarByClass(driver.class);

        // Set Mapper and Reducer classes
        job.setMapperClass(mapper.class);
        job.setReducerClass(reducer.class);

        // Set output types
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        // Set input and output paths
        FileInputFormat.addInputPath(job, new Path(args[0]));  // Input path (CSV file)
        FileOutputFormat.setOutputPath(job, new Path(args[1])); // Output path (results)

        // Wait for job to complete
        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
}

