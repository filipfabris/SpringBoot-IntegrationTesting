package opp.service;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import com.fasterxml.jackson.databind.ObjectMapper;

import opp.controller.AutomobilContoller;
import opp.model.Automobil;

@WebMvcTest(AutomobilContoller.class)
class AutomobilServiceTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private AutomobilService service;

	@Test
	void getAllCars() throws Exception {
		given(service.getAll()).willReturn(List.of());

		mvc.perform(get("/cars").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(content().string("[]"));
	}

	@Test
	void addCar() throws Exception {
		mvc.perform(post("/add").accept(MediaType.APPLICATION_JSON)
				.content(asJsonString(new Automobil((long) 5, "AUDI", "A8", (long) 1234)))
				.contentType(MediaType.APPLICATION_JSON)).andDo(MockMvcResultHandlers.print())
				.andExpect(status().isCreated()).andExpect(header().exists("Location"));

	}

	@Test
	void addCar_Error() throws Exception {
		mvc.perform(post("/add").accept(MediaType.APPLICATION_JSON)
				.content(asJsonString(new AutomobilModified((long) 5, "A8", "das23")))
				.contentType(MediaType.APPLICATION_JSON)).andDo(MockMvcResultHandlers.print())
				.andExpect(status().isBadRequest());

	}

	@Test
	void getCar() throws Exception {
		given(service.getAutomobil(5L)).willReturn(new Automobil((long) 5, "AUDI", "A8", (long) 1234));

		mvc.perform(get("/car/{id}", 5L).accept(MediaType.APPLICATION_JSON)).andDo(MockMvcResultHandlers.print())
				.andExpect(status().isOk())
				.andExpect(content().string(asJsonString(new Automobil((long) 5, "AUDI", "A8", (long) 1234))));
	}

	@Test
	void getCar_404() throws Exception {
		given(service.getAutomobil(1L)).willReturn(null);

		mvc.perform(get("/car/{id}", 1L).accept(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound());
	}

	@Test
	void deleteCar() throws Exception {
		given(service.removeAutomobil(22L)).willReturn(true);

		mvc.perform(delete("/remove/{id}", 22L).accept(MediaType.APPLICATION_JSON)).andDo(MockMvcResultHandlers.print())
				.andExpect(status().isAccepted()).andExpect(content().string("true"));
	}

	@Test
	void deleteCar_404() throws Exception {
		given(service.removeAutomobil(10000L)).willReturn(false);

		mvc.perform(delete("/remove/{id}", 1L).accept(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound());
	}

	@Test
	void updateCar() throws Exception {
		mvc.perform(put("/modify").content(asJsonString(new Automobil((long) 5, "AUDI", "A8", (long) 1234)))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print()).andExpect(status().isCreated())
				.andExpect(header().exists("Location"));
	}
	
	@Test
	void updateCar_Error() throws Exception {
		mvc.perform(put("/modify").accept(MediaType.APPLICATION_JSON)
				.content(asJsonString(new AutomobilModified((long) 5, "A8", "das23")))
				.contentType(MediaType.APPLICATION_JSON)).andDo(MockMvcResultHandlers.print())
				.andExpect(status().isBadRequest());

	}

	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static class AutomobilModified {
		
		private Long id;
		private String model;
		private String serialNumber;

		public AutomobilModified(Long id, String model, String serialNumber) {
			this.id = id;
			this.model = model;
			this.serialNumber = serialNumber;
		}

		public Long getId() {
			return id;
		}

		public String getModel() {
			return model;
		}

		public String getSerialNumber() {
			return serialNumber;
		}	
	}

}
