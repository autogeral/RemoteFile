/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.jcomputacao.RemoteFile;

import java.io.IOException;
import org.junit.Test;

/**
 *
 * @author odair
 */
public class TesteServer {

    public TesteServer() {
    }

    @Test
    public void testTransferencia() throws IOException {
        execute("Conteúdo a ser escrito");
    }

    @Test
    public void testEncerramento() throws IOException {
        execute("SHUTDOWN");
    }

    public void execute(String conteudo) throws IOException {
        RemoteFileWriter rfw = new RemoteFileWriter("192.168.1.31", 1025);
        conteudo = "ODAIR SILVA\nMIQUEIAS\nNIERI\nCHEFE";
        rfw.write(conteudo);
        rfw.flush();
        rfw.close();
    }
}