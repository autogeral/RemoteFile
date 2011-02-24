package br.com.jcomputacao.RemoteFile;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.net.URL;

public class RemoteWriterFactory {
    public static Writer getWriter(String oldstr) throws IOException {
        if (oldstr.contains("file://")) {
            URL url = new URL(oldstr);
            int port = url.getPort();
            if (port == -1) {
                return new RemoteFileWriter(url.getHost());
            }
            return new RemoteFileWriter(url.getHost(), port);
        }
        FileWriter fw = new FileWriter(oldstr);
        return fw;
    }
}
