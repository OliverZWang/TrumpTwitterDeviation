import java.io.IOException; 
import org.apache.hadoop.io.LongWritable; 
import org.apache.hadoop.io.Text; 
import org.apache.hadoop.mapreduce.Mapper; 

public class CleanMapper extends Mapper<LongWritable, Text, Text, Text>{

	@Override
	public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException{
		
		String line = value.toString(); 
		String[] tokens = line.split(","); 
		if(tokens[0].equals("source")){
			return; 
		}

		String[] unformated_date = tokens[2].split("\\s+")[0].split("-");
		String formated_date = unformated_date[2] + "-" + unformated_date[0] + "-" + unformated_date[1];

		Text output_key = new Text(formated_date); 

		String output_value = "";
		for(int i = 1; i < tokens.length; i++){
			if(i != 2){
				output_value += tokens[i] + ","; 
			}
		} 

		if(output_value.length() > 0){
			output_value = output_value.substring(0, output_value.length()-1); 
		}


		context.write(output_key, new Text(output_value)); 
		


	}


}