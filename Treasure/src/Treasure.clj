(println "GET THAT TREASURE\n")
(defn readMapFile []
  (def mapContent (slurp "map.txt"))
  (println "CHALLENGE MAP")
  (println mapContent)
  (def readMap (clojure.string/split-lines mapContent))
  (def rows (count readMap))
  (def columns (count (str (get-in readMap [0]))))
  (def mapData (-> (mapv vec (clojure.string/split-lines (slurp "map.txt")))) )
  )

(defn isValid [x y]
  (if (and (and (< x rows) (< y columns)) (and (>= x 0) (>= y 0)))
    "true"
    "false")
  )

(defn findPath [x y readMap]
  (cond
    (= (str (get-in readMap [x y])) "@") (do
                                           (println "\nYOU FOUND THE TREASURE! WOO HOO! :)")
                                           (def updatedMap (clojure.string/join "\n" updatedMap))
                                           (def updatedMap (clojure.string/replace updatedMap "\\" ""))
                                           (def updatedMap (clojure.string/replace updatedMap " " ""))
                                           (def updatedMap (clojure.string/replace updatedMap "[" ""))
                                           (def updatedMap (clojure.string/replace updatedMap "]" ""))
                                           (println updatedMap)
                                           "true"
                                           )
    (and (= (isValid x y) "true") (= (str (get-in readMap[x y])) "-")) (do
                                                                                 (def updatedMap (-> readMap (assoc-in [x y] \+)))
                                                                                 (cond
                                                                                   (= (findPath (inc x) y updatedMap) "true") "true"
                                                                                   (= (findPath x (inc y)  updatedMap) "true") "true"
                                                                                   (= (findPath (dec x) y updatedMap) "true") "true"
                                                                                   (= (findPath x (dec y) updatedMap) "true") "true"
                                                                                   :else (do
                                                                                           (def updatedMap (-> updatedMap (assoc-in [x y] \!)))
                                                                                           "false"
                                                                                           )
                                                                                   )
                                                                                 )

    :else (do
            "false"
            )

    )

  ;(cond
  ;  (= (str (get-in readMap [x y])) "@") (do
  ;                                         (println readMap)
  ;                                         "true"
  ;                                         )
  ;  :else (do
  ;          (def updatedMap (-> readMap (assoc-in [x y] \+)))
  ;          (def nx [(inc x) x (dec x) x ])
  ;          (def ny [y (inc y) y (dec y)])
  ;          (println nx)
  ;          (println ny)
  ;          (loop [i 0]
  ;            (when (< i 4)
  ;              (println "INSIDE LOOP " i)
  ;              (println "LOCATION" (get-in nx [i]) (get-in ny [i]) (get-in updatedMap [(get-in nx [i]) (get-in ny [i])]))
  ;              (cond
  ;                (= (isValid (get-in nx [i]) (get-in ny [i])) "true") (do
  ;                                                                       (println "VALID")
  ;                                                                       (cond
  ;                                                                         (= (str (get-in updatedMap [(get-in nx [i]) (get-in ny [i])])) "-") (do
  ;                                                                                                                                               (println "INSIDE")
  ;                                                                                                                                               (cond
  ;                                                                                                                                                 (= (findPath (get-in nx [i]) (get-in ny [i]) updatedMap) "true") "true"
  ;                                                                                                                                                 )
  ;
  ;                                                                                                                                            )
  ;                                                                         (= (str (get-in updatedMap [(get-in nx [i]) (get-in ny [i])])) "@") (do
  ;                                                                                                                                               (println "FOUND")
  ;                                                                                                                                            (cond
  ;                                                                                                                                              (= (findPath (get-in nx [i]) (get-in ny [i]) updatedMap) "true") "true"
  ;                                                                                                                                              )
  ;                                                                                                                                            )
  ;                                                                         :else
  ;                                                                         (println "ELSE")
  ;                                                                         )
  ;                                                                       )
  ;                )
  ;              (recur (+ i 1))))
  ;
  ;          ;(def updatedMap (-> updatedMap (assoc-in [x y] \!)))
  ;          "false"
  ;          )
  ;  )
  )
(readMapFile)
(cond
  (= (findPath 0 0 mapData) "true") "PATH FOUND"
  :else (do
          (println "\n\nUH OH.. YOU COULDNT FIND THE TREASURE :(")
          (def updatedMap (clojure.string/join "\n" updatedMap))
          (def updatedMap (clojure.string/replace updatedMap "\\" ""))
          (def updatedMap (clojure.string/replace updatedMap " " ""))
          (def updatedMap (clojure.string/replace updatedMap "[" ""))
          (def updatedMap (clojure.string/replace updatedMap "]" ""))
          (println updatedMap)
          )
  )


