(ns clj-a-world-without-strings-is-chaos.core
  (:require [clojure.string :as str]))

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

(comment
  "7 - Popularity Contest

Sixty-two thousand four hundred repetitions make one truth. Given a string x, identify the character
 which occurs most frequently. If more than one character occurs the same number of times, you may
choose arbitrarily. Is it harder to find all such characters?"

  (->> (frequencies "CCCBBBAA")
       (sort-by second >)
       first
       key
       str))

(comment
  "8 - esreveR A ecnetneS

Little-endian encoding is such a brilliant idea I want to try applying it to English. Given a string x
 consisting of words (one or more non-space characters) which are separated by spaces, reverse the
order of the characters in each word."

  (->> (str/split "a few words in a sentence" #"\s")
       (map (comp (partial apply str) reverse))
       (str/join " " )))

(comment
  "9 - Compression Session

Let’s cut some text down to size. Given a string x and a boolean vector y of the same length, extract
the characters of x corresponding to a 1 in y."

  (->> (map (fn [x y] ({1 x} y)) "embiggener" [0 0 1 1 1 1 0 0 1 1])
       (apply str)))

(comment
  "10 - Expansion Mansion

Wait, strike that- reverse it. Given a string x and a boolean vector y, spread the characters of x to
the positions of 1s in y, filling intervening characters with underscores."

  (defn expansion-mansion [word b-array]
    (lazy-seq
     (let [[w & ws] word [b & bs] b-array]
       (cond (= 1 b) (cons w (expansion-mansion ws bs))
             (= 0 b) (cons "_" (expansion-mansion word bs))))))

  (apply str (expansion-mansion "bigger" [0 0 1 1 1 1 0 0 1 1])))

(comment
  "11 - C_ns_n_nts

Vowels make prose far too… pronounceable. Given a string x, replace all the vowels (a, e, i, o, u, or
y) with underscores."

  (str/replace "Several normal words" #"[aeiouyAEIOUY]" "_"))

(comment
  "12 - Cnsnnts Rdx

On second thought, I’ve deemed vowels too vile for placeholders. Given a string x, remove all the
vowels entirely."

  (str/replace "FLAPJACKS" #"[aeiouyAEIOUY]" ""))

(comment
  "13 - TITLE REDACTED

Given a string x consisting of words separated by spaces (as above), and a string y, replace all words
in x which are the same as y with a series of xes."

  (let [word "fish"]
    (str/replace "one fish two fish"
                 (re-pattern word)
                 (apply str (take (count word) (repeat "X"))))))

(comment
  "14 - It’s More Fun to Permute

My ingenious histogram-based filing system has a tiny flaw: some people insist that the order of
letters in their names is significant, and now I need to re-file everything. Given a string x,
generate a list of all possible reorderings of the characters in x. Can you do this non-recursively?"

  (defn permutations [s]
    (lazy-seq
     (if (next s)
       (for [head s
             tail (permutations (remove #{head} s))]
         (cons head tail))
       [s])))

  (map (partial apply str) (permutations "xyz")))
