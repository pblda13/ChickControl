document.getElementById('meuFormulario').addEventListener('submit', function(event) {
    event.preventDefault();

    const nomeReprodutor = document.getElementById('nomeReprodutor').value;
    const matriz = document.getElementById('matriz').value;
    const dataNascimento = document.getElementById('dataNascimento').value;
    const quantidadeTotal = document.getElementById('quantidadeTotal').value;
    const quantidadeMachos = parseInt(document.getElementById('quantidadeMachos').value);
    const quantidadeFemeas = parseInt(document.getElementById('quantidadeFemeas').value);

    const anilhasMachos = [];
    for (let i = 1; i <= quantidadeMachos; i++) {
        const anilhaMacho = document.getElementById(`anilhaMacho${i}`).value;
        anilhasMachos.push(anilhaMacho);
    }

    const anilhasFemeas = [];
    for (let i = 1; i <= quantidadeFemeas; i++) {
        const anilhaFemea = document.getElementById(`anilhaFemea${i}`).value;
        anilhasFemeas.push(anilhaFemea);
    }

    const data = {
        nomeReprodutor,
        matriz,
        dataNascimento,
        quantidadeTotal,
        quantidadeMachos,
        quantidadeFemeas,
        anilhasMachos,
        anilhasFemeas
    };

    fetch('http://localhost:8080/api/v1/chick', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Erro ao enviar os dados');
        }
        return response.json();
    })
    .then(responseData => {
        console.log('Dados enviados com sucesso:', responseData);
        // Faça algo com a resposta da API, se necessário
    })
    .catch(error => {
        console.error('Erro:', error);
        // Lida com o erro da requisição, se necessário
    });
});
