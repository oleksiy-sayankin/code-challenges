#!/usr/bin/env bash

export JDBC_CONNECTOR_HOME="$(cd "$(dirname "${BASH_SOURCE[0]}")"/.. >/dev/null && pwd )"
source "${JDBC_CONNECTOR_HOME}"/bin/env.sh
java -jar "${JDBC_CONNECTOR_HOME}"/target/jdbc-connector*-with-dependencies*jar
