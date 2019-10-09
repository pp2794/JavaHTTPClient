package com.piy.javaHTTPClient.client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import com.piy.javaHTTPClient.models.Shipwreck;
import com.piy.javaHTTPClient.utils.PojoToJson;

public class MyHTTPClient {
	
	String url="";
	final String USER_AGENT = "Mozilla/5.0";
	
	public MyHTTPClient(String url) {
		this.url=url;
	}
	
	public void sendGet() throws IOException {
		
		URL urlOb = new URL(url);
		
		HttpURLConnection conn = (HttpURLConnection)urlOb.openConnection();
		conn.setRequestProperty("User-agent", USER_AGENT);
		conn.setRequestMethod("GET");
		
		System.out.println("Response code= "+conn.getResponseCode());
		
		BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		
		StringBuffer sb = new StringBuffer();
		String line="";
		
		while((line = br.readLine()) != null) {
			sb.append(line);
		}
		
		conn.disconnect();
		br.close();
		
		System.out.println("Response is:");
		System.out.println(sb);
		
		
	}
	
	public void sendPost(Shipwreck wreck) throws IOException {
		
		URL urlObj = new URL(url);
		HttpURLConnection conn = (HttpURLConnection)urlObj.openConnection();
		
		conn.setRequestMethod("POST");
		conn.setRequestProperty("User-agent", USER_AGENT);
		conn.setRequestProperty("Content-type", "application/json");
		//conn.setRequestProperty("Accept", "application/json");
		conn.setDoOutput(true);
		
		
		String json = new PojoToJson().convertPojoToJson(wreck);
		
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
		bw.write(json);
		bw.close();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		String line="";
		StringBuffer sb = new StringBuffer();
		
		while((line = br.readLine()) != null) {
			sb.append(line);
		}
		
		System.out.println("POSTed. Got the following response:");
		System.out.println(sb);
		
		conn.disconnect();
		br.close();
		
	}
	
	public static void main(String[] args) throws IOException {
		
		MyHTTPClient client = new MyHTTPClient("http://localhost:8080/api/v1/shipwrecks/2");
		
		//Shipwreck wreck = new Shipwreck((long)4, "name1", "desc.", "cond.", 34, 12.0, 13.0, 1992);
		
		//client.sendPost(wreck);

		client.sendGet();
		
	}
	
}
