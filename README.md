Foram criadas 5 classes para comunicação onde as classes sao conectadas através da classe ConnectionFactory, essa classe é chamada sempre que é precisa fazer a conexão entre as classes e o banco.
O Banco de dados foi criado no postgree onde o mesmo contém as tabelas usuario e evento, a tabela usuario contém cinco colunas e a tabela eventos contem 8 colunas, esse banco recebe as informações através do codico ja implementado no software eclipse.

A classe DaoEvento contém as funções mostrarEvento, buscaEvento, excluiEvento, atualizaEvento, insereEvento, encodeToString, decodeToImage. 
a função mostraEventos serve para mostrar todos os eventos que foram cadastrados.
a função buscaEvento serve para buscar o eventos que ja foi cadastro.
a função excluiEvento serve para exclui o evento cadastrado, ele faz a exclusão através do ID cadastrado!
A função atualizaEvento serve para atualizar o evento ja cadastrado, ele atualiza qualquer coluna que o cliente queira
A função insereEvnto serva para cadastrar o evento, ele so pode ser cadastrado desde que ja haja algum usuario cadastrado
A função encodeToString serve para receber uma imagem como String e transforma-la para base64
A função decodeToImage serve para decodificar a imagem que está em base64 para seu formato real.

A classe  DaoUsuario contém as funções mostraUsuario, buscaUsuarioEventos, buscaUsuario, excluiUsuario, atualizaUsuario, insereUsuario.
A Função mostraUsuario serve para mostrar todos os usuarios que ja foram cadastrados no banco de dados
A função buscaUsuaurioEvento busca o usuario apenas em um unico evento
A função buscausuario serve para fazer uma busca de um usuario que possa ter sido cadastrados em varios eventos
A função excluiUsuario faz a busca em todos eventos e exclui o usuario selecionado em todos os eventos
A função atualizaUsuario serve para modificar os usuarios em determinados eventos
A Função insereUsuario cadastra os usuario no banco de dados

A Classe Usuario foi criada para fazer a leitura e armazenar as informações que são passadas.
a classe recebe as informações Nome que é passado como uma String, a informação senha que é passada como String,a informação email que é passada como uma String, e a informação datacriação, essa recebe a data através da função timestamp, essa função busca no computador e passa para o banco automaticamente.

A Classe Evento foi criada para fazer a leitura e armazenar as informações que são passadas.
a classe receb as informações foto, essa foto é tirada pelo usuario e armazenada e convertida para base64 para o banco de dados, recebe a informação descrição, essa vai passar toda a informação sobre a foto que foi tirada e é armazeda no banco de dados como texto
a informação tag, essa serve para avaliar o nivel de reclamação sobre a qual está sendo feita.
a informação longitude e latitude para sabermos onde foi tirada a foto
e a informação datahora que é implementada através da função timestamp, essa faz atualização automatica da hora e data na hora que for armazenado no banco de dados
