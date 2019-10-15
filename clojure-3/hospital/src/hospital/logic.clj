(ns hospital.logic)

(defn cabe-na-fila?
  [hospital departamento]
  (-> hospital
      (get,,, departamento)
      count
      (<,,, 5)))

(defn chega-em
  [hospital departamento pessoa]
  (if (cabe-na-fila? hospital departamento)
    (update hospital departamento conj pessoa)
    (throw (ex-info "fila já está cheia" {:tentando-adicionar pessoa}))))

(defn chega-em-pausado
  [hospital departamento pessoa]
  (if (cabe-na-fila? hospital departamento)
    (do (Thread/sleep (* (rand) 2000))
        (update hospital departamento conj pessoa))
    (throw (ex-info "fila já está cheia" {:tentando-adicionar pessoa}))))

(defn chega-em-pausado-logando
  [hospital departamento pessoa]
  (println "tentando adicionar a pessoa" pessoa)
  (if (cabe-na-fila? hospital departamento)
    (do (Thread/sleep (* (rand) 2000))
        (println "dando o update" pessoa)
        (update hospital departamento conj pessoa))
    (throw (ex-info "fila já está cheia" {:tentando-adicionar pessoa}))))

(defn atende
  [hospital departamento]
  (update hospital departamento pop))

(defn atende-completo
  [hospital departamento]
  {:paciente (update hospital departamento peek)
   :hospital     (update hospital departamento pop)})

(defn atende-completo-que-chama-ambos
  [hospital departamento]
  (let [fila (get hospital departamento)
        peek-pop (juxt peek pop)
        [pessoa fila-atualizada] (peek-pop fila)
        hospital-atualizado (update hospital assoc departamento fila-atualizada)]
    {:paciente pessoa
     :hospital     hospital-atualizado}))

(defn proxima
  "Retorna o próximo paciente da fila"
  [hospital departamento]
  (-> hospital
      departamento
      peek))

(defn transfere
  "Transfere o proximo paciente da fila para outra fila"
  [hospital de para]
  (let [pessoa (proxima hospital de)]
    (-> hospital
        (atende de)
        (chega-em para pessoa))))