(ns tickers.views
  (:require
   [re-frame.core :as re-frame]
   [tickers.subs :as subs]
   [reagent.core :as r]
   ))

(defn as-table [coins]
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
  )

(defn as-list [coins]
  [:ol
   (for  [coin @coins]
     [:li {:key (-> coin .-item .-id)}
      [:img {:src  (-> coin .-item .-small)}]
      (-> coin .-item .-name)
      (-> coin .-item .-id)])])

(defn table-or-list [data]
  (let [display (r/atom "table")]
    (fn [data]
      [:div
       [:select {:name "display-type" :on-change #(reset! display (-> % .-target .-value))}
        [:option {:value "table"} "Table"]
        [:option {:value "list"} "List"]
        ]
       [:div
        (cond
          (= @display "table") (as-table data)
          (= @display "list") (as-list data))
        ]
       ]
      )
    )
  )

(defn trending-coins []
  (let [coins (re-frame/subscribe [::subs/trending-coins])]
    [:div
     [:h1 "Trending Coins"]
     [:input {:type "button" :value "Refresh" :on-click #(re-frame/dispatch [:request-trending-coins])}]
     [table-or-list coins]
     ]))

(defn main-panel []
  trending-coins)
