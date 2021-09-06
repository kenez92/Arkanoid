package com.kenez92.client.utils;

import com.google.gwt.media.client.Audio;
import com.kenez92.client.enums.SoundEffect;

public class SoundUtil {
    public static void sound(SoundEffect effect) {
        String soundPath = getSoundPath(effect);
        if (soundPath != null) {
            Audio makeAudio;
            makeAudio = Audio.createIfSupported();
            makeAudio.setSrc(soundPath);
            makeAudio.play();
        }
    }

    private static String getSoundPath(SoundEffect effect) {
        switch (effect) {
            case GAME_WIN:
                return Consts.SOUND_GAME_WIN_PATH;
            case GAME_LOST:
                return Consts.SOUND_GAME_OVER_PATH;
            case COLLISION_BORDER:
                return Consts.SOUND_COLLISION_BORDER_PATH;
            case COLLISION_RACKET:
                return Consts.SOUND_COLLISION_RACKET_PATH;
            case COLLISION_BRICK:
                return Consts.SOUND_COLLISION_BRICK_PATH;
            case LIFE_LOST:
                return Consts.SOUND_LIFE_LOST_PATH;
        }
        return null;
    }
}
