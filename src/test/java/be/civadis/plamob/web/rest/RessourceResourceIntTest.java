package be.civadis.plamob.web.rest;

import be.civadis.plamob.PlamobApp;

import be.civadis.plamob.domain.Ressource;
import be.civadis.plamob.repository.RessourceRepository;
import be.civadis.plamob.service.RessourceService;
import be.civadis.plamob.service.dto.RessourceDTO;
import be.civadis.plamob.service.mapper.RessourceMapper;
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

import be.civadis.plamob.domain.enumeration.TYPE_RESSOURCE;
/**
 * Test class for the RessourceResource REST controller.
 *
 * @see RessourceResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = PlamobApp.class)
public class RessourceResourceIntTest {

    private static final String DEFAULT_TRIGRAMME = "AAAAAAAAAA";
    private static final String UPDATED_TRIGRAMME = "BBBBBBBBBB";

    private static final String DEFAULT_TEL = "AAAAAAAAAA";
    private static final String UPDATED_TEL = "BBBBBBBBBB";

    private static final TYPE_RESSOURCE DEFAULT_TYPE_RESS = TYPE_RESSOURCE.MOB;
    private static final TYPE_RESSOURCE UPDATED_TYPE_RESS = TYPE_RESSOURCE.DOM;

    @Autowired
    private RessourceRepository ressourceRepository;

    @Autowired
    private RessourceMapper ressourceMapper;

    @Autowired
    private RessourceService ressourceService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restRessourceMockMvc;

    private Ressource ressource;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final RessourceResource ressourceResource = new RessourceResource(ressourceService);
        this.restRessourceMockMvc = MockMvcBuilders.standaloneSetup(ressourceResource)
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
    public static Ressource createEntity(EntityManager em) {
        Ressource ressource = new Ressource()
            .trigramme(DEFAULT_TRIGRAMME)
            .tel(DEFAULT_TEL)
            .typeRess(DEFAULT_TYPE_RESS);
        return ressource;
    }

    @Before
    public void initTest() {
        ressource = createEntity(em);
    }

    @Test
    @Transactional
    public void createRessource() throws Exception {
        int databaseSizeBeforeCreate = ressourceRepository.findAll().size();

        // Create the Ressource
        RessourceDTO ressourceDTO = ressourceMapper.toDto(ressource);
        restRessourceMockMvc.perform(post("/api/ressources")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(ressourceDTO)))
            .andExpect(status().isCreated());

        // Validate the Ressource in the database
        List<Ressource> ressourceList = ressourceRepository.findAll();
        assertThat(ressourceList).hasSize(databaseSizeBeforeCreate + 1);
        Ressource testRessource = ressourceList.get(ressourceList.size() - 1);
        assertThat(testRessource.getTrigramme()).isEqualTo(DEFAULT_TRIGRAMME);
        assertThat(testRessource.getTel()).isEqualTo(DEFAULT_TEL);
        assertThat(testRessource.getTypeRess()).isEqualTo(DEFAULT_TYPE_RESS);
    }

    @Test
    @Transactional
    public void createRessourceWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = ressourceRepository.findAll().size();

        // Create the Ressource with an existing ID
        ressource.setId(1L);
        RessourceDTO ressourceDTO = ressourceMapper.toDto(ressource);

        // An entity with an existing ID cannot be created, so this API call must fail
        restRessourceMockMvc.perform(post("/api/ressources")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(ressourceDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Ressource in the database
        List<Ressource> ressourceList = ressourceRepository.findAll();
        assertThat(ressourceList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllRessources() throws Exception {
        // Initialize the database
        ressourceRepository.saveAndFlush(ressource);

        // Get all the ressourceList
        restRessourceMockMvc.perform(get("/api/ressources?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(ressource.getId().intValue())))
            .andExpect(jsonPath("$.[*].trigramme").value(hasItem(DEFAULT_TRIGRAMME.toString())))
            .andExpect(jsonPath("$.[*].tel").value(hasItem(DEFAULT_TEL.toString())))
            .andExpect(jsonPath("$.[*].typeRess").value(hasItem(DEFAULT_TYPE_RESS.toString())));
    }

    @Test
    @Transactional
    public void getRessource() throws Exception {
        // Initialize the database
        ressourceRepository.saveAndFlush(ressource);

        // Get the ressource
        restRessourceMockMvc.perform(get("/api/ressources/{id}", ressource.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(ressource.getId().intValue()))
            .andExpect(jsonPath("$.trigramme").value(DEFAULT_TRIGRAMME.toString()))
            .andExpect(jsonPath("$.tel").value(DEFAULT_TEL.toString()))
            .andExpect(jsonPath("$.typeRess").value(DEFAULT_TYPE_RESS.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingRessource() throws Exception {
        // Get the ressource
        restRessourceMockMvc.perform(get("/api/ressources/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateRessource() throws Exception {
        // Initialize the database
        ressourceRepository.saveAndFlush(ressource);
        int databaseSizeBeforeUpdate = ressourceRepository.findAll().size();

        // Update the ressource
        Ressource updatedRessource = ressourceRepository.findOne(ressource.getId());
        // Disconnect from session so that the updates on updatedRessource are not directly saved in db
        em.detach(updatedRessource);
        updatedRessource
            .trigramme(UPDATED_TRIGRAMME)
            .tel(UPDATED_TEL)
            .typeRess(UPDATED_TYPE_RESS);
        RessourceDTO ressourceDTO = ressourceMapper.toDto(updatedRessource);

        restRessourceMockMvc.perform(put("/api/ressources")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(ressourceDTO)))
            .andExpect(status().isOk());

        // Validate the Ressource in the database
        List<Ressource> ressourceList = ressourceRepository.findAll();
        assertThat(ressourceList).hasSize(databaseSizeBeforeUpdate);
        Ressource testRessource = ressourceList.get(ressourceList.size() - 1);
        assertThat(testRessource.getTrigramme()).isEqualTo(UPDATED_TRIGRAMME);
        assertThat(testRessource.getTel()).isEqualTo(UPDATED_TEL);
        assertThat(testRessource.getTypeRess()).isEqualTo(UPDATED_TYPE_RESS);
    }

    @Test
    @Transactional
    public void updateNonExistingRessource() throws Exception {
        int databaseSizeBeforeUpdate = ressourceRepository.findAll().size();

        // Create the Ressource
        RessourceDTO ressourceDTO = ressourceMapper.toDto(ressource);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restRessourceMockMvc.perform(put("/api/ressources")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(ressourceDTO)))
            .andExpect(status().isCreated());

        // Validate the Ressource in the database
        List<Ressource> ressourceList = ressourceRepository.findAll();
        assertThat(ressourceList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteRessource() throws Exception {
        // Initialize the database
        ressourceRepository.saveAndFlush(ressource);
        int databaseSizeBeforeDelete = ressourceRepository.findAll().size();

        // Get the ressource
        restRessourceMockMvc.perform(delete("/api/ressources/{id}", ressource.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Ressource> ressourceList = ressourceRepository.findAll();
        assertThat(ressourceList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Ressource.class);
        Ressource ressource1 = new Ressource();
        ressource1.setId(1L);
        Ressource ressource2 = new Ressource();
        ressource2.setId(ressource1.getId());
        assertThat(ressource1).isEqualTo(ressource2);
        ressource2.setId(2L);
        assertThat(ressource1).isNotEqualTo(ressource2);
        ressource1.setId(null);
        assertThat(ressource1).isNotEqualTo(ressource2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(RessourceDTO.class);
        RessourceDTO ressourceDTO1 = new RessourceDTO();
        ressourceDTO1.setId(1L);
        RessourceDTO ressourceDTO2 = new RessourceDTO();
        assertThat(ressourceDTO1).isNotEqualTo(ressourceDTO2);
        ressourceDTO2.setId(ressourceDTO1.getId());
        assertThat(ressourceDTO1).isEqualTo(ressourceDTO2);
        ressourceDTO2.setId(2L);
        assertThat(ressourceDTO1).isNotEqualTo(ressourceDTO2);
        ressourceDTO1.setId(null);
        assertThat(ressourceDTO1).isNotEqualTo(ressourceDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(ressourceMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(ressourceMapper.fromId(null)).isNull();
    }
}
