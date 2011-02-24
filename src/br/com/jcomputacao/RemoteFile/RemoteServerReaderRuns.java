package br.com.jcomputacao.RemoteFile;

/**
 * 
 * @author odair
 *
 */
public class RemoteServerReaderRuns {

    public static void main(String[] args) {
        System.out.println("Utilize os argumentos caso necessario:");
        System.out.println("-Dbr.com.jcomputacao.RemoteFile.file=<saida> para alterar a saida");
        System.out.println("-Dbr.com.jcomputacao.RemoteFile.port=<porta> para alterar a porta");
        System.out.println("Utilizacao java -jar [args] RemoteFile.jar");

        RemoteServerReader soqServidor = new RemoteServerReader();
        soqServidor.conexaoServidor();
    }
}


