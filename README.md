# OTM-harjoitustyö: HelloFolio

HelloFolio on kryptovaluuttojen hintojen seurantaa varten tehty ohjelma. Käyttäjä voi luoda oman portfolionsa ja tarkkailla omien valuuttojensa hintoja, sekä koko portfolion kokonaishintaa.

## Dokumentointi
[Käyttöohje](https://github.com/figgislol/otm-harjoitustyo/blob/master/documentation/kayttoohje.md)

[Vaatimusmäärittely](https://github.com/figgislol/otm-harjoitustyo/blob/master/documentation/vaatimusmaarittely.md)

[Työaikakirjanpito](https://github.com/figgislol/otm-harjoitustyo/blob/master/documentation/tunnit.md)

[Arkkitehtuurikuvaus](https://github.com/figgislol/otm-harjoitustyo/blob/master/documentation/arkkitehtuuri.md)

## Releaset
[Viikko 5](https://github.com/figgislol/otm-harjoitustyo/releases/tag/viikko5)

[Viikko 6](https://github.com/figgislol/otm-harjoitustyo/releases/tag/v1.0.1)

## Komentorivitoiminnot

### Testaus

Testit suoritetaan komennolla

```
mvn test
```

Testikattavuusraportti luodaan komennolla

```
mvn jacoco:report
```

Josta kattavuusraporttia voidaan tarkastella avaamalla selaimella tiedosto _./target/site/jacoco/index.html_ 

### Suoritettavan jarin generointi

Komento

```
mvn package
```

generoi hakemistoon _target_ suoritettavan jar-tiedoston HelloFolio-1.0-SNAPSHOT.jar

### Koodin ajaminen

Komento

```
mvn -e compile exec:java -Dexec.mainClass=hellofolio.Hellofolio
```

ajaa HelloFoliota terminaalissa.
