(ns runtime-infer-demo.server-port
  {:lang :core.typed
   :core.typed {:features #{:runtime-infer}}}
  (:require [clojure.core.typed :as t]))

(defn configure-server-port [{:keys [server] :as c} p]
  (assoc c :server (assoc server :port p)))
