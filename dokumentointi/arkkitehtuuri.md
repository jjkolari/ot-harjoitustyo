# Arkkitehtuurikuvaus

## Rakenne

Ohjelman rakenne on jaettu kolmeen pakkaukseen:

<img src="https://github.com/jjkolari/ot-harjoitustyo/blob/master/dokumentointi/nakymat/pakkaukset.png" width="300">

* Pakkaus *budjettisovellus.ui* sisältää käyttöliittymän
* Pakkaus *budjettisovellus.domain* sisältää sovelluslogiikan
* Pakkaus *budjettisovellus.dao* sisältää tietokannan

Riippuvuudet on merkitty katkoviivanuolilla.

## Käyttöliittymä

Käyttöliittymä sisältää neljä erillistä näkymää: *kirjautuminen, rekisteröityminen, sovellus* ja *analyysi*.

Näkymät luodaan omissa metodeissaan ja ovat toteutettu omina *Scene*-olioinaan. Käyttöliittymä löytyy luokasta 
[BudjettiUi](https://github.com/jjkolari/ot-harjoitustyo/blob/master/BudjettiSovellus/src/main/java/budjettisovellus/ui/BudjettiUi.java).

## Sovelluslogiikka

Sovelluslogiikan datan muodostaa kaksi luokkaa *User* ja *Transaction*. Niiden väliset riippuvuudet:

<img src="https://github.com/jjkolari/ot-harjoitustyo/blob/master/dokumentointi/nakymat/sovelluslogiikka.png"

Luokkien suhteita kuvaava luokka/pakkauskaavio:

<img src="https://github.com/jjkolari/ot-harjoitustyo/blob/master/dokumentointi/nakymat/luokat.png"

## Tietojen pysyväistallennus

Tiedot tallennetaan BudjettiSovellus.db tietokantaan kahtena eri tietokantatauluna: *User* ja *Transact*. Luokka DatabaseDao huolehtii tietokannasta, luokka UserDao huolehtii tietokantatauluun User tapahtuvista muutoksista ja luokka TransactDao huolehtii tietokantatauluun TransactDao tapahtuvista muutoksista.

### Päätoiminnallisuudet

### Käyttäjä rekisteröityy

### Käyttäjä kirjautuu sisään

<img src="https://github.com/jjkolari/ot-harjoitustyo/blob/master/dokumentointi/nakymat/kirjautuminen.png" width="800">

...

### Käyttäjä lisää tulon

### Käyttäjä lisää menon
