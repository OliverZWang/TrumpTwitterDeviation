
use zw1400;
/*load the table that has deviation marked for each tweet*/
create external table mark_dev(id string, t_date string, deviate smallint)
	row format delimited fields terminated by '\t'
	location '/user/zw1400/impalaInput/mark_dev'; 

/*Create a table that shows the number of tweets on each day*/
create table daily_count as
select t_date, count(*) as tweet_count
from mark_dev
group by t_date
order by count(*) desc; 

/*Display the table*/
select * from daily_count
order by t_date; 

/*Create a table that shows the total number of deviating tweets in a day*/
create table daily_dev_sum as
	select t_date, sum(deviate) as dev_sum
	from mark_dev
	group by t_date
	order by t_date; 

/*Create a table that shows the average deviation or percentage of deviating tweets in a day*/
create table avg_daily_dev as
	select daily_dev_sum.t_date, dev_sum/tweet_count as avg_daily_dev
	from daily_dev_sum inner join daily_count
	on daily_dev_sum.t_date = daily_count.t_date
	order by t_date;

/*display table*/
select * from avg_daily_dev
order by t_date; 

/*Display the number of deviating tweets on each day*/
select t_date, sum(deviate) from mark_dev group by t_date;