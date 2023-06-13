Installation de l'application
1 : Se deplacer dans le repertoire /front-angular
2 : Construire l'image Docker avec la commande docker build -t  pandemic-app . 
3 : Exécuter l'application avec la commande   docker run -d -p 4200:80/tcp --name pandemic-app pandemic-app
