#!/bin/bash

yarn classpath
javac -classpath `yarn classpath` -d . CleanMapper.java
javac -classpath `yarn classpath` -d . CleanReducer.java
javac -classpath `yarn classpath`:. -d . Clean.java

jar -cvf Clean.jar *.class

hdfs dfs -mkdir /user/zw1400/trump_tweets_cleaned
hdfs dfs -put ../../data_injest/trump_twitter_archive/trump_20200530.csv /user/zw1400/trump_tweets_cleaned

hadoop jar Clean.jar Clean /user/zw1400/trump_tweets_cleaned/trump_20200530.csv /user/zw1400/trump_tweets_cleaned/output