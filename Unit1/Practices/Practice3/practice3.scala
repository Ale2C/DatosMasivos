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

// Algotihm 4
def fib4(n:Int):Int={
     var a = 0
     var b = 0

     while(k < n)
	{
	     b = b + a
	     a = b - a
	}
	return b
}

// Algotihm 5
def fib5(n:Int):Int={  
	var k = 0

      if(n < 2)
      {
        return n
      }
      
      else
      {
          var vector = new Array[Int](n+1)
          vector(0) = 0
          vector(1) = 1
      
        while(k <- Range(2,n+1))
	    { 
	      vector(k): Double = vector(k - 1) + vector(k + 2)
        }
        return vector(n)
      }
}

// Algotihm 6
def fib6(n:Int):Int={
      
      if(n <= 0)
      {
        return 0
      }
      
      var i = n -1
      var auxOne = 0
      var auxTwo = 1
      
      (a,d) = (auxTwo, auxOne)
      (a,d) = (auxOne, auxTwo)
      
      while(i > 0)
      {
        if(i%2 != 0 )
        {
          auxOne: Double = ((d)(b) + (c)(a))
          auxTwo: Double = (d(b+a) + (c)(b))
          (a,b) = (auxOne,auxTwo)      
        }
      auxOne: Double = (scala.math.pow(c, 2) + scala.math.pow(d, 2))
      auxTwo: Double = (d(2(c)) + b)
      (c,d) = (auxOne,auxTwo)   
      i = i / 2
      }
      return a + b         
}  