package fifa;

import javafx.scene.media.AudioClip;

public class Sound {

    private static AudioClip kick = null;

    boolean correctlyLoadedSounds = true;


    Sound() {
        // Check if sounds are present (they are optional and will be downloaded by a shell script)
        
        
        loadKickSound();
    }

    private void loadKickSound() {
        String kickSource = getClass().getResource("/sound/kick.wav").toString();

        if(kickSource == null) {
            correctlyLoadedSounds = false;
            return;
        }

        kick = new AudioClip(kickSource);
    }

    public static void kick(double volume) {
        if(!kick.isPlaying())
            kick.play(volume, 0.5, 1, 0.5, 0);
    }
}
