package org.example.utils.sound;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;


/**
 * Converts sound to right format to use in application
 */
public class SoundConverter {
    public static Clip convertClipToSupportedFormat(File soundFile){
        Clip clip;
        try {
            AudioInputStream originalStream = AudioSystem.getAudioInputStream(soundFile);

            AudioFormat originalFormat = originalStream.getFormat();
            AudioFormat targetFormat = new AudioFormat(
                    AudioFormat.Encoding.PCM_SIGNED,
                    originalFormat.getSampleRate(),
                    16,
                    originalFormat.getChannels(),
                    originalFormat.getChannels() * 2,
                    originalFormat.getSampleRate(),
                    false
            );

            AudioInputStream convertedStream = AudioSystem.getAudioInputStream(targetFormat, originalStream);

            clip = AudioSystem.getClip();
            clip.open(convertedStream);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            throw new RuntimeException("failed to convert sound Clip",e);
        }
        return clip;
    }
}
