# Notacja scenariuszy

## Opis notacji
Notacja scenariuszy służy do precyzyjnego opisu wymagań funkcjonalnych za pomocą kroków, umożliwiając zrozumienie interakcji między aktorami a systemem.

### Struktura scenariusza
- **Tytuł**: [Tytuł scenariusza]
- **Aktorzy**: [Lista aktorów zaangażowanych w scenariusz]
- **Aktor systemowy**: [System lub jego moduł związany ze scenariuszem]

### Zapis scenariusza
1. Scenariusz składa się z kroków.
2. Każdy krok zawiera tekst opisujący czynność wykonywaną przez aktora.
3. Krok może zawierać pod-scenariusze o dowolnym poziomie zagłębień.
4. Krok może rozpoczynać się od słów kluczowych: IF, ELSE, FOR EACH.

### Przykładowy scenariusz:
**Tytuł**: Dodanie książki
**Aktorzy**: Bibliotekarz
**Aktor systemowy**: System

1. Bibliotekarz wybiera opcję dodania nowej pozycji książkowej.
2. Wyświetla się formularz.
3. Bibliotekarz podaje dane książki.
4. IF: Bibliotekarz pragnie dodać egzemplarze książki
   - Bibliotekarz wybiera opcję definiowania egzemplarzy.
   - System prezentuje zdefiniowane egzemplarze.
   - FOR EACH egzemplarz:
     - Bibliotekarz wybiera opcję dodania egzemplarza.
     - System prosi o podanie danych egzemplarza.
     - Bibliotekarz podaje dane egzemplarza i zatwierdza.
     - System informuje o poprawnym dodaniu egzemplarza i prezentuje zaktualizowaną listę egzemplarzy.
5. Bibliotekarz zatwierdza dodanie książki.
6. System informuje o poprawnym dodaniu książki.
