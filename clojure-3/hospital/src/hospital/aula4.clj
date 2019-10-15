(ns hospital.aula4
  (:use [clojure pprint])
  (:require [hospital.model :as h.model]
            [hospital.logic :as h.logic]))

; COLOCAR o ! Para dizer que a função tem efeito colateral
(defn chega-sem-malvado! [hospital pessoa]
  (swap! hospital h.logic/chega-em-pausado-logando :espera pessoa)
  (println "após inserir" pessoa))

; LET ESCOPO LOCAL! ENCAPSULAR ATOMsL
(defn simula-um-dia-em-paralelo-com-mapv
  "Simulação utilizando mapv para forçar quase que imperativamente a execução do que era lazy"
  []
  (let [hospital (atom (h.model/novo-hospital))
        pessoas ["111" "222" "333" "444" "555" "666"]]
    (mapv #(.start (Thread. (fn [] (chega-sem-malvado! hospital %)))) pessoas)
    (.start (Thread. (fn []
                       (Thread/sleep 8000)
                       (pprint hospital))))))

;(simula-um-dia-em-paralelo-com-mapv)

(defn simula-um-dia-em-paralelo-com-mapv-refatorado
  "Simulação utilizando mapv para forçar quase que imperativamente a execução do que era lazy"
  []
  (let [hospital (atom (h.model/novo-hospital))
        pessoas ["111" "222" "333" "444" "555" "666"]
        starta-thread-de-chegada #(.start (Thread. (fn [] (chega-sem-malvado! hospital %))))]
    (mapv starta-thread-de-chegada pessoas)
    (.start (Thread. (fn []
                       (Thread/sleep 8000)
                       (pprint hospital))))))

;(simula-um-dia-em-paralelo-com-mapv-refatorado)

(defn starta-thread-de-chegada
  [hospital pessoa]
  (.start (Thread. (fn [] (chega-sem-malvado! hospital pessoa)))))

(defn simula-um-dia-em-paralelo-com-mapv-com-partial
  []
  (let [hospital (atom (h.model/novo-hospital))
        pessoas ["111" "222" "333" "444" "555" "666"]
        starta (partial starta-thread-de-chegada hospital)]
    (mapv starta pessoas)
    (.start (Thread. (fn []
                       (Thread/sleep 8000)
                       (pprint hospital))))))

;(simula-um-dia-em-paralelo-com-mapv-com-partial)

(defn simula-um-dia-em-paralelo-com-mapv-com-doseq
  []
  (let [hospital (atom (h.model/novo-hospital))
        pessoas ["111" "222" "333" "444" "555" "666"]]
    (doseq [pessoa pessoas]
      (starta-thread-de-chegada hospital pessoas))
    (.start (Thread. (fn []
                       (Thread/sleep 8000)
                       (pprint hospital))))))

; (simula-um-dia-em-paralelo-com-mapv-com-doseq)

(defn simula-um-dia-em-paralelo-com-mapv-com-dotimes
  "Realmente estou preocupado em executar n vezes"
  []
  (let [hospital (atom (h.model/novo-hospital))]
    (dotimes [pessoas 6]
      (starta-thread-de-chegada hospital pessoas))
    (.start (Thread. (fn []
                       (Thread/sleep 8000)
                       (pprint hospital))))))

(simula-um-dia-em-paralelo-com-mapv-com-dotimes)