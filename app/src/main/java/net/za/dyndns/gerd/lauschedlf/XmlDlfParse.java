//package lauschedlf;
package net.za.dyndns.gerd.lauschedlf;

import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;

/**
 * Created by hanno on 25.08.15.
 */
public class XmlDlfParse {
  static XmlPullParser myParser;

  /*
    private static void attribut(String attributname) {
       druckeln(attributname + " = " + myParser.getAttributeValue(null, attributname));
    }
  */
  public static void main(String args[])
    throws XmlPullParserException, IOException {

    XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
    factory.setNamespaceAware(true);
    myParser = factory.newPullParser();

    myParser.setInput(new BufferedReader(new InputStreamReader(System.in)));
    String xmlString = args[0];
    myParser.setInput(new StringReader(xmlString));

    ArrayList<Item> liste = new ArrayList<>();
    String text = "";
    Item sendung = null;

    int eventType = myParser.getEventType();
    while (eventType != XmlPullParser.END_DOCUMENT) {
      String name = myParser.getName();
      Log.i("X020", name==null?"null":name);
      switch (eventType) {
        case XmlPullParser.START_DOCUMENT:
          drucke("Start document\n");
          break;

        case XmlPullParser.START_TAG:
          if (name == null) break;
          switch (name) {
            case "entries":
              //attribut("pages");
              //attribut("page" );

              break;
            case "item":
              sendung = new Item();
              sendung.itemAttribut.i = myParser.getAttributeValue(null, "id");
              sendung.itemAttribut.file_id = myParser.getAttributeValue(null, "file_id");
              sendung.itemAttribut.url = myParser.getAttributeValue(null, "url");
              sendung.itemAttribut.timestamp = myParser.getAttributeValue(null, "timestamp");
              sendung.itemAttribut.duration = myParser.getAttributeValue(null, "duration");
              sendung.itemAttribut.station = myParser.getAttributeValue(null, "station");

              break;
            case "datetime":
              break;
            case "title":
              break;
            case "author":
              break;
            case "station":
              break;
            case "sendung":
              if (sendung != null) {
                sendung.sendungsAttribut.id = myParser.getAttributeValue(null, "id");
              }
              break;
            case "article":
              if (sendung != null) {
                sendung.articleAttribut.id = myParser.getAttributeValue(null, "id");
              }
              break;
            default:  // skip(myParser);
              break;
          }
          break;

        case XmlPullParser.TEXT:
          text = myParser.getText();
          text = text.replace("\n", "\\n");
          // if("\n".equals(text)) { text = "leer";}
          // if( myParser.isWhitespace()) {druckeln(" <" + text + ">");}
          //if(!myParser.isWhitespace()) {druckeln(" {" + text + "}");}
          break;

        case XmlPullParser.END_TAG:
          if (name == null) break;
          if (sendung == null) break;
          switch (name) {
            case "datetime":
              sendung.datetime = text;
              break;
            case "title":
              sendung.title = text;
              break;
            case "author":
              sendung.author = text;
              break;
            case "station":
              sendung.station = text;
              break;
            case "sendung":
              sendung.sendung = text;
              break;
            case "article":
              sendung.article = text;
              break;
            case "item":
              sendung.item = text;
              //drucke(sendung + "99\n");
              liste.add(sendung);
              break;
          }
          break;
      }
      eventType = myParser.next();
    }
    drucke("#### fertig\n");
    for (Item element : liste) {
      drucke(element + "Sendung aus der \"liste\" erledigt\n");
    }
  }
  static void drucke(String arg) {
    //System.out.print(arg);
    Log.i("X010", arg);
  }
}

// http://www.tutorialspoint.com/android/android_xml_parsers.htm
class Item {
  public Attribut itemAttribut;
  public SendungsAttribut sendungsAttribut;
  public ArticleAttribut articleAttribut;

  Item() {
    itemAttribut = this.new Attribut();
    sendungsAttribut = this.new SendungsAttribut();
    articleAttribut = this.new ArticleAttribut();
  }

  class SendungsAttribut {
    String id;

    public String toString() {
      return ""
        + "           Attribut id       " + " = " + "\"" + id + "\"\n"
        ;
    }
  }

  class ArticleAttribut {
    String id;

    public String toString() {
      return ""
        + "           Attribut id       " + " = " + "\"" + id + "\"\n"
        ;
    }
  }

  class Attribut {
    String i;
    String file_id;
    String url;
    String timestamp;
    String duration;
    String station;

    public String toString() {
      return ""
        + "           Attribut i        " + " = " + "\"" + i + "\"\n"
        + "           Attribut file_id  " + " = " + "\"" + file_id + "\"\n"
        + "           Attribut url      " + " = " + "\"" + url + "\"\n"
        + "           Attribut timestamp" + " = " + "\"" + timestamp + "\"\n"
        + "           Attribut duration " + " = " + "\"" + duration + "\"\n"
        + "           Attribut station  " + " = " + "\"" + station + "\"\n"
        ;
    }
  }

  String item;
  String datetime;
  String title;
  String author;
  String station;
  String sendung;
  String article;

  public String toString() {
    return "+++++++++++++++++++++++++++++++++++ Item\n"
      + "Text item    " + " = " + "\"" + item + "\"\n"
      + itemAttribut.toString() + ""
      + "Text datetime" + " = " + "\"" + datetime + "\"\n"
      + "Text title   " + " = " + "\"" + title + "\"\n"
      + "Text author  " + " = " + "\"" + author + "\"\n"
      + "Text station " + " = " + "\"" + station + "\"\n"
      + "Text sendung " + " = " + "\"" + sendung + "\"\n"
      + sendungsAttribut.toString() + ""
      + "Text article " + " = " + "\"" + article + "\"\n"
      + articleAttribut.toString() + ""
      ;
  }
}
// http://www.tutorialspoint.com/android/android_xml_parsers.htm
