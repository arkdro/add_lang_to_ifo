(ns add-lang.core
  (:require [clojure.tools.cli :refer [parse-opts]]
            [add-lang.process]))

(def cli-options
  ;; An option with a required argument
  [["-i" "--indir INDIR" "input dir"
    :validate [#(and
                 (some? %)
                 (string? %))
               "Must be non-empty string"]]
   ["-o" "--outdir OUTDIR" "output dir"
    :validate [#(and
                 (some? %)
                 (string? %))
               "Must be non-empty string"]]
   ;; A boolean option defaulting to nil
   ["-h" "--help"]])

(defn -main
  "Parse command line arguments and run the generator"
  [& args]
  (let [opts (parse-opts args cli-options)
        options (get opts :options)
        indir (get options :indir)
        outdir (get options :outdir)
        errors (get opts :errors)
        help (get-in opts [:options :help])]
    (cond
      help (println (get opts :summary))
      errors (println errors)
      :default (add-lang.process/process_input_dir indir outdir))))

