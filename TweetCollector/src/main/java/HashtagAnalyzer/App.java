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
  public static final int MAX_BYTE_CNT = 10485760/10;
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
    }
}
