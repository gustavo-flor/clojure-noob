(ns clojure-noob.core)

(def message "I'm a little teapot!")

(def person {:name "Gustavo Flôr"
             :age 20
             :role "Software Engineer"})

(def caesar-cipher-shift 3)

(defn bytes-by-text
  [text]
  (map byte (char-array text)))

(defn inc-bytes-by-shift
  [byte]
  (map #(+ % caesar-cipher-shift) byte))

(defn text-by-chars
  [bytes]
  (apply str (map char bytes)))

(defn caesar-cipher-by-text
  [text]
  (->> (bytes-by-text text)
       (inc-bytes-by-shift)
       (text-by-chars)))

(def cipher-message (caesar-cipher-by-text message))

(defn -main
  "I don't do a lot ... yet."
  [& args]
  (println (str "Hello, welcome to " (:name person "Anonymous") "'s (my) code" ))
  (println (str "I'm " (person :age) " years old"))
  (println (str "I'm a " (get person :role)))
  (println (str "I love " (:favorite-thing person "Pizza")))
  (println (str ":::Caesar Cipher (Shift: " caesar-cipher-shift "):::"))
  (println (str "Plaintext: " message " Ciphertext: " cipher-message)))
