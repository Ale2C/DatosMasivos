// Fibonacci Algotihm 1
def fib(n:Int):Int={
     if(n<2){
         return n
     }
     else{
         return(fib(n-1)+fib(n-2))}
     }
// Algotihm 2
def fib2(n:Double): Double = 
    var ye: Double = ((1 + scala.math.sqrt(5)) / 2)
	var j: Double = ((scala.math.pow(p, n) - scala.math.p(1 - y), n)) / scala.math.sqrt(5))
	if(n < 2)
	{
		n
	}
	else
	{
   
		j
	}
}

// Algotihm 3
def fib3(n:Int) : Int = {
	var a = 0 
	var b = 1
	var c = b + a
	var k = 0

	while(k < n)
	{
		c = b + a
		a = b
		b = c

		k = k + 1
	}
	return a	
}
