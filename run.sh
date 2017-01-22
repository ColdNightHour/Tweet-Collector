#!
cd ./TweetCollector

if [ "$1" = "compile" ]; then
  mvn clean install
elif [ "$1" = "exec" ]; then
  mvn exec:java -Dexec.mainClass="HashtagAnalyzer.App"
elif [ "$1" = "both" ]; then
  mvn clean install && mvn exec:java -Dexec.mainClass="HashtagAnalyzer.App"
else
  echo "Wrong command"
fi

cd ..
