package com.example.api.restfull;


import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.text.MessageFormat;

/**
 * Created by Administrator on 10/10/2016.
 */

public class HttpUtils {

    final String URL_TEMPLATE = "http://192.168.1.4/PushNotify/api/{0}";
    private HttpClient httpClient;


    public HttpUtils() {
        // httpClient = HttpClientBuilder.create().build(); // new DefaultHttpClient();
        httpClient = new DefaultHttpClient();
    }

    public void SendRequest(BodyRequest bodyRequest) throws IOException, PushNotificationServerException {
        final String response = callHttpService(bodyRequest);
    }

    private String callHttpService(BodyRequest bodyRequest) throws IOException, PushNotificationServerException {

        final String url = MessageFormat.format(URL_TEMPLATE, bodyRequest.GetRequestType().GetRequestMethod());
        final HttpPost request = new HttpPost(url);
        request.setHeader("Content-Type", "application/x-www-form-urlencoded");
        request.setEntity(new UrlEncodedFormEntity(bodyRequest.GetParams(), Consts.UTF_8.toString()));

        try {
            final HttpResponse response = httpClient.execute(request);

            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    response.getEntity().getContent()));

            StringBuffer result = new StringBuffer();
            String line = "";
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }
            if (response.getStatusLine().getStatusCode() != 201) {
                throw new PushNotificationServerException(result.toString());
            }
            return result.toString();
        } catch (IOException e) {
            throw new PushNotificationServerException(e);
        }
    }

    public static final class Consts {

        public static final int CR = 13; // <US-ASCII CR, carriage return (13)>
        public static final int LF = 10; // <US-ASCII LF, linefeed (10)>
        public static final int SP = 32; // <US-ASCII SP, space (32)>
        public static final int HT = 9;  // <US-ASCII HT, horizontal-tab (9)>

        public static final Charset UTF_8 = Charset.forName("UTF-8");
        public static final Charset ASCII = Charset.forName("US-ASCII");
        public static final Charset ISO_8859_1 = Charset.forName("ISO-8859-1");

    }

  //  public void SendRequest1(BodyRequest bodyRequest) throws IOException, PushNotificationServerException {
    //    final String response = callHttpService1(bodyRequest);
   // }

   // private String callHttpService1(BodyRequest bodyRequest) throws IOException, PushNotificationServerException {


           /* MultipartEntity entity = new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE);
            entity.addPart(bodyRequest.GetFileType(), new FileBody(bodyRequest.GetFile()));
            request.setEntity(entity);*/

          /*  final MultipartEntityBuilder mpeb = MultipartEntityBuilder.create();
            mpeb.addBinaryBody(bodyRequest.GetFileType(),
                    bodyRequest.GetFile());
            if (bodyRequest.GetParams() != null) {
                for (BasicNameValuePair bnvp : bodyRequest.GetParams()) {
                    mpeb.addTextBody(bnvp.getName(), bnvp.getValue());
                }
            }
            request.setEntity(mpeb.build());
        } else {
            request.setEntity(new UrlEncodedFormEntity(bodyRequest
                    .GetParams(), Consts.UTF_8.toString()));

        }*/

         //   ByteArrayOutputStream bos = new ByteArrayOutputStream();
           // Bitmap bm = BitmapFactory.decodeFile(bodyRequest.GetFile().getAbsolutePath());
            //bm.compress(Bitmap.CompressFormat.JPEG, 75, bos);
            //byte[] data = bos.toByteArray();
         //   ByteArrayBody bab = new ByteArrayBody(data, "clear.jpg");

          //  MultipartEntity reqEntity = new MultipartEntity(
            //        HttpMultipartMode.BROWSER_COMPATIBLE);
            //reqEntity.addPart("image", bab);
            //reqEntity.addPart("name", new StringBody("clear"));
//    }

}
