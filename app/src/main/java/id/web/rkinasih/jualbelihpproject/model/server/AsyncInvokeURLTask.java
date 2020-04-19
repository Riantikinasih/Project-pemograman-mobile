package id.web.rkinasih.jualbelihpproject.model.server;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.http.HttpResponseCache;
import android.os.AsyncTask;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.util.ArrayList;

public class AsyncInvokeURLTask<nameValuePairs, postExcecuteListener> extends AsyncTask<Void, Void, String> {
    public String mNoteItWebUrl = "www.smartneasy.com";
    private ArrayList<NameValuePair> mParams;
    private OnPostExcecuteListener onPostExcecuteListener = null;
    private ProgressDialog dialog;
    public boolean showdialog = false;
    public String message = "Proses Data";
    public String url_server = "http://localhost/xphone";
    public Context applicationContext;

    public static interface OnPostExcecuteListener {
        void onPostExcecute(String result);
    }

    public AsyncInvokeURLTask(
            ArrayList<NameValuePair> nameValuePairs, OnPostExcecuteListener postExcecuteListener) throws Exception{
    mParams =nameValuePairs;
    onPostExcecuteListener =postExcecuteListener;
        if(onPostExcecuteListener ==null)
            throw new Exception("Param Cannot be nutll");
}
    @Override
    public OnPreExecute() {
        if (showdialog)
            this.dialog = ProgressDialog.show(applicationContext,message, message: "Silahkan menunggu...",)
    }
    @Override
    public String doInBackground(Void... Params) {
        String result = "timeout";
        HttpClient httpclient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(url_server+mNoteItWebUrl);
        try {
            httpPost.setEntity(new URLEncodedFornEntity(mParams));
            HttpResponseCache response = httpClient.excecute(httpPost);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                InputStream inStream = entity.getContent();
                result = convertStreamToString(inStream);

            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
    }
        return result;
    }
    @Override
    public void onPostExcecute(String result){
        if (onPostExcecuteListener !=null) {
            try {
                if (showdialog)this.dialog.dismiss();
                onPostExcecuteListener.onPostExcecute(result);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
    private static String convertStreamToString(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader());
        StringBuilder sb = new StringBuilder();
        String line = null;
        try {
            while (line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
        }catch (IOException e) {
            e.PrintStackTrace();
        }finally {
            try {
                is.close();
            }catch (IOException e){
                e.printStackTrace();
        }
    }
    return sb.toString();
    }
}
