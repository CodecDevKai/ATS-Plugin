package dev.k41j0.systems;

import dev.k41j0.ApesTogetherStrong;

public class DebugSystem {
    private static ApesTogetherStrong plugin;

    public DebugSystem(ApesTogetherStrong plugin) {
        this.plugin = plugin;
    }

    public void sendDebugMessage(String message) {
        if(plugin.getConfigSystem().isDebuggingEnabled() && message != null) {
            System.out.println("ATS DEBUG: " + message);
        }
    }

}
