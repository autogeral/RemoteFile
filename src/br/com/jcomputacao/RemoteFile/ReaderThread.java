/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.jcomputacao.RemoteFile;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.net.Socket;

/**
 *
 * @author odair
 */
public class ReaderThread extends Thread {

    private Socket sock;

    ReaderThread(Socket sock) {
        this.sock = sock;
    }

    @Override
    public void run() {
        System.out.println("Obtendo stream de entrada");
        String arquivoSaida = obtemArquivoSaida();
        InputStream istr;
        try {
            istr = sock.getInputStream();

            System.out.println("Lendo conteudo");
            String content = getContent(istr);

            if (content != null && content.equalsIgnoreCase(RemoteServerReader.SHUTDOWN)) {
                //Como a thread pricipal eh muita rapida ela já
                //esta aguardando o próximo cliente
                //Entao eu mesmo serei meu cliente para que ele saia
                RemoteFileWriter rfw = new RemoteFileWriter("127.0.0.1", RemoteServerReader.obtemPorta());
                rfw.write(RemoteServerReader.SHUTDOWN);
                rfw.flush();
                rfw.close();
            } else {
                synchronized (arquivoSaida) {
                    FileWriter writer = new FileWriter(arquivoSaida, false);
                    BufferedReader bf = new BufferedReader(new StringReader(content));

                    while (bf.readLine() != null) {
                        writer.write(content);
                        writer.write("\n");
//                        System.out.println("Lendo novamente");
                        content = getContent(istr);

                    }
                    writer.flush();
                    writer.close();
                }
            }
            istr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getContent(InputStream in) throws IOException {
        byte[] buf = new byte[1024];
        int len;
        StringBuilder sb = new StringBuilder();
        while ((len = in.read(buf)) > 0) {
            sb.append(new String(buf, 0, len));
        }
        return sb.toString();
    }

    private String obtemArquivoSaida() {
        return System.getProperty("br.com.jcomputacao.RemoteFile.file", "LPT1");
    }
}
