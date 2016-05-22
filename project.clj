(defproject cantina "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [alda "1.0.0-rc15"]]
  :main ^:skip-aot cantina.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
