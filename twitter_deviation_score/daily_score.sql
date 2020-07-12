
/*impala-shell -B -o daily_score.txt --output_delimiter=','*/
select t_date, sum(deviate) from mark_dev group by t_date;