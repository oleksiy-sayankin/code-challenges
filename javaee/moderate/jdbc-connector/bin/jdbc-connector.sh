#!/usr/bin/env bash

#
# Creates log directory if it does not exists.
#
create_log_dir() {
log_dir=$1
if [ ! -d "$log_dir" ]; then
  mkdir "$log_dir"
fi
}

#
# Connects to DB using params and inserts data from CSV files.
#
run_jdbc_connector() {
java -Dlog4j.configurationFile="${JDBC_CONNECTOR_HOME}"/conf/log4j2.properties -Djdbc.connector.home="${JDBC_CONNECTOR_HOME}" -jar "${JDBC_CONNECTOR_HOME}"/target/jdbc-connector*-with-dependencies*jar
}

#
# Main start script.
#
export JDBC_CONNECTOR_HOME="$(cd "$(dirname "${BASH_SOURCE[0]}")"/.. >/dev/null && pwd )"
source "${JDBC_CONNECTOR_HOME}"/bin/env.sh
create_log_dir "${JDBC_CONNECTOR_HOME}"/logs
run_jdbc_connector
