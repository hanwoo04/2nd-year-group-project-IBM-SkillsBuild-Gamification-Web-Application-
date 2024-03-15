package org.example.ibmskillsbuildapp.controller;

import org.example.ibmskillsbuildapp.model.Avatar;
import org.example.ibmskillsbuildapp.service.AvatarService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AvatarControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AvatarService avatarService;

    @BeforeEach
    public void setUp() {
        // Setup any necessary data before each test
    }

    @Test
    public void testSaveAvatarEndpoint() throws Exception {
        // Create a mock MultipartFile
        MockMultipartFile file = new MockMultipartFile("avatar", "avatar.jpg", MediaType.IMAGE_JPEG_VALUE, "avatar-data".getBytes());

        // Perform a POST request to save the avatar
        mockMvc.perform(MockMvcRequestBuilders.multipart("/saveAvatar")
                        .file(file)
                        .param("skinColor", "#ffddb3")
                        .param("eyeColor", "#4e3c28")
                        .param("hairType", "short")
                        .param("hairColor", "#000000")
                        .param("noseSize", "medium")
                        .param("mouthSize", "small")
                        .param("glasses", "false"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Avatar saved successfully!"))
                .andDo(print());
    }

    // Add more test methods to cover other scenarios as needed
}
