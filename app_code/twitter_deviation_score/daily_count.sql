
/*impala-shell -B -o daily_count.txt --output_delimiter=',' */
create table daily_count as
select t_date, count(*) as tweet_count
from mark_dev
group by t_date
order by count(*) desc; 

select * from daily_count
order by t_date; 