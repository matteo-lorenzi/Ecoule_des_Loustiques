# Mon Projet

## Mini Projet Android : L'École des Loustics

Ce projet a pour objectif de réaliser une application mobile destinée aux écoles primaires. L'application permettra aux élèves de s'auto-évaluer sur des exercices mathématiques et des exercices de culture générale.

## Ressources de Formation

- Formation Android
- Guide Android
- Documentation Android
- Documentation Java
- Tutoriel Android en Français

## Présentation du Projet

L'application proposera les fonctionnalités minimales suivantes :

- Exercices mathématiques : tables de multiplication et séries d'additions simples.
- Création de comptes élèves avec prénom et nom.
- Exercices de culture générale : exercices de question/réponse en français.

## Étapes du Projet

### Étape 1 : Mise en Place et Réalisation du Workflow

- Réalisation du workflow de l'application sur papier.
- Utilisation d'intentions explicites pour naviguer entre les différentes parties de l'application.

### Étape 2 : Création des Exercices Mathématiques

- Intégration des exercices de tables de multiplication et d'additions simples.
- Séparation des données, des vues et du traitement selon le modèle MVC.

### Étape 3 : Gestion des Comptes Élèves

- Création de comptes élèves avec prénom et nom.
- Stockage des informations des comptes en base de données avec Room.
- Affichage des comptes à l'accueil de l'application avec ListView ou RecyclerView.

### Étape 4 : Création des Exercices de Culture Générale

- Proposition d'exercices de type question/réponse en français.
- Utilisation d'une base de données pour stocker les questions et les réponses.

### Étape 5 : Bonus

- Ajout de fonctionnalités supplémentaires ou d'exercices additionnels.
- Possibilité de mettre en place des niveaux de difficulté, des exercices de logique, etc.

## Fichiers Java

### AddUserActivity.java

Ce fichier contient l'activité permettant à l'utilisateur d'ajouter un nouvel utilisateur à l'application. Il gère la saisie des informations de l'utilisateur et leur sauvegarde dans la base de données.

### CourActivity.java

Cette classe représente l'activité principale de l'application. Elle affiche les différentes options de cours disponibles pour l'utilisateur.

### FelicitationsActivity.java

Cette activité est affichée lorsque l'utilisateur réussit une tâche spécifique ou atteint un objectif dans l'application. Elle affiche un message de félicitations et offre des options pour continuer ou revenir en arrière.

### MainActivity.java

L'activité principale de l'application qui affiche une liste d'utilisateurs et permet d'en ajouter de nouveaux. Elle utilise une base de données SQLite pour stocker les données des utilisateurs.

### MultiplicationActivity.java

Cette activité permet à l'utilisateur de choisir une table de multiplication. Une fois sélectionnée, l'utilisateur est redirigé vers l'activité QuestionActivity pour répondre aux questions de multiplication.

### QuestionActivity.java

Cette activité présente à l'utilisateur une série de questions de multiplication basées sur la table sélectionnée. Elle vérifie les réponses de l'utilisateur et affiche le score final à la fin du quiz.

### QuizActivity.java

Cette activité propose un quiz de questions de culture générale. Elle affiche les questions à partir d'une base de données locale et vérifie les réponses de l'utilisateur.

### UsersAdapter.java

Cet adaptateur personnalisé est utilisé pour afficher une liste d'utilisateurs dans une ListView. Il prend en charge l'affichage des informations des utilisateurs dans une vue de liste.

## Video Youtube
https://youtu.be/DN5beTscsCU