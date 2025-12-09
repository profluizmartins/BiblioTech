**# RESUMO SOBRE O MÓDLO 8 #**
Responsável pelas funcionalidades de *Pesquisa Avançada* e *Relatórios Gerencias* do Sistema de Biblioteca.
Ele não cria nem altera dados. Ele consome informações do módulo 2 (Gestã/Acervo) e 3 (Gestão/Usuários), aplica regras de negócios e apresenta os resultados para o usuário de forma clara e organizada, utilizando a arquitetura MVC (Model, View e Controller).

 **Ele não depende de banco de dados. Funciona com interfaces, o que garante baixo acoplamento.**

(Interfaces: Define o que uma classe deve fazer, mas não define como. 
*Interface separa definição da implementação, permitindo trocar a forma como algo funciona sem alterar quem usa.*)

(Baixo acoplamento: um módulo não depende dos detalhes de outro, apenas da sua interface. 

*Baixo acoplamento significa que os módulos do sistema conversam por interfaces, e não por implementações específicas.*
*Isso permite trocar partes do sistema sem quebrar as outras.*)
 A utilização de interfaces é para definir os contratos entre os módulos.

 Permite o baixo acoplamento, pois o módulo 8 não conhece a implementação real dos repositórios - apenas as assinaturas dos métodos. Assim, podemos modificar o backend sem alterar a lógica da busca ou dos relatórios.

# Permições:
Buscas no acervo por:
 - Título
 - Autor
 - Ano
Gerar Relatórios:
 - Usuários com multas pendentes
 - Visão geral do acervo
 - Itens mais emprestados
 - Itens em atraso

**-------Arquitetura do módulo 8 --------**

- A **View** exibe telas e coleta os dados.
--> *Não* executa lógica.
--> Mostra dados e recebe cliques.

- O **Controller** interpreta as ações do usuário e executa as regras. 
--> *Não* renderiza tela.
--> Toma decisões e válida.

- O **Model** fornece as interfaces para acesso aos repositórios.
--> *Não* acessa GUI.
--> Define interfaces e exceções.

# Este módulo não conhece as implementações dos módulos 2 e 3. 
 Seu acesso são as intrfaces *IAcervoRepositorio* e *IUsuarioRepositorio*.

 O que garante baixo acoplamento, inversão de dependência e escalabilidade.
 --*Ele fala o que quer e os outros fornecem...*
 
Por que em MVC?
- Para manter a separação de responsabilidades, evitar lógica na **View** e permitir troca de repositórios sem alterar o módulo.
Isso permite o consumo de dados de outros módulos.

Por que a utilizção de duas eexceções?
- Uma válida a entrada, a outra válida o retorno. Isso evita consultas inúteis e mensagens confusas.
Tornao a estrutura objetiva e clara para o usuário.

Por que este módulo funciona sem os outros? 
- Como dito, foi utilizado interfaces. Que permite conectar mocks, banco real, ou outro módulo sem reescrever código.

# O módulo 8 permite que o sistema seja consultável, confiável e gerenciável.
# Sem ele, os dados exitem, mas o usuário não teria como obtê-los.