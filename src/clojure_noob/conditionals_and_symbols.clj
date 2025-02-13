(ns clojure-noob.conditionals-and-symbols)

(defn discount-rate
  "Returns the discount rate based on the original price"
  [original-price]
  (if (> original-price 50) (/ 10 100) 0))

(defn has-discount?
  [original-price]
  (> (discount-rate original-price) 0))

(defn discounted-price
  "Returns the original price with a discount rate"
  [original-price]
  (let [discount-rate (discount-rate original-price)
        discount (* original-price discount-rate)]
    (- original-price discount)))

(discounted-price 100)
(discounted-price 50)
(discounted-price 20)
