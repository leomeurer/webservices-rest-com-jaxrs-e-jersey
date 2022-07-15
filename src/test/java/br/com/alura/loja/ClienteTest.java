package br.com.alura.loja;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.thoughtworks.xstream.XStream;

import br.com.alura.loja.modelo.Carrinho;

public class ClienteTest {

	private HttpServer server;

	@Before
	public void before() {
		server = Servidor.inicializaServidor();
	}

	@After
	public void after() {

		server.stop();
		System.out.println("Servidor parou...");
	}

	@Test
	public void testeQueAConexaoComOServidorEstaFuncionando() {

		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://www.mocky.io/");
		String conteudo = target.path("v2/52aaf5bbee7ba8c60329fb7b").request().get(String.class);

		Assert.assertTrue(conteudo.contains("Rua"));
	}

	@Test
	public void testeQueBuscaCarrinhoETrazOCarrinhoEsperado() {

		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://localhost:8080");
		String conteudo = target.path("/carrinhos").request().get(String.class);

		Carrinho carrinho = (Carrinho) new XStream().fromXML(conteudo);

		Assert.assertEquals("Rua Vergueiro 3185, 8 andar", carrinho.getRua());
	}

}
