(defproject testclj "0.1.0-SNAPSHOT"
  :min-lein-version "2.0.0"
  :description "Quick test Clojure app"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [org.clojure/clojure-contrib "1.2.0"]
                 [ring/ring-core "1.2.0"]
                 [ring/ring-jetty-adapter "1.2.0"]
                 [org.mortbay.jetty/jetty "6.1.26"]
                 [compojure "1.1.5"]
                 [ns-tracker "0.2.2"]]
  :profiles {:dev {:dependencies [[ring-serve "0.1.2"]
                                  [ring-mock "0.1.5"]]}}
  :main testclj.core)
