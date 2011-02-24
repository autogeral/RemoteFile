package br.com.jcomputacao.RemoteFile;

/**
 * @author odair
 */
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.net.Socket;

public class RemoteFileWriter extends Writer {

    private Socket socket = null;
    private OutputStream ostr = null;

    public RemoteFileWriter() throws IOException {
        this("localhost", 1025);
    }

    public RemoteFileWriter(String host) throws IOException {
        this(host, 1025);
    }

    public RemoteFileWriter(String host, int port) throws IOException {
        super();
        socket = new Socket(host, port);
        ostr = socket.getOutputStream();

    }

    @Override
    public void write(String string) throws IOException {
        write(string.toCharArray(), 0, string.length());
    }

    @Override
    public void close() throws IOException {
        ostr.close();
        socket.close();
    }

    @Override
    public void flush() throws IOException {
        ostr.flush();
    }

    @Override
    public void write(char[] cbuf, int off, int len) throws IOException {
        byte[] bbuf = new byte[cbuf.length];
        for (int i = 0; i < cbuf.length; i++) {
            bbuf[i] = (byte) cbuf[i];
        }
        ostr.write(bbuf, off, len);
    }
}
