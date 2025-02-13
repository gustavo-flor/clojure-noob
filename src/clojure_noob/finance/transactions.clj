(ns clojure-noob.finance.transactions
  (:use [clojure-noob.finance.monies :only [zero-money]]))

(defn audit-transaction!
  [transaction]
  (println (str "Auditing: " transaction)))

(defn new-transaction!
  [transaction-type account-id amount & details]
  (let [timestamp (quot (System/currentTimeMillis) 1000)
        transaction {:transaction-type transaction-type
                     :account-id account-id
                     :details details
                     :timestamp timestamp
                     :amount amount}]
    (audit-transaction! transaction)
    transaction))

(new-transaction! :debit "111" zero-money)
