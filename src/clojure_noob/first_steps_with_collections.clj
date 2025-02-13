(ns clojure-noob.first-steps-with-collections
  (:require [clojure-noob.conditionals-and-symbols :refer [discounted-price
                                                           has-discount?]]))

; vectors

(def prices [30 700 1000])

(comment
  (get prices 0) ; => 30
  (get prices 3) ; => nil
  (get prices 3 0) ; => 0
  (prices 2) ; => 1000
  (prices 3) ; throws IndexOutOfBoundsException
  nil)

(conj prices 5) ; => [30 700 1000 5]

(update prices 1 inc) ; => [30 701 1000]
(update prices 2 dec) ; => [30 700 999]

(defn inc-maker
  [rate]
  #(+ % rate))

(def inc-3 (inc-maker 3))

(inc-3 1) ; => 4
(inc-3 4) ; => 7

(update prices 0 inc-3) ; => [33 700 1000]

(map (inc-maker 5) prices) ; => (35 705 1005)
(map discounted-price prices) ; => (30 630N 900N)
(filter has-discount? prices) ; => (700 1000)

;; lists

(def numbers '(1 2 3))
(def evens (filter even? (range 6)))

(comment
  (nth numbers 0) ; => 1
  (conj numbers 4) ; => (4 1 2 3)
  (conj evens 20) ; => (20 0 2 4)
  nil)
