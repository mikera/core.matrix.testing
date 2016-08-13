(ns clojure.core.matrix.properties
  (:require [clojure.test :refer :all]
            [clojure.test.check.generators :as gen]
            [clojure.test.check.properties :as prop]
            [clojure.test.check.clojure-test :refer (defspec)]
            [clojure.core.matrix.generators :as genm]
            [clojure.core.matrix :refer :all]))

;; Property based testing of randomly generated core.matrix arrays

;; # Global constants
;;
;; This constant defines a number of tests for each of properties

(def num-tests 100)

;; ## Predicates

(defn proper-matrix?
  "Check if provided nested vectors form a proper matrix — that is, all nested
   vectors have the same length"
  [mtx]
  (let [first-size (count (first mtx))]
    (every? (partial = first-size) (map count mtx))))

;; TODO: write proper-array?

;; # General functionality tests
;;
;; Check if we can construct matrix and get nested vectors back

(defspec to-nested-vector num-tests
  (let [array-generator (genm/gen-array (genm/gen-shape) genm/gen-double genm/gen-impl)]
    (prop/for-all [a array-generator]
      (let [pa (to-nested-vectors a)]
        (equals a pa))
      (let [pa (to-nested-vectors a)]
        (equals (or (shape a) []) (or (shape pa) [])))))) ;; to allow for nil shape

;; Check if new-vector returns zero- or null- filled vector of given size
(defspec new-vector-zero-filled num-tests
  (prop/for-all [l gen/pos-int
                 impl genm/gen-impl]
    (let [vec (new-vector l)
          vec-vec (to-nested-vectors vec)]
      (and (every? #(or (nil? %) (== 0 %))
                   vec-vec)
           (== l (count vec-vec))))))



(defspec householder-matrix-props num-tests
  (prop/for-all [;; shrinking of keywords is broken in current simple-check
                 ;; impl (gen/elements [:vectorz])
                 v (gen/vector gen/int 5)]
    (let [v (->> v (array :vectorz) normalise)
          i (array :vectorz (identity-matrix 5))
          m (sub i (emul 2.0 (outer-product v v)))]
      (is (equals m (transpose m) 1.0E-12))
      (is (equals m (inverse m) 1.0E-12))
      (is (equals (mmul m m) i 1.0E-12)))))

;; occasionally failing test - see issue qr-props test failure #213
;(defspec qr-props num-tests
;  (prop/for-all
;   [mtx (genm/gen-matrix)]
;   (if (>= (row-count mtx) (column-count mtx)) ;; TODO: fix when rows < cols is supported
;     (if-let [{:keys [Q R]} (qr mtx)]
;       (do  (is (orthogonal? Q))
;            (is (equals mtx (mmul Q R) 1.0E-8)))
;       true)
;     true)))
