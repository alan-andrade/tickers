(ns tickers.views
  (:require
   [re-frame.core :as rf]
   [tickers.subs :as subs]
   ))

(def >evt rf/dispatch)
(def <sub (comp deref rf/subscribe))

(defn currency-selector
  []
  (let [selected-currency (<sub [::subs/selected-currency])
        change-currency #(>evt [:change-currency (-> % .-target .-value)])]
    [:div.filters
     [:label {:for "currency"} "Currency:"]
     [:select {:id "currency" :value selected-currency :on-change change-currency}
      [:option {:value "usd"} "USD"]
      [:option {:value "mxn"} "MXN"]]]))

(defn markets
  []
  (let [selected-currency (<sub [::subs/selected-currency])]
    [:p selected-currency]))

(defn main-panel []
  [:div
    [:h1 "Crypto Markets"]
    [:div.dashboard
     (currency-selector)
     (markets)]])
