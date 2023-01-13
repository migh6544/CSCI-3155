/*
 * CSCI 3155: Week 4 Recitation Worksheet
 * This worksheet covers BNF Grammars and their relationship to inductive definitions
 * 
 * Judgment forms are also discussed
 * 
 */


/*
*   Suppose we want to work with a simple language, that of lists of integers
*   First, let us define a list structure
* 
*/

sealed trait IntList 
case object Nil extends IntList
case class Cons(head: Int, tail: IntList) extends IntList

/*
*   Consider how we might parse a list of integers like this:
*   [ 5, [3, []]]
* 
*   A possible approach, mirroring our inductive definition, could look like this:
*
*   List ::= Nil | [i, List]
*   Nil ::= [] 
* 
*   Something noteworthy here is that when trying to build a parser for this language,
*   we can think about having one function per non-terminal, and using recursion to build up our expression tree.
*   In this way, the BNF grammar, and the parser to handle it, have parallel structures.
* 
*   Derivations: Suppose we want to check if [5, [3, []]] is valid in our language.
*   We do so by starting with our top nonterminal (List), and attempting to derive the list desired.
* 
*   Derivation:
*   List
*   [i, List]
*   [5, [i, List]]
*   [5, [3, []]]
*   
*/

val l0 = Nil 
val l1 = Cons(5, Nil)
val l2 = Cons(5,l1)

/*
*   With this simple language defined, we can discuss evaluation
* 
*   Let us consider a judgment form, and inference rules, for the sum of a list.
* 
*   Like our inductive definitions, these  will also be inductive, on the structure of the data itself
* 
*   A judgment is a statement on the relationship between a set of objects
*   
*   for instance, we can have the judgment:
*   
*   sum(List) = Int 
*
*   In plain words, the sum of a List is an Integer. 
*   To derive judgments, we use inference rules to define how differing cases are handled.
* 
*   e : []
*   -----------
*   sum(e) = 0
* 
*   e1 : [n, e2]
*   ------------------
*   sum(e1) = n + sum(e2)
*
*   As e2 is also an IntList, we can take the sum of it, and in this way, we will take a step in evaluation.
*   Each recursive call of sum will be on a smaller list, and ultimately terminate
* 
*   Examples:
    let e = [5,[10,[]]]
*   sum(e )
*   Apply second rule: sum(e) = 5 + sum([10, []])
*   Apply second rule: sum(e) = 5 + 10 + sum([])
*   Apply first rule: sum(e) = 5 + 10 + 0 = 15
*
*   What about sum([1,[2,[3,[]]]])?
*   Or sum([]) ?
*/

// This naturally translates into pattern matching
def sum(e: IntList): Int = e match {
    case Nil => 0
    case Cons(n, e2) => n + sum(e2)
}

sum(l0)
sum(l1)

/*
*   Likewise, we can describe length, and other functions on lists.
*   Take prepend as an example - We want to put an int onto the front of a list
*   Judgment: prepend(Int, List) = List
* 
    n : Int , e : List
*   ---------------------
*   prepend(n,3) = [n,e]
*/

def prepend(n: Int, e:IntList) = {
    Cons(n,e)
}

assert(prepend(5, l0) == l1)

/*
* Time permitting: Some extensions
* Consider a language with addition of Ints and Floats
* 
* What judgement forms do we need? Not only for int + int, but what about float + int? what considerations do we need?
* Logically, could we extend this to talk about lists of floats, and list of ints? What about lists containing both?
*/
