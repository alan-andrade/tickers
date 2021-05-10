(ns tickers.views
  (:require
   [re-frame.core :as re-frame]
   [tickers.subs :as subs]
   ))

(defn main-panel []
  (let [name (re-frame/subscribe [::subs/name])]
    [:div
     [:h1
      "!!Hello from " @name]
     [:input {
              :type "text"
              :value @name
              :on-change #(re-frame/dispatch [:name-change (-> % .-target .-value)])}]]))
