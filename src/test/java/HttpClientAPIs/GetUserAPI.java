package HttpClientAPIs;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;

public class GetUserAPI
{
    CloseableHttpResponse response = null;

    @Test
    public void getUserTest()
    {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        // 1.Create Request with url
        HttpGet getRequest = new HttpGet("https://gorest.co.in/public/v2/users");
        
        // 2. Add the header
        getRequest.addHeader("Authorization",
                "Bearer 2103500286073c509d810e8ae8ee9e1b56f3171d8cfefd26680daa5059a0e27d");
        // 3. Execute the API
        
        try
        {
            response = httpClient.execute(getRequest);
        } catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        // 4.Get the status code
        
        int statusCode = response.getStatusLine().getStatusCode();
        System.out.println("Status Code:" + statusCode);
        Assert.assertEquals(statusCode, 200);
        
        // Get the response body
        
        HttpEntity httpEntity = response.getEntity();
        String responseBody = null;
        try
        {
            responseBody = EntityUtils.toString(httpEntity);
            System.out.println(responseBody);
        } catch (ParseException | IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // Get the JSON values with RestAssurerd JsonPath
        // JsonPath js = new JsonPath(responseBody);
        // System.out.println(js.getString("name"));
        
        // Get the Json value from Jway jsonpath
        
        Object document = Configuration.defaultConfiguration().jsonProvider().parse(responseBody);
        List<HashMap<String, String>> result = JsonPath.read(document, "$");
        System.out.println(result.get(0));
        Assert.assertEquals("Karthikeyan", result.get(0).get("name"));
    }
}
