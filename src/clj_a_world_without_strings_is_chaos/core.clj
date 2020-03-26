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

(comment
  "4 - Precious Snowflakes

It’s virtuous to be unique, just like everyone else. Given a string x, find all the characters which
occur exactly once, in the order they appear."

  (let [s "somewhat heterogenous"]
    (->> (filter (->> (frequencies s)
                      (filter (comp #{1} val))
                      keys
                      set)
                 s)
         (apply str))))

(comment
  "5 - Musical Chars

Imagine four chars on the edge of a cliff. Say a direct copy of the char nearest the cliff is sent
to the back of the line of char and takes the place of the first char. The formerly first char
becomes the second, the second becomes the third, and the fourth falls off the cliff. Strings
work the same way. Given strings x and y, is x a rotation of the characters in y?"

  (let [a "foobar" b "barfoo"]
    (->> (concat b b)
         (drop-while (partial not= (first a)))
         (take (count a))
         (apply str)
         (= a))))

(comment
  "6 - Size Matters

Sometimes small things come first. Given a list of strings x, sort the strings by length, ascending."

  (sort-by count ["books" "apple" "peanut" "aardvark" "melon" "pie"]))
