/*
 * CSCI 3155: Lab 2 Worksheet
 *
 * This worksheet demonstrates how you could experiment
 * interactively with your implementations in Lab2.scala.
 */

///////////////////////////////////////////////////////////////

/*
CASE PATTERN MATCHING: 

************
Definition:
************

  Pattern matching is a mechanism for checking a value against a pattern. 
  A successful match can also deconstruct a value into its constituent parts. 
  It is a more powerful version of the switch statement in Java and it can likewise be 
  used in place of a series of if/else statements.

Pattern matching is a control structure we will use many times in this class. 
Let's see some of the ways we can use pattern matching.

Additional reference: https://docs.scala-lang.org/tour/pattern-matching.html

*/

/* 
Exercise 1: 

We use case pattern matching to match the given list "myList" which contains 
Go through the below code and answer the following questions:

  a. Which case will be matched for the given list "myList" ?

  b. Add an additional case for extracting the two elements present in the list.

*/

// Uncomment the below code (lines 43 to 51) after answering (1a) 

// val myList = 200 :: 300 :: Nil
// myList match {
//     case Nil => "List is empty"
//     case a :: Nil => s"List contains single element which is $a"
//     // Begin solution

//     // End solution
//     case a :: _ => s"List has multiple elements starting with $a"
// }


/*********************************************************************************************/
// APPLICATION OF CASE PATTERN MATCHING
/*********************************************************************************************/

// Define traits and case classes
sealed trait Expr
sealed trait Uop
sealed trait Bop

case class Num(n: Double) extends Expr
case class Unary(uop: Uop, e1: Expr) extends Expr
case class Binary(bop: Bop, e1: Expr, e2: Expr) extends Expr

// Unary
case object Neg extends Uop 

// Binary
case object Plus extends Bop 
case object Minus extends Bop
case object Times extends Bop
case object Div extends Bop

// Examples for Expression
// exp1 = 10 + 15
val exp1: Expr = Binary(Plus, Num(10), Num(15))

// exp2 = (-10 * 5) / 2
val exp2: Expr = Binary(Div, Binary(Times, Unary(Neg, Num(10)), Num(5)), Num(2))

// Exercise: 2
// exp3 = (5 * (4 + 10)) - (4 / 2)
val exp3: Expr = ???

/*
Exercise 3
Complete the below function to get the numbers involved in the expression as a List
*/

def getConstants(e: Expr): List[Double] = {
  // Begin Solution
  ???
  // End Solution
}

// Test Cases
getConstants(exp1)
getConstants(exp2)
getConstants(exp3)