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