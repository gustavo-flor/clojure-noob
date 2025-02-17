(ns clojure-noob.finance.account-test
  (:require [clojure.test :refer :all]
            [clojure-noob.finance.account :refer :all]
            [clojure-noob.fixture :refer :all]
            [matcher-combinators.test :refer [match?]]
            [schema.core :as s]))

(def account (rand-account {:balance 125M}))

(deftest credit-test
  (s/with-fn-validation
    (testing "if adds amount to balance"
      (is (match?
            {:balance 150M}
            (credit account 25M)))
      (is (=
            (merge account {:balance 175M})
            (credit account 50M))))))
