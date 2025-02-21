(ns clojure-noob.reverse-polish-notation)

(defn- apply-rpn
  [numbers token]
  (if (fn? token)
    (conj (vec (drop-last 2 numbers)) (apply token (take-last 2 numbers)))
    (conj numbers token)))

(defn evaluate-rpn
  [tokens]
  (first (reduce apply-rpn [] tokens)))

(evaluate-rpn [3 4 + 2 * 7 /]) ; => 2

;; 3 4 + 2 * 7 /
;; 7 2 * 7 /
;; 14 7 /
;; 2

(evaluate-rpn [5 1 2 + 4 * + 3 -]) ; => 14

;; 5 1 2 + 4 * + 3 -
;; 5 3 4 * + 3 -
;; 5 12 + 3 -
;; 17 3 -
;; 14
