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

val data = spark.read.csv("iris.csv")

//2. ¿Cuáles son los nombres de las columnas?
data.columns //c0, c1, c2, c3, c4, c5

//3. ¿Cómo es el esquema?
data.printschema

//4. Imprime las primeras 5 columnas.
data.show() 

//5. Usa el metodo describe () para aprender mas sobre los datos del DataFrame.
data.describe().show()

//6. Haga la transformación pertinente para los datos categoricos los cuales serán
//nuestras etiquetas a clasificar.

//7. Construya el modelo de clasificación y explique su arquitectura.

//8. Imprima los resultados del modelo