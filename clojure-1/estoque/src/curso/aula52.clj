(ns curso.aula52)

(def pedido {:mochila  {:quantidade 2, :preco 80}
             :camiseta {:quantidade 3, :preco 40}})

(println pedido)

(def pedido (assoc pedido :chaveiro {:quantidade 2, :preco 10}))

(println pedido)
(println (pedido :mochila))                                 ; mapa como função, não é null safe
(println (get pedido :mochila))                             ; com o get
(println (get pedido :cadeira {}))                          ; com o get e com valor default

; Maneira mais comum de chamar um mapa é utilizando a keyword como função
(println (:mochila pedido))

; Nil
(println (:cadeira pedido))


; Valor padrão
(println (:cadeira pedido {}))

; Buscar a quantidade
(println (:quantidade (:mochila pedido)))

; Atualizar dentro do pedido, função update-in
(println (update-in pedido [:mochila :quantidade] inc))

; THREADING FIRST, bem parecido com a OOP, mesmo não sendo funcional
; É muito usado porque as pessoas já associaram essa síntaxe em outras linguagens
(println (-> pedido
             :mochila
             :quantidade))

; Comparando com a funcional... PREFIX,
(println (:quantidade (:mochila pedido)))

; A nível de curiosidade, não é uma boa prática, mas dá pra fazer isso
(-> pedido
    :mochila
    :quantidade
    println)