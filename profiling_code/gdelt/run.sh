#!/bin/bash

rm *.class
rm *.jar

# Compile source files except main file.
javac -classpath `yarn classpath` -d . ProfileMapper.java
javac -classpath `yarn classpath` -d . ProfileReducer.java

# Compiling main (driver) file is a bit different
javac -classpath `yarn classpath`:. -d . Profile.java

# Create jar file.
jar -cvf Profile.jar *.class


for I in $(seq 16 20)
do
    hadoop jar Profile.jar Profile /user/"$USER"/rbda_proj/profile_in/*.txt /user/crb616/rbda_proj/profile_out/
done
