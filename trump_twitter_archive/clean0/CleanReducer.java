import java.io.IOException; 
import org.apache.hadoop.io.Text; 
import org.apache.hadoop.mapreduce.Reducer; 

public class CleanReducer extends Reducer<Text, Text, Text, Text>{

	@Override
	public void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException{

		String tweets = "";

		for(Text value : values){
			String tweet = value.toString(); 
			tweets = tweets + tweet + ",";
		}

		if(tweets.length() > 0){
			tweets = tweets.substring(0, tweets.length()-1); 
		}

		context.write(key, new Text(tweets)); 

	}
}