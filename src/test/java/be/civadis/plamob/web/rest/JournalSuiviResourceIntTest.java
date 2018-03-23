package be.civadis.plamob.web.rest;

import be.civadis.plamob.PlamobApp;

import be.civadis.plamob.domain.JournalSuivi;
import be.civadis.plamob.repository.JournalSuiviRepository;
import be.civadis.plamob.service.JournalSuiviService;
import be.civadis.plamob.service.dto.JournalSuiviDTO;
import be.civadis.plamob.service.mapper.JournalSuiviMapper;
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
 * Test class for the JournalSuiviResource REST controller.
 *
 * @see JournalSuiviResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = PlamobApp.class)
public class JournalSuiviResourceIntTest {

    private static final LocalDate DEFAULT_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_EVENEMENT = "AAAAAAAAAA";
    private static final String UPDATED_EVENEMENT = "BBBBBBBBBB";

    @Autowired
    private JournalSuiviRepository journalSuiviRepository;

    @Autowired
    private JournalSuiviMapper journalSuiviMapper;

    @Autowired
    private JournalSuiviService journalSuiviService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restJournalSuiviMockMvc;

    private JournalSuivi journalSuivi;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final JournalSuiviResource journalSuiviResource = new JournalSuiviResource(journalSuiviService);
        this.restJournalSuiviMockMvc = MockMvcBuilders.standaloneSetup(journalSuiviResource)
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
    public static JournalSuivi createEntity(EntityManager em) {
        JournalSuivi journalSuivi = new JournalSuivi()
            .date(DEFAULT_DATE)
            .evenement(DEFAULT_EVENEMENT);
        return journalSuivi;
    }

    @Before
    public void initTest() {
        journalSuivi = createEntity(em);
    }

    @Test
    @Transactional
    public void createJournalSuivi() throws Exception {
        int databaseSizeBeforeCreate = journalSuiviRepository.findAll().size();

        // Create the JournalSuivi
        JournalSuiviDTO journalSuiviDTO = journalSuiviMapper.toDto(journalSuivi);
        restJournalSuiviMockMvc.perform(post("/api/journal-suivis")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(journalSuiviDTO)))
            .andExpect(status().isCreated());

