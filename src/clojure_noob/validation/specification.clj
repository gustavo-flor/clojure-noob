(ns clojure-noob.validation.specification
  (:require [clojure.spec.alpha :as spec])
  (:require [clojure-noob.finance.monies :refer [new-money
                                                 currencies]]))

(spec/def :money/amount int?)
(spec/def :currency/divisor int?)
(spec/def :currency/sign (spec/nilable string?))
(spec/def :currency/name (spec/nilable string?))
(spec/def :currency/code (and keyword? #{:usd :brl :ukg}))

(spec/def :finance/currency (spec/keys :req-un [:currency/divisor
                                                :currency/code]
                                       :opt-un [:currency/sign
                                                :currency/name]))

(spec/valid? :finance/currency (:usd currencies)) ; => true

(spec/def :finance/money (spec/keys :req-un [:money/amount
                                             :finance/currency]))

(spec/valid? :finance/money (new-money 1234 (:brl currencies))) ; => true

(def hundred-dollars (new-money 100 (:usd currencies)))

(spec/explain :finance/money hundred-dollars)

(spec/explain :finance/money {:amount "a" :currency (:brl currencies)})
