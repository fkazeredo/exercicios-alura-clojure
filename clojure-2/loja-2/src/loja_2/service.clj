(ns loja-2.service
  (:require [loja-2.db :as l.db]))

(println (l.db/todos-os-pedidos))

(println (group-by :usuario (l.db/todos-os-pedidos)))

(defn minha-funcao-de-agrupamento
  [elemento]
  (println "elemento" elemento)
  (:usuario elemento))

(println (group-by minha-funcao-de-agrupamento (l.db/todos-os-pedidos)))

; Quantos pedidos tem cada usuário?
(println (map count (vals (group-by :usuario (l.db/todos-os-pedidos)))))

; Com Thread Last (LEGIBILIDADE)

(defn conta-o-total-por-usuario
  [[usuario pedidos]]
  (count pedidos))

(->> (l.db/todos-os-pedidos)
     (group-by :usuario)
     (map conta-o-total-por-usuario)
     println)

; Devolvendo um vetor, feio...
(defn conta-o-total-por-usuario
  [[usuario pedidos]]
  [usuario (count pedidos)])

(->> (l.db/todos-os-pedidos)
     (group-by :usuario)
     (map conta-o-total-por-usuario)
     println)

; Devolvendo um mapa, bonito, estrutura de dados na veia
(defn conta-o-total-por-usuario
  [[usuario pedidos]]
  {:usuario-id usuario
   :total-de-pedidos (count pedidos)})

(->> (l.db/todos-os-pedidos)
     (group-by :usuario)
     (map conta-o-total-por-usuario)
     println)



(println "\n\n\n\n\n\nPEDIDOS")

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

; Devolvendo um mapa, bonito, estrutura de dados na veia
(defn quantia-de-pedidos-e-gasto-total-por-usuarios
  [[usuario pedidos]]
  {:usuario-id usuario
   :total-de-pedidos (count pedidos)
   :preco-total (total-dos-pedidos pedidos)})

(->> (l.db/todos-os-pedidos)
     (group-by :usuario)
     (map quantia-de-pedidos-e-gasto-total-por-usuarios)
     println)