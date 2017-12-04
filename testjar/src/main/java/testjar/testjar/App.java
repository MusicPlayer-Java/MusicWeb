package testjar.testjar;

import ouc.cs.course.java.model.MusicSheet;
import ouc.cs.course.java.musicclient.MusicOperationClient;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	/*
		 * 鍒涘缓MusicOperationClient瀵硅薄
		 */
		MusicOperationClient moc = new MusicOperationClient();

		/*
		 * 闊充箰鍗曞強闊充箰涓婁紶娴嬭瘯锛屽垱寤篗usicSheet瀵硅薄鏃讹紝灏嗗叾uuid璁剧疆涓烘湇鍔″櫒宸茬粡瀛樺湪鐨勶紝浼氭洿鏂拌闊充箰鍗�
		 */
		/*
		List<String> filePaths = new ArrayList<String>();

		filePaths.add("/Users/xiaodong/Music/Rock_Chinese/榛戣惫 - 鍒幓绯熻箣.mp3");
		filePaths.add("/Users/xiaodong/Music/Rock_Chinese/榛戣惫 - 鏃犲湴鑷.mp3");

		MusicSheet ms = new MusicSheet();
		ms.setCreatorId("2011022");
		ms.setPicture("/Users/xiaodong/Music/Rock_Chinese/fig-heibao-band.jpg");
		ms.setCreator("鐜嬫檽涓�");
		ms.setName("鏃犲湴鑷鐨勬垜");

		moc.createMusicSheetAndUploadFiles(ms, filePaths);
		*/
		
		/*
		 * 鑾峰彇鎵�鏈夐煶涔愬崟娴嬭瘯
		 *///MusicSheet musicSheet : moc.queryAllMusicSheets()
		/*for (int i = 0; i < moc.queryAllMusicSheets().size(); i++) {
			MusicSheet musicSheet = (MusicSheet)moc.queryAllMusicSheets().get(i);
			String name = musicSheet.getName();
			System.out.println("123");
		}*

		/*
		 * 涓嬭浇uuid涓篹a601e51fd4346aa85d9c239366e8a29鐨勯煶涔愬崟灏侀潰鍥剧墖娴嬭瘯
		 */
		moc.downloadMusicSheetPicture("ea601e51fd4346aa85d9c239366e8a29", "/Users/xiaodong/Desktop");

		/*
		 * 涓嬭浇md5涓�332d4b51d6b7b410bf829df2654c5c0e鐨勯煶涔愭枃浠舵祴璇�
		 */
		//moc.downloadMusicFile("332d4b51d6b7b410bf829df2654c5c0e", "/Users/xiaodong/Desktop");
    }
}
