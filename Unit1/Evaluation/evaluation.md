## Evaluation Unit 1  
## Answer the following questions with Spark DataFramas using the "CSV" Netflix_2011_2016.csv.

## 1.- Start a simple Spark session.
<div align="Justify">
Using SparkSession and get0rCreate will help create a session on spark
</div>

``` scala 
import org.apache.spark.sql.SparkSession
```

``` scala
## Terminal
spark: import org.apache.spark.sql.SparkSession
```

``` scala 
val spark = SparkSession.builder().getOrCreate()
```

``` scala 
## Terminal
spark: org.apache.spark.sql.SparkSession = org.apache.spark.sql.SparkSession@1013aa94
```

