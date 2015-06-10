(ns clojure.core.matrix.test-instances
  (:use clojure.core.matrix)
  (:use clojure.test)
  (:require [clojure.core.matrix.compliance-tester :as ctest]))

(deftest clojure.core.matrix.test-instances
  (ctest/instance-test 0)
  (ctest/instance-test -1.75)
  (ctest/instance-test [1 2 3])
  (ctest/instance-test [[1] [2]])
  (ctest/instance-test [[1 2] [3 4]])
  (ctest/instance-test (double-array [1 2 -3]))
  (ctest/instance-test (object-array [-1 2 -3]))
  ;; (ctest/instance-test (array :vectorz [[1 2] [3 4]]))
  )

