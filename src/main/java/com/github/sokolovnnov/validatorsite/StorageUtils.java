package com.github.sokolovnnov.validatorsite;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.sokolovnnov.connectivitytest.model.SimpleNode;
import org.apache.commons.net.ftp.FTPClient;
import com.github.sokolovnnov.validatorsite.repo.inmemory.IsolatedNodeStorage;

import java.io.*;
import java.util.List;

public class StorageUtils {

    private static final String SERIALIZE_PATH = "d:\\osmtmp\\serialized\\";
    private static final ObjectMapper mapper = new ObjectMapper();

    public static IsolatedNodeStorage deSerializeInMemoryRepository() throws IOException, ClassNotFoundException {

        FTPClient ftpClient = new FTPClient();

        ftpClient.connect(System.getenv("FTP_ADDRESS"), Integer.parseInt(System.getenv("FTP_PORT")));
        ftpClient.login(System.getenv("FTP_USER"), System.getenv("FTP_PASSWORD"));

        ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
        ftpClient.enterLocalPassiveMode();

        InputStream inputStream1 = ftpClient.retrieveFileStream("\\osmserialized\\repository");

        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream1);
        ObjectInputStream objectInputStream = new ObjectInputStream(bufferedInputStream);
        IsolatedNodeStorage isolatedNodeStorage = (IsolatedNodeStorage) objectInputStream.readObject();
        objectInputStream.close();

        ftpClient.logout();
        ftpClient.disconnect();

        return isolatedNodeStorage;
    }

    public static List<SimpleNode> fromJson() throws IOException {

        FTPClient ftpClient = new FTPClient();

        ftpClient.connect(System.getenv("FTP_ADDRESS"), Integer.parseInt(System.getenv("FTP_PORT")));
        ftpClient.login(System.getenv("FTP_USER"), System.getenv("FTP_PASSWORD"));


        ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
        ftpClient.enterLocalPassiveMode();

        InputStream inputStream1 = ftpClient.retrieveFileStream("\\osmserialized\\jsonRepository");


        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream1);

        List<SimpleNode> simpleNodes = mapper.readValue(bufferedInputStream, new TypeReference<>() {
        });

        ftpClient.logout();
        ftpClient.disconnect();

        return simpleNodes;
    }

}
