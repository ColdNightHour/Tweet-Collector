package HashtagAnalyzer;

import org.json.JSONArray;
import org.json.JSONObject;
import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

import java.io.*;
import java.util.HashSet;

import java.lang.*;
import java.io.File;

public class App{
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
  public static int fileCnt = 1;
  public static int filePassCnt = 1;
  public static long overallBytes = 0;
  public static PrintWriter writer;
    public static void main( String[] args )  {
      ConfigurationBuilder config = new ConfigurationBuilder();
      config.setOAuthConsumerKey("K8P38gSDQUTk1Et7QAxDg5a1B")
            .setOAuthConsumerSecret("GcRmcdSe6CbLT1BvGNZREso8jrcNRENsYDc4crHWr4UOvaA44s")
            .setOAuthAccessToken("1853291412-qwBGaZr2S64KfmIU0hB1aYi4Kc0goxOQWzyeA20")
            .setOAuthAccessTokenSecret("CV17evpidxeGGTkP6mn7OwOOMIflt6DZSljwO0jbq6wZM");
      TwitterStream twitterStream = new TwitterStreamFactory(config.build()).getInstance();
      StatusListener statusListener = new StatusListener() {
        public void onStatus(Status status) {

          try {
            //Duplicate detection
             long id = status.getId();
             if (tweetCnt < MAX_TWEET_CNT) {
                 ++tweetCnt;
                 if (tweetIdHash.contains(id)) {
                     return;
                 } else {
                     tweetIdHash.add(id);
                 }
             } else {
                 System.out.println("Cap Reset for duplicates");
                 tweetCnt = 1;
                 tweetIdHash.clear();
                 tweetIdHash.add(id);
             }
             //Extract tweet data and put into JSON
             org.json.JSONObject object = new JSONObject();
             object.put("name", status.getUser().getScreenName());
             object.put("message", status.getText());
             object.put("timestamp", status.getCreatedAt());
             object.put("location", ((status.getGeoLocation() == null) ? "N/A" : status.getGeoLocation().toString()));
             object.put("place_type", status.getPlace().getCountry());
             object.put("country", status.getPlace().getCountry());
             object.put("full_name", status.getPlace().getFullName());
             object.put("favorites", status.getFavoriteCount());
             object.put("language", status.getLang());

             HashtagEntity [] hashTags = status.getHashtagEntities();
             org.json.JSONArray hashtags = new JSONArray();
             for(int i = 0; i < hashTags.length; i = i + 1) {
               String hashtag = hashTags[i].getText();
               hashtags.put(i, hashTags[i].getText());
             }
             object.put("hashTags", hashtags);
             System.out.println(object);


             String jsonString = object.toString() + "\n";
             try{
               tweetByteCnt = tweetByteCnt + jsonString.getBytes("UTF-8").length;
             } catch(Exception e) {
               System.out.println("WRONG ENCODING");
             }
             buffer.append(jsonString);
             if(tweetByteCnt >= MAX_BYTE_CNT) {
               tweetByteCnt = 0;
               try{
                 System.out.println("FILE " + fileCnt + " WRITTEN OF PASS " + filePassCnt);
                 if(filePassCnt == 1) {
                   filePassCnt++;
                   writer = new PrintWriter("~/Documents/Tweet-Collector/tweets/file" + fileCnt + ".txt", "UTF-8");
                   writer.println(buffer);
                 }
                 else if(filePassCnt != 1) {
                   writer= new PrintWriter(new FileOutputStream("~/Documents/Tweet-Collector/tweets/file" + fileCnt + ".txt", true));
                   writer.append(buffer);
                   if(filePassCnt == 10) {
                     filePassCnt = 1;
                     fileCnt++;
                   }
                   else
                     filePassCnt++;
                 }
                 writer.close();
                 buffer = new StringBuffer();
                 buffer.trimToSize();
                 System.gc();
               }
               catch(Exception e) {
                 System.out.println("ERROR:" + e.getMessage());
               }
             }
           } catch (Exception e) {
               System.out.println("ERROR:" + e.getMessage());
           }
        }
        public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {
            //System.out.println("Got a status deletion notice id:" + statusDeletionNotice.getStatusId());
        }

        public void onTrackLimitationNotice(int numberOfLimitedStatuses) {
            System.out.println("Got track limitation notice:" + numberOfLimitedStatuses);
        }

        public void onScrubGeo(long userId, long upToStatusId) {
            //System.out.println("Got scrub_geo event userId:" + userId + " upToStatusId:" + upToStatusId);
        }

        public void onStallWarning(StallWarning warning) {
            System.out.println("Got stall warning");
        }

        public void onException(Exception ex) {
            ex.printStackTrace();
        }
      };
      twitterStream.addListener(statusListener);

      double [][] boundingbox = {{-121.7099473, 31.6558962},{-83.4119626,47.8417964}};
      FilterQuery filter = new FilterQuery();
      filter.locations(boundingbox);
      twitterStream.filter(filter);
      System.out.println( "Hello World!" );
    }
}
