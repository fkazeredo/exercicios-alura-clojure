(ns curso.aula62)

(def pedido {:mochila  {:quantidade 2, :preco 80}
             :camiseta {:quantidade 3, :preco 40}})

(defn preco-dos-produtos
  [produto]
  (* (:quantidade produto) (:preco produto)))

(defn total-do-pedido
  [pedido]
  (->> pedido
       vals
       (map preco-dos-produtos,,,)
       (reduce +,,,)))

(println (total-do-pedido pedido))

(def pedido {:mochila  {:quantidade 2, :preco 80}
             :camiseta {:quantidade 3, :preco 40}
             :chaveiro {:quantidade 1}})

; <= é menor ou igual mesmo
(defn gratuito?
  [item]
  (<= (get item :preco 0) 0))

(println (filter gratuito? (vals pedido)))                  ; repara no vals

; Com destruct
(defn gratuito?
  [[_ item]]
  (<= (get item :preco 0) 0))

(println (filter gratuito? pedido))

(defn gratuito?
  [item]
  (<= (get item :preco 0) 0))

; Com função inline
(println (filter (fn [[chave item]] (gratuito? item)) pedido))

; Com função lambda
(println (filter #(gratuito? (second %)) pedido))

; not gratuito
(defn pago?
  [item]
  (not (gratuito? item)))

(println (filter #(pago? (second %)) pedido))
(println (pago? {:preco 50}))
(println (pago? {:preco 0}))

; Também funciona
(println ((comp not gratuito?) {:preco 50}))

; Definindo símbolo pago
(def pago? (comp not gratuito?))

(println (pago? {:preco 0}))