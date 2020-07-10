import java.io.IOException; 
import org.apache.hadoop.io.LongWritable; 
import org.apache.hadoop.io.Text; 
import org.apache.hadoop.mapreduce.Mapper; 

import java.io.BufferedReader;
import java.io.InputStreamReader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class CleanMapper extends Mapper<LongWritable, Text ,Text, Text>{

	@Override
	public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException{
		
		String stopwords = "&amp,rt,i,me,my,myself,we,our,ours,ourselves,you,you're,you've,you'll,you'd,your,yours,yourself,yourselves,he,him,his,himself,she,she's,her,hers,herself,it,it's,its,itself,they,them,their,theirs,themselves,what,which,who,whom,this,that,that'll,these,those,am,is,are,was,were,be,been,being,have,has,had,having,do,does,did,doing,a,an,the,and,but,if,or,because,as,until,while,of,at,by,for,with,about,against,between,into,through,during,before,after,above,below,to,from,up,down,in,out,on,off,over,under,again,further,then,once,here,there,when,where,why,how,all,any,both,each,few,more,most,other,some,such,no,nor,not,only,own,same,so,than,too,very,s,t,can,will,just,don,don't,should,should've,now,d,ll,m,o,re,ve,y,ain,aren,aren't,couldn,couldn't,didn,didn't,doesn,doesn't,hadn,hadn't,hasn,hasn't,haven,haven't,isn,isn't,ma,mightn,mightn't,mustn,mustn't,needn,needn't,shan,shan't,shouldn,shouldn't,wasn,wasn't,weren,weren't,won,won't,wouldn,wouldn't";



		String line = value.toString(); 
		String[] tokens = line.split(","); 

		//drop the first line
		if(tokens[0].equals("source")){
			return;
		}

		//take the id_str field as the key.
		String id = tokens[6];

		String keywords = extractKeywords(tokens[1], stopwords);

		String[] unformated_date = tokens[2].split("\\s+")[0].split("-");
		String formated_date = unformated_date[2] + "-" + unformated_date[0] + "-" + unformated_date[1];



		context.write(new Text(id), new Text(formated_date + "\t" + keywords)); 

	}



	public static String extractKeywords(String text, String stopwords){
		text = text.replaceAll("’", "'"); 

		String[] words = text.split("[\\p{Punct}\\s]+");
		ArrayList<String> keywords = new ArrayList<String>(); 

		for(String word : words){
			if(stopwords.indexOf(word.toLowerCase()) == -1){
				keywords.add(word); 
			}
		}

		String keywordsString = String.join(",", keywords); 
		return keywordsString; 

	}

	// public static ArrayList<String> getWordlist(String source) throws IOException{
	// 	ArrayList<String> output = new ArrayList<String>();  
	// 	try{
	// 		Path path = new Path("hdfs:"+source); 
	// 		FileSystem fs = FileSystem.get(new Configuration()); 
	// 		BufferedReader br = new BufferedReader(new InputStreamReader(fs.open(path))); 
	// 		String line = br.readLine();
	// 		while(line.trim().length() != 0){
	// 			output.add(Integer.toString(line.length())); 
	// 			// String trimmed = line.trim(); 
	// 			// if(trimmed.length() > 0){
	// 			// 	output.add(trimmed); 
	// 			// }
	// 		}
	// 		br.close();

	// 	}
	// 	catch(IOException e){
	// 		System.out.println("Error"); 
	// 	}
	// 	return output; 
	// }

}