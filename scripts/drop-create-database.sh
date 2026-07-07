#!/bin/bash
set -e

# This script is used to drop and recreate the meta-data tables

docker exec -i postgres_billing_job_db \
         psql -U postgres \
         -d postgres_billing_job_db \
         < ./src/sql/schema-drop-postgresql.sql

docker exec -i postgres_billing_job_db \
         psql -U postgres \
         -d postgres_billing_job_db \
         < ./src/sql/schema-postgresql.sql

# the billing data table
docker exec -i postgres_billing_job_db \
         psql -U postgres \
         -d postgres_billing_job_db \
         < ./src/sql/drop-billing-data.sql

docker exec -i postgres_billing_job_db \
         psql -U postgres \
         -d postgres_billing_job_db \
         < ./src/sql/create-billing-data.sql

