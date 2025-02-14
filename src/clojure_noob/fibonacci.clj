(ns clojure-noob.fibonacci)

(defn recursive-fib
  "Calculate Fibonacci value by potency"
  ([] 0)
  ([potency]
   (if (<= potency 1)
     potency
     (+ (recursive-fib (- potency 1)) (recursive-fib (- potency 2))))))

(defn fib-next
  [[a b]]
  [b (+ a b)])

(fib-next [0 1]) ; => [1 1]
(fib-next [1 1]) ; => [1 2]
(fib-next [1 2]) ; => [2 3]
(fib-next [2 3]) ; => [3 5]

(def fib-res-seq (iterate fib-next [0 1]))

(take 5 fib-res-seq) ; => ([0 1] [1 1] [1 2] [2 3] [3 5])

(def fib-seq (map first fib-res-seq))

(take 5 fib-seq) ; => (0 1 1 2 3 5)
