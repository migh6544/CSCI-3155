package jsy.student

import jsy.lab3.Lab3Like
import jsy.lab3.Parser.parse
import jsy.lab3.ast._
import org.scalatest._
import org.scalatest.flatspec.AnyFlatSpec
/*
 * This class should be placed in src/test/scala/jsy/student/
 */
class Lab3RecitationSpec(lab3: Lab3Like) extends AnyFlatSpec {
  import lab3._

  "eval/andtrue-1" should "be considered values" in {
    assertResult(???) {
      eval(empty, ???) 
    }
  }

  "eval/andtrue-2" should "be considered values" in {
    assertResult(???) {
      eval(empty, ???) 
    }
  }

  "eval/andfalse-1" should "be considered values" in {
    assertResult(???) {
      eval(empty, ???) 
    }
  }

  "eval/andfalse-2" should "be considered values" in {
    assertResult(???) {
      eval(empty, ???) 
    }
  }

  "eval/const-1" should "be considered values" in {
    assertResult(???) {
      eval(empty, ???) 
    }
  }

  "eval/call-1" should "be considered values" in {
    assertResult(???) {
      eval(empty, ???) 
    }
  }
}

// An adapter class to pass in your Lab3 object.
class Lab3RecitationSpecRunner extends Lab3RecitationSpec(Lab3)