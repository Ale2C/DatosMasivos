## Evaluation Unit 2

## 1.- Load into an Iris.csv dataframe found in https://github.com/jcromerohdz/iris, prepare the necessary data to be processed by the following algorithm (Important, this cleaning must be by middle of a Scala script in Spark)

##  a. Use the Spark Mllib library the corresponding Machine Learning algorithm use a multilayer perceptron
<div align="Justify">
First we need the necesary libraries, for this case we will use speciality use MultilayerPerceptronClassifier
and MulticlassClassificationEvaluator because of this problem focus on the algorithm multilayer perceptron classifier. 
</div>
``` scala 
import org.apache.spark.ml.classification.MultilayerPerceptronClassifier
import org.apache.spark.ml.evaluation.MulticlassClassificationEvaluator
import org.apache.spark.sql.SparkSession
import org.apache.spark.ml.feature.{IndexToString, StringIndexer, VectorIndexer}
import org.apache.spark.ml.feature.VectorAssembler
import org.apache.spark.ml.feature.StringIndexer
import org.apache.spark.ml.Pipeline

## Terminal
spark: import org.apache.spark.ml.classification.MultilayerPerceptronClassifier
spark: import org.apache.spark.ml.evaluation.MulticlassClassificationEvaluator
spark: import org.apache.spark.sql.SparkSession
spark: import org.apache.spark.ml.feature.{IndexToString, StringIndexer, VectorIndexer}
spark: import org.apache.spark.ml.feature.VectorAssembler
spark: import org.apache.spark.ml.feature.StringIndexer
spark: import org.apache.spark.ml.Pipeline
```


<div align="Justify">
We use spark.read.option code to load the iris.csv file and aply the following questions
</div>
``` scala 
val data = spark.read.option("header", "true").option("inferSchema","true")csv("iris.csv")

## Terminal
data: org.apache.spark.sql.DataFrame = [sepal_length: double, sepal_width: double ... 3 more fields]
```

## 2.- What are the name of the columns?
<div align="Justify">
Using the value data we use the command columns to show the name of all the columns in the iris.csv file
</div>
``` scala 
data.columns 

## Terminal
res0: Array[String] = Array(sepal_length, sepal_width, petal_length, petal_width, species)
```

## 3.- ¿How is the schema?
<div align="Justify">
To see the structure of the schema we use the command printSchema() so we can see it on the terminal
</div>

``` scala 
data.printSchema()

## Terminal
scala> data.printSchema()
root
 |-- sepal_length: double (nullable = true)
 |-- sepal_width: double (nullable = true)
 |-- petal_length: double (nullable = true)
 |-- petal_width: double (nullable = true)
 |-- species: string (nullable = true)
```

## 4.- Print the first five columns
<div align="Justify">
Using the command show() displays the columns we need and its contents
</div>

``` scala 
data.show(5) 

## Terminal
scala> data.show()
+------------+-----------+------------+-----------+-------+
|sepal_length|sepal_width|petal_length|petal_width|species|
+------------+-----------+------------+-----------+-------+
|         5.1|        3.5|         1.4|        0.2| setosa|
|         4.9|        3.0|         1.4|        0.2| setosa|
|         4.7|        3.2|         1.3|        0.2| setosa|
|         4.6|        3.1|         1.5|        0.2| setosa|
|         5.0|        3.6|         1.4|        0.2| setosa|
|         5.4|        3.9|         1.7|        0.4| setosa|
|         4.6|        3.4|         1.4|        0.3| setosa|
|         5.0|        3.4|         1.5|        0.2| setosa|
|         4.4|        2.9|         1.4|        0.2| setosa|
|         4.9|        3.1|         1.5|        0.1| setosa|
|         5.4|        3.7|         1.5|        0.2| setosa|
|         4.8|        3.4|         1.6|        0.2| setosa|
|         4.8|        3.0|         1.4|        0.1| setosa|
|         4.3|        3.0|         1.1|        0.1| setosa|
|         5.8|        4.0|         1.2|        0.2| setosa|
|         5.7|        4.4|         1.5|        0.4| setosa|
|         5.4|        3.9|         1.3|        0.4| setosa|
|         5.1|        3.5|         1.4|        0.3| setosa|
|         5.7|        3.8|         1.7|        0.3| setosa|
|         5.1|        3.8|         1.5|        0.3| setosa|
+------------+-----------+------------+-----------+-------+
only showing top 20 rows

```

