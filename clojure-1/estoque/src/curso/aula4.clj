(ns curso.aula4)

(def precos [30 700 1000])

(println (precos 0))
(println (get precos 0))

; (println (precos 17))
; Get é focado para tratar vetores e trata o IndexOutOfBoundsException
(println (get precos 17))

(println "Valor padrão de nil" (get precos 17))
(println "Valor padrão 0" (get precos 17 0))
(println "Valor padrão 0, mas existe" (get precos 2 0))

(println (conj precos 5))
;(println precos)

(println (+ 5 1))
; Incrementa 1
(println (inc 5))

(println (update precos 0 inc))
(println (update precos 0 dec))
(println precos)

(defn soma-1
  [valor]
  (println "Estou somando um em " valor)
  (+ valor 1))

(println (update precos 0 soma-1))

; Código da aula anterior
(defn aplica-desconto?
  [valor-bruto]
  (> valor-bruto 100))

(defn valor-descontado
  [valor-bruto]
  (if (aplica-desconto? valor-bruto)
    (let [taxa-de-desconto (/ 10 100)
          desconto (* valor-bruto taxa-de-desconto)]
      (- valor-bruto desconto))
    valor-bruto))

; Aplicar valor descontado em todos os preços... como?

; Map - Passa por todos os elementos e aplica uma função
(println (map valor-descontado precos))

; Filter
(println (range 10)

         println (filter even? (range 10)))

(println "filtrado" (filter aplica-desconto? precos))
(println "original" precos)

(println "map após o filter"
         (map valor-descontado
              (filter aplica-desconto? precos)))

; Reduce
; Pegar uma sequência de elements e reduzir para algo, um agrupamento
(println (reduce + precos))

; Reduce na mão...
(defn minha-soma
  [valor1, valor2]
  (println "Somando" valor1 valor2)
  (+ valor1, valor2))

(println (reduce minha-soma (range 10)))

; Se o vetor só tiver um elemento, ele nem chama a função de somar
(println (reduce minha-soma [15]))

; Se quiser passar um valor inicial, há um outro parâmetro no reduce
(println (reduce minha-soma 0 [15]))

; Se passar um vetor vazio, tem que passar o valor inicial
(println (reduce minha-soma 0 []))

; Esse código dá erro porque não tem o valor inicial
; (println (reduce minha-soma []))
