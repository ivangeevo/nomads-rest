package org.btwr.nomads_rest.config;

import org.btwr.nomads_rest.Constants;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

final class TomlFile {
    private final Map<String, String> values = new HashMap<>();

    private TomlFile() {}

    /** Reads a file. A missing or unreadable one gives no values, so every setting keeps its fallback. */
    static TomlFile read(Path file) {
        TomlFile toml = new TomlFile();

        if (!Files.exists(file)) return toml;

        try {
            for (String line : Files.readAllLines(file, StandardCharsets.UTF_8)) {
                int comment = line.indexOf('#');
                String content = comment >= 0 ? line.substring(0, comment) : line;
                int separator = content.indexOf('=');

                if (separator > 0) {
                    toml.values.put(content.substring(0, separator).trim(), content.substring(separator + 1).trim());
                }
            }
        } catch (IOException e) {
            Constants.LOG.warn("Could not read {}, using default settings", file, e);
        }

        return toml;
    }

    boolean getBoolean(String key, boolean fallback) {
        String value = this.values.get(key);

        if (value == null) return fallback;
        if (value.equalsIgnoreCase("true")) return true;
        if (value.equalsIgnoreCase("false")) return false;

        warnInvalid(key, value, fallback);

        return fallback;
    }

    int getInt(String key, int fallback, int min, int max) {
        String value = this.values.get(key);

        if (value == null) return fallback;

        try {
            return Math.clamp(Integer.parseInt(value), min, max);
        } catch (NumberFormatException e) {
            warnInvalid(key, value, fallback);
            return fallback;
        }
    }

    double getDouble(String key, double fallback, double min, double max) {
        String value = this.values.get(key);

        if (value == null) return fallback;

        try {
            return Math.clamp(Double.parseDouble(value), min, max);
        } catch (NumberFormatException e) {
            warnInvalid(key, value, fallback);
            return fallback;
        }
    }

    List<String> getStringList(String key, Collection<String> fallback) {
        String value = this.values.get(key);

        if (value == null) return List.copyOf(fallback);

        if (!value.startsWith("[") || !value.endsWith("]")) {
            warnInvalid(key, value, fallback);
            return List.copyOf(fallback);
        }

        List<String> list = new ArrayList<>();
        for (String entry : value.substring(1, value.length() - 1).split(",")) {
            String trimmed = entry.trim().replace("\"", "");
            if (!trimmed.isEmpty()) list.add(trimmed);
        }

        return list;
    }

    private static void warnInvalid(String key, String value, Object fallback) {
        Constants.LOG.warn("Invalid value '{}' for config setting {}, using {}", value, key, fallback);
    }

    /** Builds the text of a config file, one commented setting at a time. */
    static final class Writer {
        private final List<String> lines = new ArrayList<>();

        Writer(String header) {
            this.lines.add("# " + header);
        }

        Writer add(String key, Object value, String... comments) {
            this.lines.add("");

            for (String comment : comments) {
                this.lines.add("# " + comment);
            }

            this.lines.add(key + " = " + format(value));

            return this;
        }

        void save(Path file) {
            try {
                Files.createDirectories(file.getParent());
                Files.write(file, this.lines, StandardCharsets.UTF_8);
            } catch (IOException e) {
                Constants.LOG.warn("Could not write {}", file, e);
            }
        }

        private static String format(Object value) {
            if (value instanceof Collection<?> list) {
                return list.stream()
                        .map(entry -> "\"" + entry + "\"")
                        .collect(Collectors.joining(", ", "[", "]"));
            }

            return String.valueOf(value);
        }
    }
}