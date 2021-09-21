package ru.antisida.validatorsite;

import org.apache.commons.net.ftp.FTPClient;
import ru.antisida.validatorsite.repo.inmemory.IsolatedNodes;
import ru.antisida.validatorsite.repo.inmemory.ResultInMemoryRepository;

import java.io.*;

public class StorageUtils {

    private static final String SERIALIZE_PATH = "d:\\osmtmp\\serialized\\";

    public static IsolatedNodes deSerializeInMemoryRepository(/*InputStream inputStream*/) throws IOException,
            ClassNotFoundException{
//        FileInputStream fileInputStream = new FileInputStream(SERIALIZE_PATH + "repository");
        FTPClient ftpClient = new FTPClient();

        ftpClient.connect("185.36.172.214", 21);
        ftpClient.login("ILYA", "gjgjvflt");

        ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
        ftpClient.enterLocalPassiveMode();

        InputStream inputStream1 = ftpClient.retrieveFileStream("\\osmserialized\\repository" );



        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream1);
        ObjectInputStream objectInputStream = new ObjectInputStream(bufferedInputStream);
        IsolatedNodes isolatedNodes
                = (IsolatedNodes) objectInputStream.readObject();
        objectInputStream.close();

        ftpClient.logout();
        ftpClient.disconnect();

        return isolatedNodes;
    }

    public static InputStream downloadFromFTP() throws IOException {
        FTPClient ftpClient = new FTPClient();



        ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
        ftpClient.enterLocalPassiveMode();



//        InputStream outputStreamFromFtp =
//                return ftpClient.retrieveFileStream("\\osmserialized\\repository" );



//        FileOutputStream fileOutputStream = new FileOutputStream("d:\\osmtmp\\serialized\\" + "repository");
//        boolean isDownloaded = ftpClient.retrieveFile("\\osmserialized\\repository" , fileOutputStream);
//        fileOutputStream.flush();
//        fileOutputStream.close();
        ftpClient.logout();
        ftpClient.disconnect();
        return ftpClient.retrieveFileStream("\\osmserialized\\repository" );

    }

}