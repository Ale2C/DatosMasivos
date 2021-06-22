# Gradient boosted tree clasissifier
## Practice 4
We import the libraries we use
```scala
import org.apache.spark.ml.Pipeline
import org.apache.spark.ml.classification.{GBTClassificationModel, GBTClassifier}
import org.apache.spark.ml.evaluation.MulticlassClassificationEvaluator
import org.apache.spark.ml.feature.{IndexToString, StringIndexer, VectorIndexer}
```
We load the txt file of the established path
```scala
val data = spark.read.format("libsvm").load("sample_libsvm_data.txt")
```

Create a column using stringIndexer for the data to have its categorization
```scala
val labelIndexer = new StringIndexer()
  .setInputCol("label")
  .setOutputCol("indexedLabel")
  .fit(data)
```  
Create a vector that will have a maximum of 4 categories
```scala
val featureIndexer = new VectorIndexer()
  .setInputCol("features")
  .setOutputCol("indexedFeatures")
  .setMaxCategories(4)
  .fit(data)
```
We separate the data into two parts, one called training with 70% and the other called mushroom test with 30%.
```scala
val Array(trainingData, testData) = data.randomSplit(Array(0.7, 0.3))
```
Train the GPT model
```scala
val gbt = new GBTClassifier()
  .setLabelCol("indexedLabel")
  .setFeaturesCol("indexedFeatures")
  .setMaxIter(10)
  .setFeatureSubsetStrategy("auto")
```
Convert undefined labels to original labels
```scala
val labelConverter = new IndexToString()
  .setInputCol("prediction")
  .setOutputCol("predictedLabel")
  .setLabels(labelIndexer.labels)
```
The chain of undeciders and GPT IN Pipeline
```scala
val pipeline = new Pipeline()
  .setStages(Array(labelIndexer, featureIndexer, gbt, labelConverter))
```
The model is delivered to the model. This also executes the indexers
```scala
val model = pipeline.fit(trainingData)
```
Create predictions.
```scala
val predictions = model.transform(testData)
```
Select the first 5 rows to display them
```scala
predictions.select("predictedLabel", "label", "features").show(5)
```
Select prediction and test error calculation.
```scala
val evaluator = new MulticlassClassificationEvaluator()
  .setLabelCol("indexedLabel")
  .setPredictionCol("prediction")
  .setMetricName("accuracy")
val accuracy = evaluator.evaluate(predictions)
println(s"Test Error = ${1.0 - accuracy}")

val gbtModel = model.stages(2).asInstanceOf[GBTClassificationModel]
println(s"Learned classification GBT model:\n ${gbtModel.toDebugString}")
```