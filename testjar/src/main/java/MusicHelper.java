import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.io.IOUtils;

import ouc.cs.course.java.musicclient.MusicOperationClient;


public class MusicHelper {
	
	// 根据歌曲ID获取歌曲信息
	public static Music getMusic(int id)
	{
		SqlHelper.getConnection();
		String sql = "select * from Music where MusicId = '" + id + "';";
		List ls = SqlHelper.select(sql);
		SqlHelper.closeConnection();
		Map hm = (Map)ls.get(0);
		Music myMusic = new Music(hm);
		return myMusic;
	}
	
	//添加歌曲到歌单
	public static void AddMusicToSheet(String path, String uuid, String singer) {		
		try {
			SqlHelper.getConnection();
			String md5 = DigestUtils.md5Hex(IOUtils.toByteArray(new FileInputStream(path)));			
			File music = new File(path);	
			String name = music.getName();
			String newPath = System.getProperty("user.dir").toString().replace('\\', '/') + "/music/" + name;
			FileHelper.copyFile(path, newPath);
			path = "/music/" + name;
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String time = formatter.format(new Date());
			String sql = "insert into Music values(null,'" + name + "','" + singer + "','" + uuid + "','" + md5 + "','" + path + "','" + time + "')";
			SqlHelper.update(sql);
			SqlHelper.closeConnection();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	// 根据md5值下载歌曲并返回路径
	public static String downloadMusic(String md5)
	{
		HttpClient client = new HttpClient();
		GetMethod get = null;
		FileOutputStream output = null;
		String filename = null;
		int SUCCESS = 200;
		String url = "http://service.uspacex.com/music.server/downloadMusic";
		String targetPath = "./music/";

		try {
			get = new GetMethod(url + "?md5=" + md5);
			int i = client.executeMethod(get);

			if (SUCCESS == i) {
				filename = java.net.URLDecoder
				.decode(get.getResponseHeader("Content-Disposition").getValue().substring(21), "UTF-8");
				System.out.println("[The file name getting from HTTP HEADER] " + filename);

				File storeFile = new File(targetPath + "/" + filename);
				output = new FileOutputStream(storeFile);
				output.write(get.getResponseBody());
			} else {
				System.out.println("DownLoad file failed with error code: " + i);
			}
			String path = System.getProperty("user.dir").toString().replace('\\', '/') + "/music/" + filename;
			return path;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			try {
				if (output != null) {
					output.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

			get.releaseConnection();
			client.getHttpConnectionManager().closeIdleConnections(0);
		}
	}

}
