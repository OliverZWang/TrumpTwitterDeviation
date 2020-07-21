#!/bin/bash

yarn classpath
javac -classpath `yarn classpath` -d . CleanMapper.java
javac -classpath `yarn classpath`:. -d . Clean.java

jar -cvf Clean.jar *.class

hdfs dfs -mkdir /user/zw1400/trump_tweets_test
# hdfs dfs -mkdir /user/zw1400/trump_tweets
hdfs dfs -put ../../data_injest/trump_twitter_archive/trump_20200530.csv /user/zw1400/trump_tweets_test
# hdfs dfs -put ../stopwords.txt /user/zw1400/trump_tweets_test

hadoop jar Clean.jar Clean /user/zw1400/trump_tweets_cleaned/trump_20200530.csv /user/zw1400/trump_tweets_test/output