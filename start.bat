@echo off

set log4j=config/log4j2.properties
set config=config/config.properties

java -Dlog4j2.configurationFile=%log4j% -Dconfig=%config% -jar airplane.jar
