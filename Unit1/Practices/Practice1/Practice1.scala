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
