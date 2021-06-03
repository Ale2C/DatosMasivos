// Import the librarys needded
import org.apache.spark.ml.classification.NaiveBayes
import org.apache.spark.ml.evaluation.MulticlassClassificationEvaluator
import org.apache.spark.sql.SparkSession

// Load data in LIBSVM storage format as DataFrame.
val data = spark.read.format("libsvm").load("C:/Users/brise/Documents/GitHub/NaiveBayes/sample_libsvm_data.txt")

println ("Numero de lineas en el archivo de datos:" + data.count ())

// Display 20 lines by default
data.show()

// Randomly divide the data set into training set and test set according to the weights provided. You can also specify a seed
val Array (trainingData, testData) = data.randomSplit (Array (0.7, 0.3), 100L) // The result is the type of the array, and the array stores the data of type DataSet

// Incorporate into the training set (fitting operation) to train a Bayesian model
val naiveBayesModel = new NaiveBayes().fit(trainingData)

// The model calls transform () to make predictions and generate a new DataFrame.
val predictions = naiveBayesModel.transform(testData)

// Prediction results data output
predictions.show()
 
//Model accuracy assessment
val evaluator = new MulticlassClassificationEvaluator().setLabelCol("label").setPredictionCol("prediction").setMetricName("accuracy")

val precision = evaluator.evaluate (predictions) // Precisión

println ("tasa de error =" + (1-precision))