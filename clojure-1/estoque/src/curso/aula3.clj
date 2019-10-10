(ns curso.aula3)

(defn valor-descontado
  "Retorna valor com desconto de 10 % se o valor bruto for estritamente maior do que 100."
  [valor-bruto]
  (if (> valor-bruto 100)
    (let [taxa-de-desconto (/ 10 100)
          desconto (* valor-bruto taxa-de-desconto)]
      desconto
      (- valor-bruto desconto))
    valor-bruto))

(println (valor-descontado 100))
(println (valor-descontado 200))

; PREDICATE: Normal essa interrogação aí para funções booleanas
(defn aplica-desconto?
  [valor-bruto]
  (if (> valor-bruto 100)
    true
    false))

; Redefinir
(defn valor-descontado
  "Retorna valor com desconto de 10 % se o valor bruto for estritamente maior do que 100."
  [valor-bruto]
  (if (aplica-desconto? valor-bruto)
    (let [taxa-de-desconto (/ 10 100)
          desconto (* valor-bruto taxa-de-desconto)]
      desconto
      (- valor-bruto desconto))
    valor-bruto))

; Tirar o false, true e Nill. Nulo é considerado falso...
(defn aplica-desconto?
  [valor-bruto]
  (println "Chamando a versão redefinida")
  (if (> valor-bruto 100)
    true))

(println (valor-descontado 100))
(println (valor-descontado 200))

; Utilizar o When quando só tem verdadeiro
(defn aplica-desconto?
  [valor-bruto]
  (println "Chamando a versão redefinida 2")
  (when (> valor-bruto 100)
    true))

(println (valor-descontado 100))
(println (valor-descontado 200))


; Ou chamar direto a comparação
(defn aplica-desconto?
  [valor-bruto]
  (println "Chamando a versão redefinida 3")
  (> valor-bruto 100))

(println (valor-descontado 100))
(println (valor-descontado 200))
