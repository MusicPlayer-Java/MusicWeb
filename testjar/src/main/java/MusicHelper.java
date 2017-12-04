import java.util.List;
import java.util.Map;


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

}
