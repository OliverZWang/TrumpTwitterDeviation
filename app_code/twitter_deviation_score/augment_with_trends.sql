
/*impala-shell -B -o output.txt --output_delimiter=';' */
create external table trump_temp(id string, t_date string, keywords string) 
	row format delimited fields terminated by '\t'
	location '/user/zw1400/impalaInput/trump';

create table trump as 
	select id, to_timestamp(t_date, 'yyyy-MM-dd') as t_date, keywords
	from trump_temp; 

create external table twitter_temp (tw_date string, trends string)
	row format delimited fields terminated by '\t'
	location '/user/zw1400/impalaInput/twitter'; 

create table twitter as
	select to_timestamp(tw_date, 'yyyy-MM-dd') as tw_date, trends
	from twitter_temp;

create table aug_trump_twitter as
select id, t_date, keywords, trends
from trump left join twitter
on t_date = adddate(tw_date, 1);

select * from aug_trump_twitter; 

create external table trump_comp(source string, t_text string, t_date string, rt_count int, fav_count int, is_retweet boolean, id string) 
	row format delimited fields terminated by ',' 
	location '/user/zw1400/impalaInput/complete_trump';

create table trump_complete as
	select trump.id as id, trump.t_date as t_date, t_text, rt_count, fav_count, is_retweet, source
	from trump inner join trump_comp
	on trump.id = trump_comp.id;