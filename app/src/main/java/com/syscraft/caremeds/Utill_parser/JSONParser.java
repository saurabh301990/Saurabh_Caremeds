package com.syscraft.caremeds.Utill_parser;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.GZIPInputStream;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.params.ConnManagerParams;
//import org.apache.http.entity.mime.HttpMultipartMode;
//import org.apache.http.entity.mime.MultipartEntity;
//import org.apache.http.entity.mime.content.ByteArrayBody;
//import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;

import com.syscraft.caremeds.constants.App_Constants;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.security.cert.X509Certificate;

public class JSONParser {

    static InputStream is = null;
    Context contxt;
//    static String json = "", responsecode = "";
//    HttpPost httpPost;
//    private int statusCode;

    public JSONParser(Context ctx) {
        contxt = ctx;
    }


    public static String convertStreamToString(InputStream is) {
        /*
         * To convert the InputStream to String we use the BufferedReader.readLine() method. We iterate until the BufferedReader return null which means there's no more data to read. Each line will
		 * appended to a StringBuilder and returned as String.
		 *
		 * (c) public domain: http://senior.ceng.metu.edu.tr/2009/praeda/2009/01/11/a-simple-restful-client-at-android/
		 */
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

    public String postdata_method(String url, String json_params) {
        JSONObject jObj = null;
        String json_response = "";
        try {
            // defaultHttpClient
            HttpClient httpClient = new DefaultHttpClient();
            // // set http params
            HttpConnectionParams.setConnectionTimeout(httpClient.getParams(), 30000); //Timeout Limit
//			HttpConnectionParams.setSoTimeout(params11, 300000);

            HttpPost httpPost = new HttpPost(url);

            // httpPost.setEntity(new StringEntity(arr.toString()));
            StringEntity se = new StringEntity(json_params);
            // se.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
            //se.setContentType(new BasicHeader("", "application/json"));
            se.setContentType(new BasicHeader("Accept", "application/json"));
            se.setContentType(new BasicHeader("Content-type", "application/json"));
            httpPost.setEntity(se);




            try {
                HttpResponse httpResponse = httpClient.execute(httpPost);
                HttpEntity httpEntity = httpResponse.getEntity();
                is = httpEntity.getContent();


                StatusLine status = httpResponse.getStatusLine();
                int statusCode = status.getStatusCode();
                Log.e("login response", "" + httpResponse.getStatusLine());
            } catch (Exception e5) {

                e5.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(is, "iso-8859-1"), 8);
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "");
            }
            is.close();

            Log.e("sb.toString()", "sb.toString()--------------> " + sb.toString());
            json_response = sb.toString();

        } catch (Exception e) {
            Log.e("Buffer Error", "Error converting result " + e.toString());
        }

//		// try parse the string to a JSON object
//		try {
//			jObj = new JSONObject(json_response);
//		} catch (JSONException e) {
//			Log.e("JSON Parser", "Error parsing data " + e.toString());
//		}

        // return JSON String
        return json_response;

    }


    /***********************************************************************************/


