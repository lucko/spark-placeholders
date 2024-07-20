package me.lucko.spark.placeholders;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import me.lucko.spark.api.SparkProvider;
import me.lucko.spark.api.placeholder.PlaceholderResolver;
import org.bukkit.entity.Player;

public class SparkPlaceholderExpansion extends PlaceholderExpansion {
    private static final String IDENTIFIER = "spark";
    private static final String AUTHOR = "Luck";
    private static final String VERSION = "1.0";

    private PlaceholderResolver resolver;

    @Override
    @SuppressWarnings("ResultOfMethodCallIgnored")
    public boolean canRegister() {
        try {
            SparkProvider.get();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean register() {
        if (!canRegister()) {
            return false;
        }

        this.resolver = SparkProvider.get().placeholders();
        return super.register();
    }

    @Override
    public String onPlaceholderRequest(Player player, String identifier) {
        return this.resolver.resolveLegacyFormatting(identifier);
    }

    @Override
    public String getIdentifier() {
        return IDENTIFIER;
    }

    @Override
    public String getAuthor() {
        return AUTHOR;
    }

    @Override
    public String getVersion() {
        return VERSION;
    }

}
