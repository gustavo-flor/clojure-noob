(ns clojure-noob.word-count)

(defn- word-count*
  [acc v]
  (assoc acc v (+ (get acc v 0) 1)))

(defn word-count
  [text]
  (-> text
      (clojure.string/split #" ")
      (#(reduce word-count* {} %))))

(word-count "hello world hello")
;; => {"hello" 2, "world" 1}

(word-count "Clojure is fun and Clojure is powerful")
;; => {"Clojure" 2, "is" 2, "fun" 1, "and" 1, "powerful" 1}
