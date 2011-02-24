package br.com.jcomputacao.RemoteFile;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class RemoteServerReader {
    boolean acceptNextCall = true;
    static final String SHUTDOWN = "shutdown";

    public void conexaoServidor() {
        final int porta = obtemPorta();
        ServerSocket soqueteServidor = null;
        try {
            System.out.println("Aguardando conexões na porta " + porta);
            // Cria um novo soquete servidor
            soqueteServidor = new ServerSocket(porta);
            int cliente = 0;
            while (acceptNextCall) {
                System.out.println("Aguardando conexão do cliente " + (++cliente));
                Socket sock = soqueteServidor.accept();
                ReaderThread th = new ReaderThread(sock);
                th.start();
            }

            soqueteServidor.close();
        } catch (IOException excecao) {
            System.out.println("Não foi possível ouvir cliente remoto");
            excecao.printStackTrace();
        }
    }

    static int obtemPorta() {
        String str = System.getProperty("br.com.jcomputacao.RemoteFile.port", "1025");
        int port = Integer.parseInt(str);
        return port;
    }
}
