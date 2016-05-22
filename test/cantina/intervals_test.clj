(ns cantina.intervals-test
  (:require [clojure.test :refer :all]
            [cantina.intervals :refer :all]))


(deftest test-helpers
  (testing "index-of"
    (is (= (index-of [:a :b :c] :c) 2)))

  (testing "flatify-if-needed"
    (is (= (flatify-if-needed [:a]) [:a]) "Doesn't change non-sharps.")
    (is (= (flatify-if-needed [:a :sharp]) [:b :flat]) "Does change sharps to flats.")))

(deftest test-basic-intervals

  (testing "half-step"
    (is (= (second (half-step [0 [:b :flat]])) [:b]))
    (is (= (second (half-step [0 [:g :sharp]])) [:a])))

  (testing "half-step-down"
    (is (= (half-step-down [0 [:b]]) [0 [:b :flat]]))
    (is (= (half-step-down [1 [:c]]) [0 [:b]])) "Reduces octave when appropriate.")

  (testing "whole-step"
    (is (= (whole-step [0 [:c]]) [0 [:d]]))
    (is (= (whole-step [0 [:b]]) [1 [:d :flat]]) "Increases octave")))

(deftest test-major-intervals
  (testing "major-second"
    (is (= (major-second [0 [:c]]) [0 [:d]] )))

  (testing "major-third"
    (is (= (major-third [0 [:c]]) [0 [:e]]))
    (is (= (major-third [0 [:g]]) [0 [:b]])))

  (testing "perfect-fourth"
    (is (= (perfect-fourth [0 [:c]]) [0 [:f]]))
    (is (= (perfect-fourth [0 [:g]]) [1 [:c]])))

  (testing "perfect-fifth"
    (is (= (perfect-fifth [0 [:c]]) [0 [:g]]))
    (is (= (perfect-fifth [0 [:g]]) [1 [:d]])))

  (testing "major-sixth"
    (is (= (major-sixth [0 [:c]]) [0 [:a]]))
    (is (= (major-sixth [0 [:e :flat]]) [1 [:c]])))

  (testing "major-seventh"
    (is (= (major-seventh [0 [:c]]) [0 [:b]]))
    (is (= (major-seventh [0 [:d]]) [1 [:d :flat]])))

  (testing "octave-interval"
    (is (= (octave-interval [0 [:c]]) [1 [:c]]))
    (is (= (octave-interval [0 [:e :flat]]) [1 [:e :flat]])))
)

(deftest test-minor-intervals
  (testing "minor-second"
    (is (= (minor-second [0 [:c]]) [0 [:d :flat]])))
  (testing "minor-third"
    (is (= (minor-third [0 [:c]]) [0 [:e :flat]])))
  (testing "minor-sixth"
    (is (= (minor-sixth [0 [:c]]) [0 [:a :flat]])))
  (testing "minor-seventh"
    (is (= (minor-seventh [0 [:c]]) [0 [:b :flat]])))
)

(deftest test-augment-and-diminish
  (testing "augment"
    (is (= ((augmented perfect-fourth) [0 [:c]])) [0 [:g :flat]]))
  (testing "diminish"
    (is (= ((diminished perfect-fifth) [0 [:c]])) [0 [:g :flat]])
  )
)
