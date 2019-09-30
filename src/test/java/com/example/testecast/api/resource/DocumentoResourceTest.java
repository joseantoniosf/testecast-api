package com.example.testecast.api.resource;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DocumentoResourceTest {

	@Autowired
	public WebApplicationContext context;
	
	private MockMvc mvc;
	
	@Before
	public void setup() {
		this.mvc = MockMvcBuilders.webAppContextSetup(this.context).build();
	}
	
	// ------------------------------------------------------------------------------
	// ------- DOCUMENTO LEFT -------------------------------------------------------
	// ------------------------------------------------------------------------------
	
	@Test
	public void testaRequisicaoListarTodosLeftSucesso() throws Exception {
		String url = "/v1/diff/left";
		this.mvc.perform(get(url))
			.andExpect(status().isOk());
	}
	
	@Test
	public void testaRequisicaoListarTodosLeftFalha() throws Exception {
		String url = "/v1/diff/lefts";
		this.mvc.perform(get(url))
			.andExpect(status().isBadRequest());
	}

	@Test
	public void testaRequisicaoListarUmItemLeftSucesso() throws Exception {
		String url = "/v1/diff/1/left";
		this.mvc.perform(get(url))
			.andExpect(status().isOk())
			.andExpect(content().string(containsString("documento")));
	}
	
	@Test
	public void testaRequisicaoListarUmItemLeftFalha() throws Exception {
		String url = "/v1/diff/50/left";
		this.mvc.perform(get(url))
			.andExpect(status().isNotFound());
	}
	
	@Test
	public void testaRequisicaoPostLeftSucesso() throws Exception {
		String url = "/v1/diff/4/left";
		this.mvc.perform(post(url)
			.content("{\"documento\": \"QW1pemFkZQ==\"}") //Amizade
			.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isCreated())
			.andExpect(header().string("Location", is("http://localhost/v1/diff/4/left")));
	}

	@Test
	public void testaRequisicaoPostLeftFalha1() throws Exception {
		String url = "/v1/diff/4/left";
		this.mvc.perform(post(url)
			.content("{\"documento\": null}")
			.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isBadRequest());
	}

	@Test
	public void testaRequisicaoPostLeftFalha2() throws Exception {
		String url = "/v1/diff/4/left";
		this.mvc.perform(post(url)
			.content("{\"documento1\": \"QW1pemFkZQ==\"}") //Amizade
			.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isBadRequest());
	}

	
	
	// ------------------------------------------------------------------------------
	// ------- DOCUMENTO RIGHT ------------------------------------------------------
	// ------------------------------------------------------------------------------
	
	@Test
	public void testaRequisicaoListarTodosRightSucesso() throws Exception {
		String url = "/v1/diff/right";
		this.mvc.perform(get(url))
			.andExpect(status().isOk());
	}
	
	@Test
	public void testaRequisicaoListarTodosRightFalha() throws Exception {
		String url = "/v1/diff/rights";
		this.mvc.perform(get(url))
			.andExpect(status().isBadRequest());
	}

	@Test
	public void testaRequisicaoListarUmItemRightSucesso() throws Exception {
		String url = "/v1/diff/1/right";
		this.mvc.perform(get(url))
			.andExpect(status().isOk())
			.andExpect(content().string(containsString("documento")));
	}
	
	@Test
	public void testaRequisicaoListarUmItemRightFalha() throws Exception {
		String url = "/v1/diff/50/right";
		this.mvc.perform(get(url))
			.andExpect(status().isNotFound());
	}
	
	@Test
	public void testaRequisicaoPostRightSucesso() throws Exception {
		String url = "/v1/diff/4/right";
		this.mvc.perform(post(url)
			.content("{\"documento\": \"QW1pemFkZQ==\"}") //Amizade
			.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isCreated())
			.andExpect(header().string("Location", is("http://localhost/v1/diff/4/right")));
	}

	@Test
	public void testaRequisicaoPostRightFalha1() throws Exception {
		String url = "/v1/diff/4/right";
		this.mvc.perform(post(url)
			.content("{\"documento\": null}")
			.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isBadRequest());
	}

	@Test
	public void testaRequisicaoPostRightFalha2() throws Exception {
		String url = "/v1/diff/4/right";
		this.mvc.perform(post(url)
			.content("{\"documento1\": \"QW1pemFkZQ==\"}") //Amizade
			.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isBadRequest());
	}

	
	// --------------------------------------------------------------------------
	// ----------RESULTADO DA DIFERENÇA ENTRE LEFT E RIGHT ----------------------
	// --------------------------------------------------------------------------
	@Test
	public void testaRequisicaoDiffSucesso1() throws Exception {
		String url = "/v1/diff/1";
		this.mvc.perform(get(url))
			.andExpect(status().isOk())
			.andExpect(content().string(containsString("idênticos")));
	}
	
	@Test
	public void testaRequisicaoDiffSucesso2() throws Exception {
		String url = "/v1/diff/2";
		this.mvc.perform(get(url))
			.andExpect(status().isOk())
			.andExpect(content().string(containsString("tamanhos diferentes")));
	}

	@Test
	public void testaRequisicaoDiffSucesso3() throws Exception {
		String url = "/v1/diff/3";
		this.mvc.perform(get(url))
			.andExpect(status().isOk())
			.andExpect(content().string(containsString("resposta")));
	}

}
