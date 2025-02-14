(ns clojure-noob.finance.transactions
  (:use [clojure-noob.finance.monies :refer [zero-money
                                             rand-money
                                             new-money
                                             default-currency
                                             +$]]))

(defn audit-transaction!
  [transaction]
  (println (str "Auditing: " transaction)))

(defn new-transaction!
  [transaction-type account-id amount status & details]
  (let [timestamp (quot (System/currentTimeMillis) 1000)
        transaction {:transaction-type transaction-type
                     :account-id account-id
                     :status status
                     :details details
                     :timestamp timestamp
                     :amount amount}]
    (audit-transaction! transaction)
    transaction))

(new-transaction! :debit 111 zero-money :settled)

(defn rand-transaction!
  []
  (new-transaction! (rand-nth [:debit :credit])
                    (rand-nth [111 222 333])
                    (rand-money)
                    (rand-nth [:settled :pending])))

(def all-transactions (let [txs (take 100 (repeatedly rand-transaction!))
                            all (map #(update-in % [:amount] assoc :currency default-currency) txs)]
                        all))

(defn transactions-by-account-id
  [account-id txs]
  (filter #(= account-id (:account-id %)) txs))

(defn transactions-by-status
  [status txs]
  (filter #(= status (:status %)) txs))

(defn transactions-by-type
  [tx-type txs]
  (filter #(= tx-type (:transaction-type %)) txs))

(defn total-settled-debits
  [account-id]
  (->> all-transactions
       (transactions-by-account-id account-id)
       (transactions-by-type :debit)
       (map :amount)
       (reduce +$)))

(total-settled-debits 111)

(defn available-balance
  [account-id]
  (let [txs (transactions-by-account-id account-id all-transactions)
        settled-txs (transactions-by-status :settled txs)
        debits (transactions-by-type :debit settled-txs)
        credits (transactions-by-type :credit settled-txs)
        debit-amts (map :amount debits)
        credit-amts (map :amount credits)
        neg-amts (map #(assoc % :amount (- (:amount %))) credit-amts)
        amounts (concat debit-amts neg-amts)
        total-monies (reduce +$ amounts)]
    (if (<= 0 (:amount total-monies))
      total-monies
      (new-money 0))))

(available-balance 222)

(defn pending-credits
  [txs]
  (->> txs
       (transactions-by-type :credit)
       (transactions-by-status :pending)
       (map :amount)
       (reduce +$)))

(pending-credits (transactions-by-account-id 333 all-transactions))
