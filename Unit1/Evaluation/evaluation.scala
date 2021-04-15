// 1.- Start a simple Spark session.
import org.apache.spark.sql.SparkSession

val spark = SparkSession.builder().getOrCreate()

// 2.- Load the Netflix Stock CSV file, make Spark infer the data types.

val df = spark.read.option("header", "true").option("inferSchema","true")csv("Netflix_2011_2016.csv")

// 3- What are the column names?
scala> df.columns

// 4- How is the scheme?
df.printSchema

// 5.- Print the first 5 columns.
df.head(5)

// 6.- Use describe () to learn about the DataFrama.
df.describe().show
