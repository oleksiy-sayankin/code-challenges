#!/usr/bin/env bash
#
# JDBC connection parameters are here
#
export DB_HOST=localhost
export DB_NAME=test
export DB_URL=jdbc:mysql://"${DB_HOST}"/"${DB_NAME}"?createDatabaseIfNotExist=true
export DB_DRIVER=com.mysql.jdbc.Driver
export DB_USER=root
export DB_PASSWORD=123456
export INITIAL_POOL_SIZE=3
