package br.com.alura.loja.resource;

import java.net.URI;
import java.net.URISyntaxException;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.thoughtworks.xstream.XStream;

import br.com.alura.loja.dao.CarrinhoDAO;
import br.com.alura.loja.modelo.Carrinho;

@Path("carrinhos")
public class CarrinhoResource {

	// Parametro faz parte da URI
	@Path("{id}")
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public String busca(@PathParam(value = "id") long id) {
		Carrinho carrinho = new CarrinhoDAO().busca(id);
		System.out.println(carrinho.toXML());
		return carrinho.toXML();
	}

	@POST
	@Consumes(MediaType.APPLICATION_XML)
	public Response adiciona(String conteudo) throws Exception {		
		Carrinho carrinho = (Carrinho) new XStream().fromXML(conteudo);
		new CarrinhoDAO().adiciona(carrinho);
		return Response.created(new URI("/carrinhos/"+carrinho.getId())).build();
		
	}

//	// Perde a capacidade de cache, pois parametro não faz parte da URI
//	@GET
//	@Produces(MediaType.APPLICATION_XML)
//	public String buscaComParametro(@QueryParam(value = "id") long id) {
//		Carrinho carrinho = new CarrinhoDAO().busca(id);
//		return carrinho.toXML();
//	}

//	// Parametro faz parte da URI
//	@Path("{id}")
//	@GET
//	@Produces(MediaType.APPLICATION_JSON)
//	public String buscaJSON(@PathParam(value = "id") long id) {
//		Carrinho carrinho = new CarrinhoDAO().busca(id);
//		return carrinho.toJson();
//	}	

}
