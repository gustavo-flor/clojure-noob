(ns clojure-noob.compress-seq)

(defn- compress-seq*
  [acc value]
  (let [last-value (last acc)
        equal? (= value last-value)
        new-acc (if equal? acc (conj acc value))]
    new-acc))

(defn compress-seq
  [symbols]
  (reduce compress-seq* [] symbols))

(compress-seq [1 1 2 2 2 3 3 1 1 1])
;; => [1 2 3 1]

(compress-seq ["a" "a" "b" "c" "c" "c" "d"])
;; => ["a" "b" "c" "d"]
