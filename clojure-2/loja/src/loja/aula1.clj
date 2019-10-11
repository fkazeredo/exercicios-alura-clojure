(ns loja.aula1)

; (map println ["Alladin", "Bella", "Simba"])
; map na unha...

; Pega o primeiro
(println (first ["Alladin", "Bella", "Simba"]))

; Pega todos exceto o primeiro
(println (rest ["Alladin", "Bella", "Simba"]))
(println (rest []))
; Pega o próximo
(println (next ["Alladin", "Bella", "Simba"]))
(println (next []))

; Transformar vetor em uma sequência
(println (seq [1 2 3 4 5]))
(println (seq []))

(println "\n\n\n\n\n\nMEU MAPA\n\n\n\n")

; Recursão sem fim... precisa de um condicional para sair...
(defn meu-mapa
  [funcao sequencia]
  (let [primeiro (first sequencia)]
    (funcao primeiro)
    (meu-mapa funcao (rest sequencia))))

;(meu-mapa println ["Alladin", "Bella", "Simba"])

; Com um condicional... do executa mais de um bloco no statement do if
(defn meu-mapa
  [funcao sequencia]
  (let [primeiro (first sequencia)]
    (if primeiro
      (do (funcao primeiro)
      (meu-mapa funcao (rest sequencia))))))

(meu-mapa println ["Alladin", "Bella", "Simba"])

; Clojure não tem tipos... isso poderia quebrar o código acima
(meu-mapa println ["Alladin", false, "Simba"])

; Se não for nulo ele vai agora mesmo se receber um false
(defn meu-mapa
  [funcao sequencia]
  (let [primeiro (first sequencia)]
    (if (not (nil? primeiro))
      (do (funcao primeiro)
          (meu-mapa funcao (rest sequencia))))))

(meu-mapa println ["Alladin", false, "Simba"])
(meu-mapa println [])
(meu-mapa println nil)

; Causar um stackoverflow, a pilha de execução estourou a memória
;(meu-mapa println (range 100000))

; TAIL RECURSION
; recur indica para o Clojure que isso é um laço e ele otimiza a recursão para um laço em tempo de execução
; recur é um atalho para a própria função, só que marcado como um laço recursivo
(defn meu-mapa
  [funcao sequencia]
  (let [primeiro (first sequencia)]
    (if (not (nil? primeiro))
      (do (funcao primeiro)
          (recur funcao (rest sequencia))))))

; Não causa um stackoverflow, a pilha de execução fluiu
; Belo exemplo de um for imutável... sem variáveis
(meu-mapa println (range 100000))