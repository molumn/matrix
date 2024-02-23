package io.github.molumn.matrix.plugin

import org.bukkit.plugin.java.JavaPlugin

class MatrixTestPlugin : JavaPlugin() {

    override fun onEnable() {
        server.logger.info("Matrix Test Plugin Enabled!")
    }

    override fun onDisable() {
        server.logger.info("Matrix Test Plugin Disabled!")
    }

}
