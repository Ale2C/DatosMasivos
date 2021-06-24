# Evaluation 3
## Model Kmeans

1. Import a simple Spark session.
```scala
import org.apache.spark.sql.SparkSession
```

2. Use the lines of code to minimize errors
```scala
import org.apache.log4j._
Logger.getLogger("org").setLevel(Level.ERROR)
```

3. Create an instance of the Spark session
```scala
val spark1 = SparkSession.builder().getOrCreate()
```
4. Import the Kmeans library for the clustering algorithm.
```scala
import org.apache.spark.ml.clustering.KMeans
import org.apache.spark.ml.evaluation.ClusteringEvaluator
```
```scala
5. Load the Wholesale Customers Data dataset
val ds = spark.read.option("header", "true").option("inferSchema","true")csv("Wholesale customers data.csv")
```
6. Select the following columns: Fresh, Milk, Grocery, Frozen, 
```scala
Detergents_Paper, Delicassen and call this set feature_data
val feature_data = ds.select("Fresh", "Milk", "Grocery", "Frozen", "Detergents_Paper", "Delicassen").show()
```
7. Import Vector Assembler and Vector
```scala
import org.apache.spark.ml.feature.VectorAssembler
```
8. Create a new Vector Assembler object for the feature columns as an input set, remembering that there are no labels
```scala
val assembler = (new VectorAssembler().setInputCols(Array("Fresh","Milk", "Grocery","Frozen","Detergents_Paper","Delicassen")).setOutputCol("features"))
```
9. Use the assembler object to transform feature_data
```scala
val features = assembler.transform(feature_data)
```
10. Create a Kmeans model with K = 3
```scala
val kmeans = new KMeans().setK(3).setMaxIterations(10)
val model = kmeans.fit(features)
```
11. Evaluate the groups using Within Set Sum of Squared Errors WSSSE and print the centroids.
```scala
val WSSSE = model.computeCost(features)
println(s"Within set sum of Squared Errors = $WSSSE")

println("Cluster Centers: ")
model.clusterCenters.foreach(println)
```