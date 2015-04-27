(defproject net.mikera/core.matrix.testing "0.5.0"
  :description "Testing library for core.matrix implementations"
  :dependencies [[org.clojure/clojure "1.7.0-beta2"]
                 [net.mikera/core.matrix "0.34.0"]
                 [net.mikera/clojure-utils "0.6.1"]]
  
  :repositories {"clojars.org" "http://clojars.org/repo"}
  :parent [net.mikera/clojure-pom "0.4.0"]
  :source-paths ["src/main/clojure"]
  :test-paths ["src/test/clojure"]
  :resource-paths ["src/test/resources"])
