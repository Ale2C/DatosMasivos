// Import libraries
import org.apache.spark.ml.Pipeline
import org.apache.spark.ml.classification.{RandomForestClassificationModel, RandomForestClassifier}
import org.apache.spark.ml.evaluation.MulticlassClassificationEvaluator
import org.apache.spark.ml.feature.{IndexToString, StringIndexer, VectorIndexer}
import org.apache.spark.sql.SparkSession

// Create a SparkSession
// We declare the calue spark that will represent the sparksession
val spark = SparkSession.builder().getOrCreate()

// Load data and convert to DataFrame
// Using value data we load the file sample_libsvm_data.txt to make the necesary changes
// and we use printSchema and show to display the data from the file
val data = spark.read.format("libsvm").load("data/mllib/sample_libsvm_data.txt")

data.printSchema()
data.show()

// Creation of the label index
// Adjust index to the dataframe
val labelIndexer = new StringIndexer().setInputCol("label").setOutputCol("indexedLabel").fit(data)

// Identify and index the features
// Features values > 4 are continuous.
val featureIndexer = new VectorIndexer().setInputCol("features").setOutputCol("indexedFeatures").setMaxCategories(4).fit(data)

// Split the data into training and test sets (70% training and 30% for tests)
val Array(trainingData, testData) = data.randomSplit(Array(0.7, 0.3))

// Create a RandomForest model.
val rf = new RandomForestClassifier().setLabelCol("indexedLabel").setFeaturesCol("indexedFeatures").setNumTrees(10)

// Convert indexed labels back to original labels.
val labelConverter = new IndexToString().setInputCol("prediction").setOutputCol("predictedLabel").setLabels(labelIndexer.setLabels)

// Pipeline with chain indexers and forest.
val pipeline = new Pipeline().setStages(Array(labelIndexer, featureIndexer, rf, labelConverter))

// Fit Model.
val model = pipeline.fit(trainingData)

// Make predictions.
val predictions = model.transform(testData)