(ns clojure-noob.first-functions)

(def areas {:eng {:name "Software Engineer"}
            :em {:name "Engineering Manager"}})

(def users [{:name "Gustavo" :area :eng}
            {:name "Carlos":area :eng}
            {:name "Abraham" :area :em}])

(defn do-something
  []
  (println "1. ---")
  (println "2. Hello")
  (println (map #(areas (:area %)) users))
  (users 0))

(do-something)
