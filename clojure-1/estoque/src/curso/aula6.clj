(ns curso.aula6)

(def pedido {:mochila  {:quantidade 2, :preco 80}
             :camiseta {:quantidade 3, :preco 40}})

; A função class é boa pra explorar o tipo de alguma função do clojure ou de terceiros
(defn imprime-e-15
  [valor]
  (println "valor" (class valor) valor)
  15)

(println (map imprime-e-15 pedido))

; Não compila, só tem 1 parâmetro, não dá pra desestruturar
;(defn imprime-e-15
;  [chave valor]
;  (println chave "e" valor)
;  15)
;
;(println (map imprime-e-15 pedido))

; Desestruturar vetor
(defn imprime-e-15
  [[chave valor]]
  (println chave "e" valor)
  15)

(println (map imprime-e-15 pedido))

(defn preco-dos-produtos
  [[chave valor]]
  (* (:quantidade valor) (:preco valor)))

(println (map preco-dos-produtos pedido))
(println (reduce + (map preco-dos-produtos pedido)))

; O underline significa que você vai desconstruir, mas não vai usar o parâmetro
(defn preco-dos-produtos
  [[_ valor]]
  (* (:quantidade valor) (:preco valor)))

(println (map preco-dos-produtos pedido))
(println (reduce + (map preco-dos-produtos pedido)))

(defn total-do-pedido
  [pedido]
  (reduce + (map preco-dos-produtos pedido)))

(println (total-do-pedido pedido))

; ->> é igual a THREAD LAST
(defn total-do-pedido
  [pedido]
  (->> pedido
      (map preco-dos-produtos ,,,)
      (reduce + ,,,)))

(println (total-do-pedido pedido))

(defn preco-dos-produtos
  [produto]
  (* (:quantidade produto) (:preco produto)))

(defn total-do-pedido
  [pedido]
  (->> pedido vals
       (map preco-dos-produtos ,,,)
       (reduce + ,,,)))

(println (total-do-pedido pedido))