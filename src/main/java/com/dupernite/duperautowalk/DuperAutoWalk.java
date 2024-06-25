package com.dupernite.duperautowalk;

import com.dupernite.duperautowalk.compat.MidnightLibAPI;
import eu.midnightdust.lib.config.MidnightConfig;
import net.fabricmc.api.ModInitializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DuperAutoWalk implements ModInitializer {
    public static final String MOD_ID = "duperautowalk";
    public static final Logger LOGGER = LogManager.getLogger(MOD_ID);
    @Override
    public void onInitialize() {
        MidnightConfig.init(MOD_ID, MidnightLibAPI.class);
    }
}
