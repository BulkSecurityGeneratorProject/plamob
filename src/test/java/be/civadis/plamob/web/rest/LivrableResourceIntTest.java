package be.civadis.plamob.web.rest;

import be.civadis.plamob.PlamobApp;

import be.civadis.plamob.domain.Livrable;
import be.civadis.plamob.repository.LivrableRepository;
import be.civadis.plamob.service.LivrableService;
import be.civadis.plamob.service.dto.LivrableDTO;
import be.civadis.plamob.service.mapper.LivrableMapper;
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
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import static be.civadis.plamob.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the LivrableResource REST controller.
 *
 * @see LivrableResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = PlamobApp.class)
public class LivrableResourceIntTest {

    private static final Long DEFAULT_NUM_SEQ = 1L;
    private static final Long UPDATED_NUM_SEQ = 2L;

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DATE_FIN = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_FIN = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_REMARQUES = "AAAAAAAAAA";
    private static final String UPDATED_REMARQUES = "BBBBBBBBBB";

    @Autowired
    private LivrableRepository livrableRepository;

    @Autowired
    private LivrableMapper livrableMapper;

    @Autowired
    private LivrableService livrableService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restLivrableMockMvc;

    private Livrable livrable;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final LivrableResource livrableResource = new LivrableResource(livrableService);
        this.restLivrableMockMvc = MockMvcBuilders.standaloneSetup(livrableResource)
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
    public static Livrable createEntity(EntityManager em) {
        Livrable livrable = new Livrable()
            .numSeq(DEFAULT_NUM_SEQ)
            .description(DEFAULT_DESCRIPTION)
            .dateFin(DEFAULT_DATE_FIN)
            .remarques(DEFAULT_REMARQUES);
        return livrable;
    }

    @Before
    public void initTest() {
        livrable = createEntity(em);
    }

    @Test
    @Transactional
    public void createLivrable() throws Exception {
        int databaseSizeBeforeCreate = livrableRepository.findAll().size();

        // Create the Livrable
        LivrableDTO livrableDTO = livrableMapper.toDto(livrable);
        restLivrableMockMvc.perform(post("/api/livrables")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(livrableDTO)))
            .andExpect(status().isCreated());

