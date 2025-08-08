#!/bin/bash
set -e

# This script is used to drop and recreate the meta-data tables

docker exec -i postgres_postgres_billing_job_db \
         psql -U postgres \
         < ./src/sql/schema-drop-postgresql.sql

docker exec -i postgres_postgres_billing_job_db \
         psql -U postgres \
         < ./src/sql/schema-postgresql.sql

