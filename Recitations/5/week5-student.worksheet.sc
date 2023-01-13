/*
 *  CSCI 3155: Week 5 Recitation Worksheet
 *  Topics covered in this recitation.
 *  > Operational semantics expressed by Inference Rules
 *  > Implementing the Big-Step semantics from Inference Rules
 *  > Testing the implementation of semantics
 * 
 *  We have seen how inference rules are written and how to understand them. 
 *  Inference rules are used to express how evaluation should work in the 'object language'.
 *  These can be considered the mandates of the authors for the 'object' language.
 *  
 *  Note: 
 *  Should be placed in lab3/src/main/scala/jsy/student/
 */

 import  jsy.lab3.Parser 
// Imports the parse function from jsy.lab1.Parser
import jsy.lab3.Parser.parse

// Imports the ast nodes
import jsy.lab3.ast._

// Imports all of the functions form jsy.student.Lab2 (your implementations in Lab2.scala)
import jsy.student.Lab3._
import jsy.lab3.Lab3Like

/* 
 * Let's convert!
 * 1. Write test(s) for Inference rule - jsy code that depends on the rule.
 * 2. Convert the Inference Rule to Scala expressions
 * 3. Implement the Scala code from the converted premises and conclusion
 * 4. Make sure it works right
 */

 def eval(env:Env, e:Expr): Expr = {
    e match {
        case _ => ???
    }  
 }


// Example 1 - EVALANDTRUE 
// Let's check the rule from the Lab3 handout


/* 1. Write tests
 * JSY:
 *  true && false 
 * 
 * Evaluation result:  
 *  false
 * 
 * JSY:
 *  true && true
 * 
 * Evaluation result:
 *  true
 * 
 * JSY:
 *  true && 0
 * 
 * Evaluation result:
 *  0
 * 
 * JSY:
 *  true && 'abcd'
 * 
 * Evaluation result:
 * 'abcd'
 * 
 */


/* 2. Convert to Meta language (Scala)
 *  Let's write it down in Scala
 * 
 *  Converted Premises: 
 *  val v1 = eval(env, e1)
 *  val v2 = eval(env, e2)
 *  (true == toBoolean(v1))
 * 
 *  Converted Conclusion:
 *  eval(env, Binary(And, e1, e2)) == v2
 */

/*
 * 3. Implement the code for it
 */


// 4. Make sure it works right
assert(eval(empty, parse("true && false")) == B(false))
assert(eval(empty, parse("true && true")) == B(true))


// Exercise 2 - EVALANDFALSE

/* 1. Writing tests
 * 
 * JSY:
 *  false && false 
 * 
 * Evaluation result:  
 *  false
 * 
 * JSY:
 *  false && true
 * 
 * Evaluation result:
 *  false
 * 
 * JSY:
 *  false && 0
 * 
 * Evaluation result:
 *  false
 * 
 * JSY:
 *  false && 'abcd'
 * 
 * Evaluation result:
 *  false
 */

/* 2. Convert to Meta language (Scala)
 *  Let's write it down in Scala
 * 
 *  Converted Premises: 
 *  ???
 * 
 *  Converted Conclusion:
 *  ???
 */

/*
 * 3. Implement the code for it
 */


// 4. Make sure it works right
assert(eval(empty, parse("false && false")) == B(false))
assert(eval(empty, parse("false && true")) == B(false))

/*
 * Is this the only way to implement ANDFALSE?
 * Since it always results in False, should we even evaluate the second expression?
 */

// Exercise 3 - EVALCONST
/* 
 * 1. Write tests
 * 
 * JSY:
 * const value = true ; value && false
 * 
 * Evaluation result:
 * false
 * 
 * JSY:
 * const value = true ; value && true
 * 
 * Evaluation result:
 * true
 */


/* 2. Convert to Meta language (Scala)
 *  We have the test cases, which are failing as expected.   
 *  Let's write it down in Scala
 * 
 *  Converted Premises: 
 *  ???
 * 
 *  Converted Conclusion:
 *  ???
 */


/*
 * 3. Implement the code for it
 */


// 4. Make sure it works right
assert(eval(empty, parse("const value = true; value && false")) == B(false))

// Exercise 4 - EVALCALL
/* 
 * 1. Write test
 * 
 * JSY:
 * const andTrue = function(x) { return true && x}; andTrue(false) 
 * 
 * Evaluation Result:
 * false
 */

/* 2. 
 * Converted Premises:
 * ???
 * 
 * Converted Conclusion:
 * ???
 */

/*
 * 3. Implement the code for it
 */

// 4. Make sure it works right.
assert(eval(empty, eval4(empty, parse("const andTrue = function(x) { return true && x}; andTrue(false)"))) == B(false))