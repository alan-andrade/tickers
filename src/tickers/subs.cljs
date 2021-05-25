(ns tickers.subs
  (:require
   [re-frame.core :refer [reg-sub]]))

(reg-sub
  ::selected-currency
  (fn [db]
    (:selected-currency db)))

