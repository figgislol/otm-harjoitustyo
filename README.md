# OTM-harjoitustyö: HelloFolio

HelloFolio on kryptovaluuttojen hintojen seurantaa varten tehty ohjelma. Käyttäjä voi luoda oman portfolionsa ja tarkkailla omien valuuttojensa hintoja, sekä koko portfolion kokonaishintaa.

## Dokumentointi
[Käyttöohje](https://github.com/figgislol/otm-harjoitustyo/blob/master/documentation/kayttoohje.md)

[Määrittelydokumentti](https://github.com/figgislol/otm-harjoitustyo/blob/master/documentation/vaatimusmaarittely.md)

[Työaikakirjanpito](https://github.com/figgislol/otm-harjoitustyo/blob/master/documentation/tunnit.md)

[Arkkitehtuurikuvaus](https://github.com/figgislol/otm-harjoitustyo/blob/master/documentation/arkkitehtuuri.md)

[Testausdokumentti](https://github.com/figgislol/otm-harjoitustyo/blob/master/documentation/testausdokumentti.md)

## Releaset
[Viikko 5](https://github.com/figgislol/otm-harjoitustyo/releases/tag/viikko5)

[Viikko 6](https://github.com/figgislol/otm-harjoitustyo/releases/tag/v1.0.1)

[Loppupalautus](https://github.com/figgislol/otm-harjoitustyo/releases/tag/v1.1.0)

## Komentorivitoiminnot

### Testaus

Testit suoritetaan komennolla

```
mvn test
```

Testikattavuusraportti luodaan komennolla

```
mvn test jacoco:report
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


### JavaDoc

JavaDoc generoidaan komennolla

```
mvn javadoc:javadoc
```

JavaDocia voi tarkastella avaamalla selaimella tiedosto *target/site/apidocs/index.html*

### Checkstyle

Avaamalla selaimella tiedosto *target/site/checkstyle.html* nähdään seuraavan komennon ajamisen jälkeen mahdolliset virheilmoitukset

```
mvn jxr:jxr checkstyle:checkstyle
```
