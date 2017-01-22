#!
cd ./TweetCollector
mvn package && java -cp target/TweetCollector-1.0-SNAPSHOT.jar HashtagAnalyzer.App
cd ..
