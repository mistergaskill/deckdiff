(ns deckdiff.core)

(defn card-count [card deck]
  (if (contains? deck card)
    (get deck card)
    0))

(defn card-min [card deck1 deck2]
  (min (card-count card deck1) (card-count card deck2)))

(defn card-diff [card deck1 deck2]
  (- (card-count card deck1) (card-count card deck2)))


(defn cards-intersect [deck1 deck2]
  (reduce merge {}
    (map (fn [card] {card (card-min card deck1 deck2)})
      (filter
        (fn [card] (contains? deck2 card))
        (keys deck1)))))

(defn cards-left [deck1 deck2]
  (reduce merge {}
  (filter (fn [pair] (> (get pair (first (keys pair))) 0))
  (map
    (fn [card] {card (card-diff card deck1 deck2)})
    (keys deck1)))))

(defn cards-right [deck1 deck2]
  (cards-left deck2 deck1))

(defn cards-diff [deck1 deck2]
  [(cards-left deck1 deck2)
  (cards-right deck1 deck2)
  (cards-intersect deck1 deck2)])

(defn load-deck [file]
  (clojure.data.json/read-str (slurp (str "./decks/" file ".json"))))
