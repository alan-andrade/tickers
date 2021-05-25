(ns tickers.events
  (:require
   [re-frame.core :refer [reg-event-db reg-event-fx console]]
   [tickers.db :as db]
   [ajax.core :as ajax]
   ))

;; Initiazlie databse
(reg-event-db
 ::initialize-db
 (fn [_ _]
   db/default-db))

(reg-event-fx
  :change-currency
  (fn [{:keys [db]} [_ result]]
    {:db (assoc db :selected-currency result)
     :dispatch [:get-markets]}))

;; (reg-event-db
;;   :handle-http-error
;;   (fn [db [_ result]]
;;     (assoc db :http-errors result)))
;; 
(reg-event-fx
  :get-markets
  (fn [{:keys [db]} _]
    (let [currency (:selected-currency db)
          params (str "?vs_currency=", currency)]
    {:http-xhrio {:method          :get
                  :uri             (str "https://api.coingecko.com/api/v3/coins/markets", params)
                  :timeout         8000
                  :response-format (ajax/json-response-format {:keywords? true})
                  :on-success      [:receive-trending-coins]
                  :on-failure      [:handle-http-error]}})))

(defn query-mappings
  []
  {:selected-currency "vs_currency"})

(defn form-query-string
  [db]
  (let [values (select-keys db [:selected-currency])]
    (js/console.debug values)))
