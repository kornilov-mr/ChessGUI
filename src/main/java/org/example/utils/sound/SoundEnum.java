package org.example.utils.sound;

import javax.sound.sampled.*;
import java.io.File;

public enum SoundEnum {
    CHECK_SOUND(new File("src/main/resources/sounds/CheckSound.wav")),
    PIECE_PLAYED_SOUND(new File("src/main/resources/sounds/PiecePlayed.wav"));
    private final Clip clip;
    SoundEnum(File file) {
        if(!file.exists()) throw new RuntimeException("file"+file.getAbsolutePath()+" does not exist");
        this.clip=SoundConverter.convertClipToSupportedFormat(file);
    }

    public Clip getClip() {
        return clip;
    }
}
