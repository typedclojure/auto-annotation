(defproject runtime-infer-demo "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :repositories {"sonatype-oss-public" "https://oss.sonatype.org/content/groups/public/"}

  :dependencies [[org.clojure/clojure "1.9.0-alpha10"]
                 ;; minimum version for runtime inference
                 [org.clojure/core.typed "0.3.24"]
                 [org.clojure/test.check "0.9.0"]]
  ;; must add these injections
  :injections [(require 'clojure.core.typed)
               (clojure.core.typed/install
                 #{:load})])
