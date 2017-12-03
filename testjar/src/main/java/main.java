import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.json.JSONArray;
import org.json.JSONObject;

import ouc.cs.course.java.model.MusicSheet;

//import ouc.cs.course.java.model.MusicSheet;
//import ouc.cs.course.java.musicclient.MusicOperationClient;

public class main {
	public static void main(String[] args) throws HttpException, IOException{
		List sheets = SheetHelper.getServerSheets();
		MusicSheet ms = (MusicSheet)sheets.get(0);
		System.out.println(SheetHelper.getPicture(ms.getUuid(), ms.getPicture()));
		Map map = ms.getMusicItems();
		String[] s = new String[map.values().size()];
		map.values().toArray(s);
		for(int i = 0; i < s.length; i++){
			System.out.println(s[i]);
		}
	}

}
