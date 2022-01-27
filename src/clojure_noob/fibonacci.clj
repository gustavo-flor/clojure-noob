(ns clojure-noob.fibonacci)

(defn calc
  "Calculate Fibonacci value by potency"
  [potency]
  (if (<= potency 1)
    potency
    (+ (calc (- potency 1)) (calc (- potency 2)))))
