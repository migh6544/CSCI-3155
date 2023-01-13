/*
 *  CSCI 3155: Week 6 Recitation Worksheet
 *  Topics covered in this recitation.
 *  > Small Step Semantics Evaluation & Substitution 
 *  > Short Circuiting
 * 
 *  Note: 
 *  Should be placed in lab3/src/main/scala/jsy/student/
 */

 import  jsy.lab3.Parser 
// Imports the parse function from jsy.lab3.Parser
import jsy.lab3.Parser.parse

// Imports the ast nodes
import jsy.lab3.ast._

// Imports all of the functions form jsy.student.Lab3 (your implementations in Lab3.scala)
import jsy.student.Lab3._
import jsy.lab3.Lab3Like

/* 
*********************
Small Step Semantics:
*********************

A small-step interpreter makes explicit the evaluation order of expressions.

*************
Substitution: 
*************

Substitution is a simple way to get lexical scoping. Substitution replaces all occurences of a 
given variable 'x' with its value 'v' in an expression 'e'

*/

// Exercise 1: Write the Step evaluation for the inputs provided below. 

/* Note: 
    Fill in the expected values for each step in Lab3RecitationWeek6Spec.scala file provided.
    step/exp3-1 and step/exp3-1 are already solved for your reference
*/  

/*
Example 1: 
    const x = 5; x * 10;
*/

/*
Example 2: 
    const f = function (y) { return 2 * y }
    f(4)
*/

/*
Example 3: 
    const x = 5 
    console.log(x); const f = function(x) { return x * x }
    f(7)
*/

/*
Short Circuiting:

Short-circuit evaluation means that when evaluating boolean expressions (logical AND and OR) you can 
stop as soon as you find the first condition which satisfies or negates the expression.
*/

// Exercise 2: Complete the step method for Binary AND and Binary OR and make sure the tests pass.

/* Note: 
    With the current implementation of eval, you would not be able to observe any difference b/w big step 
    and small step semantics. Ask the TA on what changes to make inorder to visualize the changes. 
    Make sure to revert the changes after recitation to pass the autograder.
*/

// Helper methods
def toBoolean1(v: Expr): Boolean = {
    require(isValue(v))
    (v: @unchecked) match {
      case B(b) => b
      case N(n) => if (n.isNaN | n==0 ) false else true
      case S(s) => if (s == "") false else true
      case Undefined => false
      case Function(_, _, _) => true
    }
  }

def eval1(env: Env, e: Expr): Expr = {
    e match  {
        case N(_) | S(_) | B(_) => e
        case Print(e1) => println(pretty(eval1(env, e1))); Undefined
        case Function(_, _, _) => e

        /*Evaluation without short circuiting*/
        case Binary(And, e1, e2) => ???
        case Binary(Or, e1, e2) => ???
        case _ => throw new Exception("Not in scope for this recitation !")
    }
}


/*
Step Method does Small Step Semantics and handles Short Circuiting 
*/

/* 
Note: 
    Below is just a skeleton code. 
    Write your logic directly in Lab3.scala and run the corresponding test cases in Lab3RecitationWeek6Spec.scala file
*/

def step1(e: Expr): Expr = {
    e match {
        // Do cases
        case Print(v1) if isValue(v1) => println(pretty(v1)); Undefined
        
        // Begin Solution
        // Binary AND and OR, Do Rules here
        // End Solution

        // Search cases
        case Print(e1) => Print(step1(e1))
        // Begin Solution
        // Binary AND and OR, Search Rules here
        // End Solution
    }
}

/*
Exercise 3: 

Now, let us try to take the examples from Exercise 1 and try to run them using the small step semantics.
*/

/* 
Note: 
    Below is just a skeleton code. 
    Write your logic directly in Lab3.scala and run the corresponding test cases in Lab3RecitationWeek6Spec.scala file
*/

def substitute1(e: Expr, v: Expr, x: String): Expr = {
    require(isValue(v))
    // Helper
    ???
  } 

def step2(e: Expr): Expr = {
    e match {
        // Do cases
        case Print(v1) if isValue(v1) => println(pretty(v1)); Undefined
        // Begin Solution
        // Do Rules here
        // End Solution
        

        // Search cases
        case Print(e1) => Print(step2(e1))
        // Begin Solution
        // Search Rules here
        // End Solution

        case _ => throw new Exception("Not in scope for this recitation")
    }
}
