
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.commons.httpclient.HttpException;

import ouc.cs.course.java.model.MusicSheet;

public class main {
	public static void main(String[] args) throws HttpException, IOException{
		/*List sheets = SheetHelper.getServerSheets();
		for(int i = 0; i < sheets.size(); i++) {
			MusicSheet ms = (MusicSheet)sheets.get(i);
			System.out.println("歌单名称：" + ms.getName());
			
			Map map = ms.getMusicItems();
			System.out.println(map.values().size());
			String[] s = new String[map.values().size()];
			map.values().toArray(s);
			for(int j = 0; j < s.length; j++){
				System.out.println(s[j]);
			}
		}*/
		
		
		//"create table if not exists Music(MusicId INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, Name VARCHAR(30) NOT NULL, Singer VARCHAR(30) NOT NULL, SheetId INT NOT NULL, MusicMd5 VARCHAR(40) NOT NULL, MusicPath VARCHAR(100) NOT NULL, Time VARCHAR(10) NOT NULL);" );
		
		View view = new View();
		
		view.init();
		
		SheetHelper.deleteSheet("f4d920b2e0f24a29bc637d2be5655ee9");
		System.out.println("ok");
		
		}

}
