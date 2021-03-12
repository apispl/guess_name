package pl.pszczolkowski.guess_name.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class NameApiTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void contexLoads() {
        assertThat(mockMvc).isNotNull();
    }

//    @Test
//    void shouldGetAllTokens() throws Exception {
//        mockMvc.perform(get("/detector")
//                .contentType(MediaType.TEXT_PLAIN)
//                .header("Content-Type","text/plain")
//                .header("charset","UTF-8"))
//                .andExpect(status().isOk())
//                .andExpect(content().string(tokenList()));
//    }
//
//    private static String tokenList() {
//        return "Jan\r\n" + "Zbigniew\r\n" + "Karol\r\n" +
//                "Maria\r\n" + "Anna\r\n" + "Gertruda\r\n";
//    }
//
//    @Test
//    void shouldDetectName_male() throws Exception {
//        mockMvc.perform(post("/detector")
//                .header("Content-Type","text/plain")
//                .header("charset","UTF-8")
//                .contentType(MediaType.TEXT_PLAIN)
//                .content("Karol"))
//                .andExpect(status().isOk())
//                .andExpect(content().string("MALE"));
//    }
//
//    @Test
//    void shouldDetectName_female() throws Exception {
//        mockMvc.perform(post("/detector")
//                .header("Content-Type","text/plain")
//                .header("charset","UTF-8")
//                .contentType(MediaType.TEXT_PLAIN)
//                .content("Anna"))
//                .andExpect(status().isOk())
//                .andExpect(content().string("FEMALE"));
//    }
//
//    @Test
//    void shouldDetectName_inconclusive() throws Exception {
//        mockMvc.perform(post("/detector")
//                .header("Content-Type","text/plain")
//                .header("charset","UTF-8")
//                .contentType(MediaType.TEXT_PLAIN)
//                .content("Ramona"))
//                .andExpect(status().isOk())
//                .andExpect(content().string("INCONCLUSIVE"));
//    }
}
