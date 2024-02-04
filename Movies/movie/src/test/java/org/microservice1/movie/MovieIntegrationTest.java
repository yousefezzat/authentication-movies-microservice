//package org.microservice1.movie;
//
//import com.github.tomakehurst.wiremock.WireMockServer;
//import com.github.tomakehurst.wiremock.client.WireMock;
//import com.github.tomakehurst.wiremock.junit5.WireMockTest;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//
//import static com.github.tomakehurst.wiremock.client.WireMock.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//@SpringBootTest
//@AutoConfigureMockMvc
//@WireMockTest(httpsEnabled = true,httpPort = 8082)
//
//public class MovieIntegrationTest {
//
//   @Autowired
//   private MockMvc mockMvc;
//
//   @BeforeEach
//   void setup() {
//      WireMock.reset();
//   }
//
//   @Test
//   void testGetMoviesWithMockedAuthentication() throws Exception {
//      // Set up WireMock to mock the validateToken endpoint
//      WireMock.stubFor(WireMock.get(urlEqualTo("/api/v1/validate"))
//              .withHeader(HttpHeaders.AUTHORIZATION, equalTo("Bearer mockToken"))
//              .willReturn(WireMock.aResponse()
//                      .withStatus(200)
//                      .withBody("OK")));
//
//      // Perform the request and validate the response
//      mockMvc.perform(MockMvcRequestBuilders.get("/movies")
//                      .param("page", "0")
//                      .param("size", "10")
//                      .header(HttpHeaders.AUTHORIZATION, "Bearer mockToken")
//                      .contentType(MediaType.APPLICATION_JSON))
//              .andExpect(status().isOk())
//              .andExpect(jsonPath("$.length()").value(0));
//   }
//}
