(ns testclj.core
  (:gen-class)
  (:use
    [compojure.core :only [defroutes GET]]
    [compojure.handler :only [site]]
    org.httpkit.server)
  (:require
            [compojure.route :as route]
            [clojure.string :as string]
            [testclj.reload :as reload]))

(defn parse-int
  "Parse a string into an integer"
  [str]
  (Integer/parseInt str))

(defn filter-re
  "Filters a collection of strings on a regular expression"
  [regex col]
  (filter #(re-matches regex %) col))

(defn split-uri
  "Splits a URI on / into a vector"
  [uri]
  (string/split uri #"/"))

(def operations {"-" - "+" + "mod" mod "div" / "mul" *})

(defn str-rest
  "Returns the 'rest' of the string, a.k.a. remove the first character."
  [string]
  (apply str (rest string)))

(defn operate-on-numbers-in-uri
  [uri]
  (let [parts     (rest (split-uri (str-rest uri)))
        operation (first parts)
        operands  (rest parts)]
    (if (contains? operations operation)
      (->> operands
           (filter-re #"\d+")
           (map parse-int)
           (reduce (get operations operation))
           (str "result is "))
      "Invalid operation"
      )
    ))

(defroutes app-routes
  (GET "/" [] "Hello from Compojure")
  (GET "/:who" [who] (str "Hello to '" who "' from Compojure"))
  (GET "/math/*" {uri :uri} (operate-on-numbers-in-uri uri))
  (route/resources "/")
  (route/not-found "Not Found"))

(def app
  (site app-routes))

(defn -main [port]
  (run-server (site #'app-routes) {:port (Integer. port)}))

; 1. run 'lein repl' in another tmux tab
; 2. run 'cp%' on the next line to start the dev server
; 3. run 'cp%' on the next line to stop the dev server
(comment
  (def stop-dev (dev-server))
  (stop-dev)
  )
(defn dev-server
  "Start a dev server with live-reload of namespaces"
  []
  (reload/start-nstracker)
  (-main 8080))
