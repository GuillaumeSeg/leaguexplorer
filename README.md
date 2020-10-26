## LeagueXplorer
Football Android application with thesportsdb API 

L'application se compose de 3 écrans qui sont le **Splashscreen**, la **Homescreen** et la page **Details équipe**.
## Principe
Le principe de l'application : l'utilisateur arrive sur la Homepage, il peut saisir une ligue dans la barre de recherche sur l'appbar, une fois la ligue rentrée, les équipes en compétition dans cette ligue s'affiche sous forme de liste de badge d'équipe. L'utilisateur peut ensuite choisir de cliquer sur une équipe pour accèder aux informations détaillées de l'équipe dans une nouvelle page (notamment la bannière de l'équipe, son nom, le pays,
la ligue, sa description et un lien vers son site web officiel.

## Techno
Le projet est écrit en Kotlin. J'utilise Koin pour l'injection de dépendances.
J'utilise Glide pour loader les images reçues du serveur dans l'app. L'animation du Splashscreen est une animation lottie en JSON (http://airbnb.io/lottie/)
Pour les layouts, j'utilise les ConstraintLayouts pour le Splashscreen, un relativeLayout pour la Home, et un LinearLayout pour la page de détails équipe.
J'utilise les coroutines pour les requetes serveurs, ainsi que Retrofit et Moshi pour le parsing JSON.
Pour la partie tests, j'ai utilisé Junit et Mockk.
Un des points de l'exercice était de ne pas utiliser Rx, cela m'a posé problème au début car je l'utilise quasiment sur tous mes projets.

## API
Pour requeter les données sportives des ligues et équipe, j'ai utilisé l'api https://www.thesportsdb.com, avec Retrofit et Moshi pour parser.

## Architecture
L'architecture choisi pour le projet est MVP (Model View Presenter).
L'organisation est fait en Clean Architecture, avec une couche Presentation : une couche qui intéragis avec L'UI -> c'est là qu'on va avoir nos trois écrans Home/Splash/details.
une couche Domain : qui contient la logique de l'app pour la récupération des données, ainsi que les différents classes des modèles.
et la couche Data : qui va avoir la définition des sources de données (ici seulement les données venant des serveurs)

## Gestion erreurs
Quand l'utilisateur a un soucis de connexion, les requêtes ne passent pas et il est averti par un message Toast, que sa connexion pose problème, qu'il doit réessayer plus tard.
Pareil si les resultats des équipes sont completement vide, l'utilisateur est averti par un Toast qu'il n'y a aucun résultat pour sa requête (je me suis aperçu que pour certaines ligues, il n'y avait pas de résultats d'équipe reçu.)

## Feedbacks
J'ai pris beaucoup de plaisir à développer ce projet autant sur le plan UI design que sur l'aspect back et requêtes serveurs, je pense que je vais continuer à le maintenir et le mettre en avant, peut être mettre l'application en production sur le playstore en faisant une release avec proguard activé.
J'aimerai aussi récupérer les différents crashs via crashlytics à l'avenir, améliorer les features, ajouter des nouveaux tests.

![Splashscreen|2200x2200,20%](https://github.com/GuillaumeSeg/leaguexplorer/blob/master/documentation/mockup_1.png "Splashscreen")
![Home Page 1|2200x2200,20%](https://github.com/GuillaumeSeg/leaguexplorer/blob/master/documentation/mockup_2.png "Home Page 1")
![Home autocompletion|2200x2200,20%](https://github.com/GuillaumeSeg/leaguexplorer/blob/master/documentation/mockup_3.png "Home autocompletion")
![Home list teams|2200x2200,20%](https://github.com/GuillaumeSeg/leaguexplorer/blob/master/documentation/mockup_4.png "Home list teams")
![Team details page|2200x2200,20%](https://github.com/GuillaumeSeg/leaguexplorer/blob/master/documentation/mockup_5.png "Team details page")
