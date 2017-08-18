(ns melinda-marc-validate-cli.core
  (:require [marclojure.core :as marc]
            [marclojure.parser :as parser]
            [marclojure.writer :as writer]
            [clj-http.client :as client]
            [environ.core :refer [env]]
            [clojure.walk :refer [prewalk-replace]])
  (:gen-class))

(def creds
  {:user (env :user)
   :pass (env :pass)})

(def config (read-string (slurp "config.edn")))

(defn update-subfield
  [from to record]
  (prewalk-replace {from to} record))

(defn get-record [id]
  (let [record-url (str (:host config) id)]
    (parser/load-data :marcxml record-url)))

(defn put-to-db
  [id record]
  (client/put (str (:host config) id)
              {:basic-auth [(env :user) (env :pass)]
               :headers {"Content-Type" "text/xml"}
               :body (writer/to-xml-string record)}))

(defn fix-record [id]
  (->> id
       get-record
       first
       (update-subfield {:code "a" :data "uskonpuhdistus"}
                        {:code "a" :data "reformaatio"})
       (put-to-db id)))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))