        // Validate the Livrable in the database
        List<Livrable> livrableList = livrableRepository.findAll();
        assertThat(livrableList).hasSize(databaseSizeBeforeCreate + 1);
        Livrable testLivrable = livrableList.get(livrableList.size() - 1);
        assertThat(testLivrable.getNumSeq()).isEqualTo(DEFAULT_NUM_SEQ);
        assertThat(testLivrable.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testLivrable.getDateFin()).isEqualTo(DEFAULT_DATE_FIN);
        assertThat(testLivrable.getRemarques()).isEqualTo(DEFAULT_REMARQUES);
    }

    @Test
    @Transactional
    public void createLivrableWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = livrableRepository.findAll().size();

        // Create the Livrable with an existing ID
        livrable.setId(1L);
        LivrableDTO livrableDTO = livrableMapper.toDto(livrable);

        // An entity with an existing ID cannot be created, so this API call must fail
        restLivrableMockMvc.perform(post("/api/livrables")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(livrableDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Livrable in the database
        List<Livrable> livrableList = livrableRepository.findAll();
        assertThat(livrableList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllLivrables() throws Exception {
        // Initialize the database
        livrableRepository.saveAndFlush(livrable);

        // Get all the livrableList
        restLivrableMockMvc.perform(get("/api/livrables?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(livrable.getId().intValue())))
            .andExpect(jsonPath("$.[*].numSeq").value(hasItem(DEFAULT_NUM_SEQ.intValue())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].dateFin").value(hasItem(DEFAULT_DATE_FIN.toString())))
            .andExpect(jsonPath("$.[*].remarques").value(hasItem(DEFAULT_REMARQUES.toString())));
    }

    @Test
    @Transactional
    public void getLivrable() throws Exception {
        // Initialize the database
        livrableRepository.saveAndFlush(livrable);

        // Get the livrable
        restLivrableMockMvc.perform(get("/api/livrables/{id}", livrable.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(livrable.getId().intValue()))
            .andExpect(jsonPath("$.numSeq").value(DEFAULT_NUM_SEQ.intValue()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.dateFin").value(DEFAULT_DATE_FIN.toString()))
            .andExpect(jsonPath("$.remarques").value(DEFAULT_REMARQUES.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingLivrable() throws Exception {
        // Get the livrable
        restLivrableMockMvc.perform(get("/api/livrables/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateLivrable() throws Exception {
        // Initialize the database
        livrableRepository.saveAndFlush(livrable);
        int databaseSizeBeforeUpdate = livrableRepository.findAll().size();

        // Update the livrable
        Livrable updatedLivrable = livrableRepository.findOne(livrable.getId());
        // Disconnect from session so that the updates on updatedLivrable are not directly saved in db
        em.detach(updatedLivrable);
        updatedLivrable
            .numSeq(UPDATED_NUM_SEQ)
            .description(UPDATED_DESCRIPTION)
            .dateFin(UPDATED_DATE_FIN)
            .remarques(UPDATED_REMARQUES);
        LivrableDTO livrableDTO = livrableMapper.toDto(updatedLivrable);

        restLivrableMockMvc.perform(put("/api/livrables")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(livrableDTO)))
            .andExpect(status().isOk());

        // Validate the Livrable in the database
        List<Livrable> livrableList = livrableRepository.findAll();
        assertThat(livrableList).hasSize(databaseSizeBeforeUpdate);
        Livrable testLivrable = livrableList.get(livrableList.size() - 1);
        assertThat(testLivrable.getNumSeq()).isEqualTo(UPDATED_NUM_SEQ);
        assertThat(testLivrable.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testLivrable.getDateFin()).isEqualTo(UPDATED_DATE_FIN);
        assertThat(testLivrable.getRemarques()).isEqualTo(UPDATED_REMARQUES);
    }

    @Test
    @Transactional
    public void updateNonExistingLivrable() throws Exception {
        int databaseSizeBeforeUpdate = livrableRepository.findAll().size();

        // Create the Livrable
        LivrableDTO livrableDTO = livrableMapper.toDto(livrable);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restLivrableMockMvc.perform(put("/api/livrables")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(livrableDTO)))
            .andExpect(status().isCreated());

        // Validate the Livrable in the database
        List<Livrable> livrableList = livrableRepository.findAll();
        assertThat(livrableList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteLivrable() throws Exception {
        // Initialize the database
        livrableRepository.saveAndFlush(livrable);
        int databaseSizeBeforeDelete = livrableRepository.findAll().size();

        // Get the livrable
        restLivrableMockMvc.perform(delete("/api/livrables/{id}", livrable.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Livrable> livrableList = livrableRepository.findAll();
        assertThat(livrableList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Livrable.class);
        Livrable livrable1 = new Livrable();
        livrable1.setId(1L);
        Livrable livrable2 = new Livrable();
        livrable2.setId(livrable1.getId());
        assertThat(livrable1).isEqualTo(livrable2);
        livrable2.setId(2L);
        assertThat(livrable1).isNotEqualTo(livrable2);
        livrable1.setId(null);
        assertThat(livrable1).isNotEqualTo(livrable2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(LivrableDTO.class);
        LivrableDTO livrableDTO1 = new LivrableDTO();
        livrableDTO1.setId(1L);
        LivrableDTO livrableDTO2 = new LivrableDTO();
        assertThat(livrableDTO1).isNotEqualTo(livrableDTO2);
        livrableDTO2.setId(livrableDTO1.getId());
        assertThat(livrableDTO1).isEqualTo(livrableDTO2);
        livrableDTO2.setId(2L);
        assertThat(livrableDTO1).isNotEqualTo(livrableDTO2);
        livrableDTO1.setId(null);
        assertThat(livrableDTO1).isNotEqualTo(livrableDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(livrableMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(livrableMapper.fromId(null)).isNull();
    }
}
