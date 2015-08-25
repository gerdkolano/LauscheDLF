package net.za.dyndns.gerd.lauschedlf;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by hanno on 25.08.15.
 */
public class XmlDlfParse {

  public static void main(String args[])
    throws XmlPullParserException, IOException {
    Sendungo sendung = new Sendungo();
    XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
    factory.setNamespaceAware(true);
    XmlPullParser myParser = factory.newPullParser();

    myParser.setInput(new BufferedReader(new InputStreamReader(System.in)));

    int eventType = myParser.getEventType();
    String text = "";
    while (eventType != XmlPullParser.END_DOCUMENT) {
      String name = myParser.getName();
      switch (eventType) {
        case XmlPullParser.START_DOCUMENT:
          System.out.println("Start document");
          break;

        case XmlPullParser.START_TAG:
          if (name.equals("entries")) {

            System.out.println("Start tag " + name + myParser.getName());
          }
          if (name.equals("item")) {
            System.out.println("Start tag " + name + myParser.getName());

          }
          break;

        case XmlPullParser.TEXT:
          text = myParser.getText();
          break;

        case XmlPullParser.END_TAG:
          if (name.equals("country")) {
            //datetime = text;
          } else if (name.equals("humidity")) {
            //humidity = myParser.getAttributeValue(null, "value");
          } else if (name.equals("pressure")) {
            //pressure = myParser.getAttributeValue(null, "value");
          } else if (name.equals("temperature")) {
            //temperature = myParser.getAttributeValue(null, "value");
          } else {
          }
          break;
      }
      eventType = myParser.next();
    }

  }
}

class Item {
  class Attribut {
    String i;
    String file_id;
    String url;
    String timestamp;
    String duration;
    String station;
  }

  String datetime;
  String title;
  String author;
  String station;
  String sendung;
  String article;
}

class Sendungo {
  public class Entries {
    String pages;
    String page;

    public void setPage(String page) {
      this.page = page;
    }

    public void setPages(String pages) {
      this.pages = pages;
    }

    public class Item {

      int i;
      String file_id;
      String url;
      String timestamp;
      String duration;
      String station;

      public void setStation(String station) {
        this.station = station;
      }

      public void setDuration(String duration) {
        this.duration = duration;
      }

      public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
      }

      public void setUrl(String url) {
        this.url = url;
      }

      public void setFile_id(String file_id) {
        this.file_id = file_id;
      }

      public void setI(int i) {
        this.i = i;
      }
    }
  }

  String datetime;
  String title;
  String author;
  String station;
  String sendung;
  String article;


  public void setArticle(String article) {
    this.article = article;
  }

  public void setSendung(String sendung) {
    this.sendung = sendung;
  }

  public void setStation(String station) {
    this.station = station;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public void setDatetime(String datetime) {
    this.datetime = datetime;
  }
}


