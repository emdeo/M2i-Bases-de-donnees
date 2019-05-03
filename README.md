# REQUÊTES SQL

Introduction au SQL et à JDBC (mySQL avec JAVA).

## Table des matières

1. [Références](#references)
1. [Concepts](#concepts)

## <a href="references">Références</a>

Liens vers le site de <a href="http://staruml.io/">StarUML</a>.

Tutoriels sur <a href="http://www.ntu.edu.sg/home/ehchua/programming/java/jdbc_basic.html">JDBC</a>.

Présentation globale de JDBC sur <a href="https://fr.wikibooks.org/wiki/D%C3%A9velopper_en_Java/Introduction_%C3%A0_JDBC">WikiLivres</a>.

Bases données en libre accès sur <a href="https://www.w3schools.com/sql/trysql.asp?filename=trysql_op_equal_to">W3Schools</a>.

CRUD sur <a href="https://en.wikipedia.org/wiki/Create,_read,_update_and_delete">Wikipedia</a>.

## <a href="concepts">Concepts</a>

SQL = gestion de base de données (CRUD = create, read, update, delete). Une BD est divisiée en plusieurs tables (jeux de données)

Pratique (<a href="https://demo.phpmyadmin.net/master-config/">voir démo en ligne</a>) : dans la table 'Film', afficher les champs (colonnes) `title`, `length` et `replacement_cost`

    SELECT `title`, `length`, `replacement_cost` FROM `film` ;

## Commandes de base

**Créer** une nouvelle base de données (BD) :

    CREATE TABLE `M2i-Formation`.`Personne` ( `ID_Personne` INT NOT NULL , `Nom` VARCHAR(60) NOT NULL , `Prénom` VARCHAR(60) NOT NULL , `Téléphone` VARCHAR(16) NOT NULL , `Email` VARCHAR(150) NOT NULL , PRIMARY KEY (`ID_Personne`)) ENGINE = InnoDB;

**Insérer** (créer) une valeur dans la BD :

    INSERT
    INTO `personne`(`ID_Personne`, `Nom`, `Prénom`, `Téléphone`, `Email`)
    VALUES (123456789, "Carroll", "Alice", "0612345789", "alice.c@wonder.com");

Si l'ordre des paramètres est respecté, peut également s'écrire sous la forme suivante :

    INSERT INTO `personne`
    VALUES (123456789, "Carroll", "Alice", "0612345789", "alice.c@wonder.com");

**Supprimer** une valeur dans la BD :

    DELETE FROM `personne` WHERE `personne`.`ID_Personne` = 123456780;

**Modifier** un élément de la BD (le WHERE est nécessaire pour éviter d'appliquer la modification à tous les éléments `Téléphone`) :

    UPDATE `personne`
    SET `Téléphone` = '0712345689'
    WHERE `personne`.`ID_Personne` = 123456789;

## Comparateurs

Afficher les champs `title` et `length` dont les éléments `length` sont supérieurs à 100 :

    SELECT `title`,`length` FROM `film` WHERE `length` > 100;

## ORDER BY

Afficher les champs `title` et `length` dont les éléments `length` sont supérieurs à 100 (et classer par ordre croissant de `length`)

    SELECT `title`,`length`
    FROM `film`
    WHERE `length` > 100
    ORDER BY `length`;

## DISTINCT, COUNT

Afficher les valeurs unique de `release_year` (évite d'afficher les éléments redondants de la colonnes) :

    SELECT DISTINCT `release_year` FROM `film`;

Affiche tous les films uniques :

    SELECT COUNT(DISTINCT `title`) FROM `film`;

## WHERE

Affiche tous les films qu'on peut louer pendant moins de 4 jours :

    SELECT `title` FROM `film` WHERE `rental_duration` < 4;

Affiche les individus qui habitent à Berlin (<a href="https://www.w3schools.com/sql/sql_where.asp">source</a>):

    SELECT CustomerName FROM Customers WHERE City="Berlin";

## LIMIT

Afficher les 2 premiers éléments d'une requête :

    SELECT `Prénom` FROM `personne` limit 2;

Afficher les 10 premiers films qu'on peut louer pendant 3 jours max :

    select * from film where `rental_duration` < 4 limit 10;

## ALTER TABLE

Créer une nouvelle colonne :

    ALTER TABLE personne ADD NbVictoires INT NULL;
    ALTER TABLE personne ADD Métier VARCHAR NOT NULL;

## LIKE (et RegEx)

Sélectionner tous les éléments de `personne` dont le `Nom` commence par la lettre 'a' (puis ceux dont le prénom commence par 'j' et finit par 'y'):

    SELECT * FROM `personne` WHERE `Nom` LIKE 'a%';
    SELECT * FROM `personne` WHERE `Prénom` LIKE 'j%y';

## IN

Le mot clé IN signifie qu'on cherche toutes les conditions vraies qui s'appliquent aux éléments sélectionnés

Sélectionner tous les clients (éléments de la BD `Customers`) dont le pays d'origine est 'Germany', 'France' ou 'UK' :

    SELECT * FROM Customers
    WHERE Country IN ('Germany', 'France', 'UK');

Sélectionner tous les clients ne venant ni de 'Germany', ni de 'France', ni de 'UK' :

    SELECT * FROM Customers
    WHERE Country NOT IN ('Germany', 'France', 'UK');

Sélectionner tous les clients venant du même pays qu'un des fournisseurs (BD `Suppliers`) :

    SELECT * FROM Customers
    WHERE Country IN (SELECT Country FROM Suppliers);

## GROUP BY

Afficher la quantité de films de chaque catégorie de prix de location :

    select rental_rate, count(film_id) from film group by `rental_rate`

Résultat attendu :

<table align="center">
    <tbody>
        <tr>
            <td>rental_rate</td>
            <td>count(film_id)</td>
        </tr>
        <tr>
            <td>0.99</td>
            <td>341</td>
        </tr>
        <tr>
            <td>2.99</td>
            <td>323</td>
        </tr>
        <tr>
            <td>4.99</td>
            <td>336</td>
        </tr>
    </tbody>
</table>

Sélectionner tous les IDs et les pays de la BD `Customers`. On regroupe les IDs par pays et on affiche le décompte des ID dans chaque pays :

    SELECT COUNT(CustomerID), Country
    FROM Customers
    GROUP BY Country;

Même processus, en ordonnant les IDs par ordre croissant (décroissant = **ORDER BY COUNT(CustomerID) DESC**) :

    SELECT COUNT(CustomerID), Country
    FROM Customers
    GROUP BY Country
    ORDER BY COUNT(CustomerID);

## JOIN

Soit deux BD `Personne` et `Ecurie` ayant un nom de champs en commun (`ID`). On souhaite fusionner les BD en associant les lignes ayant le même `ID`.

BD `Ecurie` :

<table align="center">
    <tbody>
        <tr>
            <td>ID</td>
            <td>NOM</td>
            <td>PAYS</td>
        </tr>
        <tr>
            <td>1</td>
            <td>Alpha</td>
            <td>Arménie</td>
        </tr>
        <tr>
            <td>2</td>
            <td>Bravo</td>
            <td>Bouthan</td>
        </tr>
        <tr>
            <td>3</td>
            <td>Charly</td>
            <td>Corse</td>
        </tr>
    </tbody>
</table>

BD `Personne` :

<table align="center">
    <tbody>
        <tr>
            <td>ID</td>
            <td>NOM</td>
            <td>PRENOM</td>
            <td>NBVICTOIRES</td>
        </tr>
        <tr>
            <td>1</td>
            <td>Autour</td>
            <td>Alice</td>
            <td>50</td>
        </tr>
        <tr>
            <td>2</td>
            <td>Belin</td>
            <td>Bernard</td>
            <td>31</td>
        </tr>
        <tr>
            <td>3</td>
            <td>Corvet</td>
            <td>Cindy</td>
            <td>12</td>
        </tr>
    </tbody>
</table>

On sélectionne tous les éléments d'`Ecurie`, puis on les joint aux éléments de `Personne` en utilisant ID comme **relation** (mot-clé **ON**) :

    SELECT *
    FROM ecurie
    INNER JOIN personne
    ON personne.ID=ecurie.ID;

Résultat :

<table align="center">
    <tbody>
        <tr>
            <td>ID</td>
            <td>NOM</td>
            <td>PAYS</td>
            <td>ID</td>
            <td>NOM</td>
            <td>PRENOM</td>
            <td>NBVICTOIRES</td>
        </tr>
        <tr>
            <td>1</td>
            <td>Alpha</td>
            <td>Arménie</td>
            <td>1</td>
            <td>Autour</td>
            <td>Alice</td>
            <td>50</td>
        </tr>
        <tr>
            <td>2</td>
            <td>Bravo</td>
            <td>Bouthan</td>
            <td>2</td>
            <td>Belin</td>
            <td>Bernard</td>
            <td>31</td>
        </tr>
        <tr>
            <td>3</td>
            <td>Charly</td>
            <td>Corse</td>
            <td>3</td>
            <td>Corvet</td>
            <td>Cindy</td>
            <td>12</td>
        </tr>
    </tbody>
</table>

Même processus, sauf qu'on ne souhaite afficher que le `Pays` de l'`Ecurie` associé au `Nom` de la `Personne` :

    SELECT ecurie.ID, ecurie.Pays, personne.Nom
    FROM ecurie
    INNER JOIN personne
    ON ecurie.ID = personne.ID

<table align="center">
    <tbody>
        <tr>
            <td>ID</td>
            <td>PAYS</td>
            <td>NOM</td>
        </tr>
        <tr>
            <td>1</td>
            <td>Arménie</td>
            <td>Autour</td>
        </tr>
        <tr>
            <td>2</td>
            <td>Bouthan</td>
            <td>Belin</td>
        </tr>
        <tr>
            <td>3</td>
            <td>Corse</td>
            <td>Corvet</td>
        </tr>
    </tbody>
</table>

## FOREIGN KEY

Configurer une foreign key en créant une nouvelle table (par ex. "Societe") :

    CREATE TABLE Societe (
        ID_Societe int NOT NULL,
        Nom varchar NOT NULL,
        PersonID int,
        PRIMARY KEY (ID_Societe),
        FOREIGN KEY (ID_Societe) REFERENCES Personne(ID_Societe)
    ); 

Configurer une foreign key dans une table déjà créée :

    ALTER TABLE Personne ADD FOREIGN KEY (ID_Societe) REFERENCES Societe(ID_Societe)
