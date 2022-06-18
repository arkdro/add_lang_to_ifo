(ns add-lang.process-files
  (:require [clojure.string :as str])
  )

(def abbrev_regex #"(?im)^bookname=Abbrev$")
(def languages_regex #"(?i)^(.*)_\w+\.ifo$")

(defn write_output_file
  [new_content file indir outdir]
  )

(defn get_languages
  [file]
  (let [filename (.getName file)]
    (second (re-find languages_regex filename))))

(defn change_content
  [file content]
  (let [languages (get_languages file)]
    (str/replace content abbrev_regex #(str %1 " " languages))))

(defn need_change?
  [text]
  (re-find abbrev_regex text))

(defn get_new_content
  [file]
  (let [content (slurp file)]
    (if (need_change? content)
      (change_content file content)
      content)))

(defn process_one_file
  [file indir outdir]
  (let [new_content (get_new_content file)]
    (write_output_file new_content file indir outdir)))

(defn process_files
  [files indir outdir]
  (for [file files]
    (process_one_file file indir outdir)))
