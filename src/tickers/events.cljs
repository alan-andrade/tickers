(ns tickers.events
  (:require
   [re-frame.core :as re-frame]
   [tickers.db :as db]
   [ajax.core :as ajax]
   ))

(re-frame/reg-event-db
 ::initialize-db
 (fn [_ _]
   db/default-db))

(re-frame/reg-event-fx
  :request-trending-coins
  (fn [_ _]
    {:http-xhrio {:method          :get
                  :uri             "https://api.coingecko.com/api/v3/search/trending"
                  :timeout         8000
                  :response-format (ajax/json-response-format {:keywords? true})
                  :on-success      [:receive-trending-coins]
                  :on-failure      [:handle-http-error]}}))

(re-frame/reg-event-db
  :receive-trending-coins
  (fn [db [_ result]]
    (assoc db :trending-coins result)))

(re-frame/reg-event-db
  :handle-http-error
  (fn [db [_ result]]
    (assoc db :http-errors result)))
