(ns clojure-noob.fixture)

(defn rand-account
  ([]
   (rand-account {}))
  ([base-account]
   (let [random-account {:id          (rand-int 999999)
                         :customer-id (rand-int 999999)
                         :balance     (bigdec (rand 999999))}]
     (merge random-account base-account))))
