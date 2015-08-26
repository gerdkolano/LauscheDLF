package net.za.dyndns.gerd.lauschedlf;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    XmlDlfParse parseDlf = new XmlDlfParse();
    String xmlText;
    //xmlText = getResources().getString(R.string.dlf_xml_text);
    xmlText = "<?xml version=\"1.0\" encoding=\"utf-8\" standalone=\"yes\"?>\n" +
      "<entries page=\"1\" pages=\"14\">\n" +
      "  <item duration=\"1480\" file_id=\"5050c259\" i=\"0\" id=\"391729\" station=\"4\" timestamp=\"1440426902\" url=\"http://ondemand-mp3.dradio.de/file/dradio/2015/08/24/dlf_20150824_1635_5050c259.mp3\">\n" +
      "    <datetime>2015-08-24 16:35:02</datetime>\n" +
      "    <title>Forschung aktuell 24.08.2015, komplette Sendung</title>\n" +
      "    <author>Pyritz, Lennart</author>\n" +
      "    <station>DLF</station>\n" +
      "    <sendung id=\"117\">Forschung aktuell</sendung>\n" +
      "    <article id=\"\"/>\n" +
      "  </item>\n" +
      "  <item duration=\"1479\" file_id=\"ead6d0bb\" i=\"1\" id=\"391171\" station=\"4\" timestamp=\"1440167702\" url=\"http://ondemand-mp3.dradio.de/file/dradio/2015/08/21/dlf_20150821_1635_ead6d0bb.mp3\">\n" +
      "    <datetime>2015-08-21 16:35:02</datetime>\n" +
      "    <title>Forschung aktuell 21.08.2015, komplette Sendung</title>\n" +
      "    <author>Reuning, Arndt</author>\n" +
      "    <station>DLF</station>\n" +
      "    <sendung id=\"117\">Forschung aktuell</sendung>\n" +
      "    <article id=\"\"/>\n" +
      "  </item>\n" +
      "  <item duration=\"1479\" file_id=\"46b0a4b4\" i=\"2\" id=\"390875\" station=\"4\" timestamp=\"1440081300\" url=\"http://ondemand-mp3.dradio.de/file/dradio/2015/08/20/dlf_20150820_1635_46b0a4b4.mp3\">\n" +
      "    <datetime>2015-08-20 16:35:00</datetime>\n" +
      "    <title>Forschung aktuell 20.08.2015, komplette Sendung</title>\n" +
      "    <author>Krauter, Ralf</author>\n" +
      "    <station>DLF</station>\n" +
      "    <sendung id=\"117\">Forschung aktuell</sendung>\n" +
      "    <article id=\"\"/>\n" +
      "  </item>\n" +
      "  <item duration=\"1489\" file_id=\"8f8c1707\" i=\"3\" id=\"390597\" station=\"4\" timestamp=\"1439994934\" url=\"http://ondemand-mp3.dradio.de/file/dradio/2015/08/19/dlf_20150819_1635_8f8c1707.mp3\">\n" +
      "    <datetime>2015-08-19 16:35:34</datetime>\n" +
      "    <title>Forschung aktuell 19.08.2015, komplette Sendung</title>\n" +
      "    <author>Reuning, Arndt</author>\n" +
      "    <station>DLF</station>\n" +
      "    <sendung id=\"117\">Forschung aktuell</sendung>\n" +
      "    <article id=\"\"/>\n" +
      "  </item>\n" +
      "  <item duration=\"1490\" file_id=\"9463ff18\" i=\"4\" id=\"390331\" station=\"4\" timestamp=\"1439908502\" url=\"http://ondemand-mp3.dradio.de/file/dradio/2015/08/18/dlf_20150818_1635_9463ff18.mp3\">\n" +
      "    <datetime>2015-08-18 16:35:02</datetime>\n" +
      "    <title>Forschung aktuell 18.08.2015, komplette Sendung</title>\n" +
      "    <author>Krauter, Ralf</author>\n" +
      "    <station>DLF</station>\n" +
      "    <sendung id=\"117\">Forschung aktuell</sendung>\n" +
      "    <article id=\"\"/>\n" +
      "  </item>\n" +
      "  <item duration=\"1483\" file_id=\"9643ad3a\" i=\"5\" id=\"390054\" station=\"4\" timestamp=\"1439822102\" url=\"http://ondemand-mp3.dradio.de/file/dradio/2015/08/17/dlf_20150817_1635_9643ad3a.mp3\">\n" +
      "    <datetime>2015-08-17 16:35:02</datetime>\n" +
      "    <title>Forschung aktuell 17.08.2015, komplette Sendung</title>\n" +
      "    <author>Pyritz, Lennart</author>\n" +
      "    <station>DLF</station>\n" +
      "    <sendung id=\"117\">Forschung aktuell</sendung>\n" +
      "    <article id=\"\"/>\n" +
      "  </item>\n" +
      "  <item duration=\"1476\" file_id=\"6bc70577\" i=\"6\" id=\"389476\" station=\"4\" timestamp=\"1439562903\" url=\"http://ondemand-mp3.dradio.de/file/dradio/2015/08/14/dlf_20150814_1635_6bc70577.mp3\">\n" +
      "    <datetime>2015-08-14 16:35:03</datetime>\n" +
      "    <title>Forschung aktuell 14.08.2015, komplette Sendung</title>\n" +
      "    <author>Krauter, Ralf</author>\n" +
      "    <station>DLF</station>\n" +
      "    <sendung id=\"117\">Forschung aktuell</sendung>\n" +
      "    <article id=\"\"/>\n" +
      "  </item>\n" +
      "  <item duration=\"1484\" file_id=\"3ab2dcf9\" i=\"7\" id=\"389187\" station=\"4\" timestamp=\"1439476542\" url=\"http://ondemand-mp3.dradio.de/file/dradio/2015/08/13/dlf_20150813_1635_3ab2dcf9.mp3\">\n" +
      "    <datetime>2015-08-13 16:35:42</datetime>\n" +
      "    <title>Forschung aktuell 13.08.2015, komplette Sendung</title>\n" +
      "    <author>Blumenthal, Uli</author>\n" +
      "    <station>DLF</station>\n" +
      "    <sendung id=\"117\">Forschung aktuell</sendung>\n" +
      "    <article id=\"\"/>\n" +
      "  </item>\n" +
      "  <item duration=\"1485\" file_id=\"ae7f8c32\" i=\"8\" id=\"388905\" station=\"4\" timestamp=\"1439390144\" url=\"http://ondemand-mp3.dradio.de/file/dradio/2015/08/12/dlf_20150812_1635_ae7f8c32.mp3\">\n" +
      "    <datetime>2015-08-12 16:35:44</datetime>\n" +
      "    <title>Forschung aktuell 12.08.2015, komplette Sendung</title>\n" +
      "    <author>Rieger, Jennifer</author>\n" +
      "    <station>DLF</station>\n" +
      "    <sendung id=\"117\">Forschung aktuell</sendung>\n" +
      "    <article id=\"\"/>\n" +
      "  </item>\n" +
      "  <item duration=\"1483\" file_id=\"485ece4a\" i=\"9\" id=\"388649\" station=\"4\" timestamp=\"1439303702\" url=\"http://ondemand-mp3.dradio.de/file/dradio/2015/08/11/dlf_20150811_1635_485ece4a.mp3\">\n" +
      "    <datetime>2015-08-11 16:35:02</datetime>\n" +
      "    <title>Forschung aktuell 11.08.2015, komplette Sendung</title>\n" +
      "    <author>Krauter, Ralf</author>\n" +
      "    <station>DLF</station>\n" +
      "    <sendung id=\"117\">Forschung aktuell</sendung>\n" +
      "    <article id=\"\"/>\n" +
      "  </item>\n" +
      "</entries>\n";
    String args [] = {xmlText};
    Log.i("M010", xmlText );
    try {
      parseDlf.main(args);
    } catch (XmlPullParserException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }

  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.menu_main, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();

    //noinspection SimplifiableIfStatement
    if (id == R.id.action_settings) {
      return true;
    }

    return super.onOptionsItemSelected(item);
  }
}
