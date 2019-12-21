## Testausdokumentti

Ohjelmaa on testattu JUnit testiluokilla ja järjestelmää manuaalisesti.

#Yksikkö- ja integraatiotestaus

Automaattiset JUnit-testit ovat sijoittuneet 
[BudgetService](https://github.com/jjkolari/ot-harjoitustyo/blob/master/BudjettiSovellus/src/main/java/budjettisovellus/domain/BudgetService.java)-luokan
toiminallisuuden testaukseen. Testeille on kolme eri luokkaa: 
[BudgetServiceTest](https://github.com/jjkolari/ot-harjoitustyo/blob/master/BudjettiSovellus/src/test/java/budjettisovellus/domain/BudgetServiceTest.java),
[UserTest](https://github.com/jjkolari/ot-harjoitustyo/blob/master/BudjettiSovellus/src/test/java/budjettisovellus/domain/UserTest.java) ja 
[TransactTest](https://github.com/jjkolari/ot-harjoitustyo/blob/master/BudjettiSovellus/src/test/java/budjettisovellus/domain/TransactTest.java). 


Testeissä tärkeimpänä osana on ollut kirjautumisen, rekisteröitymisen ja tulojen ja menojen lisäyksen toimiminen. Testeissä hyödynnetään
 tietokantaa BudjettiSovellus.db.
 
 #Testikattavuus
 
 
