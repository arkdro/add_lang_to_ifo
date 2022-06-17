(ns add-lang.process
  (:require [clojure.java.io :as io])
  )

(def file_regex #".+\.ifo$")

(defn get_input_directories
  [base_input_dir]
  (let [fs (file-seq (io/file base_input_dir))]
    (filter #(.isDirectory %) fs)))

(defn matches_file_and_regex?
  [regex file]
  (and
   (.isFile file)
   (re-find regex (.getName file)))
  )

(defn get_input_files_in_directory
  [dir regex]
  (filter #(matches_file_and_regex? regex %) (file-seq dir)))

(defn get_input_files
  [dirs]
  (let [files_in_directories (map #(get_input_files_in_directory % file_regex) dirs)]
    (flatten files_in_directories)))

(defn process_files
  [files]
  )

(defn process_input_dir
  [indir outdir]
  (let [
        dirs (get_input_directories indir)
        files (get_input_files dirs)
        ]
    (process_files files)
    )
  )
