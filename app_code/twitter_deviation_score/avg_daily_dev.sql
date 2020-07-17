
/*impala-shell -B -o avg_daily_dev.csv --output_delimiter=',' */
create table daily_dev_sum as
	select t_date, sum(deviate) as dev_sum
	from mark_dev
	group by t_date
	order by t_date; 

create table avg_daily_dev as
	select daily_dev_sum.t_date, dev_sum/tweet_count as avg_daily_dev
	from daily_dev_sum inner join daily_count
	on daily_dev_sum.t_date = daily_count.t_date
	order by t_date;

select * from avg_daily_dev
order by t_date; 