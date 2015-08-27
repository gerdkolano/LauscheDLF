//package lauschedlf;
package net.za.dyndns.gerd.lauschedlf;

import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;

/**
 * Created by hanno on 25.08.15.
 */
public class XmlDlfParse {
  XmlPullParser myParser;

  public static void main(String args[]) throws XmlPullParserException, IOException {
    String xmlString = args[0];
    new XmlDlfParse().holeEineXmlSeite(new StringReader(xmlString));
  }

  public ArrayList<Item> holeEineXmlSeite(Reader reader)
    throws XmlPullParserException, IOException {
    Log.i("X020", "holeEineXmlSeite() erreicht");

    XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
    factory.setNamespaceAware(true);
    myParser = factory.newPullParser();

    myParser.setInput(reader);

    ArrayList<Item> liste = new ArrayList<>();
    String text = "";
    Item sendung = null;
    PagePages pagePages = null;
    //noinspection
    String namespace = null;

    int eventType = myParser.getEventType();
    while (eventType != XmlPullParser.END_DOCUMENT) {
      String name = myParser.getName();
      //Log.i("X020", name==null?"null":name);
      switch (eventType) {
        case XmlPullParser.START_DOCUMENT:
          drucke("Start document\n");
          break;

        case XmlPullParser.START_TAG:
          if (name == null) break;
          switch (name) {
            case "entries":
              pagePages = new PagePages();
              pagePages.page = myParser.getAttributeValue(namespace, "page");
              pagePages.pages = myParser.getAttributeValue(namespace, "pages");
              break;
            case "item":
              sendung = new Item();
              sendung.pagePages = pagePages;
              sendung.itemAttribut.i = myParser.getAttributeValue(namespace, "id");
              sendung.itemAttribut.file_id = myParser.getAttributeValue(namespace, "file_id");
              sendung.itemAttribut.url = myParser.getAttributeValue(namespace, "url");
              sendung.itemAttribut.timestamp = myParser.getAttributeValue(namespace, "timestamp");
              sendung.itemAttribut.duration = myParser.getAttributeValue(namespace, "duration");
              sendung.itemAttribut.station = myParser.getAttributeValue(namespace, "station");
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
                sendung.sendungsAttribut.id = myParser.getAttributeValue(namespace, "id");
              }
              break;
            case "article":
              if (sendung != null) {
                sendung.articleAttribut.id = myParser.getAttributeValue(namespace, "id");
              }
              break;
            default:  // skip(myParser);
              break;
          }
          break;

        case XmlPullParser.TEXT:
          text = myParser.getText();
          text = text.replace("\n", "\\n");
          // if( myParser.isWhitespace()) {druckeln(" <" + text + ">");}
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
    return liste  ;
  }
int debug = 1;
   void drucke(String arg) {
    //System.out.print(arg);
    if(this.debug>8) Log.i("X010", arg);
  }
}

class PagePages {
  public String page;
  public String pages;

  public int getPage() {
    return Integer.parseInt(page);
  }

  public int getPages() {
    return Integer.parseInt(pages);
  }
}

// http://www.tutorialspoint.com/android/android_xml_parsers.htm
class Item {
  public ItemAttribut itemAttribut;
  public SendungsAttribut sendungsAttribut;
  public ArticleAttribut articleAttribut;
  public PagePages pagePages;

  Item() {
    itemAttribut = this.new ItemAttribut();
    sendungsAttribut = this.new SendungsAttribut();
    articleAttribut = this.new ArticleAttribut();
  }

  class SendungsAttribut {
    String id;

    public String toString() {
      return ""
        + "           ItemAttribut id       " + " = " + "\"" + id + "\"\n"
        ;
    }
  }

  class ArticleAttribut {
    String id;

    public String toString() {
      return ""
        + "           ItemAttribut id       " + " = " + "\"" + id + "\"\n"
        ;
    }
  }

  class ItemAttribut {
    String i;
    String file_id;
    String url;
    String timestamp;
    String duration;
    String station;

    public String toString() {
      return ""
        + "           ItemAttribut i        " + " = " + "\"" + i + "\"\n"
        + "           ItemAttribut file_id  " + " = " + "\"" + file_id + "\"\n"
        + "           ItemAttribut url      " + " = " + "\"" + url + "\"\n"
        + "           ItemAttribut timestamp" + " = " + "\"" + timestamp + "\"\n"
        + "           ItemAttribut duration " + " = " + "\"" + duration + "\"\n"
        + "           ItemAttribut station  " + " = " + "\"" + station + "\"\n"
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
