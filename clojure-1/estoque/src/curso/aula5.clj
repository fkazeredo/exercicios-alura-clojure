(ns curso.aula5)

(def estoque {"mochila" 10 "Camiseta" 5})

(println estoque)

; Uma vírgula entre os elementos ajuda a leitura
(def estoque {"mochila" 10,
              "Camiseta" 5})

; Quantos elementos tem o mapa?
(println "Temos" (count estoque) "elementos")

; Quais são as chaves?
(println "As chaves são:" (keys estoque))

; E os valores?
(println "Os valores são:" (vals estoque))

; ATENÇÃO: Não existe garantia por padrão na ordem em que os elementos vem no mapa

; Não é uma boa prática utilizar Strings como chaves de mapas... utilizar keyword

(def estoque {:mochila 10,
              :camiseta 5})

(println "As chaves são:" (keys estoque))

; Incluir novo valor (lembrando que NÃO REDEFINE)
(println (assoc estoque :cadeira 3))

; Sobrescrever valor (lembrando que NÃO REDEFINE)
(println (assoc estoque :mochila 1))

; Também podemos utilizar o update
(println (update estoque :mochila inc))

; Vendo por debaixo dos panos
(defn tira-um
  [valor]
  (println "Tirando um do valor")
  (- valor 1))

(println (update estoque :mochila tira-um))

; Com lambda
(println (update estoque :mochila #(- % 3)))

; Remover elementos
(println (dissoc estoque :mochila))