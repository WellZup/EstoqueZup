package br.com.gerenciamentozupestoque;

import br.com.gerenciamentozupestoque.domain.dto.EstoqueDTO;
import br.com.gerenciamentozupestoque.domain.entity.Estoque;
import br.com.gerenciamentozupestoque.repository.EstoqueRepository;
import br.com.gerenciamentozupestoque.service.EstoqueService;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


public class TestEstoqueService {
    @Mock
    private EstoqueRepository estoqueRepository;
    @InjectMocks
    private EstoqueService estoqueService;
    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(estoqueService).build();
    }



    @Test
    public void testCriarEstoque() throws Exception {
        EstoqueDTO estoqueDTO = new EstoqueDTO();
        estoqueDTO.setTitulo("The King");
        estoqueDTO.setPlataforma("PS5");
        estoqueDTO.setPreco(BigDecimal.valueOf(99.99));
        estoqueDTO.setQuantidade(10);

        Estoque estoqueSimulado = new Estoque();
        estoqueSimulado.setTitulo(estoqueDTO.getTitulo());
        estoqueSimulado.setPlataforma(estoqueDTO.getPlataforma());
        estoqueSimulado.setPreco(estoqueDTO.getPreco());
        estoqueSimulado.setQuantidade(estoqueDTO.getQuantidade());

        when(estoqueRepository.save(any(Estoque.class))).thenReturn(estoqueSimulado);

        Estoque estoqueCriado = estoqueService.criarEstoque(estoqueDTO);

        assertNotNull(estoqueCriado);
        assertEquals("The King", estoqueCriado.getTitulo());
        assertEquals("PS5", estoqueCriado.getPlataforma());
        assertEquals(BigDecimal.valueOf(99.99), estoqueCriado.getPreco());
        assertEquals(10, estoqueCriado.getQuantidade());

        verify(estoqueRepository, times(1)).save(any(Estoque.class));

    }

    @Test
    public void testBuscarPorId() {
        Long id = 1L;
        Estoque estoque = new Estoque();
        estoque.setTitulo("The King");
        estoque.setPlataforma("PS5");
        estoque.setPreco(BigDecimal.valueOf(50.99));
        estoque.setQuantidade(10);

        when(estoqueRepository.findById(id)).thenReturn(Optional.of(estoque));

        Estoque estoqueEncontrado = estoqueService.buscarPorId(id);

        assertNotNull(estoqueEncontrado);
        assertEquals("The King", estoqueEncontrado.getTitulo());

    }



    @Test
    public void testAtualizarEstoque() {
        Long id = 1L;
        EstoqueDTO estoqueDTO = new EstoqueDTO();
        estoqueDTO.setTitulo("The King2");
        estoqueDTO.setPlataforma("PS5");
        estoqueDTO.setPreco(BigDecimal.valueOf(100));
        estoqueDTO.setQuantidade(10);

        Estoque estoqueAntigo = new Estoque();
        estoqueAntigo.setTitulo("The King");
        estoqueAntigo.setPlataforma("PS4");
        estoqueAntigo.setPreco(BigDecimal.valueOf(100));
        estoqueAntigo.setQuantidade(10);

        when(estoqueRepository.findById(id)).thenReturn(Optional.of(estoqueAntigo));
        when(estoqueRepository.save(any())).thenReturn(estoqueAntigo);

        Estoque estoqueAtualizado = estoqueService.atualizarEstoque(id, estoqueDTO);

        assertNotNull(estoqueAtualizado);
        assertEquals("The King2", estoqueAtualizado.getTitulo());
        assertEquals("PS5", estoqueAtualizado.getPlataforma());
        assertEquals(BigDecimal.valueOf(100), estoqueAtualizado.getPreco());
        assertEquals(10, estoqueAtualizado.getQuantidade());


    }

    @Test
    public void buscarTodoEstoque() throws Exception {
        Estoque estoque1 = new Estoque();
        estoque1.setId(1L);
        estoque1.setTitulo("The King");

        Estoque estoque2 = new Estoque();
        estoque2.setId(2L);
        estoque2.setTitulo("The Thief");

        List<Estoque> estoques = Arrays.asList(estoque1, estoque2);

        when(estoqueService.buscarTodoEstoque()).thenReturn(estoques);

        assertEquals(2,estoques.size());
    }



    @Test
    public void testDeletarEstoque() {

        Long id = 1L;


        when(estoqueRepository.existsById(id)).thenReturn(true);
        doThrow(EntityNotFoundException.class).when(estoqueRepository).deleteById(id);

        assertThrows(EntityNotFoundException.class, ()-> estoqueRepository.deleteById(id));

    }
}



