/*
 * CSCI 3155: Week 3 Recitation Worksheet
 *
 * This worksheet demonstrates how to annalyze javascript if expressions
 * This introduces the concept of type casting to booleans
 * This introduces the concept of short circuiting
 * This demonstrates the return type of javascript ternary is ambiguous
 */

import jsy.lab2.Parser.{parse, parseFile}

// Imports the ast nodes
import jsy.lab2.ast._

/*

Using your prefered js node interpreter confirm the values of the following
    e1 ? e2 : e3

Here is the python equivalent:
    e2 if e1 else e3

Here is the Scala equivalent:
    if (e1) e2 else e3

We note that true JavaScript does support the Scala like notation:
    if (e1) e2 else e3

*/

/*

Explore the following values in JavaScripty

true ? 1 : 2
    value is 1

false ? 1 : 2
    value is 2

Hopefully not a big surprise. We could guess that anything of the form
e1 ? e2 : e3, if e1 is true, then we return e2, else we return e3
That isn't totally correct, let us explore
*/

def eval1(e: Expr): Expr = {
    e match {
        case If(B(true), e2, e3) => e2
        case If(B(false), e2, e3) => e3
    }
}
assert(N(1.0) == eval1(parse("true ? 1 : 2")))
assert(N(2.0) == eval1(parse("false ? 1 : 2")))
1


/*

Explore the following values in JavaScripty

true ? 2 + 3 : 10 + 15
    value is 5

false ? 2 + 3 : 10 + 15
    value is 25

Again, hopefully not a big surprise. We could refine our guess that anything of the form
e1 ? e2 : e3, if e1 is true, 
    then we return the value of e2, 
    else we return the value of e3
NOTE: the addition 'the value of'
That isn't totally correct, let us explore.
*/

def eval2(e: Expr): Expr = {
    e match {
        case B(b) => e
        case N(n) => e
        // just enough for us, but not good enough for final sol;n
        case Binary(Plus, e1, e2) => {
            val N(n1) = eval2(e1)
            val N(n2) = eval2(e2)
            N(n1 + n2)
        }
        case If(B(true), e2, e3) => eval2(e2)
        case If(B(false), e2, e3) => eval2(e3)
    }
}
assert(N(1.0) == eval2(parse("true ? 1 : 2")))
assert(N(2.0) == eval2(parse("false ? 1 : 2")))
assert(N(5.0) == eval2(parse("true ? 2 + 3 : 10 + 15")))
assert(N(25.0) == eval2(parse("false ? 2 + 3 : 10 + 15")))
2

/*

Explore the following values in JavaScripty

1 ? 2 + 3 : 10 + 15
    value is 5

0 ? 2 + 3 : 10 + 15
    value is 25
    
25 ? 2 + 3 : 10 + 15
    value is 5

NaN ? 2 + 3 : 10 + 15
    value is 25

What are we seeing here?
e1 ? e2 : e3, if e1 is truethy, 
    then we return the value of e2, 
    else we return the value of e3
NOTE: the use of the word 'truethy' not mearly true
That isn't totaally correct, let us explore.
*/

def toBoolean3(e: Expr): Boolean = {
    e match {
        case B(b) => b
        case N(0) => false
        case N(n) if n.isNaN => false
        case N(_) => true
        case _ => ???
    }
}
def eval3(e: Expr): Expr = {
    e match {
        case v@( N(_) | B(_) ) => v
        // just enough for us, but not good enough for final sol;n
        case Binary(Plus, e1, e2) => {
            val N(n1) = eval3(e1)
            val N(n2) = eval3(e2)
            N(n1 + n2)
        }
        case If(e1, e2, e3) => {
            if (toBoolean3(e1)) eval3(e2)
            else eval3(e3)
        }
    }
}
assert(N(1.0) == eval3(parse("true ? 1 : 2")))
assert(N(2.0) == eval3(parse("false ? 1 : 2")))
assert(N(5.0) == eval3(parse("true ? 2 + 3 : 10 + 15")))
assert(N(25.0) == eval3(parse("false ? 2 + 3 : 10 + 15")))
assert(N(5.0) == eval3(parse("1 ? 2 + 3 : 10 + 15")))
assert(N(25.0) == eval3(parse("0 ? 2 + 3 : 10 + 15")))
assert(N(5.0) == eval3(parse("25 ? 2 + 3 : 10 + 15")))
assert(N(25.0) == eval3(If(N(Double.NaN), parse("2 + 3"), parse("10+15"))))
3


