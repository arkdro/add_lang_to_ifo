(ns add-lang.process-files
  (:require [clojure.string :as str])
  )

(def abbrev_regex #"(?im)^bookname=Abbrev$")
(def languages_regex #"(?i)^(.*)_\w+\.ifo$")
(def languages_regex_whole_name #"(?i)^(.*)\.ifo$")
(def filename_addition ".new")

(defn build_new_name
  [file]
  (let [fullname (.getPath file)]
    (str fullname filename_addition)))

(defn write_new_content_to_file
  [filename content]
  (spit filename content))

(defn write_output_file
  [content file]
  (let [new_name (build_new_name file)]
    (write_new_content_to_file new_name content)))

(defn get_languages
  [file]
  (let [filename (.getName file)]
    (if-let [short_part (second (re-find languages_regex filename))]
      short_part
      (second (re-find languages_regex_whole_name filename)))))

(defn change_content
  [file content]
  (let [languages (get_languages file)]
    (str/replace content abbrev_regex #(str %1 " " languages))))

(defn need_change?
  [text]
  (re-find abbrev_regex text))

(defn get_content_and_change_flag
  [file]
    (let [content (slurp file)]
    (if (need_change? content)
      {:need_change true, :content content}
      {:need_change false, :content nil})))

(defn change_one_file
  [file content]
  (let [new_content (change_content file content)]
    (write_output_file new_content file)))

(defn process_one_file
  [file]
  (let [{need_change :need_change
         content :content} (get_content_and_change_flag file)]
    (if need_change
      (change_one_file file content)
      nil)))

(defn process_files
  [files]
  (for [file files]
    (process_one_file file)))
