(ns clojure-noob.finance.monies)

(def currencies
  {:usd {:divisor 100 :code :usd :sign "$" :name "US Dollars"}
   :brl {:divisor 100 :code :brl :sign "R$" :name "Brazilian Real"}
   :ukg {:divisor (* 17 29) :code :ukg :sign "ʛ" :name "Galleons of the United Kingdom"}})

(def default-currency (:brl currencies))

(defn display-galleons
  "Returns a display string for Harry Potter money"
  [amount]
  (let [{:keys [divisor]} (:ukg currencies)
        galleons      (int (/ amount divisor))
        less-galleons (rem amount divisor)
        sickles       (int (/ less-galleons 17))
        knuts         (rem less-galleons 29)]
    (str galleons " Galleons, " sickles " Sickles, and " knuts " Knuts")))

(defn display-money
  [{:keys [amount currency]}]
  (let [{:keys [divisor code sign]} currency]
    (if (= code :ukg)
      (display-galleons amount)
      (let [major (int (/ amount divisor))
            minor (mod amount divisor)]
        (str sign major "." minor)))))

(defn new-money
  ([amount] (new-money amount default-currency))
  ([amount currency]
   (let [money {:amount amount
                :currency currency}]
     (assoc money :display (display-money money)))))

(defn rand-money
  []
  (new-money (rand-int 99999) ((rand-nth [:usd :brl :ukg]) currencies)))

(def zero-money (new-money 0))

(def hundred-dollars (new-money 10000 (:usd currencies)))

(display-money hundred-dollars) ; => "$100.0"
(display-money (new-money 10000 (:brl currencies))) ; => "R$100.0"
(display-money (new-money 10000 (:ukg currencies))) ; => "20 Galleons, 8 Sickles, and 24 Knuts"

; Providing domain operations

(defn- same-currency?
  "Returns true if the Currencies of the Money entities are the same"
  ([m1 m2] (= (:currency m1) (:currency m2)))
  ([m1 m2 & monies] (every? true? (map #(same-currency? m1 %) (conj monies m2)))))

(defn- same-amount?
  "Returns true if the amount of the Money entities are the same"
  ([m1 m2] (zero? (.compareTo (:amount m1) (:amount m2))))
  ([m1 m2 & monies] (every? true? (map #(same-amount? m1 %) (conj monies m2)))))

(defn- ensure-same-currency!
  "Throws an exception if the Currencies do not match, true otherwise"
  ([m1 m2]
   (or (same-currency? m1 m2)
       (throw (ex-info "Currencies do not match." {:m1 m1 :m2 m2}))))
  ([m1 m2 & monies]
   (every? true? (map #(ensure-same-currency! m1 %) (conj monies m2)))))

(defn =$
  "Returns true if Money entities are equal"
  ([m1 m2]
   (and (same-currency? m1 m2)
        (same-amount? m1 m2)))
  ([m1 m2 & monies] (every? true? (map #(=$ m1 %) (conj monies m2)))))

(defn +$
  "Returns a Money object equal to the sum of the Money arguments"
  ([m1 m2]
   (ensure-same-currency! m1 m2)
   (new-money (+ (:amount m1) (:amount m2)) (:currency m1)))
  ([m1 m2 & monies]
   (apply ensure-same-currency! m1 m2 monies)
   (let [amounts (map :amount (conj monies m1 m2))
         new-amount (reduce + amounts)]
     (new-money new-amount (:currency m1)))))
