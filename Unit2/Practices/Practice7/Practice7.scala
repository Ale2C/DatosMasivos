// Import libraries
import org.apache.spark.ml.classification.{LogisticRegression, OneVsRest}
import org.apache.spark.ml.evaluation.MulticlassClassificationEvaluator

// Load the file
val inputData = spark.read.format("libsvm").load("sample_multiclass_classification_data.txt")

// Generate the division of the train and test set.
val Array(train, test) = inputData.randomSplit(Array(0.8, 0.2))

// Instantiate the base classifier
val classifier = new LogisticRegression().setMaxIter(10).setTol(1E-6).setFitIntercept(true)

// An instance of the One Vs Rest classifier is created.
val ovr = new OneVsRest().setClassifier(classifier)

// The multiclass model is trained.
val ovrModel = ovr.fit(train)

// The model is scored on the test data.
val predictions = ovrModel.transform(test)

// You get the evaluator
val evaluator = new MulticlassClassificationEvaluator().setMetricName("accuracy")

// The classification error is calculated on the test data.
val accuracy = evaluator.evaluate(predictions)
println(s"Test Error = ${1 - accuracy}")