# Practice 1

## Basic Statistics

### Correlation
We load all the necessary libraries to access local arrays, factory methods for Vector, use the correlation method and access a row value.
``` scala
import org.apache.spark.ml.linalg.{Matrix, Vectors}
import org.apache.spark.ml.stat.Correlation
import org.apache.spark.sql.Row
```
Then we create the dense and sparse vectors from their values, within the matrix 
``` scala
val data = Seq(
  Vectors.sparse(4, Seq((0, 1.0), (3, -2.0))),
  Vectors.dense(4.0, 5.0, 0.0, 3.0),
  Vectors.dense(6.0, 7.0, 0.0, 8.0),
  Vectors.sparse(4, Seq((0, 9.0), (3, 1.0)))
)
```
- A dataframe is created to extract the data from our matrix.
- The Pearson correlation matrix is created using the dataframe we have just created and we ask for the first values with head. We print the result of the matrix in question. 
- The Spearman correlation matrix is created using the dataframe with the dataframe we created earlier, the first values are displayed using the head function. Print the result of the matrix in question.
``` scala
val df = data.map(Tuple1.apply).toDF("features")

val Row(coeff1: Matrix) = Correlation.corr(df, "features").head
println(s"Pearson correlation matrix:\n $coeff1")

val Row(coeff2: Matrix) = Correlation.corr(df, "features", "spearman").head
println(s"Spearman correlation matrix:\n $coeff2")

```
## Hypothesis testing

### ChiSquareTest
The following libraries are loaded to apply methods to vectors and also to perform the necessary calculations in this exercise
``` scala
import org.apache.spark.ml.linalg.{Vector, Vectors}
import org.apache.spark.ml.stat.ChiSquareTest

```
The sequence of dense vectors

``` scala
val data = Seq(
  (0.0, Vectors.dense(0.5, 10.0)),
  (0.0, Vectors.dense(1.5, 20.0)),
  (1.0, Vectors.dense(1.5, 30.0)),
  (0.0, Vectors.dense(3.5, 30.0)),
  (0.0, Vectors.dense(3.5, 40.0)),
  (1.0, Vectors.dense(3.5, 40.0))
)
```
- We create a new dataframe based on the vectors we loaded previously 
- The first values are taken from the previously created dataframe
<br>
- Starting with the parts of the test, the values of p 
- Then we look for the degrees of freedom of the model.
- Finally, certain values will be extracted from a given vector, all based on the chi-square function.

``` scala
val df = data.toDF("label", "features")
val chi = ChiSquareTest.test(df, "features", "label").head

println(s"pValues = ${chi.getAs[Vector](0)}")

println(s"degreesOfFreedom ${chi.getSeq[Int](1).mkString("[", ",", "]")}")

println(s"statistics ${chi.getAs[Vector](2)}")
```
### Summarizer

It is necessary to import the necessary libraries, in this use of vectors and summarizer.

``` scala
import org.apache.spark.ml.linalg.{Vector, Vectors}
import org.apache.spark.ml.stat.Summarizer
```
A set of vectors or sequence is created
```scala
val data = Seq(
  (Vectors.dense(2.0, 3.0, 5.0), 1.0),
  (Vectors.dense(4.0, 6.0, 7.0), 2.0)
)
```

- The summarizer library is used to obtain the mean and variance of some data in the requested dataframe.

- Previously worked variables are printed

- The process is repeated with 2 new variables 

- Printing of variables
``` scala
val df = data.toDF("features", "weight")

val (meanVal, varianceVal) = df.select(metrics("mean", "variance")
  .summary($"features", $"weight").as("summary"))
  .select("summary.mean", "summary.variance")
  .as[(Vector, Vector)].first()

println(s"with weight: mean = ${meanVal}, variance = ${varianceVal}")

val (meanVal2, varianceVal2) = df.select(mean($"features"), variance($"features"))
  .as[(Vector, Vector)].first()

println(s"without weight: mean = ${meanVal2}, sum = ${varianceVal2}")
```