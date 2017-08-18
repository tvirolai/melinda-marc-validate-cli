(defproject melinda-marc-validate-cli "0.1.0-SNAPSHOT"
  :description "A "
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.9.0-alpha17"]
                 [marclojure "1.0.2"]
                 [clj-http "3.7.0"]
                 [org.clojure/tools.cli "0.3.5"]
                 [environ "1.1.0"]]
  :main ^:skip-aot melinda-marc-validate-cli.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
