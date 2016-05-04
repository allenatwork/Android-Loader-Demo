package allen.projecttest5;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import allen.projecttest5.model.ClubObject;

/**
 * Created by Allen on 04-May-16.
 */
public class DownloadStringLoader extends AsyncTaskLoader<List<ClubObject>> {
    private List<ClubObject> listClub = null;
    private String url;

    public DownloadStringLoader(Context context, String url) {
        super(context);
        this.url = url;
    }

    @Override
    public List<ClubObject> loadInBackground() {
        Log.d("Loader", "loadInBackground");
        String content = null;
        try {
            content = downloadContent(url);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (content != null) {
            Gson gson = new Gson();
            listClub = gson.fromJson(content, new TypeToken<List<ClubObject>>() {
            }.getType());
        }
        return listClub;
    }

    @Override
    protected void onStartLoading() {
        Log.d("Loader", "onStartLoading");
        if (listClub != null) deliverResult(listClub);
        if (takeContentChanged() || listClub == null) forceLoad();
    }

    private String downloadContent(String myurl) throws IOException {
        Log.d("Loader", "Load Content from Server");
        InputStream is = null;
        HttpURLConnection conn = null;
        int length = 500;

        try {
            URL url = new URL(myurl);
            conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            conn.connect();
//            int response = conn.getResponseCode();
            is = conn.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String line = "";
            StringBuilder builder = new StringBuilder();
            while ((line = br.readLine()) != null) {
                builder.append(line);
            }
            return builder.toString();
        } finally {
            if (is != null) {
                is.close();
            }
            conn.disconnect();
        }
    }
}
