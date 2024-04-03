package com.ortega.tshomboapi.util;

import java.util.UUID;

public class UploadUtil {

    public static String getResourcePath() {
        return System.getProperty("user.dir") + "/src/main/resources/images/";
    }

    public static String gUniqueFileName(String fileName) {

        // generate uuid and convert it in String
        String uniqueUUID = UUID.randomUUID().toString();

        // split file in two parts (name and extension)
        String[] parts = fileName.split("\\.");

        // get an extension of the current file
        String extension = parts[parts.length - 1];

        // file path
        return uniqueUUID + "." + extension;

    }

}
