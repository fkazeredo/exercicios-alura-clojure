(ns loja-2.logic)
; Repara que esse arquivo trabalha apenas com lógica pura...
; não recebe o banco de dados

(defn total-do-item
  [[item-id detalhes]]
  (* (get detalhes :quantidade 0) (get detalhes :preco-unitario 0)))

(defn total-do-pedido
  [pedido]
  (reduce + (map total-do-item pedido)))

(defn total-dos-pedidos
  [pedidos]
  (->> pedidos
       (map :itens)
       (map total-do-pedido)
       (reduce +)))

(defn quantia-de-pedidos-e-gasto-total-por-usuarios
  [[usuario pedidos]]
  {:usuario-id       usuario
   :total-de-pedidos (count pedidos)
   :preco-total      (total-dos-pedidos pedidos)})

(defn resumo-por-usuario
  [pedidos]
  (->> pedidos
       (group-by :usuario)
       (map quantia-de-pedidos-e-gasto-total-por-usuarios)))