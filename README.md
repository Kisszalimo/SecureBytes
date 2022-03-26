# SecureBytes
A SecureBytes egy jelszókezelő alkalmazás, amiben a felhasználó eltárolhatja a saját internetes jelszavait, lekérdezheti, és módosíthatja azokat. Ez az alkalmazás Java-ban íródott és a JavaFX szoftverplatformot használja grafikus felületként.

## Használat
### Jogosultságok
A program képes arra, hogy több felhasználót kezeljen. Ez azt jelenti, hogy minden felhasználó létrehozhat magának a programon belül egy-egy fiókot (felhasználónév és jelszó páros), majd a saját fiókjába lementheti a saját jelszavait. Ha tehát Béla és Imre létrehoztak maguknak egy-egy saját fiókot, akkor Béla nem érheti el a saját fiókjában Imre jelszavait és fordítva sem. Mindenki csak a saját jelszavait kezelheti.

### Biztonság
A program elindításakor meg kell adnunk az adott fiókhoz tartozó helyes felhasználónév és jelszó kombinációt. Ez fogja a programot feloldani, és így fog a felhasználó hozzáférni a saját (csakis saját) jelszavaihoz. A programban lehetősége van a felhasználónak a jelszavait lekérdezni, vagy letárolni.

### Jelszavak letárolása
Amikor egy új jelszót szeretnénk letárolni, az alábbi adatokat lehet megadni:

- felhasználónév*
- jelszó*
- e-mail cím
- weboldal* (amelyikhez a letárolni kívánt fiók tartozik)
- leírás

**Kötelező megadni. Hiányos kitöltés esetén a program nem engedi elmenteni a bejegyzést.*

### Jelszavak lekérdezése
Amikor egy jelszót szeretnénk lekérdezni, lehetőség van keresésre az alábbi szempontok alapján:

- felhasználónév
- jelszó
- e-mail cím
- weboldal (amelyikhez a letárolni kívánt fiók tartozik)

![example](https://user-images.githubusercontent.com/102418063/160244574-91f31e09-95e6-4e35-82b0-5bbeba5a8a09.png)

Látható, hogy a program részsztringek esetén is képes megtalálni a keresett jelszót, nincs szükség tökéletes egyezésre. A könnyebb (kevésbé szigorú) keresés érdekében a program nem tesz különbséget kis és nagybetű, illetve ékezetek között, mert ez sok találat felesleges kiszűrését jelentené.

### Licenc

Ez a program a GPL linenc alapján került kiadásra.
