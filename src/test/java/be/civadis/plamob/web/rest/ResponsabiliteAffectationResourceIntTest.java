package be.civadis.plamob.web.rest;

import be.civadis.plamob.PlamobApp;

import be.civadis.plamob.domain.ResponsabiliteAffectation;
import be.civadis.plamob.repository.ResponsabiliteAffectationRepository;
import be.civadis.plamob.service.ResponsabiliteAffectationService;
import be.civadis.plamob.service.dto.ResponsabiliteAffectationDTO;
import be.civadis.plamob.service.mapper.ResponsabiliteAffectationMapper;
import be.civadis.plamob.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

import static be.civadis.plamob.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the ResponsabiliteAffectationResource REST controller.
 *
 * @see ResponsabiliteAffectationResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = PlamobApp.class)
public class ResponsabiliteAffectationResourceIntTest {

    @Autowired
    private ResponsabiliteAffectationRepository responsabiliteAffectationRepository;

    @Autowired
    private ResponsabiliteAffectationMapper responsabiliteAffectationMapper;

    @Autowired
    private ResponsabiliteAffectationService responsabiliteAffectationService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restResponsabiliteAffectationMockMvc;

    private ResponsabiliteAffectation responsabiliteAffectation;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ResponsabiliteAffectationResource responsabiliteAffectationResource = new ResponsabiliteAffectationResource(responsabiliteAffectationService);
        this.restResponsabiliteAffectationMockMvc = MockMvcBuilders.standaloneSetup(responsabiliteAffectationResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ResponsabiliteAffectation createEntity(EntityManager em) {
        ResponsabiliteAffectation responsabiliteAffectation = new ResponsabiliteAffectation();
        return responsabiliteAffectation;
    }

    @Before
    public void initTest() {
        responsabiliteAffectation = createEntity(em);
    }

    @Test
    @Transactional
    public void createResponsabiliteAffectation() throws Exception {
        int databaseSizeBeforeCreate = responsabiliteAffectationRepository.findAll().size();

        // Create the ResponsabiliteAffectation
        ResponsabiliteAffectationDTO responsabiliteAffectationDTO = responsabiliteAffectationMapper.toDto(responsabiliteAffectation);
        restResponsabiliteAffectationMockMvc.perform(post("/api/responsabilite-affectations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(responsabiliteAffectationDTO)))
            .andExpect(status().isCreated());

        // Validate the ResponsabiliteAffectation in the database
        List<ResponsabiliteAffectation> responsabiliteAffectationList = responsabiliteAffectationRepository.findAll();
        assertThat(responsabiliteAffectationList).hasSize(databaseSizeBeforeCreate + 1);
        ResponsabiliteAffectation testResponsabiliteAffectation = responsabiliteAffectationList.get(responsabiliteAffectationList.size() - 1);
    }

    @Test
    @Transactional
    public void createResponsabiliteAffectationWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = responsabiliteAffectationRepository.findAll().size();

        // Create the ResponsabiliteAffectation with an existing ID
        responsabiliteAffectation.setId(1L);
        ResponsabiliteAffectationDTO responsabiliteAffectationDTO = responsabiliteAffectationMapper.toDto(responsabiliteAffectation);

        // An entity with an existing ID cannot be created, so this API call must fail
        restResponsabiliteAffectationMockMvc.perform(post("/api/responsabilite-affectations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(responsabiliteAffectationDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ResponsabiliteAffectation in the database
        List<ResponsabiliteAffectation> responsabiliteAffectationList = responsabiliteAffectationRepository.findAll();
        assertThat(responsabiliteAffectationList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllResponsabiliteAffectations() throws Exception {
        // Initialize the database
        responsabiliteAffectationRepository.saveAndFlush(responsabiliteAffectation);

        // Get all the responsabiliteAffectationList
        restResponsabiliteAffectationMockMvc.perform(get("/api/responsabilite-affectations?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(responsabiliteAffectation.getId().intValue())));
    }

    @Test
    @Transactional
    public void getResponsabiliteAffectation() throws Exception {
        // Initialize the database
        responsabiliteAffectationRepository.saveAndFlush(responsabiliteAffectation);

        // Get the responsabiliteAffectation
        restResponsabiliteAffectationMockMvc.perform(get("/api/responsabilite-affectations/{id}", responsabiliteAffectation.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(responsabiliteAffectation.getId().intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingResponsabiliteAffectation() throws Exception {
        // Get the responsabiliteAffectation
        restResponsabiliteAffectationMockMvc.perform(get("/api/responsabilite-affectations/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateResponsabiliteAffectation() throws Exception {
        // Initialize the database
        responsabiliteAffectationRepository.saveAndFlush(responsabiliteAffectation);
        int databaseSizeBeforeUpdate = responsabiliteAffectationRepository.findAll().size();

        // Update the responsabiliteAffectation
        ResponsabiliteAffectation updatedResponsabiliteAffectation = responsabiliteAffectationRepository.findOne(responsabiliteAffectation.getId());
        // Disconnect from session so that the updates on updatedResponsabiliteAffectation are not directly saved in db
        em.detach(updatedResponsabiliteAffectation);
        ResponsabiliteAffectationDTO responsabiliteAffectationDTO = responsabiliteAffectationMapper.toDto(updatedResponsabiliteAffectation);

        restResponsabiliteAffectationMockMvc.perform(put("/api/responsabilite-affectations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(responsabiliteAffectationDTO)))
            .andExpect(status().isOk());

        // Validate the ResponsabiliteAffectation in the database
        List<ResponsabiliteAffectation> responsabiliteAffectationList = responsabiliteAffectationRepository.findAll();
        assertThat(responsabiliteAffectationList).hasSize(databaseSizeBeforeUpdate);
        ResponsabiliteAffectation testResponsabiliteAffectation = responsabiliteAffectationList.get(responsabiliteAffectationList.size() - 1);
    }

    @Test
    @Transactional
    public void updateNonExistingResponsabiliteAffectation() throws Exception {
        int databaseSizeBeforeUpdate = responsabiliteAffectationRepository.findAll().size();

        // Create the ResponsabiliteAffectation
        ResponsabiliteAffectationDTO responsabiliteAffectationDTO = responsabiliteAffectationMapper.toDto(responsabiliteAffectation);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restResponsabiliteAffectationMockMvc.perform(put("/api/responsabilite-affectations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(responsabiliteAffectationDTO)))
            .andExpect(status().isCreated());

        // Validate the ResponsabiliteAffectation in the database
        List<ResponsabiliteAffectation> responsabiliteAffectationList = responsabiliteAffectationRepository.findAll();
        assertThat(responsabiliteAffectationList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteResponsabiliteAffectation() throws Exception {
        // Initialize the database
        responsabiliteAffectationRepository.saveAndFlush(responsabiliteAffectation);
        int databaseSizeBeforeDelete = responsabiliteAffectationRepository.findAll().size();

        // Get the responsabiliteAffectation
        restResponsabiliteAffectationMockMvc.perform(delete("/api/responsabilite-affectations/{id}", responsabiliteAffectation.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<ResponsabiliteAffectation> responsabiliteAffectationList = responsabiliteAffectationRepository.findAll();
        assertThat(responsabiliteAffectationList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ResponsabiliteAffectation.class);
        ResponsabiliteAffectation responsabiliteAffectation1 = new ResponsabiliteAffectation();
        responsabiliteAffectation1.setId(1L);
        ResponsabiliteAffectation responsabiliteAffectation2 = new ResponsabiliteAffectation();
        responsabiliteAffectation2.setId(responsabiliteAffectation1.getId());
        assertThat(responsabiliteAffectation1).isEqualTo(responsabiliteAffectation2);
        responsabiliteAffectation2.setId(2L);
        assertThat(responsabiliteAffectation1).isNotEqualTo(responsabiliteAffectation2);
        responsabiliteAffectation1.setId(null);
        assertThat(responsabiliteAffectation1).isNotEqualTo(responsabiliteAffectation2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ResponsabiliteAffectationDTO.class);
        ResponsabiliteAffectationDTO responsabiliteAffectationDTO1 = new ResponsabiliteAffectationDTO();
        responsabiliteAffectationDTO1.setId(1L);
        ResponsabiliteAffectationDTO responsabiliteAffectationDTO2 = new ResponsabiliteAffectationDTO();
        assertThat(responsabiliteAffectationDTO1).isNotEqualTo(responsabiliteAffectationDTO2);
        responsabiliteAffectationDTO2.setId(responsabiliteAffectationDTO1.getId());
        assertThat(responsabiliteAffectationDTO1).isEqualTo(responsabiliteAffectationDTO2);
        responsabiliteAffectationDTO2.setId(2L);
        assertThat(responsabiliteAffectationDTO1).isNotEqualTo(responsabiliteAffectationDTO2);
        responsabiliteAffectationDTO1.setId(null);
        assertThat(responsabiliteAffectationDTO1).isNotEqualTo(responsabiliteAffectationDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(responsabiliteAffectationMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(responsabiliteAffectationMapper.fromId(null)).isNull();
    }
}
