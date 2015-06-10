(ns clojure.core.matrix.test-instances
  (:use clojure.core.matrix)
  (:use clojure.test)
  (:require [clojure.core.matrix.compliance-tester :as ctest]))

(deftest clojure.core.matrix.test-instances
  (ctest/instance-test [1 2 3]))

