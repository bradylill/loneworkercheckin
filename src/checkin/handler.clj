(ns checkin.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [checkin.routes.auth :as auth]
            [net.cgrand.enlive-html :as enlive]))

(def login (enlive/html-resource "login.html"))

(defroutes app-routes
           (GET "/" [] "Hello World with Compojure")
           ; TODO: move login routes to specific collection
           (GET "/login" [] (enlive/emit* login))
           (GET "/auth/callback" {params :query-params} (auth/process-auth-response params))
           (route/not-found "Not Found"))

(def app
  (wrap-defaults app-routes site-defaults))
