#!/bin/bash

yarn classpath
javac -classpath `yarn classpath` -d . MarkDevMapper.java
javac -classpath `yarn classpath`:. -d . MarkDev.java

jar -cvf MarkDev.jar *.class

# hdfs dfs -mkdir /user/zw1400/MarkDev
# # hdfs dfs -mkdir /user/zw1400/trump_tweets
# hdfs dfs -put ../trump_20200530.csv /user/zw1400/trump_tweets_test
# # hdfs dfs -put ../stopwords.txt /user/zw1400/trump_tweets_test

hadoop jar MarkDev.jar MarkDev /user/zw1400/mark_dev/trump_with_trends.txt /user/zw1400/mark_dev/output