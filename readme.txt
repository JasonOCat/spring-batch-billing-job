to launch the job with the input csv :
./mvnw clean package -Dmaven.test.skip=true
java -jar target/spring-batch-billing-job-0.0.1-SNAPSHOT.jar input.file=src/main/resources/billing-2025-01.csv

to clean database:
scripts/drop-create-database.sh

to connect to databse:
docker exec -it postgres_postgres_billing_job_db psql -U postgres

to see spring batch table:
select * from BATCH_JOB_EXECUTION;
select * from BATCH_JOB_EXECUTION_PARAMS


