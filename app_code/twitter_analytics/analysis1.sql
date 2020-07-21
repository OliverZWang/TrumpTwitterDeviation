/*impala-shell -i compute-1-1 -f ./analysis.sql -B -o analysis0.txt*/
use zw1400;

select "Number of tweets that that do not contain twitter trends";
select count(*) from mark_dev where deviate = 1;

select "\n";

select "Number of tweets that contain twitter trends";
select count(*) from mark_dev where deviate = 0;

select "\n";

select "Total mumber of tweets analyzed"; 
select count(*) from mark_dev;

select "\n";

select "Percentage that deviate";
select concat(cast(sum(deviate)/count(*)*100 AS string), "%") from mark_dev;

select "\n";

select "Percentage that did not deviate";
select concat(cast((count(*)-sum(deviate))/count(*)*100 AS string), "%") from mark_dev;

select "\n";

select "Days with highest number of deviating tweets";
select "(date, # of deviating tweets, total # of tweets)";
select daily_dev_sum.t_date, dev_sum, tweet_count 
from daily_dev_sum inner join daily_count 
on daily_dev_sum.t_date = daily_count.t_date
order by dev_sum DESC 
limit 20;

select "\n";

select "Number of days when all tweets deviate";
select count(*) from avg_daily_dev
where avg_daily_dev = 1;

select "\n";

select "Days when all tweets deviate"; 
select "(date, total # of tweets)";
select avg_daily_dev.t_date, tweet_count 
from avg_daily_dev inner join daily_count
on avg_daily_dev.t_date = daily_count.t_date
where avg_daily_dev = 1
order by avg_daily_dev.t_date DESC;

select "\n";

select "Monthly deviating tweet count ordered by month";
select "(month, # of deviating tweets)";
select substr(mark_dev.t_date, 1, 7) as month, 
sum(deviate) as num_of_deviating_tweets,
sum(tweet_count) as num_of_tweets
from mark_dev inner join daily_count
on mark_dev.t_date = daily_count.t_date
group by substr(mark_dev.t_date, 1, 7)
order by substr(mark_dev.t_date, 1, 7);

select "\n";


select "Monthly deviation average and monthly tweet count ordered by date";
select "(month, % of deviating tweets, total # of tweets this month)"; 
select substr(avg_daily_dev.t_date, 1, 7) as month, 
sum(avg_daily_dev)/count(*) as deviation_avg, 
sum(tweet_count) as tweet_count
from avg_daily_dev inner join daily_count
on avg_daily_dev.t_date = daily_count.t_date
group by substr(avg_daily_dev.t_date, 1, 7)
order by substr(avg_daily_dev.t_date, 1, 7) DESC;

select "\n";

select "Monthly deviation average and monthly tweet count ordered by deviation";
select "(month, % of deviating tweets, total # of tweets this month)"; 
select substr(avg_daily_dev.t_date, 1, 7) as month, 
sum(avg_daily_dev)/count(*) as deviation_avg, 
sum(tweet_count) as tweet_count
from avg_daily_dev inner join daily_count
on avg_daily_dev.t_date = daily_count.t_date
group by substr(avg_daily_dev.t_date, 1, 7)
order by deviation_avg DESC;

select "\n";

select "Monthly deviation average and monthly tweet count ordered by tweet count";
select "(month, % of deviating tweets, total # of tweets this month)"; 
select substr(avg_daily_dev.t_date, 1, 7) as month, 
sum(avg_daily_dev)/count(*) as deviation_avg, 
sum(tweet_count) as tweet_count
from avg_daily_dev inner join daily_count
on avg_daily_dev.t_date = daily_count.t_date
group by substr(avg_daily_dev.t_date, 1, 7)
order by tweet_count DESC;

select "\n";

select "Yearly deviation average and yearly tweet count ordered by year"; 
select "(year, % of deviating tweets, total # of tweets this month)"; 
select substr(avg_daily_dev.t_date, 1, 4) as year, 
sum(avg_daily_dev)/count(*) as deviation_avg, 
sum(tweet_count) as tweet_count
from avg_daily_dev inner join daily_count
on avg_daily_dev.t_date = daily_count.t_date
group by substr(avg_daily_dev.t_date, 1, 4)
order by substr(avg_daily_dev.t_date, 1, 4); 

select "\n";

select "Average retweets"; 
select sum(rt_count)/count(*) from trump_complete;

select "\n";

select "Average favs"; 
select sum(fav_count)/count(*) from trump_complete;

select "\n";

select "Average retweets and favs for deviating and non-deviating tweets"; 
select "(deviate, avgerage retweets, average favs)";
select deviate,
sum(trump_complete.rt_count)/count(*) as avg_retweet,
sum(trump_complete.fav_count)/count(*) as avg_fav
from mark_dev inner join trump_complete
on mark_dev.id = trump_complete.id
group by mark_dev.deviate; 



