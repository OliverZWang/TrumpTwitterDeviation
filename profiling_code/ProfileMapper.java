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
		// key is name of source with source identifier, 
		// value is 1
		String source_output = "source_" + tokens[0];
		context.write(new Text(source_output), new LongWritable(1));

		// write results for "text"
		// key is identifier "text_length"
		// values is the length of the text
		int textLength = tokens[1].length(); 
		context.write(new Text("text_length"), new LongWritable(textLength)); 

		// write results for "created_at"
		// key is identifier "created_at_" + year
		// value is 1
		String year = tokens[2].split("\\s+")[0].split("-")[2]; 
		context.write(new Text("created_in_"+year), new LongWritable(1)); 

		// write results for "retweet_count"
		// key is identifier "retweet_count"
		// value is value of the field
		int retweetCount = Integer.parseInt(tokens[3]); 
		context.write(new Text("retweet_count"), new LongWritable(retweetCount)); 

		// write results for "fav_count"
		// key is identifier "fav_count"
		// value is value of the field
		int favCount = Integer.parseInt(tokens[4]); 
		context.write(new Text("favorite_count"), new LongWritable(favCount)); 

		//write results for "is_retweet"
		// key is identifier "is_retweet"
		// value is 1 if true 0 of false
		int isRetweet; 
		if(tokens[5].equals("true")){
			isRetweet = 1; 
		}
		else{
			isRetweet = 0;
		}
		context.write(new Text("is_retweet"), new LongWritable(isRetweet)); 
	}



}