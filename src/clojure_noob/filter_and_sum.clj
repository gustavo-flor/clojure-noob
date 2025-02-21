(ns clojure-noob.filter-and-sum)

(defn filter-and-sum
  [numbers]
  (->> numbers
       (filter even?)
       (reduce +)))

(filter-and-sum [1 2 3 4 5 6 7 8 9 10]) ; => 30
