# PROJET JAVA : JEU DE DAMES

Nous avons choisis de réaliser le sujet 1 qui consiste à créer un jeu de dame en mode console.  
Pour un travail plus approfondis nous avons décider de présenter un jeu de dame en mode graphique.




## AUTEURS 

**AIN SEBA Rayane**
**CARMOUN Ryad**
**ABOU-KHADIJA Yasmine**  

## Histoire et règles standards du jeu de dames

Dérivé des jeux d'Afrique du nord, tel que l'Alquerque au VIIème siècle après J.C., c'est en Espagne que le jeu de dames commença sans doute à se répandre. Il existe de nombreuses variantes du jeu de dames dans le monde.

Le jeu se pratique sur un damier de 10 cases sur 10, orienté avec une case foncée en bas à gauche.  
Chaque joueur possède 20 pions, placés sur les cases foncées des 4 premières rangées. 

Les joueurs jouent chacun à leur tour. Les blancs commencent toujours. 

Le but du jeu est de capturer tous les pions adverses.  
Si un joueur ne peut plus bouger, même s'il lui reste des pions, il perd la partie.  
Chaque pion peut se déplacer d'une case vers l'avant en diagonale.  
Un pion arrivant sur la dernière rangée et s'y arrêtant est promu en « dame ». Il est alors doublé (on pose dessus un deuxième pion de sa couleur).  
La dame se déplace sur une même diagonale d'autant de cases qu'elle le désire, en avant et en arrière.


![jeu de dames](http://www.lecomptoirdesjeux.com/media/diagdam2.jpg)

**La prise par un pion**

 Un pion peut en prendre un autre en sautant par dessus le pion adverse pour se rendre sur la case vide située derrière celui-ci. Le pion sauté est retiré du jeu.  La prise peut également s'effectuer en arrière.  La prise est obligatoire.  
Si, après avoir pris un premier pion, vous vous retrouvez de nouveau en position de prise, vous devez continuer, jusqu'à ce que cela ne soit plus possible.

**La prise par la dame**

Puisque la dame a une plus grande marge de manœuvre, elle a aussi de plus grandes possibilités pour les prises.  
 La dame doit prendre tout pion situé sur sa diagonale (s'il y a une case libre derrière) et doit changer de direction à chaque fois qu'une nouvelle prise est possible.  On ne peut passer qu'une seule fois sur un même pion.  
En revanche, on peut passer deux fois sur la même case.




Enfin, la partie peut être déclarée nulle si aucun des deux joueurs ne peut prendre toutes les pièces adverses (par exemple 3 dames contre une).






## Fonctionnalités

 1. Lors d'une partie, le nom de chaque joueur peut être saisie

	![jeu de dames](https://media.discordapp.net/attachments/755070424299339868/917762982463832074/Capture_decran_2021-12-07_133532.png?width=923&height=671)

 2. Un système de sauvegarde de score a été mise en place.  A chaque abandon ou chaque défaite , un point est ajouté pour le vainqueur. 
    Ces différents score sont visibles dans le fichier ```gameResult.properties```, avec comme format nombre_victoire#nombre_defaite

	![jeu de dames](https://cdn.discordapp.com/attachments/755070424299339868/917885889986854982/unknown.png)
  
   3. Lors de chaque partie, un fichier texte est généré et récapitule en console tout les mouvements effectués durant celle-ci. Idéal pour analyser ses parties et améliorer vos sa part de la !

![jeu de dames](https://media.discordapp.net/attachments/755070424299339868/917762983508209674/Capture_decran_2021-12-07_134006.png?width=1353&height=670)

## Comment lancer notre jeu de dames

Premièrement, lancer le code avec l'IDE ellipse, puis appuyer sur run. Le jeu s'ouvrira sur l'écran d'accueil.

![jeu de dames](https://media.discordapp.net/attachments/755070424299339868/917762984594518106/Capture_decran_2021-12-07_135950.png)

## Lancer votre première partie

Commencer par rentrer le nom des deux joueurs et le jeu commence ! Le joueur 1 joue les blancs (rouge ici) commence donc la partie.
							
	
![jeu de dames](https://media.discordapp.net/attachments/755070424299339868/917762984296726578/Capture_decran_2021-12-07_135701.png?width=628&height=671)


## Comment jouer à notre jeu de dames graphiques

Premièrement, sélectionner un pion que vous voulez déplacer. Si vous sélectionnez un pion non-valide, un message d'erreur apparaitra.


![jeu de dames](https://media.discordapp.net/attachments/755070424299339868/917762983680155668/Capture_decran_2021-12-07_135418.png?width=913&height=671)



Lorsque que vous sélectionnez un pion déplaçable , les carrés disponibles seront affichés en bleu. De même pour les dames.


Lorsqu'une situation de capture de pion est disponible, le jeu vous proposera obligatoirement de prendre le pion par conformité au règle du jeu de dames.

![jeu de dames](https://media.discordapp.net/attachments/755070424299339868/917762983030050856/Capture_decran_2021-12-07_133741.png)

Si un pion peut être pris de plusieurs façon, le choix reviens au joueur. 
																						
![jeu de dames](https://media.discordapp.net/attachments/755070424299339868/917762984061829140/Capture_decran_2021-12-07_135602.png?width=622&height=671)

				





