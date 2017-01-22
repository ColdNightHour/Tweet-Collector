#!
cd ./TweetCollector

if [ "$1" = "compile" ]; then
  mvn package
elif [ "$1" = "exec" ]; then
  java -cp target/TweetCollector-1.0-SNAPSHOT.jar HashtagAnalyzer.App
elif [ "$1" = "both" ]; then
  mvn package && java -cp target/TweetCollector-1.0-SNAPSHOT.jar HashtagAnalyzer.App
else
  echo "Wrong command"
fi

cd ..
