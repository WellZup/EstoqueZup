package br.com.gerenciamentozupestoque;



import br.com.gerenciamentozupestoque.controller.EstoqueController;
import br.com.gerenciamentozupestoque.domain.dto.EstoqueDTO;
import br.com.gerenciamentozupestoque.domain.entity.Estoque;
import br.com.gerenciamentozupestoque.service.EstoqueService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class TestEstoqueController {
    @Autowired
    private MockMvc mockMvc;

    @Mock
    private EstoqueService estoqueService;

    @InjectMocks
    private EstoqueController estoqueController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(estoqueController).build();
    }

    @Test
    public void testCriarEstoque() throws Exception {
        EstoqueDTO estoqueDTO = new EstoqueDTO();
        estoqueDTO.setTitulo("The King");
        estoqueDTO.setQuantidade(10);

        when(estoqueService.criarEstoque(Mockito.any(EstoqueDTO.class))).thenReturn(new Estoque());

        mockMvc.perform(get("/v1/estoque/criar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"titulo\": \"The King\", \"qantidade\": \"10\"}"))
                .andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andDo(print());

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

        mockMvc.perform(get("/v1/estoque/buscarTodos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].titulo").value("The King"))
                .andExpect(jsonPath("$[1].id").value(2L))
                .andExpect(jsonPath("$[1].titulo").value("The Thief"))
                .andDo(print());

    }

    @Test
    public void testBuscarEstoquePorId() throws Exception {
        Long estoqueId = 1L;

        Estoque estoque = new Estoque();
        estoque.setId(estoqueId);
        estoque.setTitulo("The King");

        when(estoqueService.buscarPorId(estoqueId)).thenReturn(estoque);

        mockMvc.perform(get("/v1/estoque/buscarId/{id}", estoqueId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(estoqueId))
                .andExpect(jsonPath("$.titulo").value("The King"))
                .andDo(print());
    }

    @Test
    public void testAtualizarEstoque() throws Exception {
        Long estoqueId = 1L;
        EstoqueDTO estoqueDTO = new EstoqueDTO();
        estoqueDTO.setTitulo("The King 2");

        Estoque estoqueAtualizado = new Estoque();
        estoqueAtualizado.setId(estoqueId);
        estoqueAtualizado.setTitulo(estoqueDTO.getTitulo());

        mockMvc.perform(put("/v1/estoque/atualizarEstoque/{id}", estoqueId)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(TestUtils.asJsonString(estoqueDTO)))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeletarEstoque() throws Exception {
        Long estoqueId = 1L;

        mockMvc.perform(delete("/v1/estoque/deletarEstoque/{id}", estoqueId))
                .andExpect(status().isNoContent());
    }

}
