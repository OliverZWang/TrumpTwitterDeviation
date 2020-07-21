#!/bin/bash

yarn classpath
javac -classpath `yarn classpath` -d . MarkDevMapper.java
javac -classpath `yarn classpath`:. -d . MarkDev.java

jar -cvf MarkDev.jar *.class

hadoop jar MarkDev.jar MarkDev /user/zw1400/mark_dev/trump_with_trends.txt /user/zw1400/mark_dev/output