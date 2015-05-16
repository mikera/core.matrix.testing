(ns clojure.core.matrix.testing.generators
  (:require [clojure.test :refer :all]
            [clojure.test.check.generators :as gen]
            [clojure.test.check.properties :as prop]
            [clojure.test.check.clojure-test :refer (defspec)]
            [clojure.core.matrix :refer :all]))

(set! *warn-on-reflection* true)
(set! *unchecked-math* true)
