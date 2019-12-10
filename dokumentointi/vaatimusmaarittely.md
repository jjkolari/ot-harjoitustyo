# Vaatimusmäärittely

## Sovelluksen tarkoitus

Sovelluksen avulla käyttäjien on mahdollista pitää kirjaa omasta kuukausittaisesta budjetistaan. Sovellusta on mahdollista käyttää 
useamman rekisteröityneen käyttäjän, joilla kaikilla on oma yksilöllinen budjettinsa.

## Perusversion tarjoama toiminnallisuus

### Ennen kirjautumista

- käyttäjä voi luoda järjestelmään käyttäjätunnuksen
  - käyttäjätunnuksen täytyy olla uniikki ja pituudeltaan vähintään 3 merkkiä

- käyttäjä voi kirjautua järjestelmään
  - kirjautuminen onnistuu syötettäessä olemassaoleva käyttäjätunnus kirjautumislomakkeelle "tehty osittain - onnistuu nyt käyttäjätunnuksella jjkolari"
  - jos käyttäjää ei olemassa, ilmoittaa järjestelmä tästä "tehty"

### Kirjautumisen jälkeen

- käyttäjä näkee kuukausittaiset omat tulonsa, menonsa sekä niiden välisen suhteen kuvaamaan ns. balancea "tehty"

- käyttäjä voi lisätä tuloja sekä menoja "tehty"

- käyttäjä voi nähdä viivakaaviona lisäämiänsä tuloja ja menoja "tehty alkuun vielä hiottavaa"

- käyttäjä voi kirjautua ulos järjestelmästä

## Jatkokehitysideoita

Perusversion jälkeen järjestelmää täydennetään ajan salliessa esim. seuraavilla toiminnallisuuksilla

- käyttäjä voi määritellä pysyvät tulot ja menot, niin että ne säilyy kuukaudesta toiseen
- käyttäjä voi luokitella menoja osioihin: talous, viihde, säästö
- käyttäjä voi vaihtaa näkymää haluamaan ajanjaksoon: päivä, viikko, kuukausi, vuosi
- lisänäkymä sovellukseen, mihin on voinut määritellä suuren ostoksen mihin haluaa säästää ja nähdä miten se etenee
