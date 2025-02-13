(ns clojure-noob.maps-and-threading)

(def storage {:backpack 10
              :shirt    5})

(:backpack storage) ; => 10

(count storage) ; => 2

(keys storage) ; => (:backpack :shirt)
(vals storage) ; => (10 5)

(assoc storage :chair 3) ; => {:backpack 10, :shirt 5, :chair 3}
(assoc storage :backpack 1) ; => {:backpack 1, :shirt 5}

(update storage :shirt inc) ; => {:backpack 10, :shirt 6}
(update storage :backpack #(- % 3)) ; => {:backpack 7, :shirt 5}

(dissoc storage :shirt) ; => {:backpack 10}

(def order {:backpack {:quantity 2
                       :price    80}
            :shirt    {:quantity 3
                       :price    40}})

(assoc order :keychain {:quantity 1
                        :price 10})

(get order :chair) ; => nil
(get order :chair {}) ; => {}
(:chair order) ; => nil
(:chair order {}) ; => {}

(:backpack order) ; => {:quantity 2, :price 80}
(order :backpack) ; => {:quantity 2, :price 80}

(get nil :chair) ; => nil
(:chair nil) ; => nil

(update-in order [:backpack :quantity] inc) ; => {:backpack {:quantity 3, :price 80}, :shirt {:quantity 3, :price 40}}

(-> order :backpack :quantity) ; threading == pipeline

(-> order
    :backpack
    :price
    (#(+ % 5))) ; => 85
