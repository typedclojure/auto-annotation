(ns runtime-infer-demo.core
  {:lang :core.typed
   :core.typed {:features #{:runtime-infer}}}
  (:require [clojure.core.typed :as t]))

(defn neg
  "Returns the negation of the input.
  Works with strings.
  
  eg. (neg 1) ;=> -1"
  [x]
  (cond
    (string? x) (- (Long/parseLong x))
    :else (- x)))
