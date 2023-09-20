document.addEventListener('DOMContentLoaded', function () {
    const estoqueForm = document.getElementById('estoqueForm');
    const estoqueTableBody = document.getElementById('estoqueTableBody');

    // Função para buscar e exibir os itens do estoque
    function exibirItensEstoque() {
        fetch('/v1/estoque', {
            method: 'GET',
        })
        .then(response => response.json())
        .then(data => {
            // Limpa a tabela
            estoqueTableBody.innerHTML = '';

            // Adiciona os itens do estoque à tabela
            data.forEach(item => {
                const newRow = document.createElement('tr');
                newRow.innerHTML = `
                    <td>${item.titulo}</td>
                    <td>${item.plataforma || ''}</td>
                    <td>${item.quantidade}</td>
                    <td>${item.preco || ''}</td>
                    <td>
                        <button class="btn btn-danger" onclick="deletarItem(${item.id})">Excluir</button>
                    </td>
                `;
                estoqueTableBody.appendChild(newRow);
            });
        })
        .catch(error => console.error('Erro ao buscar itens do estoque:', error));
    }

    // Função para adicionar um novo item ao estoque
    estoqueForm.addEventListener('submit', function (e) {
        e.preventDefault();

        const formData = new FormData(estoqueForm);
        const data = {};
        formData.forEach((value, key) => {
            data[key] = value;
        });

        fetch('/v1/estoque', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(data),
        })
        .then(() => {
            // Limpa o formulário após a adição do item
            estoqueForm.reset();
            exibirItensEstoque(); // Atualiza a tabela após a adição
        })
        .catch(error => console.error('Erro ao adicionar item:', error));
    });

    // Função para deletar um item do estoque
    function deletarItem(id) {
        fetch(`/v1/estoque/${id}`, {
            method: 'DELETE',
        })
        .then(() => {
            exibirItensEstoque(); // Atualiza a tabela após a exclusão
        })
        .catch(error => console.error(`Erro ao deletar item ${id}:`, error));
    }

    // Exibe os itens do estoque ao carregar a página
    exibirItensEstoque();
});
