(ns runtime-infer-demo.core-test
  (:require [clojure.test :refer :all]
            [runtime-infer-demo.core :refer :all]))

(deftest neg-test
  (testing "long input"
    (is (= (neg 1) 
           -1)))
  (testing "works with strings"
    (is (= (neg "-42")
           42))))

