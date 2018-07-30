package edu.zhoutt.mall.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class FileUtil {


    public static String convertPathToUnix(String source) {
        if (StringUtil.hasText(source)) {
            return source.replace("\\", "/");
        }
        return "";
    }

    public static String transferTo(MultipartFile multipartFile, String destPath) {

        File destDir = new File(destPath);
        if (!destDir.exists()) {
            destDir.mkdirs();
        }
        String originalFilename = multipartFile.getOriginalFilename();
        String suffer = originalFilename.substring(originalFilename.lastIndexOf("."));
        String absPath = destPath + "/" + UUID.randomUUID().toString() + suffer;
        File destFile = new File(absPath);
        try {
            multipartFile.transferTo(destFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return absPath;
    }

    public static String relationPath(String absPath, String prefix) {
        String regex = "^" + prefix;
        return absPath.replaceAll(regex, "");
    }

}
