# Käyttöohje

Lataa tiedosto [HelloFolio.jar](https://github.com/figgislol/otm-harjoitustyo/releases)

## Ohjelman käynnistäminen

Ohjelma käynnistetään joko komennolla

```
java -jar HelloFolio.jar
```

tai

```
mvn -e compile exec:java -Dexec.mainClass=hellofolio.HelloFolio
```

Ohjelma kysyy aloitettaessa, haluaako käyttäjä kirjautua sisään, johon voi vastata komennoilla y/n. Tarvittaessa voi luoda uuden käyttäjän komennolla new. 
Vastatessa esimerkiksi komennolla n, saadaan käyttäjäksi *basic*-tili, joka sisältää tiedon tämän hetkisestä top 10 kryptovaluutasta markkinoilla.

Kun tiliin ollaan kirjauduttu, niin voidaan antaa komentoja show/add/remove/exit. Komento *show* näyttää nykyisen portfolion tilan, ja kertoo kuinka paljon kaikki omistamat valuutat ovat arvoltaan yhteensä bitcoineina ja dollareina. Käyttäjien portfolioon lisätään valuuttoja antamalla komento *add*, jonka jälkeen 
spesifioidaan valuutan nimi (pienillä kirjaimilla) esim. ethereum, jonka jälkeen ohjelma kysyy kuinka monta kappaletta lisätään.

Lukumäärän jälkeen valuutta talletetaan portfolioon, ja nyt sen arvoa voidaan seurata komennolla *show*.

Tarvittaessa portfoliosta voi myös poistaa valuuttoja. Tämä tapahtuu antamalla konsolille komento *remove*, jonka jälkeen spesifioidaan mitä on poistettava,
esim. ethereum, ja tämän jälkeen ohjelma kysyy kuinka monta kappaletta kyseistä valuuttaa poistetaan. Antamalla komennon *-1* valuutan voi poistaa kokonaan 
portfoliosta, siten että tämä ei enää näy komennon *show* jälkeen.h
