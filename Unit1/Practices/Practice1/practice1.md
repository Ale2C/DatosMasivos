# Practice #1

## Exercise 1
Algorit that caulculates the radius of a circle

var radio: Double = 0;

def radioperimetro (perimetro:Double): Unit = {
    radio = perimetro / ( 2 * 3.1416);
    println(s"El radio del circulo es de: $radio%");
}

## Exercise 2
Use an Algorit to know if a number is prime or not

def isPrime(num:Int): Boolean = {
    for(n <- Range(2, num)){
        if(num%n == 0){
            return false
        }
    }
    return true
}

isPrime(4)
isPrime(7)

## Exercise 3
Use interpolation to make the variable bird = "tweet" to show "Im writing a tweet"

val bird = "tweet";
println(s"Estoy escribiendo un $bird")

## Exercise 4
Use slice to show part of String
`val menssage = ("Hello Luke, Im your father!")
menssage.slice(5,10)`