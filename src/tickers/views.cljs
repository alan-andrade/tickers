(ns tickers.views
  (:require
   [re-frame.core :as re-frame]
   [tickers.subs :as subs]
   ))

(defn request []
  (let [coins (re-frame/subscribe [::subs/trending-coins])]
    [:div 
     [:h1 "Response is"]
     [:p @coins]
     [:input {:type "button" :value "fire" :on-click #(re-frame/dispatch [:request-trending-coins])}]]))

(defn main-panel []
  (let [name (re-frame/subscribe [::subs/name])]
    [:div
     [:h1
      "!!Hello from " @name]
     [:input {
              :type "text"
              :value @name
              :on-change #(re-frame/dispatch [:name-change (-> % .-target .-value)])}]
     (request)]))
