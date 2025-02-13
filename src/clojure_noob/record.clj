(ns clojure-noob.record)

(def william {:first-name    "William"
              :last-name     "McQueen"
              :business-unit :fraud
              :id            111})

(println william)

(defrecord Employee [first-name
                     last-name
                     business-unit
                     id])

(def gustavo (->Employee "Gustavo"
                         "Flôr"
                         :fraud
                         222))

(def paul (map->Employee {:first-name    "Paul"
                          :last-name     "Jobs"
                          :business-unit :fraud
                          :id            333}))

(println gustavo)
(println paul)
(println (assoc gustavo :birth-day 3))
