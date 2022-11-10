package HttpClientAPIs;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rest.api.post.User;

public class POSTUserAPI
{
    @Test
    public void createUserAPITest()
    {
        CloseableHttpResponse response = null;
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        // 1. Crate a post request

        HttpPost postRequest = new HttpPost("https://gorest.co.in/public-api/users/");
        // 2 Add headers
        postRequest.addHeader("Authorization",
                "Bearer 2103500286073c509d810e8ae8ee9e1b56f3171d8cfefd26680daa5059a0e27d");
        postRequest.setHeader("Content-Type", "application/json");
        postRequest.setHeader("accept", "application/json");
        // 3.Add the JSON body

        User user = new User("Pavi", "pavi123@gmail.com", "female", "active");

        // 4.Convert the POJO to JSON - Jackson API
        ObjectMapper mapper = new ObjectMapper();
        String userJson = null;

        try
        {
            userJson = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(user);
        } catch (JsonProcessingException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        System.out.println(userJson);

        StringEntity userEntity = null;
        try
        {
            userEntity = new StringEntity(userJson);
        } catch (UnsupportedEncodingException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // 5.Add th json body to the request

        postRequest.setEntity(userEntity);
        // 6. Hit the API /Execute

        try
        {
            response = httpClient.execute(postRequest);
        } catch (ClientProtocolException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // 7. Get the status code
        System.out.println(response.getStatusLine().getStatusCode());

        // 8. Get the response body

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
    }

}
