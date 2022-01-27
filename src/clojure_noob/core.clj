(ns clojure-noob.core
  (:gen-class)
  (:require [clojure-noob.fibonacci :as fibonacci])
  (:require [clojure-noob.caesar-cipher :as caesar-cipher]))

(def plaintext "I'm a little teapot!")

(def ciphertext "Khoor#Zruog")

(def person {:name "Gustavo Flôr"
             :age 20
             :role "Software Engineer"})

(def number 9)

(defn -main
  "I don't do a lot ... yet."
  [& args]
  (println (str "Hello, welcome to " (:name person "Anonymous") "'s (my) code" ))
  (println (str "I'm " (person :age) " years old"))
  (println (str "I'm a " (get person :role)))
  (println (str "I love " (:favorite-thing person "Pizza")))
  (println (str "Arguments passed to the function: " args))
  (println (str "Fibonacci for " number " is " (fibonacci/calc number)))
  (println (str ":::Caesar Cipher (Shift: " caesar-cipher/shift "):::"))
  (println (str "Plaintext: " plaintext " Ciphertext: " (caesar-cipher/encode plaintext)))
  (println (str "Plaintext: " (caesar-cipher/decode ciphertext) " Ciphertext: " ciphertext)))
