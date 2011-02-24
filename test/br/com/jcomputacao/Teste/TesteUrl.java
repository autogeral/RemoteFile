package br.com.jcomputacao.Teste;

import java.io.IOException;
import java.io.Writer;
import java.net.MalformedURLException;
import java.net.URL;

import br.com.jcomputacao.RemoteFile.RemoteWriterFactory;

public class TesteUrl {
    private static Writer Writer;

    @SuppressWarnings("static-access")
	public static void main(String[] args) throws Throwable,
			MalformedURLException, Exception, IOException {

		String str = "file://localhost:1025";
		URL url = new URL(str);

		System.out.println("Host: " + url.getHost());
		System.out.println("Porta: " + url.getPort());
		System.out.println("Protocolo usado: " + url.getProtocol());
		System.out.println("User Info: " + url.getUserInfo());
		System.out.println("Método get authority:" + url.getAuthority());

		
		RemoteWriterFactory returnString = new RemoteWriterFactory();
        returnString.getWriter(str);

	}
}
