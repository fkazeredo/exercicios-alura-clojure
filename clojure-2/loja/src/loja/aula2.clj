(ns loja.aula2)

;["daniela" "guilher" "carlos" "paulo" "lucia" "ana"]

(defn conta
  [total-ate-agora elementos]
  (recur (inc total-ate-agora) (rest elementos)))

; Mais uma vez, esse código não funciona porque entra em recursão infinita
;(println (conta 0 ["daniela" "guilher" "carlos" "paulo" "lucia" "ana"]))

; Fez a condição para parar, as não a para exibir, o else
(defn conta
  [total-ate-agora elementos]
  (if (next elementos)
    (recur (inc total-ate-agora) (rest elementos))))

(println (conta 0 ["daniela" "guilher" "carlos" "paulo" "lucia" "ana"]))


(defn conta
  [total-ate-agora elementos]
  (println total-ate-agora elementos)
  (if (next elementos)
    (recur (inc total-ate-agora) (rest elementos))
    (inc total-ate-agora)))

(println (conta 0 ["daniela" "guilher" "carlos" "paulo" "lucia" "ana"]))

; Com a implementação do inc, o vetor vazio retorna 1
(println (conta 0 []))


(defn conta
  [total-ate-agora elementos]
  (println total-ate-agora elementos)
  (if (seq elementos)                                       ; Tem elementos aí dentro?
    (recur (inc total-ate-agora) (rest elementos))
    total-ate-agora))

(println (conta 0 ["daniela" "guilher" "carlos" "paulo" "lucia" "ana"]))
(println (conta 0 []))

; Sobrecarga de funções? Aridade!
(defn minha-funcao
  ([param1] (println param1))
  ([param1 param2] (println param1 param2)))

(println (minha-funcao 1))
(println (minha-funcao 2))

(defn conta
  ([elementos]
   (conta 0 elementos))
  ([total-ate-agora elementos]
   (if (seq elementos)                                      ; Tem elementos aí dentro?
     (recur (inc total-ate-agora) (rest elementos))
     total-ate-agora)))

(println (conta 0 ["daniela" "guilher" "carlos" "paulo" "lucia" "ana"]))
(println (conta 0 []))

; Loop comum
(defn conta
  [elementos]
  (println "antes do loop")
  (loop [total-ate-agora 0
         elementos-restantes elementos]
    (if (seq elementos-restantes)
      (recur (inc total-ate-agora) (next elementos-restantes))
      total-ate-agora)))

(println (conta ["daniela" "guilher" "carlos" "paulo" "lucia" "ana"]))
(println (conta []))


