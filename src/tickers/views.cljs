(ns tickers.views
  (:require
   [re-frame.core :as re-frame]
   [tickers.subs :as subs]
   ))

(defn trending-coins []
  (let [coins (re-frame/subscribe [::subs/trending-coins])]
    [:div
     [:h1 "Trending Coins"]
     [:input {:type "button" :value "Refresh" :on-click #(re-frame/dispatch [:request-trending-coins])}]
     (when-not (empty? @coins)
       [:table
        [:thead
         [:tr
         [:td "Icon"]
         [:td "Coin"]
         [:td "Price"]
         ]]
        [:tbody
         (for [coin @coins]
           [:tr {:key (-> coin .-item .-id)}
            [:td [:img {:src  (-> coin .-item .-small)}]]
            [:td (-> coin .-item .-name)]
            [:td (-> coin .-item .-id)]
            ]
           )
         ]])
     ]))

(defn main-panel []
  trending-coins)
