#!
cd ./TweetCollector

if[ "$1" = "compile" ];
then
  mvn package
else if[ "$1" = "exec" ];
then
  java -cp target/TweetCollector-1.0-SNAPSHOT.jar HashtagAnalyzer.App
else if[ "$1" = "both" ];
then
  mvn package && java -cp target/TweetCollector-1.0-SNAPSHOT.jar HashtagAnalyzer.App
else
  echo "Wrong command"
fi

cd ..