## 5.- Use the describe () method to learn more about the data in the DataFrame.
<div align="Justify">
Using both the describe() and show() commands allows us to see the data in a more detail way to see the data of
iris.csv file 
</div>
``` scala 
data.describe().show()

## Terminal
+-------+------------------+-------------------+------------------+------------------+---------+
|summary|      sepal_length|        sepal_width|      petal_length|       petal_width|  species|
+-------+------------------+-------------------+------------------+------------------+---------+
|  count|               150|                150|               150|               150|      150|
|   mean| 5.843333333333335| 3.0540000000000007|3.7586666666666693|1.1986666666666672|     null|
| stddev|0.8280661279778637|0.43359431136217375| 1.764420419952262|0.7631607417008414|     null|
|    min|               4.3|                2.0|               1.0|               0.1|   setosa|
|    max|               7.9|                4.4|               6.9|               2.5|virginica|
+-------+------------------+-------------------+------------------+------------------+---------+
```

## 6.- Make the relevant transformation for the categorical data which will be our labels to classify.

``` scala 
val assembler = new VectorAssembler() .setInputCols(Array("sepal_length", "sepal_width", "petal_length", "petal_width")).setOutputCol("features")
val features = assembler.transform(data)
features.show(5)

val labelIndexer = new StringIndexer().setInputCol("species").setOutputCol("indexedLabel").fit(features)
println(s"Found labels: ${labelIndexer.labels.mkString("[", ", ", "]")}")

val featureIndexer = new VectorIndexer().setInputCol("features").setOutputCol("indexedFeatures").setMaxCategories(4).fit(features)
```

## 7.- Build the classification model and explain its architecture.

<div align="Justify">
Divide the data into training and test sets
</div>

``` scala 
val splits = features.randomSplit(Array(0.6, 0.4))
val trainingData = splits(0)
val testData = splits(1)
```

<div align="Justify">
Specify layers for the neural network: input layer of size 4 (features),
two intermediate size 6 and 4 and output size 3 (classes)
</div>

``` scala 
val layers = Array[Int](4, 6, 4, 3)
```

<div align="Justify">
Create the trainer and set its parameters
</div>

``` scala 
val trainer = new MultilayerPerceptronClassifier().setLayers(layers).setLabelCol("indexedLabel").setFeaturesCol("indexedFeatures").setBlockSize(128).setSeed(System.currentTimeMillis).setMaxIter(100)
```

<div align="Justify">
Convert indexed tags to original tags.
</div>

``` scala 
val labelConverter = new IndexToString().setInputCol("prediction").setOutputCol("predictedLabel").setLabels(labelIndexer.labels)
```

<div align="Justify">
Chain Indexers and Multi-Layer Perceptron Classifier in a pipeline.
</div>

``` scala 
val pipeline = new Pipeline().setStages(Array(labelIndexer, featureIndexer, trainer, labelConverter))
``` 

<div align="Justify">
Train the model. This also runs the indexers.
</div>

``` scala 
val model = pipeline.fit(trainingData)
``` 

<div align="Justify">
Make predictions.
</div>

``` scala 
val predictions = model.transform(testData)
predictions.show(5)
``` 

<div align="Justify">
Select (prediction, true label) and calculate the error of the test.
</div>

``` scala 
val evaluator = new MulticlassClassificationEvaluator().setLabelCol("indexedLabel").setPredictionCol("prediction").setMetricName("accuracy")
val accuracy = evaluator.evaluate(predictions)
``` 

## 8.- Print the model results

``` scala 
println("Test Error = " + (1.0 - accuracy))
``` 

