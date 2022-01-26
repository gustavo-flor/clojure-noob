(ns clojure-noob.core
  (:gen-class))

(def message "I'm a little teapot!")

(def character "Chewbacca")

(def villains ["Lord Voldemort"
               "Thanos"
               "Darth Vader"])

(def person {:name "Gustavo Flôr"
             :age 20
             :role "Software Engineer"})

(defn create-person
  [name, age, role]
  (hash-map :name name :age age :role role))

(def other-person (create-person "Pedro Scooby", 30, "Surfer"))

(defn -main
  "I don't do a lot ... yet."
  [& args]
  (println message)
  (println (str "\"Uggllglglglglglglll\" - " character))
  (println (first villains))
  (println (last villains))
  (println (get villains 1))
  (println (get villains (- (count villains) 2)))
  (println (if args 1 0))
  (println (get person :name))
  (println (person :name))
  (println (:name person))
  (println (conj villains "Joker"))
  (println other-person)
  (println (* 1 2 3 4)))
