package com.dupernite.duperautowalk.compat;

import eu.midnightdust.lib.config.MidnightConfig;

public class MidnightLibAPI extends MidnightConfig {
    @Entry(category = "text") public static boolean isIconEnabled = true;
    @Entry(category = "text") public static PositionEnum positionEnum = PositionEnum.TOP_LEFT;
    public enum PositionEnum {
        TOP_LEFT, TOP_RIGHT, MIDDLE_LEFT, MIDDLE_RIGHT, BOTTOM_LEFT, BOTTOM_RIGHT
    }
}
