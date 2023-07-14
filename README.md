A Flink application project using Scala and SBT.

IDE - VSCodium
Terminal Commands:

sbt clean
sbt compile
sbt package
sbt assembly

To run and test your application locally, you can just execute `sbt run` then select the main class that contains the Flink job . 

You can also package the application into a fat jar with `sbt assembly`, then submit it as usual, with something like: 

```
./bin/flink run -c org.WordCount --detached /opt/flink/flinkwordcount_2.12-1-SNAPSHOT.jar --input /opt/flink/README.txt --output /tmp/wordcount.csv
```


You can also run your application from within IntelliJ:  select the classpath of the 'mainRunner' module in the run/debug configurations.
Simply open 'Run -> Edit configurations...' and then select 'mainRunner' from the "Use classpath of module" dropbox. 
# FLINK-PROJECTS


