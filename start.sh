#!/bin/sh

LOG4J2CONF="conf/log4j2.properties"
CONFIG="conf/config.properties"

java -Dlog4j2.configurationFile=$LOG4J2CONF -Dconfig=$CONFIG -jar airplane.jar
