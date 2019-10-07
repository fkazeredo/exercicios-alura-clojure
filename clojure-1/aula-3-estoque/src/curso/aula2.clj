(ns curso.aula2)

; Comentário em Clojure é o ponto-vírgula
; Voltando com else
(defn valor-descontado
  "Retorna valor com desconto de 10 % se o valor bruto for estritamente maior do que 100."
  [valor-bruto]
  (if (> valor-bruto 100)
    (let [taxa-de-desconto (/ 10 100)
          desconto (* valor-bruto taxa-de-desconto)]
      (println "Desconto de" desconto)
      (- valor-bruto desconto))
    valor-bruto))

(println
  (valor-descontado 100))


; ALT + Shift + L para rodar no REPL
; ALT + SHIFT + J, joga algo de dentro dos parênteses para fora
; ALT + SHIFT + K, Joga algo de fora dos parênteses para dentro
; Barf e slurp... ??? Structural editing, ver nos atalhos