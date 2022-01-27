(ns clojure-noob.caesar-cipher)

(def shift 3)

(defn bytes-by-text
  "Get bytes list by text"
  [text]
  (map byte (char-array text)))

(defn inc-bytes-by-shift
  "Increment bytes by a shift value"
  [bytes, shift]
  (map #(+ % shift) bytes))

(defn inc-bytes-by-default-shift
  "Increment bytes by Caesar Cipher shift value"
  [bytes]
  (inc-bytes-by-shift bytes shift))

(defn dec-bytes-by-shift
  "Decrement bytes by a shift value"
  [bytes, shift]
  (map #(- % shift) bytes))

(defn dec-bytes-by-default-shift
  "Decrement bytes by Caesar Cipher shift value"
  [bytes]
  (dec-bytes-by-shift bytes shift))

(defn text-by-bytes
  "Get string by bytes list"
  [bytes]
  (apply str (map char bytes)))

(defn encode
  "Encode Caesar Cipher by plaintext"
  [plaintext]
  (->> (bytes-by-text plaintext)
       (inc-bytes-by-default-shift)
       (text-by-bytes)))

(defn decode
  "Decode Caesar Cipher by ciphertext"
  [ciphertext]
  (->> (bytes-by-text ciphertext)
       (dec-bytes-by-default-shift)
       (text-by-bytes)))
