(ns clojure-noob.map-reduce-and-filters
  (:require [clojure-noob.maps-and-threading :refer [order]]))

(defn total-price-per-product
  [[_ {:keys [quantity price]}]] ; an object is a collection of [key value]
  (* quantity price))

(map total-price-per-product order)

; threading first = ->
; threading last = ->>

(defn total-price
  [order]
  (->> order
      (map total-price-per-product)
      (reduce +)))

(total-price order)

(def special-order (assoc order :keychain {:quantity 1}))

(defn for-free?
  [item]
  (<= (:price item 0) 0))

(->> special-order
     (filter #(for-free? (second %)))
     keys)

(def paid? (comp not for-free?))

(->> special-order
     (filter (fn [[_ value]] (paid? value)))
     keys)

(paid? {:price 50}) ; => true
(paid? {:price 0}) ; => false
(paid? {}) ; => false
((comp not paid?) {:price 50}) ; => false
