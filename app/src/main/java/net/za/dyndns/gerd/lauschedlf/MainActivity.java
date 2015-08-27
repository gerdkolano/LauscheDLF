package net.za.dyndns.gerd.lauschedlf;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import org.apache.commons.io.IOUtils;
import org.xmlpull.v1.XmlPullParserException;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.UnknownHostException;

/*
* File -> Project Structure -> modules app -> Dependencies
* Click '+' in the upper right corner and select "Library dependency"
* In the search field type: "org.apache.commons.io" and click Search
* Select "org.apache.directory.studio:org.apache.commons.io:
* */
public class MainActivity extends AppCompatActivity
  implements AsyncTaskCompleteListener<String> {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    String xmlString;
    //xmlString = getResources().getString(R.string.dlf_xml_text);
    xmlString = "<?xml version=\"1.0\" encoding=\"utf-8\" standalone=\"yes\"?>\n" +
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

    //Log.i("M010", xmlString );
    // myParser.setInput(new BufferedReader(new InputStreamReader(System.in)));
    // myParser.setInput(new StringReader(xmlString));


    lies(3);

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

  public void lies(int page) {
    Log.i("M022", "lies(" + page + ")");
    String myUri = "http://srv.deutschlandradio.de/"
      + "aodlistaudio.1706.de.rpc"
      + "?drau:searchterm=forschung+aktuell"
      + "&drau:page="
      + page;

    Hintergrund hintergrund = new Hintergrund(this, this);
    hintergrund.execute(myUri);
  }

  @Override
  public void onTaskComplete(String result) {
    // do whatever you need
    XmlDlfParse parseDlf = new XmlDlfParse();
    Reader reader = new StringReader(result);
    try {
      Log.i("M022", "parseDlf.holeEineXmlSeite(reader) rufen");
      Item eineXmlSeite = parseDlf.holeEineXmlSeite(reader);
      Log.i("M024", String.format("page %d pages %d",
        eineXmlSeite.pagePages.getPage(),
        eineXmlSeite.pagePages.getPages()));
    } catch (XmlPullParserException | IOException e) {
      e.printStackTrace();
    }
  }
}

// http://stackoverflow.com/questions/3291490/common-class-for-asynctask-in-android

interface AsyncTaskCompleteListener<T> {
  void onTaskComplete(T result);
}
// class Hintergrund extends AsyncTask<String, Void, AsyncTaskResult<String>> {
// class Hintergrund extends AsyncTask<String, Void, String> {

class Hintergrund extends AsyncTask<String, Void, AsyncTaskResult<String>> {
  private AsyncTaskCompleteListener<String> callback;
  private Context context = null;
  private ProgressDialog progressDialog = null;

  public Hintergrund(Context context, AsyncTaskCompleteListener<String> callback) {
    this.context = context;
    this.callback = callback;
    progressDialog = null;
  }

  @Override

  protected AsyncTaskResult<String> doInBackground(String... urls) {

    HttpURLConnection conn = null;
    String response;
    try {
      URL url = new URL(urls[0]);
      conn = (HttpURLConnection) url.openConnection();
      if (conn == null) {
        return new AsyncTaskResult<>(new Exception());
      } else {
        Log.i("M048", "trying to read XML from the internet");
        conn.setRequestMethod("GET");
        int responseCode;
        responseCode = conn.getResponseCode();
        Log.i("M033", "Response Code: " + responseCode);
        InputStream in = new BufferedInputStream(conn.getInputStream());
        response = IOUtils.toString(in, "UTF-8");
      }
    } catch (MalformedURLException e) {
      return new AsyncTaskResult<>(e);
    } catch (UnknownHostException e) {
      return new AsyncTaskResult<>(e);
    } catch (ProtocolException e) {
      return new AsyncTaskResult<>(e);
    } catch (IOException e) {
      return new AsyncTaskResult<>(e);
    }

    int debug = 1;
    if (debug > 8) Log.i("M050", "response =\n" + response + "\n= response");
    if (debug > 8) Log.i("M051", "= response");

    return new AsyncTaskResult<>(response);

  }

  protected void onPreExecute() {
    progressDialog = new ProgressDialog(this.context);
    progressDialog.setMessage("Bitte Geduld ...");
    progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
    progressDialog.setCancelable(true);
    progressDialog.show();
  }

  protected void onPostExecute(AsyncTaskResult<String> result) {
    progressDialog.dismiss();
    Log.i("M040", "on Post execute arbeitet");

    Exception exception = result.getError();
    if (exception != null) {
      // error handling here
      Log.i("M060", "Fehler " + exception.getMessage());
      //e.printStackTrace();
    } else if (isCancelled()) {
      // cancel handling here
    } else {
      // result handling here
      callback.onTaskComplete(result.getResult());
    }

  }
}

class AsyncTaskResult<T> {
  private T result;
  private Exception error;

  public T getResult() {
    return result;
  }

  public Exception getError() {
    return error;
  }

  public AsyncTaskResult(T result) {
    this.result = result;
  }

  public AsyncTaskResult(Exception error) {
    this.error = error;
  }
}
