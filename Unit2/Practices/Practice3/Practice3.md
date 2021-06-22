# Practice 3
## Random forest classifier
The present code is from Random forest classifier a machine learning algorithm

Import libraries
``` scala
import org.apache.spark.ml.Pipeline
import org.apache.spark.ml.classification.{RandomForestClassificationModel, RandomForestClassifier}
import org.apache.spark.ml.evaluation.MulticlassClassificationEvaluator
import org.apache.spark.ml.feature.{IndexToString, StringIndexer, VectorIndexer}
import org.apache.spark.sql.SparkSession
```

Create a SparkSession, and also we declare the calue spark that will represent the sparksession
``` scala
val spark = SparkSession.builder().getOrCreate()
```

Load data and convert to DataFrame, using value data we load the file sample_libsvm_data.txt to make the necesary changes and we use printSchema and show to display the data from the file
``` scala
val data = spark.read.format("libsvm").load("data/mllib/sample_libsvm_data.txt")

data.printSchema()
data.show()
```

Creation of the label index and adjust index to the dataframe
``` scala
val labelIndexer = new StringIndexer().setInputCol("label").setOutputCol("indexedLabel").fit(data)
```

Identify and index the features
Features values > 4 are continuous.
``` scala
val featureIndexer = new VectorIndexer().setInputCol("features").setOutputCol("indexedFeatures").setMaxCategories(4).fit(data)
```

Split the data into training and test sets (70% training and 30% for tests)
``` scala
val Array(trainingData, testData) = data.randomSplit(Array(0.7, 0.3))
```

Create a RandomForest model.
``` scala
val rf = new RandomForestClassifier().setLabelCol("indexedLabel").setFeaturesCol("indexedFeatures").setNumTrees(10)
```

Convert indexed labels back to original labels.
``` scala
val labelConverter = new IndexToString().setInputCol("prediction").setOutputCol("predictedLabel").setLabels(labelIndexer.setLabels)
```

Pipeline with chain indexers and forest.
``` scala
val pipeline = new Pipeline().setStages(Array(labelIndexer, featureIndexer, rf, labelConverter))
```

Fit Model.
``` scala
val model = pipeline.fit(trainingData)
```

Make predictions.
``` scala
val predictions = model.transform(testData)
```