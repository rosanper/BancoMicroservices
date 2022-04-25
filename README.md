# Banco-Microservices

Atividade do curso Santander Coders | Web Full-Stack da Let's Code.

### Requisitos da atividade:

Implementar uma comunicação entre dois microserviços de um banco digital, que possui estrutura semelhante à exibida nesta imagem.

![image](https://user-images.githubusercontent.com/86617390/165172821-f68668b5-6e8f-40d3-89ba-18c59051c2bb.png)

   
### Desenvolvimento e Organização do código:

O código é desenvolvido a partir do Spring boot, utilizando o banco de dados H2 e a dependência JPA para manipulação do banco de dados. 
Outras dependências utilizadas no projeto são:

- Swagger: Para a documentação da API;
- Lombok: Remover a verbosidade do código;
- Bean Validation: Auxiliar na validação dos dados.

Foram desenvolvidos dois micro serviços, um para cadastro de usuário e outro para a criação de conta. 

O usuariomicroservice é responsável por cadastrar o usuário e todas as operações de CRUD dele. Para criar um novo usuário é necessário passar o nome, o cpf e a senha, 
e antes da criação é verificado no banco se já existe algum cadastro com o cpf passado.

O contamicroservice é responsável por criar uma conta. Para a criação da conta é ncessário passar a agencia, o saldo, o tipoConta (que é um ENUM com as opções: 
CONTA_POUPANCA e CONTA_CORRENTE), o nome da pessoa atrelado a conta e seu cpf. É feito uma consulta no usuariomicroservice para obter os dados dessa pessoa,
e caso ela não seja cadastrada é feito esse cadastro.

O usuaio usuariomicroservice está rodando na porta 8080, enquanto o contamicroservice está rodando na porta 8081.

OBS: Foi feito a tentativa de utilização do PostgreSQL no projeto de usuário, porém este apresentou problema que não foi possível solucionar antes da data atual. 
Por esse motivo foi utilizado em ambos o banco H2.
