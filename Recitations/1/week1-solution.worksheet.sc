/*
 * CSCI 3155: Lab 1 Worksheet
 *
 * This worksheet demonstrates how you could experiment
 * interactively with your implementations in Lab1.scala.
 */

///////////////////////////////////////////////////////////////

/*
* VAL vs. VAR:
* In scala, there are two types of variable declarations (outside of stating the type of a variable) val and var
*   - val is immutable
*   - var is mutable
*/

val x = 3
// x = x + 1 // -> Uncomment this and try

var y = 3
y = y + 1

println(y)

/*
 * Functions: 
  Functions in scala are defined a lot like python3 where the syntax is 
      def <function name> (parameters):[function return type] = {
              code body...
          }
  so to define a function called plusOne that takes in an integer and returns a number, the function definition looks like 

  Note: scala doesn't really need the return key word, the functions works fine without them.
 */

def plusOne(x:Int ): Int = {
  x + 1;
}

plusOne(1)

///////////////////////////////////////////////////////////////
/**
*  NOTE: In scala, a parameter passed into the function is DEFAULT val (immutable), given the plusOne example above, it will give the same
*  reassignment error when we tried to modify a val 
*/
///////////////////////////////////////////////////////////////

def plusOneWontWork(x:Int ): Int = {
  // x = x + 1; // -> Uncomment this and try
  return x;
} 

plusOneWontWork(10)


///////////////////////////////////////////////////////////////
// SCOPE
/* 
 *   Programs define and use identifiers (these can be constant vals or mutable vars) all over the place.
 *  The scope of a definition/declaration in a program specifies where in the program that particular definition/declaration is used. 
 */
//////////////////////////////////////////////////////////////
val number = 0;
def printNumberOne() {
  val number = 1
  println(number)
}
// What is going to be printed?
printNumberOne() 

// What is going to be printed here?
println(number)

// Let's try to answer 2(a) for the checkpoint

//////////////////////////////////////////////////////////////
/* 
 * Let's try and implement the Floor function 
 * Floor function can be simply defined as f(x) = x - x mod 1
 * 
 * What is the operator for mod?
 */
//////////////////////////////////////////////////////////////
def floor(x:Double) : Double = (x) - (x % 1)

// Let's try to write the 4(a) for the checkpoint
floor(1.5)