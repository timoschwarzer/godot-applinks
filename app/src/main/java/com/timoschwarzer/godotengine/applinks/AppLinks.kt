package com.timoschwarzer.godotengine.applinks

import org.godotengine.godot.Godot
import org.godotengine.godot.plugin.GodotPlugin

class AppLinks(godot: Godot?) : GodotPlugin(godot) {
    override fun getPluginName(): String {
        return "AppLinks"
    }

    fun getUrl(): String? {
        return Godot.getCurrentIntent()?.dataString
    }

    override fun getPluginMethods(): MutableList<String> {
        return mutableListOf("getUrl")
    }
}