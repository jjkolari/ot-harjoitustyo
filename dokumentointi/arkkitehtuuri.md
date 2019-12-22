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



## Tietojen pysyväistallennus

...

### Tiedostot


### Päätoiminnallisuudet

#### Käyttäjä lisää tulon

<img src="https://raw.githubusercontent.com/jjkolari/ot-harjoitustyo/master/dokumentointi/addIncome.png" width="500">

...

## Ohjelman rakenteeseen jääneet heikkoudet

### käyttöliittymä

...

