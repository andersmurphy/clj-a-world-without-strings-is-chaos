(ns clj-a-world-without-strings-is-chaos.core)

(comment
  "0 - Multiplicity

Characters are expensive, and the accountants tell me we can’t hand them out willy-nilly anymore. Given a string x and a character y, how many times does y occur in x?"

  ((frequencies "fhqwhgads") \h)

  (count (re-seq #"h" "fhqwhgads")))

(comment
  "1 - Trapeze Part

Sometimes I try reading sentences right-to-left to make my life more exciting. Results have been
mixed. Given a string x, is it identical when read forwards and backwards?"

  (let [word  "wasitaratisaw"
        [a b] (split-at (/ (count word) 2) word)]
    (= (rest (reverse a)) b)))

(comment
  "2 - Duplicity

One is the loneliest number. Given a string x, produce a list of characters which appear more
than once in x."

  (->> (frequencies "applause")
       (filter (comp (partial <= 2) val))
       keys
       (apply str)))

(comment
  "3 - Sort Yourself Out

Alphabetical filing systems are passe. It’s far more Zen to organize words by their histograms!
Given strings x and y, do both strings contain the same letters, possibly in a different order?"

  (= (set "toptea")
     (set "teapot")))
