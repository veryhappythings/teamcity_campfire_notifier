/**
 * Created by IntelliJ IDEA.
 * User: apearson
 * Date: Sep 8, 2010
 * Time: 3:01:45 PM
 * To change this template use File | Settings | File Templates.
 */

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Campfire {
    private String myToken;
    private String myUrl;
    private AuthScope myScope;
    private UsernamePasswordCredentials myCredentials;

    public Campfire(String token, String url, Boolean useSSL) {
        myToken = token;
        myUrl = url;
        myScope = new AuthScope(
                myUrl.replaceAll("http(s)?://", ""),
                useSSL ? 443 : 80
        );

        myCredentials = new UsernamePasswordCredentials(myToken, "X");
    }

    public Boolean postMessage(String roomNumber, String message) {
        try {
            DefaultHttpClient client = new DefaultHttpClient();
            client.getCredentialsProvider().setCredentials(myScope, myCredentials);

            HttpPost post = new HttpPost(myUrl + "/room/" + roomNumber + "/speak.xml");

            
            StringEntity postBody = new StringEntity("<message><type>TextMessage</type><body>" + message + "</body></message>");
            post.setEntity(postBody);
            post.setHeader("Content-Type", "application/xml");

            HttpResponse response = client.execute(post);
            System.out.println(response.getStatusLine());

            HttpEntity entity = response.getEntity();
            
            if (entity != null) {
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(entity.getContent()));

                String inputLine;
                while ((inputLine = in.readLine()) != null)
                    System.out.println(inputLine);
            }
            return true;
        }
        catch (IOException e) {
            return false;
        }
    }
}