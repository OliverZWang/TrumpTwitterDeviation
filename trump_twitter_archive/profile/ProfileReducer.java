import java.io.IOException; 
import org.apache.hadoop.io.Text; 
import org.apache.hadoop.io.LongWritable; 
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.mapreduce.Reducer; 

public class ProfileReducer extends Reducer<Text, LongWritable, Text, FloatWritable>{

	@Override
	public void reduce(Text key, Iterable<LongWritable> values, Context context) throws IOException, InterruptedException{
		
		String input_key = key.toString(); 

		// if(input_key.indexOf("source") == 0){
			float sum = 0; 
			for(LongWritable value : values){
				sum += value.get(); 
			}

			context.write(key, new FloatWritable(sum));  
		// }

	}

}
