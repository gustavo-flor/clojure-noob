(ns clojure-noob.core-functions-in-depth)

; Map

(def human-consumption [8.1 7.3 6.6 5.0])

(def critter-consumption [0.0 0.2 0.3 1.1])

(defn unify-diet-data
  [human critter]
  {:human human
   :critter critter})

(def unified-diet-data (map unify-diet-data human-consumption critter-consumption))

(def sum #(reduce + %))

(def avg #(/ (sum %) (count %)))

(defn stats
  [numbers]
  (map #(% numbers) [sum count avg]))

(stats [3 4 10])

; Reduce

(reduce
  (fn [new-map [key val]] (assoc new-map key (inc val)))
  {}
  {:max 30 :min 10})

(reduce (fn [new-map [key val]]
          (if (> val 4)
            (assoc new-map key val)
            new-map))
        {}
        {:human 4.1 :critter 3.9})

; Take, drop, take-while, drop-while, filter...

(filter even? (range 7)) ; returns (2 4 6)

(take-while even? (range 7)) ; returns ()

(drop-while odd? (range 7)) ; returns ()

; Some

(def food-journal
  [{:month 1 :day 1 :human 5.3 :critter 2.3}
   {:month 2 :day 7 :human 2.1 :critter 0.3}
   {:month 3 :day 9 :human 3.0 :critter 1.7}
   {:month 4 :day 5 :human 1.1 :critter 1.0}])

(some #(> (:critter %) 3) food-journal) ; returns true