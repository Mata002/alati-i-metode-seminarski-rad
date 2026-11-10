(ns seminarski-rad.core-test
  (:require [midje.sweet :refer :all]
            [seminarski-rad.core :as core :refer :all]))

(facts "about valid-book?"
  (fact "a book with score of 0.6 is valid because the score thresholds are 0.5"
    (core/valid-book? {:title "Book 1"}) => true))

(facts "about popular-evaluation"
  (fact "popular score is between 0 and 1"
    (<= 0 (core/popular-evaluation {:title "Book 1"}) 1) => true))

(facts "about academic-evaluation"
  (fact "academic score is between 0 and 1"
    (<= 0 (core/academic-evaluation {:title "Book 1"}) 1) => true))

(facts "about input"
  (fact "returns what the user typed"
    (with-in-str "french revolution" (core/input)) => "french revolution"))

(facts "about input-limit"
  (fact "returns the number the user typed"
    (with-in-str "5" (core/input-limit)) => 5)
  (fact "ignores spaces around the number"
    (with-in-str " 7 " (core/input-limit)) => 7)
  (fact "returns the default if the user types text"
    (with-in-str "abc" (core/input-limit)) => core/default-result-limit)
  (fact "returns the default if the user types nothing"
    (with-in-str "" (core/input-limit)) => core/default-result-limit))

(facts "about output"
  (fact "prints only the first n result-limit books"
    (with-out-str (core/output [{:title "A"} {:title "B"} {:title "C"}] 2))
    => (str "({:title A} {:title B})" (System/lineSeparator)))
  (fact "a result-limit bigger than the list of books prints all the books"
    (with-out-str (core/output [{:title "A"} {:title "B"} {:title "C"}] 10))
    => (str "({:title A} {:title B} {:title C})" (System/lineSeparator))))