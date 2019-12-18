# Käyttöohje

Lataa tiedosto [budjettisovellus.jar](https://github.com/mluukkai/OtmTodoApp/releases/tag/0.1)

## Konfigurointi

Ohjelmaan ei tarvitse konfigurointia, sillä ohjelman käyttämä tietokanta users.db luodaan, jos se puuttuu.

## Ohjelman käynnistäminen

Ohjelma käynnistetään komennolla 

```
java -jar budjettisovellus.jar
```

## Kirjautuminen

Sovellus käynnistyy kirjautumisnäkymään:

<img src="https://raw.githubusercontent.com/mluukkai/OtmTodoApp/master/dokumentaatio/kuvat/k-1.png" width="400">

Voit kirjautua sovellukseen kirjoittamalla olemassaoleva käyttäjätunnus ja painamalla 'Kirjaudu'.

## Rekisteröinti

Kirjautumisnäkymästä on mahdollista siirtyä uuden käyttäjän luomisnäkymään panikkeella 'Uusi tunnus'.

Uusi käyttäjä luodaan syöttämällä haluama käyttäjätunnus tekstikenttään ja painamalla 'Valmis'

<img src="https://raw.githubusercontent.com/mluukkai/OtmTodoApp/master/dokumentaatio/kuvat/k-2.png" width="400">

Jos käyttäjän luominen onnistuu, ohjelma jatkaa itse sovellukseen juuri rekistöröidyllä käyttäjätunnuksella sisäänkirjautuneena.

## Menojen ja tulojen lisääminen budjettiin

Onnistuneen kirjautumisen tai rekisterlinnin myötä siirrytään käyttäjien tekemättömät työt listaavaan näkymään

<img src="https://raw.githubusercontent.com/mluukkai/OtmTodoApp/master/dokumentaatio/kuvat/k-3.png" width="400">

Näkymässä on mahdollista lisätä tuloja ja menoja omaan budjettiin syöttämällä rahamäärä tekstikenttään. Huomio: Määrän tulee 
olla kokonaisluku, muuten sovellus ei hyväksy tuloa/menoa. Tämän jälkeen näkymässä on mahdollista nähdä oma rahankulutus 
'Yhteensä rahaa jäljellä: ' perästä.

Klikkaamalla näkymän alhaalta painiketta 'Analyysi' käyttäjä voi nähdä viivadiagrammina analyysin rahankäytöstään ja klikkaamalla
painiketta
'Kirjaudu ulos' käyttäjä kirjautuu ulos sovelluksesta ja sovellus palaa takaisin kirjaantumisnäkymään.


## Oman rahankäytön analysointi

Klikkaamalla painiketta 'Analyysi' käyttäjä pääsee näkemään viivadiagrammin

IMAGE

Punainen viiva näyttää sen hetkisen rahatilanteen, 
keltainen viiva näyttää käyttäjän lisäämät menot ja vihreä viiva näyttää käyttäjän lisäämät tulot aikajanassa.

Klikkaamalla painiketta 'Takaisin' käyttäjä päätyy takaisin menojen ja tulojen lisäämiseen käytettävään näkymään.
