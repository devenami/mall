package edu.zhoutt.mall.util;

import org.apache.commons.net.ftp.FTPClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import java.io.IOException;
import java.io.InputStream;
import java.net.SocketException;

/**
 * ftp 工具类
 */
public class FTPUtil {

    private static final Logger log = LoggerFactory.getLogger(FTPUtil.class);


    /**
     * 获取 ftp 连接
     *
     * @param host     主机地址
     * @param port     端口
     * @param username 用户名
     * @param password 密码
     * @return ftp 客户端
     */
    private static FTPClient getFTPClient(String host, int port, String username,
                                          String password) {
        FTPClient client = null;
        try {
            client = new FTPClient();
            client.connect(host, port);
            client.login(username, password);

        } catch (SocketException e) {
            if (log.isErrorEnabled()) {
                log.error("FTP IP 配置错误, 请检查", e);
            }
        } catch (IOException e) {
            if (log.isErrorEnabled()) {
                log.error("FTP 配置错误, 请检查", e);
            }
        }
        return client;
    }

    /**
     * 从 FTP 读取文件
     *
     * @param host     主机
     * @param port     端口
     * @param username 用户名
     * @param password 密码
     * @param absPath  文件在ftp上的绝对路径
     * @return 读取到的文件输入流
     */
    public static InputStream fromFTPServer(String host, int port, String username, String password,
                                            String absPath) {

        Assert.hasText(absPath, "文件地址不能为空");

        String ftpPath = absPath.substring(0, absPath.lastIndexOf("/"));
        String fileName = absPath.substring(absPath.lastIndexOf("/") + 1);

        Assert.hasText(fileName, "FTP 绝对地址不正确: " + absPath);

        if (log.isDebugEnabled()) {
            log.debug("开始读取文件, FTP 文件绝对路径:{}", ftpPath);
        }

        InputStream in = null;
        FTPClient ftpClient = null;

        try {
            ftpClient = getFTPClient(host, port, username, password);
            ftpClient.setControlEncoding("UTF-8");
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
            ftpClient.enterLocalPassiveMode();
            ftpClient.changeWorkingDirectory(ftpPath);
            in = ftpClient.retrieveFileStream(fileName);
        } catch (IOException e) {
            if (log.isErrorEnabled()) {
                log.error("FTP 读取文件错误", e);
            }
        } finally {
            if (ftpClient != null) {
                try {
                    ftpClient.disconnect();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return in;
    }


    /**
     * 将文件写入到 FTP
     *
     * @param host     主机
     * @param port     端口
     * @param username 用户名
     * @param password 密码
     * @param absPath  FTP 上文件的绝对路径 /fileDir/filename.jpg
     * @param in       本地文件的输入流， 使用完会被自动关闭,但不会删除本地临时文件
     */
    public static void toFTPServer(String host, int port, String username, String password,
                                   String absPath, InputStream in) {
        Assert.hasText(absPath, "文件地址不能为空");

        String ftpPath = absPath.substring(0, absPath.lastIndexOf("/"));
        String fileName = absPath.substring(absPath.lastIndexOf("/") + 1);

        Assert.hasText(fileName, "FTP 绝对地址不正确: " + absPath);

        if (log.isDebugEnabled()) {
            log.debug("开始写入文件, FTP 文件绝对路径:{}", ftpPath);
        }

        FTPClient ftpClient = null;

        try {
            ftpClient = getFTPClient(host, port, username, password);
            ftpClient.setControlEncoding("UTF-8");
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
            ftpClient.enterLocalPassiveMode();

            ftpClient.makeDirectory(ftpPath);
            ftpClient.changeWorkingDirectory(ftpPath);

            ftpClient.storeFile(fileName, in);

        } catch (IOException e) {
            if (log.isErrorEnabled()) {
                log.error("FTP 写入文件错误", e);
            }
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (ftpClient != null) {
                try {
                    ftpClient.disconnect();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }


}
