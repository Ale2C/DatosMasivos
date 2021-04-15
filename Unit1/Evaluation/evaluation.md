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

## 3- What are the column names?
<div align="Justify">
There are 7 columns, Date, Open, High, Low, Close, Volume, Adj Close
</div>

``` scala
df.columns

## Terminal
res0: Array[String] = Array(Date, Open, High, Low, Close, Volume, Adj Close)
``` 


## 4- How is the scheme?
<div align="Justify">
Using the function printSchema it shows us the name of the columns and the data type
</div>

``` scala
df.printSchema

## Terminal
root
 |-- Date: timestamp (nullable = true)
 |-- Open: double (nullable = true)
 |-- High: double (nullable = true)
 |-- Low: double (nullable = true)
 |-- Close: double (nullable = true)
 |-- Volume: integer (nullable = true)
 |-- Adj Close: double (nullable = true)
``` 


##  5.- Print the first 5 columns.
<div align="Justify">
Using the function of head, it can specify the number of columns that will the shown
</div>

``` scala
df.head(5)

## Terminal
res2: Array[org.apache.spark.sql.Row] = Array([2011-10-24 00:00:00.0,119.100002,120.28000300000001,115.100004,118.839996,120460200,16.977142], [2011-10-25 00:00:00.0,74.899999,79.390001,74.249997,77.370002,315541800,11.052857000000001], [2011-10-26 00:00:00.0,78.73,81.420001,75.399997,79.400002,148733900,11.342857], [2011-10-27 00:00:00.0,82.179998,82.71999699999999,79.249998,80.86000200000001,71190000,11.551428999999999], [2011-10-28 00:00:00.0,80.280002,84.660002,79.599999,84.14000300000001,57769600,12.02])
``` 

##  6.- Use describe () to learn about the DataFrama.
<div align="Justify">
The function of describe will show statistics for the selected columns in this case as we didnt name a specific column it will show all.
</div>

``` scala
df.describe().show
``` 

``` scala
## Terminal
+-------+------------------+------------------+------------------+------------------+--------------------+------------------+
|summary|              Open|              High|               Low|             Close|              Volume|         Adj Close|
+-------+------------------+------------------+------------------+------------------+--------------------+------------------+
|  count|              1259|              1259|              1259|              1259|                1259|              1259|
|   mean|230.39351086656092|233.97320872915006|226.80127876251044|  230.522453845909|2.5634836060365368E7|55.610540036536875|
| stddev|164.37456353264244| 165.9705082667129| 162.6506358235739|164.40918905512854| 2.306312683388607E7|35.186669331525486|
|    min|         53.990001|         55.480001|             52.81|              53.8|             3531300|          7.685714|
|    max|        708.900017|        716.159996|        697.569984|        707.610001|           315541800|        130.929993|
+-------+------------------+------------------+------------------+------------------+--------------------+------------------+
``` 


##  7.- Create a new dataframe with a new column called "HV Ratio" what is the relationship between the price of the column "High" versus the "Volume" column of shares traded for a day (Hint: It is a column operation)
<div align="Justify">
First create the dataframe df2 from the dataframa df and the calculate the ratio of HIGH and Volume with a division 
</div>
``` scala
val df2 = df.withColumn("HV Ratio",df("High")/df("Volume"))
``` 


<div align="Justify">
Now that we have a new dataframe, we use the function select and type the column HIGH, VOLUME and HV RATIO, so we can see the columns that are needed
</div>
``` scala
df2.select("HIGH","VOLUME","HV RATIO").show()
``` 

``` scala
## Terminal
+------------------+---------+--------------------+
|              HIGH|   VOLUME|            HV RATIO|
+------------------+---------+--------------------+
|120.28000300000001|120460200|9.985040951285156E-7|
|         79.390001|315541800|2.515989989281927E-7|
|         81.420001|148733900|5.474206014903126E-7|
| 82.71999699999999| 71190000|1.161960907430818...|
|         84.660002| 57769600|1.465476686700271...|
|         84.090002| 39653600|2.120614572195210...|
|         80.999998| 33016200|2.453341026526372E-6|
|         84.400002| 41384000|2.039435578967717E-6|
|         92.600003| 94685500| 9.77974483949496E-7|
| 92.89000300000001| 84483700|1.099502069629999...|
|         93.839998| 47485200|1.976194645910725...|
|         92.600003| 31906000|2.902275528113834...|
|         90.440001| 28756000|3.145082800111281E-6|
| 90.29999699999999| 39614400|2.279474054889131E-6|
|         87.949997| 38140200|2.305965805108520...|
|              88.1| 21811300|4.039190694731629...|
|         87.050003| 21372400|4.073010190713256...|
|         86.460003| 34560400|2.501707242971725E-6|
|         80.999998| 52823400|1.533411291208063...|
|         78.999999| 34729100|2.274749388841058...|
+------------------+---------+--------------------+
``` 


