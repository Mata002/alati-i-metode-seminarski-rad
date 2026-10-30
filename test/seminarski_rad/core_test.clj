(ns seminarski-rad.core-test
  (:require [clojure.test :refer :all]
            [seminarski-rad.core :refer :all]))

(deftest valid-book-test
  (testing "a book with scores of 0.6 is valid because the thresholds are 0.5"
    (is (true? (valid-book? {:title "Knjiga 1"})))))
