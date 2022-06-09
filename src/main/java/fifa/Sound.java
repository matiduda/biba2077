package fifa;

import java.net.URL;
import java.util.HashMap;
import java.util.Random;

import javafx.scene.media.AudioClip;

public class Sound {

    private final double KICK_VOLUME = 0.7;

    private final double MUSIC_VOLUME = 0.20;

    private AudioClip menu = null;

    private AudioClip kick = null;

    private boolean correctlyLoadedMenu = false;
    private boolean correctlyLoadedKick = false;
    private boolean correctlyLoadedComm = false;
    private boolean correctlyLoadedTrck = false;
    
    // The music and commentary are optional
    // and will be downloaded by a shell script

    private final int NUMBER_OF_COM_ENTRIES = 37;
    private HashMap<Integer, AudioClip> commentary = new HashMap<Integer, AudioClip>();
    
    private final int NUMBER_OF_TRACK_ENTRIES = 9;
    private HashMap<Integer, AudioClip> tracks = new HashMap<Integer, AudioClip>();
    private int currentTrackInd = 5;
    private AudioClip currentTrack = null;

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

    public void menuPlay() {
        if(correctlyLoadedMenu)
            menu.play(MUSIC_VOLUME);
    }

    public void menuStop() {
        if(correctlyLoadedMenu)
            menu.stop();
    }

    public void kick() {
        if (!kick.isPlaying() && correctlyLoadedKick)
            kick.play(KICK_VOLUME, 0.5, 1, 0.5, 0);
    }

    public void commentary() {
        if (correctlyLoadedComm) {
            int r = new Random().nextInt(NUMBER_OF_COM_ENTRIES);
            commentary.get(r).play(0.5);
        }
    }

    public void music() {
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
        URL res = getClass().getResource("/sound/menu/menu_music.mp3");

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
