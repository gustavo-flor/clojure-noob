(ns clojure-noob.find-max)

(defn find-max
  [numbers]
  (apply max numbers))

(find-max [1 2 3 4 5])
;; => 5

(find-max [-10 -5 -3 -20])
;; => -3
