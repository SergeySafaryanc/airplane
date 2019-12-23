#!/bin/sh

LOG4J2CONF="conf/log4j2.properties"
CONFIG="conf/config.properties"

java -Dlog4j2.configurationFile=file:$LOG4J2CONF -Dconfig=$PWD/$CONFIG -jar aiplane.jar
