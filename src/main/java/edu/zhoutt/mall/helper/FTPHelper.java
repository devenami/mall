package edu.zhoutt.mall.helper;

import java.io.InputStream;

/**
 * FTP 帮助类， 开发人员只需要将该接口注入到对应的服务中，即可使用
 */
public interface FTPHelper {

    /**
     * 从 FTP 服务器获取文件
     *
     * @param absPath 文件在服务器上的绝对地址 /fileDir/file.jpg
     * @return 读取到的文件输入流
     */
    InputStream fromFTPServer(String absPath);


    /**
     * 将文件上传到 FTP
     *
     * @param absPath     文件在服务器上的绝对地址 /fileDir/file.jpg
     * @param inputStream 本地或远程文件输入流
     */
    void toFTPServer(String absPath, InputStream inputStream);

}
