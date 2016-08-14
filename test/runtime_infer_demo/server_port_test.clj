(ns runtime-infer-demo.server-port-test
  (:require [clojure.test :refer :all]
            [runtime-infer-demo.server-port :refer :all]))

(deftest configure-server-port-test
  (is
    (= (->
         (configure-server-port
           {:client {:port 5566
                     :hostname "client"
                     :password "mysecret"}
            :server {:hostname "server"
                     :password "theadmin"
                     :port 6666}}
           7646)
         (get-in [:server :port]))
       7646)))
