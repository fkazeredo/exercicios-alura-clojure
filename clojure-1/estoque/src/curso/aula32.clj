(ns curso.aula32)

; Ou chamar direto a comparação
(defn mais-caro-que-100?
  [valor-bruto]
  (> valor-bruto 100))

; Higher order Functions
(defn valor-descontado
  "Retorna valor com desconto de 10 % se deve aplicar desconto."
  [aplica? valor-bruto]
  (if (aplica? valor-bruto)
    (let [taxa-de-desconto (/ 10 100)
          desconto (* valor-bruto taxa-de-desconto)]
      desconto
      (- valor-bruto desconto))
    valor-bruto))

(println (valor-descontado mais-caro-que-100? 200))

; Função sem nome, anônima, lambda
(fn [valor-bruto] (> valor-bruto 100))
(println (valor-descontado (fn [valor-bruto] (> valor-bruto 100)) 200))
(println (valor-descontado (fn [v] (> v 100)) 200))
(println (valor-descontado #(> %1 100) 200))
(println (valor-descontado #(> % 100) 200))

(def mais-caro-que-100? (fn [valor-bruto] (> valor-bruto 100)))
(def mais-caro-que-100? #(> % 100))