(ns clj-a-world-without-strings-is-chaos.core)

(comment
  "0 - Multiplicity

Characters are expensive, and the accountants tell me we can’t hand them out willy-nilly anymore. Given a string x and a character y, how many times does y occur in x?"

  ((frequencies "fhqwhgads") \h)

  (count (re-seq #"h" "fhqwhgads")))
