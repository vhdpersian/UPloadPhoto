package com.example.api.restfull;

import android.graphics.Bitmap;
import android.util.Base64;

import org.apache.http.message.BasicNameValuePair;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 10/11/2016.
 */

public class BodyRequestFactory {


    public static BodyRequest CreateTokenRegisterRequest(String id_token) {
        List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
        params.add(new BasicNameValuePair("id_token", id_token));
        return new BodyRequest(RequestType.Token_Register, params);
    }

    public static BodyRequest CreateSendPhoto(Bitmap bitmap) {
        List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
        params.add(new BasicNameValuePair("image", convertBitmapToString(bitmap)));
        return new BodyRequest(RequestType.Upload_Image, null);
    }

    public static String convertBitmapToString(Bitmap bmp){
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 70, stream); //compress to which format you want.
        byte[] byte_arr = stream.toByteArray();
        String imageStr = Base64.encodeToString(byte_arr,Base64.NO_WRAP);
        return imageStr;
    }
}
