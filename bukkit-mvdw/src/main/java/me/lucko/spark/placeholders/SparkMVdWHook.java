package me.lucko.spark.placeholders;

import be.maximvdw.placeholderapi.PlaceholderAPI;
import be.maximvdw.placeholderapi.PlaceholderReplaceEvent;
import be.maximvdw.placeholderapi.PlaceholderReplacer;
import me.lucko.spark.api.SparkProvider;
import me.lucko.spark.api.placeholder.PlaceholderResolver;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Locale;

public class SparkMVdWHook extends JavaPlugin implements PlaceholderReplacer {
    private PlaceholderResolver provider;

    @Override
    public void onEnable() {
        this.provider = SparkProvider.get().placeholders();
        PlaceholderAPI.registerPlaceholder(this, "spark_*", this);
    }

    @Override
    public String onPlaceholderReplace(PlaceholderReplaceEvent event) {
        String placeholder = event.getPlaceholder();
        if (!placeholder.startsWith("spark_")) {
            return null;
        }

        String identifier = placeholder.substring("spark_".length()).toLowerCase(Locale.ROOT);
        return this.provider.resolveLegacyFormatting(identifier);
    }

}
