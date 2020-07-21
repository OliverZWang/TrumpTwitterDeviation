/***
 * GDELT Profiler Mapper
 *
 * Carl Barbee (crb616)
 */

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;


public class ProfileMapper
  extends Mapper<LongWritable, Text, Text, Text> {

    @Override
    public void map(LongWritable key,
                    Text         value,
                    Context      context)
        throws IOException, InterruptedException
    {
        String tokens[] = value.toString().split("\t");
        String date     = tokens[0];

        // n-gram for the date.
        String ngram = tokens[3];
        String freq  = tokens[4];

        context.write(new Text("date"), new Text(date));
        context.write(new Text("ngram"), new Text(ngram));
        context.write(new Text("freq"), new Text(freq));
   }
}
