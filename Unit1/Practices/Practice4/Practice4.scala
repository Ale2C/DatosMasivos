// Practice 4, analyse the following code with your own words

// Code 1
// We have function listEvens that ir will accept the values
// of a list and those values will be of type Int. 
// We have String because it will print a message with the 
// value of "n", we have a for loop to see each of the numebers
// in the list and each number will be put on the condition if
// with that the number will be diveded by 2 and if the remainder
// is 0 then the number is even and shows the message of its condition
// if not then the number is odd and it shows the message that is off.
// Followig the completion of the for loop, it returns the message Done.
//  
def listEvens(list:List[Int]): String ={
    for(n <- list){
        if(n%2==0){
            println(s"$n is even")
        }else{
            println(s"$n is odd")
        }
    }
    return "Done"
}

// The creation of the values l and l2 with each of them having
// a list, with l having 8 numbes and l2 with 6 numbers.
// The way toingress the lists to the function
val l = List(1,2,3,4,5,6,7,8)
val l2 = List(4,3,22,55,7,8)
listEvens(l)
listEvens(l2)

// Code 2
// We have function afornutado that ir will receive lists 
// of type Int an it will return type int values.
// We declare a variable res with the value of 0
// Then it uses a for loop that will check all the numbers
// in the list, with if we see if the current number is equal 
// to seven, if the number is not 7 then it will be added
// with the value of res and res value will becamo the result 
// of the addition, on the contrary if the number is equal to
// 7 then the value of res is added with 14 and the value of res
// becomes the result of the addition.
// When the for loop finishes it will return current value of res.
def afortunado(list:List[Int]): Int={
    var res=0
    for(n <- list){
        if(n==7){
            res = res + 14
        }else{
            res = res + n
        }
    }
    return res
}

// Creation of value af with a list of 3 numbers and
// the way the ingress the value to the function.
val af= List(1,7,7)
println(afortunado(af))

// Code 3
/*
This is a function named "balance" which returns a list of integers and returns a Boolean.
Declare a pair of variables "primera" and "segunda", the latter is assigned the value of the sum of the list.

There is a for loop which is repeated from i = 0 to the length of the list, the "primera" variable is assigned its value plus the value of the list in position i and the "segunda" is assigned its minus value the value of the list in position i.

If "primera" equals "segunda" then it returns a true. If not, until the cycle is finished, a flash will return.

*/
def balance(list:List[Int]): Boolean={
    var primera = 0
    var segunda = 0

    segunda = list.sum

    for(i <- Range(0,list.length)){
        primera = primera + list(i)
        segunda = segunda - list(i)

        if(primera == segunda){
            return true
        }
    }
    return false 
}
//Here only the three lists are declared
val bl = List(3,2,1)
val bl2 = List(2,3,3,2)
val bl3 = List(10,30,90)
//Here the "balance" function is applied to each of the lists
balance(bl)
balance(bl2)
balance(bl3)

// Code 4
/*We have a function called "palindromo" which returns a value called "palabra" which is a string, this function will return a boolean.
It will only return true if the String is written the same from left to right as from left to right.
*/
def palindromo(palabra:String):Boolean ={
    return (palabra == palabra.reverse)
}
// The three words "OSO" "ANNA" and "JUAN" are declared.
val palabra = "OSO"
val palabra2 = "ANNA"
val palabra3 = "JUAN"
// The value of the function is printed depending on the one of the "words" that is inserted.
println(palindromo(palabra))
println(palindromo(palabra2))
println(palindromo(palabra3))
