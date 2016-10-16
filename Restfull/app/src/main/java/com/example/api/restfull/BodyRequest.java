package com.example.api.restfull;

import org.apache.http.message.BasicNameValuePair;

import java.io.File;
import java.util.List;

/**
 * Created by Administrator on 10/11/2016.
 */

public class BodyRequest {

    private List<BasicNameValuePair> params;
    private RequestType requestType;
    private File file;
    private String fileType;

    public BodyRequest( RequestType requestType,List<BasicNameValuePair> params)
    {
        this.params=params;
        this.requestType=requestType;
    }

    public BodyRequest( RequestType requestType,List<BasicNameValuePair> params,File file,String fileType)
    {
        this.params=params;
        this.requestType=requestType;
        this.file=file;
        this.fileType=fileType;
    }

    public RequestType GetRequestType()
    {
        return this.requestType;
    }

    public List<BasicNameValuePair> GetParams()
    {
        return this.params;
    }

    public File GetFile()
    {
        return  this.file;
    }

    public  String GetFileType()
    {
        return  this.fileType;
    }
}
