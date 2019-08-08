mvn clean package
sudo docker build -t app/refeicoes .
sudo docker run --name ctn_app_refeicoes -p 8080:8080 -d app/refeicoes