/*
strings

"" ? 1 : 2
"hello" ? 1 : 2
"false" ? 1 : 2
 ^ NOTE the "", not 
        false ? 1 : 2
*/

def toBoolean4(e: Expr): Boolean = {
    e match {
        case B(b) => b
        case N(0) => false
        case N(n) if n.isNaN => false
        case N(_) => true
        case S("") => false
        case S(_) => true
        case _ => ???
    }
}

def eval4(e: Expr): Expr = {
    e match {
        case v@( N(_) | B(_) | S(_) ) => v
        // just enough for us, but not good enough for final sol;n
        case Binary(Plus, e1, e2) => {
            val N(n1) = eval4(e1)
            val N(n2) = eval4(e2)
            N(n1 + n2)
        }
        case If(e1, e2, e3) => {
            if (toBoolean4(e1)) eval4(e2)
            else eval4(e3)
        }
    }
}
assert(N(1.0) == eval4(parse("true ? 1 : 2")))
assert(N(2.0) == eval4(parse("false ? 1 : 2")))
assert(N(5.0) == eval4(parse("true ? 2 + 3 : 10 + 15")))
assert(N(25.0) == eval4(parse("false ? 2 + 3 : 10 + 15")))
assert(N(5.0) == eval4(parse("1 ? 2 + 3 : 10 + 15")))
assert(N(25.0) == eval4(parse("0 ? 2 + 3 : 10 + 15")))
assert(N(5.0) == eval4(parse("25 ? 2 + 3 : 10 + 15")))
assert(N(25.0) == eval4(If(N(Double.NaN), parse("2 + 3"), parse("10+15"))))
assert(N(2.0) == eval4(parse("'' ? 1 : 2")))
assert(N(1.0) == eval4(parse("'hello' ? 1 : 2")))
assert(N(1.0) == eval4(parse("'false' ? 1 : 2")))
4


/*
true && false ? 1 : 2

What are we seeing here?
e1 ? e2 : e3, if e1 evaluates to a truethy value,
    then we return the value of e2, 
    else we return the value of e3
NOTE: the change 'e1 evaluates to a truehty value'
*/
def toBoolean5(e: Expr): Boolean = {
    e match {
        case B(b) => b
        case N(0) => false
        case N(n) if n.isNaN => false
        case N(_) => true
        case S("") => false
        case S(_) => true
        case _ => ???
    }
}

def eval5(e: Expr): Expr = {
    e match {
        case v@( N(_) | B(_) | S(_) ) => v
        // just enough for us, but not good enough for final sol;n
        case Binary(Plus, e1, e2) => {
            val N(n1) = eval5(e1)
            val N(n2) = eval5(e2)
            N(n1 + n2)
        }
        // just enough for us here, but not good enough for final soln
        case Binary(And, B(true), e2) => e2
        case If(e1, e2, e3) => {
            if (toBoolean5(eval5(e1))) eval5(e2)
            else eval5(e3)
        }
    }
}
assert(N(1.0) == eval5(parse("true ? 1 : 2")))
assert(N(2.0) == eval5(parse("false ? 1 : 2")))
assert(N(5.0) == eval5(parse("true ? 2 + 3 : 10 + 15")))
assert(N(25.0) == eval5(parse("false ? 2 + 3 : 10 + 15")))
assert(N(5.0) == eval5(parse("1 ? 2 + 3 : 10 + 15")))
assert(N(25.0) == eval5(parse("0 ? 2 + 3 : 10 + 15")))
assert(N(5.0) == eval5(parse("25 ? 2 + 3 : 10 + 15")))
assert(N(25.0) == eval5(If(N(Double.NaN), parse("2 + 3"), parse("10+15"))))
assert(N(2.0) == eval5(parse("'' ? 1 : 2")))
assert(N(1.0) == eval5(parse("'hello' ? 1 : 2")))
assert(N(1.0) == eval5(parse("'false' ? 1 : 2")))
assert(N(2.0) == eval5(parse("true && false ? 1 : 2")))
5

