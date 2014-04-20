(defproject testclj "0.1.0-SNAPSHOT"
  :min-lein-version "2.0.0"
  :description "Quick test Clojure app"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [http-kit  "2.1.18"]
                 [compojure "1.1.6"]
                 [ns-tracker "0.2.2"]]
  ;:profiles {:dev {:dependencies []}}
  :main testclj.core)
