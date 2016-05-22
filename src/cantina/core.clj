(ns cantina.core
  (:gen-class))

(require '[alda.lisp :refer :all])
(require '[alda.now  :as    now])

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!")
  (now/play!
  (part "accordion"
    (note (pitch :c) (duration (note-length 8)))
    (note (pitch :d))
    (note (pitch :e :sharp))
    (note (pitch :f))
    (note (pitch :g))
    (note (pitch :a :flat))
    (note (pitch :b))
    (octave :up)
    (note (pitch :c))))
  )
