#!/bin/bash

jstorm jar montoring-0.0.1-SNAPSHOT.jar com.tripb2b.montoring.jstorm.MontoringTopology conf/conf.yaml
jstorm jar montoring-0.0.1-SNAPSHOT-jar-with-dependencies.jar com.tripb2b.montoring.jstorm.MontoringTopology conf/conf.yaml


#kafkaMontoring depends on belowing jar, copy from kafka/lib and kafka-manager/lib; you must copy these jars to jstorm/lib.
#com.yammer.metrics.metrics-core-2.2.0.jar
#kafka_2.11-0.8.2.2.jar
#kafka-clients-0.8.2.2.jar
#scala-library-2.11.5.jar
#scala-parser-combinators_2.11-1.0.2.jar
#scala-xml_2.11-1.0.2.jar
#jstorm-kafka-2.0.4-SNAPSHOT.jar

#hbase, copy all files of hbase/lib to jstorm/lib

