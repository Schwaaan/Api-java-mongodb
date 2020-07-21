# Api-java-mongodb
Api java utilizando banco de dados mongodb.


Para utilizar essa api você deverá seguir as seguintes etapas : 

1 - Você deve instalar o docker em sua máquina. 

2 - Subir uma imagem do mongo no docker na porta padrão do MongoDB(27017) e sem senha.
   
   2.1 - Comandos necessários para subir a imagem do MongoDB:
    
      1 - docker pull mongo - aqui vai baixar a imagem do mongodb
      2 - docker run -d -p 27017:27017 -p 28017:28017 -e AUTH=no mongo - aqui vc vai criar um container com a imagem que foi baixada e suas configurações
      3 - docker ps -a - vai listar todos os containers existentes na maquina.
      4 - docker start {id do container} - aqui vc vai iniciar o container que contém a imagem do MongoDB
  

3 - Iniciar a aplicação.


Para ver a documentação você deve acessar http://localhost:8080/swagger-ui.html.
