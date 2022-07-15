package br.com.alura.loja;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.junit.Assert;
import org.junit.Test;

public class ClienteTest {

	@Test
	public void testeQueAConexaoComOServidorEstaFuncionando() {

		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://www.mocky.io/");
		String conteudo = target.path("v2/52aaf5bbee7ba8c60329fb7b").request().get(String.class);

		Assert.assertTrue(conteudo.contains("Rua"));
	}

}
