#!/bin/bash

yarn classpath
javac -classpath `yarn classpath` -d . ProfileMapper.java
javac -classpath `yarn classpath` -d . ProfileReducer.java
javac -classpath `yarn classpath`:. -d . Profile.java

jar -cvf Profile.jar *.class

hdfs dfs -mkdir /user/zw1400/trump_tweets_profile_test
hdfs dfs -put ../data_injest/trump_twitter_archive/trump_20200530.csv /user/zw1400/trump_tweets_profile_test

hadoop jar Profile.jar Profile /user/zw1400/trump_tweets_profile_test/trump_20200530.csv /user/zw1400/trump_tweets_profile_test/output