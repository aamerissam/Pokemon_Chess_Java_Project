Projet : Pokémon Chess

Ce projet est un jeu stratégique inspiré des échecs, mais avec des Pokémon ! L'objectif était de créer un jeu où deux joueurs s'affrontent sur un plateau de 9x9 cases. Chaque joueur a 21 Pokémon, dont un Mewtwo qui agit comme un "roi" : si le Mewtwo d'un joueur est battu, la partie est terminée, même si d'autres Pokémon sont encore en vie.

J'ai développé ce jeu en Java en utilisant JDK pour la partie graphique et la logique du jeu. Au début, j'avais pensé utiliser MG2D pour gérer l'interface graphique, mais j'ai finalement opté pour JDK avec Swing, car ça me permettait d'avoir plus de contrôle sur l'affichage et les interactions. Le plateau est affiché avec une grille où chaque Pokémon est représenté par son image (ou une initiale si l'image ne charge pas) et ses points de vie en rouge en dessous.

Les Pokémon sont placés de manière symétrique pour les deux joueurs : les lignes 0 à 2 pour le joueur 1, et les lignes 6 à 8 pour le joueur 2. Les stats des Pokémon (HP, attaque, défense, vitesse) sont chargées depuis un fichier pokedex_gen1.csv. Pendant la partie, un joueur peut sélectionner un Pokémon, puis choisir de le déplacer (une case adjacente) ou d'attaquer un Pokémon ennemi à côté. Les combats sont calculés en fonction des stats et des types des Pokémon.

Le développement a été un peu compliqué, surtout pour gérer l'interface graphique et s'assurer que le placement des Pokémon corresponde à ce qui était attendu. Mais au final, le jeu fonctionne bien et respecte les règles établies !