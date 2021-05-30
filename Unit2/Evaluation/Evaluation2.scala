/*Desarrollar las siguientes instrucciones en Spark con el leguaje de programación Scala,
utilizando solo la documentacion de la librería de Machine Learning Mllib de Spark y
Google.*/

/*1. Cargar en un dataframe Iris.csv que se encuentra en
https://github.com/jcromerohdz/iris, elaborar la liempieza de datos necesaria para ser
procesado por el siguiente algoritmo (Importante, esta limpieza debe ser por
medio de un script de Scala en Spark) .*/

// a. Utilice la librería Mllib de Spark el algoritmo de Machine Learning correspondiente
// a multilayer perceptron
import org.apache.spark.ml.classification.MultilayerPerceptronClassifier
import org.apache.spark.ml.evaluation.MulticlassClassificationEvaluator
import org.apache.spark.sql.SparkSession
import org.apache.spark.ml.feature.{IndexToString, StringIndexer, VectorIndexer}
import org.apache.spark.ml.feature.VectorAssembler
import org.apache.spark.ml.feature.StringIndexer
import org.apache.spark.ml.Pipeline

val data = spark.read.option("header", "true").option("inferSchema","true")csv("iris.csv")

//2. ¿Cuáles son los nombres de las columnas?
data.columns //c0, c1, c2, c3, c4, c5

//3. ¿Cómo es el esquema?
data.printSchema()

//4. Imprime las primeras 5 columnas.
data.show(5) 

//5. Usa el metodo describe () para aprender mas sobre los datos del DataFrame.
data.describe().show()

//6. Haga la transformación pertinente para los datos categoricos los cuales serán
//nuestras etiquetas a clasificar.
val assembler = new VectorAssembler() .setInputCols(Array("sepal_length", "sepal_width", "petal_length", "petal_width")).setOutputCol("features")
val features = assembler.transform(data)
features.show(5)

val labelIndexer = new StringIndexer().setInputCol("species").setOutputCol("indexedLabel").fit(features)
println(s"Found labels: ${labelIndexer.labels.mkString("[", ", ", "]")}")

val featureIndexer = new VectorIndexer().setInputCol("features").setOutputCol("indexedFeatures").setMaxCategories(4).fit(features)
//7. Construya el modelo de clasificación y explique su arquitectura.
//Dividir los datos en conjuntos de entrenamiento y de prueba
val splits = features.randomSplit(Array(0.6, 0.4))
val trainingData = splits(0)
val testData = splits(1)
//Especificar capas para la red neuronal: capa de entrada de tamaño 4 (características), 
//dos intermedias de tamaño 6 y 4 y de salida de tamaño 3 (clases)
val layers = Array[Int](4, 6, 4, 3)
//Crear el entrenador y establecer sus parámetros
val trainer = new MultilayerPerceptronClassifier().setLayers(layers).setLabelCol("indexedLabel").setFeaturesCol("indexedFeatures").setBlockSize(128).setSeed(System.currentTimeMillis).setMaxIter(100)
//Convierte las etiquetas indexadas en etiquetas originales.
val labelConverter = new IndexToString().setInputCol("prediction").setOutputCol("predictedLabel").setLabels(labelIndexer.labels)
//Indexadores en cadena y ClasificadorPerceptrón de Capa Múltiple en una tubería.
val pipeline = new Pipeline().setStages(Array(labelIndexer, featureIndexer, trainer, labelConverter))
//Entrena el modelo. Esto también ejecuta los indexadores.
val model = pipeline.fit(trainingData)
//Hacer predicciones.
val predictions = model.transform(testData)
predictions.show(5)
//Seleccione (predicción, etiqueta verdadera) y calcule el error de la prueba.
val evaluator = new MulticlassClassificationEvaluator().setLabelCol("indexedLabel").setPredictionCol("prediction").setMetricName("accuracy")
val accuracy = evaluator.evaluate(predictions)
//8. Imprima los resultados del modelo
println("Test Error = " + (1.0 - accuracy))