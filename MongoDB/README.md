## Choix de conception
Nous avons choisi d'utiliser le SGBD MongoDB étudié cette année.
Nous aurons dans celui-ci une base de données **Pandemic** contenant nos collections :

 - Maladies *(Les différentes maladies infectants les villes)* de la forme :
```json
{
	"_id": "maladie:1",
	"couleur": "Bleue"
}
```
 - Villes *(Les différentes villes du jeu)* de la forme :
```json
{
	"_id": "ville:1",
	"nom": "Atlanta",
	"population": 475000,
	"maladieParDefaut": {
		"_id": "maladie:1"
	},
	"villesAlentours": [
		{
			"_id": "ville:2"
		},
		{
			"_id": "ville:12"
		},
		{
			"_id": "ville:15"
		}
	]
}
```
 - Parties *(Les différentes parties enregistrées avec leurs informations)* de la forme :
 ```json
{
    "_id": "partie:1",
    "joueurCourant": "Joueur 3",
    "joueurs": [
      {
        "pseudo": "Joueur 1",
        "role": "EXPERT_AUX_OPERATIONS",
        "emplacement": { "_id": "ville:1" },
        "cartesVille": [ { "_id": "ville:42" }, { "_id": "ville:36" } ],
        "cartesEvenement": [ "PREVISION" ]
      },
      {
        "pseudo": "Joueur 3",
        "role": "CHERCHEUSE",
        "emplacement": { "_id": "ville:1" },
        "cartesVille": [ { "_id": "ville:15" }, { "_id": "ville:14" } ],
        "cartesEvenement": [ "SUBVENTION_PUBLIQUE" ]
      },
      {
        "pseudo": "Joueur 4",
        "role": "MEDECIN",
        "emplacement": { "_id": "ville:1" },
        "cartesVille": [
          { "_id": "ville:4" },
          { "_id": "ville:25" },
          { "_id": "ville:47" }
        ],
        "cartesEvenement": []
      }
    ],
    "plateau": {
      "maladies": [
        {
          "_id": "maladie:1",
          "cubesRestants": 15,
          "remede": false,
          "eradique": false
        },
        {
          "_id": "maladie:2",
          "cubesRestants": 18,
          "remede": false,
          "eradique": false
        },
        {
          "_id": "maladie:3",
          "cubesRestants": 22,
          "remede": false,
          "eradique": false
        },
        {
          "_id": "maladie:4",
          "cubesRestants": 23,
          "remede": false,
          "eradique": false
        }
      ],
      "stations": [ { "_id": "ville:1" } ],
      "compteurEclosion": 0,
      "indicePropagation": 0,
      "defausseJoueurVille": [],
      "defausseJoueurEvenement": [],
      "defausseJoueurEpidemie": 0,
      "piocheJoueurVille": [
        { "_id": "ville:29" }, { "_id": "ville:23" },
        { "_id": "ville:3" },  { "_id": "ville:41" },
        { "_id": "ville:2" },  { "_id": "ville:28" },
        { "_id": "ville:7" },  { "_id": "ville:27" },
        { "_id": "ville:21" }, { "_id": "ville:9" },
        { "_id": "ville:24" }, { "_id": "ville:43" },
        { "_id": "ville:8" },  { "_id": "ville:39" },
        { "_id": "ville:5" },  { "_id": "ville:34" },
        { "_id": "ville:16" }, { "_id": "ville:6" },
        { "_id": "ville:11" }, { "_id": "ville:33" },
        { "_id": "ville:44" }, { "_id": "ville:31" },
        { "_id": "ville:45" }, { "_id": "ville:46" },
        { "_id": "ville:38" }, { "_id": "ville:17" },
        { "_id": "ville:37" }, { "_id": "ville:12" },
        { "_id": "ville:10" }, { "_id": "ville:20" },
        { "_id": "ville:40" }, { "_id": "ville:48" },
        { "_id": "ville:32" }, { "_id": "ville:35" },
        { "_id": "ville:30" }, { "_id": "ville:13" },
        { "_id": "ville:1" },  { "_id": "ville:26" },
        { "_id": "ville:22" }, { "_id": "ville:19" },
        { "_id": "ville:18" }
      ],
      "piocheJoueurEvenement": [
        "PAR_UNE_NUIT_TRANQUILLE",
        "PONT_AERIEN",
        "POPULATION_RESILIENTE"
      ],
      "piocheJoueurEpidemie": 0,
      "defaussePropagation": [
        { "_id": "ville:18" },
        { "_id": "ville:30" },
        { "_id": "ville:8" },
        { "_id": "ville:11" },
        { "_id": "ville:38" },
        { "_id": "ville:24" },
        { "_id": "ville:10" },
        { "_id": "ville:6" },
        { "_id": "ville:19" }
      ],
      "piochePropagation": [
        { "_id": "ville:48" }, { "_id": "ville:14" },
        { "_id": "ville:27" }, { "_id": "ville:1" },
        { "_id": "ville:17" }, { "_id": "ville:16" },
        { "_id": "ville:37" }, { "_id": "ville:40" },
        { "_id": "ville:4" },  { "_id": "ville:39" },
        { "_id": "ville:21" }, { "_id": "ville:7" },
        { "_id": "ville:36" }, { "_id": "ville:33" },
        { "_id": "ville:15" }, { "_id": "ville:9" },
        { "_id": "ville:23" }, { "_id": "ville:5" },
        { "_id": "ville:22" }, { "_id": "ville:29" },
        { "_id": "ville:26" }, { "_id": "ville:45" },
        { "_id": "ville:31" }, { "_id": "ville:2" },
        { "_id": "ville:42" }, { "_id": "ville:35" },
        { "_id": "ville:32" }, { "_id": "ville:28" },
        { "_id": "ville:25" }, { "_id": "ville:43" },
        { "_id": "ville:20" }, { "_id": "ville:47" },
        { "_id": "ville:41" }, { "_id": "ville:44" },
        { "_id": "ville:12" }, { "_id": "ville:46" },
        { "_id": "ville:34" }, { "_id": "ville:13" },
        { "_id": "ville:3" }
      ]
    },
    "partieTerminee": false
}
 ```

