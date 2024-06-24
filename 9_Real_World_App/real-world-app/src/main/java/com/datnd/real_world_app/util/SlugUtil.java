package com.datnd.real_world_app.util;

import java.util.Date;

public class SlugUtil {
    public static String getSlug(String title){
        return title.toLowerCase().replaceAll("\\s+", "-")+"-"+ new Date().getTime();
    }
}
