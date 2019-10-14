(ns hospital.colecoes
  (:use [clojure pprint]))


; O vetor é feito para adicionar e retirar em memória sempre do final
(defn testa-vetor
  []
  (let [espera [111 222]]
    (println espera)
    (println (conj espera 333))
    (println (conj espera 444))
    (println (pop espera))))

; A lista é feita para adicionar e retirar em memória sempre no começo
(defn testa-lista
  []
  (let [espera '(111 222)]
    (println espera)
    (println (conj espera 333))
    (println (conj espera 444))
    (println (pop espera))))

; Conjunto (SET) é desordenado, não rola pop, mas pelo menos ele não repete elementos
(defn testa-conjunto
  []
  (let [espera #{111 222}]
    (println espera)
    (println (conj espera 111))
    (println (conj espera 333))
    (println (conj espera 444))
    ; (println (pop espera))
    ))

(defn testa-fila
  []
  (let [espera (conj clojure.lang.PersistentQueue/EMPTY 111 222)]
    (println "Fila")
    (println (seq espera))
    (println (seq (conj espera 333)))
    (println (seq (pop espera)))
    (println (peek espera))
    (pprint espera)))

(testa-fila)