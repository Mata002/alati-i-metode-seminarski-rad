(ns seminarski-rad.core
  (:require [clojure.edn :as edn]
            [clojure.string :as str])
  (:gen-class))

;; book validity threshold
(def popular-threshold 0.5)
(def academic-threshold 0.5)

;; default result-limit if the user does not type a number.
(def default-result-limit
  10)

;; load file with books data
(defn load-books
  [path]
  (edn/read-string (slurp path)))

;; input: none; output: search string from the user
(defn input
  []
  (print "Search: ") (flush) (read-line))

(defn input-limit
  []
  (print "How many books to show? ") (flush)
  (or (parse-long (str/trim (str (read-line)))) default-result-limit))

;; True if the pattern is found in the title or description.
(defn matches?
  [pattern book]
  (or (re-find pattern (str (:title book))) (re-find pattern (str (:description book)))))

;; input: search string, books list. output: filtered books list
;; Returns books whose title or description contains the search string (not case-sensitive).
(defn search
  [search-terms books]
  (let [pattern (re-pattern (str "(?i)" search-terms))]
    (filter (partial matches? pattern) books)))

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
  [ranked-books result-limit]
  (println (take result-limit ranked-books)))

;; main function
(defn -main
  [& args]
  (let [terms (input)
        result-limit (input-limit)
        books (load-books "resources/books.edn")
        filtered (search terms books)
        final (main-evaluation filtered)
        ranked (ranking final)]
    (output ranked result-limit)))
