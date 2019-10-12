(ns loja-2.service3
  (:require [loja-2.db :as l.db]
            [loja-2.logic :as l.logic]))

(defn gastou-bastante?
  [info-do-usuario]
  (> (:preco-total info-do-usuario) 500))

(let [pedidos (l.db/todos-os-pedidos)
      resumo (l.logic/resumo-por-usuario pedidos)]
  (println "keep" (keep gastou-bastante? resumo))
  (println "filter" (filter gastou-bastante? resumo)))

(println "\n\n\n\n\n\n\n")
(defn gastou-bastante?
  [info-do-usuario]
  (println "gastou bastante" (:usuario-id info-do-usuario))
  (> (:preco-total info-do-usuario) 500))

(let [pedidos (l.db/todos-os-pedidos)
      resumo (l.logic/resumo-por-usuario pedidos)]
  (println "keep" (keep gastou-bastante? resumo)))


(println "vamos isolar...")

(println (range 10))
; A sequência não está sendo eager... é lazy
(println (take 2 (range 10000000000)))

; Exemplo
(let [sequencia (range 10000000000)]
  (println (take 2 sequencia))
  (println (take 2 sequencia)))


(println "vamos isolar o MAP...")

(defn filtro-1 [x]
  (println "filtro1" x)
  x)

(println (map filtro-1 (range 10)))

(defn filtro-2 [x]
  (println "filtro2" x)
  x)

(println (map filtro-2 (map filtro-1 (range 10))))

(->> (range 50)
     (map filtro-1)
     (map filtro-2)
     println)

; Forçar eager, não utilizar em sequencias infinitas
(->> (range 50)
     (mapv filtro-1)
     (mapv filtro-2)
     println)

; Com vetor, chuncked, meio lazy e meio eager
(->> [0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31 32 33 34 45 56]
     (map filtro-1)
     (map filtro-2)
     println)

; Com LinkedList é 100% lazy
(->> '(1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8)
     (map filtro-1)
     (map filtro-2)
     println)