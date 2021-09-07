package com.kenez92.client.sound;

import com.google.gwt.media.client.Audio;

import static com.kenez92.client.settings.SoundSettings.SOUND_COLLISION_BORDER_PATH;
import static com.kenez92.client.settings.SoundSettings.SOUND_COLLISION_BRICK_PATH;
import static com.kenez92.client.settings.SoundSettings.SOUND_COLLISION_RACKET_PATH;
import static com.kenez92.client.settings.SoundSettings.SOUND_GAME_OVER_PATH;
import static com.kenez92.client.settings.SoundSettings.SOUND_GAME_WIN_PATH;
import static com.kenez92.client.settings.SoundSettings.SOUND_LIFE_LOST_PATH;

public class SoundService {
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
                return SOUND_GAME_WIN_PATH;
            case GAME_LOST:
                return SOUND_GAME_OVER_PATH;
            case COLLISION_BORDER:
                return SOUND_COLLISION_BORDER_PATH;
            case COLLISION_RACKET:
                return SOUND_COLLISION_RACKET_PATH;
            case COLLISION_BRICK:
                return SOUND_COLLISION_BRICK_PATH;
            case LIFE_LOST:
                return SOUND_LIFE_LOST_PATH;
        }
        return null;
    }
}
