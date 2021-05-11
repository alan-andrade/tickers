(ns tickers.subs
  (:require
   [re-frame.core :as re-frame]))

(re-frame/reg-sub
 ::name
 (fn [db]
   (:name db)))

(re-frame/reg-sub
 ::trending-coins
 (fn [db]
   (-> (:trending-coins db) clj->js js/JSON.stringify)))