        // Validate the JournalSuivi in the database
        List<JournalSuivi> journalSuiviList = journalSuiviRepository.findAll();
        assertThat(journalSuiviList).hasSize(databaseSizeBeforeCreate + 1);
        JournalSuivi testJournalSuivi = journalSuiviList.get(journalSuiviList.size() - 1);
        assertThat(testJournalSuivi.getDate()).isEqualTo(DEFAULT_DATE);
        assertThat(testJournalSuivi.getEvenement()).isEqualTo(DEFAULT_EVENEMENT);
    }

    @Test
    @Transactional
    public void createJournalSuiviWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = journalSuiviRepository.findAll().size();

        // Create the JournalSuivi with an existing ID
        journalSuivi.setId(1L);
        JournalSuiviDTO journalSuiviDTO = journalSuiviMapper.toDto(journalSuivi);

        // An entity with an existing ID cannot be created, so this API call must fail
        restJournalSuiviMockMvc.perform(post("/api/journal-suivis")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(journalSuiviDTO)))
            .andExpect(status().isBadRequest());

        // Validate the JournalSuivi in the database
        List<JournalSuivi> journalSuiviList = journalSuiviRepository.findAll();
        assertThat(journalSuiviList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllJournalSuivis() throws Exception {
        // Initialize the database
        journalSuiviRepository.saveAndFlush(journalSuivi);

        // Get all the journalSuiviList
        restJournalSuiviMockMvc.perform(get("/api/journal-suivis?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(journalSuivi.getId().intValue())))
            .andExpect(jsonPath("$.[*].date").value(hasItem(DEFAULT_DATE.toString())))
            .andExpect(jsonPath("$.[*].evenement").value(hasItem(DEFAULT_EVENEMENT.toString())));
    }

    @Test
    @Transactional
    public void getJournalSuivi() throws Exception {
        // Initialize the database
        journalSuiviRepository.saveAndFlush(journalSuivi);

        // Get the journalSuivi
        restJournalSuiviMockMvc.perform(get("/api/journal-suivis/{id}", journalSuivi.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(journalSuivi.getId().intValue()))
            .andExpect(jsonPath("$.date").value(DEFAULT_DATE.toString()))
            .andExpect(jsonPath("$.evenement").value(DEFAULT_EVENEMENT.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingJournalSuivi() throws Exception {
        // Get the journalSuivi
        restJournalSuiviMockMvc.perform(get("/api/journal-suivis/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateJournalSuivi() throws Exception {
        // Initialize the database
        journalSuiviRepository.saveAndFlush(journalSuivi);
        int databaseSizeBeforeUpdate = journalSuiviRepository.findAll().size();

        // Update the journalSuivi
        JournalSuivi updatedJournalSuivi = journalSuiviRepository.findOne(journalSuivi.getId());
        // Disconnect from session so that the updates on updatedJournalSuivi are not directly saved in db
        em.detach(updatedJournalSuivi);
        updatedJournalSuivi
            .date(UPDATED_DATE)
            .evenement(UPDATED_EVENEMENT);
        JournalSuiviDTO journalSuiviDTO = journalSuiviMapper.toDto(updatedJournalSuivi);

        restJournalSuiviMockMvc.perform(put("/api/journal-suivis")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(journalSuiviDTO)))
            .andExpect(status().isOk());

        // Validate the JournalSuivi in the database
        List<JournalSuivi> journalSuiviList = journalSuiviRepository.findAll();
        assertThat(journalSuiviList).hasSize(databaseSizeBeforeUpdate);
        JournalSuivi testJournalSuivi = journalSuiviList.get(journalSuiviList.size() - 1);
        assertThat(testJournalSuivi.getDate()).isEqualTo(UPDATED_DATE);
        assertThat(testJournalSuivi.getEvenement()).isEqualTo(UPDATED_EVENEMENT);
    }

    @Test
    @Transactional
    public void updateNonExistingJournalSuivi() throws Exception {
        int databaseSizeBeforeUpdate = journalSuiviRepository.findAll().size();

        // Create the JournalSuivi
        JournalSuiviDTO journalSuiviDTO = journalSuiviMapper.toDto(journalSuivi);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restJournalSuiviMockMvc.perform(put("/api/journal-suivis")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(journalSuiviDTO)))
            .andExpect(status().isCreated());

        // Validate the JournalSuivi in the database
        List<JournalSuivi> journalSuiviList = journalSuiviRepository.findAll();
        assertThat(journalSuiviList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteJournalSuivi() throws Exception {
        // Initialize the database
        journalSuiviRepository.saveAndFlush(journalSuivi);
        int databaseSizeBeforeDelete = journalSuiviRepository.findAll().size();

        // Get the journalSuivi
        restJournalSuiviMockMvc.perform(delete("/api/journal-suivis/{id}", journalSuivi.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<JournalSuivi> journalSuiviList = journalSuiviRepository.findAll();
        assertThat(journalSuiviList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(JournalSuivi.class);
        JournalSuivi journalSuivi1 = new JournalSuivi();
        journalSuivi1.setId(1L);
        JournalSuivi journalSuivi2 = new JournalSuivi();
        journalSuivi2.setId(journalSuivi1.getId());
        assertThat(journalSuivi1).isEqualTo(journalSuivi2);
        journalSuivi2.setId(2L);
        assertThat(journalSuivi1).isNotEqualTo(journalSuivi2);
        journalSuivi1.setId(null);
        assertThat(journalSuivi1).isNotEqualTo(journalSuivi2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(JournalSuiviDTO.class);
        JournalSuiviDTO journalSuiviDTO1 = new JournalSuiviDTO();
        journalSuiviDTO1.setId(1L);
        JournalSuiviDTO journalSuiviDTO2 = new JournalSuiviDTO();
        assertThat(journalSuiviDTO1).isNotEqualTo(journalSuiviDTO2);
        journalSuiviDTO2.setId(journalSuiviDTO1.getId());
        assertThat(journalSuiviDTO1).isEqualTo(journalSuiviDTO2);
        journalSuiviDTO2.setId(2L);
        assertThat(journalSuiviDTO1).isNotEqualTo(journalSuiviDTO2);
        journalSuiviDTO1.setId(null);
        assertThat(journalSuiviDTO1).isNotEqualTo(journalSuiviDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(journalSuiviMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(journalSuiviMapper.fromId(null)).isNull();
    }
}
