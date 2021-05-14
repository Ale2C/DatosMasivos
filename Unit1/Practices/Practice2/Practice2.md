# Practice #2

## Exercise 1
Create a list called "lsita" with the elements "rojo", "blanco", "negro"

val lista = List("rojo","blanco","negro")
lista.toSet

## Exercise 2
Add the following elements to "lista" “verde”, “amarillo”, “azul”, “naranja”, “perla”

val lista1 = "verde" :: lista
val lista2 = "amarillo" :: lista1
val lista3 = "azul" :: lista2
val lista4 = "naranja" :: lista3
val lista5 = "perla" :: lista4

## Exercise 3
Add the elements of "lista", “verde”, “amarillo”, “azul”

lista5 slice (3, 6)

## Exercise 4
Create an array of range of 1 to 1000 with steps of 5 on 5

val arr = 1 to 1000 by 5

## Exercise 5

`val list = List (1,3,3,4,6,7,3,7)`

`list.toSet`

## Exercise 6
Create a mutable map named names that contains the following "Jose", 20, "Luis", 24, "Ana", 23, "Susana", "27"

`val map = collection.mutable.Map( ("Jose",20), ("Luis", 24), ("Ana", 23), ("Susana", "27") ) `

### 6a 

`map.keys`

### 7b 

`map += ("Miguel" -> 23)`