(ns seminarski-rad.core
  (:gen-class))

;; book validity threshold
(def popular-threshold 0.5)
(def academic-threshold 0.5)

;; all books list
(def books-list
  [{:title "Knjiga 1"}
   {:title "Knjiga 2"}
   {:title "Knjiga 3"}])

;; input: none; output: search string from the user
(defn input
  []
  "francuska revolucija")

;; input: search string, books list. output: filtered books list
(defn search
  [search-terms books]
  books)

;; input: one book; output: popular score for that book
(defn popular-evaluation
  [book]
  0.6)

;; input: one book; output: academic score for that book
(defn academic-evaluation
  [book]
  0.6)

;; input: one book; output: true/false (true if the book is vlaid)
(defn valid-book?
  [book]
  (and (>= (popular-evaluation book) popular-threshold)
       (>= (academic-evaluation book) academic-threshold)))

;; input: filtered books list; output: list of valid books
(defn main-evaluation
  [filtered-books]
  (filter valid-book? filtered-books))

;; input: final-list / list of valid books; output: ranked list;
(defn ranking
  [final-valid-books]
  final-valid-books)

;; input: ranked-list; output: showing results to the user
(defn output
  [ranked-books]
  (println ranked-books))

;; main function
(defn -main
  [& args]
  (let [terms (input)
        filtered (search terms books-list)
        final (main-evaluation filtered)
        ranked (ranking final)]
    (output ranked)))
