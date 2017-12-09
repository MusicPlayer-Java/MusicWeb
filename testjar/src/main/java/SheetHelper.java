import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.GetMethod;
import org.json.JSONArray;
import org.json.JSONObject;

import ouc.cs.course.java.model.MusicSheet;
import ouc.cs.course.java.musicclient.MusicOperationClient;
import ouc.cs.course.java.httpclient.MusicSheetTaker;


public class SheetHelper {
	
	// 获取全部本地歌单信息
	public static ArrayList getAll()
	{
		SqlHelper.getConnection();
		String sql = "select * from Sheet;";
		List ls = SqlHelper.select(sql);
		SqlHelper.closeConnection();
		ArrayList sheets = new ArrayList();
		Iterator it = ls.iterator();   
		while(it.hasNext()) {   
		    Map hm = (Map)it.next();
		    Sheet mySheet = new Sheet(hm);
		    sheets.add(mySheet);
		} 		
		return sheets;
	}
	
	// 根据歌单ID获取歌单信息
	public static Sheet getSheet(String id)
	{
		SqlHelper.getConnection();
		String sql = "select * from Sheet where SheetId = '" + id + "';";
		List ls = SqlHelper.select(sql);
		SqlHelper.closeConnection();
		Map hm = (Map)ls.get(0);
		Sheet mySheet = new Sheet(hm);
		return mySheet;
	}
	
	// 根据歌单ID获取歌单中全部歌曲信息
	public static ArrayList getAllSongs(String id)
	{
		SqlHelper.getConnection();
		String sql = "select * from Music where SheetId = '" + id + "';";
		List ls = SqlHelper.select(sql);
		SqlHelper.closeConnection();
		ArrayList songs = new ArrayList();
		Iterator it = ls.iterator();   
		while(it.hasNext()) {   
		    Map hm = (Map)it.next();
		    Music myMusic = new Music(hm);
		    songs.add(myMusic);
		} 		
		return songs;
	}
	
	// 获取服务器端所有歌单
	public static List getServerSheets() throws HttpException, IOException
	{
		String url = "http://service.uspacex.com/music.server/queryMusicSheets?type=all";
		int port = 80;
		HttpClient client = new HttpClient();
		client.getHostConfiguration().setHost(url, port);
		GetMethod method = new GetMethod(url);
		client.executeMethod(method);
		InputStream bodystreams = method.getResponseBodyAsStream();
		JSONObject jsonBody = new JSONObject(MusicSheetTaker.convertStreamToString(bodystreams));
		JSONArray jsonMusicSheetList = (JSONArray) jsonBody.get("musicSheetList");
		JSONObject jms = null;
		List mss = new ArrayList();
		MusicSheet ms = null;		
		for (int i = 0; i < jsonMusicSheetList.length(); i++) {
			Map mum = new HashMap();
			Object musicSheetObj = jsonMusicSheetList.get(i);
			jms = new JSONObject(musicSheetObj.toString());
			ms = new MusicSheet();
			ms.setUuid((String) jms.get("uuid"));
			ms.setName((String) jms.get("name"));
			ms.setCreator((String) jms.get("creator"));
			if(jms.get("creatorId") == null)
				ms.setCreatorId(null);
			else
			 ms.setCreatorId(jms.get("creatorId").toString());
			ms.setDateCreated((String) jms.get("dateCreated"));
			if(jms.get("picture") == null)
				ms.setPicture(null);
			else
				ms.setPicture(jms.get("picture").toString());
			String mi = jms.get("musicItems").toString();
			if(mi.length() != 0) {
				mi = mi.substring(0, mi.length()-1);
				String[] musics = mi.split(",");
				for(int j = 0; j < musics.length; j++){
					String[] items = musics[j].split(":");	
					if(items.length > 1) {
						int index1 = items[0].indexOf('"');
						int index2 = items[0].lastIndexOf('"');
						int size = items[1].length();
						int index3 = items[1].lastIndexOf('"');
						if(items[1].indexOf('.') != -1)
							mum.put(items[0].substring(index1, index2), items[1].substring(1, size-5));
						else
							mum.put(items[0].substring(index1, index2), items[1].substring(1));
					}				
				}					
				ms.setMusicItems(mum);
				mss.add(ms);
			}			
		}
		method.releaseConnection();
		return mss;
	}
	
	// 根据MD5值下载对应的歌单封面图片并返回路径
	public static String getPicture(String md5, String pictureName)
	{
		MusicOperationClient moc = new MusicOperationClient();
		moc.downloadMusicSheetPicture(md5, "./images");
		String path = System.getProperty("user.dir").toString().replace('\\', '/') + "/images/" + pictureName;
		return path;
	}
	
	// 创建歌单
	public static void createSheet(String name, String imagePath, String creator, String creatorId)
	{
		Sheet mySheet = new Sheet();
		mySheet.setName(name);
		File f = new File(imagePath);
		String imageName = f.getName();
		String newPath = System.getProperty("user.dir").toString().replace('\\', '/') + "/images/" + imageName;
		FileHelper.copyFile(imagePath, newPath);
		imagePath = "/images/" + imageName;
		mySheet.setPicture(imagePath);
		SqlHelper.getConnection();
		String sql = "insert into Sheet values('" + mySheet.getUuid() + "','" + mySheet.getName() + "','" + mySheet.getDateCreated() + "','" + creatorId + "','" + creator + "','" + mySheet.getPicture() + "')";
		SqlHelper.update(sql);		
		SqlHelper.closeConnection();
	}
}
