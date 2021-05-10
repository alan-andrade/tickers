(ns tickers.events
  (:require
   [re-frame.core :as re-frame]
   [tickers.db :as db]
   ))

(re-frame/reg-event-db
 ::initialize-db
 (fn [_ _]
   db/default-db))

(re-frame/reg-event-db
  :name-change
  (fn [db [_ new-name]]
    (assoc db :name new-name)))
