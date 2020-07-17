import org.apache.hadoop.fs.Path; 
import org.apache.hadoop.io.LongWritable; 
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.Text; 
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat; 
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class Profile{

	public static void main(String[] args) throws Exception{

		if(args.length != 2){
			System.err.println("Usage: MaxTemperature <input path> <output path>");
			System.exit(-1); 
		}

		Job job = new Job(); 

		job.setJarByClass(Profile.class); 
		job.setJobName("Profile"); 

		FileInputFormat.addInputPath(job, new Path(args[0])); 
		FileOutputFormat.setOutputPath(job, new Path(args[1]));

		job.setMapperClass(ProfileMapper.class); 
		job.setReducerClass(ProfileReducer.class); 

		job.setOutputKeyClass(Text.class); 
		job.setOutputValueClass(LongWritable.class); 

		job.setNumReduceTasks(1); 

		System.exit(job.waitForCompletion(true) ? 0 : 1); 
	}

}