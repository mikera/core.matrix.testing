(ns clojure.core.matrix.testing.generators
  (:require [clojure.test :refer :all]
            [clojure.test.check.generators :as gen]
            [clojure.test.check.properties :as prop]
            [clojure.test.check.clojure-test :refer (defspec)]
            [clojure.core.matrix :refer :all]))

(set! *warn-on-reflection* true)
(set! *unchecked-math* true)

(defn gen-resize 
  "Creates a generator that pre-modifies the 'size' pramater with the function f. Use if you want to 
   have the size grow at a different rate from the normal linear scaling."
  ([f gen]
    (let [gf (or (:gen gen) "gen paramter must be a test.check generator")
          new-gf (fn [rnd size]
                   (gf rnd (f size)))]
      (clojure.test.check.generators.Generator. new-gf))))


(def gen-dims
  "A generator for dimensionalities (including zero). Grows quite slowly."
  (gen-resize #(Math/pow (double %) 0.333) gen/pos-int))

(def gen-s-pos-dims
  "A generator for dimensionalities greated than or equal to one. Grows quite slowly."
  (gen/such-that pos? gen-dims 100))

(defn gen-shape 
  "Creates a generator that returns valid core.matrix shapes for arrays, with strictly positive 
   dimension sizes. Grows linearly in the number of elements."
  ([]
    (gen-shape gen-dims))
  ([gen-dims]
    (gen/bind gen-dims
              (fn [dims]
                (gen/vector (gen-resize #(Math/pow (double %) (/ 1.0 (double dims))) gen/s-pos-int) 
                            dims)))))
