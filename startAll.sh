echo "CONFIGURING..."
sudo docker rmi -f 
sudo docker build -t app/refeicoes .

echo "============== BUILD PROJECT =============="

echo "============== CREATING IMAGE =============="

echo "============== INITIALIZING CONTAINER  =============="
