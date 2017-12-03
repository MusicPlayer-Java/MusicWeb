import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
class MP3Player {
	private String filename;

    private Player player = null;
    
	private BufferedInputStream buffer = null;
	
	public MP3Player(String filename) throws FileNotFoundException {

        this.filename = filename;

        buffer = new BufferedInputStream(new FileInputStream(filename));
        try {
			player = new Player(buffer);
		} catch (JavaLayerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

	

    public void play() {

        try {
        	
            player.play();

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void stop() {
        try {

            player.close();

        } catch (Exception e) {

            System.out.println(e);

        }
    }
    
   //MP3Player mp3 = new MP3Player("³ÂÈð - °×ºü.mp3");



    

}

