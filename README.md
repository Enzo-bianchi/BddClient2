# BddClient2
TP2
A] Etude des données d’une application mobile
Dans cette partie, nous allons étudier les données d’une application mobile. En particulier, il est demandé de lister, voir structurer, les différentes données traitées dans l’application précédemment conçue. Ensuite, pour chaque donnée ou jeu de données, il s’agit d’y associer un moyens de stockage Android.
Reprenez l’application conçue dans le TD n°1, app. lié à Github ou bien app. de météo.
1. Identifier les données locales et distantes à traiter dans l’application, structurer les données complexes dans un schéma.
2. Pour chaque donnée ou ensemble de données, choisir un moyen de stockage local dans l’environnement Android.

1)
distante : Les information d'un utilisateur GitHub
locale : les informations d'un utilisateur apres une recherche sur l'API

2)
SharedPreference : un objet en JSON correspondant a l'utilisateur recherché

Toutes les autres questions sont réalisés (sauf la question bonus).

Lancer l'application, mettre un login GitHub dans la methode onCreate.
Il est enregistré dans les shared preference et afficher dans la console.
l'objet est stocké en JSON dans les SharedPreference.

La deuxieme partie sur l'interface n'à pas été réalisé
