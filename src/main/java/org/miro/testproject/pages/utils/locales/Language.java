package org.miro.testproject.pages.utils.locales;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public enum Language {
    ENGLISH("en", "English"),
    GERMAN("de", "Deutsch"),
    DUTCH("nl", "Dutch"),
    SPANISH("es", "Español"),
    FRENCH("fr", "Français"),
    ITALIAN("it", "Italiano"),
    PORTUGUESE("pt", "Português"),
    JAPANESE("ja", "日本語");

    public final String code;
    public final String label;
    public static final List<String> codes = new ArrayList<>();

    Language(String code, String label) {
        this.code = code;
        this.label = label;
    }

    static {
        for (Language l : values()) {
            codes.add(l.code);
        }
    }

    @Override
    public String toString() {
        return this.name().toLowerCase(Locale.ROOT);
    }
}
