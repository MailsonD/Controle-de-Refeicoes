sudo docker build -t ctrl_refeicoes/postgres .
sudo docker run --name db_ctrl_refeicoes -p 5433:5432 -d ctrl_refeicoes/postgres
