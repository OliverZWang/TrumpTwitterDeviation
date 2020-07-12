import java.io.IOException; 
import org.apache.hadoop.io.LongWritable; 
import org.apache.hadoop.io.Text; 
import org.apache.hadoop.mapreduce.Mapper; 

public class MarkDevMapper extends Mapper<LongWritable, Text, Text, Text>{
	
	@Override
	public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException{

		String line = value.toString(); 
		String[] tokens = line.split(";"); 

		String tweetId = tokens[0]; 
		String timeStamp = tokens[1]; 
		String[] keywords = tokens[2].toLowerCase().split(","); 
		String trends = tokens[3].toLowerCase();

		int deviate = 1;

		for(int i = 0; i < keywords.length && deviate == 1; i++){
			if(trends.contains(keywords[i])){
				deviate = 0;
			}
		}

		String outputValue = timeStamp + "\t" + deviate;

		context.write(new Text(tweetId), new Text(outputValue)); 
	}
}