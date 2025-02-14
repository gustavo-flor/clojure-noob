(ns clojure-noob.sequences)

(def numbers (range 10))

(->> numbers
     (filter odd?)
     (map #(* % %))
     (clojure.string/join ", ")) ; => "1, 9, 25, 49, 81"

(seq '(1 2 3)) ; => (1 2 3) :: list is a sequence

(seq [1 2 3]) ; => (1 2 3) :: vector is a sequence

(seq #{1 2 3}) ; => (1 3 2) :: set is a sequence

(seq {:k1 1 :k2 2 :k3 3}) ; => ([:k1 1] [:k2 2] [:k3 3]) :: map is a sequence (of entries)

(not-any? #(> % 10) numbers) ; => true
(not-any? #(= 5 %) numbers) ; => false

(some #{3 4 5} numbers) ; => 3 :: returns the first value present
