# Practice 2
## Decision Tree
The following example loads a dataset and splits it into test and training sets.

Import the necessary libraries 
``` scala
import  org.apache.spark.ml.Pipeline 
import  org.apache.spark.ml.classification.DecisionTreeClassificationModel 
import  org.apache.spark.ml.classification.DecisionTreeClassifier 
import  org.apache.spark.ml.evaluation.MulticlassClassificationEvaluator 
import  org.apache .spark.ml.feature. { IndexToString ,  StringIndexer ,  VectorIndexer }
import  org.apache.spark.sql.SparkSession
```
Load data stored in LIBSVM format as a DataFrame.
It is implemented for optimization, supports sorting and regression. 
``` scala
val data = spark.read.format("libsvm").load("/home/eduardo/Escritorio/expo/sample_libsvm_data.txt")
```

Index tags, adding metadata to the tag column. 
Fit in the full dataset to include all tags in the index. 
``` scala
val labelIndexer = new StringIndexer().setInputCol("label").setOutputCol("indexedLabel").fit(data)
```
Automatically identifies categorical features and indexes them. 
``` scala
val featureIndexer = new VectorIndexer().setInputCol("features").setOutputCol("indexedFeatures").setMaxCategories(4).fit(data)
```
Divide the data into test and training sets (30% reserved for testing). 
``` scala
val Array(trainingData, testData) = data.randomSplit(Array(0.7, 0.3))
```
Train a DecisionTree model. 
``` scala
val dt = new DecisionTreeClassifier().setLabelCol("indexedLabel").setFeaturesCol("indexedFeatures")
```
Convert indexed tags back to original tags.
```scala
val labelConverter = new IndexToString().setInputCol("prediction").setOutputCol("predictedLabel").setLabels(labelIndexer.labels)
```
Chain of indexers and tree in a Pipeline. 
```scala
val pipeline = new Pipeline().setStages(Array(labelIndexer, featureIndexer, dt, labelConverter))
```
Train model. This also executes the indexers. 
```scala
val model = pipeline.fit(trainingData)
```

Making predictions. 
```scala
val predictions = model.transform(testData)
```
Select example rows to display. In this case there will only be 5
```scala
predictions.select("predictedLabel", "label", "features").show(10)
```
Select (prediction, true label).
```scala
val evaluator = new MulticlassClassificationEvaluator().setLabelCol("indexedLabel").setPredictionCol("prediction").setMetricName("accuracy")
```

Calculate the test error. 
```scala
val accuracy = evaluator.evaluate(predictions)
println(s"Test Error = ${(1.0 - accuracy)}")
```
Show by stages the classification of the tree model
```scala
val treeModel = model.stages(2).asInstanceOf[DecisionTreeClassificationModel]
println(s"Learned classification tree model:\n ${treeModel.toDebugString}")
```