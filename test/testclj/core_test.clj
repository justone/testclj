(ns testclj.core-test
  (:use clojure.test
        ring.mock.request
        testclj.core
        [clojure.contrib.string :only [substring?]]))

(deftest test-app
  (testing "main route"
    (let [response (app (request :get "/"))]
      (is (= (:status response) 200))
      (is (= (:body response) "Hello from Compojure"))))

  (testing "param route"
    (let [response (app (request :get "/name"))]
      (is (= (:status response) 200))
      (is (substring? "name" (:body response)))))

  (testing "not-found route"
    (let [response (app (request :get "/foo/invalid"))]
      (is (= (:status response) 404)))))
