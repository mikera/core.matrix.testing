(defproject net.mikera/core.matrix.testing "0.0.1-SNAPSHOT"
  :description "Testing library for core.matrix implementations"
  
  :dependencies [[org.clojure/clojure "1.7.0-beta2"]
                 [org.clojure/test.check "0.7.0"]
                 [net.mikera/core.matrix "0.34.0"]
                 [net.mikera/vectorz-clj "0.29.0"]
                 [net.mikera/clojure-utils "0.6.1"]
                 [net.mikera/cljunit "0.3.1"]]
  
  :repositories {"clojars.org" "http://clojars.org/repo"}
  :parent [net.mikera/clojure-pom "0.4.0"]
  :source-paths ["src/main/clojure"]
  :test-paths ["src/test/clojure"]
  :resource-paths ["src/test/resources"])
