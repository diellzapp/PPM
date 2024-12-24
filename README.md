# Hapirri - Aplikacion për Oferta të Restoranteve

Hapirri është një aplikacion mobil që u mundëson përdoruesve të shikojnë dhe eksplorojnë ofertat më të mira të restoranteve, të krijojnë profile personale dhe të regjistrohen për të hyrë në platformë. Me një ndërfaqe të thjeshtë dhe funksionalitete të avancuara, Hapirri ofron një eksperiencë të këndshme për përdoruesit.

---

## **Çfarë Bën Ky Aplikacion?**
### Funksionalitetet Kryesore:
- **Sign In:** Identifikim duke përdorur emrin e përdoruesit dhe fjalëkalimin.
- **Sign Up:** Regjistrim për krijimin e profileve personale.
- **Profili i Përdoruesit:** Menaxhimi i të dhënave personale dhe preferencave.
- **Shikimi i Ofertave:** Eksplorimi i ofertave nga restorantet e regjistruara.
- **Animacione:** Tranzicione të qeta dhe elemente vizuale tërheqëse.
- **Validimi i Input-it:** Kontroll për të dhënat gjatë regjistrimit.
- **Notifikime:** Mesazhe mirëseardhjeje.
- **Hashing i Fjalëkalimeve:** Ruajtje e sigurt e fjalëkalimeve.
- **Mesazhe Gabimi:** Informacione për gabime gjatë përdorimit.
- **2FA:** Siguri shtesë përmes verifikimit me email.

---

## **Integrimi me Firebase**
Aplikacioni Hapirri përdor Firebase për:
- **Autentifikimin e Përdoruesve:** Firebase Authentication për Sign In dhe Sign Up.
- **Ruajtjen e të Dhënave:** Firebase Firestore për profilet dhe ofertat e restoranteve.

---

## **Karakteristikat Teknike**
- **Gjuhët e Programimit:** Java
- **SDK Version:** `compileSdkVersion 35` (Android UpsideDownCake)
- **MinSdkVersion:** 21 (Android 5.0)
- **Database:** Firebase Firestore

---

## **Frameworks dhe Biblioteka:**
- `androidx.core:core:1.15.0`
- `androidx.core:core-ktx:1.15.0`
- Firebase SDK për Android

---

## **Si të Instaloni dhe Përdorni**
1. Klono ose shkarko këtë projekt:
    ```bash
   git clone https://github.com/diellzapp/PPM
    ```
2. Hap projektin në Android Studio.
3. Sigurohu që të kesh të instaluar versionin e kërkuar të Android SDK dhe Android Emulator.
4. **Konfiguro Firebase:**
   - Krijo një projekt të ri në Firebase Console.
   - Shkarko skedarin `google-services.json` dhe vendose në dosjen `app`.
   - Aktivizo Firebase Authentication dhe Firestore Database.
5. Përpilo dhe ekzekuto aplikacionin.

---

## **Autorët:**
- **Diellza Prebreza**
- **Aladin Bajra**
- **Endi Rashica**
- **Liburna Berisha**

---

## **Screenshot-e të Aplikacionit**
HOME:
![pjesa1](https://github.com/diellzapp/images/raw/main/pjesa1.JPG)
SIGN UP:
![signup](https://github.com/diellzapp/images/raw/main/signup.JPG)
LOG IN:
![login](https://github.com/diellzapp/images/raw/main/login.JPG)
RESET PASSWORD
![reset](https://github.com/diellzapp/images/raw/main/reset.JPG)
HOME:
![pjesa2](https://github.com/diellzapp/images/raw/main/pjesa2.JPG)
PROFILI
![profili](https://github.com/diellzapp/images/raw/main/profili.JPG)
EDIT PROFILE:
![editprofile2](https://github.com/diellzapp/images/raw/main/editprofile2.JPG)
OFERTAT:
![ofertat](https://github.com/diellzapp/images/raw/main/ofertat.JPG)
OFERTA:
![detail](https://github.com/diellzapp/images/raw/main/detail.JPG)
ABOUT US:
![aboutus](https://github.com/diellzapp/images/raw/main/aboutus.JPG)


