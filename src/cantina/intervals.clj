(ns cantina.intervals)

(def chromatic
  "A list of the notes of the chromatic scale. This is our pallete."
  [[:a] [:b :flat] [:b] [:c] [:d :flat] [:d] [:e :flat] [:e] [:f] [:g :flat] [:g] [:a :flat]]
  )

(def equivalent-notes
  "Map of equivalent notes."
  {[:a :sharp] [:b :flat],
   [:c :sharp] [:d :flat],
   [:d :sharp] [:e :flat],
   [:f :sharp] [:g :flat],
   [:g :sharp] [:a :flat]}
  )

(defn flatify-if-needed
  "replace a note with its flat equivalent, else leave alone."
  [note]
  (if (some #{:sharp} note)
    (equivalent-notes note)
    note)
  )


(defn index-of
  "returns the index of the value in vec"
  [vec val]
  (count (take-while (partial not= val) vec))
  )


(defn half-step
  "Get the note a half step above the input note.  A note is of the form [octave [note value]]"
  [[octave note]]
  ;(println (str "Octave: " octave "; note: " note))
  [ (if (= note [:b])
      (inc octave)
      octave)
    ( nth chromatic (mod (inc (index-of chromatic (flatify-if-needed note))) (count chromatic) ) )]
  )

(defn half-step-down
  "Gets the note a half step down from the input note."
  [[octave note]]
  ;(println (str "Octave: " octave "; note: " note))
  [ (if (= note [:c])
      (dec octave)
      octave)
    (nth chromatic (mod (dec (index-of chromatic (flatify-if-needed note))) (count chromatic) ) )]
  )

(defn whole-step
  [octave-and-note]
  (half-step
    (half-step octave-and-note))
  )

(defn major-second
  "Returns a major second interval from note"
  [octave-and-note]
  (whole-step octave-and-note)
  )

(defn minor-second
  "One half step"
  [octave-and-note]
  (half-step octave-and-note)
  )

(defn major-third
  "Returns a major third interval from note.  Four half steps."
  [octave-and-note]
  (-> octave-and-note
      whole-step
      whole-step)
  )

(defn minor-third
  "Three half steps."
  [octave-and-note]
  (-> octave-and-note
      major-third
      half-step-down)
  )

(defn perfect-fourth
  "Five half steps"
  [octave-and-note]
  (-> octave-and-note
      whole-step
      whole-step
      half-step)
  )

(defn perfect-fifth
  "Seven half steps"
  [octave-and-note]
  (-> octave-and-note
      whole-step
      whole-step
      whole-step
      half-step)
  )

(defn major-sixth
  "Nine half steps"
  [octave-and-note]
  (-> octave-and-note
      whole-step
      whole-step
      whole-step
      whole-step
      half-step)
  )

(defn minor-sixth
  "Eight half steps."
  [octave-and-note]
  (-> octave-and-note
      major-sixth
      half-step-down)
  )

(defn major-seventh
  "Eleven half-steps"
  [octave-and-note]
  (-> octave-and-note
      whole-step
      whole-step
      whole-step
      whole-step
      whole-step
      half-step)
  )

(defn minor-seventh
  "Ten half steps"
  [octave-and-note]
  (-> octave-and-note
      major-seventh
      half-step-down)
  )

(defn octave-interval
  "Twelve half-steps"
  [octave-and-note]
  (-> octave-and-note
      whole-step
      whole-step
      whole-step
      whole-step
      whole-step
      whole-step)
  )

(defn augmented
  "Takes an interval function and returns a function that
   returns the augmented version of that interval; i.e.
   one half step higher"
  [fn]
  (comp half-step fn)
  )

(defn diminished
  "Takes an interval function and returns a function
  that returns the diminished version of that interval; i.e.
  one half step lower."
  [fn]
  (comp half-step-down fn)
  )

