(ns clojure-noob.threading
  (:require [clojure.string :as str])
  (:import (java.util Date)))

(defn company-email
  [employee]
  (format "%s.%s@nudemo.com.br"
          (str/lower-case (:first-name employee))
          (str/lower-case (:last-name employee))))

(defn hired-day
  [employee]
  (.getDay (:hired-at employee)))

(defn old-email-format?
  [employee]
  (not= (:email employee) (company-email employee)))

(def gustavo {:first-name         "Gustavo"
              :last-name          "Flor"
              :employment-history [:pique]})

; nested function calls

(defn hire-nested
  [employee]
  (assoc (update (assoc employee :email (company-email employee))
                 :employment-history conj :nudemo)
    :hired-at (Date.)))

(hire-nested gustavo)

; intermediate value binding

(defn hire-binding
  [employee]
  (let [email (company-email employee)
        employee-with-email (assoc employee :email email)
        employee-with-history (update employee-with-email :employment-history conj :nudemo)]
    (assoc employee-with-history :hired-at (Date.))))

(hire-binding gustavo)

; thread first

(defn hire-thread
  [employee]
  (-> employee
      (assoc :email (company-email employee))
      (update :employment-history conj :nudemo)
      (assoc :hired-at (Date.))))

(hire-thread gustavo)

(def hire-thread-impl
  '(-> employee
       (assoc :email (company-email employee))
       (update :employment-history conj :nudemo)
       (assoc :hired-at (Date.))))

(macroexpand hire-thread-impl) ; to check what threading generates

; thread last

(def employees
  [{:first-name "Cris" :last-name "Moreira" :email "cris@nudemo.com.br" :hired-at #inst "2022-05-20"}
   {:first-name "David" :last-name "Leve" :email "david@nudemo.com.br" :hired-at #inst "2022-05-21"}
   {:first-name "Eduardo" :last-name "Vin" :email "eduardo@nudemo.com.br" :hired-at #inst "2022-05-21"}
   {:first-name "Mark" :last-name "Zuk" :email "mark.zuk@nudemo.com.br" :hired-at #inst "2022-05-21"}
   {:first-name "John" :last-name "Marston" :email "john.marston@nudemo.com.br" :hired-at #inst "2022-05-22"}])

(defn report
  [employees]
  (->> employees
       (filter old-email-format?)
       (map #(assoc % :email (company-email %)))
       (group-by hired-day)
       vals))

(report employees)

; some-> :: prevent operating on nil values unexpectedly.

(comment
  (-> {:first-name "Rich" :last-name "Hickey"}
      :hired-at
      .getTime) ; throws NullPointerException
  (some-> {:first-name "Rich" :last-name "Hickey"}
          :hired-at
          .getTime) ; => nil
  nil)

; cond-> :: given a set of predicate/form pairs, thread the expression into the first arg when test is truthy

(defn describe-number
  [number]
  (cond-> []
          (odd? number) (conj :odd)
          (even? number) (conj :even)
          (zero? number) (conj :zero)
          (pos? number) (conj :positive)))

(describe-number 3) ; => [:odd :positive]
(describe-number 4) ; => [:even :positive]

; as-> : bind a var and thread into the expressions (in this example I'm using `$`, but any valid var is acceptable)

(as-> {:ints (range 5)} $
      (:ints $)
      (map inc $)
      (conj $ 10)
      (apply + $)) ; => 25
