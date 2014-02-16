(ns testclj.reload
  (:gen-class)
  (:use [ns-tracker.core]))

; This code comes directly from
; http://cmdrdats.wordpress.com/2012/08/14/auto-reloading-code-with-clojure/
(defn check-namespace-changes [track]
 (try
   (doseq [ns-sym (track)]
     (println "Reloading namespace:" ns-sym)
     (require ns-sym :reload))
   (catch Throwable e (.printStackTrace e)))
   (Thread/sleep 500))
 
(defn start-nstracker []
 (let [track (ns-tracker ["src" "checkouts"])]
   (doto
     (Thread.
       #(while true
         (check-namespace-changes track)))
     (.setDaemon true)
     (.start))))
