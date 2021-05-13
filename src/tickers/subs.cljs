(ns tickers.subs
  (:require
   [re-frame.core :as re-frame]))

(re-frame/reg-sub
 ::trending-coins
 (fn [db]
   (-> (:coins (:trending-coins db)) clj->js)))
