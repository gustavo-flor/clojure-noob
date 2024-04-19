(ns clojure-noob.do-things)

(defn inc-100
  [value]
  (+ value 100))

(defn inc-maker
  [factor]
  (fn [value] (+ value factor)))

(def inc-5 (inc-maker 5))

(defn map-set
  [fn, vector]
  (set (map fn vector)))
