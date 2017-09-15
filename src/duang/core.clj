(ns duang.core
  (:require [selmer.parser :refer :all]
            [clojure.string :as str]
            [inflections.core :refer :all])
  (:gen-class))

(set-resource-path! (str (System/getProperty "user.dir") "/templates/"))

(defn filename
  [file singular-coinname plural-coinname coinsym]
  (str/replace
    (str/replace
      (str/replace
        file
        "plural_coinname" plural-coinname)
      "singular_coinname" singular-coinname)
    "coinsym" coinsym))

(defn fullpath
  [file singular-coinname plural-coinname coinsym]
  (str
    (System/getProperty "user.dir")
    "/out/"
    (filename
      (str/replace file ":" "/")
      singular-coinname
      plural-coinname
      coinsym)))

(defn genfile
  [file coinname coinsym]
  (let
    [singular-coinname coinname
     plural-coinname (plural singular-coinname)
     fullpath (fullpath file singular-coinname plural-coinname coinsym)]
    (clojure.java.io/make-parents fullpath)
    (spit fullpath
      (render-file file
        { :plural-coinname plural-coinname
          :singular-coinname singular-coinname
          :coinsym coinsym}
        { :tag-open \[
          :tag-close \]}))))

; 除了下面的，还有国际化文件要添加
; client.zh-CN.yml: zh-CN.funds.deposit_atm.title
; client.zh-CN.yml: zh-CN.funds.withdraw_atm.title
; client.zh-CN.yml: zh-CN.markets.market_list.atm
; server.zh-CN.yml: zh-CN.market.currency.atm
; config/currencies.yml
; config/deposit_channels.yml
; config/withdraw_channels.yml
; 图标
; Handler
(defn -main
  [& args]
  (let [coinname "litecoin"
        coinsym  "ltc"]
    ; new
    (genfile "app:controllers:private:withdraws:plural_coinname_controller.rb" coinname coinsym)
    (genfile "app:controllers:private:deposits:plural_coinname_controller.rb" coinname coinsym)
    (genfile "app:models:deposits:singular_coinname.rb" coinname coinsym)
    (genfile "app:models:withdraws:singular_coinname.rb" coinname coinsym)
    (genfile "app:views:private:assets:_coinsym_assets.html.slim" coinname coinsym)
    (genfile "app:views:private:withdraws:plural_coinname:edit.html.slim" coinname coinsym)
    (genfile "app:views:private:withdraws:plural_coinname:new.html.slim" coinname coinsym)
    (genfile "public:templates:funds:deposit_coinsym.html" coinname coinsym)
    (genfile "public:templates:funds:withdraw_coinsym.html" coinname coinsym)
    ; new admin
    (genfile "app:controllers:admin:deposits:plural_coinname_controller.rb" coinname coinsym)
    (genfile "app:controllers:admin:withdraws:plural_coinname_controller.rb" coinname coinsym)
    (genfile "app:views:admin:deposits:plural_coinname:index.html.slim" coinname coinsym)
    (genfile "app:views:admin:withdraws:plural_coinname:_table.html.slim" coinname coinsym)
    (genfile "app:views:admin:withdraws:plural_coinname:index.html.slim" coinname coinsym)
    (genfile "app:views:admin:withdraws:plural_coinname:show.html.slim" coinname coinsym)))
    ; modify
    ; (genfile "app:controllers:private:assets_controller.rb~" coinname coinsym)
    ; (genfile "app:views:private:assets:_liability_tabs.html.slim~" coinname coinsym)
    ; (genfile "app:views:private:assets:index.html.slim~" coinname coinsym)
    ; (genfile "public:templates:funds:deposit.html~" coinname coinsym)
    ; (genfile "public:templates:funds:withdraw.html~" coinname coinsym)))
