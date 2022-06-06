package fifa;

import java.net.URL;
import java.util.HashMap;
import java.util.Random;

import javafx.scene.media.AudioClip;

public class Sound {

    private static final double MUSIC_VOLUME = 0.20;

    private static AudioClip menu = null;

    private static AudioClip kick = null;

    private static boolean correctlyLoadedMenu = false;
    private static boolean correctlyLoadedKick = false;
    private static boolean correctlyLoadedComm = false;
    private static boolean correctlyLoadedTrck = false;
    
    // The music and commentary are optional
    // and will be downloaded by a shell script

    private final static int NUMBER_OF_COM_ENTRIES = 37;
    private static HashMap<Integer, AudioClip> commentary = new HashMap<Integer, AudioClip>();
    
    private final static int NUMBER_OF_TRACK_ENTRIES = 9;
    private static HashMap<Integer, AudioClip> tracks = new HashMap<Integer, AudioClip>();
    private static int currentTrackInd = 5;
    private static AudioClip currentTrack = null;

    public Sound() {
        loadMenuSound();
        loadKickSound();
        loadCommentary();
        loadTracks();

        if(!correctlyLoadedMenu)
            System.out.println("Could not locate the menu sound. Check README.md for download info!");
        if(!correctlyLoadedKick)
            System.out.println("Could not locate the kick sound. Check README.md for download info!");
        if(!correctlyLoadedComm)
            System.out.println("Could not load commentary. Check README.md for download info!");
        if(!correctlyLoadedTrck)
            System.out.println("Could not load game music. Check README.md for download info!");
    }

    public static void menuPlay() {
        if(correctlyLoadedMenu)
            menu.play(MUSIC_VOLUME);
    }

    public static void menuStop() {
        if(correctlyLoadedMenu)
            menu.stop();
    }

    public static void kick(double volume) {
        if (!kick.isPlaying() && correctlyLoadedKick)
            kick.play(volume, 0.5, 1, 0.5, 0);
    }

    public static void commentary() {
        if (correctlyLoadedComm) {
            int r = new Random().nextInt(NUMBER_OF_COM_ENTRIES);
            commentary.get(r).play(0.5);
        }
    }

    public static void music() {
        if(!correctlyLoadedTrck)
            return;
        if (currentTrack != null && currentTrack.isPlaying())
            return;
            
        currentTrack = tracks.get(currentTrackInd);
        currentTrack.play(MUSIC_VOLUME);
        
        int newIndex = new Random().nextInt(NUMBER_OF_TRACK_ENTRIES);
        while(currentTrackInd == newIndex)
            newIndex = new Random().nextInt(NUMBER_OF_TRACK_ENTRIES);
        currentTrackInd = newIndex;
        }

    // --------- Loading sounds ---------

    private void loadMenuSound() {
        URL res = getClass().getResource("/sound/menu/menu_music.wav");

        if(res == null)
            return;

        String menuSource = res.toString();

        if (menuSource != null) {            
            menu = new AudioClip(menuSource);
            correctlyLoadedMenu = true;
        }
    }

    private void loadKickSound() {
        URL res = getClass().getResource("/sound/kick.wav");

        if(res == null)
            return;

        String kickSource = res.toString();

        if (kickSource != null) {            
            kick = new AudioClip(kickSource);
            correctlyLoadedKick = true;
        }
    }

    private void loadCommentary() {
        
        for(int i = 0; i < NUMBER_OF_COM_ENTRIES; i++) {
            String filename = String.format("/sound/com/COM%d.wav", i + 1);
            
            URL res = getClass().getResource(filename);
            if(res == null)
                return;

            String commSource = res.toString();

            if (commSource == null)
                return;
            
            commentary.put(i, new AudioClip(commSource)); 
        }
        correctlyLoadedComm = true;
    }

    private void loadTracks() {
        
        for(int i = 0; i < NUMBER_OF_TRACK_ENTRIES; i++) {
            String filename = String.format("/sound/music/TRACK%d.wav", i + 1);
            
            URL res = getClass().getResource(filename);
            if(res == null)
                return;

            String trackSource = res.toString();

            if (trackSource == null)
                return;
            
            tracks.put(i, new AudioClip(trackSource)); 
        }
        correctlyLoadedTrck = true;
    }
}
