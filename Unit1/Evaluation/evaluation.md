## Evaluation Unit 1  
## Answer the following questions with Spark DataFramas using the "CSV" Netflix_2011_2016.csv.

## 1.- Start a simple Spark session.
<div align="Justify">
Using SparkSession and get0rCreate will help create a session on spark
</div>

``` scala 
import org.apache.spark.sql.SparkSession

## Terminal
spark: import org.apache.spark.sql.SparkSession
```



``` scala 
val spark = SparkSession.builder().getOrCreate()

## Terminal
spark: org.apache.spark.sql.SparkSession = org.apache.spark.sql.SparkSession@1013aa94
```


## 2.- Load the Netflix Stock CSV file, make Spark infer the data types.
<div align="Justify">
To load a csv file we use the following code in order to work, typing the name of the of the cvs file and have that file in the right location.
</div>

``` scala
val df = spark.read.option("header", "true").option("inferSchema","true")csv("Netflix_2011_2016.csv")

## Terminal
df: org.apache.spark.sql.DataFrame = [Date: timestamp, Open: double ... 5 more fields] 
```
