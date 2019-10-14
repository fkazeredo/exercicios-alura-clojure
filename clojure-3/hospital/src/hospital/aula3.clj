(ns hospital.aula3
  (:use [clojure pprint])
  (:require [hospital.model :as h.model]
            [hospital.logic :as h.logic]))

; simbolo que qualquer thread que acessar esse namespace vai ter acesso a ele com o valor padrão "guilherme"
(def nome "guilherme")

; Redefinir o símbolo, o bind
(def nome 324567)

(let [nome "guilherme"]
  (println nome)
  ; Não esttou refazendo o binding do símbolo local
  ; criando um novo símbolo local a este bloco e escondendo o anterior
  ; SHADOWING
  (let [nome "daniela"]
    (println nome))
  (println nome))

; repara no escopo LOCAL do ATOM! ENCAPSULAMENTO!
(defn testa-atom []
  (let [hospital-silveira (atom {:espera h.model/fila-vazia})]
    (println hospital-silveira)
    (pprint hospital-silveira)
    (pprint (deref hospital-silveira))
    (pprint @hospital-silveira)

    ; Não é assim que altera o contéudo dentro de um atom
    (pprint (assoc @hospital-silveira :laboratorio1 h.model/fila-vazia))
    (pprint @hospital-silveira)

    ; Essa é uma das maneiras de alterar conteúdo dentro de um atom
    (swap! hospital-silveira assoc :laboratorio1 h.model/fila-vazia)
    (pprint @hospital-silveira)

    (swap! hospital-silveira assoc :laboratorio2 h.model/fila-vazia)
    (pprint @hospital-silveira)

    (swap! hospital-silveira update :espera conj "111")
    (pprint @hospital-silveira)

    ))

;(testa-atom)

; COLOCAR o ! Para dizer que a função tem efeito colateral
(defn chega-em-malvado! [hospital pessoa]
  (swap! hospital h.logic/chega-em-pausado-logando :espera pessoa)
  (println "após inserir" pessoa))

; LET ESCOPO LOCAL! ENCAPSULAR ATOMs
(defn simula-um-dia-em-paralelo []
  (let [hospital (atom (h.model/novo-hospital))]
    (.start (Thread. (fn [] (chega-em-malvado! hospital "111"))))
    (.start (Thread. (fn [] (chega-em-malvado! hospital "222"))))
    (.start (Thread. (fn [] (chega-em-malvado! hospital "333"))))
    (.start (Thread. (fn [] (chega-em-malvado! hospital "444"))))
    (.start (Thread. (fn [] (chega-em-malvado! hospital "555"))))
    (.start (Thread. (fn [] (chega-em-malvado! hospital "666"))))
    (.start (Thread. (fn []
                       (Thread/sleep 8000)
                       (pprint hospital))))))

(simula-um-dia-em-paralelo)