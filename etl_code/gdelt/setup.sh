# Setup the HDFS directory stucture and populate it. 

# Create new directory structure in HDFS
hdfs dfs -mkdir rbda_proj/clean
hdfs dfs -mkdir rbda_proj/clean/fox_news

# Verify what is in the input data directory
hdfs dfs -ls rbda_proj/raw
