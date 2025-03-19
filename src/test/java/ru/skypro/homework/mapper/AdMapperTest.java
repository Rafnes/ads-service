package ru.skypro.homework.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import ru.skypro.homework.dto.AdDTO;
import ru.skypro.homework.dto.AdsDTO;
import ru.skypro.homework.dto.CreateOrUpdateAdDTO;
import ru.skypro.homework.dto.ExtendedAdDTO;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.model.Comment;
import ru.skypro.homework.model.User;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AdMapperTest {
    private final AdMapper adMapper = Mappers.getMapper(AdMapper.class);

    private Ad ad;
    private AdDTO adDTO;
    private CreateOrUpdateAdDTO createOrUpdateAdDTO;
    private ExtendedAdDTO extendedAdDTO;
    private User author;

    @BeforeEach
    void setUp() {
        author = new User();
        author.setId(8);
        author.setFirstName("John");
        author.setLastName("Doe");
        author.setEmail("john.doe@example.com");
        author.setPhone("123456789");
        author.setImage("avatar.jpg");

        ad = new Ad();
        ad.setId(6);
        ad.setTitle("Объявление 1");
        ad.setPrice(100);
        ad.setAuthor(author);
        ad.setImage("image.jpg");

        adDTO = new AdDTO();
        adDTO.setPk(6);
        adDTO.setTitle("Объявление 1");
        adDTO.setPrice(100);
        adDTO.setAuthor(8);
        adDTO.setImage("image.jpg");

        createOrUpdateAdDTO = new CreateOrUpdateAdDTO();
        createOrUpdateAdDTO.setTitle("Объявление 1");

        extendedAdDTO = new ExtendedAdDTO();
        extendedAdDTO.setPk(6);
        extendedAdDTO.setTitle("Объявление 1");
        extendedAdDTO.setPrice(100);
        extendedAdDTO.setAuthorFirstName("John");
        extendedAdDTO.setAuthorLastName("Doe");
        extendedAdDTO.setEmail("john.doe@example.com");
        extendedAdDTO.setPhone("123456789");
        extendedAdDTO.setImage("image.jpg");
    }

    @Test
    void testToModelFromAdDTO() {
        Ad mappedAd = adMapper.toModel(adDTO);
        assertEquals(ad.getId(), mappedAd.getId());
        assertEquals(ad.getTitle(), mappedAd.getTitle());
        assertEquals(ad.getPrice(), mappedAd.getPrice());
        assertEquals(ad.getImage(), mappedAd.getImage());
    }

    @Test
    void testToModelFromCreateOrUpdateAdDTO() {
        Ad mappedAd = adMapper.toModel(createOrUpdateAdDTO);
        assertEquals(createOrUpdateAdDTO.getTitle(), mappedAd.getTitle());
    }

    @Test
    void testToModelFromExtendedAdDTO() {
        Ad mappedAd = adMapper.toModel(extendedAdDTO);
        assertEquals(extendedAdDTO.getPk(), mappedAd.getId());
        assertEquals(extendedAdDTO.getTitle(), mappedAd.getTitle());
        assertEquals(extendedAdDTO.getPrice(), mappedAd.getPrice());
        assertEquals(extendedAdDTO.getImage(), mappedAd.getImage());
        assertNotNull(mappedAd.getAuthor());
        assertEquals(extendedAdDTO.getAuthorFirstName(), mappedAd.getAuthor().getFirstName());
        assertEquals(extendedAdDTO.getAuthorLastName(), mappedAd.getAuthor().getLastName());
    }

    @Test
    void testToDtoAdDTO() {
        AdDTO mappedDto = adMapper.toDtoAdDTO(ad);
        assertEquals(ad.getId(), mappedDto.getPk());
        assertEquals(ad.getTitle(), mappedDto.getTitle());
        assertEquals(ad.getPrice(), mappedDto.getPrice());
        assertEquals(ad.getImage(), mappedDto.getImage());
        assertEquals(ad.getAuthor().getId(), mappedDto.getAuthor());
    }

    @Test
    void testToDtoCreateOrUpdateAdDTO() {
        CreateOrUpdateAdDTO mappedDto = adMapper.toDtoCreateOrUpdateAdDTO(ad);
        assertEquals(ad.getTitle(), mappedDto.getTitle());
    }

    @Test
    void testToDtoExtendedAdDTO() {
        ExtendedAdDTO mappedDto = adMapper.toDtoExtendedAdDTO(ad);
        assertEquals(ad.getId(), mappedDto.getPk());
        assertEquals(ad.getTitle(), mappedDto.getTitle());
        assertEquals(ad.getPrice(), mappedDto.getPrice());
        assertEquals(ad.getImage(), mappedDto.getImage());
        assertEquals(ad.getAuthor().getFirstName(), mappedDto.getAuthorFirstName());
        assertEquals(ad.getAuthor().getLastName(), mappedDto.getAuthorLastName());
        assertEquals(ad.getAuthor().getEmail(), mappedDto.getEmail());
        assertEquals(ad.getAuthor().getPhone(), mappedDto.getPhone());
    }

    @Test
    void testToAds() {
        List<Ad> adsList = Collections.singletonList(ad);
        AdsDTO adsDTO = adMapper.toAds(adsList.size(), adsList);
        assertEquals(adsList.size(), adsDTO.getCount());
        assertEquals(adsList.size(), adsDTO.getResults().size());
        assertEquals(ad.getTitle(), adsDTO.getResults().get(0).getTitle());
    }
}