/*
console.log("hello") ? 1 : 2
console.log("true") ? 1 : 2

console.log(e) evaluates to undefined
Is undefined truethy or falsey?
*/
def toBoolean6(e: Expr): Boolean = {
    e match {
        case B(b) => b
        case N(0) => false
        case N(n) if n.isNaN => false
        case N(_) => true
        case S("") => false
        case S(_) => true
        case Undefined => false
        case _ => ???
    }
}

def eval6(e: Expr): Expr = {
    e match {
        case v@( N(_) | B(_) | S(_) ) => v
        // just enough for us, but not good enough for final sol;n
        case Binary(Plus, e1, e2) => {
            val N(n1) = eval6(e1)
            val N(n2) = eval6(e2)
            N(n1 + n2)
        }
        // just enough for us here, but not good enough for final soln
        case Binary(And, B(true), e2) => e2
        // provided in the lab for us
        case Print(e1) => println(pretty(eval6(e1))); Undefined
        case If(e1, e2, e3) => {
            if (toBoolean6(eval6(e1))) eval6(e2)
            else eval6(e3)
        }
    }
}
assert(N(1.0) == eval6(parse("true ? 1 : 2")))
assert(N(2.0) == eval6(parse("false ? 1 : 2")))
assert(N(5.0) == eval6(parse("true ? 2 + 3 : 10 + 15")))
assert(N(25.0) == eval6(parse("false ? 2 + 3 : 10 + 15")))
assert(N(5.0) == eval6(parse("1 ? 2 + 3 : 10 + 15")))
assert(N(25.0) == eval6(parse("0 ? 2 + 3 : 10 + 15")))
assert(N(5.0) == eval6(parse("25 ? 2 + 3 : 10 + 15")))
assert(N(25.0) == eval6(If(N(Double.NaN), parse("2 + 3"), parse("10+15"))))
assert(N(2.0) == eval6(parse("'' ? 1 : 2")))
assert(N(1.0) == eval6(parse("'hello' ? 1 : 2")))
assert(N(1.0) == eval6(parse("'false' ? 1 : 2")))
assert(N(2.0) == eval6(parse("true && false ? 1 : 2")))
assert(N(2.0) == eval6(parse("console.log('hello') ? 1 : 2")))
assert(N(2.0) == eval6(parse("console.log('true') ? 1 : 2")))
6

/*
short circuiting

true ? console.log("hello") : console.log("goodbye")
    goodbye is printed
    undefined is returned
    goodbye is NOT printed

false ? console.log("hello") : console.log("goodbye")
    goodbye is printed
    undefined is returned
    hello is NOT printed

We call this short circuit evaluation
Our interpreter should be able to handle it
So the following wouldn't be valid. 
why?
*/
def toBoolean7(e: Expr): Boolean = {
    e match {
        case B(b) => b
        case N(0) => false
        case N(n) if n.isNaN => false
        case N(_) => true
        case S("") => false
        case S(_) => true
        case Undefined => false
        case _ => ???
    }
}

def eval7(e: Expr): Expr = {
    e match {
        case v@( N(_) | B(_) | S(_) ) => v
        // just enough for us, but not good enough for final sol;n
        case Binary(Plus, e1, e2) => {
            val N(n1) = eval7(e1)
            val N(n2) = eval7(e2)
            N(n1 + n2)
        }
        // just enough for us here, but not good enough for final soln
        case Binary(And, B(true), e2) => e2
        // provided in the lab for us
        case Print(e1) => println(pretty(eval7(e1))); Undefined
        case If(e1, e2, e3) => {
            val b1 = toBoolean7(eval7(e1))
            val v2 = eval7(e2)
            val v3 = eval7(e3)
            if (b1) v2 else v3
        }
    }
}
7

/*
Refactoring?
*/
def toBooleanRefactored(e: Expr): Boolean = {
    e match {
        case B(false) | Undefined | S("") | N(0) => false
        case N(n) if n.isNaN => false
        case _ => true
    }
}
8