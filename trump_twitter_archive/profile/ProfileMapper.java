import java.io.IOException; 
import org.apache.hadoop.io.LongWritable; 
import org.apache.hadoop.io.Text; 
import org.apache.hadoop.mapreduce.Mapper; 

public class ProfileMapper extends Mapper<LongWritable, Text, Text, LongWritable>{

	@Override
	public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException{

		String line = value.toString();
		String[] tokens = line.split(","); 

		if(tokens[0].equals("source")){
			return;
		}


		// write results for "source"
		// key is 
		String source_output = "source_" + tokens[0];
		context.write(new Text(source_output), new LongWritable(1));


	}



}