Nous pourrons donc faire la correspondance entre nos collections grâce aux identifiants `"_id"`.
Cette dernière collection sera utilisée à des fins d'historique de parties également : 
- On insère le document à la fin du premier tour de jeu.
- On met à jour le document d'une partie à chaque tour.
Pour des raisons de simplicités, nous avons scindé les cartesJoueurs en 3 groupe (CartesEvenements, CartesVilles et CartesEpidemies) qui ont donc chacuns leur pioche et défausse. Celles-ci seront fusionnées à nouveau quand on les récupèrera en jeu.

## Arborescence

Vous trouverez, dans le dossier accompagnant ce fichier, 2 autres éléments :
 - Un fichier `docker-compose.yml`
 - Un dossier `data` 

**1) docker-compose**

Le fichier `docker-compose.yml` contient les instructions pour la création de notre SGBD.
```
version: '3.8'

services:
  
  # Database - Mongo DB
  pandemic:
	image: mongo:6.0.3
	restart: unless-stopped
	container_name: pandemicdb
	ports:
	  - 27017:27017
  
  # Une commande par import
  seed:
	image: mongo:6.0.3
	container_name: pandemic_seed
	command: bash -c "
		mongoimport --host pandemicdb --db pandemic --collection maladies --type json --file /data/maladies.json --jsonArray 
		&& mongoimport --host pandemicdb --db pandemic --collection villes --type json --file /data/villes.json --jsonArray
	  "
	volumes:
	  - ./data:/data
	depends_on:
	  - pandemic
```

- Le service `pandemic` représente notre conteneur Mongo utilisé par l'application.
 - Le service `seed` est un conteneur qui sera utilisé pour effectuer l'importation automatique des données dans le conteneur `pandemic`.

**2) Data**

Le dossier data est un dossier mappé sur le conteneur temporaire `pandemic_seed` pour utiliser les fichier JSON fournis à l'intérieur afin d'importer les données dans notre BDD.

## Création de notre BDD
> /!\ Nos manipulations ont été effectuées sur Windows et n'ont pas été testées sur Linux /!\

Pour créer notre Docker, il suffit d'exécuter la commande `docker-compose up -d` dans le dossier `MongoDB`.

Le conteneur `pandemicdb` sera créé avec notre bdd Mongo et un conteneur `pandemic_seed` qui remplira la bdd et s'éteindra. 

> À noter qu'en relançant le conteneur avec `docker start pandemic_seed`, on réinjecte les données dans notre bdd, ce qui peut s'avérer utile dans certains cas

