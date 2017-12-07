import java.util.Map;

public class Music {
	private int id;
	private String name;
	private String singer;
<<<<<<< HEAD
	private int sheet_id;
=======
	private String sheet_id;
>>>>>>> b8cc915a28c37c9c1dd25037c87d5b8ca2ada296
	private String md5;
	private String path;
	private String time;
	
	private static String root = System.getProperty("user.dir").toString().replace('\\', '/');
	
	public Music(Map hm)
	{
		id = Integer.parseInt(hm.get("MusicId").toString());
		name = hm.get("Name").toString();
		singer = hm.get("Singer").toString();
		//sheet_id = Integer.parseInt(hm.get("SheetId").toString());
		sheet_id =hm.get("SheetId").toString();
		md5 = hm.get("MusicMd5").toString();
		path = root + hm.get("MusicPath").toString();		
		time = hm.get("Time").toString();
	}
	
	public int getId()
	{
		return id;
	}
	
	public String getName()
	{
		return name;
	}
	
	public String getSinger()
	{
		return singer;
	}
	
	public String getSheetId()
	{
		return sheet_id;
	}
	
	public String getMd5()
	{
		return md5;
	}
	
	public String getPath()
	{
		return path;
	}
	
	public String getTime()
	{
		return time;
	}

}
