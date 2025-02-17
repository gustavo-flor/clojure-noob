(ns clojure-noob.finance.account
  (:require [schema.core :as s]))

(s/defschema Account
             {:id          s/Int
              :customer-id s/Int
              :balance     BigDecimal})

(s/defn credit :- Account
        [account :- Account
         amount :- BigDecimal]
        (update account :balance + amount))
