(ns testclj.core
  (:use [ring.adapter.jetty :only [run-jetty]]))

(defn handler [request]
  {:status 200
     :headers {"Content-Type" "text/html"}
        :body "Hello from Clojure!"})

(defn -main [port]
  (run-jetty handler {:port (Integer. port)}))
