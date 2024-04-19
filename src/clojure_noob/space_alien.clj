(ns clojure-noob.space-alien)

(def asym-alien-body-parts [{:name "head" :size 5}
                            {:name "0-nth-eye" :size 1}
                            {:name "0-nth-ear" :size 3}
                            {:name "mouth" :size 2}
                            {:name "nose" :size 7}
                            {:name "neck" :size 1}
                            {:name "0-nth-shoulder" :size 1}
                            {:name "0-nth-upper-arm" :size 3}
                            {:name "chest" :size 5}
                            {:name "back" :size 8}
                            {:name "0-nth-forearm" :size 2}
                            {:name "abdomen" :size 3}
                            {:name "0-nth-kidney" :size 2}
                            {:name "0-nth-hand" :size 1}
                            {:name "0-nth-knee" :size 1}
                            {:name "0-nth-thigh" :size 1}
                            {:name "0-nth-lower-leg" :size 2}
                            {:name "0-nth-achilles" :size 2}
                            {:name "0-nth-foot" :size 1}])

(defn matching-parts
  [part, amount]
  (vector
    (for [i (range amount)]
      {:name (clojure.string/replace (:name part) #"^0-nth" (str i "-nth"))
       :size (:size part)})))

(defn symmetrize-body-parts
  "Expects a seq of maps that have a :name and :size"
  [asym-body-parts]
  (loop [remaining-asym-parts asym-body-parts
         final-body-parts []]
    (if (empty? remaining-asym-parts)
      final-body-parts
      (let [[part & remaining] remaining-asym-parts]
        (recur remaining
               (into final-body-parts
                     (set (into (matching-parts part 5) [part]))))))))

(def alien-body-parts (symmetrize-body-parts asym-alien-body-parts))
