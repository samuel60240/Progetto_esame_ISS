# Contribuire a Red Echoes

Grazie per l'interesse verso **Red Echoes**! Questo documento spiega come
partecipare al progetto: proporre ottimizzazioni, idee di design, nuovi capitoli
della storia o segnalare e correggere bug.

> *"Ogni dettaglio potrebbe rappresentare un indizio."* Vale anche per le segnalazioni:
> più sono precise, più è facile lavorarci.

---

## Indice
1. [Come iniziare](#come-iniziare)
2. [Flusso di lavoro con Git](#flusso-di-lavoro-con-git)
3. [Proposte di ottimizzazione](#proposte-di-ottimizzazione)
4. [Proposte di design](#proposte-di-design)
5. [Proposte per i capitoli della storia](#proposte-per-i-capitoli-della-storia)
6. [Segnalazione e revisione dei bug](#segnalazione-e-revisione-dei-bug)
7. [Revisione delle Pull Request](#revisione-delle-pull-request)
8. [Sicurezza](#sicurezza)

---

## Come iniziare

1. Leggi il [README](README.md) per conoscere storia, ambientazione e struttura dei moduli 3D.
2. Clona il repository:
   ```
   git clone <url-del-repository>
   ```
3. Installa gli strumenti usati dal progetto: **Blender** e **Python**
   (usa le stesse versioni del resto del team, indicate dal project manager).
4. Prima di iniziare un lavoro, controlla le **Issue** aperte: qualcuno potrebbe
   già occuparsene. Se non c'è, aprine una nuova.

---

## Flusso di lavoro con Git

- **Non fare commit diretti su `main`.**
- Crea un branch per ogni modifica, con un nome che ne descriva lo scopo:

  | Tipo di lavoro    | Esempio di branch           |
  |-------------------|-----------------------------|
  | Ottimizzazione    | `opt/riduzione-poligoni-bosco` |
  | Design            | `design/modulo-roccioso`    |
  | Storia            | `story/capitolo-2`          |
  | Correzione bug    | `fix/sentiero-non-allineato`|

- Scrivi messaggi di commit chiari e in italiano, al passato:
  `Aggiunto modulo lago`, `Corretto allineamento del sentiero`.
- A lavoro finito apri una **Pull Request** collegata alla Issue
  (scrivi `Closes #numero` nella descrizione).
- Non committare file temporanei di Blender (`.blend1`, `.blend2`): devono stare nel `.gitignore`.

---

## Proposte di ottimizzazione

L'obiettivo è rendere il gioco **più leggero** sia da scaricare sia da eseguire.
Apri una Issue con l'etichetta `ottimizzazione` e descrivi:

- **Cosa** vuoi ottimizzare (modello, texture, script, struttura dei file).
- **Perché**: il problema attuale (es. file troppo pesante, rallentamenti).
- **Come**: la soluzione proposta.
- **Risultato misurato**: valori prima e dopo, se possibile
  (dimensione del file, numero di poligoni, FPS).

Idee su cui lavorare:

- **Modelli 3D**
  - ridurre il numero di poligoni con il modificatore *Decimate*
  - usare livelli di dettaglio (**LOD**): modelli semplificati per gli oggetti lontani
  - eliminare facce nascoste o mai visibili dal giocatore
- **Vegetazione**
  - usare **istanze** (*linked duplicates*) per alberi e cespugli ripetuti,
    invece di copie indipendenti
  - sostituire la vegetazione lontana con piani texturizzati (*billboard*)
- **Texture**
  - ridurre la risoluzione dove non serve (es. 1K invece di 4K per oggetti piccoli)
  - riunire più texture in un unico **atlas**
  - preferire formati compressi
- **Moduli (Tiles)**
  - riutilizzare gli stessi elementi tra tessere diverse
  - caricare solo i moduli vicini al giocatore
- **Repository**
  - valutare **Git LFS** per i file `.blend` e le texture più pesanti
  - rimuovere asset non più usati

---

## Proposte di design

Per proporre nuovi ambienti, oggetti o modifiche visive apri una Issue con
l'etichetta `design`. Allegare un'immagine (schizzo, render o reference) aiuta molto.

Ogni proposta dovrebbe rispettare l'identità visiva del progetto:

- **Vegetazione rossastra**, coerente con la luce della nana rossa di Kepler-186f.
- **Elementi naturali**: specchi d'acqua, torrenti e laghi.
- **Architettura scientifica abbandonata**: strutture modulari, stazioni di ricerca, ponti logistici.

### Regole per i nuovi moduli (Tiles)

- Rispettare le **dimensioni standard** delle tessere concordate dal team.
- Il **sentiero comune** deve entrare e uscire negli stessi punti degli altri moduli,
  così le tessere si raccordano tra loro.
- Nomi dei file coerenti: `tile_<tipo>.blend` (es. `tile_bosco.blend`, `tile_lago.blend`).
- Indicare nella proposta se il modulo serve a un **enigma** o a un **evento della storia**.

### Modello di proposta

```
Titolo: [Design] Nome della proposta
Tipo: nuovo modulo / oggetto / modifica visiva / interfaccia
Descrizione: cosa si vuole aggiungere o cambiare
Ruolo nel gioco: esplorazione / enigma / narrazione
Riferimenti: immagini o schizzi allegati
```

---

## Proposte per i capitoli della storia

La trama di Red Echoes si costruisce capitolo per capitolo. Chi vuole proporre
la continuazione della storia apra una Issue con l'etichetta `storia`.

### Punti fermi da rispettare

- Il protagonista è l'**astronauta** inviato a indagare sulla spedizione scomparsa.
- La narrazione si basa su **indizi**: documenti, registrazioni audio e campioni scientifici.
- Devono emergere **informazioni contraddittorie** che mettono in dubbio la versione ufficiale.
- La storia converge verso una **scelta finale** con **due finali possibili**:
  ogni capitolo dovrebbe contribuire a preparare quella scelta.

### Modello di proposta

```
Titolo: [Storia] Capitolo N – Titolo provvisorio
Riassunto: cosa succede, in poche righe
Ambientazione: moduli coinvolti (pianura, bosco, lago, roccioso, struttura)
Indizi introdotti: documenti, audio, campioni
Enigmi: eventuali puzzle ambientali
Collegamento ai finali: quale finale rende più probabile o quali dubbi solleva
```

Le proposte vengono discusse nella Issue prima di essere scritte in modo
definitivo, così da mantenere coerente la linea temporale degli eventi.

---

## Segnalazione e revisione dei bug

### Segnalare un bug

Prima di aprire una nuova Issue, controlla che il bug non sia già stato segnalato.
Poi apri una Issue con l'etichetta `bug` e includi:

```
Titolo: [Bug] Descrizione breve
Descrizione: cosa succede
Passaggi per riprodurlo:
  1. ...
  2. ...
Comportamento atteso: cosa dovrebbe succedere
Comportamento attuale: cosa succede invece
Screenshot o video: se utili
Ambiente: sistema operativo, versione di Blender / Python
```

### Gravità

Per aiutare a stabilire le priorità, indica la gravità:

| Livello   | Significato                                          |
|-----------|------------------------------------------------------|
| `critico` | il gioco si blocca, non si avvia o si perdono dati   |
| `alto`    | una funzionalità importante non funziona             |
| `medio`   | problema fastidioso ma aggirabile                    |
| `basso`   | dettaglio grafico o di testo                         |

### Correggere un bug

1. Assegnati la Issue (o scrivi un commento) per evitare lavori doppi.
2. Crea un branch `fix/<descrizione>`.
3. Correggi il problema e verifica che non ne crei di nuovi.
4. Apri una Pull Request che spieghi la causa del bug e la soluzione adottata.

---

## Revisione delle Pull Request

Ogni Pull Request deve essere **revisionata da almeno un altro membro del team**
prima del merge. Chi revisiona controlla che:

- la modifica faccia quello che dichiara e sia collegata a una Issue;
- i moduli 3D rispettino dimensioni, sentiero comune e convenzioni sui nomi;
- non ci siano file inutili, temporanei o troppo pesanti;
- gli script Python funzionino e siano leggibili;
- la storia resti coerente con i capitoli già approvati.

I commenti di revisione devono essere costruttivi: si critica il lavoro, non la persona.

---

## Sicurezza

Per problemi di sicurezza **non aprire una Issue pubblica**: segui le istruzioni
in [SECURITY.md](SECURITY.md).

---

*Ogni contributo, grande o piccolo, aiuta a fare luce su ciò che è successo su Kepler-186f.*
