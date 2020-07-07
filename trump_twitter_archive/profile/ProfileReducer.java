import java.io.IOException; 
import org.apache.hadoop.io.Text; 
import org.apache.hadoop.io.LongWritable; 
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.mapreduce.Reducer; 

public class ProfileReducer extends Reducer<Text, LongWritable, Text, FloatWritable>{

	@Override
	public void reduce(Text key, Iterable<LongWritable> values, Context context) throws IOException, InterruptedException{
		
		String inputKey = key.toString(); 

		if(inputKey.indexOf("source") == 0){
			float sumSource = 0; 
			for(LongWritable value : values){
				sumSource += value.get(); 
			}

			context.write(key, new FloatWritable(sumSource));  
		}
		else if (inputKey.equals("text_length")){
			float maxText = -1; 
			float sumText = 0; 
			int valuesSize = 0; 
			for(LongWritable value : values){
				valuesSize++; 
				float thisValue = value.get(); 
				if (thisValue > maxText){
					maxText = thisValue; 
				}
				sumText += thisValue; 
			}
			FloatWritable averageLength = new FloatWritable(sumText/valuesSize); 
			context.write(new Text("max_text_length"), new FloatWritable(maxText));
			context.write(new Text("average_text_length"), averageLength); 
		}
		else if(inputKey.indexOf("created_in") == 0){
			float sumOfTheYear = 0; 
			for(LongWritable value : values){
				sumOfTheYear += 1; 
			}
			context.write(key, new FloatWritable(sumOfTheYear)); 
		}
		else if(inputKey.equals("retweet_count")){
			float maxRetweet = -1;
			float minRetweet = Float.MAX_VALUE;
			float totalRetweet = 0;
			int count = 0;
			for(LongWritable value : values){
				float numRetweet = value.get(); 
				if(numRetweet > maxRetweet){
					maxRetweet = numRetweet; 
				}
				if(numRetweet < minRetweet){
					minRetweet = numRetweet; 
				}
				totalRetweet += numRetweet; 
				count++; 
			}
			context.write(new Text("max_num_of_retweet"), new FloatWritable(maxRetweet));
			context.write(new Text("min_num_of_retweet"), new FloatWritable(minRetweet)); 
			context.write(new Text("average_num_of_retweet"), new FloatWritable(totalRetweet/count)); 
		}
		else if(inputKey.equals("favorite_count")){
			float maxFav = -1;
			float minFav = Float.MAX_VALUE;
			float totalFav = 0;
			int count = 0;
			for(LongWritable value : values){
				float numFav = value.get(); 
				if(numFav > maxFav){
					maxFav = numFav; 
				}
				if(numFav < minFav){
					minFav = numFav; 
				}
				totalFav += numFav; 
				count++; 
			}
			context.write(new Text("max_num_of_favorite"), new FloatWritable(maxFav)); 
			context.write(new Text("min_num_of_favorite"), new FloatWritable(minFav)); 
			context.write(new Text("average_num_of_favorite"), new FloatWritable(totalFav/count)); 
		}
		else{
			float isRetweetCount = 0;
			float notRetweetCount = 0;
			int totalCount = 0;
			for(LongWritable value : values){
				if(value.get() == 1){
					isRetweetCount += 1; 
				}
				else{
					notRetweetCount += 1;
				}
				totalCount++; 
			}
			float retweetPercentage = isRetweetCount/totalCount *100; 
			float notRetweetPercentage = notRetweetCount/totalCount *100;

			context.write(new Text("percentage_of_retweet"), new FloatWritable(retweetPercentage)); 
			context.write(new Text("percentage_of_not_retweet"), new FloatWritable(notRetweetPercentage)); 
			


		}

	}

}