//    public String postdata_method_222(String url, String json_params) {
//        JSONObject jObj = null;
//        String json_response = "";
//        try {
//            // defaultHttpClient
//            HttpClient httpClient = new DefaultHttpClient();
//            // // set http params
//            HttpConnectionParams.setConnectionTimeout(httpClient.getParams(), 10000); //Timeout Limit
////			HttpConnectionParams.setSoTimeout(params11, 300000);
//
//            HttpPost httpPost = new HttpPost(url);
//
//            // httpPost.setEntity(new StringEntity(arr.toString()));
//            StringEntity se = new StringEntity(json_params);
//            // se.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
//            //se.setContentType(new BasicHeader("", "application/json"));
//            se.setContentType(new BasicHeader("Accept", "application/json"));
//            se.setContentType(new BasicHeader("Content-type", "application/json"));
//            httpPost.setEntity(se);
//
//            try {
//                HttpResponse httpResponse = httpClient.execute(httpPost);
//                HttpEntity httpEntity = httpResponse.getEntity();
//                is = httpEntity.getContent();
//                StatusLine status = httpResponse.getStatusLine();
//                int statusCode = status.getStatusCode();
//                Log.e("login response", "" + httpResponse.getStatusLine());
//            } catch (Exception e5) {
//
//                e5.printStackTrace();
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        try {
//            BufferedReader reader = new BufferedReader(new InputStreamReader(is, "iso-8859-1"), 8);
//            StringBuilder sb = new StringBuilder();
//            String line = null;
//            while ((line = reader.readLine()) != null) {
//                sb.append(line + "");
//            }
//            is.close();
//
//            Log.e("sb.toString()", "--------------> " + sb.toString());
//            json_response = sb.toString();
//
//        } catch (Exception e) {
//            Log.e("Buffer Error", "Error converting result " + e.toString());
//        }
//
////		// try parse the string to a JSON object
////		try {
////			jObj = new JSONObject(json_response);
////		} catch (JSONException e) {
////			Log.e("JSON Parser", "Error parsing data " + e.toString());
////		}
//
//        // return JSON String
//        return json_response;
//
//    }


    /***********************************************************************************/


    public String getjson_method(String url) {
        JSONObject jObj = null;
        String json_response = "";
        try {

            HttpClient httpClient = new DefaultHttpClient();
            HttpConnectionParams.setConnectionTimeout(httpClient.getParams(), 10000); //Timeout Limit
            HttpGet httpget = new HttpGet(url);

            try {
                HttpResponse httpResponse = httpClient.execute(httpget);
                HttpEntity httpEntity = httpResponse.getEntity();
                is = httpEntity.getContent();
               Log.e("httpResponse.getAllHeaders()","ContentLength----> " + httpEntity.getContentLength()) ;
                // convert content stream to a String
                json_response = convertStreamToString(is);
//                StatusLine status = httpResponse.getStatusLine();
//                int statusCode = status.getStatusCode();
//                Log.e("login response", "" + httpResponse.getStatusLine());
            } catch (SSLPeerUnverifiedException e5) {

                e5.printStackTrace();
            } catch (Exception e5) {

                e5.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // return JSON String
        return json_response;

    }


    /***********************************************************************************/


    public String get_login_json_method(String url) {
        JSONObject jObj = null;
        String json_response = "";
        try {
            // defaultHttpClient
            HttpClient httpClient = new DefaultHttpClient();
            // // set http params
            HttpConnectionParams.setConnectionTimeout(httpClient.getParams(), 10000); //Timeout Limit
//			HttpConnectionParams.setSoTimeout(params11, 300000);

            HttpGet httpget = new HttpGet(url);

            try {
                HttpResponse httpResponse = httpClient.execute(httpget);
                HttpEntity httpEntity = httpResponse.getEntity();
                is = httpEntity.getContent();

                Log.e("httpResponse.getAllHeaders()","ContentLength----> " + httpEntity.getContentLength()) ;

                // convert content stream to a String
                json_response = convertStreamToString(is);
//                StatusLine status = httpResponse.getStatusLine();
//                int statusCode = status.getStatusCode();
//                Log.e("login response", "" + httpResponse.getStatusLine());
            } catch (SSLPeerUnverifiedException e5) {

                e5.printStackTrace();
            } catch (Exception e5) {

                e5.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // return JSON String
        return json_response;

    }


//    private DefaultHttpClient getSecuredHttpClient(HttpClient httpClient) throws Exception {
//        final X509Certificate[] _AcceptedIssuers = new X509Certificate[] {};
//        try {
//            SSLContext ctx = SSLContext.getInstance("TLS");
//            X509TrustManager tm = new X509TrustManager() {
//                @Override
//                public X509Certificate[] getAcceptedIssuers() {
//                    return _AcceptedIssuers;
//                }
//                @Override
//                public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
//                }
//                @Override
//                public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
//                }
//            };
//            ctx.init(null, new TrustManager[] { tm }, new SecureRandom());
//            SSLSocketFactory ssf = new SSLSocketFactory(ctx, SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
//            ClientConnectionManager ccm = httpClient.getConnectionManager();
//            SchemeRegistry sr = ccm.getSchemeRegistry();
//            sr.register(new Scheme("https", 443, ssf));
//            return new DefaultHttpClient(ccm, httpClient.getParams());
//        } catch (Exception e) {
//            throw e;
//        }
//    }


    /***********************************************************************************************/

//    public JSONArray getJSONArrayFromUrl(String url, List<NameValuePair> params) {
//        JSONArray jObj = null;
//        json = "";
//        try {
//            // defaultHttpClient
//            DefaultHttpClient httpClient = new DefaultHttpClient();
//            // // set http params
//
//            HttpParams params11 = httpClient.getParams();
//            HttpConnectionParams.setConnectionTimeout(params11, 300000);
//            HttpConnectionParams.setSoTimeout(params11, 300000);
//
//            HttpPost httpPost = new HttpPost(url);
//
//            // httpPost.setEntity(new StringEntity(arr.toString()));
//            httpPost.setEntity(new UrlEncodedFormEntity(params));
//
//            try {
//                HttpResponse httpResponse = httpClient.execute(httpPost);
//                HttpEntity httpEntity = httpResponse.getEntity();
//
//                // this line here is what saves a lot of work
//                // String result = EntityUtils.toString(httpEntity, HTTP.UTF_8);
//                //
//                // Log.e("EntityUtils", "--------------> " + result);
//
//                is = httpEntity.getContent();
//
//            } catch (Exception e5) {
//
//                e5.printStackTrace();
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        try {
//            BufferedReader reader = new BufferedReader(new InputStreamReader(is, "iso-8859-1"), 8);
//            StringBuilder sb = new StringBuilder();
//            String line = null;
//            while ((line = reader.readLine()) != null) {
//                sb.append(line + "");
//            }
//            is.close();
//
//            Log.e("sb.toString()", "--------------> " + sb.toString());
//            json = sb.toString();
//
//        } catch (Exception e) {
//            Log.e("Buffer Error", "Error converting result " + e.toString());
//        }
//
//        // try parse the string to a JSON object
//        try {
//            jObj = new JSONArray(json);
//        } catch (JSONException e) {
//            Log.e("JSON Parser", "Error parsing data " + e.toString());
//        }
//
//        // return JSON String
//        return jObj;
//
//    }
//
//    public JSONObject getJSONFromUrlTimeout(Activity context, String url, List<NameValuePair> params) {
//        JSONObject jObj = null;
//
//        try {
//
//            HttpParams params11 = new BasicHttpParams();
//            HttpConnectionParams.setConnectionTimeout(params11, 3000);
//            HttpConnectionParams.setSoTimeout(params11, 3000);
//
//            ConnManagerParams.setTimeout(params11, 3 * 1000);
//
//            HttpClient httpClient = new DefaultHttpClient();
//            httpPost = new HttpPost(url);
//
//            httpPost.setEntity(new UrlEncodedFormEntity(params));
//
//            try {
//                HttpResponse httpResponse = httpClient.execute(httpPost);
//                HttpEntity httpEntity = httpResponse.getEntity();
//                is = httpEntity.getContent();
//
//            } catch (Exception e5) {
//
//                e5.printStackTrace();
//            }
//
//            try {
//                BufferedReader reader = new BufferedReader(new InputStreamReader(is, "iso-8859-1"), 8);
//                StringBuilder sb = new StringBuilder();
//                String line = null;
//                while ((line = reader.readLine()) != null) {
//                    sb.append(line + "\n");
//                }
//                is.close();
//                json = sb.toString();
//
//            } catch (Exception e) {
//                Log.e("Buffer Error", "Error converting result " + e.toString());
//            }
//
//            // try parse the string to a JSON object
//            try {
//                jObj = new JSONObject(json);
//            } catch (JSONException e) {
//                Log.e("JSON Parser", "Error parsing data " + e.toString());
//            }
//
//            // return JSON String
//            return jObj;
//
//        } catch (Exception e) {
//            e.printStackTrace();
//
//            // App_Constants.showAlert("Request Time out.", "Alert!!!", context);
//
//            return null;
//        }
//
//    }
//
//    public void abortHttpPost() {
//        httpPost.abort();
//    }
//
//    public String getJSONStringFromUrl(String url, List<NameValuePair> params) {
//
//        JSONArray arr = new JSONArray(params);
//        // Log.e("arr.toString()", "--------------> " + arr.toString());
//        try {
//            // defaultHttpClient
//            DefaultHttpClient httpClient = new DefaultHttpClient();
//            // // set http params
//
//            HttpParams params11 = httpClient.getParams();
//            HttpConnectionParams.setConnectionTimeout(params11, 300000);
//            HttpConnectionParams.setSoTimeout(params11, 300000);
//
//            HttpPost httpPost = new HttpPost(url);
//
//            // httpPost.setEntity(new StringEntity(arr.toString()));
//            httpPost.setEntity(new UrlEncodedFormEntity(params));
//
//            try {
//                HttpResponse httpResponse = httpClient.execute(httpPost);
//                HttpEntity httpEntity = httpResponse.getEntity();
//                is = httpEntity.getContent();
//
//            } catch (Exception e5) {
//
//                e5.printStackTrace();
//            }
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        } catch (Exception e) {
//            e.printStackTrace();
//
//        }
//
//        try {
//            BufferedReader reader = new BufferedReader(new InputStreamReader(is, "iso-8859-1"), 8);
//            StringBuilder sb = new StringBuilder();
//            String line = null;
//            while ((line = reader.readLine()) != null) {
//                sb.append(line + "");
//            }
//            is.close();
//
//            Log.e("sb.toString()", "--------------> " + sb.toString());
//            json = sb.toString();
//
//        } catch (Exception e) {
//            Log.e("Buffer Error", "Error converting result " + e.toString());
//        }
//
//        // return JSON String
//        return json;
//
//    }
//
//    public JSONObject getJSONFromUrlPost(String url) {
//        JSONObject jObj = null;
//        // Making HTTP request
//        try {
//            // defaultHttpClient
//            DefaultHttpClient httpClient = new DefaultHttpClient();
//            HttpPost httpGet = new HttpPost(url);
//
//            HttpResponse httpResponse = httpClient.execute(httpGet);
//            HttpEntity httpEntity = httpResponse.getEntity();
//            is = httpEntity.getContent();
//
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        } catch (ClientProtocolException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        try {
//            BufferedReader reader = new BufferedReader(new InputStreamReader(is, "iso-8859-1"), 8);
//            StringBuilder sb = new StringBuilder();
//            String line = null;
//            while ((line = reader.readLine()) != null) {
//                sb.append(line + "\n");
//            }
//            is.close();
//            json = sb.toString();
//            System.out.println("here 3");
//            Log.e("JSON", json);
//        } catch (Exception e) {
//            Log.e("Buffer Error", "Error converting result " + e.toString());
//        }
//
//        // try parse the string to a JSON object
//        try {
//            jObj = new JSONObject(json);
//        } catch (Exception e) {
//            Log.e("JSON Parser", "Error parsing data " + e.toString());
//        }
//
//        // return JSON String
//        return jObj;
//
//    }
//
//    public static JSONObject getJsonObjectFrom_Url(String url) {
//        JSONObject jObj = null;
//        try {
//            DefaultHttpClient httpClient = new DefaultHttpClient();
//            HttpGet httpGet = new HttpGet(url);
//            HttpResponse httpResponse = httpClient.execute(httpGet);
//            HttpEntity httpEntity = httpResponse.getEntity();
//            is = httpEntity.getContent();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        try {
//            BufferedReader reader = new BufferedReader(new InputStreamReader(is, "iso-8859-1"), 8);
//            StringBuilder sb = new StringBuilder();
//            String line = null;
//            while ((line = reader.readLine()) != null) {
//                sb.append(line + "\n");
//            }
//            is.close();
//            json = sb.toString();
//            System.out.println("here 3");
//            Log.e("JSON", json);
//        } catch (Exception e) {
//            Log.e("Buffer Error", "Error converting result " + e.toString());
//        }
//        try {
//            jObj = new JSONObject(json);
//        } catch (Exception e) {
//            Log.e("JSON Parser", "Error parsing data " + e.toString());
//        }
//        return jObj;
//    }
//
//    public static JSONObject getJSONFromUrlget(String url) {
//        JSONObject jObj = null;
//        // Making HTTP request
//
//        Log.e("URL---------->", "" + url);
//
//        try {
//            // defaultHttpClient
//            DefaultHttpClient httpClient = new DefaultHttpClient();
//            HttpGet httpGet = new HttpGet(url);
//            httpGet.setHeader("x-li-format", "json");
//            HttpResponse httpResponse = httpClient.execute(httpGet);
//            HttpEntity httpEntity = httpResponse.getEntity();
//            is = httpEntity.getContent();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        try {
//            BufferedReader reader = new BufferedReader(new InputStreamReader(is, "iso-8859-1"), 8);
//            StringBuilder sb = new StringBuilder();
//            String line = null;
//            while ((line = reader.readLine()) != null) {
//                sb.append(line + "\n");
//            }
//            is.close();
//            json = sb.toString();
//            System.out.println("here 3");
//            Log.e("JSON", json);
//        } catch (Exception e) {
//            Log.e("Buffer Error", "Error converting result " + e.toString());
//        }
//
//        // try parse the string to a JSON object
//        try {
//            jObj = new JSONObject(json);
//        } catch (Exception e) {
//            Log.e("JSON Parser", "Error parsing data " + e.toString());
//        }
//
//        // return JSON String
//        return jObj;
//
//    }
//
////	public static String Update_Profile(String Loc, String Mypro, String DOB, String Gender, String Email, String Category, String Interest, String Professionals, String FilePath,
////			String Profilepic_url) {
////		HttpClient _httpclient = null;
////		Bitmap bm = null;
////		try {
////
////			json = "";
////			responsecode = "";
////			_httpclient = new DefaultHttpClient();
////			MultipartEntity reqEntity = new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE);
////			HttpPost httpPost = new HttpPost(UserFunctions.update_profile);
////
////			// ByteArrayOutputStream bos = new ByteArrayOutputStream();
////			// // BitmapFactory.Options opts = new BitmapFactory.Options();
////			// // opts.inSampleSize = 4;
////			// // opts.inPurgeable = true;
////			// // opts.inInputShareable = true;
////			// Bitmap bm = BitmapFactory.decodeFile(filePath2);
////			// bm.compress(CompressFormat.JPEG, 100, bos);
////			//
////			// byte[] data = bos.toByteArray();
////			// ByteArrayBody bab = new ByteArrayBody(data, filePath2.substring(filePath2.lastIndexOf("/")));
////			// reqEntity.addPart("photo", bab);
////
////			try {
////				ByteArrayOutputStream bos = new ByteArrayOutputStream();
////				bm = BitmapFactory.decodeFile(FilePath);
////				bm.compress(CompressFormat.JPEG, 90, bos);
////				byte[] data = bos.toByteArray();
////				// ByteArrayBody bab = new ByteArrayBody(data, FilePath.substring(FilePath.lastIndexOf("/")));
////				String encodedImage = Base64.encodeToString(data, Base64.DEFAULT);
////				reqEntity.addPart("image", new StringBody(encodedImage));
////				// reqEntity.addPart("photo", bab);
////			} catch (Exception e1) {
////				// TODO Auto-generated catch block
////				e1.printStackTrace();
////			}
////
////			reqEntity.addPart("email", new StringBody(Email));
////			reqEntity.addPart("gender", new StringBody(URLEncoder.encode(Gender, "UTF-8")));
////			reqEntity.addPart("dob", new StringBody(URLEncoder.encode(DOB, "UTF-8")));
////			reqEntity.addPart("permanent_location", new StringBody(URLEncoder.encode(Loc, "UTF-8")));
////			reqEntity.addPart("my_profession", new StringBody(URLEncoder.encode(Mypro, "UTF-8")));
////			reqEntity.addPart("category", new StringBody(URLEncoder.encode(Category, "UTF-8")));
////			reqEntity.addPart("interests", new StringBody(URLEncoder.encode(Interest, "UTF-8")));
////			reqEntity.addPart("professsion_interest", new StringBody(URLEncoder.encode(Professionals, "UTF-8")));
////			reqEntity.addPart("image_url", new StringBody(URLEncoder.encode(Profilepic_url, "UTF-8")));
////
////			httpPost.setEntity(reqEntity);
////			HttpResponse httpResponse = _httpclient.execute(httpPost);
////
////			HttpEntity httpEntity = httpResponse.getEntity();
////			json = Get_Entity(httpEntity, httpResponse);
////
////			bm.recycle();
////			System.out.println("here 2");
////		} catch (Exception e) {
////			e.printStackTrace();
////		}
////
////		return json;
////
////	}
//
////	public static String UpdateSell_Event(String eventname, String description, String price, String tag1, String tag2, String tag3, String id, String userid, String eventid, String filePath2) {
////		// JSONObject jObj = null;
////		HttpClient _httpclient = null;
////		Bitmap bm = null;
////		// Making HTTP request
////		try {
////
////			json = "";
////			responsecode = "";
////			// defaultHttpClient
////			// trustAllHosts();
////			_httpclient = new DefaultHttpClient();
////			// _httpclient = ApplicationConstants.getNewHttpClient();
////			MultipartEntity reqEntity = new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE);
////
////			HttpPost httpPost = new HttpPost("url");
////
////			try {
////				ByteArrayOutputStream bos = new ByteArrayOutputStream();
////				bm = BitmapFactory.decodeFile(filePath2);
////				bm.compress(CompressFormat.JPEG, 70, bos);
////				byte[] data = bos.toByteArray();
////				// ByteArrayBody bab = new ByteArrayBody(data, filePath2.substring(filePath2.lastIndexOf("/")));
////				String encodedImage = Base64.encodeToString(data, Base64.DEFAULT);
////				reqEntity.addPart("photo", new StringBody(encodedImage));
////				// reqEntity.addPart("photo", bab);
////			} catch (Exception e1) {
////				// TODO Auto-generated catch block
////				e1.printStackTrace();
////			}
////
////			reqEntity.addPart("eventname", new StringBody(eventname));
////			reqEntity.addPart("description", new StringBody(description));
////			reqEntity.addPart("price", new StringBody(price));
////			reqEntity.addPart("tag1", new StringBody(tag1));
////			reqEntity.addPart("tag2", new StringBody(tag2));
////			reqEntity.addPart("tag3", new StringBody(tag3));
////			reqEntity.addPart("userid", new StringBody(id));
////			reqEntity.addPart("category_id", new StringBody(id));
////			reqEntity.addPart("eventid", new StringBody(eventid));
////
////			httpPost.setEntity(reqEntity);
////			HttpResponse httpResponse = _httpclient.execute(httpPost);
////
////			HttpEntity httpEntity = httpResponse.getEntity();
////			json = Get_Entity(httpEntity, httpResponse);
////
////			bm.recycle();
////			System.out.println("here 2");
////		} catch (Exception e) {
////			e.printStackTrace();
////		}
////
////		return json;
////
////	}
////
////	public static String Update_UserProfile(String userid, String fbuserid, String phone, String fname, String about, String filePath2) {
////		// JSONObject jObj = null;
////		HttpClient _httpclient = null;
////		Bitmap bm = null;
////		// Making HTTP request
////		try {
////
////			json = "";
////			responsecode = "";
////			// defaultHttpClient
////			// trustAllHosts();
////			_httpclient = new DefaultHttpClient();
////			MultipartEntity reqEntity = new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE);
////
////			HttpPost httpPost = new HttpPost("url");
////
////			try {
////
////				if (filePath2.equals("")) {
////					reqEntity.addPart("profilepic", new StringBody(""));
////				} else {
////					ByteArrayOutputStream bos = new ByteArrayOutputStream();
////					bm = BitmapFactory.decodeFile(filePath2);
////					bm.compress(CompressFormat.JPEG, 70, bos);
////					byte[] data = bos.toByteArray();
////					ByteArrayBody bab = new ByteArrayBody(data, filePath2.substring(filePath2.lastIndexOf("/")));
////					String encodedImage = Base64.encodeToString(data, Base64.DEFAULT);
////					reqEntity.addPart("profilepic", new StringBody(encodedImage));

////				}
////
////				// reqEntity.addPart("photo", bab);
////			} catch (Exception e1) {
////				// TODO Auto-generated catch block
////				e1.printStackTrace();
////			}
////
////			reqEntity.addPart("userid", new StringBody(userid));
////			reqEntity.addPart("fbuserid", new StringBody(fbuserid));
////			reqEntity.addPart("phone", new StringBody(phone));
////			reqEntity.addPart("fname", new StringBody(fname));
////			reqEntity.addPart("about", new StringBody(about));
////
////			httpPost.setEntity(reqEntity);
////			HttpResponse httpResponse = _httpclient.execute(httpPost);
////
////			HttpEntity httpEntity = httpResponse.getEntity();
////			json = Get_Entity(httpEntity, httpResponse);
////
////			try {
////				bm.recycle();
////			} catch (Exception e) {
////				// TODO Auto-generated catch block
////				e.printStackTrace();
////			}
////			System.out.println("here 2");
////		} catch (Exception e) {
////			e.printStackTrace();
////		}
////
////		return json;
////
////	}
////
////	public static String GetEvents(String cate_id) {
////		HttpClient _httpclient = null;
////		try {
////
////			json = "";
////			responsecode = "";
////			_httpclient = new DefaultHttpClient();
////			MultipartEntity reqEntity = new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE);
////
////			HttpPost httpPost = new HttpPost("url");
////
////			reqEntity.addPart("category_id", new StringBody(cate_id));
////
////			httpPost.setEntity(reqEntity);
////			HttpResponse httpResponse = _httpclient.execute(httpPost);
////
////			HttpEntity httpEntity = httpResponse.getEntity();
////			json = Get_Entity(httpEntity, httpResponse);
////			System.out.println("here 2");
////		} catch (Exception e) {
////			e.printStackTrace();
////		}
////
////		return json;
////
////	}
//
//    static String Get_Entity(HttpEntity httpEntity, HttpResponse httpResponse) {
//
//        String resultString = "";
//
//        if (httpEntity != null) {
//            // Read the content stream
//            try {
//                InputStream instream = httpEntity.getContent();
//                Header contentEncoding = httpResponse.getFirstHeader("Content-Encoding");
//                if (contentEncoding != null && contentEncoding.getValue().equalsIgnoreCase("gzip")) {
//                    instream = new GZIPInputStream(instream);
//                }
//
//                // convert content stream to a String
//                resultString = convertStreamToString(instream);
//                instream.close();
//                resultString = resultString.substring(0, resultString.length()); // remove wrapping "[" and "]"
//                Log.e("httpPost resultString", "--->" + resultString);
//            } catch (Exception e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            }
//
//        }
//        return resultString;
//    }


}
