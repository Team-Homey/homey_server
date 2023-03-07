package com.homey.homeyserver.domain.enums;

public enum Emotion {
    EXCITED("EXCITED"),
    HAPPY("HAPPY"),
    SAD("SAD"),
    ANGRY("ANGRY"),
    LOVELY("LOVELY"),
    SOSO("SOSO"),
    UNKNOWN("UNKNOWN");

    String emotion;
    Emotion(String emotion) {
        this.emotion = emotion;
    }
}
