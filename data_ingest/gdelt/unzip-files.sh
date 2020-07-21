#!/bin/bash

# Bash script for unzipping files in HDFS.

# change the year
for J in $(seq 16 16)
do
    # process months 1-9
    for I in $(seq 2 9)
    do
        hdfs dfs -cat "rbda_proj/fox_news/20${J}0$I*.gz" | gunzip -d | hdfs dfs -put - "/user/crb616/rbda_proj/raw/fox_news/20${J}+0$I.txt"
    done

    # process months 10-12
    for I in $(seq 10 12)
    do
        hdfs dfs -cat "rbda_proj/fox_news/20${J}${I}*.gz" | gunzip -d | hdfs dfs -put - "/user/crb616/rbda_proj/raw/fox_news/20${J}+${I}.txt"
    done
done

# check that all files are present.
hdfs dfs -ls rbda_proj/raw/fox_news/*.txt
