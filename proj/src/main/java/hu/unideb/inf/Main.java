package hu.unideb.inf;

import com.jthemedetecor.OsThemeDetector;
import hu.unideb.inf.model.Felhasznalo;
import org.h2.tools.Server;
import java.sql.SQLException;

public class Main {
    private static Felhasznalo bejelentkezett = new Felhasznalo();
    private static boolean bejelentkezve;
    private static boolean torolte = false;
    private static boolean kijelentkezett = false;
    private static int tema = 0;

    /*
    0 - világos
    1 - sötét
    */
    private static int sikerUzenet;
    /*
    0 - adatok sikeresen hozaadva
    1 - felhasználó sikeresen létrehozva
    2 - sikeres bejelentkezés
    3 - sikeres jelszóváltás
    4 - sikeres felhasználótörlés
    */
    private static int errorUzenet;
    /*
    0 - hibás felhasználónév
    1 - helytelen jelszó
    2 - írj be felhasználónevet
    3 - írj be jelszót
    4 - ez a felhasználónév már foglalt
    5 - írj be egy emailt
    6 - írj be egy weboldalt
    7 - két jelszó nem egyezik
    8 - eddig is ez volt a jelszavad
    9 - minimális jelszóhossz az 6 karakter
    */

    public static void main(String[] args) throws SQLException {
        startDatabase();
        MainApp.main(args);
        System.exit(0);
    }

    private static void startDatabase() throws SQLException {
        new Server().runTool("-tcp", "-web", "-ifNotExists");
    }

    public static void setBejelentkezett(Felhasznalo felhasznalo){
        bejelentkezett = felhasznalo;
    }

    public static Felhasznalo getBejelentkezett(){
        return bejelentkezett;
    }

    public static void setErrorUzenet(int i){
        errorUzenet = i;
    }

    public static int getErrorUzenet() {
        return errorUzenet;
    }

    public static void setSikerUzenet(int i){
        sikerUzenet = i;
    }

    public static int getSikerUzenet() {
        return sikerUzenet;
    }

    public static void setTema(int i){
        tema = i;
    }

    public static int getTema() {
        return tema;
    }

    public static void setBejelentkezve(boolean b)
    {
        bejelentkezve = b;
    }

    public static boolean getBejelentkezve(){
        return bejelentkezve;
    }

    public static void setTorolte(boolean b){
        torolte = b;
    }

    public static boolean getTorolte(){
        return torolte;
    }

    public static void setKijelentkezett(boolean b){
        torolte = b;
    }

    public static boolean getKijelentkezett(){
        return torolte;
    }

    public static void setAutoTheme(){
        final OsThemeDetector detector = OsThemeDetector.getDetector();
        final boolean isDarkThemeUsed = detector.isDark();
        if (isDarkThemeUsed) {
            setTema(1);
        }
        else {
            setTema(0);
        }
    }
}
