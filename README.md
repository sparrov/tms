"# TMS - Training Management System" 

System zarządzania szkoleniami

Krótki opis systemu
System usprawniający zarządzanie prowadzeniem szkoleń.

Główne funkcje systemu:
- Rejestracja administratora, prowadzących oraz kursantów
- Kalendarz zajęć
- Udostępnianie przez prowadzących materiałów dotyczących zajęć
- Podsystem powiadomień

Technologie:
Spring + Hibernate + Frontend w Thymeleaf

Funkcjonalności:

Rejestracja
- domyślnie mamy utworzone jedno konto administratora
- administrator może tworzyć (i usuwać) konta prowadzących
- użytkownik rejestruje się poprzez formularz zgłoszeniowy – podaje nazwę kursu, a następnie
administrator akceptuje zgłoszenie lub nie

Administrator ma do dyspozycji formularze:
- do edycji kursów (tworzenie, edycja – łącznie z przypisaniem bloków zajęć, usuwanie)
- do edycji bloków zajęć (tworzenie, edycja – łącznie z przypisaniem i edycją zajęć – wszystko na
jednym formularzu, usuwanie)
- do akceptacji zgłoszeń uczestników

Kursy
- prowadzący po zalogowaniu powinien mieć na widoku listę kursów, w których prowadzi bloki zajęć
- po wybraniu kursu powinna się wyświetlić lista bloków, które w ramach tych zajęć prowadzi
- po kliknięciu wybranego bloku powinien zobaczyć listę dat zajęć wraz z tematami
- po kliknięciu wybranych zajęć, prowadzący może edytować powiadomienia przypisane do zajęć4 | 4

Uczestnik kursu powinien mieć do dyspozycji trzy widoki:
- kalendarz: na kalendarzu zaznaczone są zajęcia wraz z informacją o temacie i bloku zajęć (z
wykorzystaniem wybranej biblioteki kalendarza)
- powiadomienia: w pierwszej kolejności powinny wyświetlać się powiadomienia nieprzeczytane,
następnie przeczytane
- bloki zajęć: widok, na którym uczestnik może wybrać blok zajęć. Po wybraniu bloku zajęć
wyświetlają się zajęcia w ramach tego bloku: ich daty, tematy oraz powiadomienia przypisane do
zajęć