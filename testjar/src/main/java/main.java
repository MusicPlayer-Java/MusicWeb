
import java.io.IOException;
import org.apache.commons.httpclient.HttpException;

public class main {
	public static void main(String[] args) throws HttpException, IOException{
		/*List sheets = SheetHelper.getServerSheets();
		MusicSheet ms = (MusicSheet)sheets.get(0);
		System.out.println(SheetHelper.getPicture(ms.getUuid(), ms.getPicture()));
		Map map = ms.getMusicItems();
		String[] s = new String[map.values().size()];
		map.values().toArray(s);
		for(int i = 0; i < s.length; i++){
			System.out.println(s[i]);
		}*/
		//"create table if not exists Music(MusicId INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, Name VARCHAR(30) NOT NULL, Singer VARCHAR(30) NOT NULL, SheetId INT NOT NULL, MusicMd5 VARCHAR(40) NOT NULL, MusicPath VARCHAR(100) NOT NULL, Time VARCHAR(10) NOT NULL);" );
		
		View view = new View();
		
		view.init();
		
		
		}

}
