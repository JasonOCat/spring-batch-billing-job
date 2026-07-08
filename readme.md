# 🚀 Spring Batch Billing Job - Cheat Sheet


## 🗄️ Gestion de la Base de Données

### Réinitialiser la base de données
Exécutez le script pour vider et recréer les tables :
```bash
scripts/drop-create-database.sh
```

### Se connecter à la base (Docker Postgres)
```bash
docker exec -it postgres_billing_job_db psql -U postgres
```

## 🚀 Exécution des Jobs (Spring Batch)

### 1. Exécutions standards (Paramètres dynamiques)
Lancez les jobs mois par mois en spécifiant les fichiers d'entrée/sortie ainsi que la période :

* **Janvier 2025 :**
  ```bash
  java -jar target/spring-batch-billing-job-0.0.1-SNAPSHOT.jar \
    input.file=input/billing-2025-01.csv \
    output.file=staging/billing-report-2025-01.csv \
    data.year=2025 data.month=1
  ```
* **Février 2025 :**
  ```bash
  java -jar target/spring-batch-billing-job-0.0.1-SNAPSHOT.jar \
    input.file=input/billing-2025-02.csv \
    output.file=staging/billing-report-2025-02.csv \
    data.year=2025 data.month=2
  ```
* **Mars 2025 :**
  ```bash
  java -jar target/spring-batch-billing-job-0.0.1-SNAPSHOT.jar \
    input.file=input/billing-2025-03.csv \
    output.file=staging/billing-report-2025-03.csv \
    data.year=2025 data.month=3
  ```

### 2. Exécution avec gestion du Skip (Lignes ignorées)
Pour suivre et enregistrer les lignes en erreur dans un fichier dédié :
```bash
java -jar target/spring-batch-billing-job-0.0.1-SNAPSHOT.jar \
  input.file=input/billing-2025-03.csv \
  output.file=staging/billing-report-2025-03.csv \
  skip.file=staging/billing-data-skip-2025-03.psv \
  data.year=2025 data.month=3
```

### 3. Test de la politique de Retry (Réévaluation)
```bash
java -jar target/spring-batch-billing-job-0.0.1-SNAPSHOT.jar \
  input.file=input/billing-2025-04.csv \
  output.file=staging/billing-report-2025-04.csv \
  skip.file=staging/billing-data-skip-2025-04.psv \
  data.year=2023 data.month=4
```

## 🔍 Suivi et Requêtes SQL (Métadonnées Batch)

À exécuter une fois connecté à votre base de données pour vérifier l'état de vos traitements.

### Voir les tables principales de Spring Batch
```sql
SELECT * FROM BATCH_JOB_EXECUTION;
SELECT * FROM BATCH_JOB_EXECUTION_PARAMS;
```

### Vérifier le statut global des Jobs
```sql
SELECT job_instance_id, job_execution_id, status 
FROM BATCH_JOB_EXECUTION;
```

### Analyser le détail des Steps (Lectures, Écritures, Commits)
```sql
SELECT step_execution_id, job_execution_id, step_name, status, read_count, write_count, commit_count, rollback_count 
FROM BATCH_STEP_EXECUTION;
```