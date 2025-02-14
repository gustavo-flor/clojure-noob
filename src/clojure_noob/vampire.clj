(ns clojure-noob.vampire)

(def vampire-database
  {0 {:make-blood-puns? false :has-pulse? true :name "McFishwich"}
   1 {:make-blood-puns? false :has-pulse? true :name "McMackson"}
   2 {:make-blood-puns? true :has-pulse? false :name "Damon Salvatore"}
   3 {:make-blood-puns? true :has-pulse? true :name "Mickey Mouse"}})

(defn vampire-related-details
  [social-security-number]
  (Thread/sleep 1000)
  (get vampire-database social-security-number))

(defn vampire?
  [record]
  (and (:makes-blood-puns? record)
       (not (:has-pulse? record))
       record))

(defn identify-vampire
  [social-security-numbers]
  (first (filter vampire?
                 (map vampire-related-details social-security-numbers))))

(time (identify-vampire (vampire-database 0)))
