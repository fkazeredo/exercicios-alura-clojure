(ns loja-2.service2
  (:require [loja-2.db :as l.db]
            [loja-2.logic :as l.logic]))

(println (l.db/todos-os-pedidos))

; Chamando do arquivo de lógica
(let [pedidos (l.db/todos-os-pedidos)
      resumo (l.logic/resumo-por-usuario pedidos)]
  (println "resumo" resumo)
  (println "ordenado" (sort-by :preco-total resumo))
  (println "ordenado do maior pro menor" (reverse (sort-by :preco-total resumo)))
  (println "ordenado por id" (sort-by :usuario-id resumo))
  (println (get-in pedidos [0 :itens :mochila :quantidade])))

(defn resumo-por-usuario-ordenado [pedidos]
  (->> pedidos
       l.logic/resumo-por-usuario
       (sort-by :preco-total)
       reverse))

(let [pedidos (l.db/todos-os-pedidos)
      resumo (resumo-por-usuario-ordenado pedidos)]
  (println "resumo" resumo)
  (println "primeiro" (first resumo))
  (println "segundo" (second resumo))
  (println "resto" (rest resumo))
  (println "total" (count resumo))
  (println "classe" (class resumo))
  (println "enésimo elemento 2" (nth resumo 1))
  (println "enésimo elemento 2 com get" (get resumo 1))     ;Vem nulo porque não é um vetor
  (println "pegar" (take 2 resumo)))

(defn top-2 [resumo]
  (take 2 resumo))

(let [pedidos (l.db/todos-os-pedidos)
      resumo (resumo-por-usuario-ordenado pedidos)]
  (println "resumo" resumo)
  (println "top 2" (top-2 resumo)))

(let [pedidos (l.db/todos-os-pedidos)
      resumo (resumo-por-usuario-ordenado pedidos)]
  (println "> 500" (filter #(> (:preco-total %) 500) resumo))
  (println "> 500 empty not" (not (empty? (filter #(> (:preco-total %) 500) resumo))))
  (println "alguém > 500?" (some #(> (:preco-total %) 500) resumo)))