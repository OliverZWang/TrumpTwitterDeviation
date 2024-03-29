#!/bin/bash

rm *.class
rm *.jar

# Compile source files except main file.
javac -classpath `yarn classpath` -d . CleanMapper.java
javac -classpath `yarn classpath` -d . CleanReducer.java

# Compiling main (driver) file is a bit different
javac -classpath `yarn classpath`:. -d . Clean.java

# Create jar file.
jar -cvf Clean.jar *.class

for I in $(seq 16 20)
do
    hadoop jar Clean.jar Clean /user/"$USER"/rbda_proj/raw/fox_news/*.txt /user/"$USER"/rbda_proj/clean/fox_news/20${I}/
done
