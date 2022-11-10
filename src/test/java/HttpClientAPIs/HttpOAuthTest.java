package HttpClientAPIs;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import oauth.signpost.OAuthConsumer;
import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;
import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;

public class HttpOAuthTest
{
    
    public static void twitterAPIOAuthTest()
    {
        String consumerKey = "nToTrMmI1RXiFGsoV57u2viOJ";
        String consumerSecret = "POaAmY2cdJ7foi8vnvi44SBEtSqBiTlND4z0H7mUAQskFvnZQc";
        String accessTOken = "66744190-kJRLtbdKYXKGQMlMAI3OykdlknR2GxL9M63AQUfDp";
        String accessTokenSecret = "CeJNuJSaZW3SORl6XSnGeLRMNOflGhrqanR8P50NVaDc0";
        OAuthConsumer consumer = new CommonsHttpOAuthConsumer(consumerKey, consumerSecret);
        consumer.setTokenWithSecret(accessTOken, accessTokenSecret);
        
        CloseableHttpResponse response = null;
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost postRequest = new HttpPost(
                "https://api.twitter.com/1.1/statuses/update.json?status=RajeevHTTPClient");
        
        try
        {
            consumer.sign(postRequest);
        } catch (OAuthMessageSignerException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (OAuthExpectationFailedException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (OAuthCommunicationException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try
        {
            response = httpClient.execute(postRequest);
        } catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        System.out.println(response.getStatusLine().getStatusCode());
        
        HttpEntity httpEntity = response.getEntity();
        String responseBody = null;
        
        try
        {
            responseBody = EntityUtils.toString(httpEntity);
        } catch (ParseException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println(responseBody);
        
        // Get the tweet info
        
        HttpGet getRequest = new HttpGet("https://api.twitter.com/1.1/statuses/show.json?id=1580062497083908096");
        try
        {
            consumer.sign(getRequest);
        } catch (OAuthMessageSignerException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (OAuthExpectationFailedException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (OAuthCommunicationException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        try
        {
            response = httpClient.execute(getRequest);
        } catch (ClientProtocolException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        System.out.println(response.getStatusLine().getStatusCode());
        
        HttpEntity httpGetEntity = response.getEntity();
        String getResponseBody = null;
        
        try
        {
            getResponseBody = EntityUtils.toString(httpGetEntity);
        } catch (ParseException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println(getResponseBody);
        // https://twitter.com/krkumarid/status/1580062497083908096
    }
    
    public static void main(String[] args)
    {
        // TODO Auto-generated method stub
        twitterAPIOAuthTest();
    }
    
}
