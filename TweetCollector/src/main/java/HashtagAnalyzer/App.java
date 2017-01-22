package HashtagAnalyzer;

import org.json.JSONArray;
import org.json.JSONObject;
import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

import java.io.*;
import java.util.HashSet;

public class App
{
  public static int byteCount = 0;
  public static final String baseFileName = "TWEET_FILE";
  public static int tweetCnt = 0; //COUNTING # OF TWEETS SEEN
  public static int tweetByteCnt = 0;
  public static final int MAX_TWEET_CNT = 15000000; //CAP FOR DETECTING DUPES
  public static final int MAX_BYTE_CNT = 10485760/10; //NUMBER OF BYTES FOR 10MB FILE 10485760
  public static HashSet<Long> tweetIdHash = new HashSet<Long>();
  public static File currentFile; //CURRENT FILE BEING I/O'd TO
  public static OutputStreamWriter stream;
  public static String location = "NULL";
  public static Boolean run = true;
  public static StringBuffer buffer = new StringBuffer("");
  public static int fileCnt = 48;
  public static int filePassCnt = 5;
  public static long overallBytes = 0;
  public static PrintWriter writer;
    public static void main( String[] args )
    {
      ConfigurationBuilder config = new ConfigurationBuilder();
      config.setOAuthConsumerKey("K8P38gSDQUTk1Et7QAxDg5a1B")
            .setOAuthConsumerSecret("GcRmcdSe6CbLT1BvGNZREso8jrcNRENsYDc4crHWr4UOvaA44s")
            .setOAuthAccessToken("1853291412-qwBGaZr2S64KfmIU0hB1aYi4Kc0goxOQWzyeA20")
            .setOAuthAccessTokenSecret("CV17evpidxeGGTkP6mn7OwOOMIflt6DZSljwO0jbq6wZM");
      TwitterStream twitterStream = new TwitterStreamFactory(config.build()).getInstance();
        System.out.println( "Hello World!" );
    }
}
