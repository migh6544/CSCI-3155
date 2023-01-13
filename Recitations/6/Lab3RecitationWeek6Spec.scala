package jsy.student

import jsy.lab3.Lab3Like
import jsy.lab3.Parser.parse
import jsy.lab3.ast._
import jsy.tester.JavascriptyTester
import org.scalatest._
import flatspec._

/*
 * This class should be placed in src/test/scala/jsy/student/
 */
class Lab3RecitationWeek6Spec(lab3: Lab3Like) extends AnyFlatSpec {

  import lab3._

  // Test Cases for Big Step Semantics (Without short circuiting)
  // Note: Might have to change implementation to see the impact in eval to see actual output
  "eval/bigstep-eval-and" should "be considered values" in {
    assertResult(Undefined) {
      eval(empty, parse("console.log('a') && console.log('b')"))
    }
  }

  "eval/bigstep-eval-or" should "be considered values" in {
    assertResult(Function(None, "x", Var("x"))) {
      eval(empty, parse("function(x) { return x } || console.log(5)"))
    }
  }

  // Small Step - Short Circuiting
  "step/shortcircuit-and" should "be considered values" in {
    assertResult(Binary(And, Undefined, Print(S("b")))) {
      step(parse("console.log('a') && console.log('b')"))
    }
  }

  "step/shortcircuit-or" should "be considered values" in {
    assertResult(Function(None, "x", Var("x"))) {
      step(parse("function(x) { return x } || console.log(5)"))
    }
  }
  
  // Test Cases for Step Method
  // Example 1:
  "step/exp1-1" should "be considered values" in {
    assertResult(???) {
      step(parse("const x = 5; console.log(x); const f = function(x) { return x * x }; f(7)"))
    }
  }

  "step/exp1-2" should "be considered values" in {
    assertResult(???) {
      step(Binary(Seq, Print(N(5.0)), ConstDecl("f", Function(None, "x", Binary(Times, Var("x"), Var("x"))), Call(Var("f"), N(7.0)))))
    }
  }

  "step/exp1-3" should "be considered values" in {
    assertResult(???) {
      step(Binary(Seq, Undefined, ConstDecl("f", Function(None, "x", Binary(Times, Var("x"), Var("x"))), Call(Var("f"), N(7.0)))))
    }
  }

  "step/exp1-4" should "be considered values" in {
    assertResult(???) {
      step(ConstDecl("f", Function(None, "x", Binary(Times, Var("x"), Var("x"))), Call(Var("f"), N(7.0))))
    }
  }

  "step/exp1-5" should "be considered values" in {
    assertResult(???) {
      step(Call(Function(None, "x", Binary(Times, Var("x"), Var("x"))), N(7.0)))
    }
  }

  "step/exp1-6" should "be considered values" in {
    assertResult(???) {
      step(Binary(Times, N(7.0), N(7.0)))
    }
  }

  // Example 2: 
  "step/exp2-1" should "be considered values" in {
    assertResult(???) {
      step(parse("const f = function (y) { return 2 * y }; f(4)"))
    }
  }

  "step/exp2-2" should "be considered values" in {
    assertResult(???) {
      step(Call(Function(None, "y", Binary(Times, N(2.0), Var("y"))), N(4.0)))
    }
  }

  "step/exp2-3" should "be considered values" in {
    assertResult(???) {
      step(Binary(Times, N(2.0), N(4.0)))
    }
  }

  // Example 3:
  "step/exp3-1" should "be considered values" in {
    assertResult(Binary(Times, N(5.0), N(10.0))) {
      step(parse("const x = 5; x * 10"))
    }
  }

  "step/exp3-2" should "be considered values" in {
    assertResult(N(50.0)) {
      step(Binary(Times, N(5.0), N(10.0)))
    }
  }
}

// An adapter class to pass in your Lab3 object.
class Lab3RecitationWeek6SpecRunner extends Lab3RecitationWeek6Spec(Lab3)