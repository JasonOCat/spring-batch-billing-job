to launch the job with the input csv :
./mvnw clean package -Dmaven.test.skip=true
java -jar target/spring-batch-billing-job-0.0.1-SNAPSHOT.jar input.file=src/main/resources/billing-2025-01.csv
-> ne marche plus car fichier déplacés dans le dossier "input"

to clean database:
scripts/drop-create-database.sh

to connect to databse:
docker exec -it postgres_postgres_billing_job_db psql -U postgres

to see spring batch table:
select * from BATCH_JOB_EXECUTION;
select * from BATCH_JOB_EXECUTION_PARAMS

to launch the job with the dynamic parameters
./mvnw clean package -Dmaven.test.skip=true
java -jar target/spring-batch-billing-job-0.0.1-SNAPSHOT.jar input.file=input/billing-2025-01.csv output.file=staging/billing-report-2025-01.csv data.year=2025 data.month=1
java -jar target/spring-batch-billing-job-0.0.1-SNAPSHOT.jar input.file=input/billing-2025-02.csv output.file=staging/billing-report-2025-02.csv data.year=2025 data.month=2
java -jar target/spring-batch-billing-job-0.0.1-SNAPSHOT.jar input.file=input/billing-2025-03.csv output.file=staging/billing-report-2025-03.csv data.year=2025 data.month=3


