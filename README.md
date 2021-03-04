## Be_Fast


# Atividade 1

1. Toggle button
    - Na tela de login, ao digitiar a senha, o button serve para mostrar e esconder a senha.

2. EditText
    - Em telas como Login e Registrar é possível encontrar os EditText.
  
3. Arrays[Lists]
    - Cada Usuário do sistema possui uma lista de serviços: List<Servico> servicos = new Arraylist<>(); Esta lista armazena, caso o usuário seja um prestador de serviço, cada um deles especificando hora, nome e valor.
  
4. Autocomplete
   - Não implementado.
 
5. Spinners
    - Ao se registrar no App é necessário indiciar o sexo, esse processo acontece dentro de um Spinner.
  
6. RadioButtons
    - Na lista de Serviços do usuário é possível percebelos em cima de cada item da lista. Sua função é de indiciar os itens selecionados pelo usuário
   
7. OptionsMenu
    - Ao se logar no App é possível percebe-los no canto superior direito
    
8. Popup Menu
    - Não implementado.
   
9. Como usar Menus
    - Não precisa ser implementado. Apenas boas práticas
    
10. Adicionar clique longo em um componenete de tela
    - Não implementado.

11. Imagem de fundo
    - Todas as telas do app possuem imagem de fundo
  
12. Navegação de telas
    - Página referenciada no trabalho não funciona mais
   
13. Activities	com	múltiplas	Tabs
    - Página referenciada no trabalho não funciona mais
    - Fragments são utilizados para alterar entre os itens da Navagation 

14. ListView
    - Dentro de um usuário logado, na parte de Serviços, se houver qualquer serviço cadastrado será mostrado em forma de lista.
  
15. ArrayAdapter
    - Os serviços do usuário são mostrados em listViews e possuem tanto ArrayAdapater quanto ViewHolder para fazer o tratamento dos dados

16. GridView
    - Dentro de um usuário logado, na parte de Serviços, se houver qualquer serviço cadastrado será mostrado em forma de lista dentro de um RecyclerView.
  
17. Tocar um som
    - Não implementado.

# Atividade 2
  Ao invés de Activities contendo a list de itens inseridas pelo usuário foi utilizado Fragments.
  
    - Ao logar no app, dirija-se a área de Serviços
    - Se não houve nenhum, irá aparecer: "Nenhum serviço cadastrado"
    - caso haja ao menos um, irá aparecer uma lista dos serviços que foram cadastrado
    - todos esses serviços possuem um indicador único para o usuário
  
  Para cada item da lista há um RadioButton para indicar aquele na qual o usuário quer realizar o CRUD
  
  # Atividade 3
   Todos os dados inseridos no app são salvos no Firebase Database, Firebase Authenticator e Firebase Storage
   
   
  # Atividade 4
   Não implementado.
   
    
