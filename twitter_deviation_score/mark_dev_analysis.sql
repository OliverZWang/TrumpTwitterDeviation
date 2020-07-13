


create external table mark_dev(id string, t_date string, deviate smallint)
	row format delimited fields terminated by '\t'
	location '/user/zw1400/impalaInput/mark_dev'; 