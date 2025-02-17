(ns clojure-noob.atoms-and-refs)

; atoms...

(def learn-atom (atom 0))

(+ (deref learn-atom) 1) ; => 1
(+ @learn-atom 1) ; => 1 :: `@<symbol>` will be transformed into (deref <symbol>)

(println @learn-atom)

; `swap!` is thread safe!

(swap! learn-atom inc) ; it always increments [when running on REPL]
(swap! learn-atom / 2) ; in the end it will run a function like: #(/ % 2), 'cause the order of the args

; `reset!` is NOT thread safe!

(reset! learn-atom 10) ; => 10

; refs...

(def learn-ref (ref [:red :green]))

(conj (deref learn-ref) :blue) ; => [:red :green :blue]
(conj @learn-ref :blue) ; => [:red :green :blue]

(def other-ref (ref [:purple :blue]))

(dosync
  (let [moving (last @other-ref)]
    (alter learn-ref conj moving)
    (alter other-ref pop)))

(println @learn-ref @other-ref)

(dosync
  (commute learn-ref conj :orange)
  (commute other-ref conj :yellow))

(println @learn-ref @other-ref)

; `alter`: use to update atom-dependent values
; `commute`: use to update non-atom-dependent values
