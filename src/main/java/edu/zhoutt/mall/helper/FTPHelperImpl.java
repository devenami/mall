package edu.zhoutt.mall.helper;

import edu.zhoutt.mall.common.Properties;
import edu.zhoutt.mall.util.FTPUtil;

import java.io.InputStream;

public class FTPHelperImpl implements FTPHelper {

    private Properties.FTPProperties ftp;

    public FTPHelperImpl(Properties properties) {
        this.ftp = properties.getFtp();
    }


    @Override
    public InputStream fromFTPServer(String absPath) {

        String username = ftp.getUsername();
        String password = ftp.getPassword();
        String host = ftp.getHost();
        Integer port = ftp.getPort();

        return FTPUtil.fromFTPServer(host, port, username, password, absPath);
    }

    @Override
    public void toFTPServer(String absPath, InputStream inputStream) {

        String username = ftp.getUsername();
        String password = ftp.getPassword();
        String host = ftp.getHost();
        Integer port = ftp.getPort();

        FTPUtil.toFTPServer(host, port, username, password, absPath, inputStream);
    }

}
