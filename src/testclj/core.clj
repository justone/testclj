(ns testclj.core
  (:gen-class)
  (:use [ring.adapter.jetty :only [run-jetty]]
        [compojure.core])
  (:require [compojure.handler :as handler]
            [compojure.route :as route]))

(defn parse-int
  [str]
  (. Integer parseInt str))

(defroutes app-routes
  (GET "/" [] "Hello from Compojure")
  (GET "/:who" [who] (str "Hello to '" who "' from Compojure"))
  (GET "/add/:num1/:num2" [num1 num2] (str "The sum is " (+ (parse-int num1) (parse-int num2))))
  (route/resources "/")
  (route/not-found "Not Found"))

(def app
  (handler/site app-routes))

(defn -main [port]
  (run-jetty app {:port (Integer. port)}))
