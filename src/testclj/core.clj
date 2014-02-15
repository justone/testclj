(ns testclj.core
  (:gen-class)
  (:use [ring.adapter.jetty :only [run-jetty]]
        [compojure.core])
  (:require [compojure.handler :as handler]
            [compojure.route :as route]
            [clojure.string :as string]))

(defn parse-int
  "Parse a string into an integer"
  [str]
  (. Integer parseInt str))

(defn filter-re
  "Filters a collection of strings on a regular expression"
  [regex col]
  (filter #(re-matches regex %) col))

(defn split-uri
  "Splits a URI on / into a vector"
  [uri]
  (string/split uri #"/"))

(defn sum-numbers-in-uri
  [uri]
  (reduce + (map parse-int (filter-re #"\d+" (split-uri uri)))))

(defroutes app-routes
  (GET "/" [] "Hello from Compojure")
  (GET "/:who" [who] (str "Hello to '" who "' from Compojure"))
  (GET "/a/*" {uri :uri} (str "sum is " (sum-numbers-in-uri uri)))
  (route/resources "/")
  (route/not-found "Not Found"))

(def app
  (handler/site app-routes))

(defn -main [port]
  (run-jetty app {:port (Integer. port)}))
