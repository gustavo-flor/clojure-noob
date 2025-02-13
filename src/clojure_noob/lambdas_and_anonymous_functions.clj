(ns clojure-noob.lambdas-and-anonymous-functions)

(comment (println "Hello"))

(when (not= true false)
  (println "Hello"))

(defn rest-of-division
  [op1, op2]
  (#(rem %1 %2) op1 op2))

(defn is-odd? ;; this is a predicate (functions which returns a bool result suffixed with a question mark '?')
  [number]
  (not= (rest-of-division number 2) 0))

(is-odd? 2)
(is-odd? 3)

(defn is-even? [number] (not (is-odd? number)))

(is-even? 5)
