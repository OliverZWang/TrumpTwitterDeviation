/***
 * GDELT Clean Reducer
 *
 * Carl Barbee (crb616)
 */
import java.io.IOException;
import java.lang.Math;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;


public class CleanReducer
    extends Reducer<Text, Text, Text, Text>
{
    @Override
    public void reduce( Text           key,
                        Iterable<Text> values,
                        Context        context)
        throws IOException, InterruptedException
    {
        for (Text value : values)
        {
            StringBuffer keyStr = new StringBuffer(key.toString());

            keyStr.insert(4, '-');
            keyStr.insert(7, '-');

            context.write(new Text(keyStr.toString()), value);
        }
    }
}
