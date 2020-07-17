/***
 * GDELT Profiler Reducer
 *
 * Carl Barbee (crb616)
 */
import java.io.IOException;
import java.lang.Math;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;


public class ProfileReducer
    extends Reducer<Text, Text, Text, Text>
{
    @Override
    public void reduce( Text           key,
                        Iterable<Text> values,
                        Context        context)
        throws IOException, InterruptedException
    {
        String keyStr = key.toString();

        if (keyStr.equals("date"))
        {
            int min_date = Integer.MAX_VALUE;
            int max_date = Integer.MIN_VALUE;

            for (Text value : values)
            {
                int date = Integer.parseInt(value.toString());

                min_date = Math.min(min_date, date);
                max_date = Math.max(max_date, date);
            }

            context.write(new Text("start_date: "),
                          new Text(Integer.toString(min_date)));
            context.write(new Text("end_date: "),
                          new Text(Integer.toString(max_date)));
        }
        else if (keyStr.equals("ngram"))
        {
            int   numberNgrams        = 0;
            float longestNgramLength  = 0;
            float averageNGramLength  = 0;
            float minNGramLength      = Float.MAX_VALUE;
            float sumOfLengths        = 0;

            for (Text value : values)
            {
                float currNgramLength = value.toString().length();

                numberNgrams += 1;
                sumOfLengths += currNgramLength;

                longestNgramLength = Math.max(longestNgramLength, currNgramLength);
                minNGramLength     = Math.min(minNGramLength, currNgramLength);
            }

            averageNGramLength = sumOfLengths / numberNgrams;

            // Max and Average length of an N-gram.
            context.write( new Text("longest_ngram:"),
                           new Text(Float.toString(longestNgramLength)));
            context.write( new Text("avg_ngram_len:"),
                           new Text(Float.toString(averageNGramLength)));
            context.write( new Text("shortest_ngram:"),
                           new Text(Float.toString(minNGramLength)));
        }
        else if (keyStr.equals("freq"))
        {
            float highestNgramFreq    = 0;
            float averageNgramFreq    = 0;
            float minNgramFreq        = Float.MAX_VALUE;
            float sumOfFreqs          = 0;
            float numberNgrams        = 0;

            for (Text value : values)
            {
                float ngramFreq = Integer.parseInt(value.toString());

                numberNgrams += 1;
                sumOfFreqs   += ngramFreq;

                highestNgramFreq = Math.max(highestNgramFreq, ngramFreq);
                minNgramFreq     = Math.min(minNgramFreq, ngramFreq);
            }

            averageNgramFreq = sumOfFreqs / numberNgrams;

            // Max and Average n-gram frequency.
            context.write( new Text("highest_freq:"),
                           new Text(Float.toString(highestNgramFreq)));
            context.write( new Text("avg_ngram_freq:"),
                           new Text(Float.toString(averageNgramFreq)));
            context.write( new Text("min_freq:"),
                           new Text(Float.toString(minNgramFreq)));
        }
    }
}
