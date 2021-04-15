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

// 7.- Create a new dataframe with a new column called "HV Ratio" what is the relationship between the price of the column "High" versus the "Volume" column of shares traded for a day (Hint: It is a column operation)
val df2 = df.withColumn("HV Ratio",df("High")/df("Volume"))

df2.select("HIGH","VOLUME","HV RATIO").show()

// 8-. Which day had the highest peak in the “Close” column?

df.select("Date", "Close").sort(desc("Close")).show(1)

// 9.- Write in your own words in a comment of your code. What is the meaning of the Close column "Close"? 

//Looking at the content of the DataFrame, we find that the values refer to Netflix stocks. 
//The Close column refers to the last value of the day of these stocks in question. 
//With contrast this Open which are the ones with which the day begins. 

// 10.- What is the maximum and minimum of the “Volume” column?

df.select(max("Volume")).show()

// 11.- With Scala / Spark $ syntax answer the following: 

// a. How many days was the “Close” column less than $ 600?
val dias = df.filter($"Close" < 600).count() 

// b. What percentage of the time was the “High” column greater than $ 500? 

// c. What is the Pearson correlation between column "High" and column "Volume"? 
df.select(corr("High", "Volume")).show()

// d. What is the maximum in the “High” column per year? 
val df2 = df.withColumn("Year", year(df("Date")))
val df3 = df2.select($"Year", $"High").groupBy("Year").max()
df3.select($"Year", $"max(High)").show()


// e. What is the “Close” column average for each calendar month?
val df2 = df.withColumn("Year", year(df("Date")))
val df3 = df2.select($"Year", $"High").groupBy("Year").max()
df3.select($"Year", $"max(High)").show()
