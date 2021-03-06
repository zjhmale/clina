(ns clina.gitcore-test
  (:require [clojure.test :refer :all]
            [clina.util.git.cljgit :refer :all]
            [clina.util.git.gitcore :refer :all]))

(deftest init-bare-repo
  (testing "create bare repo"
    (let [owner (str "owner" (System/currentTimeMillis))
          repo (str "repo" (System/currentTimeMillis))
          result (init-repo owner repo)]
      (do (println "create a bare repo done")
          (delete-repo owner repo)
          (is (= result true))))))

(deftest repo-commits
  (testing "get repo commits with"
    (let [commits (:commits (list-commits "root" "hehehe" "jihui_dev" "3" "."))]
      (doall
        (map println commits))))
  (testing "get repo commits with path"
    (let [commits (:commits (list-commits "root" "hehehe" "jihui_dev" "1" "hehe"))]
      (doall
        (map println commits)))))

(deftest repo-branches
  (testing "get repo branches with info"
    (let [branches (apply with-repo-object (conj ["root" "hehehe"] get-repo-branches-withinfo))]
      (println branches))))

(deftest commit-revisions
  (testing "get branches of specific commit"
    (let [branches (apply with-repo-object (conj ["root" "hehehe"] get-commit-branches "17fd66c156f508ab2cb8440af566029c8ba5cced"))]
      (println branches)))
  (testing "get tags of specific commit"
    (let [tags (apply with-repo-object (conj ["root" "hehehe"] get-commit-tags "17fd66c156f508ab2cb8440af566029c8ba5cced"))]
      (println tags))))

(deftest commit-diffs
  (testing "get diffs of specific commit"
    (let [diffs (get-commit-diffs "root" "hehehe" "c84b0e7c404e2ed791b5fa1f527a1c4886f62672")]
      (println diffs))))