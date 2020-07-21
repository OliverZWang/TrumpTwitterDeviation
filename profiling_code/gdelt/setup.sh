# Setup the HDFS directory stucture and populate it. 

# Create new directory structure in HDFS
hdfs dfs -mkdir rbda_proj
hdfs dfs -mkdir rbda_proj/profile_in

# Populate the input directory in HDFS with the input file
hdfs dfs -put input.txt rbda_proj/profile_in

# Verify what is in the input data directory
hdfs dfs -ls rbda_proj/profile_in
