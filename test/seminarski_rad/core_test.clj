(ns seminarski-rad.core-test
  (:require [clojure.test :refer :all]
            [seminarski-rad.core :refer :all]))

(deftest valid-book-test
  (testing "a book with scores of 0.6 is valid because the thresholds are 0.5"
    (is (true? (valid-book? {:title "Knjiga 1"})))))

(deftest popular-evaluation-test
  (testing "popular score is between 0 and 1"
    (is (<= 0 (popular-evaluation {:title "Book 1"}) 1))))

(deftest academic-evaluation-test
  (testing "academic score is between 0 and 1"
    (is (<= 0 (academic-evaluation {:title "Book 1"}) 1))))
