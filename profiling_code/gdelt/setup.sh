# Setup the HDFS directory stucture and populate it. 

# Create new directory structure in HDFS
hdfs dfs -mkdir rbda_proj
hdfs dfs -mkdir rbda_proj/profile_out

# Verify what is in the input data directory
hdfs dfs -ls rbda_proj/profile_in
