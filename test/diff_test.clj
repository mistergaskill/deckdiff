(ns deckdiff.core-test
  (:require [expectations :refer :all]
            [deckdiff.core :refer :all]))

(expect 0 (card-count "a" {}))
(expect 1 (card-count "a" {"a" 1}))
(expect 2 (card-count "a" {"a" 2}))
(expect 0 (card-count "a" {"b" 2}))
(expect 0 (card-diff "a" {"a" 1} {"a" 1}))
(expect 1 (card-diff "a" {"a" 2} {"a" 1}))
(expect 2 (card-diff "a" {"a" 2} {}))
(expect {} (cards-intersect {} {}))
(expect {} (cards-intersect {"a" 1} {}))
(expect {"a" 1} (cards-intersect {"a" 1} {"a" 1}))
(expect {"a" 1 "b" 1} (cards-intersect {"a" 2 "b" 1} {"a" 1 "b" 1 "c" 1}))
(expect {"a" 1} (cards-left {"a" 2 "b" 1} {"a" 1 "b" 1 "c" 1}))
(expect {"c" 1} (cards-right {"a" 2 "b" 1} {"a" 1 "b" 1 "c" 1}))
(expect [{"a" 1} {"c" 1} {"a" 1 "b" 1}] (cards-diff {"a" 2 "b" 1} {"a" 1 "b" 1 "c" 1}))
