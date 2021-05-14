// 1.- Use an algorit to calculate the radius of a circle
var radio: Double = 0;

def radioperimetro (perimetro:Double): Unit = {
    radio = perimetro / ( 2 * 3.1416);
    println(s"El radio del circulo es de: $radio%");
}


// 2.- Use an algorit to know if a number is prime or not
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


// 3.- Use interpolation to make the variable bird = "tweet" to show "Im writing a tweet"
val bird = "tweet";
println(s"Estoy escribiendo un $bird")

// 4.- Exercise 4 Use slice to show part of String

val menssage = ("Hello Luke, I'm your father!")
menssage.slice(5,10)

//Exercise 5 What is the difference between value and a variable in scala?
 // val are constant which means that we cannot modify its value, while var if we can modify its value throughout the program

// Exerce 6 Given the tuple (2,4,5,1,2,3,3.1416,23) returns the number 3.1416
val tupla = (2,4,5,1,2,3,3.1416,23)
tupla._7