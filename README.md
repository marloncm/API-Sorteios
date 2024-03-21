#Título

API de Sorteios

###Resumo

Este programa se trata de uma API REST para gerenciamento de sorteios.
O projeto foi criado utilizando Java 17 com Spring Boot 3.2.3, Maven na versão 4.0 e o banco de dados utilizado é o MySQL através do XAMPP

###Descrição

O objetivo deste programa é gerenciar um sistema de sorteio de números e tem o seguinte fluxo:
1. O usuário começa criando um novo sorteio. no momento da criação o programa já gera 5 números vencedores mas não os informa ainda, somente salva no banco de dados.
2. O usuário pode criar quantas apostas quiser, e cada aposta deve conter o nome do apostador, CPF do mesmo, e os 5 números escolhidos por ele.
2.1. Caso queira, o usuário pode optar por apenas informar nome e CPF e deixar que o sistema gere os 5 números para ele.
3. Quando todas as apostas forem registradas deve-se finalizar a fase de apostas e ir para a fase de apuração dos resultados.
3.1 O sistema vai conferir os números sorteados e criar uma lista com as apostas vencedoras.
3.2 Caso não haja nenhum vencedor até então, será gerado um novo número sorteado e com ele uma nova apuração. Isso se repete até que sejam gerados 25 números no total.
4. Assim que tudo estiver terminado, o sistema mostra uma lista com todos os vencedores, ou um aviso de Zero vencedores.
   
###Como Utilizar

Todas as requisições podem ser feitas com um gerenciador como o Postman, mas também pode ser usada a página web que criei especificamente para fazer os acessos no servidor.
a página vai estar em uma pasta separada, zipada junto com este programa.

#### Passo a Passo com Postman

1. Com a API já em execução, abrir o postman e fazer uma requisição POST para http://localhost:8080/draws/startNewDraw (sem passar nenhum argumento)
2.1.  Fazer quantas requisições POST quiser no endereço http://localhost:8080/bets informando um json no formato:
{
  betterName: "nome do apostador",
  cetterCPF: "cpf do apostador",
  chosenNumbers: [num1, num2, num3, num4, num5]
}

2.2 Ou no endereço http://localhost:8080/bets/surprise no formato:
{
  betterName: "nome do apostador",
  cetterCPF: "cpf do apostador"
}

3. Para finalizar as apostas basta acessar o endpoint http://localhost:8080/draws/makeDraw
4. Acessar http://localhost:8080/draws/winners para receber uma lista com todas as apostas vencedoras.
4.1 Para ter o número total de vencedores, http://localhost:8080/draws/getTotalWinners
4.2 Para saber quais números foram sorteados, http://localhost:8080/draws/getDrawnNumbers
4.3 E para listar todas as apostas feitas para esse sorteio, http://localhost:8080/draws/getAllBets


####Passo a Passo com a interface WEB

1. Iniciar abrindo a página pelo index.html e clicando em Novo Sorteio.
2.1. Inserir quantas apostas quiser preenchendo todo o formulário e clicando em Confirmar Aposta para cada aposta.
2.2 Preencher somente os campos Nome e CPF e clicando em Sorteio Surpresa.
3. Clicar em Finalizar apostas. você será redirecionado para uma página com os resultados da apuração
4. Aqui será mostrado a relação dos vencedores e os seguintes botões:
4.1. Resgatar Prêmio -> O usuário será redirecionado para a página de premiação
4.2 Voltar para o Inicio -> redireciona para a página inicial onde o usuário pode optar por realizar um novo sorteio
4.3 Mostrar todas as apostas -> A página mostra uma lista com todas as apostas, contendo o nome do apostador e os números escolhidos por ele.

###Status do Projeto

O projeto está parcialmente funcional. Todas as requisições funcionam, menos a responsável por finalizar a fase de apostas e apurar o resultado.
Erro: ela está tentando criar uma chave duplicada ao tentar adicionar uma aposta para a lista de vencedoras mesmo quando não existe uma aposta vencedora.

###Premiação

O prêmio deste sorteio é poder conhecer um pouco mais a pessoa que desenvolveu esse projeto :smirk:
Sinta-se livre para visitar meu perfil e mandar alguma mensagem. Mesmo que não tenha ganho o sorteio, você ainda pode ganhar um ótimo profissinal na sua equipe :blush:

