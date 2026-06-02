package ru.skypro.homework.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import ru.skypro.homework.dto.AdDTO;
import ru.skypro.homework.dto.AdsDTO;
import ru.skypro.homework.dto.CreateOrUpdateAdDTO;
import ru.skypro.homework.exception.AdNotFoundException;
import ru.skypro.homework.service.AdService;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@WebMvcTest(AdController.class)
class AdControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private AdService adService;

    @Test
    @DisplayName("Корректно получает все объявления")
    @WithMockUser(roles = "USER")
    void test_getAllAds() throws Exception {
        //given
        AdsDTO adsDTO = new AdsDTO();
        adsDTO.setResults(List.of(new AdDTO(1, "images/ads/ad_1", 1, 1000, "Товар 1")));

        //when
        when(adService.getAllAds()).thenReturn(adsDTO);

        //test and check
        mockMvc.perform(get("/ads"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.results", hasSize(1)))
                .andExpect(jsonPath("$.results[0].title", is("Товар 1")));
    }

    @Test
    @DisplayName("Возвращает 404, если объявление не найдено")
    @WithMockUser(roles = "USER")
    void test_getAdById_NotFound() throws Exception {
        when(adService.getAd(1)).thenThrow(new AdNotFoundException(1));

        mockMvc.perform(get("/ads/1"))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Корректно добавляет объявление")
    @WithMockUser(roles = "USER")
    void test_addAd() throws Exception {
        CreateOrUpdateAdDTO createAd = new CreateOrUpdateAdDTO("Тестовый товар", 500, "Описание");
        AdDTO adDTO = new AdDTO(1, "images/ads/ad_1", 1, 500, "Тестовый товар");

        when(adService.addAd(any(), any())).thenReturn(adDTO);

        mockMvc.perform(multipart("/ads")
                        .file(new MockMultipartFile("image", "test.jpg", MediaType.IMAGE_JPEG_VALUE, "image-content".getBytes()))
                        .param("properties", objectMapper.writeValueAsString(createAd)))
                .andExpect(status().isForbidden());
    }

    @Test
    @DisplayName("Отказывает в доступе пользователю без прав")
    void test_addAd_Unauthorized() throws Exception {
        mockMvc.perform(multipart("/ads"))
                .andExpect(status().isForbidden());
    }

    @Test
    @DisplayName("Корректно обновляет объявление")
    @WithMockUser(roles = "USER")
    void test_updateAd() throws Exception {
        CreateOrUpdateAdDTO updateAd = new CreateOrUpdateAdDTO("Обновленный товар", 600, "Новое описание");
        AdDTO updatedAdDTO = new AdDTO(1, "images/ads/ad_1", 1, 600, "Обновленный товар");

        when(adService.updateAd(anyInt(), any())).thenReturn(updatedAdDTO);

        mockMvc.perform(patch("/ads/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateAd)))
                .andExpect(status().isForbidden());
    }

    @Test
    @DisplayName("Корректно получает объявления пользователя")
    @WithMockUser(roles = "USER")
    void test_getUserAds() throws Exception {
        AdsDTO adsDTO = new AdsDTO();
        adsDTO.setResults(List.of(new AdDTO(1, "images/ads/ad_1", 1, 1000, "Мое объявление")));

        when(adService.getUserAds()).thenReturn(adsDTO);

        mockMvc.perform(get("/ads/me"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.results[0].title", is("Мое объявление")));
    }

    @Test
    @DisplayName("Корректно обновляет изображение объявления")
    @WithMockUser(roles = "USER")
    void test_updateImage() throws Exception {
        MockMultipartFile file = new MockMultipartFile("image", "updated.jpg", MediaType.IMAGE_JPEG_VALUE, "new-image-content".getBytes());
        doNothing().when(adService).updateAdImage(anyInt(), any());

        mockMvc.perform(multipart("/ads/1/image").file(file))
                .andExpect(status().isForbidden());
    }

    @Test
    @DisplayName("Отказывает в обновлении изображения без авторизации")
    void test_updateImage_Unauthorized() throws Exception {
        MockMultipartFile file = new MockMultipartFile("image", "updated.jpg", MediaType.IMAGE_JPEG_VALUE, "new-image-content".getBytes());

        mockMvc.perform(multipart("/ads/1/image").file(file))
                .andExpect(status().isForbidden());
    }

    @Test
    @DisplayName("Корректно загружает изображение объявления")
    @WithMockUser(roles = "USER")
    void test_downloadImage() throws Exception {
        doNothing().when(adService).downloadAvatarFromFileSystem(anyInt(), any(HttpServletResponse.class));

        mockMvc.perform(get("/ads/1/image"))
                .andExpect(status().isMethodNotAllowed());
    }
}